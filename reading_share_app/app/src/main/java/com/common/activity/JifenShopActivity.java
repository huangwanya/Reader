package com.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blueberry.activity.R;
import com.common.util.HttpUtil;
import com.common.util.MyApp;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONException;
import org.json.JSONObject;

public class JifenShopActivity extends Activity{
	private TextView shouye,faxian,haoyou,wo,top_back,fabu,logout,saoma,qiandao,username,tips,tips0,huiyi,baoxiao;
	private MyApp myApp;
	LinearLayout line1,line2,line2_,line3,line4,line5,line6,line7,line8;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) JifenShopActivity.this.getApplication();
		setView();
		initView();
		username.setText("欢迎登录,"+myApp.getLoginName());
		tips.setText("角色:"+myApp.getName());
//		tips0.setText("累计阅读分享活动活动天数:"+myApp.getHours());
		
		
	
	}

	public void setView() {
		setContentView(R.layout.activity_jifen);
		ViewUtils.inject(this);

	}
	@OnClick({ R.id.top_back })
	public void onclick(View view) {
		switch (view.getId()) {

		case R.id.top_back:
			finish();
			break;
		}
	}
	

	public void initView() {
		top_back =(TextView) findViewById(R.id.top_back);
		username =(TextView) findViewById(R.id.username);
		tips =(TextView) findViewById(R.id.tips);
		tips0 =(TextView) findViewById(R.id.tips0);



		line1=(LinearLayout) findViewById(R.id.line1);
		line2=(LinearLayout) findViewById(R.id.line2);
		line2_=(LinearLayout) findViewById(R.id.line2_);
		line3=(LinearLayout) findViewById(R.id.line3);
		line4=(LinearLayout) findViewById(R.id.line4);
		line5=(LinearLayout) findViewById(R.id.line5);
		line6=(LinearLayout) findViewById(R.id.line6);
		line7=(LinearLayout) findViewById(R.id.line7);
		line8=(LinearLayout) findViewById(R.id.line8);




		line1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(JifenShopActivity.this, "正在开发中", 0).show();


			}
		});
		line2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(JifenShopActivity.this, "正在开发中", 0).show();


			}
		});
		line2_.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(JifenShopActivity.this, "正在开发中", 0).show();


			}
		});
		line3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(myApp.getName().equals("管理员")){
					Intent intent = new Intent(JifenShopActivity.this,MyNews.class);
					startActivity(intent);
				}else{

					Toast.makeText(JifenShopActivity.this, "只有管理员具有项目管理权限", 0).show();
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
				Intent intent = new Intent(JifenShopActivity.this,Login.class);
				startActivity(intent);
			}
		});
		line5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(JifenShopActivity.this,TieziList.class);
				startActivity(intent);
			}
		});
		line6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(JifenShopActivity.this,MessageList.class);
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




			}
		});
		line8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			}
		});

		
/*		fabu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
					
					
					if(myApp.getName().equals("管理员")){
						Intent intent = new Intent(JifenShopActivity.this,MyHuodongList.class);
						startActivity(intent);
					}else{
						
							Toast.makeText(JifenShopActivity.this, "只有管理员具有阅读分享活动管理权限", 0).show();
					}
			
				
				
			}
		});*/

	}

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
