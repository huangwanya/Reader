package com.common.activity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.blueberry.activity.R;
import com.common.bean.ShetuanBean;
import com.common.util.HttpUtil;
import com.common.util.IAsynTask;
import com.common.util.SendGETRequest;
import com.common.util.Util;
import com.common.util.Web;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class UserRegisterActivity extends Activity {
	@ViewInject(R.id.username)
	private EditText username;
	@ViewInject(R.id.userpasswords)
	private EditText userpasswords;
	@ViewInject(R.id.editText1)
	private EditText editText1;
	@ViewInject(R.id.editQQ)
	private EditText editQQ;
	@ViewInject(R.id.editPhone)
	private EditText editPhone;
	@ViewInject(R.id.editName)
	private Spinner editName;
	@ViewInject(R.id.editage)
	private Spinner editage;
	
	private String jsonString;
	
	@ViewInject(R.id.editAddress)
	private Spinner editAddress;
	
	  private List<ShetuanBean> basemarkBeans = new ArrayList<ShetuanBean>();
			private GameAdapter adapter;
	
	private ProgressDialog dialog;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userregister);
		
/*		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		.detectDiskReads().detectDiskWrites().detectNetwork()
		.penaltyLog().build());
StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
		.penaltyLog().penaltyDeath().build());*/
		ViewUtils.inject(this);
		
		adapter = new GameAdapter();
		editAddress.setAdapter(adapter);
		 getGameXD();
		username.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					// 此处为得到焦点时的处理内容
				} else {
					if (TextUtils.isEmpty(username.getText().toString().trim())) {
						Toast.makeText(UserRegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT)
								.show();
					} else if (username.getText().toString().trim().length() < 4
							|| username.getText().toString().trim().length() > 20) {
						Toast.makeText(UserRegisterActivity.this,
								"用户名的长度应在4-20个字符之间", Toast.LENGTH_SHORT).show();
					} else {
						Pattern pattern = Pattern.compile("[0-9]*");
						Matcher matcher = pattern.matcher(username.getText()
								.toString().trim());
						Pattern pattern2 = Pattern.compile("\\W+");
						Matcher matcher2 = pattern2.matcher(username.getText()
								.toString().trim());
						if (matcher.matches()) {
							Toast.makeText(UserRegisterActivity.this,
									"用户名不能全部是数字", Toast.LENGTH_SHORT).show();
							return;
						}
						if (matcher2.find()) {
							Toast.makeText(UserRegisterActivity.this,
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
	public void getGameXD() {
		
		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_GOODSTYPELIST_;


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

							try {
								JSONObject obj = new JSONObject(result);
								String arrlist = obj.optString("jsonString");
								// JSONObject obj = new JSONObject(json);
								if (arrlist != "" && !arrlist.equals("arrlist")
										&& arrlist != null && !arrlist.equals("[]")) {
									jsonString =arrlist;
									
									
									
									handler.sendEmptyMessage(0x126);
								} else {
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
			
			if (msg.what == 0x126) {
				//
				basemarkBeans = new ArrayList<ShetuanBean>();
				ArrayList<ShetuanBean> goods_list = ShetuanBean
						.newInstanceList(jsonString);
				basemarkBeans.addAll(goods_list);
				editAddress.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
			}
			if (msg.what == 0x127) {
				//
				basemarkBeans.addAll(new ArrayList<ShetuanBean>());
				editAddress.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
			}
			if (msg.what == 0x128) {
				Toast.makeText(UserRegisterActivity.this, "恭喜，注册成功", Toast.LENGTH_SHORT).show();;
            	Intent intent = new Intent(UserRegisterActivity.this,Login.class);
            	UserRegisterActivity.this.startActivity(intent);
			}
			if (msg.what == 0x129) {
				Toast.makeText(UserRegisterActivity.this, "抱歉，注册失败", Toast.LENGTH_SHORT).show();;

			}
		};
	};
	@OnClick({ R.id.add_new_xd, R.id.top_back })
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
			String phone = editPhone.getText().toString().trim();
//			String name = editName.getSelectedItem().toString().trim();
			String age = editage.getSelectedItem().toString().trim();
			String address = ((ShetuanBean)editAddress.getSelectedItem()).getName().toString().trim();
			SendData(user_name, password, qq, phone,"",address,age);
			break;

		case R.id.top_back:
			finish();
			break;
		}
	}
	public void SendData(final String username , final String password ,final String qq,final String phone,final String name,final String address,final String age){
		
		
		new Thread() {
			public void run() {
				try {
					String url = HttpUtil.URL_REGISTER;
					String query ="";
					query+="user.username="+username+"&";
					query+="user.password="+password+"&";
					query+="user.qqnum="+qq+"&";
					query+="user.name="+name+"&";
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
									
									
									if(arrlist.equals("1")){
										
										handler.sendEmptyMessage(0x128);
									
									}else{
										
										handler.sendEmptyMessage(0x129);
									}
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
						Util.Toast(UserRegisterActivity.this, "用户名已经存在。");
						username.setText("");
					}
				} else {
					Util.Toast(UserRegisterActivity.this, "网络连接错误");
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
	
	
	private class GameAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<ShetuanBean> duanziList;

		private GameAdapter() {
			inflater = LayoutInflater.from(UserRegisterActivity.this);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return basemarkBeans.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return basemarkBeans.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Holder holder;
			if (convertView == null) {
				convertView = inflater
						.inflate(R.layout.type_list_item_, null);
				holder = new Holder();
				holder.title = (TextView) convertView.findViewById(R.id.title);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			
			holder.title.setText(basemarkBeans.get(position).getName());
			
			
			return convertView;
		}

		public ArrayList<ShetuanBean> getDuanziList() {
			return duanziList;
		}

		public void setDuanziList(ArrayList<ShetuanBean> duanziList) {
			this.duanziList = duanziList;
		}


	}
	
	private class Holder {
		TextView title;
	}
}
