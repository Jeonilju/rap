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

import com.rap.dao.CategoryLDao;
import com.rap.dao.MemberDao;
import com.rap.dao.ProjectDao;
import com.rap.dao.PromotionDao;
import com.rap.dao.SettingDao;
import com.rap.dao.Virtual_MainDao;
import com.rap.dao.Virtual_SubDao;
import com.rap.models.CategoryLInfo;
import com.rap.models.MemberInfo;
import com.rap.models.ProjectInfo;
import com.rap.models.PromotionInfo;
import com.rap.models.SettingInfo;
import com.rap.models.Virtual_MainInfo;
import com.rap.models.Virtual_SubInfo;

import net.sf.json.JSONObject;

@Controller
public class RAP_MainController {
	private static final int PROJECTMAXNUM = 3;
	private static final Logger logger = LoggerFactory.getLogger(RAP_MainController.class);

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private PromotionDao promotionDao;

	@Autowired
	private CategoryLDao categoryLDao;
	
	@Autowired
	private SettingDao settingDao;

	@Autowired
	private Virtual_MainDao virtual_MainDao;

	@Autowired
	private Virtual_SubDao virtual_SubDao;
	/** RAP 홈 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String MainController_index(HttpServletRequest request) {
		logger.info("index Page");

		return "index";
	}

	/** 프로젝트 홈 */
	@RequestMapping(value = "/projecthome", method = RequestMethod.GET)
	public String MainController_projecthome(HttpServletRequest request) {
		logger.info("projecthome Page");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		MemberInfo member = (MemberInfo) session.getAttribute("currentmember");

		if (member != null) {
			int member_pk = member.getPk();

			List<ProjectInfo> projectlist = projectDao.selectFromMemberPK(member_pk);
			request.setAttribute("projectlist", projectlist);
			request.setAttribute("projectcount", projectlist.size());
		}
		return "projecthome";
	}

	/** 프로젝트 등록 */
	@RequestMapping(value = "/projectregister", method = RequestMethod.GET)
	public String MainController_projectregister(HttpServletRequest request) {
		logger.info("projectregister Page");
		// 세션 객체 생성
		HttpSession session = request.getSession();
		MemberInfo member = (MemberInfo) session.getAttribute("currentmember");

		if (member != null) {
			int member_pk = member.getPk();

			List<ProjectInfo> projectlist = projectDao.selectFromMemberPK(member_pk);
			request.setAttribute("projectcount", projectlist.size());
		}
		return "projectregister";
	}

	/** 프로젝트 등록 처리 */
	@RequestMapping(value = "/projectregister_db", method = RequestMethod.POST)
	@ResponseBody
	public String setProject(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("project_name") String project_name, 
			@RequestParam("summary") String summary,
			@RequestParam("description") String description) {
		logger.info("projectregister_db");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		MemberInfo member = (MemberInfo) session.getAttribute("currentmember");

		//로그인 
		if (member == null)
			return "2";
		
		int member_pk = member.getPk();

		//현재 사용자의 프로젝트 리스트		
		List<ProjectInfo> projectlist = projectDao.selectFromMemberPK(member_pk);

		String pk;

		// 사용자의 프로젝트 이름이 중복될 경우
		for (int i = 0; i < projectlist.size(); i++) {
			if (projectlist.get(i).getProject_name().equals(project_name)) {
				return "1";
			}
		}

		// 사용자의 프로젝트 개수가 3개일 경우
		if (projectlist.size() >= PROJECTMAXNUM)
			return "3";
		
		// 해시 적용
		do {
			pk = "";
			// 날짜+사용자고유키
			StringBuffer buf = new StringBuffer();
			buf.append(new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date()));
			buf.append(new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date()));
			buf.append(member_pk);

			String str = buf.toString();
			logger.info("pk 전환 전 : "+str);
			
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(str.getBytes());
				byte byteData[] = md.digest();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
				pk = sb.toString();

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				pk = null;
			}
			projectlist = projectDao.select(pk);
		} while (projectlist != null && !projectlist.isEmpty());

		logger.info("프로젝트 생성");
		projectDao.create(pk, project_name, summary, description, member_pk);
		settingDao.create(pk, 0, 0, 0, 0, 0, 0, "" );

		projectlist = projectDao.selectFromMemberPK(member_pk);

		request.setAttribute("projectlist", projectlist);
		request.setAttribute("projectcount", projectlist.size());

		return "0";

	}

	/** 프로젝트 삭제 */
	@RequestMapping(value = "/projectdelete", method = RequestMethod.POST)
	@ResponseBody
	public String MainController_projectdelete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("projectname") String projectname) {
		logger.info("projectdelete Page");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		MemberInfo member = (MemberInfo) session.getAttribute("currentmember");
		int member_pk = member.getPk();

		List<ProjectInfo> projectlist = projectDao.selectFromMemberPK(member_pk);
		int projectlistcount = projectlist.size();

		for (int i = 0; i < projectlistcount; i++) {
			if (projectlist.get(i).getProject_name().equals(projectname)) {
				projectDao.delete(projectlist.get(i).getPk());
				return "200";
			}
		}

		return "error";
	}
	
	/** 프로젝트 수정 */
	@RequestMapping(value = "/ProjectEdit", method = RequestMethod.POST)
	@ResponseBody
	public String MainController_ProjectEdit(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("project_name") String project_name,
			@RequestParam("project_summary") String project_summary,
			@RequestParam("project_description") String project_description
			) {
		logger.info("ProjectEdit Page");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		MemberInfo member = (MemberInfo) session.getAttribute("currentmember");
		if(member==null) return "error";
		
		ProjectInfo project = (ProjectInfo)session.getAttribute("currentproject");
		if(project==null) return "error";

		//입력값 검증
		if(project_name == null) return "project_name";
		if(project_name.isEmpty()) return "project_name";
		
		if(project_summary == null) return "project_summary";
		if(project_summary.isEmpty()) return "project_summary";
		
		if(project_description == null) return "project_description";
		if(project_description.isEmpty()) return "project_description";
		
		//현재 사용자의 프로젝트 리스트
		List<ProjectInfo> projectlist = projectDao.selectFromMemberPK(member.getPk());

		if(projectlist == null) return "error";
		if(projectlist.isEmpty()) return "error";

		for(int i=0;i<projectlist.size();i++)
		{
			if(projectlist.get(i).getProject_name().equals(project_name))
				{
				if(!projectlist.get(i).getProject_name().equals(project.getProject_name()))
					return "overlap";
				}
		}
		
		projectDao.update(project_name, project_summary, project_description, project.getPk());


		return "200";
	}
	
	/** 프로젝트 선택 */
	@RequestMapping(value = "/selectProject", method = RequestMethod.POST)
	@ResponseBody
	public String MainController_selectProject(HttpServletRequest request,
			 HttpServletResponse response,
			@RequestParam("currentprojectname") String currentprojectname) {
		logger.info("selectProject Page");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		MemberInfo member = (MemberInfo) session.getAttribute("currentmember");
		if(member==null) logger.info("member = null");
		int member_pk = member.getPk();

		List<ProjectInfo> projectlist = projectDao.selectFromMemberPK(member_pk);
		int projectcount = projectlist.size();

		logger.info("selectProject Page - projectcount = " + projectcount);

		ProjectInfo currentproject=null;
		
		for (int i = 0; i < projectcount; i++) {
			if (projectlist.get(i).getProject_name().equals(currentprojectname)) {
				currentproject = projectlist.get(i);
				break;
			}
		}
		
		if(currentproject==null) return "error";
		
		session.setAttribute("currentproject", currentproject);

		logger.info("currentproject = "+currentproject.getProject_name());
		return "projectsettings";
	}
	
	/** 프로젝트 세팅 */
	@RequestMapping(value = "/projectsettings", method = RequestMethod.GET)
	public String MainController_projectsettings(HttpServletRequest request) {
		logger.info("projectsettings Page");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");
		
		if(currentproject == null)
		{
			return "projectsettings";
		}
		
		String project_key = currentproject.getPk();
		// 대분류 리스트
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);
		
		// 주화폐 리스트
		List<Virtual_MainInfo> mainlist = virtual_MainDao.select(project_key);
		
		// 부화폐 리스트
		List<Virtual_SubInfo> sublist = virtual_SubDao.select(project_key);
		
		request.setAttribute("categoryLlist", categoryLlist);
		request.setAttribute("mainlist", mainlist);
		request.setAttribute("sublist", sublist);


		return "projectsettings";
	}

	/** 회원가입 설정 */
	@RequestMapping(value = "/signup_db", method = RequestMethod.POST)
	@ResponseBody
	public String setMember(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("password_check") String password_check) {
		logger.info("signup_db pages");

		// 이메일을 입력하지 않았을 때
		if (email == null)
			return "email";
		if (email.isEmpty())
			return "email";
		
		// password 입력하지 않았을 때
		if (password == null)
			return "password";
		if (password.isEmpty())
			return "password";
		
		// password_check 입력하지 않았을 때
		if (password_check == null)
			return "password_check";
		if (password_check.isEmpty())
			return "password_check";

		// 패스워드 값이 같지 않을 때
		logger.info(" 패스워드 값이 같지 않을 때");
		if (!password.equals(password_check)) return "password equality";
		
		List<MemberInfo> memberlist = memberDao.select(email);

		// 이메일이 존재하지 않을 때
		if (memberlist == null || memberlist.isEmpty()) {
			memberDao.create(email, password);
		} 
		else {
			if (memberlist.get(0).getEmail().equals(email)) {
				logger.info("중복");
				return "overlap";
			} else {
				logger.info("에러");
				return "error";
			}
		}

		logger.info("회원가입 완료");
		return "success";
	}

	/** 아이디 중복 체크 */
	@RequestMapping(value = "/overlaptest_db", method = RequestMethod.POST)
	@ResponseBody
	public String idvalidcheck(HttpServletRequest request) {
		String result = "0";

		logger.info("ID Valid Check");

		String email = request.getParameter("email");

		// 세션 객체 생성
		HttpSession session = request.getSession();

		List<MemberInfo> data;
		data = memberDao.select(email);

		// 이메일이 존재하지 않을 때
		if (data == null || data.isEmpty()) {
			result = "0";
		} else {
			if (data.get(0).getEmail().equals(email)) {
				result = "1";
			} else {
				result = "0";
			}
		}

		return result;
	}

	/** 로그인 설정 */
	@RequestMapping(value = "/login_db", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request) {

		String result = "0";

		logger.info("User Login");

		String email = request.getParameter("Signin_email");
		String password = request.getParameter("Signin_password");

		// 세션 객체 생성
		HttpSession session = request.getSession();

		List<MemberInfo> data;
		data = memberDao.select(email);

		// 이메일이 존재하지 않을 때
		if (data == null || data.isEmpty()) {
			result = "0"; // no email
		} else {
			if (data.get(0).getPassword().equals(password)) // password unequal
			{
				result = "1";

				session.setAttribute("currentmember", data.get(0));
			} else {
				result = "0";
			}
		}

		return result;
	}

	/** 로그아웃 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String MainController_logout(HttpServletRequest request) {
		logger.info("logout Page");

		return "logout";
	}

	@RequestMapping(value = "/itemmanagement", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String MainController_itemmanagement(HttpServletRequest request) {
		logger.info("itemmanagement Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null)
			return "itemmanagement";

		String project_key = currentproject.getPk();

		// 프로젝트 키 존재 X
		if (project_key == null)
			return "itemmanagement";
		if (project_key.isEmpty())
			return "itemmanagement";

		// 대분류 리스트
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);

		request.setAttribute("categoryLlist", categoryLlist);

		return "itemmanagement";
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

	/* minsu add */
	@RequestMapping(value = "/promotions", method = RequestMethod.GET)
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

	/** 회원가입 설정 */
	@RequestMapping(value = "/promotion_db", method = RequestMethod.POST)
	@ResponseBody
	public String setPromotion(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("project_name") String project_name, @RequestParam("name") String name,
			@RequestParam("summary") String summary, @RequestParam("grade_using") int grade_using,
			@RequestParam("grade_time") int grade_time) {
		logger.info("APICall: " + "setPromotion");

		String project_key = "";

		// 프로젝트명이 존재하지 않을때
		if (project_name == null)
			return "2";
		if (project_name.isEmpty())
			return "2";

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

		// 프로젝트 키가 존재하지 않을 경우
		if (project_key == null)
			return "1";
		if (project_key.isEmpty())
			return "1";

		promotionDao.create(project_key, name, summary, grade_using, grade_time);

		return "200";
	}

	/** 프로모션 목록 설정 */
	@RequestMapping(value = "/promotionlist_db", method = RequestMethod.POST)
	@ResponseBody
	public String MainController_promotionlist_db(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("project_name") String project_name, ModelMap model) {

		logger.info("promotionlist_db");

		String project_key = "";
		JSONObject jObject = new JSONObject();

		if (project_name == null)
			return "";
		if (project_name.isEmpty())
			return "";

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

		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";

		List<PromotionInfo> promotionlist = promotionDao.selectFromProject(project_key);
		jObject.put("promotionlist", promotionlist);
		logger.info(jObject.toString());
		return jObject.toString();

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
	@RequestMapping(value = "/new_member", method = RequestMethod.GET)
	public String MainController_new_member(HttpServletRequest request) {
		logger.info("new_member Page");

		return "new_member";
	}	
	@RequestMapping(value = "/best_activity", method = RequestMethod.GET)
	public String MainController_best_activity(HttpServletRequest request) {
		logger.info("best_activityr Page");

		return "best_activity";
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
