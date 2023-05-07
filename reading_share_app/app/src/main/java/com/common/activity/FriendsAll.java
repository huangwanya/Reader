package com.common.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.blueberry.activity.R;
import com.bumptech.glide.Glide;
import com.common.bean.FriendBean;
import com.common.bean.MyFriendBean;
import com.common.util.HttpUtil;
import com.common.util.MyApp;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsAll extends Activity {
	@ViewInject(R.id.listView1)
	private ListView listView1;
	private List<FriendBean> basemarkBeans = new ArrayList<FriendBean>();
	@ViewInject(R.id.add_new_xd)
	private TextView add_new_xd;
	private MyApp myApp;
	private GameAdapter adapter;
    private String jsonString,bioid,family_group;

	@ViewInject(R.id.searchView)
	private SearchView mSearchView;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) FriendsAll.this.getApplication();
		setContentView(R.layout.friends_list_all);
		ViewUtils.inject(this);
		adapter = new GameAdapter();
		listView1.setAdapter(adapter);
//		getGameXD();


		mSearchView.setSubmitButtonEnabled(true);
		mSearchView.onActionViewExpanded();

		// 设置搜索文本监听
		mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			// 当点击搜索按钮时触发该方法
			@Override
			public boolean onQueryTextSubmit(String query) {

				getGameXD(query);
				return false;
			}

			// 当搜索内容改变时触发该方法
			@Override
			public boolean onQueryTextChange(String newText) {
//				if (!TextUtils.isEmpty(newText)){
//					listView1.setFilterText(newText);
//				}else{
//					listView1.clearTextFilter();
//				}
				return false;
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		getGameXD();
	}

	@OnClick(R.id.top_back)
	public void back(View view) {
		finish();
	}
	@OnClick(R.id.add_new_xd)
	public void add(View view) {
		/*Intent intent =new Intent(FamilyGroupAll.this,AddFamilyGroup.class);
		startActivity(intent);*/
		
	}

	public void getGameXD(final String query) {
		basemarkBeans =new ArrayList<FriendBean>();
		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_USERLISTQUERY+query;
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


	/*	public void getGameXD() {
		basemarkBeans =new ArrayList<FriendBean>();


		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_USERLISTALL;

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
						Toast.makeText(FriendsAll.this, result, Toast.LENGTH_SHORT).show();
						;
					} catch (IOException e) {
						e.printStackTrace();
						result = "网络异常！";
						Toast.makeText(FriendsAll.this, result, Toast.LENGTH_SHORT).show();
						;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();

	}*/
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x125) {
				//
				basemarkBeans = new ArrayList<FriendBean>();
				ArrayList<FriendBean> goods_list = FriendBean
						.newInstanceList(jsonString);
				basemarkBeans.addAll(goods_list);
				listView1.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
			}
			if (msg.what == 0x126) {
				//
				basemarkBeans.addAll(new ArrayList<FriendBean>());
				listView1.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
			}
			if (msg.what == 0x127) {
				//
				Toast.makeText(FriendsAll.this, "操作成功", Toast.LENGTH_SHORT).show();
				;
				getGameXD("");
			}
			if (msg.what == 0x128) {
				//
				Toast.makeText(FriendsAll.this, "操作失败", Toast.LENGTH_SHORT).show();
				;
				
			}
			if (msg.what == 0x129) {
				//
				Toast.makeText(FriendsAll.this, "抱歉,请不要重复操作", Toast.LENGTH_SHORT).show();
				;
				
			}
			if (msg.what == 0x121) {
				//
				Toast.makeText(FriendsAll.this, "操作成功,等待对方确认", Toast.LENGTH_SHORT).show();
				;
				
			}
			if (msg.what == 0x122) {
				//
				Toast.makeText(FriendsAll.this, "您是该同学圈创建人员,无须加入", Toast.LENGTH_SHORT).show();
				;
				
			}
			if (msg.what == 0x123) {
				//
//			 	Intent intent =new Intent(FriendsAll.this,ClassGroupMainActivity.class);
//            	intent.putExtra("bioid",bioid);
//            	intent.putExtra("family_group", family_group);
//            	startActivity(intent);
				
			}
			if (msg.what == 0x124) {
				//
				Toast.makeText(FriendsAll.this, "您没有权限进人", Toast.LENGTH_SHORT).show();
				;
				
			}
			if (msg.what == 0x1255) {
				//
//			 	Intent intent =new Intent(FriendsAll.this,ClassGroupMainActivity.class);
//            	intent.putExtra("bioid",bioid);
//            	intent.putExtra("family_group", family_group);
//            	startActivity(intent);
				
			}
			if (msg.what == 0x1266) {
				//
				check(bioid) ;
				
			}
		
		};
	};
	public void baoming(final String goods_id) {


		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_ADDFRIENDS + "friends.userid2="+goods_id+"&friends.userid1="+myApp.getLoginKey();
					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					try {
						JSONObject obj = new JSONObject(result);
						String arrlist = obj.optString("jsonString");
						// JSONObject obj = new JSONObject(json);
						if (arrlist != "" && !arrlist.equals("arrlist")
								&& arrlist != null && !arrlist.equals("[]")) {
							if(arrlist.equals("1")){
								handler.sendEmptyMessage(0x121);

							}else if(arrlist.equals("0")){
								handler.sendEmptyMessage(0x128);

							}else if(arrlist.equals("2")){
								handler.sendEmptyMessage(0x129);
							}
						}


						else {
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}/*	public void baoming(final String goods_id) {

		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_ADDFRIENDS + "friends.userid2="+goods_id+"&friends.userid1="+myApp.getLoginKey();

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
									if(arrlist.equals("1")){
										handler.sendEmptyMessage(0x121);

									}else if(arrlist.equals("0")){
										handler.sendEmptyMessage(0x128);

									}else if(arrlist.equals("2")){
										handler.sendEmptyMessage(0x129);
									}
								}


								else {
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}

						}
					} catch (ClientProtocolException e) {
						e.printStackTrace();
						result = "网络异常！";
						Toast.makeText(FriendsAll.this, result, Toast.LENGTH_SHORT).show();
						;
					} catch (IOException e) {
						e.printStackTrace();
						result = "网络异常！";
						Toast.makeText(FriendsAll.this, result, Toast.LENGTH_SHORT).show();
						;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}*/

	
	public void check(final String goods_id) {
		
		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_CHECKBAOMING + "baoming.bioid="+goods_id+"&baoming.userid="+myApp.getLoginKey();

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
									if(arrlist.equals("1")){
										handler.sendEmptyMessage(0x123);
										
									}else if(arrlist.equals("0")){
										handler.sendEmptyMessage(0x124);
										
									}
								}
									
									
								else {
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}

						}
					} catch (ClientProtocolException e) {
						e.printStackTrace();
						result = "网络异常！";
						Toast.makeText(FriendsAll.this, result, Toast.LENGTH_SHORT).show();
						;
					} catch (IOException e) {
						e.printStackTrace();
						result = "网络异常！";
						Toast.makeText(FriendsAll.this, result, Toast.LENGTH_SHORT).show();
						;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}
	private class GameAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<FriendBean> duanziList;

		private GameAdapter() {
			inflater = LayoutInflater.from(FriendsAll.this);
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
						.inflate(R.layout.family_list_item, null);
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
			holder.name
					.setText("账号:" + basemarkBeans.get(position).getUsername()+"\n姓名:" + basemarkBeans.get(position).getQqnum()+"\n兴趣:" + basemarkBeans.get(position).getAddress()+"\n联系方式:" + basemarkBeans.get(position).getPhone()+"\n累计参加阅读分享活动天数:"+basemarkBeans.get(position).getHours());
			holder.phone
					.setText("年级:"+ basemarkBeans.get(position).getName()+"年级");
            if(basemarkBeans.get(position).getImage_url() !=null&&!basemarkBeans.get(position).getImage_url().equals("") ){
				String imagename = basemarkBeans.get(position).getImage_url()
						.split("\\\\")[1];

				Glide.with(FriendsAll.this)
						.load(HttpUtil.URL_BASEUPLOAD + imagename)
						.into(holder.image_view);
			}


			holder.shenhe.setVisibility(View.GONE);
			holder.address.setVisibility(View.GONE);

			return convertView;
		}

		public ArrayList<FriendBean> getDuanziList() {
			return duanziList;
		}

		public void setDuanziList(ArrayList<FriendBean> duanziList) {
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

	@OnItemClick(R.id.listView1)
	public void onItemClick(AdapterView<?> parent, View view, final int position,
			long id) {
	/*	Intent intent = new Intent(FriendsList.this,AddMessage.class);
		intent.putExtra("id", basemarkBeans.get(position).getId());
		startActivity(intent);*/
		
		if(myApp.getLoginKey() !=null&&!myApp.getLoginKey().equals("")){

			new AlertDialog.Builder(FriendsAll.this).setTitle("提示")  
	        .setMessage("根据提示进行操作").setPositiveButton("加为好友", new DialogInterface.OnClickListener(){  
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	                // TODO Auto-generated method stub  
	              	if(myApp.getLoginKey().equals(basemarkBeans.get(position).getId())){
	            		Toast.makeText(FriendsAll.this, "不能添加自己为好友", Toast.LENGTH_SHORT).show();
	            	}else{
	            		baoming(basemarkBeans.get(position).getId());
	            	}
	            }  
	        }).show();
		}else{
			Toast.makeText(FriendsAll.this, "请登录后进行操作", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(FriendsAll.this,Login.class);
			startActivity(intent);
		}
		
		
	}
}
