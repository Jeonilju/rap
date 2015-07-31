package com.rap.main;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rap.dao.ProjectDao;
import com.rap.dao.PromotionResultDao;

@Controller
public class RAP_APIsPromotionController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_APIsController.class);
	
	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private PromotionResultDao promotionResultDao;
	
	////////////////////////////////////////////////////////////////
	//////////											////////////
	//////////					GCM 					////////////
	//////////											////////////
	////////////////////////////////////////////////////////////////

	/** 프로모션 설정 */
	@RequestMapping(value = "/APIs/promotion", method = RequestMethod.POST)
	@ResponseBody
	public String setPromotion(HttpServletResponse response
			,@RequestParam("key") String key,
			@RequestParam("name") String name,
			@RequestParam("promotion_pk") int promotion_pk) {
		logger.info("Promotion API: " + "setPromotion");
		promotionResultDao.create(key, promotion_pk, name);

		isRight(key, response);
		
		return "200";
	}
	
	public boolean isRight(String project_key, HttpServletResponse response){
		
		if(projectDao.select(project_key).size() == 0){
			response.setStatus(401);
			return false;
		}
		
		return true;
	}
}
