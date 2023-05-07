package com.common.activity;

import java.io.IOException;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blueberry.activity.R;
import com.common.bean.LoginBean;
import com.common.util.CodeUtils;
import com.common.util.HttpUtil;
import com.common.util.MyApp;
import com.common.util.Util;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class Login extends Activity {
	@ViewInject(R.id.name)
	private EditText name;
	@ViewInject(R.id.pwd)
	private EditText pwd;
	private String state;
	private MyApp myApp;
	@ViewInject(R.id.checkboxMyAuto)
	private CheckBox checkboxMyAuto;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		myApp = (MyApp) Login.this.getApplication();
		ViewUtils.inject(this);
//		SharedPreferences sp = getSharedPreferences("user",
//				Context.MODE_PRIVATE);
//		name.setText(sp.getString("username", null));
//		pwd.setText(sp.getString("password", null));

		if(myApp.isIsCheckLogin()){
			//startActivity(new Intent(Login.this,MainActivity.class));
			name.setText(myApp.getLoginName());
			pwd.setText(myApp.getLoginPwd());
			checkboxMyAuto.setChecked(true);
		}

		checkboxMyAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
//				Toast.makeText(Login.this, arg1+"", 1).show();
				myApp.setIsCheckLogin(arg1);

			}
		});
	}

	@OnClick({  R.id.loginButton, R.id.tozhuce })
	public void onclick(View view) {
		switch (view.getId()) {
		case R.id.tozhuce:
			Util.toIntent(this, UserRegisterActivity.class);
			break;
		case R.id.loginButton:
			if (TextUtils.isEmpty(name.getText().toString().trim())) {
//				Util.Toast(this, "请输入用户名");
				name.setError("请输入用户名");
				return;
			}
			if (TextUtils.isEmpty(pwd.getText().toString().trim())) {
//				Util.Toast(this, "请输入登录密码");
				pwd.setError("请输入登录密码");
				return;
			}

			login(name.getText().toString().trim(), pwd.getText().toString()
					.trim());
			break;
		}
	}

	public void login(final String loginName, final String loginPassword) {

		
		
		new Thread() {
			public void run() {
				try {
					
				
					String url = HttpUtil.URL_LOGIN;
					String query = "";
					query += "user.username=" + loginName + "&";
					query += "user.password=" + loginPassword;

					url += query;


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);


							LoginBean login = LoginBean.newInstanceList(result);
							if (login != null) {
								myApp.setLoginKey(login.getKey());
								myApp.setLoginName(login.getUsername());
								myApp.setLoginPwd(login.getPassword());
								myApp.setName(login.getName());
								myApp.setHours(login.getHours());
								myApp.setAddress(login.getAddress());
								myApp.setShetuanid(login.getShetuan_id());
								
								if(login.getStatus().equals("0")){
									handler.sendEmptyMessage(0x125);
							;
								}else{
									
									handler.sendEmptyMessage(0x126);
								}
							
						
							} else {
								handler.sendEmptyMessage(0x127);
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
				Toast.makeText(Login.this, "未通过审核，请联系管理员", Toast.LENGTH_SHORT)
				.show();
			}
			
			if (msg.what == 0x126) {
				//
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
						InputMethodManager.HIDE_NOT_ALWAYS);
				Intent intent = new Intent(Login.this, MainActivity.class);
				Login.this.startActivity(intent);
				
			}
			if (msg.what == 0x127) {
				//
				Toast.makeText(Login.this, "用户名或者密码错误", Toast.LENGTH_SHORT)
				.show();
		;
				
			}
		};
	};
}
