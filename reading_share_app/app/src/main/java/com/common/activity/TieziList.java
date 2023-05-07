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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.blueberry.activity.R;
import com.bumptech.glide.Glide;
import com.common.bean.BasemarkBean;
import com.common.bean.DuanziBean;
import com.common.bean.MyFriendBean;
import com.common.bean.SignBean;
import com.common.bean.TieziBean;
import com.common.util.BitmapCache;
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

public class TieziList extends Activity {
	@ViewInject(R.id.listView1)
	private ListView listView1;
	private List<TieziBean> basemarkBeans = new ArrayList<TieziBean>();
	@ViewInject(R.id.add_new_xd)
	private ImageView add_new_xd;
	@ViewInject(R.id.searchbtn)
	private Button searchbtn;
	@ViewInject(R.id.searchcontent)
	private EditText searchcontent;
	private MyApp myApp;
	private GameAdapter adapter;
	private String type;
	private String jsonString,jsonString0;

	private List<MyFriendBean> basemarkBeans0 = new ArrayList<MyFriendBean>();

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) TieziList.this.getApplication();
		setContentView(R.layout.tiezi_list);
		ViewUtils.inject(this);
		if (TextUtils.isEmpty(myApp.getLoginName())) {
			add_new_xd.setVisibility(View.INVISIBLE);
		}
//		type =getIntent().getExtras().getString("type");
		
	/*	searchbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				search(searchcontent.getText().toString());
			}
		});*/
		adapter = new GameAdapter();
		listView1.setAdapter(adapter);

		getGameXD0();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	public void getGameXD0() {
		basemarkBeans0 =new ArrayList<MyFriendBean>();
		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_MYMANLIST+myApp.getLoginKey();
					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					try {
						JSONObject obj = new JSONObject(result);
						String arrlist = obj.optString("jsonString");
						// JSONObject obj = new JSONObject(json);
						if (arrlist != "" && !arrlist.equals("arrlist")
								&& arrlist != null && !arrlist.equals("[]")) {
							jsonString0 =arrlist;



							handler.sendEmptyMessage(0x128);
						} else {
							handler.sendEmptyMessage(0x129);
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
	@OnClick(R.id.top_back)
	public void back(View view) {
		finish();
	}

	@OnClick(R.id.add_new_xd)
	public void addNew(View view) {
		Util.toIntent(TieziList.this, AddTiezi.class);
	}
	@SuppressLint("NewApi") public void getGameXD() {
		
		new Thread() {
			public void run() {
				try {
					
					basemarkBeans = new ArrayList<TieziBean>();
							String url = HttpUtil.URL_DUANZILISTBYTYPE2;


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
					e.printStackTrace();
					// TODO: handle exception
				}
			}
		}.start();
	}
	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x125) {
				//
				ArrayList<TieziBean> goods_list = TieziBean
						.newInstanceList(jsonString);



				ArrayList<TieziBean> goods_list_friends=new ArrayList<>();
				ArrayList<TieziBean> goods_list_all=new ArrayList<>();
				ArrayList<TieziBean> goods_list_blacklist=new ArrayList<>();


				for(TieziBean bean:goods_list){
					for(MyFriendBean user:basemarkBeans0){
						//好友
						if(user.getStatus().equals("1")&&user.getBlack_status().equals("0")&&(bean.getAuthor().equals(user.getUsername2())||bean.getAuthor().equals(user.getUsername1()))){
							goods_list_friends.add(bean);

						}
						//黑名单
						if(user.getStatus().equals("1")&&user.getBlack_status().equals("1")&&(bean.getAuthor().equals(user.getUsername2())||bean.getAuthor().equals(user.getUsername1()))){
							if(!myApp.getLoginName().equals(bean.getAuthor())){
								goods_list_blacklist.add(bean);
							}

						}


					}
				}

				goods_list.removeAll(goods_list_blacklist);
				goods_list.removeAll(goods_list_friends);

				goods_list_all.addAll(goods_list_friends);
				goods_list_all.addAll(goods_list);





				basemarkBeans.addAll(goods_list_all);
				adapter.notifyDataSetChanged();
			}
			
			if (msg.what == 0x126) {
		
				//
			}
			if (msg.what == 0x127) {
				//
			}
			if (msg.what == 0x128) {
				//
				basemarkBeans0 = new ArrayList<MyFriendBean>();
				ArrayList<MyFriendBean> goods_list = MyFriendBean
						.newInstanceList(jsonString0);
				basemarkBeans0.addAll(goods_list);

				getGameXD();
			}
			if (msg.what == 0x129) {
				//
				basemarkBeans0.addAll(new ArrayList<MyFriendBean>());

				getGameXD();
			}
		};
	};
/*	@SuppressLint("NewApi") public void getGameXD(final String keywords) {
		basemarkBeans = new ArrayList<DuanziBean>();
		
		
	

			
			new Thread() {
				public void run() {
					try {
						
						String url = HttpUtil.URL_DUANZILISTBYTYPE+type;
						
				

						HttpPost request = HttpUtil.getHttpPost(url);
						String result = null;
						try {
							// 获得响应对象
							HttpResponse response = HttpUtil.getHttpResponse(request);
							// 判断是否请求成功
							if (response.getStatusLine().getStatusCode() == 200) {
								// 获得响应
								result = EntityUtils.toString(response.getEntity());

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

							}
						} catch (ClientProtocolException e) {
							e.printStackTrace();
							result = "网络异常！";
							Toast.makeText(TieziList.this, result, Toast.LENGTH_SHORT).show();
							;
						} catch (IOException e) {
							e.printStackTrace();
							result = "网络异常！";
							Toast.makeText(TieziList.this, result, Toast.LENGTH_SHORT).show();
							;
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
					basemarkBeans = new ArrayList<DuanziBean>();
					ArrayList<DuanziBean> goods_list = DuanziBean
							.newInstanceList(jsonString);
					basemarkBeans.addAll(goods_list);
					listView1.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					
				}
				if (msg.what == 0x126) {
					//
					basemarkBeans.addAll(new ArrayList<DuanziBean>());
					listView1.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					
				}
			};
		};
	@SuppressLint("NewApi") public void search(final String keywords) {
		
		
	
		


		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_DUANZILIST+"?keyword="+keywords;
					

					HttpPost request = HttpUtil.getHttpPost(url);
					String result = null;
					try {
						// 获得响应对象
						HttpResponse response = HttpUtil.getHttpResponse(request);
						// 判断是否请求成功
						if (response.getStatusLine().getStatusCode() == 200) {
							// 获得响应
							result = EntityUtils.toString(response.getEntity());

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

						}
					} catch (ClientProtocolException e) {
						e.printStackTrace();
						result = "网络异常！";
						Toast.makeText(TieziList.this, result, Toast.LENGTH_SHORT).show();
						;
					} catch (IOException e) {
						e.printStackTrace();
						result = "网络异常！";
						Toast.makeText(TieziList.this, result, Toast.LENGTH_SHORT).show();
						;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}*/
	private class GameAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<DuanziBean> duanziList;
		private RequestQueue queue;
		private ImageLoader imageLoader;
		private GameAdapter() {
			inflater = LayoutInflater.from(TieziList.this);
			queue = Volley.newRequestQueue(TieziList.this);
			imageLoader = new ImageLoader(queue, new BitmapCache());
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
			String imageurl=HttpUtil.URL_BASEUPLOAD+basemarkBeans.get(position).getImage_url()
					.split("\\\\")[1];
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
			
			holder.status.setVisibility(View.GONE);
			holder.title
					.setText( basemarkBeans.get(position).getTitle()+"\n分类:"+basemarkBeans.get(position).getType2());
			
			
			
			
			
			
			
			
			if (imageurl != null && !imageurl.equals("")) {
				String imagename = basemarkBeans.get(position).getImage_url()
						.split("\\\\")[1];
//			MyBackAsynaTask asynaTask = new MyBackAsynaTask(
//					HttpUtil.URL_BASEUPLOAD + imagename, holder.image_view);
//			asynaTask.execute();

				Glide.with(TieziList.this)
						.load(HttpUtil.URL_BASEUPLOAD + imagename)
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
		TextView time;
		TextView status;
		TextView username;
	}

	@OnItemClick(R.id.listView1)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, InfoDetail.class);
		intent.putExtra("id", basemarkBeans.get(position).getId());
		intent.putExtra("tag", "社区分享");
		startActivity(intent);
	}
}
