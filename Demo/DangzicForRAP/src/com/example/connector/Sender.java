package com.example.connector;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Handler;

public class Sender {

	private static final String serverIP = "210.118.74.207";
	private static final int serverPort = 9765;
	private String msg;

	private Context context;
	private Handler callback;
	
	public Sender(Context context, Handler callback, String msg) {
		super();
		this.msg = msg;
		this.context = context;
		this.callback = callback;
		
		run();
	}

	public void run() {
		try {
			InetAddress serverAddr = InetAddress.getByName(serverIP);
			Socket sock = new Socket(serverAddr, serverPort);
			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);
				//out.println(msg);
				//out.flush();
				
				for(int n=0;n <= 35;n++){
					if(n == 0){
						byte[] message_byte = msg.getBytes(Charset.forName("UTF-8"));
						
						byte[] bytes = ByteBuffer.allocate(4).putInt(n).array();
						byte[] lenth = ByteBuffer.allocate(4).putInt(message_byte.length).array();
						
						byte[] aaa = new byte[8];
						aaa[0] = bytes[0];
						aaa[1] = bytes[1];
						aaa[2] = bytes[2];
						aaa[3] = bytes[3];
						aaa[4] = lenth[0];
						aaa[5] = lenth[1];
						aaa[6] = lenth[2];
						aaa[7] = lenth[3];
						
						sock.getOutputStream().write(aaa);
						
						
						sock.getOutputStream().write(message_byte);
					}
					else{
						ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
						assetsRead2("" + n + ".jpg").compress(CompressFormat.JPEG, 0 /*ignored for PNG*/, bos); 
						byte[] bitmapdata = bos.toByteArray();
						
						byte[] bytes = ByteBuffer.allocate(4).putInt(n).array();
						byte[] lenth = ByteBuffer.allocate(4).putInt(bitmapdata.length).array();
						
						byte[] aaa = new byte[8];
						aaa[0] = bytes[0];
						aaa[1] = bytes[1];
						aaa[2] = bytes[2];
						aaa[3] = bytes[3];
						aaa[4] = lenth[0];
						aaa[5] = lenth[1];
						aaa[6] = lenth[2];
						aaa[7] = lenth[3];
						
						sock.getOutputStream().write(aaa);
						
						sock.getOutputStream().write(bitmapdata);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sock.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		callback.sendEmptyMessage(-1);
	}

	/** 저장된 파일 읽어오기 */
	public Bitmap assetsRead2(String file) {
		Bitmap bitmap = null;
		String path = context.getFilesDir().getPath().toString();
		bitmap = BitmapFactory.decodeFile(path + "/" + file);

		return bitmap;
	}
}
