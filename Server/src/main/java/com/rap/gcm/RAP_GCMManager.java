package com.rap.gcm;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.rap.models.UserInfo;

public class RAP_GCMManager {

	private static RAP_GCMManager instance = null;
	
	private static final Logger logger = LoggerFactory.getLogger(RAP_GCMManager.class);
	
	private RAP_GCMManager(){
		
	}
	
	public static RAP_GCMManager getInstance(){
		
		if(instance == null)
			instance = new RAP_GCMManager();
		
		return instance;
	}

	/**
	 * GCM을 보낸다.
	 * @param promotion_pk	프로모션의 PK
	 * @param title	제목
	 * @param content	내용
	 * @param class		class 이름
	 * @param userList	GCM을 받을 사용자 list
	 * */
	public void sendPush(int promotion_pk, String title, String content, String className, List<UserInfo> userList) {
		Sender sender = new Sender("AIzaSyCE2Hy_AvqYduWKT4YiZWoIrGH4iYlu12I");  //구글 코드에서 발급받은 서버 키
		Message msg = new Message.Builder()
							.addData("title", title)  //데이터 추가
							.addData("contents", content)  //데이터 추가
							.addData("class", className)
							.addData("promotion_pk", "" + promotion_pk)
							.build();

		for(UserInfo user : userList){
			try {
				logger.info("보내는 ID: " + user.getGcm_id());
				Result result = sender.send(msg, user.getGcm_id(), 5);
				if(result.getMessageId() != null) {
				      //푸시 전송 성공
					logger.info("성공");
				}
				else {
					logger.info("실패");
					String error = result.getErrorCodeName();   //에러 내용 받기
					if(Constants.ERROR_INTERNAL_SERVER_ERROR.equals(error)) {
				         //구글 푸시 서버 에러
				    }
				      
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
}
