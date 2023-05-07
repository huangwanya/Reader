package com.common.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blueberry.activity.R;
import com.common.bean.HistoryBean;
import com.common.bean.SignBean;
import com.common.util.HttpUtil;
import com.common.util.MyApp;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryVisitList extends Activity {
	@ViewInject(R.id.listView1)
	private ListView listView1;
	private List<HistoryBean> basemarkBeans = new ArrayList<HistoryBean>();
	@ViewInject(R.id.add_new_xd)
	private TextView add_new_xd;
	private MyApp myApp;
	private GameAdapter adapter;
	private String bioid;
	private String jsonString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) HistoryVisitList.this.getApplication();
		setContentView(R.layout.history_list);
		ViewUtils.inject(this);
		add_new_xd.setVisibility(View.INVISIBLE);
		adapter = new GameAdapter();
		listView1.setAdapter(adapter);
		getGameXD();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@OnClick(R.id.top_back)
	public void back(View view) {
		finish();
	}
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x125) {
				//
				ArrayList<HistoryBean> goods_list = HistoryBean
						.newInstanceList(jsonString);
				basemarkBeans.addAll(goods_list);
				adapter.notifyDataSetChanged();
			}
			
			if (msg.what == 0x126) {
				basemarkBeans.addAll(new ArrayList<HistoryBean>());
				adapter.notifyDataSetChanged();
				//
			}
			if (msg.what == 0x127) {
				//
			}
			if (msg.what == 0x128) {
			}
		};
	};
	public void getGameXD() {
		
		
		new Thread() {
			public void run() {
				try {
					
					basemarkBeans =new ArrayList<HistoryBean>();
					String url = HttpUtil.URL_VISITLIST+myApp.getLoginName();


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

							try {
								JSONObject obj = new JSONObject(result);
								String arrlist = obj.optString("jsonString");
								// JSONObject obj = new JSONObject(json);
								if (arrlist != "" && !arrlist.equals("arrlist")
										&& arrlist != null && !arrlist.equals("[]")) {
									jsonString =arrlist;
									
									
									
									handler.sendEmptyMessage(0x125);
								} else {
									handler.sendEmptyMessage(0x126);
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

	private class GameAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<HistoryBean> duanziList;

		private GameAdapter() {
			inflater = LayoutInflater.from(HistoryVisitList.this);
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
						.inflate(R.layout.history_list_item, null);
				holder = new Holder();
				holder.image_view = (ImageView) convertView
						.findViewById(R.id.image_view);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				holder.phone = (TextView) convertView.findViewById(R.id.phone);
				holder.qq = (TextView) convertView.findViewById(R.id.qq);
				holder.address = (TextView) convertView.findViewById(R.id.address);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.name
					.setText("来访人:" + basemarkBeans.get(position).getUsername()+"\n访问内容："+basemarkBeans.get(position).getTitle());
			holder.phone
			.setText("来访时间:" + basemarkBeans.get(position).getUpdatedate());
		/*	holder.qq
			.setText("QQ:" + basemarkBeans.get(position).getDanwei());
			holder.address
			.setText("地址:" + basemarkBeans.get(position).getScore());*/

			return convertView;
		}

		public ArrayList<HistoryBean> getDuanziList() {
			return duanziList;
		}

		public void setDuanziList(ArrayList<HistoryBean> duanziList) {
			this.duanziList = duanziList;
		}

	}

	private class Holder {
		ImageView image_view;
		TextView name;
		TextView phone;
		TextView qq;
		TextView address;
	}
}
