package com.rap.main;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.rap.dao.TimeDao;
import com.rap.dao.UserDao;
import com.rap.dao.Virtual_MainDao;
import com.rap.dao.Virtual_SubDao;
import com.rap.gcm.RAP_GCMManager;

@Controller
public class RAP_APIsController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_APIsController.class);
	
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
	
	@RequestMapping(value = "/APIs", method = RequestMethod.GET)
	public String APIs_GET(HttpServletRequest request) {
		logger.info("APIs Tab");
		
		return "APIs";
	}
	
	@RequestMapping(value = "/APIs/GCM_TEST", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String gcmTest(HttpServletRequest request) {
		logger.info("APIs Tab");
		
		RAP_GCMManager.getInstance().sendPush("제목", "내용", "com.rap.example.RAP_MainActivity" , userDao.select("1"));
		
		return "APIs";
	}
	
	////////////////////////////////////////////////////////////////
	//////////											////////////
	//////////					APIs					////////////
	//////////											////////////
	////////////////////////////////////////////////////////////////
	
	/** 성별 설정 */
	@RequestMapping(value = "/APIs/User/sex", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserSex(
			@RequestParam("key") String key, 
			@RequestParam("name") String name, 
			@RequestParam("sex") int sex){
		logger.info("APIcall: " + "setUserSex" );
		userDao.setSex(key, name, sex);
		
		return "200";
	}
	
	/** 나이 설정 */
	@RequestMapping(value = "/APIs/User/age", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String setUserAge(HttpServletRequest request,
 			HttpServletResponse response
 			, @RequestParam("key") String key
 			, @RequestParam("name") String name
 			, @RequestParam("age") int age){
		logger.info("APIcall: " + "setUserAge");
		
		userDao.setAge(key, name, age);
		
		return "200";
	}
	
	/** 위치 설정 */
	@RequestMapping(value = "/APIs/User/location", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserLocation(
			@RequestParam("key") String key, 
			@RequestParam("name") String name, 
			@RequestParam("lon") double lon, 
			@RequestParam("lat") double lat){
		logger.info("APIcall: " + "setUserLocation (lat: " + lat + ", len: " + lon + ")");
		userDao.setPosition(key, name, lat, lon);
		
		return "200";
	}
	
	/** OS정보 설정 */
	@RequestMapping(value = "/APIs/User/os", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserOs(
			 @RequestParam("key") String key, 
			 @RequestParam("name") String name, 
			 @RequestParam("os_version") String os_version){
		logger.info("APIcall: " + "setUserOs");
		userDao.setOsVersion(key, name, os_version);
		
		return "";
	}
	
	/** GCM 정보 설정 */
	@RequestMapping(value = "/APIs/User/gcm", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserGCM(
			 @RequestParam("key") String key, 
			 @RequestParam("name") String name, 
			 @RequestParam("gcm_id") String gcm_id){
		logger.info("APIcall: " + "setUserGCM");
		userDao.setGCM(key, name, gcm_id);
		
		return "";
	}
	
	/** 기기정보 설정 */
	@RequestMapping(value = "/APIs/User/device", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserDevice(
			@RequestParam("key") String key, 
			@RequestParam("name") String name, 
			@RequestParam("device_vertion") String device_vertion){
		logger.info("APIcall: " + "setUserDevice");
		userDao.setDevice(key, name, device_vertion);
		
		return "";
	}
	
	/** 사용시간 설정 */
	@RequestMapping(value = "/APIs/User/time", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserUsingTime(
			@RequestParam("key") int key, 
			@RequestParam("name") String name,
			@RequestParam("startTime") long startTime, 
			@RequestParam("endTime") long endTime){
		logger.info("APIcall: " + "setUserUsingTime");
		timeDao.create(key, name, new Timestamp(startTime), new Timestamp(endTime));
		
		return "";
	}
	
	/** 사용횟수 설정 */
	@RequestMapping(value = "/APIs/User/count", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserUsingCount(
			@RequestParam("key") String key, 
			@RequestParam("name") String name){
		logger.info("APIcall: " + "setUserUsingCount");
		userDao.appCountInc(key, name);
		
		return "";
	}
	
	/** Activity 이동 정보 설정 */
	@RequestMapping(value = "/APIs/Activity/move", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setActivityMove(
			@RequestParam("key") String key, 
			@RequestParam("name") String name, 
			@RequestParam("activity_name") String activity_name,
			@RequestParam("activityb_name") String activityb_name){
		logger.info("APIcall: " + "setActivityMove");
		activityDao.create(key, name, activity_name, activityb_name);
		
		return "";
	}
	
	/** GCM ID 정보 설정 */
	@RequestMapping(value = "/APIs/User/GCM", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setGCMId(
			@RequestParam("key") String key, 
			@RequestParam("name") String name, 
			@RequestParam("gcmid") String gcmid){
		logger.info("APIcall: " + "setGCMId");
		userDao.setGCMId(key, name, gcmid);
		
		return "";
	}
	
	/** Activity 단일 정보 설정 */
	@RequestMapping(value = "/APIs/Activity/frequency", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setActivityMove(
			@RequestParam("key") String key, 
			@RequestParam("name") String name, 
			@RequestParam("activity_name") String activity_name){
		logger.info("APIcall: " + "setActivityMove");
		activityDao.create(key, name, activity_name);
		
		return "";
	}
	
	////////////////////////////////////////////////////////////////
	//////////											////////////
	//////////				카테고리 정보					////////////
	//////////											////////////
	////////////////////////////////////////////////////////////////
	
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
	
	@RequestMapping(value = "/APIs/getIAP_CategoryL", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getIAP_CategoryL(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("CategoryL") String CategoryL) {
		logger.info("APIs Tab");

		String json = new Gson().toJson(iapSDao.select(project_key, CategoryL));
		
		return json;
	}
	
	@RequestMapping(value = "/APIs/getIAP_CategoryM", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getIAP_CategoryM(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("CategoryL") String CategoryL
			, @RequestParam("CategoryM") String CategoryM) {
		logger.info("APIs Tab");

		String json = new Gson().toJson(iapSDao.select(project_key, CategoryL, CategoryM));
		
		return json;
	}
	
	@RequestMapping(value = "/APIs/getIAP_CategoryS", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getIAP_CategoryS(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("CategoryL") String CategoryL
			, @RequestParam("CategoryM") String CategoryM
			, @RequestParam("CategoryS") String CategoryS) {
		logger.info("APIs Tab");

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

		String json = new Gson().toJson(virtual_mainDao.select(project_key));
		return json;
	}
	
	@RequestMapping(value = "/APIs/checkVirtualSub", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String checkVirtualSub(HttpServletRequest request
			, @RequestParam("project_key") String project_key) {
		logger.info("APIs Tab");

		String json = new Gson().toJson(virtual_subDao.select(project_key));
		return json;
	}
	
	/**
	 * 사용자의 Main 가상화폐 조회
	 * */
	@RequestMapping(value = "/APIs/GetVirtualMain", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String checkVirtualSub(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User) {
		logger.info("APIs Tab");

		String json = new Gson().toJson(userDao.selectUser(project_key, User));
		return json;
	}
	
	/**
	 * 사용자의 Main 가상화폐 사용
	 * */
	@RequestMapping(value = "/APIs/UseVirtualMain", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String UseVirtualMain(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User
			, @RequestParam("money") int money) {
		logger.info("APIs Tab");

		userDao.useVirtual_main(project_key, User, money);
		
		return "";
	}
	
	/**
	 * 사용자의 Main 가상화폐 추가
	 * */
	@RequestMapping(value = "/APIs/TakeVirtualMain", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String TakeVirtualMain(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User
			, @RequestParam("money") int money) {
		logger.info("APIs Tab");

		userDao.getVirtual_main(project_key, User, money);
		
		return "";
	}
	
	/**
	 * 사용자의 Sub 가상화폐 조회
	 * */
	@RequestMapping(value = "/APIs/GetVirtualSub", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String GetVirtualSub(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User) {
		logger.info("APIs Tab");

		String json = new Gson().toJson(userDao.selectUser(project_key, User));
		return json;
	}
	
	/**
	 * 사용자의 Sub 가상화폐 사용
	 * */
	@RequestMapping(value = "/APIs/UseVirtualSub", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String UseVirtualSub(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User
			, @RequestParam("money") int money) {
		logger.info("APIs Tab");

		userDao.useVirtual_sub(project_key, User, money);
		
		return "";
	}
	
	/**
	 * 사용자의 Sub 가상화폐 추가
	 * */
	@RequestMapping(value = "/APIs/TakeVirtualSub", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String TakeVirtualSub(HttpServletRequest request
			, @RequestParam("project_key") String project_key
			, @RequestParam("User") String User
			, @RequestParam("money") int money) {
		logger.info("APIs Tab");

		userDao.getVirtual_sub(project_key, User, money);
		
		return "";
	}
	
	
}
