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
import com.rap.dao.MemberDao;
import com.rap.models.CategoryLInfo;
import com.rap.models.MemberInfo;
import com.rap.models.ProjectInfo;

@Controller
public class RAP_MainController {
	private static final Logger logger = LoggerFactory.getLogger(RAP_MainController.class);


	@Autowired
	private MemberDao memberDao;

	@Autowired
	private CategoryLDao categoryLDao;

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

	@RequestMapping(value = "/promotions_analysis", method = RequestMethod.GET)
	public String MainController_promotions_analysis(HttpServletRequest request) {
		logger.info("promotions_analysis Page");

		return "promotions_analysis";
	}	
	
	@RequestMapping(value = "/sales_ranking", method = RequestMethod.GET)
	public String MainController_sales_ranking(HttpServletRequest request) {
		logger.info("sales_ranking Page");

		return "sales_ranking";
	}	
	
	@RequestMapping(value = "/deleted_member", method = RequestMethod.GET)
	public String MainController_deleted_member(HttpServletRequest request) {
		logger.info("deleted_member Page");

		return "deleted_member";
	}	
	
	@RequestMapping(value = "/IAP_amount", method = RequestMethod.GET)
	public String MainController_IAP_amount(HttpServletRequest request) {
		logger.info("IAP_amount Page");

		return "IAP_amount";
	}	
}
