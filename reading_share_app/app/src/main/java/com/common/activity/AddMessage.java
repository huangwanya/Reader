package com.common.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blueberry.activity.R;
import com.common.util.HttpUtil;
import com.common.util.MyApp;
import com.common.util.Util;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

public class AddMessage extends Activity {
	@ViewInject(R.id.gamecontext)
	private TextView gamecontext;
	@ViewInject(R.id.imageMyAvator)
	private ImageView imageMyAvator;
	private MyApp myApp;

	private String id;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) AddMessage.this.getApplication();
	/*	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());*/
		setContentView(R.layout.add_message);
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
	 *
	 */
	public void addXD(final String content) {
	
		
		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_MESSAGESADD;
					String query ="";
					query+="message.content="+content+"&";
					query+="message.username="+myApp.getLoginName()+"&";
					query+="message.userid="+myApp.getLoginKey();
					
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
					e.printStackTrace();
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
				Toast.makeText(AddMessage.this, "恭喜，发布成功", Toast.LENGTH_SHORT).show();;
				AddMessage.this.finish();
				
			}
			if (msg.what == 0x127) {
				//
				Toast.makeText(AddMessage.this, "抱歉，发布失败", Toast.LENGTH_SHORT).show();;
				
			}
		};
	};
}
