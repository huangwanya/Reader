package com.common.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.blueberry.activity.R;
import com.common.util.Util;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class Loading extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		ViewUtils.inject(this);
	}
	@OnClick({R.id.denglu,R.id.kankan})
	public void onclick(View view){
		switch (view.getId()) {
		case R.id.denglu:
			Util.toIntent(this, Login.class);
			finish();
			break;
			
		case R.id.kankan:
			Util.toIntent(this, MainActivity.class);
			finish();
			break;
		}
	}
}
