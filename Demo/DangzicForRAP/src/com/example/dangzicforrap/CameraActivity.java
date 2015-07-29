package com.example.dangzicforrap;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

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

import com.example.models.Preference;

public class CameraActivity extends Activity {

	private static final String TAG = "CameraTestActivity";

	private TextView tvTitle;
	
	private String fileName;
	
	// ī�޶� ����
	private Camera camera;
	
	// �Կ��� ��������
	private ImageView imageView;
	
	// ó����
	private boolean inProgress;

	// ī�޶� ���� �̹��� ������
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
			
			// surface�� �����ϴ� SurfaceHolder
			SurfaceHolder holder = surfaceView.getHolder();

			// SurfaceView �����ʸ� ���
			holder.addCallback(surfaceListener);

			// ���� ������� ����
			holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

			surfaceView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.i(TAG, "���� ����");
					shutterListener.onShutter();
				}
			});
		} else {
			finish();
		}
	}

	/** Sample �̹��� ���� */
	private void initSampleImage(String type){
		if(type.equals("�ó���")){
			Log.i(TAG, "�ó���");
			imageView.setImageBitmap(assetsRead("1.jpg"));
			fileName = "1_.jpg";
		} else if (type.equals("���̳���")) {
			imageView.setImageBitmap(assetsRead("2.jpg"));
			fileName = "2_.jpg";
		} else if (type.equals("����Ʈ")) {
			imageView.setImageBitmap(assetsRead("3.jpg"));
			fileName = "3_.jpg";
		} else if (type.equals("��ī��")) {
			imageView.setImageBitmap(assetsRead("4.jpg"));
			fileName = "4_.jpg";
		} else if (type.equals("�����")) {
			imageView.setImageBitmap(assetsRead("5.jpg"));
			fileName = "5_.jpg";
		} else if (type.equals("3�� ȭ��� (�����)")) {
			imageView.setImageBitmap(assetsRead("6.jpg"));
			fileName = "6_.jpg";
		} else if (type.equals("3�� ȭ��� (�ڵ�Ÿ��)")) {
			imageView.setImageBitmap(assetsRead("7.jpg"));
			fileName = "7_.jpg";
		} else if (type.equals("����Ʈ TV")) {
			imageView.setImageBitmap(assetsRead("8.jpg"));
			fileName = "8_.jpg";
		} else if (type.equals("������")) {
			imageView.setImageBitmap(assetsRead("9.jpg"));
			fileName = "9_.jpg";
		} else if (type.equals("ü�´ܷý� (���׸ӽ�)")) {
			imageView.setImageBitmap(assetsRead("10.jpg"));
			fileName = "10_.jpg";
		} else if (type.equals("ü�´ܷý� (��ġ)")) {
			imageView.setImageBitmap(assetsRead("11.jpg"));
			fileName = "11_.jpg";
		} else if (type.equals("�ִ���")) {
			imageView.setImageBitmap(assetsRead("12.jpg"));
			fileName = "12_.jpg";
		} else if (type.equals("����")) {
			imageView.setImageBitmap(assetsRead("13.jpg"));
			fileName = "13_.jpg";
		} else if (type.equals("����")) {
			imageView.setImageBitmap(assetsRead("14.jpg"));
			fileName = "14_.jpg";
		} else if (type.equals("������")) {
			imageView.setImageBitmap(assetsRead("15.jpg"));
			fileName = "15_.jpg";
		} else if (type.equals("����")) {
			imageView.setImageBitmap(assetsRead("16.jpg"));
			fileName = "16_.jpg";
		} else if (type.equals("4�� ȭ��� (�����)")) {
			imageView.setImageBitmap(assetsRead("17.jpg"));
			fileName = "17_.jpg";
		} else if (type.equals("4�� ȭ��� (�ڵ�Ÿ��)")) {
			imageView.setImageBitmap(assetsRead("18.jpg"));
			fileName = "18_.jpg";
		} else if (type.equals("������ (�ſ�)")) {
			imageView.setImageBitmap(assetsRead("19.jpg"));
			fileName = "19_.jpg";
		} else if (type.equals("������ (�ٴ�)")) {
			imageView.setImageBitmap(assetsRead("20.jpg"));
			fileName = "20_.jpg";
		} else if (type.equals("������ (����)")) {
			imageView.setImageBitmap(assetsRead("21.jpg"));
			fileName = "21_.jpg";
		} else if (type.equals("������ (����)")) {
			imageView.setImageBitmap(assetsRead("22.jpg"));
			fileName = "22_.jpg";
		} else if (type.equals("������ (����)")) {
			imageView.setImageBitmap(assetsRead("23.jpg"));
			fileName = "23_.jpg";
		} else if (type.equals("������ (�ٱ���)")) {
			imageView.setImageBitmap(assetsRead("24.jpg"));
			fileName = "24_.jpg";
		} else if (type.equals("����� (�δ���)")) {
			imageView.setImageBitmap(assetsRead("25.jpg"));
			fileName = "25_.jpg";
		} else if (type.equals("����� (��ũ��)")) {
			imageView.setImageBitmap(assetsRead("26.jpg"));
			fileName = "26_.jpg";
		} else if (type.equals("����� (���Ǳ�)")) {
			imageView.setImageBitmap(assetsRead("27.jpg"));
			fileName = "27_.jpg";
		} else if (type.equals("����� (��Ź)")) {
			imageView.setImageBitmap(assetsRead("28.jpg"));
			fileName = "28_.jpg";
		} else if (type.equals("����� (���)")) {
			imageView.setImageBitmap(assetsRead("29.jpg"));
			fileName = "29_.jpg";
		} else if (type.equals("����� (�ı�)")) {
			imageView.setImageBitmap(assetsRead("30.jpg"));
			fileName = "30_.jpg";
		} else if (type.equals("����� (��Ʈ����)")) {
			imageView.setImageBitmap(assetsRead("31.jpg"));
			fileName = "31_.jpg";
		} else if (type.equals("����� (�̺� �谳)")) {
			imageView.setImageBitmap(assetsRead("32.jpg"));
			fileName = "32_.jpg";
		} else if (type.equals("����� (��)")) {
			imageView.setImageBitmap(assetsRead("33.jpg"));
			fileName = "33_.jpg";
		} else if (type.equals("����� (��Ŀ)")) {
			imageView.setImageBitmap(assetsRead("34.jpg"));
			fileName = "34_.jpg";
		} else if (type.equals("������")) {
			imageView.setImageBitmap(assetsRead("35.jpg"));
			fileName = "35_.jpg";
		} else if (type.equals("Best")) {

		} else if (type.equals("Worst")) {
		
		}
	}

	private void initSampleImage2(String type){
		if(type.equals("�ó���")){
			Log.i(TAG, "�ó���");
			imageView.setImageBitmap(assetsRead2("1_.jpg"));
			fileName = "1_.jpg";
		} else if (type.equals("���̳���")) {
			imageView.setImageBitmap(assetsRead2("2_.jpg"));
			fileName = "2_.jpg";
		} else if (type.equals("����Ʈ")) {
			imageView.setImageBitmap(assetsRead2("3_.jpg"));
			fileName = "3_.jpg";
		} else if (type.equals("��ī��")) {
			imageView.setImageBitmap(assetsRead2("4_.jpg"));
			fileName = "4_.jpg";
		} else if (type.equals("�����")) {
			imageView.setImageBitmap(assetsRead2("5_.jpg"));
			fileName = "5_.jpg";
		} else if (type.equals("3�� ȭ��� (�����)")) {
			imageView.setImageBitmap(assetsRead2("6_.jpg"));
			fileName = "6_.jpg";
		} else if (type.equals("3�� ȭ��� (�ڵ�Ÿ��)")) {
			imageView.setImageBitmap(assetsRead2("7_.jpg"));
			fileName = "7_.jpg";
		} else if (type.equals("����Ʈ TV")) {
			imageView.setImageBitmap(assetsRead2("8_.jpg"));
			fileName = "8_.jpg";
		} else if (type.equals("������")) {
			imageView.setImageBitmap(assetsRead2("9_.jpg"));
			fileName = "9_.jpg";
		} else if (type.equals("ü�´ܷý� (���׸ӽ�)")) {
			imageView.setImageBitmap(assetsRead2("10_.jpg"));
			fileName = "10_.jpg";
		} else if (type.equals("ü�´ܷý� (��ġ)")) {
			imageView.setImageBitmap(assetsRead2("11_.jpg"));
			fileName = "11_.jpg";
		} else if (type.equals("�ִ���")) {
			imageView.setImageBitmap(assetsRead2("12_.jpg"));
			fileName = "12_.jpg";
		} else if (type.equals("����")) {
			imageView.setImageBitmap(assetsRead2("13_.jpg"));
			fileName = "13_.jpg";
		} else if (type.equals("����")) {
			imageView.setImageBitmap(assetsRead2("14_.jpg"));
			fileName = "14_.jpg";
		} else if (type.equals("������")) {
			imageView.setImageBitmap(assetsRead2("15_.jpg"));
			fileName = "15_.jpg";
		} else if (type.equals("����")) {
			imageView.setImageBitmap(assetsRead2("16_.jpg"));
			fileName = "16_.jpg";
		} else if (type.equals("4�� ȭ��� (�����)")) {
			imageView.setImageBitmap(assetsRead2("17_.jpg"));
			fileName = "17_.jpg";
		} else if (type.equals("4�� ȭ��� (�ڵ�Ÿ��)")) {
			imageView.setImageBitmap(assetsRead2("18_.jpg"));
			fileName = "18_.jpg";
		} else if (type.equals("������ (�ſ�)")) {
			imageView.setImageBitmap(assetsRead2("19_.jpg"));
			fileName = "19_.jpg";
		} else if (type.equals("������ (�ٴ�)")) {
			imageView.setImageBitmap(assetsRead2("20_.jpg"));
			fileName = "20_.jpg";
		} else if (type.equals("������ (����)")) {
			imageView.setImageBitmap(assetsRead2("21_.jpg"));
			fileName = "21_.jpg";
		} else if (type.equals("������ (����)")) {
			imageView.setImageBitmap(assetsRead2("22_.jpg"));
			fileName = "22_.jpg";
		} else if (type.equals("������ (����)")) {
			imageView.setImageBitmap(assetsRead2("23_.jpg"));
			fileName = "23_.jpg";
		} else if (type.equals("������ (�ٱ���)")) {
			imageView.setImageBitmap(assetsRead2("24_.jpg"));
			fileName = "24_.jpg";
		} else if (type.equals("����� (�δ���)")) {
			imageView.setImageBitmap(assetsRead2("25_.jpg"));
			fileName = "25_.jpg";
		} else if (type.equals("����� (��ũ��)")) {
			imageView.setImageBitmap(assetsRead2("26_.jpg"));
			fileName = "26_.jpg";
		} else if (type.equals("����� (���Ǳ�)")) {
			imageView.setImageBitmap(assetsRead2("27_.jpg"));
			fileName = "27_.jpg";
		} else if (type.equals("����� (��Ź)")) {
			imageView.setImageBitmap(assetsRead2("28_.jpg"));
			fileName = "28__.jpg";
		} else if (type.equals("����� (���)")) {
			imageView.setImageBitmap(assetsRead2("29_.jpg"));
			fileName = "29_.jpg";
		} else if (type.equals("����� (�ı�)")) {
			imageView.setImageBitmap(assetsRead2("30_.jpg"));
			fileName = "30_.jpg";
		} else if (type.equals("����� (��Ʈ����)")) {
			imageView.setImageBitmap(assetsRead2("31_.jpg"));
			fileName = "31_.jpg";
		} else if (type.equals("����� (�̺� �谳)")) {
			imageView.setImageBitmap(assetsRead2("32_.jpg"));
			fileName = "32_.jpg";
		} else if (type.equals("����� (��)")) {
			imageView.setImageBitmap(assetsRead2("33_.jpg"));
			fileName = "33_.jpg";
		} else if (type.equals("����� (��Ŀ)")) {
			imageView.setImageBitmap(assetsRead2("34_.jpg"));
			fileName = "34_.jpg";
		} else if (type.equals("������")) {
			imageView.setImageBitmap(assetsRead2("35_.jpg"));
			fileName = "35_.jpg";
		} else if (type.equals("Best")) {

		} else if (type.equals("Worst")) {
		
		}
	}
	
	/** ����� ���� �о���� */
	public Bitmap assetsRead2(String file) {
		InputStream is;
		Bitmap bitmap = null;

		String path = getFilesDir().getPath().toString();
		bitmap = BitmapFactory.decodeFile(path + "/" + file);

		return bitmap;
	}
	
	/** Assets�� �ִ� �̹��� �ҷ����� */
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
	
	// ī�޶� SurfaceView�� ������
	private SurfaceHolder.Callback surfaceListener = new SurfaceHolder.Callback() {

		// SurfaceView�� �����Ǿ����� ȭ�鿡 �����ֱ����� �ʱ�ȭ �۾��� ����
		// ī�޶� �����ϰ�, �������� ��ġ�� �����Ѵ�.
		public void surfaceCreated(SurfaceHolder holder) {

			camera = Camera.open();
			camera.setDisplayOrientation(90);

			try {
				camera.setPreviewDisplay(holder); // ī�޶��� preview ����
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// SurfaceView�� ȭ�鿡 ǥ�õ���������(��Ƽ��Ƽ�� ��Ȱ��ȭ �ɶ�)ȣ���Ѵ�.
		public void surfaceDestroyed(SurfaceHolder holder) {
			camera.release();
			camera = null;
		}

		// surfaceCreate()�� ȣ��� ���� ȣ��ȴ�.
		// �������� ũ�⸦ �����ϰ� ������ ������ ǥ���Ѵ�.
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

			// ī�޶��� �Ķ���� ���� �����ͼ� �̸����� ũ�⸦ ���� �ϰ�
			// �����並 �����ش�.
			Camera.Parameters parameters = camera.getParameters();
			List<Camera.Size> list = parameters.getSupportedPictureSizes();

			parameters.setPreviewSize(list.get(list.size() - 7).width, list.get(list.size() - 7).height);
			camera.setParameters(parameters);
			camera.startPreview();
		}
	};

	// ī�޶� ��Ʈ�� ��������
	private Camera.ShutterCallback shutterListener = new Camera.ShutterCallback() {

		public void onShutter() {

			if (camera != null && inProgress == false) {
				camera.takePicture(shutterListener, // ���� ��
						null, // Raw �̹��� ���� ��
						picutureListener); // JPE �̹��� ���� ��
				inProgress = true;
			}
		}
	};

	Bitmap bitmap;

	// JPEG �̹����� ���� �� ȣ��
	private Camera.PictureCallback picutureListener = new Camera.PictureCallback() {

		public void onPictureTaken(byte[] data, Camera camera) {
			if (data != null) {
				// ������ �ɼ��� �ִ� ��� BitmapFactoryŬ������ Options()
				// �޼���� �ɼǰ�ü�� ����� ���� �����ϸ�
				// �̷��� ������� �ɼ��� Bitmap ��ü�� ���鶧 �׹�°
				// �ƱԸ�Ʈ�� ����Ѵ�.

				// ó���ϴ� �̹����� ũ�⸦ ���
				// BitmapFactory.Options options =
				// new BitmapFactory.Options();
				// options.inSampleSize = IN_SAMPLE_SIZE;

				CameraActivity.this.data = data;
				bitmap = imgRotate(BitmapFactory.decodeByteArray(data, 0, data.length, null));

				// �̹��� �� �̹��� ����
				imageView.setImageBitmap(bitmap);

				doSaveFile(); // sdcard�� ���� ����
				// doFileUpload(); // ������ �̹����� �����ϴ� �޼��� ȣ��
				
				camera.startPreview(); // ������ �����並 �簳
				inProgress = false; // ó���� �÷��׸� ��
				
				Preference.putBoolean(CameraActivity.this, fileName, true);
				
				Intent intent = new Intent(CameraActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}

		}

	};

	/** �̹��� 90�� ȸ�� */
	private Bitmap imgRotate(Bitmap bmp){

		int width = bmp.getWidth(); 
		int height = bmp.getHeight(); 

		Matrix matrix = new Matrix(); 
		matrix.postRotate(90); 

		Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true); 
		bmp.recycle();

		return resizedBitmap;
	}

	/** �̹��� ���� */
	public void doSaveFile() {

		OutputStream out = null;
		
		String path = getFilesDir().getPath().toString();
		
		File saveFile = new File( path + "/" + fileName); // ������ ����Ǵ� ��� ����
		Log.i(TAG, "�̹��� ����: " + path + "/" + fileName);
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
