package com.common.activity;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

public class AddDuanziComments extends Activity {
	@ViewInject(R.id.gamename)
	private EditText gamename;
	@ViewInject(R.id.gamecontext)
	private TextView gamecontext;
	@ViewInject(R.id.imageMyAvator)
	private ImageView imageMyAvator;
	private MyApp myApp;
	private String jsonString;
	private String id;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) AddDuanziComments.this.getApplication();
		setContentView(R.layout.add_duanzi_comments);
		id = getIntent().getStringExtra("id");
		ViewUtils.inject(this);
	}

	@OnClick({ R.id.add_new, R.id.top_back, R.id.imageMyAvator })
	public void onclick(View view) {
		switch (view.getId()) {
		case R.id.add_new:
			if (TextUtils.isEmpty(gamecontext.getText().toString().trim())) {
				Util.Toast(this, "请输入内容");
				return;
			}
			addXD(gamecontext.getText().toString().trim());
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
	public void addXD(final String content) {
		
		
		new Thread() {
			public void run() {
				try {
					
				
					String url = HttpUtil.URL_COMMENTSADD;
					String query ="";
					query+="comment="+content+"&";
					query+="luxianid="+id+"&";
					query+="userid="+myApp.getLoginKey();
					
					url+=query;


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

							try {
								JSONObject obj = new JSONObject(result);
								String arrlist = obj.optString("jsonString");
								// JSONObject obj = new JSONObject(json);
								if(arrlist.equals("1")){
									handler.sendEmptyMessage(0x126);
									
								}else{
									handler.sendEmptyMessage(0x127);
								
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}

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

			}
			
			if (msg.what == 0x126) {
				//
				Toast.makeText(AddDuanziComments.this, "恭喜，评论成功", Toast.LENGTH_SHORT).show();;
				AddDuanziComments.this.finish();
				
			}
			if (msg.what == 0x127) {
				//
				Toast.makeText(AddDuanziComments.this, "抱歉，评论失败", Toast.LENGTH_SHORT).show();;
				
			}
		};
	};

}
