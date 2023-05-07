package com.common.activity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
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
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.blueberry.activity.R;
import com.common.bean.ShetuanBean;
import com.common.util.BasicFuns;
import com.common.util.FormFile;
import com.common.util.HttpUtil;
import com.common.util.IAsynTask;
import com.common.util.MyApp;
import com.common.util.SendGETRequest;
import com.common.util.SocketHttpRequester;
import com.common.util.Util;
import com.common.util.Web;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import de.hdodenhof.circleimageview.CircleImageView;

@SuppressLint("NewApi") public class AddNews extends Activity {
	@ViewInject(R.id.gamename)
	private EditText gamename;
	@ViewInject(R.id.gamecontext)
	private TextView gamecontext;
	@ViewInject(R.id.imageMyAvator)
	private CircleImageView imageMyAvator;
	private MyApp myApp;
	private String jsonString;
	private String path;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) AddNews.this.getApplication();
		/*
		 * StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		 * .detectDiskReads().detectDiskWrites().detectNetwork()
		 * .penaltyLog().build()); StrictMode.setVmPolicy(new
		 * StrictMode.VmPolicy.Builder()
		 * .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
		 * .penaltyLog().penaltyDeath().build());
		 */
		setContentView(R.layout.add_news);
		ViewUtils.inject(this);
		
	}
	@OnClick({ R.id.add_new_xd, R.id.top_back, R.id.imageMyAvator })
	public void onclick(View view) {
		switch (view.getId()) {
		case R.id.add_new_xd:
			if (TextUtils.isEmpty(gamename.getText().toString().trim())) {
//				Util.Toast(this, "请输入标题");
				gamename.setError("不能为空");
				return;
			}
			if (TextUtils.isEmpty(gamecontext.getText().toString().trim())) {
//				Util.Toast(this, "请输入内容");
				gamecontext.setError("不能为空");
				return;
			}
			addXD();
			break;

		case R.id.top_back:
			finish();
			break;
		case R.id.imageMyAvator:
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			/* 开启Pictures画面Type设定为image */
			intent.setType("image/*");
			/* 使用Intent.ACTION_GET_CONTENT这个Action */
			intent.setAction(Intent.ACTION_GET_CONTENT);
			/* 取得相片后返回本画面 */
			startActivityForResult(intent, 1);
			break;
		}
	}

	/**
	 * 添加新的游戏心得
	 */
	public void addXD() {
//
//		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//				.permitAll().build();
//		StrictMode.setThreadPolicy(policy);
		final String title = gamename.getText().toString();
		final String content = gamecontext.getText().toString();
		String author = myApp.getLoginName();
		
		
		
		Intent intent = new Intent(AddNews.this, MainActivity.class);
		startActivity(intent);
		finish();
		final String actionUrl = HttpUtil.URL_BIO_ADD_;
		new Thread() {
			public void run() {
				try {

					try {
						// 请求普通信息
						Map<String, String> params = new HashMap<String, String>();
						// params.put("filename", "张三");
						params.put("biotech.title", title);
			    		params.put("biotech.content", content);
			    		params.put("biotech.author", myApp.getLoginName());
			    		params.put("biotech.type2", "通知公告");
			    		params.put("biotech.type", 1+"");
			    		params.put("biotech.type_id", "");

						File imageFile = new File(path);
						FormFile formfile = new FormFile(imageFile.getName(),
								imageFile, "biotech.file",
								"application/octet-stream");
						SocketHttpRequester.post(actionUrl, params, formfile);

					} catch (Exception e) {
						Toast.makeText(AddNews.this, "上传失败!",
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

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x125) {
				//

				Toast.makeText(AddNews.this, "发布成功!", Toast.LENGTH_LONG)
						.show();
				finish();
			}
			
		};
	};
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Uri uri = data.getData();
			Log.e("uri", uri.toString());
			Cursor cursor=null;
			ContentResolver cr=null;
			
			 InputStream inputstream=null;
			try {
				String[] pojo = { MediaStore.Images.Media.DATA };
				 cursor = managedQuery(uri, pojo, null, null, null);
				 cr = this.getContentResolver();
				 inputstream=cr.openInputStream(uri);
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

						
						BitmapFactory.Options options = new BitmapFactory.Options();  
						  //避免出现内存溢出的情况，进行相应的属性设置。
				        options.inPreferredConfig = Bitmap.Config.RGB_565;
				        options.inDither = true;
						options.inSampleSize = 2;//图片宽高都为原来的二分之一，即图片为原来的四分之一    
						Bitmap bitmap =BitmapFactory.decodeStream(inputstream, null, options); 
						imageMyAvator.setImageBitmap(bitmap);  
						
					} else {
						// alert();
						
					}
				} else {
					// alert();
				}

			} catch (Exception e) {

			}finally{
			    if (cursor != null) {
			    	cursor.close();
			    }
			    if (inputstream != null) {
			    	try {
						inputstream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}
