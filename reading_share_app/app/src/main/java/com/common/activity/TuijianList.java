package com.common.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import com.blueberry.activity.R;
import com.common.bean.DuanziBean;
import com.common.util.HttpUtil;
import com.common.util.MyApp;
import com.common.util.MyBackAsynaTask;
import com.common.util.Util;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TuijianList extends Activity {
	@ViewInject(R.id.listView1)
	private ListView listView1;
	private List<DuanziBean> basemarkBeans = new ArrayList<DuanziBean>();
	@ViewInject(R.id.add_new_xd)
	private TextView add_new_xd;
	private MyApp myApp;
	private GameAdapter adapter;
	private String type;
	private String jsonString;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) TuijianList.this.getApplication();
		setContentView(R.layout.tuijian_xd_list);
		ViewUtils.inject(this);
		if (TextUtils.isEmpty(myApp.getLoginName())) {
			add_new_xd.setVisibility(View.INVISIBLE);
		}
		adapter = new GameAdapter();
		listView1.setAdapter(adapter);

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
		Util.toIntent(TuijianList.this, AddHuodong.class);
	}

	@SuppressLint("NewApi") public void getGameXD() {
		
		
		new Thread() {
			public void run() {
				try {
					
					basemarkBeans = new ArrayList<DuanziBean>();
//					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//							.permitAll().build();
//					StrictMode.setThreadPolicy(policy);
					String url = HttpUtil.URL_HUODONGTUIJIAN+myApp.getLoginKey();


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
			if (msg.what == 0x125) {
				//

			}
			
			if (msg.what == 0x126) {
				//
				ArrayList<DuanziBean> goods_list = DuanziBean
						.newInstanceList(jsonString);
				basemarkBeans.addAll(goods_list);
				adapter.notifyDataSetChanged();
			}
			if (msg.what == 0x127) {
				//
				basemarkBeans.addAll(new ArrayList<DuanziBean>());
				adapter.notifyDataSetChanged();
			}
		};
	};
	private class GameAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<DuanziBean> duanziList;

		private GameAdapter() {
			inflater = LayoutInflater.from(TuijianList.this);
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
				holder.username = (TextView) convertView
						.findViewById(R.id.username);
				holder.status = (TextView) convertView.findViewById(R.id.status);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.time.setText("发布时间:"
					+ basemarkBeans.get(position).getUpdatetime());
			holder.username.setText("发布人:"
					+ basemarkBeans.get(position).getAuthor());
			holder.title.setText(basemarkBeans.get(position).getTitle()+"\n阅读分享活动活动天数:"+basemarkBeans.get(position).getHours()+"天"+"\n限定报名:"+basemarkBeans.get(position).getCk_name()+"\n实际报名:"+basemarkBeans.get(position).getCk_phone()+""
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
			String imagename = basemarkBeans.get(position).getImage_url()
					.split("\\\\")[1];
			MyBackAsynaTask asynaTask = new MyBackAsynaTask(
					HttpUtil.URL_BASEUPLOAD + imagename, holder.image_view);
			asynaTask.execute();
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
		if(basemarkBeans.get(position).getStatus2().equals("0")){
			Toast.makeText(TuijianList.this, "抱歉,该阅读分享活动未通过审核", Toast.LENGTH_SHORT).show();
		}else{
			Intent intent = new Intent(this, InfoDetail.class);
			intent.putExtra("id", basemarkBeans.get(position).getId());
			intent.putExtra("tag", "阅读分享活动");
			startActivity(intent);
		}
		
	}
}
