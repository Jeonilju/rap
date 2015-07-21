package com.rap.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rap.dao.CategoryLDao;
import com.rap.dao.CategoryMDao;
import com.rap.dao.CategorySDao;
import com.rap.dao.UserDao;
import com.rap.models.ProjectInfo;

import net.sf.json.JSONObject;

@Controller
public class RAP_AnalysisController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_AnalysisController.class);
	
	@Autowired
	private CategoryLDao categoryLDao;
	   
	@Autowired
	private CategoryMDao categoryMDao;
	   
	@Autowired
	private CategorySDao categorySDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/Analysis", method = RequestMethod.GET)
	public String TCManagement_GET(HttpServletRequest request) {
		logger.info("Analysis Tab");
		
		return "Analysis";
	}
	
	@RequestMapping(value = "/sex_db", method = RequestMethod.POST)
	@ResponseBody
	public String Sex_Get(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Sex_db Tab");
		
		JSONObject jObject= new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject=(ProjectInfo)session.getAttribute("currentproject");
		
		if(currentproject ==null)
			return "1";//세션에 프로젝트 없는 경우
		
		  
		String project_key=currentproject.getPk();
		
	      if (project_key == null)
	          return "2";
	       if (project_key.isEmpty())
	          return "2";
	       
	       
		int woman= userDao.countSex(project_key,0);
		int man= userDao.countSex(project_key,1);
		jObject.put("woman", woman);
		jObject.put("man", man);
		return jObject.toString();
	}
	
	
	@RequestMapping(value = "/age_db", method = RequestMethod.POST)
	@ResponseBody
	public String Age_get(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Age_db Tab");
		
		JSONObject jObject= new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject=(ProjectInfo)session.getAttribute("currentproject");
		
		if(currentproject ==null)
			return "1";//세션에 프로젝트 없는 경우
		String project_key=currentproject.getPk();
		
	      if (project_key == null)
	          return "2";
	       if (project_key.isEmpty())
	          return "2";
	       

			logger.info("basdgag");
			int baby= userDao.countAge(project_key,"baby");

			logger.info("baby : "+baby);
		
			int ten= userDao.countAge(project_key,"ten");
			int twenty= userDao.countAge(project_key,"twenty");
			int thirty= userDao.countAge(project_key,"thirty");
			int forty= userDao.countAge(project_key,"forty");
			int old= userDao.countAge(project_key,"old");

		

			jObject.put("baby", baby);
			jObject.put("ten",ten );
			jObject.put("twenty", twenty);
			jObject.put("thirty", thirty);
			jObject.put("forty", forty);
			jObject.put("old",old);
			
		return jObject.toString();
	}
	
	
	
	
	
	/*@RequestMapping(value = "/operation_count_db", method = RequestMethod.POST)
	@ResponseBody
	public String operation_count_Get(HttpServletRequest request, HttpServletResponse response) {
		logger.info("operation_count Tab");
		
		JSONObject jObject= new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject=(ProjectInfo)session.getAttribute("currentproject");
		
		if(currentproject ==null)
			return "1";//세션에 프로젝트 없는 경우
		
		  
		String project_key=currentproject.getPk();
		
	      if (project_key == null)
	          return "2";
	       if (project_key.isEmpty())
	          return "2";
	       
	       
		int woman= userDao.countoperation_count(project_key,0);
		int man= userDao.countoperation_count(project_key,1);
		
		
		jObject.put("woman", woman);
		jObject.put("man", man);
		
		
		
		
		return jObject.toString();
	}*/
	
	
	
	@RequestMapping(value = "/device_db", method = RequestMethod.POST)
	@ResponseBody
	public String Device_Get(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Device_db Tab");
		
		JSONObject jObject= new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject=(ProjectInfo)session.getAttribute("currentproject");
		
		if(currentproject ==null)
			return "1";//세션에 프로젝트 없는 경우
		
		  
		String project_key=currentproject.getPk();
		
	      if (project_key == null)
	          return "2";
	       if (project_key.isEmpty())
	          return "2";
	       
		jObject.put("Device", userDao.countDevice(project_key));
		return jObject.toString();
	}
	
	
	@RequestMapping(value = "/os_db", method = RequestMethod.POST)
	@ResponseBody
	public String OS_Get(HttpServletRequest request, HttpServletResponse response) {
		logger.info("OS_db Tab");
		
		JSONObject jObject= new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject=(ProjectInfo)session.getAttribute("currentproject");
		
		if(currentproject ==null)
			return "1";//세션에 프로젝트 없는 경우
		
		  
		String project_key=currentproject.getPk();
		
	      if (project_key == null)
	          return "2";
	       if (project_key.isEmpty())
	          return "2";
	       
	       
	
		
		
		jObject.put("OS", userDao.countOS(project_key));
		return jObject.toString();
	}
	
	
}
