package com.rap.main;

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

@Controller
public class RAP_AnalysisController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_AnalysisController.class);
	
	@Autowired
	private CategoryLDao categoryLDao;
	   
	@Autowired
	private CategoryMDao categoryMDao;
	   
	@Autowired
	private CategorySDao categorySDao;
	
	@RequestMapping(value = "/Analysis", method = RequestMethod.GET)
	public String TCManagement_GET(HttpServletRequest request) {
		logger.info("Analysis Tab");
		
		return "Analysis";
	}
}
