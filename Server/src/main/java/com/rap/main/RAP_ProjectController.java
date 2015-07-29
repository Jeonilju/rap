package com.rap.main;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rap.dao.CategoryLDao;
import com.rap.dao.ProjectDao;
import com.rap.dao.SettingDao;
import com.rap.dao.Virtual_MainDao;
import com.rap.dao.Virtual_SubDao;
import com.rap.models.CategoryLInfo;
import com.rap.models.MemberInfo;
import com.rap.models.ProjectInfo;
import com.rap.models.SettingInfo;
import com.rap.models.Virtual_MainInfo;
import com.rap.models.Virtual_SubInfo;

@Controller
public class RAP_ProjectController {
	private static final int PROJECTMAXNUM = 3;
	private static final Logger logger = LoggerFactory.getLogger(RAP_ProjectController.class);

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private CategoryLDao categoryLDao;
	
	@Autowired
	private SettingDao settingDao;

	@Autowired
	private Virtual_MainDao virtual_MainDao;

	@Autowired
	private Virtual_SubDao virtual_SubDao;
	
	/** 프로젝트 홈 */
	@RequestMapping(value = "/projecthome", method = RequestMethod.GET)
	public String MainController_projecthome(HttpServletRequest request, HttpServletResponse response) {
		logger.info("projecthome Page");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		MemberInfo member = (MemberInfo) session.getAttribute("currentmember");

		if (member != null) {
			int member_pk = member.getPk();

			List<ProjectInfo> projectlist = projectDao.selectFromMemberPK(member_pk);
			
			logger.info("projectsize = "+projectlist.size());
			request.setAttribute("projectlist", projectlist);
			request.setAttribute("projectcount", projectlist.size());
		}
		return "projecthome";
	}

	/** 프로젝트 등록 */
	@RequestMapping(value = "/projectregister", method = RequestMethod.GET)
	public String MainController_projectregister(HttpServletRequest request){
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
		//시간 5 3 1, 돈 30000 20000 10000
		settingDao.create(pk, 30000, 20000, 10000, 5, 3, 1, "" );
		virtual_MainDao.create(pk, "Main 화폐", 0, "", "");
		virtual_SubDao.create(pk, "Sub 화폐", 0, "", "");

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
		
		logger.info("프로젝트 정상적으로 수정");
		projectDao.update(project_name, project_summary, project_description, project.getPk());
		project.setProject_name(project_name);
		project.setDescription(project_description);
		project.setSummary(project_summary);
		session.setAttribute("currentproject", project);


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
	public String MainController_projectsettings(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("projectsettings Page");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");
		
		if(currentproject == null)
		{
			return "index";
		}
		
		String project_key = currentproject.getPk();
		// 대분류 리스트
		List<CategoryLInfo> categoryLlist = categoryLDao.select(project_key);
		
		// 주화폐 리스트
		List<Virtual_MainInfo> mainlist = virtual_MainDao.select(project_key);
		
		// 부화폐 리스트
		List<Virtual_SubInfo> sublist = virtual_SubDao.select(project_key);
		
		//프로젝트 설정
		SettingInfo setting = settingDao.selectSettingInfo(project_key);
		
		request.setAttribute("categoryLlist", categoryLlist);
		request.setAttribute("mainlist", mainlist);
		request.setAttribute("sublist", sublist);
		request.setAttribute("setting", setting);


		return "projectsettings";
	}
	
	/** 구글 프로젝트 번호 수정 */
	@RequestMapping(value = "/registerGoogleProjectNum", method = RequestMethod.POST)
	@ResponseBody
	public String registerGoogleProjectNum(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("google_project_num") String google_project_num) {
		logger.info("registerGoogleProjectNum");

		// 세션 객체 생성
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");
		
		if(google_project_num == null) return "google_project_num";
		if(google_project_num.isEmpty()) return "google_project_num";
		
		if(currentproject == null)
		{
			return "error";
		}
		logger.info("프로젝트 존재");
		
		settingDao.updateGoogleProjectNum(google_project_num, currentproject.getPk());

		return "200";

	}
}
