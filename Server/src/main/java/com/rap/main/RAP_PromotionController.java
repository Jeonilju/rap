package com.rap.main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rap.dao.MemberDao;
import com.rap.dao.ProjectDao;
import com.rap.dao.PromotionDao;
import com.rap.models.ProjectInfo;
import com.rap.models.PromotionInfo;

import net.sf.json.JSONObject;

@Controller
public class RAP_PromotionController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_MainController.class);

	@Autowired
	private PromotionDao promotionDao;

	/* minsu add */
	@RequestMapping(value = "/promotions", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String MainController_promotions(HttpServletRequest request) {
		logger.info("promotions Page");

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
			@RequestParam("grade_time") int grade_time) {
		logger.info("registerPromotion");

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
		
		promotionDao.create(project_key, name, summary, grade_money, grade_time);
		logger.info("프로모션 등록");
		return "200";
	}

	/** 프로모션 목록 설정 */
	@RequestMapping(value = "/promotionlist_db", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String MainController_promotionlist_db(HttpServletRequest request, HttpServletResponse response) {

		logger.info("promotionlist_db");
		JSONObject jObject = new JSONObject();

		// 세션 객체 생성
		HttpSession session = request.getSession();
		
		ProjectInfo project = (ProjectInfo)session.getAttribute("currentproject");
		if(project==null) return "error";
		
		String project_key = project.getPk();
		
		List<PromotionInfo> promotionlist = promotionDao.selectFromProject(project_key);
		jObject.put("promotionlist", promotionlist);
		logger.info(jObject.toString());
		return jObject.toString();

	}

	
}
