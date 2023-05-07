package com.common.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import com.blueberry.activity.R;
import com.bumptech.glide.Glide;
import com.common.bean.DuanziBean;
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

public class MyFriends extends Activity {
	@ViewInject(R.id.listView1)
	private ListView listView1;
	private List<MyFriendBean> basemarkBeans = new ArrayList<MyFriendBean>();
	@ViewInject(R.id.add_new_xd)
	private TextView add_new_xd;
	private MyApp myApp;
	private GameAdapter adapter;
    private String jsonString,bioid,family_group;
	@ViewInject(R.id.searchcontent)
	private EditText searchcontent;
	@ViewInject(R.id.searchbtn)
	private Button searchbtn;



    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) MyFriends.this.getApplication();
		setContentView(R.layout.my_friends);
		ViewUtils.inject(this);
		adapter = new GameAdapter();
		listView1.setAdapter(adapter);
		getGameXD("");
		
		
searchbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String keyword =searchcontent.getText().toString();
				getGameXD(keyword);
				
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getGameXD("");
	}

	@OnClick(R.id.top_back)
	public void back(View view) {
		finish();
	}
	@OnClick(R.id.add_new_xd)
	public void add(View view) {
		Intent intent =new Intent(MyFriends.this,FriendsAll.class);
		startActivity(intent);
		
	}

	public void getGameXD(final String keyword) {
		basemarkBeans =new ArrayList<MyFriendBean>();
		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_MYFRIENDSLISTALL+myApp.getLoginKey()+"&keyword="+keyword;
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
		
	}/*	public void getGameXD(final String keyword) {
		basemarkBeans =new ArrayList<MyFriendBean>();


		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_MYFRIENDSLISTALL+myApp.getLoginKey()+"&keyword="+keyword;

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
						Toast.makeText(MyFriends.this, result, Toast.LENGTH_SHORT).show();
						;
					} catch (IOException e) {
						e.printStackTrace();
						result = "网络异常！";
						Toast.makeText(MyFriends.this, result, Toast.LENGTH_SHORT).show();
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
				basemarkBeans = new ArrayList<MyFriendBean>();
				ArrayList<MyFriendBean> goods_list = MyFriendBean
						.newInstanceList(jsonString);
				basemarkBeans.addAll(goods_list);
				listView1.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
			}
			if (msg.what == 0x126) {
				//
				basemarkBeans.addAll(new ArrayList<MyFriendBean>());
				listView1.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
			}
			if (msg.what == 0x127) {
				//
				Toast.makeText(MyFriends.this, "操作成功", Toast.LENGTH_SHORT).show();
				;
				getGameXD("");
			}
			if (msg.what == 0x128) {
				//
				Toast.makeText(MyFriends.this, "操作失败", Toast.LENGTH_SHORT).show();
				;
				
			}
			if (msg.what == 0x129) {
				//
				Toast.makeText(MyFriends.this, "抱歉,请不要重复操作", Toast.LENGTH_SHORT).show();
				;
				
			}
			if (msg.what == 0x121) {
				//
				Toast.makeText(MyFriends.this, "操作成功", Toast.LENGTH_SHORT).show();
				getGameXD("");
				
			}
			if (msg.what == 0x122) {
				//

			}
			if (msg.what == 0x123) {
				//
				Toast.makeText(MyFriends.this, "操作成功", Toast.LENGTH_SHORT).show();
				getGameXD("");
				
			}
			if (msg.what == 0x124) {
				//
				Toast.makeText(MyFriends.this, "操作失败", Toast.LENGTH_SHORT).show();
				;
				
			}
			if (msg.what == 0x1255) {
				//
//			 	Intent intent =new Intent(MyFriends.this,ClassGroupMainActivity.class);
//            	intent.putExtra("bioid",bioid);
//            	intent.putExtra("family_group", family_group);
//            	startActivity(intent);
				
			}
			if (msg.what == 0x1266) {
				//
				
			}
		
		};
	};
	public void delete(final String userid1,final String userid2) {

		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_DELFRIENDS + "userid1="+userid1+"&userid2="+userid2;
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
	}/*	public void delete(final String userid1,final String userid2) {

		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_DELFRIENDS + "userid1="+userid1+"&userid2="+userid2;

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
						Toast.makeText(MyFriends.this, result, Toast.LENGTH_SHORT).show();
						;
					} catch (IOException e) {
						e.printStackTrace();
						result = "网络异常！";
						Toast.makeText(MyFriends.this, result, Toast.LENGTH_SHORT).show();
						;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}*/
/*	public void check_creator(final String goods_id) {
		
		boolean flag = false;
		
		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_CHECK + "baoming.bioid="+goods_id+"&baoming.userid="+myApp.getLoginKey();
					
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
										handler.sendEmptyMessage(0x1255);
										
									}else if(arrlist.equals("0")){
										handler.sendEmptyMessage(0x1266);
										
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
						Toast.makeText(MyFriends.this, result, Toast.LENGTH_SHORT).show();
						;
					} catch (IOException e) {
						e.printStackTrace();
						result = "网络异常！";
						Toast.makeText(MyFriends.this, result, Toast.LENGTH_SHORT).show();
						;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}*/
	
	public void agree(final String userid1,final String userid2) {

		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_UPDATE_FRIENDS + "userid1="+userid1+"&userid2="+userid2+"&status=1";
					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

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

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}
	public void add_to_blacklist(final String userid1,final String userid2) {

		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_TO_BLACKLIST + "friends.userid1="+userid1+"&friends.userid2="+userid2;
					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

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

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}


	/*public void agree(final String userid1,final String userid2) {

		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_UPDATE_FRIENDS + "userid1="+userid1+"&userid2="+userid2+"&status=1";

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
						Toast.makeText(MyFriends.this, result, Toast.LENGTH_SHORT).show();
						;
					} catch (IOException e) {
						e.printStackTrace();
						result = "网络异常！";
						Toast.makeText(MyFriends.this, result, Toast.LENGTH_SHORT).show();
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
		private ArrayList<MyFriendBean> duanziList;

		private GameAdapter() {
			inflater = LayoutInflater.from(MyFriends.this);
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
			String friends_name = null;
			
			String status="";
			String imagename = null;
			if(basemarkBeans.get(position).getStatus().equals("0")){
				if(basemarkBeans.get(position).getUserid1().equals(myApp.getLoginKey())){
					status="等待对方确认";
					friends_name=basemarkBeans.get(position).getUsername2();
					if(basemarkBeans.get(position).getImage_url2()!=null && !basemarkBeans.get(position).getImage_url2().equals("")){
						imagename = basemarkBeans.get(position).getImage_url2()
								.split("\\\\")[1];
					}

				}
				if(basemarkBeans.get(position).getUserid2().equals(myApp.getLoginKey())){
					status=basemarkBeans.get(position).getUsername1()+"请求添加您为好友";
					friends_name=basemarkBeans.get(position).getUsername1();
					if(basemarkBeans.get(position).getImage_url1()!=null && !basemarkBeans.get(position).getImage_url1().equals("")){
						imagename = basemarkBeans.get(position).getImage_url1()
								.split("\\\\")[1];
					}


				}
				holder.name
				.setText( friends_name+"\n好友状态："+status);

				Glide.with(MyFriends.this)
						.load(HttpUtil.URL_BASEUPLOAD + imagename)
						.into(holder.image_view);

				holder.shenhe.setVisibility(View.GONE);
				holder.address.setVisibility(View.GONE);
			}
			if(basemarkBeans.get(position).getStatus().equals("1")) {

				String address = null;
				String qqnum = null;
				String phone = null;
				String age = null;
				if (basemarkBeans.get(position).getUserid1().equals(myApp.getLoginKey())) {
					friends_name = basemarkBeans.get(position).getUsername2();

					address = basemarkBeans.get(position).getAddress2();
					qqnum = basemarkBeans.get(position).getQqnum2();
					phone = basemarkBeans.get(position).getPhone2();
					age = basemarkBeans.get(position).getAge2();


					if (basemarkBeans.get(position).getImage_url2() != null && !basemarkBeans.get(position).getImage_url2().equals("")) {

						imagename = basemarkBeans.get(position).getImage_url2()
								.split("\\\\")[1];
					}
				}
					if (basemarkBeans.get(position).getUserid2().equals(myApp.getLoginKey())) {
						friends_name = basemarkBeans.get(position).getUsername1();

						address = basemarkBeans.get(position).getAddress1();
						qqnum = basemarkBeans.get(position).getQqnum1();
						phone = basemarkBeans.get(position).getPhone1();
						age = basemarkBeans.get(position).getAge1();
						if (basemarkBeans.get(position).getImage_url1() != null && !basemarkBeans.get(position).getImage_url1().equals("")) {
							imagename = basemarkBeans.get(position).getImage_url1()
									.split("\\\\")[1];
						}


					}
					holder.name
							.setText(friends_name + "\n" + "兴趣:" + address + "\n姓名:" + qqnum + "\n☎:" + phone);

					holder.phone
							.setText("年级:" + age + "年级");


					Glide.with(MyFriends.this)
							.load(HttpUtil.URL_BASEUPLOAD + imagename)
							.into(holder.image_view);

					holder.shenhe.setVisibility(View.GONE);
					holder.address.setVisibility(View.GONE);

			}
		

			return convertView;
		}

		public ArrayList<MyFriendBean> getDuanziList() {
			return duanziList;
		}

		public void setDuanziList(ArrayList<MyFriendBean> duanziList) {
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
			if(basemarkBeans.get(position).getStatus().equals("0")){
				if(basemarkBeans.get(position).getUserid2().equals(myApp.getLoginKey())){
					new AlertDialog.Builder(MyFriends.this).setTitle("提示")  
			        .setMessage("是否添加对方为好友").setNegativeButton("确认", new DialogInterface.OnClickListener() {  
			              
			            @Override  
			            public void onClick(DialogInterface dialog, int which) {  
			                // TODO Auto-generated method stub  
			            	agree(basemarkBeans.get(position).getUserid1(),basemarkBeans.get(position).getUserid2());
			            }  
			        }).setPositiveButton("取消", new DialogInterface.OnClickListener(){  
			            @Override  
			            public void onClick(DialogInterface dialog, int which) {  
			                // TODO Auto-generated method stub  
			            	delete(basemarkBeans.get(position).getUserid1(),basemarkBeans.get(position).getUserid2());
			            }  
			        }).show();
				}
			}else{
				new AlertDialog.Builder(MyFriends.this).setTitle("提示")  
		        .setMessage("根据提示进行操作").setNegativeButton("删除好友", new DialogInterface.OnClickListener() {  
		              
		            @Override  
		            public void onClick(DialogInterface dialog, int which) {  
		                // TODO Auto-generated method stub  
		            	delete(basemarkBeans.get(position).getUserid1(),basemarkBeans.get(position).getUserid2());
		            }  
		        }).setPositiveButton("加入黑名单", new DialogInterface.OnClickListener(){
		            @Override  
		            public void onClick(DialogInterface dialog, int which) {

						add_to_blacklist(basemarkBeans.get(position).getUserid1(),basemarkBeans.get(position).getUserid2());
		                // TODO Auto-generated method stub  
		            /*	if(basemarkBeans.get(position).getUserid1().equals(myApp.getLoginKey())){
		            		Intent intent =new Intent(MyFriends.this,AddMessage.class);
			            	intent.putExtra("id", basemarkBeans.get(position).getUserid2());
			            	startActivity(intent);
						}
						if(basemarkBeans.get(position).getUserid2().equals(myApp.getLoginKey())){
							Intent intent =new Intent(MyFriends.this,AddMessage.class);
			            	intent.putExtra("id", basemarkBeans.get(position).getUserid1());
			            	startActivity(intent);
						}*/
		            	
		            }  
		        }).
		        show();
			}
	
		}else{
			Toast.makeText(MyFriends.this, "请登录后进行操作", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(MyFriends.this,Login.class);
			startActivity(intent);
		}
		
		
	}
}
