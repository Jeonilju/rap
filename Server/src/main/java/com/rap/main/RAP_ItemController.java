package com.rap.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rap.dao.CategoryLDao;
import com.rap.dao.IAPDao;
import com.rap.dao.SettingDao;
import com.rap.dao.Virtual_MainDao;
import com.rap.dao.Virtual_SubDao;
import com.rap.models.CategoryLInfo;
import com.rap.models.IAPInfo;
import com.rap.models.ProjectInfo;
import com.rap.models.SettingInfo;
import com.rap.models.Virtual_MainInfo;
import com.rap.models.Virtual_SubInfo;

import net.sf.json.JSONObject;

@Controller
public class RAP_ItemController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_ItemController.class);

	@Autowired
	private IAPDao iapDao;
	
	@Autowired
	private Virtual_MainDao virtual_MainDao;

	@Autowired
	private Virtual_SubDao virtual_SubDao;
	
	@Autowired
	private SettingDao settingDao;

	@Autowired
	private CategoryLDao categoryLDao;
	
	@RequestMapping(value = "/itemmanagement", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String MainController_itemmanagement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("itemmanagement Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
		{
			response.sendRedirect("projecthome");
			return "projecthome";
		}

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "projecthome";
		if (project_key.isEmpty())
			return "projecthome";

		// 대분류 리스트
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);
		request.setAttribute("categoryLlist", categoryLlist);
		return "itemmanagement";
	}
	/** 아이템 추가 */
	@RequestMapping(value = "/registerItem",  method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String registerItem(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("ItemName") String ItemName, 
			@RequestParam("ItemDescription") String ItemDescription,
			@RequestParam("GoogleID") String GoogleID,
			@RequestParam("ItemPrice") String ItemPrice, 
			@RequestParam("Lcategory2") String Lcategory,
			@RequestParam("Mcategory2") String Mcategory,
			@RequestParam("Scategory2") String Scategory,
			@RequestParam("ImageFile") MultipartFile ImageFile,
			@RequestParam("coinlist") String Coin) throws IOException, FileUploadException {
		
		logger.info("registerItem pages");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
		{
			request.setAttribute("result", "index");
			return "index";
		}

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
		{
			request.setAttribute("result", "index");
			return "index";
		}
		if (project_key.isEmpty())
		{
			request.setAttribute("result", "index");
			return "index";
		}

		logger.info("프로젝트 = "+project_key);

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
		{
			request.setAttribute("result", "Lcategory");
			return "Lcategory";
		}
		if (Lcategory.isEmpty())
		{
			request.setAttribute("result", "Lcategory");
			return "Lcategory";
		}
		logger.info("대분류 = "+Lcategory);

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
		{
			request.setAttribute("result", "Mcategory");
			return "Mcategory";
		}
		if (Mcategory.isEmpty())
		{
			request.setAttribute("result", "Mcategory");
			return "Mcategory";
		}
		logger.info("중분류 = "+Mcategory);

		// 소분류가 정상적으로 들어오지 않은 경우
		if (Scategory == null)
		{
			request.setAttribute("result", "Scategory");
			return "Scategory";
		}
		if (Scategory.isEmpty())
		{
			request.setAttribute("result", "Scategory");
			return "Scategory";
		}
		logger.info("소분류 = "+Scategory);

		// 아이템명이 정상적으로 들어오지 않은 경우
		if (ItemName == null)
		{
			request.setAttribute("result", "ItemName");
			return "ItemName";
		}
		if (ItemName.isEmpty())
		{
			request.setAttribute("result", "ItemName");
			return "ItemName";
		}
		logger.info("ItemName = "+ItemName);
		
		// 아이템명이 정상적으로 들어오지 않은 경우
		if (ItemDescription == null)
		{
			request.setAttribute("result", "ItemDescription");
			return "ItemDescription";
		}
		if (ItemDescription.isEmpty())
		{
			request.setAttribute("result", "ItemDescription");
			return "ItemDescription";
		}
		logger.info("ItemDescription = "+ItemDescription);

		if(ItemName.length()>22)
		{
			request.setAttribute("result", "LongItemName");
			return "LongItemName";
		}
		if(ItemDescription.length()>50)
		{
			request.setAttribute("result", "LongItemDescription");
			return "LongItemDescription";
		}
		if(GoogleID.length()>25)
		{
			request.setAttribute("result", "LongGoogleID");
			return "LongGoogleID";
		}
		if(ImageFile==null)
		{
			request.setAttribute("result", "ImageFile");
			return "ImageFile";
		}
		if(ImageFile.isEmpty())
		{
			request.setAttribute("result", "ImageFile");
			return "ImageFile";
		}
		
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String now = time.getYear()+"_"+time.getMonth()+"_"+time.getDate()+"_"+time.getHours()+"_"+time.getSeconds();
		logger.info("now = "+now);
		
	      String saveDir = "";
	      String tempSavePath = request.getRealPath(File.separator) + "resources\\upload\\"; 
	      logger.info("tempSavePath = "+tempSavePath);
	      String savePath = tempSavePath.replace('\\', '/'); 
	      logger.info("savePath = "+savePath);
	       
	      File targetDir = new File(savePath);
	      if (!targetDir.exists()) {
	         targetDir.mkdirs();
	      }
	      saveDir = savePath;
	      
		String fileLocation = ImageFile.getOriginalFilename();
		String rootPath;
		
		try{
			byte[] bytes = ImageFile.getBytes();
			
			rootPath = saveDir+now+fileLocation;
			logger.info("saveDir = "+saveDir);
			logger.info("fileLocation = "+fileLocation);
			File serverFile = new File(rootPath);
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			
			logger.info("Server File Location = "+serverFile.getAbsolutePath());
		}
		catch(Exception e){
			logger.info("Exception = "+e.toString());
			request.setAttribute("result", "error");
			return "error";
		}
		
		//가격 입력값 검증
		String temp;
		if(ItemPrice.length() > 10){
			request.setAttribute("result", "LongItemPrice");
			return "LongItemPrice";
		}
		
		for(int i=0;i<ItemPrice.length();i++)
		{
			temp=ItemPrice.substring(i,i+1);
			if(temp.equals("0")||temp.equals("1")||temp.equals("2")||temp.equals("3")
					||temp.equals("4")||temp.equals("5")||temp.equals("6")||temp.equals("7")||
					temp.equals("8")||temp.equals("9"))
			{	continue;	}
			else{
				request.setAttribute("result", "ItemPrice");
				return "ItemPrice";
			}
		}
		logger.info("ItemPrice = "+ItemPrice);
		
		//화폐가 정상적으로 입력되지 않은 경우
		if (Coin == null){
			request.setAttribute("result", "Coin");
			return "Coin";
		}
		if (Coin.isEmpty()){
			request.setAttribute("result", "Coin");
			return "Coin";
		}

		logger.info("Coin = "+Coin);
		int price_real=-1;
		int price_main=-1;
		int price_sub=-1;
		int using_type=-1;
		
		//화폐목록
		if(Coin.equals("실제결제"))
		{
			//실제 결제인데 구글아이디가 입력되지 않은 경우
			if (GoogleID == null){
				request.setAttribute("result", "GoogleID");
				return "GoogleID";
			}
			if (GoogleID.isEmpty()){
				request.setAttribute("result", "GoogleID");
				return "GoogleID";
			}
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
				List<Virtual_SubInfo> sublist = virtual_SubDao.select(project_key, Coin);

				//항목이 없으면 에러
				if(sublist.isEmpty()){
					request.setAttribute("result", "error");
					return "error";
				}
				else
					price_sub = Integer.parseInt(ItemPrice);
			}
			else
				price_main = Integer.parseInt(ItemPrice);
		}
		
		if(price_main != -1)
			using_type = 1;
		else if(price_sub != -1)
			using_type = 2;
		else if(price_real != -1)
			using_type = 3;
		
		IAPInfo item = iapDao.select(project_key, Lcategory, Mcategory, Scategory, ItemName);
		logger.info("아이템 생성");

		iapDao.create(project_key, ItemName, price_real, price_main, price_sub, using_type, "resources/upload/"+now+fileLocation, ItemDescription, Lcategory, Mcategory, Scategory, GoogleID);
		request.setAttribute("result", "200");
		
		return "registerItem";
	}
	
	/** 아이템 중복 */
	@RequestMapping(value = "/overlapItem", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String overlapItem(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Lcategory2") String Lcategory, 
			@RequestParam("Mcategory2") String Mcategory, 
			@RequestParam("Scategory2") String Scategory,
			@RequestParam("ItemName") String ItemName) {
		logger.info("overlapItem pages");
		
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
		
		IAPInfo item = iapDao.select(project_key, Lcategory, Mcategory, Scategory, ItemName);
		
		if(item == null)
			return "200";
		else
			return "overlap";
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
			return "";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";

		logger.info("프로젝트 존재");

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "";
		if (Lcategory.isEmpty())
			return "";
		logger.info("대분류 존재");

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "";
		if (Mcategory.isEmpty())
			return "";
		logger.info("중분류 존재");

		// 소분류가 정상적으로 들어오지 않은 경우
		if (Scategory == null)
			return "";
		if (Scategory.isEmpty())
			return "";
		logger.info("소분류 존재");

		Virtual_MainInfo main = virtual_MainDao.selectOne(project_key);
		Virtual_SubInfo sub = virtual_SubDao.selectOne(project_key);

		List<IAPInfo> itemlist = iapDao.select(project_key, Lcategory, Mcategory, Scategory);
		
		JSONObject jObject = new JSONObject();
				
		jObject.put("itemlist", itemlist);
		jObject.put("main", main);
		jObject.put("sub", sub);
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
			return "";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";

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


		// 길이 체크
		if (virtual_main_name.length()>22)
			return "Longvirtual_main_name";

		// 길이 체크
		if (virtual_main_description.length()>50)
			return "Longvirtual_main_description";
		
		logger.info("정상적으로 입력");
		
		List<Virtual_MainInfo> mainlist = virtual_MainDao.select(project_key);

		//이미 주화폐가 존재하면 수정
		if(mainlist.size() == 1)
			virtual_MainDao.update(project_key, virtual_main_name, virtual_main_description);
		else
			return "error";
		logger.info("주화폐 수정");

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

		// 길이 체크
		if (virtual_sub_name.length()>22)
			return "Longvirtual_main_name";

		// 길이 체크
		if (virtual_sub_description.length()>50)
			return "Longvirtual_main_description";
		
		logger.info("정상적으로 입력");
		
		List<Virtual_SubInfo> sublist = virtual_SubDao.select(project_key);

		if(sublist.size() == 1)
			virtual_SubDao.update(project_key, virtual_sub_name, virtual_sub_description);
		else
			return "error";
		
		logger.info("부화폐 수정");

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
	
	/** 아이템 삭제 */
	@RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
	@ResponseBody
	public String deleteItem(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("itemname") String itemname,
			@RequestParam("Lcategory") String Lcategory,
			@RequestParam("Mcategory") String Mcategory,
			@RequestParam("Scategory") String Scategory) {
		
		logger.info("deleteItem Page");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		
		ProjectInfo project = (ProjectInfo)session.getAttribute("currentproject");
		if(project==null) return "error";
		
		String project_key = project.getPk();
		if(project_key == null) return "error";
		if(project_key.isEmpty()) return "error";
		
		if(itemname == null) return "itemname";
		if(itemname.isEmpty()) return "itemname";

		if(Lcategory == null) return "Lcategory";
		if(Lcategory.isEmpty()) return "Lcategory";
		
		if(Mcategory == null) return "Mcategory";
		if(Mcategory.isEmpty()) return "Mcategory";
		
		if(Scategory == null) return "Scategory";
		if(Scategory.isEmpty()) return "Scategory";
		
		IAPInfo item = iapDao.select(project_key, Lcategory, Mcategory, Scategory, itemname);
		if(item == null) return "error";
		
		String imagePath = item.getImagePath();
	    String tempSavePath = request.getRealPath(File.separator); 
	    String savePath = tempSavePath.replace('\\', '/'); 
	    
	    logger.info(savePath+"/"+imagePath);
		File imageFile = new File(savePath+"/"+imagePath);
		
		try{
		if(imageFile.delete())
			logger.info("파일 지우기 성공");
		else
			logger.info("파일 지우기 실패");
		}
		catch(Exception e)
		{
			logger.info("파일 익셉션");
		}
		iapDao.delete(item.getPk(), project_key);
		
		return "200";
	}

	/** 아이템*/
	@RequestMapping(value = "/getitem", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String getitem(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("name") String name, 
			@RequestParam("Lcategory") String Lcategory, 
			@RequestParam("Mcategory") String Mcategory,
			@RequestParam("Scategory") String Scategory) {
		logger.info("getitem pages");
		
		//UTF 인코딩
		response.setContentType("text/html; charset=utf-8"); 

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";

		logger.info("프로젝트 존재");

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "";
		if (Lcategory.isEmpty())
			return "";
		logger.info("대분류 존재");

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "";
		if (Mcategory.isEmpty())
			return "";
		logger.info("중분류 존재");

		// 소분류가 정상적으로 들어오지 않은 경우
		if (Scategory == null)
			return "";
		if (Scategory.isEmpty())
			return "";
		logger.info("소분류 존재");

		// 소분류가 정상적으로 들어오지 않은 경우
		if (name == null)
			return "";
		if (name.isEmpty())
			return "";
		logger.info("아이템이름 존재");
		
		IAPInfo item = iapDao.select(project_key, Lcategory, Mcategory, Scategory, name);
		if(item == null) return "";

		Virtual_MainInfo main = virtual_MainDao.selectOne(project_key);
		Virtual_SubInfo sub = virtual_SubDao.selectOne(project_key);
		
		logger.info("아이템 존재");
		
		JSONObject jObject = new JSONObject();
				
		jObject.put("item", item);
		jObject.put("main", main);
		jObject.put("sub", sub);
		logger.info(jObject.toString());

		return jObject.toString();
	}
	/** 아이템 수정 */
	@RequestMapping(value = "/editItem", method = RequestMethod.POST)
	@ResponseBody
	public String editItem(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("name") String name, 
			@RequestParam("ItemName") String ItemName, 
			@RequestParam("ItemDescription") String ItemDescription,
			@RequestParam("GoogleID") String GoogleID,
			@RequestParam("ItemPrice") String ItemPrice, 
			@RequestParam("Lcategory") String Lcategory,
			@RequestParam("Mcategory") String Mcategory,
			@RequestParam("Scategory") String Scategory,
			@RequestParam("Coin") String Coin) {
		
		logger.info("editItem pages");

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


		if(ItemName.length()>22)
			return "LongItemName";
		if(ItemDescription.length()>50)
			return "LongItemDescription";
		if(GoogleID.length()>25)
			return "LongGoogleID";
		
		//가격 입력값 검증
		String temp;
		if(ItemPrice.length() > 10)
			return "LongItemPrice";
		
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
		int using_type=-1;
		
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
				List<Virtual_SubInfo> sublist = virtual_SubDao.select(project_key, Coin);
				//항목이 없으면 에러
				if(sublist.isEmpty())
					return "error";
				else
					price_sub = Integer.parseInt(ItemPrice);
			}
			else
				price_main = Integer.parseInt(ItemPrice);
		}
		
		if(price_main != -1)
			using_type = 1;
		else if(price_sub != -1)
			using_type = 2;
		else if(price_real != -1)
			using_type = 3;
		
		IAPInfo item = iapDao.select(project_key, Lcategory, Mcategory, Scategory, name);
		if(item == null) return "error";
		iapDao.update(ItemName, price_real, price_main, price_sub, using_type, ItemDescription, GoogleID, item.getPk(), project_key);
		
		return "200";
	}
}
