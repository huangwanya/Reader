package com.common.activity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.blueberry.activity.R;
import com.common.bean.ShetuanBean;
import com.common.bean.TieziBean;
import com.common.util.HttpUtil;
import com.common.util.IAsynTask;
import com.common.util.MyApp;
import com.common.util.SendGETRequest;
import com.common.util.Util;
import com.common.util.Web;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class UserProfileActivity extends Activity {
	@ViewInject(R.id.username)
	private EditText username;
	@ViewInject(R.id.userpasswords)
	private EditText userpasswords;
	@ViewInject(R.id.editText1)
	private EditText editText1;
	@ViewInject(R.id.editQQ)
	private EditText editQQ;
	@ViewInject(R.id.editage)
	private Spinner editage;
	@ViewInject(R.id.editPhone)
	private EditText editPhone;
	@ViewInject(R.id.editName123)
	private Spinner edit_role;
	@ViewInject(R.id.top_back)
	private TextView top_back;
	private ProgressDialog dialog;
	private MyApp myApp;
	private String name,address,status,jsonString,image_url;

	@SuppressLint("NewApi")
	@Override  
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) UserProfileActivity.this.getApplication();
		setContentView(R.layout.userprofile);
		ViewUtils.inject(this);
/*		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		.detectDiskReads().detectDiskWrites().detectNetwork()
		.penaltyLog().build());
StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
		.penaltyLog().penaltyDeath().build());*/
		
		loaddata();
		username.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					// 此处为得到焦点时的处理内容
				} else {
					if (TextUtils.isEmpty(username.getText().toString().trim())) {
						Toast.makeText(UserProfileActivity.this, "用户名不能为空", Toast.LENGTH_SHORT)
								.show();
					} else if (username.getText().toString().trim().length() < 4
							|| username.getText().toString().trim().length() > 20) {
						Toast.makeText(UserProfileActivity.this,
								"用户名的长度应在4-20个字符之间", Toast.LENGTH_SHORT).show();
					} else {
						Pattern pattern = Pattern.compile("[0-9]*");
						Matcher matcher = pattern.matcher(username.getText()
								.toString().trim());
						Pattern pattern2 = Pattern.compile("\\W+");
						Matcher matcher2 = pattern2.matcher(username.getText()
								.toString().trim());
						if (matcher.matches()) {
							Toast.makeText(UserProfileActivity.this,
									"用户名不能全部是数字", Toast.LENGTH_SHORT).show();
							return;
						}
						if (matcher2.find()) {
							Toast.makeText(UserProfileActivity.this,
									"用户名不能含有特殊字符，只能是由汉字、数字、英文字母以及下划线组成", Toast.LENGTH_SHORT)
									.show();
							return;
						}
//						checkUser(username.getText().toString().trim());
					}
				}
			}
		});
		
		
	}

	@OnClick({ R.id.add_new_xd, R.id.userlogin , R.id.top_back })
	public void onclick(View view) {
		switch (view.getId()) {
		case R.id.add_new_xd:
			if (TextUtils.isEmpty(username.getText().toString().trim())) {
				Util.showToast(this, "输入用户名！");
				return;
			}
			if (TextUtils.isEmpty(userpasswords.getText().toString().trim())) {
				Util.showToast(this, "输入密码！");
				return;
			}
			if (TextUtils.isEmpty(editText1.getText().toString().trim())) {
				Util.showToast(this, "输入确认密码！");
				return;
			}
			if (!userpasswords.getText().toString().trim()
					.equals(editText1.getText().toString().trim())) {
				Util.showToast(this, "输入确认密码！");
				return;
			}
			String user_name = username.getText().toString().trim();
			String password = userpasswords.getText().toString().trim();
			String qq = editQQ.getText().toString().trim();
			String age = editage.getSelectedItem().toString().trim();
			String phone = editPhone.getText().toString().trim();
			String name = edit_role.getSelectedItem().toString();
			SendData(user_name, password, qq, phone,"",age);
			break;

		case R.id.userlogin:
			finish();
			Intent intent = new Intent(this, Login.class);
			startActivity(intent);
			break;
		case R.id.top_back:
			finish();
			break;
		}
	}
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x125) {
				//
				
				JSONObject obj;
				try {
					obj = new JSONObject(jsonString);
					String arrlist = obj.optString("jsonString");
					JSONObject user = new JSONObject(arrlist);
					if(obj.length()> 0){
						String username1 = user.optString("username");
						String password1 = user.optString("password");
						String qqnum1= user.optString("qqnum");
						String phone1 = user.optString("phone");
						String realname1 = user.optString("name");
						address = user.optString("address");
						status = user.optString("status");
						String age = user.optString("age");
						image_url = user.optString("image_url");

						username.setText(username1);
//						editName.setText(realname1);
						switch (age){
							case "1":
								editage.setSelection(0);
								break;
							case "2":
								editage.setSelection(1);
								break;
							case "3":
								editage.setSelection(2);
								break;
							case "4":
								editage.setSelection(3);
								break;
							default:
								editage.setSelection(0);
								break;
						}
//						editage.setText(age);
						userpasswords.setText(password1);
						editText1.setText(password1);
						editQQ.setText(qqnum1);
						editPhone.setText(phone1);
//						editName.setText(realname1);
						name=realname1;
						
//						if(realname1.equals("管理员")){
//							edit_role.setSelection(0);
//						}else if(realname1.equals("队长")){
//							edit_role.setSelection(1);
//						}else{
//							edit_role.setSelection(2);
//						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			if (msg.what == 0x126) {
		
				//
			}
			if (msg.what == 0x127) {
				//

				if(jsonString.equals("1")){
					Toast.makeText(UserProfileActivity.this, "恭喜，信息保存成功", Toast.LENGTH_SHORT).show();;
	            	finish();
				}else{
					Toast.makeText(UserProfileActivity.this, "抱歉，操作失败", Toast.LENGTH_SHORT).show();;
				}
			}
			if (msg.what == 0x128) {
			}
		};
	};
	public void loaddata(){
		new Thread() {
			public void run() {
				try {
					String url = HttpUtil.URL_LOAD;
					String query ="";
					query+="user.id="+myApp.getLoginKey();
					url+=query;


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

						   jsonString=result;
							
							handler.sendEmptyMessage(0x125);

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
		
		
		
	}
	public void SendData(final String username , final String password ,final String qq,final String phone,String address__,final String age){
		
		
		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_REGISTER;
					String query ="";
					query+="user.id="+myApp.getLoginKey()+"&";
					query+="user.username="+username+"&";
					query+="user.password="+password+"&";
					query+="user.qqnum="+qq+"&";
					query+="user.name="+name+"&";
					query+="user.status="+status+"&";
					query+="user.image_url="+image_url+"&";
					query+="user.address="+address+"&";
					query+="user.age="+age+"&";
					query+="user.phone="+phone;
					url+=query;


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

							try {
								JSONObject obj = new JSONObject(result);
								String arrlist = obj.optString("jsonString");
								// JSONObject obj = new JSONObject(json);
								if (arrlist != "" && !arrlist.equals("arrlist")
										&& arrlist != null && !arrlist.equals("[]")) {
									jsonString =arrlist;
									
									
									
									handler.sendEmptyMessage(0x127);
								} else {
									
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

	private void checkUser(final String login) {
		Util.asynTask(this, "验证用户名", new IAsynTask() {

			@Override
			public void updateUI(Serializable runData) {
				// TODO Auto-generated method stub
				if (runData != null) {
					Map<String, String> map = (Map<String, String>) runData;
					if (map.get("type").equals("success")) {

					} else {
						Util.Toast(UserProfileActivity.this, "用户名已经存在。");
						username.setText("");
					}
				} else {
					Util.Toast(UserProfileActivity.this, "网络连接错误");
				}
			}

			@Override
			public Serializable run() {
				Map<String, String> map = null;
				try {
					map = SendGETRequest.sendGETRequest(Web.checkUserLogin,
							"?users.userlogin=" + login);
				} catch (Exception e) {
					// TODO: handle exception
				}
				// TODO Auto-generated method stub
				return (Serializable) map;
			}
		});
	}
}
