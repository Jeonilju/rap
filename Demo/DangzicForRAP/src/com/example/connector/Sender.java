package com.example.connector;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import com.example.models.Preference;

public class Sender implements Runnable {
	private static String serverIP = "";
	private static int serverPort = 5555;

	private Context mContext;
	
	private Handler callback;
	
	public Sender(Context mContext, Handler callback) {
		super();
		
		this.mContext = mContext;
		this.callback = callback;
		
		serverIP = Preference.getString(mContext, "SAVED_IP");
	}

	@Override
	public void run() {
		try {
			InetAddress serverAddr = InetAddress.getByName(serverIP);
			Socket sock = new Socket(serverAddr, serverPort);
			try {

				PrintWriter out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(sock.getOutputStream())), true);
				out.flush();
				
				DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
				
				for(int n=1;n < 36;n++){
					byte[] buf = new byte[1024];
					
					callback.sendEmptyMessage(n);
					DataInputStream dis = new DataInputStream(mContext.getAssets().open("" + n + "_.jpg"));
					while (dis.read(buf) > 0) {
						dos.write(buf);
						dos.flush();
					}
				}
				
				dos.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				sock.close();
			}
			callback.sendEmptyMessage(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Assets에 있는 이미지 불러오기 */
	public Bitmap assetsRead(String file) {
		InputStream is;
		Bitmap bitmap = null;

		try {
			is = mContext.getAssets().open(file);

			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();

			bitmap = BitmapFactory.decodeByteArray(buffer, 0, buffer.length);

			bitmap = Bitmap.createScaledBitmap(bitmap, 300, 340, true);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return bitmap;
	}

}
