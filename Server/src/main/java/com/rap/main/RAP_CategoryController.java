package com.rap.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rap.dao.CategoryLDao;
import com.rap.dao.CategoryMDao;
import com.rap.dao.CategorySDao;
import com.rap.dao.IAPDao;
import com.rap.dao.MemberDao;
import com.rap.dao.ProjectDao;
import com.rap.dao.SettingDao;
import com.rap.dao.Virtual_MainDao;
import com.rap.dao.Virtual_SubDao;
import com.rap.models.CategoryLInfo;
import com.rap.models.CategoryMInfo;
import com.rap.models.CategorySInfo;
import com.rap.models.IAPInfo;
import com.rap.models.ProjectInfo;
import com.rap.models.SettingInfo;
import com.rap.models.Virtual_MainInfo;
import com.rap.models.Virtual_SubInfo;

import net.sf.json.JSONObject;

@Controller
public class RAP_CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_CategoryController.class);

	@Autowired
	private CategoryLDao categoryLDao;

	@Autowired
	private CategoryMDao categoryMDao;

	@Autowired
	private CategorySDao categorySDao;

	@Autowired
	private IAPDao iapDao;
	
	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private Virtual_MainDao virtual_MainDao;

	@Autowired
	private Virtual_SubDao virtual_SubDao;
	
	@Autowired
	private SettingDao settingDao;

	/** 대분류 리스트 */
	@RequestMapping(value = "/Lcategory_db", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String MainController_Lcategory_db(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Lcategory_db Page");

		JSONObject jObject = new JSONObject();

		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");
		
		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "Project Not Found";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "Project Not Found";
		if (project_key.isEmpty())
			return "Project Not Found";

		// 대분류 리스트
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);
		
		jObject.put("categoryLlist", categoryLlist);
		logger.info("Lcategory_db Page - "+jObject.toString());

		return jObject.toString();

	}

	//중분류 리스트
	@RequestMapping(value = "/Mcategory_db", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String MainController_Mcategory_db(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("categoryL") String categoryL) {
		logger.info("Mcategory_db Page");

		JSONObject jObject = new JSONObject();

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "rap : 1";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "2";
		if (project_key.isEmpty())
			return "2";

		// 대분류 리스트
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);

		if (categoryLlist == null)
			return "3";

		logger.info(categoryLlist.toString());

		int categoryLlistcount = categoryLlist.size();
		int categoryL_pk = -1;

		for (int i = 0; i < categoryLlistcount; i++) {
			if (categoryLlist.get(i).getCategoryL().equals(categoryL))
				categoryL_pk = categoryLlist.get(i).getPk();
		}

		logger.info("categoryL_pk = " + categoryL_pk);
		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key, categoryL_pk);

		jObject.put("categoryMlist", categoryMlist);
		logger.info(jObject.toString());

		return jObject.toString();
	}

	@RequestMapping(value = "/Scategory_db", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String MainController_Scategory_db(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("categoryL") String categoryL, @RequestParam("categoryM") String categoryM) {
		logger.info("Scategory_db Page");

		JSONObject jObject = new JSONObject();

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "1";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "2";
		if (project_key.isEmpty())
			return "2";

		// 대분류 리스트
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);

		if (categoryLlist == null)
			return "3";

		logger.info("categoryL = " + categoryL);
		logger.info("categoryM = " + categoryM);

		int categoryLlistcount = categoryLlist.size();
		int categoryL_pk = -1;

		for (int i = 0; i < categoryLlistcount; i++) {
			if (categoryLlist.get(i).getCategoryL().equals(categoryL))
				categoryL_pk = categoryLlist.get(i).getPk();
		}

		// 대분류 pk 존재하지 않는 경우
		if (categoryL_pk == -1)
			return "4";

		// 중분류 리스트
		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key, categoryL_pk);
		int categoryMlistcount = categoryMlist.size();
		int categoryM_pk = -1;

		for (int i = 0; i < categoryMlistcount; i++) {
			if (categoryMlist.get(i).getCategoryM().equals(categoryM))
				categoryM_pk = categoryMlist.get(i).getPk();
		}

		// 중분류 pk 존재하지 않는 경우
		if (categoryM_pk == -1)
			return "5";

		List<CategorySInfo> categorySlist = categorySDao.select(project_key, categoryM_pk);

		jObject.put("categorySlist", categorySlist);
		logger.info(jObject.toString());

		return jObject.toString();
	}

	/** 대분류 추가 */
	@RequestMapping(value = "/registerLcategory", method = RequestMethod.POST)
	@ResponseBody
	public String registerLcategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Lcategory") String Lcategory) {
		logger.info("registerLcategory pages");
		
		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "Project Not Found";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "Project Not Found";
		if (project_key.isEmpty())
			return "Project Not Found";

		logger.info("프로젝트 존재");

		// 카테고리명이 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "Enter Lcategory";
		if (Lcategory.isEmpty())
			return "Enter Lcategory";

		logger.info("카테고리명 존재");

		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key, Lcategory);
		int categoryLlistcount = categoryLlist.size();

		if(categoryLlistcount > 0)
			return "Lcategory already exist";

		logger.info("같은 이름의 카테고리 없음");
		categoryLDao.create(project_key, Lcategory);

		logger.info("대분류 등록");
		return "200";
	}

	/** 중분류 추가 */
	@RequestMapping(value = "/registerMcategory", method = RequestMethod.POST)
	@ResponseBody
	public String registerMcategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Mcategory") String Mcategory, @RequestParam("Lcategory") String Lcategory) {

		logger.info("registerMcategory pages");

		JSONObject jObject = new JSONObject();

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "Project Not Found";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "Project Not Found";
		if (project_key.isEmpty())
			return "Project Not Found";

		logger.info("프로젝트 존재");

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "Enter Lcategory";
		if (Lcategory.isEmpty())
			return "Enter Lcategory";
		logger.info("대분류 존재");

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "Enter Mcategory";
		if (Mcategory.isEmpty())
			return "Enter Mcategory";
		logger.info("중분류 존재");

		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key, Lcategory);

		if(categoryLlist == null)
			return "error";
		
		int categoryL_pk = categoryLlist.get(0).getPk();

		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key, categoryL_pk, Mcategory);
		if(categoryMlist == null)
			{		logger.info("중분류가 null");
return "error";}
		
		if(categoryMlist.size() > 0)
			{logger.info("중분류사이즈 >0");
			return "Mcategory already exist";}
		
		logger.info("같은 이름의 카테고리 없음");
		categoryMDao.create(project_key, categoryL_pk, Mcategory);

		return "200";
	}

	/** 소분류 추가 */
	@RequestMapping(value = "/registerScategory", method = RequestMethod.POST)
	@ResponseBody
	public String registerScategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Scategory") String Scategory, @RequestParam("Mcategory") String Mcategory,
			@RequestParam("Lcategory") String Lcategory) {

		logger.info("registerScategory pages");

		JSONObject jObject = new JSONObject();

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "Project Not Found";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "Project Not Found";
		if (project_key.isEmpty())
			return "Project Not Found";

		logger.info("프로젝트 존재");

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "Enter Lcategory";
		if (Lcategory.isEmpty())
			return "Enter Lcategory";
		logger.info("대분류 존재");

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "Enter Mcategory";
		if (Mcategory.isEmpty())
			return "Enter Mcategory";
		logger.info("중분류 존재");

		// 소분류가 정상적으로 들어오지 않은 경우
		if (Scategory == null)
			return "Enter Scategory";
		if (Scategory.isEmpty())
			return "Enter Scategory";
		logger.info("소분류 존재");

		//대분류
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key, Lcategory);

		if(categoryLlist == null)
			return "error";
		
		int categoryL_pk = categoryLlist.get(0).getPk();

		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key, categoryL_pk, Mcategory);
		if(categoryMlist == null)
			return "error";

		int categoryM_pk = categoryMlist.get(0).getPk();

		List<CategorySInfo> categorySlist = categorySDao.select(project_key, categoryM_pk, Scategory);
		int categorySlistcount = categorySlist.size();

		if(categorySlistcount>0)
			return "Scategory already exist";
		
		logger.info("같은 이름의 카테고리 없음");
		categorySDao.create(project_key, categoryM_pk, Scategory);

		return "200";
	}

	/** 대분류 삭제 */
	@RequestMapping(value = "/deleteLcategory", method = RequestMethod.POST)
	@ResponseBody
	public String deleteLcategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Lcategory") String Lcategory) {
		logger.info("deleteLcategory pages");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "1";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "2";
		if (project_key.isEmpty())
			return "2";

		// 대분류명 존재 X
		if (Lcategory == null)
			return "3";

		categoryLDao.delete(project_key, Lcategory);
		logger.info("대분류 삭제");

		return "200";
	}

	/** 중분류 삭제 */
	@RequestMapping(value = "/deleteMcategory", method = RequestMethod.POST)
	@ResponseBody
	public String deleteMcategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Lcategory") String Lcategory, @RequestParam("Mcategory") String Mcategory) {
		logger.info("deleteMcategory pages");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "Project Not Found";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "Project Not Found";
		if (project_key.isEmpty())
			return "Project Not Found";

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "Enter Lcategory";
		if (Lcategory.isEmpty())
			return "Enter Lcategory";

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "Enter Mcategory";
		if (Mcategory.isEmpty())
			return "Enter Mcategory";

		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key, Lcategory);
		int categoryL_pk = -1;
		
		//카테고리가 존재하지않을 때
		if(categoryLlist == null) return "error";
		
		categoryL_pk = categoryLlist.get(0).getPk();

		categoryMDao.delete(project_key, categoryL_pk, Mcategory);
		logger.info("중분류 삭제");

		return "200";
	}

	/** 소분류 삭제 */
	@RequestMapping(value = "/deleteScategory", method = RequestMethod.POST)
	@ResponseBody
	public String deleteScategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Lcategory") String Lcategory, @RequestParam("Mcategory") String Mcategory,
			@RequestParam("Scategory") String Scategory) {
		logger.info("deleteScategory pages");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "Project Not Found";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "Project Not Found";
		if (project_key.isEmpty())
			return "Project Not Found";

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "Enter Lcategory";
		if (Lcategory.isEmpty())
			return "Enter Lcategory";

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "Enter Mcategory";
		if (Mcategory.isEmpty())
			return "Enter Mcategory";

		// 소분류가 정상적으로 들어오지 않은 경우
		if (Scategory == null)
			return "Enter Scategory";
		if (Scategory.isEmpty())
			return "Enter Scategory";

		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key, Lcategory);
		int categoryL_pk = -1;
		
		//카테고리가 존재하지않을 때
		if(categoryLlist == null) return "error";
		
		categoryL_pk = categoryLlist.get(0).getPk();

		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key, categoryL_pk, Mcategory);
		int categoryM_pk = -1;
		
		//카테고리가 존재하지 않을 때
		if(categoryMlist == null) return "error";
		categoryM_pk = categoryMlist.get(0).getPk();
		
		//소분류 삭제
		categorySDao.delete(project_key, categoryM_pk, Scategory);
		logger.info("소분류 삭제");


		return "200";
	}
	
	/** 아이템 추가 */
	@RequestMapping(value = "/registerItem", method = RequestMethod.POST)
	@ResponseBody
	public String registerItem(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("ItemName") String ItemName, 
			@RequestParam("ItemDescription") String ItemDescription,
			@RequestParam("GoogleID") String GoogleID,
			@RequestParam("ItemPrice") String ItemPrice, 
			@RequestParam("Lcategory") String Lcategory,
			@RequestParam("Mcategory") String Mcategory,
			@RequestParam("Scategory") String Scategory,
			@RequestParam("Coin") String Coin) {
		
		logger.info("registerItem pages");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "error";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "error";
		if (project_key.isEmpty())
			return "error";

		logger.info("프로젝트 = "+project_key);

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "Lcategory";
		if (Lcategory.isEmpty())
			return "Lcategory";
		logger.info("대분류 = "+Lcategory);

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "Mcategory";
		if (Mcategory.isEmpty())
			return "Mcategory";
		logger.info("중분류 = "+Mcategory);

		// 소분류가 정상적으로 들어오지 않은 경우
		if (Scategory == null)
			return "Scategory";
		if (Scategory.isEmpty())
			return "Scategory";
		logger.info("소분류 = "+Scategory);

		// 아이템명이 정상적으로 들어오지 않은 경우
		if (ItemName == null)
			return "ItemName";
		if (ItemName.isEmpty())
			return "ItemName";
		logger.info("ItemName = "+ItemName);
		
		// 아이템명이 정상적으로 들어오지 않은 경우
		if (ItemDescription == null)
			return "ItemDescription";
		if (ItemDescription.isEmpty())
			return "ItemDescription";
		logger.info("ItemDescription = "+ItemDescription);

		//가격 입력값 검증
		String temp;
		for(int i=0;i<ItemPrice.length();i++)
		{
			temp=ItemPrice.substring(i,i+1);
			if(temp.equals("0")||temp.equals("1")||temp.equals("2")||temp.equals("3")
					||temp.equals("4")||temp.equals("5")||temp.equals("6")||temp.equals("7")||
					temp.equals("8")||temp.equals("9"))
			{	continue;	}
			else
				return "ItemPrice";
		}
		logger.info("ItemPrice = "+ItemPrice);
		
		//화폐가 정상적으로 입력되지 않은 경우
		if (Coin == null)
			return "Coin";
		if (Coin.isEmpty())
			return "Coin";

		logger.info("Coin = "+Coin);
		int price_real=-1;
		int price_main=-1;
		int price_sub=-1;
		
		//화폐목록
		if(Coin.equals("실제결제"))
		{
			//실제 결제인데 구글아이디가 입력되지 않은 경우
			if (GoogleID == null)
				return "GoogleID";
			if (GoogleID.isEmpty())
				return "GoogleID";
			logger.info("GoogleID = "+GoogleID);
			
			price_real = Integer.parseInt(ItemPrice);
		}
		else
		{
			//주화폐 리스트
			List<Virtual_MainInfo> mainlist = virtual_MainDao.select(project_key, Coin);
			
			//해당 이름의 주화폐까 없는 경우
			if(mainlist.isEmpty())
			{
				//부화폐 리스트
				List<Virtual_MainInfo> sublist = virtual_MainDao.select(project_key, Coin);
				//항목이 없으면 에러
				if(sublist.isEmpty())
					return "error";
				else
					price_sub = Integer.parseInt(ItemPrice);
			}
			else
				price_main = Integer.parseInt(ItemPrice);
		}
		
		iapDao.create(project_key, ItemName, price_real, price_main, price_sub, -1, "", ItemDescription, Lcategory, Mcategory, Scategory);
		
		return "200";
	}
	
	/** 아이템 리스트 */
	@RequestMapping(value = "/itemlist_db", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String itemlist_db(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Lcategory") String Lcategory, 
			@RequestParam("Mcategory") String Mcategory,
			@RequestParam("Scategory") String Scategory) {
		logger.info("itemlist_db pages");
		
		//UTF 인코딩
		response.setContentType("text/html; charset=utf-8"); 

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "error";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "error";
		if (project_key.isEmpty())
			return "error";

		logger.info("프로젝트 존재");

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "Lcategory";
		if (Lcategory.isEmpty())
			return "Lcategory";
		logger.info("대분류 존재");

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "Mcategory";
		if (Mcategory.isEmpty())
			return "Mcategory";
		logger.info("중분류 존재");

		// 소분류가 정상적으로 들어오지 않은 경우
		if (Scategory == null)
			return "Scategory";
		if (Scategory.isEmpty())
			return "Scategory";
		logger.info("소분류 존재");

		List<IAPInfo> itemlist = iapDao.select(project_key, Lcategory, Mcategory, Scategory);
		
		JSONObject jObject = new JSONObject();
				
		jObject.put("itemlist", itemlist);
		logger.info(jObject.toString());

		return jObject.toString();
	}

	/** 화폐 리스트 */
	@RequestMapping(value = "/coinlist_db", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String itemlist_db(HttpServletRequest request, HttpServletResponse response) {
		logger.info("coinlist_db pages");
		
		//UTF 인코딩
		response.setContentType("text/html; charset=utf-8"); 

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "error";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "error";
		if (project_key.isEmpty())
			return "error";

		logger.info("프로젝트 존재");

		List<Virtual_MainInfo> mainlist = virtual_MainDao.select(project_key);
		List<Virtual_SubInfo> sublist = virtual_SubDao.select(project_key);
		JSONObject jObject = new JSONObject();
				
		jObject.put("mainlist", mainlist);
		jObject.put("sublist", sublist);
		logger.info(jObject.toString());

		return jObject.toString();
	}

	/** 주화폐 등록 */
	@RequestMapping(value = "/registerVirtualMain", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String registerVirtualMain(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("virtual_main_name") String virtual_main_name, 
			@RequestParam("virtual_main_description") String virtual_main_description) {
		logger.info("registerVirtualMain pages");
		
		//UTF 인코딩
		response.setContentType("text/html; charset=utf-8"); 

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "error";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "error";
		if (project_key.isEmpty())
			return "error";

		logger.info("프로젝트 존재");

		// 이름 존재 X
		if (virtual_main_name == null)
			return "virtual_main_name";
		if (virtual_main_name.isEmpty())
			return "virtual_main_name";

		// 설명 존재 X
		if (virtual_main_description == null)
			return "virtual_main_description";
		if (virtual_main_description.isEmpty())
			return "virtual_main_description";
		
		logger.info("정상적으로 입력");
		
		List<Virtual_MainInfo> mainlist = virtual_MainDao.select(project_key);

		if(mainlist.size() > 0) return "Already Exist";
		
		virtual_MainDao.create(project_key, virtual_main_name, 0, "", virtual_main_description);
		logger.info("주화폐 생성");

		return "200";
	}

	/** 주화폐 삭제 */
	@RequestMapping(value = "/deleteVirtualMain", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String deleteVirtualMain(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("virtual_main_name") String virtual_main_name) {
		logger.info("deleteVirtualMain pages");
		
		//UTF 인코딩
		response.setContentType("text/html; charset=utf-8"); 

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "error";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "error";
		if (project_key.isEmpty())
			return "error";

		logger.info("프로젝트 존재");

		// 이름 존재 X
		if (virtual_main_name == null)
			return "virtual_main_name";
		if (virtual_main_name.isEmpty())
			return "virtual_main_name";

		logger.info("정상적으로 입력");
		
		virtual_MainDao.delete(project_key);
		logger.info("주화폐 삭제");

		return "200";
	}

	/** 부화폐 등록 */
	@RequestMapping(value = "/registerVirtualSub", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String registerVirtualSub(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("virtual_sub_name") String virtual_sub_name, 
			@RequestParam("virtual_sub_description") String virtual_sub_description) {
		logger.info("registerVirtualSub pages");
		
		//UTF 인코딩
		response.setContentType("text/html; charset=utf-8"); 

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "error";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "error";
		if (project_key.isEmpty())
			return "error";

		logger.info("프로젝트 존재");

		// 이름 존재 X
		if (virtual_sub_name == null)
			return "virtual_sub_name";
		if (virtual_sub_name.isEmpty())
			return "virtual_sub_name";

		// 설명 존재 X
		if (virtual_sub_description == null)
			return "virtual_sub_description";
		if (virtual_sub_description.isEmpty())
			return "virtual_sub_description";
		
		logger.info("정상적으로 입력");
		
		List<Virtual_SubInfo> sublist = virtual_SubDao.select(project_key);

		if(sublist.size() > 0) return "Already Exist";
		
		virtual_SubDao.create(project_key, virtual_sub_name, 0, "", virtual_sub_description);
		logger.info("부화폐 생성");

		return "200";
	}

	/** 주화폐 삭제 */
	@RequestMapping(value = "/deleteVirtualSub", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String deleteVirtualSub(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("virtual_sub_name") String virtual_sub_name) {
		logger.info("deleteVirtualSub pages");
		
		//UTF 인코딩
		response.setContentType("text/html; charset=utf-8"); 

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "error";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "error";
		if (project_key.isEmpty())
			return "error";

		logger.info("프로젝트 존재");

		// 이름 존재 X
		if (virtual_sub_name == null)
			return "virtual_main_name";
		if (virtual_sub_name.isEmpty())
			return "virtual_main_name";

		logger.info("정상적으로 입력");
		
		virtual_SubDao.delete(project_key);
		logger.info("부화폐 삭제");

		return "200";
	}
	/** 사용금액 등급 등록 */
	@RequestMapping(value = "/registerGradeMoney", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String registerGradeMoney(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("grade_moneyL") String grade_moneyL, 
			@RequestParam("grade_moneyM") String grade_moneyM, 
			@RequestParam("grade_moneyS") String grade_moneyS) {
		logger.info("registerGradeMoney pages");
		
		//UTF 인코딩
		response.setContentType("text/html; charset=utf-8"); 

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "error";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "error";
		if (project_key.isEmpty())
			return "error";

		logger.info("프로젝트 존재");

		// 존재 X
		if (grade_moneyL == null)
			return "grade_moneyL";
		if (grade_moneyL.isEmpty())
			return "grade_moneyL";

		// 존재 X
		if (grade_moneyM == null)
			return "grade_moneyM";
		if (grade_moneyM.isEmpty())
			return "grade_moneyM";
		
		// 존재 X
		if (grade_moneyS == null)
			return "grade_moneyS";
		if (grade_moneyS.isEmpty())
			return "grade_moneyS";
		
		logger.info("정상적으로 입력");
		//입력값 검증
		String temp;
		for(int i=0;i<grade_moneyL.length();i++)
		{
			temp=grade_moneyL.substring(i,i+1);
			if(temp.equals("0")||temp.equals("1")||temp.equals("2")||temp.equals("3")
					||temp.equals("4")||temp.equals("5")||temp.equals("6")||temp.equals("7")||
					temp.equals("8")||temp.equals("9"))
			{	continue;	}
			else
				return "Not Number";
		}
		for(int i=0;i<grade_moneyM.length();i++)
		{
			temp=grade_moneyM.substring(i,i+1);
			if(temp.equals("0")||temp.equals("1")||temp.equals("2")||temp.equals("3")
					||temp.equals("4")||temp.equals("5")||temp.equals("6")||temp.equals("7")||
					temp.equals("8")||temp.equals("9"))
			{	continue;	}
			else
				return "Not Number";
		}
		for(int i=0;i<grade_moneyS.length();i++)
		{
			temp=grade_moneyS.substring(i,i+1);
			if(temp.equals("0")||temp.equals("1")||temp.equals("2")||temp.equals("3")
					||temp.equals("4")||temp.equals("5")||temp.equals("6")||temp.equals("7")||
					temp.equals("8")||temp.equals("9"))
			{	continue;	}
			else
				return "Not Number";
		}
		
		int grade_moneyl = Integer.parseInt(grade_moneyL);
		int grade_moneym = Integer.parseInt(grade_moneyM);
		int grade_moneys = Integer.parseInt(grade_moneyS);
		
		if(grade_moneyl <= grade_moneym) return "Not Greater";
		if(grade_moneym <= grade_moneys) return "Not Greater";
		
		settingDao.updateGradeMoney(grade_moneyl, grade_moneym, grade_moneys, project_key);
		logger.info("업데이트 성공");
		
		return "200";
	}
	
	/** 구매 등급 금액 */
	@RequestMapping(value = "/getGradeMoney", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String getGradeMoney(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("grade_money") String grade_money) {
		logger.info("getGradeMoney pages");
		
		//UTF 인코딩
		response.setContentType("text/html; charset=utf-8"); 

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "error";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "error";
		if (project_key.isEmpty())
			return "error";

		logger.info("프로젝트 존재");

		// 프로젝트 키 존재 X
		if (grade_money == null)
			return "grade_money";
		if (grade_money.isEmpty())
			return "grade_money";
		
		List<SettingInfo> setting = settingDao.selectFromProject(project_key);
		if(setting.size() == 0)
			return "error";
		
		int result =0;
		if(grade_money.equals("L"))
		{
			result = setting.get(0).getGrade_moneyl();
		}
		else if(grade_money.equals("M"))
		{
			result = setting.get(0).getGrade_moneym();
		}
		else if(grade_money.equals("S"))
		{
			result = setting.get(0).getGrade_moneys();
		}
		else
			return "error";
		
		logger.info("result : "+result);
		return Integer.toString(result);
	}
	
	/** 사용시간 등급 등록 */
	@RequestMapping(value = "/registerGradeTime", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String registerGradeTime(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("grade_timeL") String grade_timeL, 
			@RequestParam("grade_timeM") String grade_timeM, 
			@RequestParam("grade_timeS") String grade_timeS) {
		logger.info("registerGradeTime pages");
		
		//UTF 인코딩
		response.setContentType("text/html; charset=utf-8"); 

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "error";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "error";
		if (project_key.isEmpty())
			return "error";

		logger.info("프로젝트 존재");

		// 존재 X
		if (grade_timeL == null)
			return "grade_timeL";
		if (grade_timeL.isEmpty())
			return "grade_timeL";

		// 존재 X
		if (grade_timeM == null)
			return "grade_timeM";
		if (grade_timeM.isEmpty())
			return "grade_timeM";
		
		// 존재 X
		if (grade_timeS == null)
			return "grade_timeS";
		if (grade_timeS.isEmpty())
			return "grade_timeS";
		
		logger.info("정상적으로 입력");
		//입력값 검증
		String temp;
		for(int i=0;i<grade_timeL.length();i++)
		{
			temp=grade_timeL.substring(i,i+1);
			if(temp.equals("0")||temp.equals("1")||temp.equals("2")||temp.equals("3")
					||temp.equals("4")||temp.equals("5")||temp.equals("6")||temp.equals("7")||
					temp.equals("8")||temp.equals("9"))
			{	continue;	}
			else
				return "Not Number";
		}
		for(int i=0;i<grade_timeM.length();i++)
		{
			temp=grade_timeM.substring(i,i+1);
			if(temp.equals("0")||temp.equals("1")||temp.equals("2")||temp.equals("3")
					||temp.equals("4")||temp.equals("5")||temp.equals("6")||temp.equals("7")||
					temp.equals("8")||temp.equals("9"))
			{	continue;	}
			else
				return "Not Number";
		}
		for(int i=0;i<grade_timeS.length();i++)
		{
			temp=grade_timeS.substring(i,i+1);
			if(temp.equals("0")||temp.equals("1")||temp.equals("2")||temp.equals("3")
					||temp.equals("4")||temp.equals("5")||temp.equals("6")||temp.equals("7")||
					temp.equals("8")||temp.equals("9"))
			{	continue;	}
			else
				return "Not Number";
		}
		
		int grade_timel = Integer.parseInt(grade_timeL);
		int grade_timem = Integer.parseInt(grade_timeM);
		int grade_times = Integer.parseInt(grade_timeS);
		
		if(grade_timel <= grade_timem) return "Not Greater";
		if(grade_timem <= grade_times) return "Not Greater";
		
		settingDao.updateGradeTime(grade_timel, grade_timem, grade_times, project_key);
		logger.info("업데이트 성공");
		
		return "200";
	}
	
	/** 구매 등급 금액 */
	@RequestMapping(value = "/getGradeTime", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String getGradeTime(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("grade_time") String grade_time) {
		logger.info("getGradeTime pages");
		
		//UTF 인코딩
		response.setContentType("text/html; charset=utf-8"); 

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "error";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "error";
		if (project_key.isEmpty())
			return "error";

		logger.info("프로젝트 존재");

		// 프로젝트 키 존재 X
		if (grade_time == null)
			return "grade_time";
		if (grade_time.isEmpty())
			return "grade_time";
		
		List<SettingInfo> setting = settingDao.selectFromProject(project_key);
		if(setting.size() == 0)
			return "error";
		
		int result =0;
		if(grade_time.equals("L"))
		{
			result = setting.get(0).getGrade_timel();
		}
		else if(grade_time.equals("M"))
		{
			result = setting.get(0).getGrade_timem();
		}
		else if(grade_time.equals("S"))
		{
			result = setting.get(0).getGrade_times();
		}
		else
			return "error";
		
		logger.info("result : "+result);
		return Integer.toString(result);
	}
	
	/** 사용금액 등급 수정 */
	@RequestMapping(value = "/EditGradeMoney", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String EditGradeMoney(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("grade_money_input") String grade_money_input, 
			@RequestParam("grade_money") String grade_money) {
		logger.info("EditGradeMoney pages");
		
		//UTF 인코딩
		response.setContentType("text/html; charset=utf-8"); 

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "error";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "error";
		if (project_key.isEmpty())
			return "error";

		logger.info("프로젝트 존재");

		// 존재 X
		if (grade_money_input == null)
			return "grade_money_input";
		if (grade_money_input.isEmpty())
			return "grade_money_input";

		// 존재 X
		if (grade_money == null)
			return "grade_money";
		if (grade_money.isEmpty())
			return "grade_money";
		
		logger.info("정상적으로 입력");
		
		//입력값 검증
		String temp;
		for(int i=0;i<grade_money_input.length();i++)
		{
			temp=grade_money_input.substring(i,i+1);
			if(temp.equals("0")||temp.equals("1")||temp.equals("2")||temp.equals("3")
					||temp.equals("4")||temp.equals("5")||temp.equals("6")||temp.equals("7")||
					temp.equals("8")||temp.equals("9"))
			{	continue;	}
			else
				return "Not Number";
		}
		
		int input = Integer.parseInt(grade_money_input);
		
		List<SettingInfo> setting = settingDao.selectFromProject(project_key);
		
		if(setting.size() == 0) return "error";
		int moneyl = setting.get(0).getGrade_moneyl();
		int moneym = setting.get(0).getGrade_moneym();
		int moneys = setting.get(0).getGrade_moneys();
		
		if(grade_money.equals("L"))
		{
			if(input>moneym)
				moneyl=input;
			else
				return "L"; 
		}
		else if(grade_money.equals("M"))
		{
			if(input>moneys && input<moneyl)
				moneym=input;
			else
				return "M"; 
		}
		else if(grade_money.equals("S"))
		{
			if(input<moneym)
				moneys=input;
			else
				return "S";
			
		}
		else
		{
			return "error";
		}
		
		settingDao.updateGradeMoney(moneyl, moneym, moneys, project_key);
		logger.info("업데이트 성공");
		
		return "200";
	}
}
