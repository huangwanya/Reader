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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

import com.alibaba.fastjson.JSONArray;
import com.blueberry.activity.R;
import com.bumptech.glide.Glide;
import com.common.bean.BasemarkBean;
import com.common.bean.DuanziBean;
import com.common.bean.FriendBean;
import com.common.bean.ShetuanBean;
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

public class FriendsList extends Activity {
	@ViewInject(R.id.listView1)
	private ListView listView1;
	private List<FriendBean> basemarkBeans = new ArrayList<FriendBean>();
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
		myApp = (MyApp) FriendsList.this.getApplication();
		setContentView(R.layout.friends_list);
		ViewUtils.inject(this);
		add_new_xd.setVisibility(View.INVISIBLE);
		bioid =getIntent().getExtras().getString("bioid");
		adapter = new GameAdapter();
		listView1.setAdapter(adapter);
		getGameXD(bioid);
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

	public void getGameXD(final String bioid) {
		
		new Thread() {
			public void run() {
				try {
					
					basemarkBeans =new ArrayList<FriendBean>();
					String url = HttpUtil.URL_FRIENDSLIST+"bioid="+ bioid;


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

				Toast.makeText(FriendsList.this, "发布成功!", Toast.LENGTH_LONG)
						.show();
				finish();
			}
			
			if (msg.what == 0x126) {
				//

				ArrayList<FriendBean> goods_list = FriendBean
						.newInstanceList(jsonString);
				basemarkBeans.addAll(goods_list);
				adapter.notifyDataSetChanged();
			}
			if (msg.what == 0x128) {
				//
				Toast.makeText(FriendsList.this, "操作成功",
						Toast.LENGTH_SHORT).show();
				getGameXD(bioid);
			}
			if (msg.what == 0x123) {
				//
				Toast.makeText(FriendsList.this, "只有活动发布者方可进行审核",
						Toast.LENGTH_SHORT).show();
			}
			if (msg.what == 0x129) {
				//
				Toast.makeText(FriendsList.this, "操作失败",
						Toast.LENGTH_SHORT).show();
			}
		};
	};
	private class GameAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<DuanziBean> duanziList;

		private GameAdapter() {
			inflater = LayoutInflater.from(FriendsList.this);
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
						.inflate(R.layout.friends_list_item, null);
				holder = new Holder();
				holder.image_view = (CircleImageView) convertView
						.findViewById(R.id.image_view);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				holder.phone = (TextView) convertView.findViewById(R.id.phone);
				holder.shenhe = (TextView) convertView.findViewById(R.id.shenhe);
//				holder.qq = (TextView) convertView.findViewById(R.id.qq);
				holder.address = (TextView) convertView.findViewById(R.id.address);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}

			if(basemarkBeans.get(position).getBaoming_status().equals("1")){
				holder.shenhe.setText("已通过报名审核");
				holder.shenhe.setTextColor(Color.GREEN);
			}else{
				holder.shenhe.setText("未通过报名审核");
				holder.shenhe.setTextColor(Color.RED);
			}
			holder.name
					.setText("账号:" + basemarkBeans.get(position).getUsername()+"\n姓名:" + basemarkBeans.get(position).getQqnum()+"\n兴趣:" + basemarkBeans.get(position).getAddress()+"\n联系方式:" + basemarkBeans.get(position).getPhone()+"\n累计参加阅读分享活动天数:"+basemarkBeans.get(position).getHours());
			holder.phone
			.setText("年级:"+ basemarkBeans.get(position).getName()+"年级");

			String imagename = basemarkBeans.get(position).getImage_url()
					.split("\\\\")[1];

			Glide.with(FriendsList.this)
					.load(HttpUtil.URL_BASEUPLOAD + imagename)
					.into(holder.image_view);
		/*	holder.qq
			.setText("QQ:" + basemarkBeans.get(position).getDanwei());
			holder.address
			.setText("地址:" + basemarkBeans.get(position).getScore());*/

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
		TextView name;
		TextView shenhe;
		TextView phone;
//		TextView qq;
		TextView address;
	}

	public void shenhebaoming(final String bioid, final String userid) {


		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_BAOMINGSHENHE+"bioid="+bioid+"&userid="+userid+"&userid2="+myApp.getLoginKey();


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
							}else if(arrlist.equals("3")){
								handler.sendEmptyMessage(0x123);

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
	@OnItemClick(R.id.listView1)
	public void onItemClick(AdapterView<?> parent, View view, final int position,
			long id) {
	/*	Intent intent = new Intent(FriendsList.this,TieziList_.class);
		intent.putExtra("author", basemarkBeans.get(position).getUsername());
		startActivity(intent);*/
		
		new AlertDialog.Builder(FriendsList.this).setTitle("提示")  
        .setMessage("报名审核").setNegativeButton("取消", new DialogInterface.OnClickListener() {
              
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                // TODO Auto-generated method stub  
            	dialog.dismiss();
            }  
        }).setPositiveButton("通过审核", new DialogInterface.OnClickListener(){
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                // TODO Auto-generated method stub
			/*	if(myApp.getName().equals("队长")){
					shenhebaoming( bioid,basemarkBeans.get(position).getId());
				}else{
					Toast.makeText(FriendsList.this, "只有队长有审核权限",
							Toast.LENGTH_SHORT).show();
				}*/

				shenhebaoming( bioid,basemarkBeans.get(position).getId());

            }  
        }).show();  
		
	}
}
