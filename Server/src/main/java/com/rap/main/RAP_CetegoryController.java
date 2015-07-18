package com.rap.main;

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

import com.rap.dao.CategoryLDao;
import com.rap.dao.CategoryMDao;
import com.rap.dao.CategorySDao;
import com.rap.dao.MemberDao;
import com.rap.dao.ProjectDao;
import com.rap.models.CategoryLInfo;
import com.rap.models.CategoryMInfo;
import com.rap.models.CategorySInfo;
import com.rap.models.MemberInfo;
import com.rap.models.ProjectInfo;

import net.sf.json.JSONObject;

@Controller
public class RAP_CetegoryController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_CetegoryController.class);

	@Autowired
	private CategoryLDao categoryLDao;

	@Autowired
	private CategoryMDao categoryMDao;

	@Autowired
	private CategorySDao categorySDao;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private MemberDao memberDao;

	/** 대분류 리스트 */
	@RequestMapping(value = "/Lcategory_db", method = RequestMethod.POST)
	@ResponseBody
	public String MainController_Lcategory_db(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Lcategory_db Page");

		JSONObject jObject = new JSONObject();
		
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo)session.getAttribute("currentproject");
		//MemberInfo currentmember = (MemberInfo)session.getAttribute("currentmember");
		
		//세션에 프로젝트 존재 X
		if(currentproject == null)
			return "1";
		
		String project_key = currentproject.getPk();
		
		// 프로젝트 키 존재 X
		if (project_key == null)
			return "2";
		if (project_key.isEmpty())
			return "2";
		
		//대분류 리스트
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);
		
		request.setAttribute("categoryLlist", categoryLlist);

		jObject.put("categoryLlist", categoryLlist);
		logger.info(jObject.toString());

		return jObject.toString();

	}

	@RequestMapping(value = "/Mcategory_db", method = RequestMethod.POST)
	@ResponseBody
	public String MainController_Mcategory_db(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("categoryL") String categoryL) {
		logger.info("Mcategory_db Page");

		JSONObject jObject = new JSONObject();
		
		//세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo)session.getAttribute("currentproject");
		
		//세션에 프로젝트 존재 X
		if(currentproject == null)
			return "1";
		
		String project_key = currentproject.getPk();
		
		// 프로젝트 키 존재 X
		if (project_key == null)
			return "2";
		if (project_key.isEmpty())
			return "2";

		//대분류 리스트
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);
		
		if(categoryLlist == null) return "3";
		
		logger.info(categoryLlist.toString());
		
		int categoryLlistcount = categoryLlist.size();
		int categoryL_pk = -1;

		for (int i = 0; i < categoryLlistcount; i++) {
			if (categoryLlist.get(i).getCategoryL().equals(categoryL))
				categoryL_pk = categoryLlist.get(i).getPk();
		}

		logger.info("categoryL_pk = "+categoryL_pk);
		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key, categoryL_pk);

		jObject.put("categoryMlist", categoryMlist);
		logger.info(jObject.toString());

		return jObject.toString();
	}

	@RequestMapping(value = "/Scategory_db", method = RequestMethod.POST)
	@ResponseBody
	public String MainController_Scategory_db(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("project_name") String project_name, @RequestParam("categoryM") String categoryM) {
		logger.info("Scategory_db Page");

		JSONObject jObject = new JSONObject();
		
		//세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo)session.getAttribute("currentproject");
		
		//세션에 프로젝트 존재 X
		if(currentproject == null)
			return "1";
		
		String project_key = currentproject.getPk();
		
		// 프로젝트 키 존재 X
		if (project_key == null)
			return "2";
		if (project_key.isEmpty())
			return "2";

		//대분류 리스트
		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key);
		
		int categoryMlistcount = categoryMlist.size();
		int categoryM_pk = -1;

		for (int i = 0; i < categoryMlistcount; i++) {
			if (categoryMlist.get(i).getCategoryM().equals(categoryM))
				categoryM_pk = categoryMlist.get(i).getPk();
		}

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
			@RequestParam("project_name") String project_name, @RequestParam("Lcategory") String Lcategory) {
		logger.info("registerLcategory pages");

		String project_key = "";

		// 세션 객체 생성
		HttpSession session = request.getSession();
		MemberInfo member = (MemberInfo) session.getAttribute("currentmember");
		int member_pk = member.getPk();

		List<ProjectInfo> projectlist = projectDao.selectFromMemberPK(member_pk);
		
		for (int i = 0; i < projectlist.size(); i++) {
			if (projectlist.get(i).getProject_name().equals(project_name)) {
				project_key = projectlist.get(i).getPk();
				logger.info("project_key = " + project_key);
			}
		}

		// 프로젝트가 존재하지 않는 경우
		if (project_key == null)
			return "1";
		if (project_key.isEmpty())
			return "1";

		logger.info("프로젝트 존재");
		// 카테고리명이 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "2";
		if (Lcategory.isEmpty())
			return "2";

		logger.info("카테고리명 존재");
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);
		int categoryLlistcount = categoryLlist.size();

		for (int i = 0; i < categoryLlistcount; i++) {
			// 같은 이름의 카테고리 존재하는 경우
			if (categoryLlist.get(i).getCategoryL().equals(Lcategory))
				return "3";
		}

		logger.info("같은 이름의 카테고리 없음");
		categoryLDao.create(project_key, Lcategory);

		return "200";
	}

	/** 중분류 추가 */
	@RequestMapping(value = "/registerMcategory", method = RequestMethod.POST)
	@ResponseBody
	public String registerMcategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("project_name") String project_name, @RequestParam("Mcategory") String Mcategory,
			@RequestParam("Lcategory") String Lcategory) {

		logger.info("registerMcategory pages");

		String project_key = "";

		// 세션 객체 생성
		HttpSession session = request.getSession();
		MemberInfo member = (MemberInfo) session.getAttribute("currentmember");
		int member_pk = member.getPk();

		List<ProjectInfo> projectlist = projectDao.selectFromMemberPK(member_pk);
		for (int i = 0; i < projectlist.size(); i++) {
			if (projectlist.get(i).getProject_name().equals(project_name)) {
				project_key = projectlist.get(i).getPk();
				logger.info("project_key = " + project_key);
			}
		}

		// 프로젝트가 존재하지 않는 경우
		if (project_key == null)
			return "1";
		if (project_key.isEmpty())
			return "1";

		logger.info("프로젝트 존재");

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "2";
		if (Lcategory.isEmpty())
			return "2";
		logger.info("대분류 존재");

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "3";
		if (Mcategory.isEmpty())
			return "3";
		logger.info("중분류 존재");


		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);
		int categoryLlistcount = categoryLlist.size();
		int categoryL_pk = -1;

		for (int i = 0; i < categoryLlistcount; i++) {
			if (categoryLlist.get(i).equals(Lcategory))
				categoryL_pk = categoryLlist.get(i).getPk();
		}

		if (categoryL_pk == -1)
			return "";

		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key, categoryL_pk);
		int categoryMlistcount = categoryMlist.size();

		for (int i = 0; i < categoryMlistcount; i++) {
			// 같은 이름의 카테고리 존재하는 경우
			if (categoryMlist.get(i).getCategoryM().equals(Mcategory))
				return "4";
		}

		logger.info("같은 이름의 카테고리 없음");
		categoryMDao.create(project_key, categoryL_pk, Mcategory);

		return "200";
	}

	/** 소분류 추가 */
	@RequestMapping(value = "/registerScategory", method = RequestMethod.POST)
	@ResponseBody
	public String registerScategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("project_name") String project_name, @RequestParam("Scategory") String Scategory,
			@RequestParam("Mcategory") String Mcategory, @RequestParam("Lcategory") String Lcategory) {

		logger.info("registerScategory pages");

		String project_key = "";

		// 세션 객체 생성
		HttpSession session = request.getSession();
		MemberInfo member = (MemberInfo) session.getAttribute("currentmember");
		int member_pk = member.getPk();

		List<ProjectInfo> projectlist = projectDao.selectFromMemberPK(member_pk);
		for (int i = 0; i < projectlist.size(); i++) {
			if (projectlist.get(i).getProject_name().equals(project_name)) {
				project_key = projectlist.get(i).getPk();
				logger.info("project_key = " + project_key);
			}
		}

		// 프로젝트가 존재하지 않는 경우
		if (project_key == null)
			return "1";
		if (project_key.isEmpty())
			return "1";

		logger.info("프로젝트 존재");

		// 대분류가 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "2";
		if (Lcategory.isEmpty())
			return "2";
		logger.info("대분류 존재");

		// 중분류가 정상적으로 들어오지 않은 경우
		if (Mcategory == null)
			return "3";
		if (Mcategory.isEmpty())
			return "3";
		logger.info("중분류 존재");

		// 소분류가 정상적으로 들어오지 않은 경우
		if (Scategory == null)
			return "4";
		if (Scategory.isEmpty())
			return "4";
		logger.info("소분류 존재");

		List<CategoryMInfo> categoryMlist = categoryMDao.select(project_key);
		int categoryMlistcount = categoryMlist.size();
		int categoryM_pk = -1;

		for (int i = 0; i < categoryMlistcount; i++) {
			if (categoryMlist.get(i).equals(Mcategory))
				categoryM_pk = categoryMlist.get(i).getPk();
		}

		if (categoryM_pk == -1)
			return "";

		List<CategorySInfo> categorySlist = categorySDao.select(project_key, categoryM_pk);
		int categorySlistcount = categorySlist.size();

		for (int i = 0; i < categorySlistcount; i++) {
			// 같은 이름의 카테고리 존재하는 경우
			if (categorySlist.get(i).getCategoryS().equals(Scategory))
				return "4";
		}

		logger.info("같은 이름의 카테고리 없음");
		categorySDao.create(project_key, categoryM_pk, Scategory);

		return "200";
	}
	
	/** 대분류 삭제 */
	@RequestMapping(value = "/deleteLcategory", method = RequestMethod.POST)
	@ResponseBody
	public String deleteLcategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("project_name") String project_name, @RequestParam("Lcategory") String Lcategory) {
		logger.info("registerLcategory pages");

		String project_key = "";

		// 세션 객체 생성
		HttpSession session = request.getSession();
		MemberInfo member = (MemberInfo) session.getAttribute("currentmember");
		int member_pk = member.getPk();

		List<ProjectInfo> projectlist = projectDao.selectFromMemberPK(member_pk);
		
		for (int i = 0; i < projectlist.size(); i++) {
			if (projectlist.get(i).getProject_name().equals(project_name)) {
				project_key = projectlist.get(i).getPk();
				logger.info("project_key = " + project_key);
			}
		}

		// 프로젝트가 존재하지 않는 경우
		if (project_key == null)
			return "1";
		if (project_key.isEmpty())
			return "1";

		logger.info("프로젝트 존재");
		// 카테고리명이 정상적으로 들어오지 않은 경우
		if (Lcategory == null)
			return "2";
		if (Lcategory.isEmpty())
			return "2";

		logger.info("카테고리명 존재");
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);
		int categoryLlistcount = categoryLlist.size();

		for (int i = 0; i < categoryLlistcount; i++) {
			// 같은 이름의 카테고리 존재하는 경우
			if (categoryLlist.get(i).getCategoryL().equals(Lcategory))
				return "3";
		}

		logger.info("같은 이름의 카테고리 없음");
		categoryLDao.create(project_key, Lcategory);

		return "200";
	}
	
	
}
