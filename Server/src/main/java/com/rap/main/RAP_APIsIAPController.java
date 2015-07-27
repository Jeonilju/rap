package com.rap.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.rap.Helper.StringPattern;
import com.rap.dao.ActivityDao;
import com.rap.dao.CategoryLDao;
import com.rap.dao.CategoryMDao;
import com.rap.dao.CategorySDao;
import com.rap.dao.IAPDao;
import com.rap.dao.PayDao;
import com.rap.dao.TimeDao;
import com.rap.dao.UserDao;
import com.rap.dao.Virtual_MainDao;
import com.rap.dao.Virtual_SubDao;
import com.rap.models.IAPInfo;
import com.rap.models.UserInfo;

@Controller
public class RAP_APIsIAPController {

	private static final Logger logger = LoggerFactory.getLogger(RAP_APIsIAPController.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private TimeDao timeDao;
	
	@Autowired
	private CategoryLDao categoryLDao;
	
	@Autowired
	private CategoryMDao categoryMDao;
	
	@Autowired
	private CategorySDao categorySDao;
	
	@Autowired
	private IAPDao iapSDao;

	@Autowired
	private Virtual_MainDao virtual_mainDao;
	
	@Autowired
	private Virtual_SubDao virtual_subDao;
	
	@Autowired
	private PayDao payDao;
	
	@RequestMapping(value = "/APIs/getCategoryL", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getCategoryL(HttpServletRequest request
			, @RequestParam("project_key") String project_key) {
		logger.info("대분류 조회");
		
		String json = new Gson().toJson(categoryLDao.select(project_key));
		return json;
	}
	
	@RequestMapping(value = "/APIs/getCategoryM", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getCategoryM(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("CategoryL") String CategoryL) {
		CategoryL = StringPattern.parseUTF(CategoryL);
		logger.info("중분류 조회: " + CategoryL);
		
		String json = new Gson().toJson(categoryMDao.select(project_key, CategoryL));
		
		return json;
	}
	
	@RequestMapping(value = "/APIs/getCategoryS", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getCategoryS(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("CategoryL") String CategoryL
			, @RequestParam("CategoryM") String CategoryM) {
		CategoryL = StringPattern.parseUTF(CategoryL);
		CategoryM = StringPattern.parseUTF(CategoryM);
		logger.info("소분류 조회: " + CategoryL + ", " + CategoryM);

		String json = new Gson().toJson(categorySDao.select(project_key, CategoryL, CategoryM));
		return json;
	}
	
	////////////////////////////////////////////////////////////////
	//////////											////////////
	//////////					IAP 정보					////////////
	//////////											////////////
	////////////////////////////////////////////////////////////////
	
	/** 대분류를 통해 아이탬 검색 */
	@RequestMapping(value = "/APIs/getIAP_CategoryL", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getIAP_CategoryL(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("CategoryL") String CategoryL) {
		logger.info("APIs Tab");

		CategoryL = StringPattern.parseUTF(CategoryL);
		String json = new Gson().toJson(iapSDao.select(project_key, CategoryL));
		
		return json;
	}
	
	/** 중분류를 통해 아이탬 검색 */
	@RequestMapping(value = "/APIs/getIAP_CategoryM", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getIAP_CategoryM(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("CategoryL") String CategoryL
			, @RequestParam("CategoryM") String CategoryM) {
		logger.info("APIs Tab");

		CategoryL = StringPattern.parseUTF(CategoryL);
		CategoryM = StringPattern.parseUTF(CategoryM);
		
		String json = new Gson().toJson(iapSDao.select(project_key, CategoryL, CategoryM));
		
		return json;
	}
	
	/** 소분류를 통해 아이탬 검색 */
	@RequestMapping(value = "/APIs/getIAP_CategoryS", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getIAP_CategoryS(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("CategoryL") String CategoryL
			, @RequestParam("CategoryM") String CategoryM
			, @RequestParam("CategoryS") String CategoryS) {
		CategoryL = StringPattern.parseUTF(CategoryL);
		CategoryM = StringPattern.parseUTF(CategoryM);
		CategoryS = StringPattern.parseUTF(CategoryS);
		logger.info("아이템 반환: " + CategoryL + ", " + CategoryM + ", " + CategoryS);
		String json = new Gson().toJson(iapSDao.select(project_key, CategoryL, CategoryM, CategoryS));
		return json;
	}
	
	////////////////////////////////////////////////////////////////
	//////////											////////////
	//////////					가상화폐 정보				////////////
	//////////											////////////
	////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/APIs/checkVirtualMain", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String checkVirtualMain(HttpServletRequest request
			, @RequestParam("project_key") String project_key) {
		logger.info("/APIs/checkVirtualMain");

		String json = new Gson().toJson(virtual_mainDao.selectOne(project_key));
		return json;
	}
	
	@RequestMapping(value = "/APIs/checkVirtualSub", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String checkVirtualSub(HttpServletRequest request
			, @RequestParam("project_key") String project_key) {
		logger.info("APIs Tab");

		String json = new Gson().toJson(virtual_subDao.selectOne(project_key));
		return json;
	}
	
	/**
	 * 사용자의 Main 가상화폐 조회
	 * */
	@RequestMapping(value = "/APIs/GetVirtualMain", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String checkVirtualSub(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User) {
		logger.info("Main 가상화폐 조회: " + project_key + ", " + User);

		UserInfo userInfo = userDao.selectUser(project_key, User);
		JSONObject jsonObj = new JSONObject();
		jsonObj.accumulate("point", userInfo.getVirtual_main());
		
		return jsonObj.toString();
	}
	
	/**
	 * 사용자의 Main 가상화폐 사용
	 * */
	@RequestMapping(value = "/APIs/UseVirtualMain", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String UseVirtualMain(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User
			, @RequestParam("money") int money) {
		logger.info("Main 가상화폐 사용");

		userDao.useVirtual_main(project_key, User, money);
		
		return "";
	}
	
	/**
	 * 사용자의 Main 가상화폐 추가
	 * */
	@RequestMapping(value = "/APIs/TakeVirtualMain", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String TakeVirtualMain(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User
			, @RequestParam("money") int money) {
		logger.info("Main 가상화폐 추가");

		userDao.getVirtual_main(project_key, User, money);
		
		return "";
	}
	
	/**
	 * 사용자의 Sub 가상화폐 조회
	 * */
	@RequestMapping(value = "/APIs/GetVirtualSub", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String GetVirtualSub(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User) {
		logger.info("Sub 가상화폐 조회" + project_key + ", " + User);

		UserInfo userInfo = userDao.selectUser(project_key, User);
		JSONObject jsonObj = new JSONObject();
		jsonObj.accumulate("point", userInfo.getVirtual_sub());
		
		return jsonObj.toString();
	}
	
	/**
	 * 사용자의 Sub 가상화폐 사용
	 * */
	@RequestMapping(value = "/APIs/UseVirtualSub", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String UseVirtualSub(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User
			, @RequestParam("money") int money) {
		logger.info("Sub 가상화폐 사용");

		userDao.useVirtual_sub(project_key, User, money);
		
		return "";
	}
	
	/**
	 * 사용자의 Sub 가상화폐 추가
	 * */
	@RequestMapping(value = "/APIs/TakeVirtualSub", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String TakeVirtualSub(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User
			, @RequestParam("money") int money) {
		logger.info("Sub 가상화폐 추가");
		userDao.getVirtual_sub(project_key, User, money);
		
		return "";
	}
	
	/** 아이템 main 화폐로 구매 */
	@RequestMapping(value = "/APIs/BuyItemByMain", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String BuyVirtualMain(HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User
			, @RequestParam("item_id") int item_pk) {
		logger.info("Main 가상화폐 결제");
		
		UserInfo info = userDao.selectUser(project_key, User);
		IAPInfo item_info = iapSDao.selectItem(project_key, item_pk);
		
		if(info != null && item_info != null){
			if(info.getVirtual_main() > item_info.getPrice_main()){
				
				userDao.useVirtual_main(project_key, User, item_info.getPrice_main());
				payDao.create(project_key, User, 1, item_info.getPrice_main(), item_info.getPk());
				
				return "200";
			}
			else{
				// 가진 돈보다 작음
				response.setStatus(401);
				return "402";
			}
		}
		else{
			response.setStatus(401);
			return "401";
		}
	}
	
	/** 아이템 sub 화폐로 구매 */
	@RequestMapping(value = "/APIs/BuyItemBySub", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String BuyVirtualSub(HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User
			, @RequestParam("item_id") int item_pk) {
		logger.info("Sub 가상화폐 결제");
		
		UserInfo info = userDao.selectUser(project_key, User);
		IAPInfo item_info = iapSDao.selectItem(project_key, item_pk);
		
		if(info != null && item_info != null){
			if(info.getVirtual_sub() > item_info.getPrice_sub()){
				
				userDao.useVirtual_sub(project_key, User, item_info.getPrice_sub());
				payDao.create(project_key, User, 2, item_info.getPrice_sub(), item_info.getPk());
				
				return "200";
			}
			else{
				// 가진 돈보다 작음
				response.setStatus(401);
				return "401";
			}
		}
		else{
			response.setStatus(401);
			return "401";
		}
	}
	
	/** 아이템 real 화폐로 구매 */
	@RequestMapping(value = "/APIs/BuyItemByReal", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String BuyVirtualReal(HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User
			, @RequestParam("item_id") int item_pk) {
		logger.info("Sub 가상화폐 결제");
		
		UserInfo info = userDao.selectUser(project_key, User);
		IAPInfo item_info = iapSDao.selectItem(project_key, item_pk);
		
		if(info != null && item_info != null){
			payDao.create(project_key, User, 3, item_info.getPrice_real(), item_info.getPk());
			return "200";
		}
		else{
			response.setStatus(401);
			return "401";
		}
	}
}
