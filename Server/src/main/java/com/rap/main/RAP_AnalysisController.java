package com.rap.main;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rap.analysismodels.DeletedmembercountInfo;
import com.rap.analysismodels.NewmemberInfo;
import com.rap.analysismodels.OPcountInfo;
import com.rap.analysismodels.OSInfo;
import com.rap.dao.ActivityDao;
import com.rap.dao.CategoryLDao;
import com.rap.dao.CategoryMDao;
import com.rap.dao.CategorySDao;
import com.rap.dao.DeleteuserDao;
import com.rap.dao.IAPDao;
import com.rap.dao.Log_timeDao;
import com.rap.dao.PayDao;
import com.rap.dao.PromotionDao;
import com.rap.dao.PromotionResultDao;
import com.rap.dao.TimeDao;
import com.rap.dao.UserDao;
import com.rap.models.BestActivityInfo;
import com.rap.models.DeviceInfo;
import com.rap.models.IAPInfo;
import com.rap.models.PayInfo;
import com.rap.models.ProjectInfo;
import com.rap.models.PromotionResultInfo;
import com.rap.models.TimeInfo;

@Controller
public class RAP_AnalysisController {
	private static final Logger logger = LoggerFactory
			.getLogger(RAP_AnalysisController.class);
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
	private TimeDao timeDao;
	
	@Autowired
	private DeleteuserDao deletedmemberDao;

	@Autowired
	private IAPDao iapDao;
	
	@Autowired
	private PayDao payDao;

	@Autowired
	private PromotionResultDao promotionResultDao;

	@Autowired
	private PromotionDao promotionDao;
	
	@Autowired
	private UserDao userDao;

	/** 분석 페이지 */
	@RequestMapping(value = "/Analysis", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String TCManagement_GET(HttpServletRequest request) {
		logger.info("Analysis Tab");

		return "Analysis";
	}

	/** 성별 데이터 반환 */
	@RequestMapping(value = "/sex_db", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String Sex_Get(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Sex_db Tab");

		JSONObject jObject = new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session
				.getAttribute("currentproject");

		if (currentproject == null)
			return "";// 세션에 프로젝트 없는 경우

		String project_key = currentproject.getPk();

		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";

		int none = userDao.countSex(project_key, 0);
		int woman = userDao.countSex(project_key, 2);
		int man = userDao.countSex(project_key, 1);
		jObject.put("woman", woman);
		jObject.put("man", man);
		jObject.put("none", none);
		
		logger.info("성별: (" + none + "/" + man + "/" + woman + ")");
		
		if(none == 0 && woman == 0 && man == 0){
			return "";
		}
		
		return jObject.toString();
	}

	/** 나이 데이터 반환 */
	@RequestMapping(value = "/age_db", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String Age_get(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Age_db Tab");

		JSONObject jObject = new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session
				.getAttribute("currentproject");

		if (currentproject == null)
			return "";// 세션에 프로젝트 없는 경우
		String project_key = currentproject.getPk();

		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";

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
		
		int total = baby + ten + twenty + thirty + forty + old;
		if(total == 0){
			return "";
		}

		return jObject.toString();
	}

	/** 신규 유저 데이터 반환 */
	@RequestMapping(value = "/new_member_db", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String New_member_Get(HttpServletRequest request,
			HttpServletResponse response
			, @RequestParam("during") int during) {
		logger.info("new_member Tab");

		JSONObject jObject = new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		if (currentproject == null)
			return "";// 세션에 프로젝트 없는 경우
		
		String project_key = currentproject.getPk();
		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";
		
		java.util.Date date= new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		List<NewmemberInfo> receive = userDao.count_new_member(project_key, current, during);
		List<String> result = new ArrayList<String>();
		logger.info("size: " + receive.size());
		
		if(receive.size() == 0){
			return "";
		}
		
		for (int i = 0; i < receive.size(); i++) {

			if(during == 0 || during == 1 || during == 2 || during == 3){
				String year = "" + (receive.get(i).getStart().getYear() + 1900);
				String month = receive.get(i).getStart().getMonth() + 1 > 9 ? "" + (receive.get(i).getStart().getMonth() + 1) : "0" + (receive.get(i).getStart().getMonth() + 1);
				String day = receive.get(i).getStart().getDate() > 9 ? "" + receive.get(i).getStart().getDate() : "0" + receive.get(i).getStart().getDate();
				result.add( "[" + year + "년 " + month + "월 " + day + "일"  + "," + receive.get(i).getCount() + "]" );
			}
			else{
				
				if(receive.size() == 4 && i == 0){
					continue;
				}
				String year = "" + (receive.get(i).getStart().getYear() + 1900);
				String month = receive.get(i).getStart().getMonth() + 1 > 9 ? "" + (receive.get(i).getStart().getMonth() + 1) : "0" + (receive.get(i).getStart().getMonth() + 1);
				result.add( "[" + year + "년" + month + "월" + "," + receive.get(i).getCount() + "]" );
			}
		}

		logger.info("NewmemberInfo : " + result.toString());
		jObject.put("result", result.toString());
		return jObject.toString();
	}

	/** 사용 안함*/
	@RequestMapping(value = "/deleted_member_db", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleted_member_Get(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("type") String type,
			@RequestParam("start") String start) {

		Calendar cal;
		Timestamp starttime = null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		start = start.replace("-", "");
		String date = new String(start + "000000");
		try {
			sd.parse(date);
			cal = sd.getCalendar();

			starttime = new Timestamp(cal.getTime().getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}

		logger.info("deleted_member Tab" + "  type : " + type + " start : "
				+ starttime.toString());

		JSONObject jObject = new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session
				.getAttribute("currentproject");

		if (currentproject == null)
			return "";// 세션에 프로젝트 없는 경우

		String project_key = currentproject.getPk();
		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";

		List<DeletedmembercountInfo> receive = deletedmemberDao
				.count_deleted_member(project_key, type, starttime);
		// List<List<String>> result=new ArrayList<List<String>>();
		List<String> result = new ArrayList<String>();

		for (int i = 0; i < receive.size(); i++) {

			List<String> templist = new ArrayList<String>();
			// templist.add(0,Hourformatter.format((java.util.Date)
			// receive.get(i).getStart()));

			if (type.equals("day"))
				templist.add(0, monthDayYearformatter
						.format((java.util.Date) receive.get(i).getStart()));
			if (type.equals("month"))
				templist.add(
						0,
						monthYearformatter.format((java.util.Date) receive.get(
								i).getStart()));
			if (type.equals("year"))
				templist.add(0, Yearformatter.format((java.util.Date) receive
						.get(i).getStart()));

			templist.add(1, receive.get(i).getCount() + "");
			result.add(templist.toString());
		}

		logger.info("DeletedmembercountInfo : " + result.toString());
		jObject.put("result", result.toString());
		return jObject.toString();

	}

	/** 사용 횟수 */
	@RequestMapping(value = "/operation_count_db", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String Operation_count_Get(HttpServletRequest request,
			HttpServletResponse response
			, @RequestParam("start_date") String start_date
			, @RequestParam("end_date") String end_date
			, @RequestParam("sex_num") int sex_num
			, @RequestParam("age") int age
			, @RequestParam("grade_using") int grade_using
			, @RequestParam("grade_time") int grade_time) {

		try{
			start_date = start_date + " 00:00:00.0";
			end_date = end_date + " 00:00:00.0";
			
			Timestamp start_time = Timestamp.valueOf(start_date);
			Timestamp end_time = Timestamp.valueOf(end_date);
			
			JSONObject jObject = new JSONObject();
			HttpSession session = request.getSession();
			ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

			if (currentproject == null)
				return "";// 세션에 프로젝트 없는 경우

			String project_key = currentproject.getPk();
			if (project_key == null || project_key.isEmpty())
				return "";

			List<OPcountInfo> receive = log_timeDao.getOperationCount(project_key, start_time, end_time, sex_num, age, grade_time, grade_using);
			//List<UserInfo> userInfo = userDao.select(project_key, sex_num, age, grade_time, grade_using);
			
			List<String> result = new ArrayList<String>();
			
			if(receive.size() == 0)
				return "";
			
			for (int i = 0; i < receive.size(); i++) {
				
				String year = "" + (receive.get(i).getStart().getYear() + 1900);
				String month = receive.get(i).getStart().getMonth() + 1 > 9 ? "" + (receive.get(i).getStart().getMonth() + 1) : "0" + (receive.get(i).getStart().getMonth() + 1);
				String date = receive.get(i).getStart().getDate() > 9 ? "" + receive.get(i).getStart().getDate() : "0" + receive.get(i).getStart().getDate();
				
				result.add( "[" + year + "년 " + month + "월 " + date + "일"  + "," + receive.get(i).getCount() + "]" );
			}

			logger.info("OPcountInfo : " + result.toString());
			jObject.put("result", result.toString());
			return jObject.toString();
		}
		catch(Exception e){
			return "";
		}
	}

	/** 사용 시간대 조회 */
	@RequestMapping(value = "/operation_time_db", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String Operation_time_Get(HttpServletRequest request,
			HttpServletResponse response
			, @RequestParam("start_date") String start_date
			, @RequestParam("end_date") String end_date
			, @RequestParam("sex_num") int sex_num
			, @RequestParam("age") int age
			, @RequestParam("grade_using") int grade_using
			, @RequestParam("grade_time") int grade_time) {

		logger.info("operation_time Tab");

		try{
			start_date = start_date + " 00:00:00.0";
			end_date = end_date + " 00:00:00.0";
			
			Timestamp start_time = Timestamp.valueOf(start_date);
			Timestamp end_time = Timestamp.valueOf(end_date);
			
			JSONObject jObject = new JSONObject();
			HttpSession session = request.getSession();
			ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

			if (currentproject == null)
				return "";// 세션에 프로젝트 없는 경우

			String project_key = currentproject.getPk();
			if (project_key == null)
				return "";
			if (project_key.isEmpty())
				return "";

			List<TimeInfo> time_log_info = timeDao.select(project_key, start_time, end_time, sex_num, age, grade_time, grade_using);
			
			if(time_log_info.size() == 0)
				return "";
			
			List<String> result = new ArrayList<String>();
			int []temp = new int[25];
			
			for(int n=0;n<25;n++){
				temp[n] = 0;
			}
			
			for(int n=0;n < time_log_info.size();n++){
				temp[time_log_info.get(n).getStart().getHours()]++;
			}
			
			for(int n=1;n<25;n++){
				result.add("[" + n + "시" + "," + temp[n] +"]");
			}
			
			jObject.put("result", result.toString());
			return jObject.toString();
		}
		catch(Exception e){
			return "";
		}
	}

	/** 기기 정보 조회 */
	@RequestMapping(value = "/device_db", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String Device_Get(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Device_db Tab");

		JSONObject jObject = new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session
				.getAttribute("currentproject");

		if (currentproject == null)
			return "";// 세션에 프로젝트 없는 경우

		String project_key = currentproject.getPk();

		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";

		List<DeviceInfo> result = userDao.countDevice(project_key);
		if(result.size() == 0){
			return "";
		}
		
		jObject.put("Device", result);
		return jObject.toString();
	}

	/** OS 정보 조회 */
	@RequestMapping(value = "/os_db", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String OS_Get(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("OS_db Tab");

		JSONObject jObject = new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session
				.getAttribute("currentproject");

		if (currentproject == null)
			return "";// 세션에 프로젝트 없는 경우

		String project_key = currentproject.getPk();

		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";

		List<OSInfo> result = userDao.countOS(project_key);
		if(result.size() == 0){
			return "";
		}
		jObject.put("OS", result);
		return jObject.toString();
	}

	/** 최다 Activity 조회 */
	@RequestMapping(value = "/best_activity_db", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String Best_activity_Get(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Best_activity_db Tab");

		JSONObject jObject = new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session
				.getAttribute("currentproject");

		if (currentproject == null)
			return "";// 세션에 프로젝트 없는 경우

		String project_key = currentproject.getPk();

		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";

		List<BestActivityInfo> receive = activityDao
				.countBest_activity(project_key);

		List<String> name = new ArrayList<String>();
		List<Integer> count = new ArrayList<Integer>();

		int size = receive.size();
		
		if(size == 0){
			return "";
		}
		
		for (int i = 0; i < size; i++) {
			name.add(receive.get(i).getActivity_name());
			count.add(receive.get(i).getCount());

		}

		jObject.put("activity_name", name);
		jObject.put("count", count);
		logger.info("best_activity Json : " + jObject.toString());
		return jObject.toString();
	}

	/** 프로모션 데이터 조회 */
	@RequestMapping(value = "/promotions_analysis_db", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String Promotions_analysis_Get(HttpServletRequest request,
			HttpServletResponse response
			, @RequestParam("sex_num") int sex_num
			, @RequestParam("age") int age
			, @RequestParam("grade_using") int grade_using
			, @RequestParam("grade_time") int grade_time
			, @RequestParam("promotion_pk") int promotion_pk) {

		
		logger.info("promotions_analysis Tab");
		try{
			JSONObject jObject = new JSONObject();
			HttpSession session = request.getSession();
			ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

			if (currentproject == null)
				return "";// 세션에 프로젝트 없는 경우

			String project_key = currentproject.getPk();
			if (project_key == null)
				return "";
			if (project_key.isEmpty())
				return "";

			List<PromotionResultInfo> receive = promotionResultDao.select(project_key, promotion_pk, sex_num, age, grade_time, grade_using);
			
			if(receive.size()== 0){
				logger.info("프로모션 결과 조회 데이터 없음");
				return "";
			}
			List<String> result = new ArrayList<String>();
			
			
			Timestamp startTime = promotionDao.selectPromotion(project_key, promotion_pk).getReg_date();
			Timestamp current = new Timestamp((new java.util.Date()).getTime());
			
			logger.info("시작시간: " + startTime.toString() );
			
			int number = 0;
			int whileCount = 0;
			int beforeCount = -1;
			while(whileCount < 24 * 3 && startTime.compareTo(current) <= 0){
				logger.info("계산시간: " + startTime.toString() );
				int count = 0;
				for (int i = 0; i < receive.size(); i++) {
					if(receive.get(i).getReg_date().compareTo(startTime) <= 0){
						count++;
					}
				}
				
				String day = startTime.getDate() > 9 ? "" + startTime.getDate() : "0" + startTime.getDate();
				String hour = startTime.getHours() > 9 ? "" + startTime.getHours() : "0" + startTime.getHours();
				
					result.add("[" + number + "시간 후" + ", " + count + "]");
					logger.info("추가: " + "[" + number + ", " + count + "]");
				
				if(startTime.getHours() == 23){
					startTime.setDate(startTime.getDate() + 1);
					startTime.setHours(0);
				}else{
					startTime.setHours(startTime.getHours() + 1);
				}
				number++;
			}

			logger.info("Promotion_resultInfo : " + result.toString());
			jObject.put("result", result.toString());
			return jObject.toString();
		}
		catch(Exception e){
			
			return "";
		}
	}

	/** 판매 순위 정보 조회 */
	@RequestMapping(value = "/sales_ranking_db", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String sales_ranking_Get(HttpServletRequest request
			, @RequestParam("start_date") String start_date
			, @RequestParam("end_date") String end_date
			, @RequestParam("sex_num") int sex_num
			, @RequestParam("age") int age
			, @RequestParam("grade_using") int grade_using
			, @RequestParam("grade_time") int grade_time
			, HttpServletResponse response) {
		logger.info("sales_ranking_db Tab");

		if(start_date.equals("") || end_date.equals("")){
			return "";
		}
		
		start_date = start_date + " 00:00:00.0";
		end_date = end_date + " 00:00:00.0";
		
		logger.info("end_date: " + end_date);
		
		Timestamp start_time = Timestamp.valueOf(start_date);
		Timestamp end_time = Timestamp.valueOf(end_date);
		
		JSONObject jObject = new JSONObject();
		HttpSession session = request.getSession();
		ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

		if (currentproject == null)
			return "";// 세션에 프로젝트 없는 경우

		String project_key = currentproject.getPk();

		if (project_key == null)
			return "";
		if (project_key.isEmpty())
			return "";

		List<IAPInfo> receive = iapDao.getRankingCount(project_key, start_time, end_time, sex_num, age, grade_time, grade_using);

		int size = receive.size();
		if(size == 0){
			return "";
		}
		
		List<String> item_name = new ArrayList<String>();
		List<Integer> count = new ArrayList<Integer>();

		for (int i = 0; i < size; i++) {
			logger.info("item_name: " + receive.get(i).getName());
			item_name.add(receive.get(i).getName());
			count.add(receive.get(i).getCount());
		}

		jObject.put("item_name", item_name);
		jObject.put("count", count);
		return jObject.toString();
	}

	/** IAP 곡선 조회 */
	@RequestMapping(value = "/IAP_amount_db", method = RequestMethod.POST , produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String IAP_amount_Get(HttpServletRequest request,
			HttpServletResponse response
			, @RequestParam("start_date") String start_date
			, @RequestParam("end_date") String end_date
			, @RequestParam("sex_num") int sex_num
			, @RequestParam("age") int age
			, @RequestParam("grade_using") int grade_using
			, @RequestParam("grade_time") int grade_time
			, @RequestParam("money") int money) {

		logger.info("IAP_amount_db Tab");

		try{
			start_date = start_date + " 00:00:00.0";
			end_date = end_date + " 00:00:00.0";
			
			Timestamp start_time = Timestamp.valueOf(start_date);
			Timestamp end_time = Timestamp.valueOf(end_date);
			
			JSONObject jObject = new JSONObject();
			HttpSession session = request.getSession();
			ProjectInfo currentproject = (ProjectInfo) session.getAttribute("currentproject");

			if (currentproject == null)
				return "";// 세션에 프로젝트 없는 경우

			String project_key = currentproject.getPk();
			if (project_key == null)
				return "";
			if (project_key.isEmpty())
				return "";
			
			
			List<PayInfo> result = payDao.select(project_key, start_time, end_time, sex_num, age, grade_time, grade_using, money);
			
			if(result.size() == 0)
				return "";
			
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			
			List<String> map_key_list = new ArrayList<String>();
			List<String> start_list = new ArrayList<String>();
			List<Integer> count = new ArrayList<Integer>();

			logger.info("결과수: " + result.size());
			
			for(int n=0;n < result.size();n++){
				
				PayInfo info = result.get(n);
				
				String year = "" + (info.getReg_date().getYear() + 1900);
				String month = info.getReg_date().getMonth() + 1 > 9 ? "" + (info.getReg_date().getMonth() + 1) : "0" + (info.getReg_date().getMonth() + 1);
				String day = info.getReg_date().getDate() > 9 ? "" + info.getReg_date().getDate() : "0" + info.getReg_date().getDate();
				
				String keyVal = year + "년 " + month + "월 " + day + "일";
				logger.info("날짜: " + keyVal);
				if(map.containsKey(keyVal)){
					map.put(keyVal, map.get(keyVal) + info.getPrice());	
				}
				else{
					map_key_list.add(keyVal);
					map.put(keyVal, info.getPrice());
				}
			}
			
			for(int n=0;n < map_key_list.size();n++){
				String key = map_key_list.get(n);
				start_list.add(key);
				count.add(map.get(key));
				logger.info("key: " + key + ", count: " + map.get(key));
			}
			
			jObject.put("start_time", start_list);
			jObject.put("count", count);

			return jObject.toString();
		}
		catch(Exception e){
			return "";
		}
	}
}
