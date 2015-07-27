package com.rap.main;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.rap.analysismodels.NewmemberInfo;
import com.rap.analysismodels.OPcountInfo;
import com.rap.analysismodels.OPtimeInfo;
import com.rap.analysismodels.Promotion_resultInfo;
import com.rap.dao.ActivityDao;
import com.rap.dao.CategoryLDao;
import com.rap.dao.CategoryMDao;
import com.rap.dao.CategorySDao;
import com.rap.dao.Log_timeDao;
import com.rap.dao.PromotionResultDao;
import com.rap.dao.UserDao;
import com.rap.models.BestActivityInfo;
import com.rap.models.ProjectInfo;

import net.sf.json.JSONObject;

@Controller
public class RAP_AnalysisController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_AnalysisController.class);
    private static final SimpleDateFormat monthDayYearformatter = new SimpleDateFormat(
  	      "yyyy-MM-dd");
    private static final SimpleDateFormat monthYearformatter = new SimpleDateFormat(
    	      "yyyy-MM");
    private static final SimpleDateFormat Yearformatter = new SimpleDateFormat(
  	      "yyyy");
    private static final SimpleDateFormat Hourformatter = new SimpleDateFormat(
    	      "HH");
      
	@Autowired
	private CategoryLDao categoryLDao;

	@Autowired
	private CategoryMDao categoryMDao;

	@Autowired
	private CategorySDao categorySDao;
	
	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private Log_timeDao log_timeDao;
	
	@Autowired
	private PromotionResultDao promotionResultDao;

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

		JSONObject jObject = new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		if (currentproject == null)
			return "1";// 세션에 프로젝트 없는 경우

		String project_key = currentproject.getPk();

		if (project_key == null)
			return "2";
		if (project_key.isEmpty())
			return "2";

		int woman = userDao.countSex(project_key, 0);
		int man = userDao.countSex(project_key, 1);
		jObject.put("woman", woman);
		jObject.put("man", man);
		return jObject.toString();
	}

	@RequestMapping(value = "/age_db", method = RequestMethod.POST)
	@ResponseBody
	public String Age_get(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Age_db Tab");

		JSONObject jObject = new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		if (currentproject == null)
			return "1";// 세션에 프로젝트 없는 경우
		String project_key = currentproject.getPk();

		if (project_key == null)
			return "2";
		if (project_key.isEmpty())
			return "2";

		logger.info("basdgag");
		int baby = userDao.countAge(project_key, "baby");

		logger.info("baby : " + baby);

		int ten = userDao.countAge(project_key, "ten");
		int twenty = userDao.countAge(project_key, "twenty");
		int thirty = userDao.countAge(project_key, "thirty");
		int forty = userDao.countAge(project_key, "forty");
		int old = userDao.countAge(project_key, "old");

		jObject.put("baby", baby);
		jObject.put("ten", ten);
		jObject.put("twenty", twenty);
		jObject.put("thirty", thirty);
		jObject.put("forty", forty);
		jObject.put("old", old);

		return jObject.toString();
	}

	@RequestMapping(value = "/new_member_db", method = RequestMethod.POST)
	@ResponseBody
	public String New_member_Get(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("type") String type, @RequestParam("start") String start
			) {
		
		Calendar cal;
		Timestamp starttime=null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		start = start.replace("-", "");
		String date = new String(start+"000000");
		 try {
	            sd.parse(date);
	            cal = sd.getCalendar();
	             
	             starttime = new Timestamp( cal.getTime().getTime() );
	             
	        } catch (ParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		 
		logger.info("new_member Tab" + "  type : " +type+" start : "+starttime.toString());
		
		JSONObject jObject= new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject=(ProjectInfo)session.getAttribute("currentproject");
		
	if(currentproject ==null)
			return "1";//세션에 프로젝트 없는 경우
		
		
		String project_key=currentproject.getPk();
		project_key="1";
	      if (project_key == null)
	          return "2";
	       if (project_key.isEmpty())
	          return "2";
	       
	     /*String project_key="1";*/
	       
	       
	       
	       
	       
		    
		    
		    List<NewmemberInfo> receive=userDao.count_new_member(project_key,type,starttime);
		    //List<List<String>> result=new ArrayList<List<String>>();
		    List<String> result=new ArrayList<String>();
		  
			for (int i = 0; i < receive.size(); i++) {		
				
				List<String> templist=new ArrayList<String>();
				//templist.add(0,Hourformatter.format((java.util.Date) receive.get(i).getStart()));
				
				if(type.equals("day"))
					templist.add(0,monthDayYearformatter.format((java.util.Date) receive.get(i).getStart()));
				if(type.equals("month"))
					templist.add(0,monthYearformatter.format((java.util.Date) receive.get(i).getStart()));
				if(type.equals("year"))
					templist.add(0,Yearformatter.format((java.util.Date) receive.get(i).getStart()));
				
				templist.add(1,receive.get(i).getCount()+"");
				result.add(templist.toString());
			}
			
			logger.info("NewmemberInfo : " + result.toString());
			jObject.put("result", result.toString());
			return jObject.toString();
	       
	       
	       
	   /* List<NewmemberInfo> result=userDao.count_new_member(project_key,type,starttime);
	    List<String> start_time=new ArrayList<String>();
	    List<Integer> count=new ArrayList<Integer>();*/
	
	   /* 
		for (int i = 0; i < result.size(); i++) {		
			if(type.equals("day"))
				start_time.add(i,monthDayYearformatter.format((java.util.Date) result.get(i).getStart()));
			if(type.equals("month"))
				start_time.add(i,monthYearformatter.format((java.util.Date) result.get(i).getStart()));
			if(type.equals("year"))
				start_time.add(i,Yearformatter.format((java.util.Date) result.get(i).getStart()));
			//start_time.add(i,result.get(i).getStart().getYear()+"/"+result.get(i).getStart().getMonth()+"/"+result.get(i).getStart().getDate());
			count.add(result.get(i).getCount());
		}
		logger.info("start_time" + start_time.toString());
		
		jObject.put("start_time", start_time);
		jObject.put("count", count);
		
		return jObject.toString();*/
	}
	
	@RequestMapping(value = "/operation_count_db", method = RequestMethod.POST)
	@ResponseBody
	public String Operation_count_Get(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("type") String type, @RequestParam("start") String start
			) {
		
		Calendar cal;
		Timestamp starttime=null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		start = start.replace("-", "");
		String date = new String(start+"000000");
		 try {
	            sd.parse(date);
	            cal = sd.getCalendar();
	             
	             starttime = new Timestamp( cal.getTime().getTime() );
	             
	        } catch (ParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		 
		logger.info("operation_count Tab" + "  type : " +type+" start : "+starttime.toString());
		
		JSONObject jObject= new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject=(ProjectInfo)session.getAttribute("currentproject");
		
		if(currentproject ==null)
			return "1";//세션에 프로젝트 없는 경우
		
		
		String project_key=currentproject.getPk();
		project_key="1";
	      if (project_key == null)
	          return "2";
	       if (project_key.isEmpty())
	          return "2";
	       
	       
	   /* List<OPcountInfo> result=log_timeDao.count_operation_count(project_key,type,starttime);
	    List<String> start_time=new ArrayList<String>();
	    List<Integer> count=new ArrayList<Integer>();*/
	
	    
	    
	    
	    
	    List<OPcountInfo> receive=log_timeDao.count_operation_count(project_key,type,starttime);
	    //List<List<String>> result=new ArrayList<List<String>>();
	    List<String> result=new ArrayList<String>();
	  
		for (int i = 0; i < receive.size(); i++) {		
			
			List<String> templist=new ArrayList<String>();
			//templist.add(0,Hourformatter.format((java.util.Date) receive.get(i).getStart()));
			
			if(type.equals("day"))
				templist.add(0,monthDayYearformatter.format((java.util.Date) receive.get(i).getStart()));
			if(type.equals("month"))
				templist.add(0,monthYearformatter.format((java.util.Date) receive.get(i).getStart()));
			if(type.equals("year"))
				templist.add(0,Yearformatter.format((java.util.Date) receive.get(i).getStart()));
			
			templist.add(1,receive.get(i).getCount()+"");
			result.add(templist.toString());
		}
		
		logger.info("OPcountInfo : " + result.toString());
		jObject.put("result", result.toString());
		return jObject.toString();
	    
	    
		/*for (int i = 0; i < result.size(); i++) {		
			
			//start_time.add(i,result.get(i).getStart().getYear()+"/"+result.get(i).getStart().getMonth()+"/"+result.get(i).getStart().getDate());
			count.add(result.get(i).getCount());
		}
		logger.info("start_time" + start_time.toString());
		
		jObject.put("start_time", start_time);
		jObject.put("count", count);
		
		return jObject.toString();*/
	}
	
	
	
	@RequestMapping(value = "/operation_time_db", method = RequestMethod.POST)
	@ResponseBody
	public String Operation_time_Get(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("start") String start
			) {
		
		Calendar cal;
		Timestamp starttime=null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		start = start.replace("-", "");
		String date = new String(start+"000000");
		 try {
	            sd.parse(date);
	            cal = sd.getCalendar();
	             
	             starttime = new Timestamp( cal.getTime().getTime() );
	             
	        } catch (ParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		 
		logger.info("operation_time Tab" +" start : "+starttime.toString());
		
		JSONObject jObject= new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject=(ProjectInfo)session.getAttribute("currentproject");
		
		if(currentproject ==null)
			return "1";//세션에 프로젝트 없는 경우
		
		
		String project_key=currentproject.getPk();
		project_key="1";
	      if (project_key == null)
	          return "2";
	       if (project_key.isEmpty())
	          return "2";
	       
	    List<OPtimeInfo> receive=log_timeDao.count_operation_time(project_key,starttime);
	    //List<List<String>> result=new ArrayList<List<String>>();
	    List<String> result=new ArrayList<String>();
	  
		for (int i = 0; i < receive.size(); i++) {		
			
			List<String> templist=new ArrayList<String>();
			templist.add(0,Hourformatter.format((java.util.Date) receive.get(i).getStart()));
			templist.add(1,receive.get(i).getCount()+"");
			result.add(templist.toString());
		}
		
		logger.info("result_time : " + result.toString());
		jObject.put("result", result.toString());
		return jObject.toString();
	    
	}
	
	
	
	
	

	@RequestMapping(value = "/device_db", method = RequestMethod.POST)
	@ResponseBody
	public String Device_Get(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Device_db Tab");

		JSONObject jObject = new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		if (currentproject == null)
			return "1";// 세션에 프로젝트 없는 경우

		String project_key = currentproject.getPk();

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

		JSONObject jObject = new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		if (currentproject == null)
			return "1";// 세션에 프로젝트 없는 경우

		String project_key = currentproject.getPk();

		if (project_key == null)
			return "2";
		if (project_key.isEmpty())
			return "2";

		jObject.put("OS", userDao.countOS(project_key));
		return jObject.toString();
	}
	
	@RequestMapping(value = "/best_activity_db", method = RequestMethod.POST)
	@ResponseBody
	public String Best_activity_Get(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Best_activity_db Tab");

		JSONObject jObject = new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		if (currentproject == null)
			return "1";// 세션에 프로젝트 없는 경우

		String project_key = currentproject.getPk();

		if (project_key == null)
			return "2";
		if (project_key.isEmpty())
			return "2";
		
		List<BestActivityInfo> receive=activityDao.countBest_activity(project_key);
		
		List<String> name=new ArrayList<String>();
		List<Integer> count=new ArrayList<Integer>();
		for(int i=0;i<5;i++){
			name.add(receive.get(i).getActivity_name());
			count.add(receive.get(i).getCount());		
			
		}
		

		jObject.put("activity_name", name);
		jObject.put("count", count);
		logger.info("best_activity Json : "+jObject.toString());
		return jObject.toString();
	}
	

	
	
	@RequestMapping(value = "/promotions_analysis_db", method = RequestMethod.POST)
	@ResponseBody
	public String Promotions_analysis_Get(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("start") String start,@RequestParam("promotion") String promotion
			) {
		
		Calendar cal;
		Timestamp starttime=null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		start = start.replace("-", "");
		String date = new String(start+"000000");
		 try {
	            sd.parse(date);
	            cal = sd.getCalendar();
	             
	             starttime = new Timestamp( cal.getTime().getTime() );
	             
	        } catch (ParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		 
		logger.info("promotions_analysis Tab" + " start : "+starttime.toString() + " promotions_name: "+promotion);
		
		JSONObject jObject= new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject=(ProjectInfo)session.getAttribute("currentproject");
		
		if(currentproject ==null)
			return "1";//세션에 프로젝트 없는 경우
		
		
		String project_key=currentproject.getPk();
		project_key="1";
	      if (project_key == null)
	          return "2";
	       if (project_key.isEmpty())
	          return "2";
	    
	    
	    List<Promotion_resultInfo> receive=promotionResultDao.count_promotion_result(project_key,starttime,promotion);
	    List<String> result=new ArrayList<String>();
	  
		for (int i = 0; i < receive.size(); i++) {		
			
			List<String> templist=new ArrayList<String>();
			
		//	if(type.equals("day"))
				templist.add(0,monthDayYearformatter.format((java.util.Date) receive.get(i).getStart()));
			/*if(type.equals("month"))
				templist.add(0,monthYearformatter.format((java.util.Date) receive.get(i).getStart()));
			if(type.equals("year"))
				templist.add(0,Yearformatter.format((java.util.Date) receive.get(i).getStart()));
			*/
			templist.add(1,receive.get(i).getCount()+"");
			result.add(templist.toString());
		}
		
		logger.info("Promotion_resultInfo : " + result.toString());
		jObject.put("result", result.toString());
		return jObject.toString();
	    
	    
	}
	
}
