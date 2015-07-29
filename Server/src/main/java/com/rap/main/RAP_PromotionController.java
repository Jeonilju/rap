package com.rap.main;

import java.io.IOException;
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

import com.rap.dao.ActivityDao;
import com.rap.dao.PromotionDao;
import com.rap.dao.SettingDao;
import com.rap.dao.UserDao;
import com.rap.gcm.RAP_GCMManager;
import com.rap.models.ProjectInfo;
import com.rap.models.PromotionInfo;
import com.rap.models.SettingInfo;
import com.rap.models.UserInfo;

import net.sf.json.JSONObject;

@Controller
public class RAP_PromotionController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_MainController.class);

	@Autowired
	private PromotionDao promotionDao;

	@Autowired
	private ActivityDao activityDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SettingDao settingDao;

	/* minsu add */
	@RequestMapping(value = "/promotions", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String MainController_promotions(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("promotions Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
		{
			response.sendRedirect("projecthome");
			return "projecthome";
		}
		
		List<PromotionInfo> promotionlist = (List<PromotionInfo>) request.getAttribute("promotionlist");

		if (promotionlist == null || promotionlist.isEmpty()) {
			logger.info("No promotion");
			request.setAttribute("promotionlist", null);
			request.setAttribute("promotioncount", 0);
		} else {
			logger.info(promotionlist.toString());
		}

		return "promotions";
	}

	/** 프로모션 등록 */
	@RequestMapping(value = "/registerPromotion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String registerPromotion(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("name") String name,
			@RequestParam("summary") String summary, 
			@RequestParam("grade_money") int grade_money,
			@RequestParam("grade_time") int grade_time,
			@RequestParam("target_activity") String target_activity
			) {
		logger.info("registerPromotion");

		//name
		if(name == null) return "name";
		if(name.isEmpty()) return "name";
		//summary
		if(summary == null) return "summary";
		if(summary.isEmpty()) return "summary";
		
		// 세션 객체 생성
		HttpSession session = request.getSession();
		
		ProjectInfo project = (ProjectInfo)session.getAttribute("currentproject");
		if(project==null) return "error";
		
		String project_key = project.getPk();
		
		List<PromotionInfo> promotionlist = promotionDao.selectFromProject(project_key);
		if(promotionlist == null) return "error";
		
		int promotioncount = promotionlist.size();
		for(int i=0;i<promotioncount;i++)
		{
			if(promotionlist.get(i).getName().equals(name))
				return "overlap";
		}
		
		promotionDao.create(project_key, name, summary, grade_money, grade_time, target_activity);
		logger.info("프로모션 등록");
		return "200";
	}

	/** 프로모션 목록 설정 */
	@RequestMapping(value = "/getpromotionlist", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String MainController_getpromotionlist(HttpServletRequest request, HttpServletResponse response) {

		logger.info("getpromotionlist");
		JSONObject jObject = new JSONObject();

		// 세션 객체 생성
		HttpSession session = request.getSession();
		
		ProjectInfo project = (ProjectInfo)session.getAttribute("currentproject");
		if(project==null) return "";
		
		String project_key = project.getPk();
		
		List<PromotionInfo> promotionlist = promotionDao.selectFromProject(project_key);
		jObject.put("promotionlist", promotionlist);
		logger.info(jObject.toString());
		
		return jObject.toString();

	}

	/** 액티비티 목록 설정 */
	@RequestMapping(value = "/getactivitylist", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String MainController_getactivitylist(HttpServletRequest request, HttpServletResponse response) {

		logger.info("getactivitylist");
		JSONObject jObject = new JSONObject();

		// 세션 객체 생성
		HttpSession session = request.getSession();
		
		ProjectInfo project = (ProjectInfo)session.getAttribute("currentproject");
		if(project==null) return "";
		
		String project_key = project.getPk();
		
		List<String> activitylist = activityDao.selectActivityList(project_key);
		jObject.put("activitylist", activitylist);
		logger.info(jObject.toString());
		
		return jObject.toString();

	}
	
	/** 푸시메시지 전송 */
	@RequestMapping(value = "/sendpushmsg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String MainController_sendpushmsg(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("name") String name
			) {

		logger.info("sendpushmsg pages");
		
		if(name == null) return "name";
		if(name.isEmpty()) return "name";

		// 세션 객체 생성
		HttpSession session = request.getSession();
		
		ProjectInfo project = (ProjectInfo)session.getAttribute("currentproject");
		if(project==null) return "error";
		
		String project_key = project.getPk();
		
		//해당 프로모션
		PromotionInfo promotion = promotionDao.selectFromProject(project_key, name);
		if(promotion==null) return "error";
		
		int grade_time = promotion.getGrade_time();
		int grade_money = promotion.getGrade_money();

		//프로젝트 설정
		SettingInfo setting_info = settingDao.selectSettingInfo(project_key);
		if(setting_info==null) return "error";

		String google_project_num = setting_info.getGoogle_project_num();
		if(google_project_num == null) return "google_project_num";
		if(google_project_num.isEmpty()) return "google_project_num";
		//사용자 리스트
		List<UserInfo> userList = userDao.selectPromotionUserlist(project_key, grade_time, grade_money);
		
		RAP_GCMManager.getInstance().sendPush(project_key,google_project_num, promotion.getPk(), promotion.getName(), promotion.getSummary(), promotion.getTarget_activity() , userList);
		
		logger.info("푸시 메시지 전송");
		
		return "200";

	}
}
