package com.common.activity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.blueberry.activity.R;
import com.bumptech.glide.Glide;
import com.common.bean.BasemarkBean;
import com.common.bean.DuanziBean;
import com.common.util.HttpUtil;
import com.common.util.IAsynTask;
import com.common.util.MyApp;
import com.common.util.MyBackAsynaTask;
import com.common.util.SendGETRequest;
import com.common.util.Util;
import com.common.util.Web;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyFolderDuanziList extends Activity {
	@ViewInject(R.id.listView1)
	private ListView listView1;
	private List<DuanziBean> basemarkBeans = new ArrayList<DuanziBean>();
	@ViewInject(R.id.add_new_xd)
	private TextView add_new_xd;
	private MyApp myApp;
	private GameAdapter adapter;
	private String jsonString;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) MyFolderDuanziList.this.getApplication();
		setContentView(R.layout.myfolderduanzilist);
		ViewUtils.inject(this);
		if (TextUtils.isEmpty(myApp.getLoginName())) {
			add_new_xd.setVisibility(View.INVISIBLE);
		}
/*		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		.detectDiskReads().detectDiskWrites().detectNetwork()
		.penaltyLog().build());
StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
		.penaltyLog().penaltyDeath().build());*/
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

	@OnClick(R.id.add_new_xd)
	public void addNew(View view) {
		Util.toIntent(MyFolderDuanziList.this, AddHuodong.class);
	}
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x125) {
				//
				ArrayList<DuanziBean> goods_list = DuanziBean
						.newInstanceList(jsonString);
				basemarkBeans.addAll(goods_list);
				adapter.notifyDataSetChanged();
			}
			
			if (msg.what == 0x126) {
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
					
					basemarkBeans =new ArrayList<DuanziBean>();
					String url = HttpUtil.URL_DUANZILISTUSERFOLDER
							+ myApp.getLoginKey();


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
		private ArrayList<DuanziBean> duanziList;

		private GameAdapter() {
			inflater = LayoutInflater.from(MyFolderDuanziList.this);
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

		@SuppressLint("ResourceAsColor") @Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Holder holder;
			if (convertView == null) {
				convertView = inflater
						.inflate(R.layout.game_xd_list_item, null);
				holder = new Holder();
				holder.image_view = (CircleImageView) convertView
						.findViewById(R.id.image_view);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.status = (TextView) convertView.findViewById(R.id.status);
				holder.time = (TextView) convertView.findViewById(R.id.times);
				holder.username = (TextView) convertView
						.findViewById(R.id.username);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
		
			if( basemarkBeans.get(position).getImage_url().length()>0){
				String imagename = basemarkBeans.get(position).getImage_url()
						.split("\\\\")[1];

				Glide.with(MyFolderDuanziList.this)
						.load(HttpUtil.URL_BASEUPLOAD + imagename)
						.into(holder.image_view);
				holder.time.setText("发布时间:"+basemarkBeans.get(position).getUpdatetime());
				holder.username.setText("发布人:"+basemarkBeans.get(position).getAuthor());
				if(basemarkBeans.get(position).getType().equals("2")){
					holder.title.setText(basemarkBeans.get(position).getTitle()+"\n阅读分享活动活动天数:"+basemarkBeans.get(position).getHours()+"天"
					          +"\n阅读分享活动报名截止时间:"+basemarkBeans.get(position).getHuodong_date()
					          +"\n阅读分享活动地点:"+basemarkBeans.get(position).getHuodong_addr()
							);
				}else{
					holder.title.setText(basemarkBeans.get(position).getTitle());
				}
				
				if(basemarkBeans.get(position).getStatus2().equals("0")){
					holder.status .setText("未通过审核");
				}
				if(basemarkBeans.get(position).getStatus2().equals("1")){
					holder.status .setText("已通过审核");
					holder.status .setTextColor(R.color.green);
				}
				holder.status.setVisibility(View.GONE);
			}else{
				holder.time.setText("团队名称:"+basemarkBeans.get(position).getProj_group());
				holder.username.setText("发布人:"+basemarkBeans.get(position).getProj_name());
				holder.title.setText(" 项目名称:"+basemarkBeans.get(position).getProj_name());
			}
			
			return convertView;
		}

		public ArrayList<DuanziBean> getDuanziList() {
			return duanziList;
		}

		public void setDuanziList(ArrayList<DuanziBean> duanziList) {
			this.duanziList = duanziList;
		}
		
		

	}

	private class Holder {
		CircleImageView image_view;
		TextView title;
		TextView time;
		TextView username;
		TextView status;
	}

	@OnItemClick(R.id.listView1)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
//		if(tag.equals("通知公告")||tag.equals("社区分享")||tag.equals("阅读精选")){
//		if(basemarkBeans.get(position).getProj_content()==null){
			Intent intent = new Intent(this, InfoDetail.class);
			intent.putExtra("id", basemarkBeans.get(position).getId());
			if(basemarkBeans.get(position).getType().equals("0")){
				intent.putExtra("tag","社区分享");
			}	if(basemarkBeans.get(position).getType().equals("1")){
				intent.putExtra("tag","通知公告");
			}	if(basemarkBeans.get(position).getType().equals("2")){
				intent.putExtra("tag","阅读分享活动");
			}	if(basemarkBeans.get(position).getType().equals("3")){
				intent.putExtra("tag","阅读精选");
			}
			startActivity(intent);
//		}

//		}
		
	}
}
