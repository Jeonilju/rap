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

import com.rap.dao.MemberDao;
import com.rap.dao.PromotionDao;
import com.rap.dao.SettingDao;
import com.rap.dao.Virtual_MainDao;
import com.rap.dao.Virtual_SubDao;
import com.rap.models.MemberInfo;
import com.rap.models.ProjectInfo;

@Controller
public class RAP_MainController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_MainController.class);

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private SettingDao settingDao;

	@Autowired
	private PromotionDao promotionDao;

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
		if (!password.equals(password_check))
			return "password equality";

		List<MemberInfo> memberlist = memberDao.select(email);

		// 이메일이 존재하지 않을 때
		if (memberlist == null || memberlist.isEmpty()) {
			memberDao.create(email, password);
		} else {
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

	@RequestMapping(value = "/age", method = RequestMethod.GET)
	public String MainController_age(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("age Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null) {
			response.sendRedirect("projecthome");
			return "projecthome";
		}

		return "age";
	}

	@RequestMapping(value = "/sex", method = RequestMethod.GET)
	public String MainController_sex(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("sex Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null) {
			response.sendRedirect("projecthome");
			return "projecthome";
		}

		return "sex";
	}

	@RequestMapping(value = "/operation_count", method = RequestMethod.GET)
	public String MainController_operation_count(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.info("operation_count Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null) {
			response.sendRedirect("projecthome");
			return "projecthome";
		}

		return "operation_count";
	}

	@RequestMapping(value = "/os", method = RequestMethod.GET)
	public String MainController_os(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("os Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null) {
			response.sendRedirect("projecthome");
			return "projecthome";
		}

		return "os";
	}

	@RequestMapping(value = "/new_member", method = RequestMethod.GET)
	public String MainController_new_member(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.info("new_member Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null) {
			response.sendRedirect("projecthome");
			return "projecthome";
		}

		return "new_member";
	}

	@RequestMapping(value = "/best_activity", method = RequestMethod.GET)
	public String MainController_best_activity(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.info("best_activityr Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null) {
			response.sendRedirect("projecthome");
			return "projecthome";
		}

		return "best_activity";
	}

	@RequestMapping(value = "/device", method = RequestMethod.GET)
	public String MainController_device(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("device Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null) {
			response.sendRedirect("projecthome");
			return "projecthome";
		}

		return "device";
	}

	@RequestMapping(value = "/operation_time", method = RequestMethod.GET)
	public String MainController_operation_time(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.info("operation_time Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null) {
			response.sendRedirect("projecthome");
			return "projecthome";
		}

		return "operation_time";
	}

	@RequestMapping(value = "/promotions_analysis", method = RequestMethod.GET)
	public String MainController_promotions_analysis(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.info("promotions_analysis Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null) {
			response.sendRedirect("projecthome");
			return "projecthome";
		}

		logger.info( "프로모션 갯수: " + promotionDao.selectFromProject(currentproject.getPk()).size());
		
		request.setAttribute("promotionList", promotionDao.selectFromProject(currentproject.getPk()));

		return "promotions_analysis";
	}

	@RequestMapping(value = "/sales_ranking", method = RequestMethod.GET)
	public String MainController_sales_ranking(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.info("sales_ranking Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null) {
			response.sendRedirect("projecthome");
			return "projecthome";
		}

		return "sales_ranking";
	}

	@RequestMapping(value = "/deleted_member", method = RequestMethod.GET)
	public String MainController_deleted_member(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.info("deleted_member Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null) {
			response.sendRedirect("projecthome");
			return "projecthome";
		}

		return "deleted_member";
	}

	@RequestMapping(value = "/IAP_amount", method = RequestMethod.GET,  produces = "text/plain;charset=UTF-8")
	public String MainController_IAP_amount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("IAP_amount Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");
		
		// 세션에 프로젝트 존재 X
		if (currentproject == null) {
			response.sendRedirect("projecthome");
			return "projecthome";
		}
		
		logger.info("화폐: " + virtual_MainDao.select(currentproject.getPk()).get(0).getName());
		logger.info("화폐: " + virtual_SubDao.select(currentproject.getPk()).get(0).getName());
		
		request.setAttribute("virtual_main", virtual_MainDao.select(currentproject.getPk()).get(0).getName());
		request.setAttribute("virtual_sub", virtual_SubDao.select(currentproject.getPk()).get(0).getName());
		
		return "IAP_amount";
	}

	@RequestMapping(value = "/activity_path", method = RequestMethod.GET)
	public String MainController_activity_path(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.info("activity_path Page");
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		// 세션에 프로젝트 존재 X
		if (currentproject == null) {
			response.sendRedirect("projecthome");
			return "projecthome";
		}

		return "activity_path";
	}
}
