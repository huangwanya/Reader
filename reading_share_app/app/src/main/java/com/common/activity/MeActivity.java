package com.common.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blueberry.activity.R;
import com.bumptech.glide.Glide;
import com.common.bean.DuanziBean;
import com.common.util.CircleLayout;
import com.common.util.HttpUtil;
import com.common.util.MyApp;
import com.common.util.MyBackAsynaTask;
import com.common.util.Util;
import com.common.util.CircleLayout.OnItemClickListener;
import com.common.util.CircleLayout.OnItemSelectedListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.zxing.activity.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MeActivity extends Activity {
	private TextView shouye, faxian, haoyou, wo, fabu, logout, saoma, qiandao, username, tips, tips0, huiyi, baoxiao;
	private MyApp myApp;
	LinearLayout line1, line2, line2_, line3, line4, line5, line6, line7, line8, line9, line10;
	private String jsonString;
	private CircleImageView geren;

	@ViewInject(R.id.zan)
	private TextView zan;
	@ViewInject(R.id.logout_button)
	private Button logout_button;
	@ViewInject(R.id.shoucang1)
	private TextView shoucang1;
	@ViewInject(R.id.zijia1)
	private TextView zijia1;
	@ViewInject(R.id.fenxiang1)
	private TextView fenxiang1;
	@ViewInject(R.id.gonglue1)
	private TextView gonglue1;
	@ViewInject(R.id.baoming1)
	private TextView baoming1;
	@ViewInject(R.id.sixin1)
	private TextView sixin1;


	@ViewInject(R.id.visit_history)
	private TextView visit_history;

	@ViewInject(R.id.check_history)
	private TextView check_history;

	@ViewInject(R.id.black_list)
	private TextView black_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) MeActivity.this.getApplication();
		setView();
		initView();
		username.setText("欢迎登录," + myApp.getLoginName());


	}

	@OnClick(R.id.logout_button)
	public void logout_button(View view) {
		AlertDialog alert = new AlertDialog.Builder(MeActivity.this).create();
		alert.setTitle("提示");
		alert.setMessage("根据提示进行操作");
		alert.setButton(DialogInterface.BUTTON_POSITIVE, "退出登录",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						myApp.setIsCheckLogin(false);
						myApp.setLoginKey(null);
						myApp.setLoginName(null);
						Intent intent = new Intent(MeActivity.this, Login.class);
						startActivity(intent);
					}
				});

		alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						//tuihuoin(model.getGood_id()+"");
					}
				});

		alert.show();

	}

	@OnClick(R.id.shoucang1)
	public void shoucang1(View view) {
		Util.toIntent(MeActivity.this,MyFolderDuanziList.class);

	}


	@OnClick(R.id.zijia1)
	public void zijia1(View view) {
		Util.toIntent(MeActivity.this,MyHuodongList.class);

	}@OnClick(R.id.fenxiang1)
	public void fenxiang1(View view) {
		Util.toIntent(MeActivity.this,MyTieziList.class);

	}@OnClick(R.id.gonglue1)
	public void gonglue1(View view) {
		Util.toIntent(MeActivity.this,MyNews.class);

	}@OnClick(R.id.baoming1)
	public void baoming1(View view) {
		Util.toIntent(MeActivity.this,MyBaomingList.class);

	}
	@OnClick(R.id.sixin1)
	public void sixin1(View view) {
		Util.toIntent(MeActivity.this,MessageList.class);
	}
	@OnClick(R.id.black_list)
	public void black_list(View view) {
		Util.toIntent(MeActivity.this,MyBlackList.class);
	}
	@OnClick(R.id.visit_history)
	public void visit_history(View view) {
		Util.toIntent(MeActivity.this,HistoryVisitList.class);
	}
	@OnClick(R.id.check_history)
	public void check_history(View view) {
		Util.toIntent(MeActivity.this,HistoryCheckList.class);
	}
	public void setView() {
		setContentView(R.layout.activity_me);
		ViewUtils.inject(this);

	}



	public void initView() {
//		top_back = (TextView) findViewById(R.id.top_back);
		username = (TextView) findViewById(R.id.username);
		tips = (TextView) findViewById(R.id.tips);
		tips0 = (TextView) findViewById(R.id.tips0);
		geren = (CircleImageView) findViewById(R.id.geren);


		line1 = (LinearLayout) findViewById(R.id.line1);
		line2 = (LinearLayout) findViewById(R.id.line2);
		line2_ = (LinearLayout) findViewById(R.id.line2_);
		line3 = (LinearLayout) findViewById(R.id.line3);
		line4 = (LinearLayout) findViewById(R.id.line4);
		line5 = (LinearLayout) findViewById(R.id.line5);
		line6 = (LinearLayout) findViewById(R.id.line6);
		line7 = (LinearLayout) findViewById(R.id.line7);
		line8 = (LinearLayout) findViewById(R.id.line8);
		line9 = (LinearLayout) findViewById(R.id.line9);
		line10 = (LinearLayout) findViewById(R.id.line10);


		geren.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MeActivity.this, AddPhoto.class);
				startActivity(intent);


			}
		});
		line10.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MeActivity.this, MyBaomingList.class);
				startActivity(intent);


			}
		});
		line2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MeActivity.this, UserProfileActivity.class);
				startActivity(intent);


			}
		});
		line2_.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (myApp.getName().equals("管理员")) {
					Intent intent = new Intent(MeActivity.this, MyHuodongList.class);
					startActivity(intent);
				} else {

					Toast.makeText(MeActivity.this, "只有管理员具有阅读分享活动管理权限", Toast.LENGTH_SHORT).show();
				}


			}
		});
		line3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (myApp.getName().equals("管理员")) {
					Intent intent = new Intent(MeActivity.this, MyNews.class);
					startActivity(intent);
				} else {

					Toast.makeText(MeActivity.this, "只有管理员具有项目管理权限", Toast.LENGTH_SHORT).show();
				}
			}
		});
		line4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				myApp.setIsCheckLogin(false);
				myApp.setLoginKey(null);
				myApp.setLoginName(null);
				Intent intent = new Intent(MeActivity.this, Login.class);
				startActivity(intent);
			}
		});
		line5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MeActivity.this, TieziList.class);
				startActivity(intent);
			}
		});
		line6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MeActivity.this, MessageList.class);
				startActivity(intent);

//				Intent intent = new Intent(MeActivity.this,History.class);
//				startActivity(intent);
			}
		});
		line7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(MeActivity.this,Chongzhi.class);
//				intent.putExtra("money",money);
//				startActivity(intent);

				sign();


			}
		});
		line8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MeActivity.this, JifenShopActivity.class);
				startActivity(intent);
			}
		});
		line9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MeActivity.this, MyFolderDuanziList.class);
				startActivity(intent);
			}
		});

/*
		fabu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
					
					
					if(myApp.getName().equals("管理员")){
						Intent intent = new Intent(MeActivity.this,MyHuodongList.class);
						startActivity(intent);
					}else{
						
							Toast.makeText(MeActivity.this, "只有管理员具有阅读分享活动管理权限", 0).show();
					}
			
				
				
			}
		});*/

	}
	@OnClick({ R.id.add_new_xd, R.id.top_back, R.id.imageMyAvator })
	public void onclick(View view) {
		switch (view.getId()) {

			case R.id.top_back:
				Util.toIntent(MeActivity.this,MainActivity.class);
				break;
			case R.id.add_new_xd:
				Util.toIntent(MeActivity.this,UserProfileActivity.class);
				break;
		}
	}
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x121) {
				//
				Toast.makeText(MeActivity.this, "打卡成功",
						Toast.LENGTH_SHORT).show();
			}

			if (msg.what == 0x122) {
				//
				Toast.makeText(MeActivity.this, "打卡失败",
						Toast.LENGTH_SHORT).show();
			}
			if (msg.what == 0x123) {
				//
				Toast.makeText(MeActivity.this, "今天已打卡过了，每日打卡一次",
						Toast.LENGTH_SHORT).show();
			}
			if (msg.what == 0x128) {
				//
				Toast.makeText(MeActivity.this, "操作失败",
						Toast.LENGTH_SHORT).show();
			}
			if (msg.what == 0x125) {
				//
				JSONObject obj;
				try {
					obj = new JSONObject(jsonString);
					String arrlist = obj.optString("jsonString");
					JSONObject user = new JSONObject(arrlist);
					if(obj.length()> 0){
						String image_url = user.optString("image_url");

						if(image_url.length()>1){
							String imageurl_ = image_url.split(
									"\\\\")[1];
							String imageurl = HttpUtil.URL_BASEUPLOAD + imageurl_;


							Glide.with(MeActivity.this)
									.load(imageurl)
									.into(geren);
						}

						String count0,count1,count2,count_baoming,count_msg,count_folder,count_blacklist,count_visit,count_check;

						count0=user.optString("count0");
						count1=user.optString("count1");
						count2=user.optString("count2");
						count_baoming=user.optString("count_baoming");
						count_msg=user.optString("count_msg");
						count_folder=user.optString("count_folder");
						count_blacklist=user.optString("count_blacklist");
						count_visit=user.optString("count_visit");
						count_check=user.optString("count_check");


//						@ViewInject(R.id.shoucang1)
//						private TextView shoucang1;
//						@ViewInject(R.id.zijia1)
//						private TextView zijia1;
//						@ViewInject(R.id.fenxiang1)
//						private TextView fenxiang1;
//						@ViewInject(R.id.gonglue1)
//						private TextView gonglue1;
//						@ViewInject(R.id.baoming1)
//						private TextView baoming1;
//						@ViewInject(R.id.sixin1)
//						private TextView sixin1;
						shoucang1.setText("信息收藏("+count_folder+")");
						zijia1.setText("发起活动("+count2+")");
						fenxiang1.setText("发布分享("+count0+")");
						gonglue1.setText("发布消息("+count1+")");
						baoming1.setText("活动报名("+count_baoming+")");
						sixin1.setText("参与私聊("+count_msg+")");
						black_list.setText("黑名单("+count_blacklist+")");
						visit_history.setText("来访("+count_visit+")");
						check_history.setText("浏览("+count_check+")");



					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}

		;
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
	public void sign() {

		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_SIGN + "sign.userid=" + myApp.getLoginKey();


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);
					try {
						JSONObject obj = new JSONObject(result);
						String jsonStr = obj.optString("jsonString");
						if (jsonStr.equals("1")) {

							handler.sendEmptyMessage(0x121);
						} else if (jsonStr.equals("0")) {
							handler.sendEmptyMessage(0x122);
						} else if (jsonStr.equals("3")) {
							handler.sendEmptyMessage(0x123);
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();



/*	@Override
	public void onItemSelected(View view, int position, long id, String name) {
		selectedTextView.setText(name);
	}

	@Override
	public void onItemClick(View view, int position, long id, String name) {
		switch (position) {
		case 0:
			Util.toIntent(this, InformationAvtivity.class);
			break;
		case 1:
			Util.toIntent(this, ExchangeList.class);
			break;
		case 2:
			Util.toIntent(this, GamePCList.class);
			break;
		case 3:
			Util.toIntent(this, GameXDList.class);
			break;
		case 4:
			Util.toIntent(this, PictureList.class);
			break;
		case 5:
			Util.toIntent(this, Foundingfriends.class);
			break;
		}
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Util.setUsersBean(null);
		return super.onKeyDown(keyCode, event);
	}*/

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		loaddata();

	}

}
