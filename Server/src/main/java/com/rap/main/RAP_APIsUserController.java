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

import com.rap.dao.ActivityDao;
import com.rap.dao.CategoryLDao;
import com.rap.dao.CategoryMDao;
import com.rap.dao.CategorySDao;
import com.rap.dao.IAPDao;
import com.rap.dao.ProjectDao;
import com.rap.dao.TimeDao;
import com.rap.dao.UserDao;
import com.rap.dao.Virtual_MainDao;
import com.rap.dao.Virtual_SubDao;

@Controller
public class RAP_APIsUserController {

	private static final Logger logger = LoggerFactory.getLogger(RAP_APIsUserController.class);
	
	@Autowired
	private ProjectDao projectDao;
	
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

	/** 성별 설정 */
	@RequestMapping(value = "/APIs/User/sex", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserSex(
			HttpServletResponse response,
			@RequestParam("key") String key, 
			@RequestParam("name") String name, 
			@RequestParam("sex") int sex){
		logger.info("APIs: " + "성별 설정" );
		isRight(key, response);
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
		logger.info("APIs: " + "나이 설정");
		isRight(key, response);
		userDao.setAge(key, name, age);
		
		return "200";
	}
	
	/** 위치 설정 */
	@RequestMapping(value = "/APIs/User/location", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserLocation(
			HttpServletResponse response,
			@RequestParam("key") String key, 
			@RequestParam("name") String name, 
			@RequestParam("lon") double lon, 
			@RequestParam("lat") double lat){
		logger.info("APIs: " + "위치 설정");
		isRight(key, response);
		userDao.setPosition(key, name, lat, lon);
		
		return "200";
	}
	
	/** OS정보 설정 */
	@RequestMapping(value = "/APIs/User/os", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserOs(
			HttpServletResponse response,
			 @RequestParam("key") String key, 
			 @RequestParam("name") String name, 
			 @RequestParam("os_version") String os_version){
		logger.info("APIs: " + "OS정보 설정");
		isRight(key, response);
		userDao.setOsVersion(key, name, os_version);
		
		return "";
	}
	
	/** GCM 정보 설정 */
	@RequestMapping(value = "/APIs/User/gcm", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserGCM(
			HttpServletResponse response,
			 @RequestParam("key") String key, 
			 @RequestParam("name") String name, 
			 @RequestParam("gcm_id") String gcm_id){
		logger.info("APIs: " + "GCM 정보 설정");
		isRight(key, response);
		userDao.setGCM(key, name, gcm_id);
		
		return "";
	}
	
	/** 기기정보 설정 */
	@RequestMapping(value = "/APIs/User/device", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserDevice(
			HttpServletResponse response,
			@RequestParam("key") String key, 
			@RequestParam("name") String name, 
			@RequestParam("device_vertion") String device_vertion){
		logger.info("APIs: " + "기기 정보 설정");
		isRight(key, response);
		userDao.setDevice(key, name, device_vertion);
		
		return "";
	}
	
	/** 사용시간 설정 */
	@RequestMapping(value = "/APIs/User/time", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserUsingTime(
			HttpServletResponse response,
			@RequestParam("key") String key, 
			@RequestParam("name") String name,
			@RequestParam("startTime") long startTime, 
			@RequestParam("endTime") long endTime){
		logger.info("APIs: " + "사용 시간 설정");
		isRight(key, response);
		timeDao.create(key, name, new Timestamp(startTime), new Timestamp(endTime));
		
		return "";
	}
	
	/** 사용횟수 설정 */
	@RequestMapping(value = "/APIs/User/count", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setUserUsingCount(
			HttpServletResponse response,
			@RequestParam("key") String key, 
			@RequestParam("name") String name){
		logger.info("APIcall: " + "setUserUsingCount");
		isRight(key, response);
		userDao.appCountInc(key, name);
		
		return "";
	}
	
	/** Activity 이동 정보 설정 */
	@RequestMapping(value = "/APIs/Activity/move", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setActivityMove(
			HttpServletResponse response,
			@RequestParam("key") String key, 
			@RequestParam("name") String name, 
			@RequestParam("activity_name") String activity_name,
			@RequestParam("activityb_name") String activityb_name){
		logger.info("APIcall: " + "setActivityMove");
		isRight(key, response);
		activityDao.create(key, name, activity_name, activityb_name);
		
		return "";
	}
	
	/** GCM ID 정보 설정 */
	@RequestMapping(value = "/APIs/User/GCM", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setGCMId(
			HttpServletResponse response,
			@RequestParam("key") String key, 
			@RequestParam("name") String name, 
			@RequestParam("gcmid") String gcmid){
		logger.info("APIcall: " + "setGCMId");
		isRight(key, response);
		userDao.setGCMId(key, name, gcmid);
		
		return "";
	}
	
	/** Activity 단일 정보 설정 */
	@RequestMapping(value = "/APIs/Activity/frequency", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  setActivityMove(
			HttpServletResponse response,
			@RequestParam("key") String key, 
			@RequestParam("name") String name, 
			@RequestParam("activity_name") String activity_name){
		logger.info("APIcall: " + "setActivityMove");
		isRight(key, response);
		
		activityDao.create(key, name, activity_name);
		
		return "";
	}
	
	public boolean isRight(String project_key, HttpServletResponse response){
		
		if(projectDao.select(project_key).size() == 0){
			response.setStatus(401);
			return false;
		}
		
		return true;
	}
}
