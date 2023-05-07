package com.common.activity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.blueberry.activity.R;
import com.common.bean.ShetuanBean;
import com.common.util.BitmapUtil;
import com.common.util.FileUtil;
import com.common.util.FormFile;
import com.common.util.HttpUtil;
import com.common.util.MyApp;
import com.common.util.SocketHttpRequester;
import com.common.util.Util;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddPhoto extends Activity    {
	@ViewInject(R.id.imageMyAvator)
	private CircleImageView imageMyAvator;
	private String jsonString;
	private MyApp myApp;
	// 创建一个以当前时间为名称的文件
	public static File tempFile = new File(
			Environment.getExternalStorageDirectory(),
			FileUtil.getPhotoFileName());


	private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
	private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 3;// 结果

	Bitmap mBitmap = null;
	

	private String path;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) AddPhoto.this.getApplication();
/*		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());*/
		setContentView(R.layout.add_photo_xd);
		ViewUtils.inject(this);

//    	adapter = new GameAdapter();
//		edittype.setAdapter(adapter);
//		 getGameXD();
	}
	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x125) {
				//

				Toast.makeText(AddPhoto.this, "上传成功!", Toast.LENGTH_LONG)
						.show();
				Intent intent = new Intent(AddPhoto.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
			
			if (msg.what == 0x126) {
				//

			}
			if (msg.what == 0x127) {
				//

			}
		};
	};
	@OnClick({ R.id.add_new_xd, R.id.top_back, R.id.imageMyAvator })
	public void onclick(View view) {
		switch (view.getId()) {
		case R.id.add_new_xd:
		/*	if (TextUtils.isEmpty(hours.getText().toString().trim())) {
				Util.Toast(this, "不能为空");
				return;
			}*/
			addXD();
			break;

		case R.id.top_back:
			finish();
			break;
		case R.id.imageMyAvator:
			showDialog();
			break;
		}
	}
	
	
	// 提示对话框方法
	private void showDialog() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		/* 开启Pictures画面Type设定为image */
		intent.setType("image/*");
		/* 使用Intent.ACTION_GET_CONTENT这个Action */
		intent.setAction(Intent.ACTION_GET_CONTENT);
		/* 取得相片后返回本画面 */
		startActivityForResult(intent, 1);
			
	}
	/**
	 * 添加新的游戏心得
	 */
	public void addXD() {

		

		final String actionUrl = HttpUtil.URL_PHOTO_ADD;
		new Thread() {
			public void run() {
				try {

					try {
						// 请求普通信息
						Map<String, String> params = new HashMap<String, String>();
			    		params.put("user.id", myApp.getLoginKey());
						File imageFile = new File(path);
						FormFile formfile = new FormFile(imageFile.getName(),
								imageFile, "user.file",
								"application/octet-stream");
						SocketHttpRequester.post(actionUrl, params, formfile);

					} catch (Exception e) {
						Toast.makeText(AddPhoto.this, "上传失败!",
								Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}

					handler.sendEmptyMessage(0x125);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Uri uri = data.getData();
			Log.e("uri", uri.toString());
			// path =uri.toString();
			/*
			 * ContentResolver cr = this.getContentResolver(); try { Bitmap
			 * bitmap = BitmapFactory.decodeStream(cr .openInputStream(uri));
			 * 将Bitmap设定到ImageView imageMyAvator.setImageBitmap(bitmap); } catch
			 * (FileNotFoundException e) { Log.e("Exception", e.getMessage(),
			 * e); }
			 */

			try {
				String[] pojo = { MediaStore.Images.Media.DATA };
				Cursor cursor = managedQuery(uri, pojo, null, null, null);
				ContentResolver cr = this.getContentResolver();
				if (cursor != null) {

					int colunm_index = cursor
							.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToFirst();
					String path1 = cursor.getString(colunm_index);
					/***
					 * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
					 * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
					 */
					if (path1.endsWith("jpg") || path1.endsWith("png")|| path1.endsWith("jpeg")) {
						path = path1;
						Bitmap bitmap = BitmapFactory.decodeStream(cr
								.openInputStream(uri));
						imageMyAvator.setImageBitmap(bitmap);
					} else {
						//alert();
					}
				} else {
					//alert();
					String path1 = uri.getPath();
					/***
					 * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
					 * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
					 */
					if (path1.endsWith("jpg") || path1.endsWith("png")|| path1.endsWith("jpeg")) {
						path = path1;
						Bitmap bitmap = BitmapFactory.decodeStream(cr
								.openInputStream(uri));
						imageMyAvator.setImageBitmap(bitmap);
					}
				}

			} catch (Exception e) {

			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	
	// 将进行剪裁后的图片显示到UI界面上
	private void setPicToView(Intent picdata) {
		Bundle bundle = picdata.getExtras();
		if (bundle != null) {
			Bitmap photo = bundle.getParcelable("data");
			mBitmap = photo;
			String uriString = System.currentTimeMillis() + "";
			BitmapUtil.saveBitmapFile(photo, uriString);
			imageMyAvator.setImageBitmap(photo);
			path = "/sdcard/pic/" + uriString + ".jpg";

		}
	}
	
	
}
