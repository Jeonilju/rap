package com.rap.main;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rap.dao.ActivityDao;
import com.rap.dao.CategoryLDao;
import com.rap.dao.TimeDao;
import com.rap.dao.UserDao;

@Controller
public class RAP_APIsController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_APIsController.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private TimeDao timeDao;
	
	@RequestMapping(value = "/APIs", method = RequestMethod.GET)
	public String TCManagement_GET(HttpServletRequest request) {
		logger.info("APIs Tab");
		
		return "APIs";
	}
	
	
	////////////////////////////////////////////////////////////////
	//////////											////////////
	//////////					APIs					////////////
	//////////											////////////
	////////////////////////////////////////////////////////////////
	
	/** 성별 설정 */
	@RequestMapping(value = "/APIs/User/sex", method = RequestMethod.POST)
	@ResponseBody
	public String  setUserSex(
			@RequestParam("key") int key, 
			@RequestParam("name") String name, 
			@RequestParam("sex") int sex){
		logger.info("APIcall: " + "setUserSex" );
		userDao.setSex(key, name, sex);
		
		return "200";
	}
	
	/** 나이 설정 */
	@RequestMapping(value = "/APIs/User/age", method = RequestMethod.POST)
	@ResponseBody
	public String setUserAge(HttpServletRequest request,
 			HttpServletResponse response
 			, @RequestParam("name") String name
 			, @RequestParam("key") int key
 			, @RequestParam("age") int age){
		logger.info("APIcall: " + "setUserAge");
		
		userDao.setAge(key, name, age);
		
		return "200";
	}
	
	/** 위치 설정 */
	@RequestMapping(value = "/APIs/User/location", method = RequestMethod.POST)
	@ResponseBody
	public String  setUserLocation(
			@RequestParam("key") int key, 
			@RequestParam("name") String name, 
			@RequestParam("lon") double lon, 
			@RequestParam("lat") double lat){
		logger.info("APIcall: " + "setUserLocation (lat: " + lat + ", len: " + lon + ")");
		userDao.setPosition(key, name, lat, lon);
		
		return "200";
	}
	
	/** OS정보 설정 */
	@RequestMapping(value = "/APIs/User/os", method = RequestMethod.POST)
	@ResponseBody
	public String  setUserOs(
			 @RequestParam("key") int key, 
			 @RequestParam("name") String name, 
			 @RequestParam("os_version") String os_version){
		logger.info("APIcall: " + "setUserOs");
		userDao.setOsVersion(key, name, os_version);
		
		return "";
	}
	
	/** GCM 정보 설정 */
	@RequestMapping(value = "/APIs/User/gcm", method = RequestMethod.POST)
	@ResponseBody
	public String  setUserGCM(
			 @RequestParam("key") int key, 
			 @RequestParam("name") String name, 
			 @RequestParam("gcm_id") String gcm_id){
		logger.info("APIcall: " + "setUserGCM");
		userDao.setGCM(key, name, gcm_id);
		
		return "";
	}
	
	/** 기기정보 설정 */
	@RequestMapping(value = "/APIs/User/device", method = RequestMethod.POST)
	@ResponseBody
	public String  setUserDevice(
			@RequestParam("key") int key, 
			@RequestParam("name") String name, 
			@RequestParam("device_vertion") String device_vertion){
		logger.info("APIcall: " + "setUserDevice");
		userDao.setDevice(key, name, device_vertion);
		
		return "";
	}
	
	/** 사용시간 설정 */
	@RequestMapping(value = "/APIs/User/time", method = RequestMethod.POST)
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
	@RequestMapping(value = "/APIs/User/count", method = RequestMethod.POST)
	@ResponseBody
	public String  setUserUsingCount(
			@RequestParam("key") int key, 
			@RequestParam("name") String name){
		logger.info("APIcall: " + "setUserUsingCount");
		userDao.appCountInc(key, name);
		
		return "";
	}
	
	/** Activity 이동 정보 설정 */
	@RequestMapping(value = "/APIs/Activity/move", method = RequestMethod.POST)
	@ResponseBody
	public String  setActivityMove(
			@RequestParam("key") int key, 
			@RequestParam("name") String name, 
			@RequestParam("activity_name") String activity_name,
			@RequestParam("activityb_name") String activityb_name){
		logger.info("APIcall: " + "setActivityMove");
		activityDao.create(key, name, activity_name, activityb_name);
		
		return "";
	}
	
	/** Activity 단일 정보 설정 */
	@RequestMapping(value = "/APIs/Activity/frequency", method = RequestMethod.POST)
	@ResponseBody
	public String  setActivityMove(
			@RequestParam("key") int key, 
			@RequestParam("name") String name, 
			@RequestParam("activity_name") String activity_name){
		logger.info("APIcall: " + "setActivityMove");
		activityDao.create(key, name, activity_name);
		
		return "";
	}
}
