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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

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

public class MyHuodongList extends Activity {
	@ViewInject(R.id.listView1)
	private ListView listView1;
	private List<DuanziBean> basemarkBeans = new ArrayList<DuanziBean>();
	@ViewInject(R.id.add_new_xd)
	private ImageView add_new_xd;
	private MyApp myApp;
	private GameAdapter adapter;
	private String jsonString;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) MyHuodongList.this.getApplication();
		setContentView(R.layout.myduanzilist);
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
		
//		if(myApp.getName().equals("管理员")){
			add_new_xd.setVisibility(View.VISIBLE);
//		}else{
//			add_new_xd.setVisibility(View.GONE);
//
//		}

		
		
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(MyHuodongList.this).setTitle("提示")  
		        .setMessage("请根据提示进行操作").setNegativeButton("查看详情", new DialogInterface.OnClickListener() {  
		              
		            @Override  
		            public void onClick(DialogInterface dialog, int which) {  
		                // TODO Auto-generated method stub  
		            	Intent intent = new Intent(MyHuodongList.this, InfoDetail.class);
		        		intent.putExtra("id", basemarkBeans.get(arg2).getId());
		        		startActivity(intent);
		            }  
		        }).setPositiveButton("查看该阅读分享活动的报名人员", new DialogInterface.OnClickListener(){
		            @Override  
		            public void onClick(DialogInterface dialog, int which) {  
		                // TODO Auto-generated method stub  
		            	Intent intent =new Intent(MyHuodongList.this,FriendsList.class);
		            	Bundle b =new Bundle();
		            	b.putString("bioid", basemarkBeans.get(arg2).getId());
						intent.putExtra("tag", "阅读分享活动");
		            	intent.putExtras(b);
		            	startActivity(intent);
		            }  
		        }).setNeutralButton("撤销该阅读分享活动", new DialogInterface.OnClickListener(){
		            @Override  
		            public void onClick(DialogInterface dialog, int which) {  
		                // TODO Auto-generated method stub  
		            	del( basemarkBeans.get(arg2).getId());
		            }  
		        }).show();  
			}
		});
		
		
		
		
		
	add_new_xd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MyHuodongList.this,AddHuodong.class);
				startActivity(intent);
				
			}
		});
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
				Toast.makeText(MyHuodongList.this, "操作成功",
						Toast.LENGTH_SHORT).show();
				 getGameXD();
			}
			if (msg.what == 0x128) {
				//
				Toast.makeText(MyHuodongList.this, "操作失败",
						Toast.LENGTH_SHORT).show();
			}
		};
	};
	public void del(final String bioid) {
		
		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_HUODONGDEL+"biotech.id="+bioid;


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

							try {
								JSONObject obj = new JSONObject(result);
								String arrlist = obj.optString("jsonString");
								// JSONObject obj = new JSONObject(json);
								if (arrlist != "" && !arrlist.equals("arrlist")
										&& arrlist != null && !arrlist.equals("[]")) {
									if(arrlist.equals("1")){
								
											handler.sendEmptyMessage(0x127);
									}else{
									
										handler.sendEmptyMessage(0x128);
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
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getGameXD();

	}

	@OnClick(R.id.top_back)
	public void back(View view) {
		finish();
	}

	@OnClick(R.id.add_new_xd)
	public void addNew(View view) {
		Util.toIntent(MyHuodongList.this, AddHuodong.class);
	}

	public void getGameXD() {
		
		
		new Thread() {
			public void run() {
				try {
					basemarkBeans =new ArrayList<DuanziBean>();
					String url = HttpUtil.URL_DUANZILISTUSER + "author="
							+ myApp.getLoginName();


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
			inflater = LayoutInflater.from(MyHuodongList.this);
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
				holder.time = (TextView) convertView.findViewById(R.id.times);
				holder.status = (TextView) convertView.findViewById(R.id.status);

				holder.username = (TextView) convertView
						.findViewById(R.id.username);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.time.setText("发布时间:"
					+ basemarkBeans.get(position).getUpdatetime());
			holder.username.setText("发布人:"
					+ basemarkBeans.get(position).getAuthor());
			holder.title.setText(basemarkBeans.get(position).getTitle()+"\n阅读分享活动活动天数:"+basemarkBeans.get(position).getHours()+"天"
			          +"\n阅读分享活动报名截止时间:"+basemarkBeans.get(position).getHuodong_date()
			          +"\n阅读分享活动地点:"+basemarkBeans.get(position).getHuodong_addr()
					+"\n年级:"+basemarkBeans.get(position).getAge()
					);
			
			if(basemarkBeans.get(position).getStatus2().equals("0")){
				holder.status .setText("未通过审核");
			}
			if(basemarkBeans.get(position).getStatus2().equals("1")){
				holder.status .setText("已通过审核");
				holder.status .setTextColor(R.color.green);
			}

			String image_url = basemarkBeans.get(position).getImage_url();

			if(image_url.length()>1){
				String imageurl_ = image_url.split(
						"\\\\")[1];
				String imageurl = HttpUtil.URL_BASEUPLOAD + imageurl_;


				Glide.with(MyHuodongList.this)
						.load(imageurl)
						.into(holder.image_view);
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
		TextView status;
		TextView time;
		TextView username;
	}

}
