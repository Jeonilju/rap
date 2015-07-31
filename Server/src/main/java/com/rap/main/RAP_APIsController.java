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
import com.rap.dao.ProjectDao;
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
	
	@Autowired
	private ProjectDao projectDao;
	
	@RequestMapping(value = "/APIs", method = RequestMethod.GET)
	public String APIs_GET(HttpServletRequest request) {
		logger.info("APIs Tab");
		
		return "APIs";
	}
	
	@RequestMapping(value = "/APIs/GCM_TEST", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String gcmTest(HttpServletRequest request) {
		logger.info("APIs Tab");
		
		return "APIs";
	}
}
