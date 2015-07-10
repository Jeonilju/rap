package com.example.dangzicforrap;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.example.models.Preference;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CameraActivity extends Activity {

	private static final String TAG = "CameraTestActivity";

	private TextView tvTitle;
	
	private String fileName;
	
	// 카메라 제어
	private Camera camera;
	
	// 촬영된 사진보기
	private ImageView imageView;
	
	// 처리중
	private boolean inProgress;

	// 카메라에 찍힌 이미지 데이터
	byte[] data;
	DataOutputStream dos;
	ImageView view;
	SurfaceView surfaceView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);

		String type = getIntent().getStringExtra("type");
		boolean isUsed = getIntent().getBooleanExtra("isUsed", false);
		
		if (type != null) {
			tvTitle = (TextView) findViewById(R.id.camera_title);
			imageView = (ImageView) findViewById(R.id.ImageView01);
			surfaceView = (SurfaceView) findViewById(R.id.SurfaceView01);

			tvTitle.setText(type);
			if(!isUsed)
				initSampleImage(type);
			else
				initSampleImage2(type);
			
			// surface를 제어하는 SurfaceHolder
			SurfaceHolder holder = surfaceView.getHolder();

			// SurfaceView 리스너를 등록
			holder.addCallback(surfaceListener);

			// 버퍼 사용하지 않음
			holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

			surfaceView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.i(TAG, "셔터 눌림");
					shutterListener.onShutter();
				}
			});
		} else {
			finish();
		}
	}

	/** Sample 이미지 설정 */
	private void initSampleImage(String type){
		if(type.equals("시너지")){
			Log.i(TAG, "시너지");
			imageView.setImageBitmap(assetsRead("1.jpg"));
			fileName = "1.jpg";
		} else if (type.equals("다이나믹")) {
			imageView.setImageBitmap(assetsRead("2.jpg"));
			fileName = "2.jpg";
		} else if (type.equals("스마트")) {
			imageView.setImageBitmap(assetsRead("3.jpg"));
			fileName = "3.jpg";
		} else if (type.equals("북카페")) {
			imageView.setImageBitmap(assetsRead("4.jpg"));
			fileName = "4.jpg";
		} else if (type.equals("복사기")) {
			imageView.setImageBitmap(assetsRead("5.jpg"));
			fileName = "5.jpg";
		} else if (type.equals("3층 화장실 (세면대)")) {
			imageView.setImageBitmap(assetsRead("6.jpg"));
			fileName = "6.jpg";
		} else if (type.equals("3층 화장실 (핸드타올)")) {
			imageView.setImageBitmap(assetsRead("7.jpg"));
			fileName = "7.jpg";
		} else if (type.equals("스마트 TV")) {
			imageView.setImageBitmap(assetsRead("8.jpg"));
			fileName = "8.jpg";
		} else if (type.equals("프리룸")) {
			imageView.setImageBitmap(assetsRead("9.jpg"));
			fileName = "9.jpg";
		} else if (type.equals("체력단련실 (런닝머신)")) {
			imageView.setImageBitmap(assetsRead("10.jpg"));
			fileName = "10.jpg";
		} else if (type.equals("체력단련실 (벤치)")) {
			imageView.setImageBitmap(assetsRead("11.jpg"));
			fileName = "11.jpg";
		} else if (type.equals("애니콜")) {
			imageView.setImageBitmap(assetsRead("12.jpg"));
			fileName = "12.jpg";
		} else if (type.equals("센스")) {
			imageView.setImageBitmap(assetsRead("13.jpg"));
			fileName = "13.jpg";
		} else if (type.equals("지펠")) {
			imageView.setImageBitmap(assetsRead("14.jpg"));
			fileName = "14.jpg";
		} else if (type.equals("갤럭시")) {
			imageView.setImageBitmap(assetsRead("15.jpg"));
			fileName = "15.jpg";
		} else if (type.equals("에덴")) {
			imageView.setImageBitmap(assetsRead("16.jpg"));
			fileName = "16.jpg";
		} else if (type.equals("4층 화상실 (세면대)")) {
			imageView.setImageBitmap(assetsRead("17.jpg"));
			fileName = "17.jpg";
		} else if (type.equals("4층 화장실 (핸드타올)")) {
			imageView.setImageBitmap(assetsRead("18.jpg"));
			fileName = "18.jpg";
		} else if (type.equals("샤워실 (거울)")) {
			imageView.setImageBitmap(assetsRead("19.jpg"));
			fileName = "19.jpg";
		} else if (type.equals("샤워실 (바닥)")) {
			imageView.setImageBitmap(assetsRead("20.jpg"));
			fileName = "20.jpg";
		} else if (type.equals("샤워실 (벽면)")) {
			imageView.setImageBitmap(assetsRead("21.jpg"));
			fileName = "21.jpg";
		} else if (type.equals("샤워실 (선반)")) {
			imageView.setImageBitmap(assetsRead("22.jpg"));
			fileName = "22.jpg";
		} else if (type.equals("샤워실 (세제)")) {
			imageView.setImageBitmap(assetsRead("23.jpg"));
			fileName = "23.jpg";
		} else if (type.equals("샤워실 (바구니)")) {
			imageView.setImageBitmap(assetsRead("24.jpg"));
			fileName = "24.jpg";
		} else if (type.equals("탕비실 (인덕션)")) {
			imageView.setImageBitmap(assetsRead("25.jpg"));
			fileName = "25.jpg";
		} else if (type.equals("탕비실 (싱크대)")) {
			imageView.setImageBitmap(assetsRead("26.jpg"));
			fileName = "26.jpg";
		} else if (type.equals("탕비실 (자판기)")) {
			imageView.setImageBitmap(assetsRead("27.jpg"));
			fileName = "27.jpg";
		} else if (type.equals("탕비실 (식탁)")) {
			imageView.setImageBitmap(assetsRead("28.jpg"));
			fileName = "28.jpg";
		} else if (type.equals("탕비실 (밥솥)")) {
			imageView.setImageBitmap(assetsRead("29.jpg"));
			fileName = "29.jpg";
		} else if (type.equals("탕비실 (식기)")) {
			imageView.setImageBitmap(assetsRead("30.jpg"));
			fileName = "30.jpg";
		} else if (type.equals("수면실 (매트릭스)")) {
			imageView.setImageBitmap(assetsRead("31.jpg"));
			fileName = "31.jpg";
		} else if (type.equals("수면실 (이불 배개)")) {
			imageView.setImageBitmap(assetsRead("32.jpg"));
			fileName = "32.jpg";
		} else if (type.equals("수면실 (요)")) {
			imageView.setImageBitmap(assetsRead("33.jpg"));
			fileName = "33.jpg";
		} else if (type.equals("수면실 (락커)")) {
			imageView.setImageBitmap(assetsRead("34.jpg"));
			fileName = "34.jpg";
		} else if (type.equals("건조실")) {
			imageView.setImageBitmap(assetsRead("35.jpg"));
			fileName = "35.jpg";
		} else if (type.equals("Best")) {

		} else if (type.equals("Worst")) {
		
		}
	}

	private void initSampleImage2(String type){
		if(type.equals("시너지")){
			Log.i(TAG, "시너지");
			imageView.setImageBitmap(assetsRead2("1.jpg"));
			fileName = "1.jpg";
		} else if (type.equals("다이나믹")) {
			imageView.setImageBitmap(assetsRead2("2.jpg"));
			fileName = "2.jpg";
		} else if (type.equals("스마트")) {
			imageView.setImageBitmap(assetsRead2("3.jpg"));
			fileName = "3.jpg";
		} else if (type.equals("북카페")) {
			imageView.setImageBitmap(assetsRead2("4.jpg"));
			fileName = "4.jpg";
		} else if (type.equals("복사기")) {
			imageView.setImageBitmap(assetsRead2("5.jpg"));
			fileName = "5.jpg";
		} else if (type.equals("3층 화장실 (세면대)")) {
			imageView.setImageBitmap(assetsRead2("6.jpg"));
			fileName = "6.jpg";
		} else if (type.equals("3층 화장실 (핸드타올)")) {
			imageView.setImageBitmap(assetsRead2("7.jpg"));
			fileName = "7.jpg";
		} else if (type.equals("스마트 TV")) {
			imageView.setImageBitmap(assetsRead2("8.jpg"));
			fileName = "8.jpg";
		} else if (type.equals("프리룸")) {
			imageView.setImageBitmap(assetsRead2("9.jpg"));
			fileName = "9.jpg";
		} else if (type.equals("체력단련실 (런닝머신)")) {
			imageView.setImageBitmap(assetsRead2("10.jpg"));
			fileName = "10.jpg";
		} else if (type.equals("체력단련실 (벤치)")) {
			imageView.setImageBitmap(assetsRead2("11.jpg"));
			fileName = "11.jpg";
		} else if (type.equals("애니콜")) {
			imageView.setImageBitmap(assetsRead2("12.jpg"));
			fileName = "12.jpg";
		} else if (type.equals("센스")) {
			imageView.setImageBitmap(assetsRead2("13.jpg"));
			fileName = "13.jpg";
		} else if (type.equals("지펠")) {
			imageView.setImageBitmap(assetsRead2("14.jpg"));
			fileName = "14.jpg";
		} else if (type.equals("갤럭시")) {
			imageView.setImageBitmap(assetsRead2("15.jpg"));
			fileName = "15.jpg";
		} else if (type.equals("에덴")) {
			imageView.setImageBitmap(assetsRead2("16.jpg"));
			fileName = "16.jpg";
		} else if (type.equals("4층 화상실 (세면대)")) {
			imageView.setImageBitmap(assetsRead2("17.jpg"));
			fileName = "17.jpg";
		} else if (type.equals("4층 화장실 (핸드타올)")) {
			imageView.setImageBitmap(assetsRead2("18.jpg"));
			fileName = "18.jpg";
		} else if (type.equals("샤워실 (거울)")) {
			imageView.setImageBitmap(assetsRead2("19.jpg"));
			fileName = "19.jpg";
		} else if (type.equals("샤워실 (바닥)")) {
			imageView.setImageBitmap(assetsRead2("20.jpg"));
			fileName = "20.jpg";
		} else if (type.equals("샤워실 (벽면)")) {
			imageView.setImageBitmap(assetsRead2("21.jpg"));
			fileName = "21.jpg";
		} else if (type.equals("샤워실 (선반)")) {
			imageView.setImageBitmap(assetsRead2("22.jpg"));
			fileName = "22.jpg";
		} else if (type.equals("샤워실 (세제)")) {
			imageView.setImageBitmap(assetsRead2("23.jpg"));
			fileName = "23.jpg";
		} else if (type.equals("샤워실 (바구니)")) {
			imageView.setImageBitmap(assetsRead2("24.jpg"));
			fileName = "24.jpg";
		} else if (type.equals("탕비실 (인덕션)")) {
			imageView.setImageBitmap(assetsRead2("25.jpg"));
			fileName = "25.jpg";
		} else if (type.equals("탕비실 (싱크대)")) {
			imageView.setImageBitmap(assetsRead2("26.jpg"));
			fileName = "26.jpg";
		} else if (type.equals("탕비실 (자판기)")) {
			imageView.setImageBitmap(assetsRead2("27.jpg"));
			fileName = "27.jpg";
		} else if (type.equals("탕비실 (식탁)")) {
			imageView.setImageBitmap(assetsRead2("28.jpg"));
			fileName = "28.jpg";
		} else if (type.equals("탕비실 (밥솥)")) {
			imageView.setImageBitmap(assetsRead2("29.jpg"));
			fileName = "29.jpg";
		} else if (type.equals("탕비실 (식기)")) {
			imageView.setImageBitmap(assetsRead2("30.jpg"));
			fileName = "30.jpg";
		} else if (type.equals("수면실 (매트릭스)")) {
			imageView.setImageBitmap(assetsRead2("31.jpg"));
			fileName = "31.jpg";
		} else if (type.equals("수면실 (이불 배개)")) {
			imageView.setImageBitmap(assetsRead2("32.jpg"));
			fileName = "32.jpg";
		} else if (type.equals("수면실 (요)")) {
			imageView.setImageBitmap(assetsRead2("33.jpg"));
			fileName = "33.jpg";
		} else if (type.equals("수면실 (락커)")) {
			imageView.setImageBitmap(assetsRead2("34.jpg"));
			fileName = "34.jpg";
		} else if (type.equals("건조실")) {
			imageView.setImageBitmap(assetsRead2("35.jpg"));
			fileName = "35.jpg";
		} else if (type.equals("Best")) {

		} else if (type.equals("Worst")) {
		
		}
	}
	
	/** 저장된 파일 읽어오기 */
	public Bitmap assetsRead2(String file) {
		InputStream is;
		Bitmap bitmap = null;

		String path = getFilesDir().getPath().toString();
		bitmap = BitmapFactory.decodeFile(path + "/" + file);

		return bitmap;
	}
	
	/** Assets에 있는 이미지 불러오기 */
	public Bitmap assetsRead(String file) {
		InputStream is;
		Bitmap bitmap = null;

		try {
			is = CameraActivity.this.getAssets().open(file);

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
	
	// 카메라 SurfaceView의 리스너
	private SurfaceHolder.Callback surfaceListener = new SurfaceHolder.Callback() {

		// SurfaceView가 생성되었을때 화면에 보여주기위한 초기화 작업을 수행
		// 카메라를 오픈하고, 프리뷰의 위치를 설정한다.
		public void surfaceCreated(SurfaceHolder holder) {

			camera = Camera.open();
			camera.setDisplayOrientation(90);

			try {
				camera.setPreviewDisplay(holder); // 카메라의 preview 설정
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// SurfaceView가 화면에 표시되지않을때(액티비티가 비활성화 될때)호출한다.
		public void surfaceDestroyed(SurfaceHolder holder) {
			camera.release();
			camera = null;
		}

		// surfaceCreate()가 호출된 다음 호출된다.
		// 프리뷰의 크기를 설정하고 프리뷰 영상을 표시한다.
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

			// 카메라의 파라메터 값을 가져와서 미리보기 크기를 설정 하고
			// 프리뷰를 보여준다.
			Camera.Parameters parameters = camera.getParameters();
			List<Camera.Size> list = parameters.getSupportedPictureSizes();

			parameters.setPreviewSize(list.get(list.size() - 7).width, list.get(list.size() - 7).height);
			camera.setParameters(parameters);
			camera.startPreview();
		}
	};

	// 카메라 셔트가 눌러질때
	private Camera.ShutterCallback shutterListener = new Camera.ShutterCallback() {

		public void onShutter() {

			if (camera != null && inProgress == false) {
				camera.takePicture(shutterListener, // 셔터 후
						null, // Raw 이미지 생성 후
						picutureListener); // JPE 이미지 생성 후
				inProgress = true;
			}
		}
	};

	Bitmap bitmap;

	// JPEG 이미지를 생성 후 호출
	private Camera.PictureCallback picutureListener = new Camera.PictureCallback() {

		public void onPictureTaken(byte[] data, Camera camera) {
			if (data != null) {
				// 적용할 옵션이 있는 경우 BitmapFactory클래스의 Options()
				// 메서드로 옵션객체를 만들어 값을 설정하며
				// 이렇게 만들어진 옵션을 Bitmap 객체를 만들때 네번째
				// 아규먼트로 사용한다.

				// 처리하는 이미지의 크기를 축소
				// BitmapFactory.Options options =
				// new BitmapFactory.Options();
				// options.inSampleSize = IN_SAMPLE_SIZE;

				CameraActivity.this.data = data;
				bitmap = imgRotate(BitmapFactory.decodeByteArray(data, 0, data.length, null));

				// 이미지 뷰 이미지 설정
				imageView.setImageBitmap(bitmap);

				doSaveFile(); // sdcard에 파일 저장
				// doFileUpload(); // 서버에 이미지를 전송하는 메서드 호출
				
				camera.startPreview(); // 정지된 프리뷰를 재개
				inProgress = false; // 처리중 플래그를 끔
				
				Preference.putBoolean(CameraActivity.this, fileName, true);
				
				Intent intent = new Intent(CameraActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}

		}

	};

	/** 이미지 90도 회전 */
	private Bitmap imgRotate(Bitmap bmp){

		int width = bmp.getWidth(); 
		int height = bmp.getHeight(); 

		Matrix matrix = new Matrix(); 
		matrix.postRotate(90); 

		Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true); 
		bmp.recycle();

		return resizedBitmap;
	}

	/** 이미지 저장 */
	public void doSaveFile() {

		OutputStream out = null;
		
		String path = getFilesDir().getPath().toString();
		
		File saveFile = new File( path + "/" + fileName); // 파일이 저장되는 경로 지정
		Log.i(TAG, "이미지 저장: " + path + "/" + fileName);
		try {
			saveFile.createNewFile();
			out = new FileOutputStream(saveFile);
			bitmap.compress(CompressFormat.JPEG, 70, out);
			out.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

//	public void doFileUpload() {
//
//		try {
//
//			HttpClient httpClient = new DefaultHttpClient();
//
//			String url = "http://172.20.11.235:8080/web1/file.jsp";
//
//			HttpPost post = new HttpPost(url);
//
//			FileBody bin = new FileBody(saveFile);
//
//			MultipartEntity multipart =
//
//			new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
//
//			multipart.addPart("images", bin);
//
//			post.setEntity(multipart);
//
//			HttpResponse response = httpClient.execute(post);
//
//			HttpEntity resEntity = response.getEntity();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
	
	
}
