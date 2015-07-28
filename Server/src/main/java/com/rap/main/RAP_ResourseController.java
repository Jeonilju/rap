package com.rap.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RAP_ResourseController {

	private static final Logger logger = LoggerFactory.getLogger(RAP_ResourseController.class);
	
	/** SDK 다운로드 탭 */
	@RequestMapping(value = "/sdk", method = RequestMethod.GET)
	public String ResourseController_sdk(HttpServletRequest request) {
		logger.info("download Page");

		return "sdk";
	}
	
	/** Api 문서 다운로드 탭 */
	@RequestMapping(value = "/ApiDoc", method = RequestMethod.GET)
	public String ResourseController_apis(HttpServletRequest request) {
		logger.info("ApiDoc Page");

		return "ApiDoc";
	}
	
	/** Get Start 탭 */
	@RequestMapping(value = "/getStart", method = RequestMethod.GET)
	public String ResourseController_getStart(HttpServletRequest request) {
		logger.info("getStart Page");

		return "getStart";
	}
	
	/** 
	 * 파일 다운로드 
	 * filename 파라미터로 전송된 파일이름을 찾아 다운로드 가능하도록 한다
	 * */
	@RequestMapping(value = "/FileDownload_lanace", method = RequestMethod.POST)
	   public String FileDownload(HttpServletRequest request, HttpServletResponse response) {
	      logger.info("FileDownload");
	      String fileName = request.getParameter("filename");
	      request.setAttribute("filename", fileName);
	      
	      logger.info("fileName: " + fileName);
	      
	      return "FileDownload";
	   }
	
}
