package com.rap.main;

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

import com.rap.dao.CategoryLDao;
import com.rap.dao.CategoryMDao;
import com.rap.dao.CategorySDao;
import com.rap.dao.SettingDao;
import com.rap.dao.Virtual_MainDao;
import com.rap.dao.Virtual_SubDao;
import com.rap.models.CategoryLInfo;
import com.rap.models.CategoryMInfo;
import com.rap.models.CategorySInfo;
import com.rap.models.ProjectInfo;

import net.sf.json.JSONObject;

@Controller
public class RAP_CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_CategoryController.class);

	@Autowired
	private CategoryLDao categoryLDao;

	@Autowired
	private CategoryMDao categoryMDao;

	@Autowired
	private CategorySDao categorySDao;
	
	@Autowired
	private Virtual_MainDao virtual_MainDao;

	@Autowired
	private Virtual_SubDao virtual_SubDao;
	
	@Autowired
	private SettingDao settingDao;

	/** 대분류 리스트 */
	@RequestMapping(value = "/Lcategory_db", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String MainController_Lcategory_db(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Lcategory_db Page");

		JSONObject jObject = new JSONObject();

		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");
		
		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";

		// 대분류 리스트
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);
		
		jObject.put("categoryLlist", categoryLlist);
		logger.info("Lcategory_db Page - "+jObject.toString());

		return jObject.toString();

	}

	//중분류 리스트
	@RequestMapping(value = "/Mcategory_db", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String MainController_Mcategory_db(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("categoryL") String categoryL) {
		logger.info("Mcategory_db Page");

		JSONObject jObject = new JSONObject();

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";

		// 대분류 리스트
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);

		if (categoryLlist == null)
			return "";

		logger.info(categoryLlist.toString());

		int categoryLlistcount = categoryLlist.size();
		int categoryL_pk = -1;

		for (int i = 0; i < categoryLlistcount; i++) {
			if (categoryLlist.get(i).getCategoryL().equals(categoryL))
				categoryL_pk = categoryLlist.get(i).getPk();
		}

		logger.info("categoryL_pk = " + categoryL_pk);
		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key, categoryL_pk);

		jObject.put("categoryMlist", categoryMlist);
		logger.info(jObject.toString());

		return jObject.toString();
	}

	@RequestMapping(value = "/Scategory_db", method = RequestMethod.POST, produces="applicateion/json;charset=UTF-8")
	@ResponseBody
	public String MainController_Scategory_db(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("categoryL") String categoryL, @RequestParam("categoryM") String categoryM) {
		logger.info("Scategory_db Page");

		JSONObject jObject = new JSONObject();

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";
		// 대분류 리스트
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);

		if (categoryLlist == null)
			return "";

		logger.info("categoryL = " + categoryL);
		logger.info("categoryM = " + categoryM);

		int categoryLlistcount = categoryLlist.size();
		int categoryL_pk = -1;

		for (int i = 0; i < categoryLlistcount; i++) {
			if (categoryLlist.get(i).getCategoryL().equals(categoryL))
				categoryL_pk = categoryLlist.get(i).getPk();
		}

		// 대분류 pk 존재하지 않는 경우
		if (categoryL_pk == -1)
			return "";

		// 중분류 리스트
		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key, categoryL_pk);
		int categoryMlistcount = categoryMlist.size();
		int categoryM_pk = -1;

		for (int i = 0; i < categoryMlistcount; i++) {
			if (categoryMlist.get(i).getCategoryM().equals(categoryM))
				categoryM_pk = categoryMlist.get(i).getPk();
		}

		// 중분류 pk 존재하지 않는 경우
		if (categoryM_pk == -1)
			return "";

		List<CategorySInfo> categorySlist = categorySDao.select(project_key, categoryM_pk);

		jObject.put("categorySlist", categorySlist);
		logger.info(jObject.toString());

		return jObject.toString();
	}

	/** 대분류 추가 */
	@RequestMapping(value = "/registerLcategory", method = RequestMethod.POST)
	@ResponseBody
	public String registerLcategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Lcategory") String Lcategory) {
		logger.info("registerLcategory pages");
		
		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "Project Not Found";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "Project Not Found";
		if (project_key.isEmpty())
			return "Project Not Found";

		logger.info("프로젝트 존재");

		// 카테고리명이 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "Enter Lcategory";
		if (Lcategory.isEmpty())
			return "Enter Lcategory";
		
		Lcategory = Lcategory.replaceAll(" ", "");

		logger.info("카테고리명 존재");

		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key, Lcategory);
		int categoryLlistcount = categoryLlist.size();

		if(categoryLlistcount > 0)
			return "Lcategory already exist";

		logger.info("같은 이름의 카테고리 없음");
		categoryLDao.create(project_key, Lcategory);

		logger.info("대분류 등록");
		return "200";
	}

	/** 중분류 추가 */
	@RequestMapping(value = "/registerMcategory", method = RequestMethod.POST)
	@ResponseBody
	public String registerMcategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Mcategory") String Mcategory, @RequestParam("Lcategory") String Lcategory) {

		logger.info("registerMcategory pages");

		JSONObject jObject = new JSONObject();

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "Project Not Found";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "Project Not Found";
		if (project_key.isEmpty())
			return "Project Not Found";

		logger.info("프로젝트 존재");

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "Enter Lcategory";
		if (Lcategory.isEmpty())
			return "Enter Lcategory";
		logger.info("대분류 존재");

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "Enter Mcategory";
		if (Mcategory.isEmpty())
			return "Enter Mcategory";
		logger.info("중분류 존재");
		
		Mcategory = Mcategory.replaceAll(" ", "");

		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key, Lcategory);

		if(categoryLlist == null)
			return "error";
		
		int categoryL_pk = categoryLlist.get(0).getPk();

		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key, categoryL_pk, Mcategory);
		if(categoryMlist == null)
			{		logger.info("중분류가 null");
return "error";}
		
		if(categoryMlist.size() > 0)
			{logger.info("중분류사이즈 >0");
			return "Mcategory already exist";}
		
		logger.info("같은 이름의 카테고리 없음");
		categoryMDao.create(project_key, categoryL_pk, Mcategory);

		return "200";
	}

	/** 소분류 추가 */
	@RequestMapping(value = "/registerScategory", method = RequestMethod.POST)
	@ResponseBody
	public String registerScategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Scategory") String Scategory, @RequestParam("Mcategory") String Mcategory,
			@RequestParam("Lcategory") String Lcategory) {

		logger.info("registerScategory pages");

		JSONObject jObject = new JSONObject();

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "Project Not Found";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "Project Not Found";
		if (project_key.isEmpty())
			return "Project Not Found";

		logger.info("프로젝트 존재");

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "Enter Lcategory";
		if (Lcategory.isEmpty())
			return "Enter Lcategory";
		logger.info("대분류 존재");

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "Enter Mcategory";
		if (Mcategory.isEmpty())
			return "Enter Mcategory";
		logger.info("중분류 존재");

		// 소분류가 정상적으로 들어오지 않은 경우
		if (Scategory == null)
			return "Enter Scategory";
		if (Scategory.isEmpty())
			return "Enter Scategory";
		logger.info("소분류 존재");

		Scategory = Scategory.replaceAll(" ", "");
		
		//대분류
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key, Lcategory);

		if(categoryLlist == null)
			return "error";
		
		int categoryL_pk = categoryLlist.get(0).getPk();

		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key, categoryL_pk, Mcategory);
		if(categoryMlist == null)
			return "error";

		int categoryM_pk = categoryMlist.get(0).getPk();

		List<CategorySInfo> categorySlist = categorySDao.select(project_key, categoryM_pk, Scategory);
		int categorySlistcount = categorySlist.size();

		if(categorySlistcount>0)
			return "Scategory already exist";
		
		logger.info("같은 이름의 카테고리 없음");
		categorySDao.create(project_key, categoryM_pk, Scategory);

		return "200";
	}

	/** 대분류 삭제 */
	@RequestMapping(value = "/deleteLcategory", method = RequestMethod.POST)
	@ResponseBody
	public String deleteLcategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Lcategory") String Lcategory) {
		logger.info("deleteLcategory pages");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "1";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "2";
		if (project_key.isEmpty())
			return "2";

		// 대분류명 존재 X
		if (Lcategory == null)
			return "3";

		categoryLDao.delete(project_key, Lcategory);
		logger.info("대분류 삭제");

		return "200";
	}

	/** 중분류 삭제 */
	@RequestMapping(value = "/deleteMcategory", method = RequestMethod.POST)
	@ResponseBody
	public String deleteMcategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Lcategory") String Lcategory, @RequestParam("Mcategory") String Mcategory) {
		logger.info("deleteMcategory pages");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "Project Not Found";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "Project Not Found";
		if (project_key.isEmpty())
			return "Project Not Found";

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "Enter Lcategory";
		if (Lcategory.isEmpty())
			return "Enter Lcategory";

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "Enter Mcategory";
		if (Mcategory.isEmpty())
			return "Enter Mcategory";

		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key, Lcategory);
		int categoryL_pk = -1;
		
		//카테고리가 존재하지않을 때
		if(categoryLlist == null) return "error";
		
		categoryL_pk = categoryLlist.get(0).getPk();

		categoryMDao.delete(project_key, categoryL_pk, Mcategory);
		logger.info("중분류 삭제");

		return "200";
	}

	/** 소분류 삭제 */
	@RequestMapping(value = "/deleteScategory", method = RequestMethod.POST)
	@ResponseBody
	public String deleteScategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("Lcategory") String Lcategory, @RequestParam("Mcategory") String Mcategory,
			@RequestParam("Scategory") String Scategory) {
		logger.info("deleteScategory pages");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "Project Not Found";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "Project Not Found";
		if (project_key.isEmpty())
			return "Project Not Found";

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "Enter Lcategory";
		if (Lcategory.isEmpty())
			return "Enter Lcategory";

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "Enter Mcategory";
		if (Mcategory.isEmpty())
			return "Enter Mcategory";

		// 소분류가 정상적으로 들어오지 않은 경우
		if (Scategory == null)
			return "Enter Scategory";
		if (Scategory.isEmpty())
			return "Enter Scategory";

		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key, Lcategory);
		int categoryL_pk = -1;
		
		//카테고리가 존재하지않을 때
		if(categoryLlist == null) return "error";
		
		categoryL_pk = categoryLlist.get(0).getPk();

		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key, categoryL_pk, Mcategory);
		int categoryM_pk = -1;
		
		//카테고리가 존재하지 않을 때
		if(categoryMlist == null) return "error";
		categoryM_pk = categoryMlist.get(0).getPk();
		
		//소분류 삭제
		categorySDao.delete(project_key, categoryM_pk, Scategory);
		logger.info("소분류 삭제");


		return "200";
	}
	
	
}
