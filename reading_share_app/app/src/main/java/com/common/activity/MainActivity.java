/**
 * ProjectName:
 * PackageName:net.shopnc.android
 * FileNmae:MainActivity.java
 */
package com.common.activity;



import java.security.acl.LastOwnerException;

import org.json.JSONException;
import org.json.JSONObject;

import com.blueberry.activity.R;
import com.common.util.MyApp;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.Toast;
/**
 * Tab 底部菜单处理
 * @author KingKong·HE
 */
public class MainActivity extends TabActivity {
	/** tab标签名*/
	public final static String TAB_TAG_HOME = "home";
	public final static String TAB_TAG_TYPE = "type";
	public final static String TAB_TAG_MYSTORE = "mystore";
	public final static String TAB_TAG_CART = "cart";
	
	private TabHost tabHost;
	
	/** 启动每个操作项的Intent */
	private Intent homeIntent;
	private Intent typeIntent;
	private Intent mystoreIntent;
	private Intent cartIntent;
	
	/** 界面上的各个单选按钮 */
	private RadioButton btn_home;
	private RadioButton btn_type;
	private RadioButton btn_mystore;
	private RadioButton btn_cart;
	private RadioButton typeButton;
	private MyApp myApp;
	private int oldButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myApp = (MyApp) MainActivity.this.getApplication();
		myApp.addActivity(this);
		


		homeIntent = new Intent(this, ShetuanList.class);
		typeIntent = new Intent(this, MyFriends.class);
		mystoreIntent = new Intent(this, MeActivity.class);
		cartIntent = new Intent(this, TuijianList.class);

		tabHost = this.getTabHost();
		tabHost.addTab(tabHost.newTabSpec(TAB_TAG_HOME).setIndicator(TAB_TAG_HOME).setContent(homeIntent));
		tabHost.addTab(tabHost.newTabSpec(TAB_TAG_TYPE).setIndicator(TAB_TAG_TYPE).setContent(typeIntent));
		tabHost.addTab(tabHost.newTabSpec(TAB_TAG_MYSTORE).setIndicator(TAB_TAG_MYSTORE).setContent(mystoreIntent));
		tabHost.addTab(tabHost.newTabSpec(TAB_TAG_CART).setIndicator(TAB_TAG_CART).setContent(cartIntent));

		////////////////////// find View ////////////////////////////
		btn_home = (RadioButton)this.findViewById(R.id.main_tab_home);
		btn_type = (RadioButton)this.findViewById(R.id.main_tab_type);
		btn_mystore = (RadioButton)this.findViewById(R.id.main_tab_mystore);
		btn_cart = (RadioButton)this.findViewById(R.id.main_tab_cart);

		MyRadioButtonClickListener listener = new MyRadioButtonClickListener();
		btn_home.setOnClickListener(listener);
		btn_type.setOnClickListener(listener);
		btn_mystore.setOnClickListener(listener);
		btn_cart.setOnClickListener(listener);

		oldButton = R.id.main_tab_home;
		myApp.setTabHost(tabHost);
		myApp.setMyStoreButton(btn_mystore);
		myApp.setTypeButton(btn_type);
		myApp.setHomeButton(btn_home);
		myApp.setCartButton(btn_cart);
		
		
		
		
		
	}
	
	class MyRadioButtonClickListener implements View.OnClickListener{
		public void onClick(View v) {
			RadioButton btn = (RadioButton)v;
			switch(btn.getId()){
			case R.id.main_tab_home:
				oldButton = R.id.main_tab_home;
				tabHost.setCurrentTabByTag(TAB_TAG_HOME);
				break;
			case R.id.main_tab_type:
				oldButton = R.id.main_tab_type;
				tabHost.setCurrentTabByTag(TAB_TAG_TYPE);
				break;
			case R.id.main_tab_cart:
				oldButton = R.id.main_tab_cart;
					tabHost.setCurrentTabByTag(TAB_TAG_CART);
				break;
				case R.id.main_tab_mystore:
					oldButton = R.id.main_tab_mystore;
					tabHost.setCurrentTabByTag(TAB_TAG_MYSTORE);
				break;
			}
		}
	}
	
	
	

	
}
