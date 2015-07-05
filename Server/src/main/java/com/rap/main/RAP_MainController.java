package com.rap.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rap.dao.CategoryLDao;
import com.rap.dao.CategoryMDao;
import com.rap.dao.CategorySDao;
import com.rap.models.CategoryLInfo;

@Controller
public class RAP_MainController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_MainController.class);
	
	@Autowired
	private CategoryLDao categoryLDao;
	   
	@Autowired
	private CategoryMDao categoryMDao;
	   
	@Autowired
	private CategorySDao categorySDao;
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String MainController_index(HttpServletRequest request) {
		logger.info("index Page");
		
		return "index";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String MainController_register(HttpServletRequest request) {
		logger.info("register Page");
		
		return "register";
	}
	
	@RequestMapping(value = "/itemmanagement", method = RequestMethod.GET)
	public String MainController_itemmanagement(HttpServletRequest request) {
		logger.info("itemmanagement Page");
		
		return "itemmanagement";
	}
	
	@RequestMapping(value = "/projectsettings", method = RequestMethod.GET)
	public String MainController_projectsettings(HttpServletRequest request) {
		logger.info("projectsettings Page");
		
		return "projectsettings";
	}
	
	@RequestMapping(value = "/analytics", method = RequestMethod.GET)
	public String MainController_analytics(HttpServletRequest request) {
		logger.info("analytics Page");
		
		return "analytics";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String MainController_test(HttpServletRequest request) {
		logger.info("test Page");
		
		return "test";
	}
	
	/*minsu add*/
	@RequestMapping(value = "/promotions", method = RequestMethod.GET)
	public String MainController_promotions(HttpServletRequest request) {
		logger.info("promotions Page");
		
		return "promotions";
	}
	
	@RequestMapping(value = "/age", method = RequestMethod.GET)
	public String MainController_age(HttpServletRequest request) {
		logger.info("age Page");
		
		return "age";
	}
	
	@RequestMapping(value = "/sex", method = RequestMethod.GET)
	public String MainController_sex(HttpServletRequest request) {
		logger.info("sex Page");
		
		return "sex";
	}
	
	@RequestMapping(value = "/operation_count", method = RequestMethod.GET)
	public String MainController_operation_count(HttpServletRequest request) {
		logger.info("operation_count Page");
		
		return "operation_count";
	}
	
	@RequestMapping(value = "/os", method = RequestMethod.GET)
	public String MainController_os(HttpServletRequest request) {
		logger.info("os Page");
		
		return "os";
	}
	
	@RequestMapping(value = "/device", method = RequestMethod.GET)
	public String MainController_device(HttpServletRequest request) {
		logger.info("device Page");
		
		return "device";
	}
	
	@RequestMapping(value = "/operation_time", method = RequestMethod.GET)
	public String MainController_operation_time(HttpServletRequest request) {
		logger.info("operation_time Page");
		
		return "operation_time";
	}
}
