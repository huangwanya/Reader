package com.common.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;




import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;


import com.blueberry.activity.R;
import com.bumptech.glide.Glide;
import com.common.adapter.GoodsDetailCommentsListViewAdapter;
import com.common.bean.CommentsList;
import com.common.bean.FriendBean;
import com.common.custom.MyListView;
import com.common.util.BaseCallback;
import com.common.util.HttpUtil;
import com.common.util.MyApp;
import com.common.util.MyBackAsynaTask;
import com.common.util.Util;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.orhanobut.dialogplus.DialogPlus;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.travel.android.common.MyCommentsDialog;

public class InfoDetail extends Activity {
	@ViewInject(R.id.title)
	private TextView title;
	@ViewInject(R.id.add_new_xd)
	private TextView add_new_xd;
	@ViewInject(R.id.play)
	private TextView play;
	@ViewInject(R.id.title1)
	private TextView title11;



	@ViewInject(R.id.zan)
	private TextView zan;
	@ViewInject(R.id.shoucang1)
	private TextView shoucang1;
	@ViewInject(R.id.pinglun1)
	private TextView pinglun1;
	@ViewInject(R.id.share1)
	private TextView share1;
	@ViewInject(R.id.jubao1)
	private TextView jubao1;
	@ViewInject(R.id.sixin1)
	private TextView sixin1;

private String image_url_w;
private String conntent_w;


	@ViewInject(R.id.time)
	private TextView time;
	@ViewInject(R.id.content)
	private TextView content;
	@ViewInject(R.id.username)
	private TextView username;
	@ViewInject(R.id.location)
	private TextView location;
	@ViewInject(R.id.imageView1)
	private ImageView imageView1;
	@ViewInject(R.id.dianzan)
	private ImageView dianzan;
	@ViewInject(R.id.goodsCommentsListView)
	private MyListView goodsCommentsListView;
	private String id;
	private String tag;
	private MyApp myApp;
	private String zuobiao;
	private GoodsDetailCommentsListViewAdapter gooddetailcommentsAdapter;
	private ArrayList<CommentsList> lists = new ArrayList<CommentsList>();
	private String status;
	private String file_link,address;
	private int count_,count_1;
	private String jsonString;
	private String age,huodongage,author;
	private boolean isbbaoming,iszan,isfolder,isjubao,ispinglun,ismsg;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//				.detectDiskReads().detectDiskWrites().detectNetwork()
//				.penaltyLog().build());
//		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
//				.penaltyLog().penaltyDeath().build());
		setContentView(R.layout.game_xd_message);
		myApp = (MyApp) InfoDetail.this.getApplication();
		ViewUtils.inject(this);
		id = getIntent().getStringExtra("id");
		tag = getIntent().getStringExtra("tag");
		title.setText(tag);
		gooddetailcommentsAdapter = new GoodsDetailCommentsListViewAdapter(InfoDetail.this);
		goodsCommentsListView.setAdapter(gooddetailcommentsAdapter);

		if(tag !=null){
			if(tag.equals("通知公告")||tag.equals("社区分享")||tag.equals("阅读精选")){
				add_new_xd.setVisibility(View.GONE);
				location.setVisibility(View.GONE);
			}else{
				add_new_xd.setVisibility(View.VISIBLE);
				location.setVisibility(View.GONE);
			}
		}

		checkbaoming(id);
		location.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			}
		});
		play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

					String type = file_link.substring(file_link
							.lastIndexOf(".") + 1);

					Bundle bundle = new Bundle();
					bundle.putString("url", file_link);
					Intent intent = null;



//					zan.setDrawable
			/*		intent = new Intent(InfoDetail.this,
							VideoPlayer.class);*/

				/*	intent.putExtras(bundle);
					startActivity(intent);*/

			}
		});
		
		add_new_xd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int age1=Integer.parseInt(age);
				int huodongage1=Integer.parseInt(huodongage);
					if(!isbbaoming){
						if(count_1 >=count_){
							Toast.makeText(InfoDetail.this, "抱歉,报名已满", 1).show();
							return;
						}
						if(age1!=huodongage1){
							Toast.makeText(InfoDetail.this, "该活动要求"+huodongage1+"年级，您是"+age1+"年级,不符合报名要求", 1).show();
							return;
						}
						if((age1==huodongage1)&&(count_1 <=count_))
						{
							baoming(id);
						}
						
					}else{
						Toast.makeText(InfoDetail.this, "您已经报名过了", 1).show();
					}
				
			
				
			}
		});

	}
	public void loaddata(){
		new Thread() {
			public void run() {
				try {
					String url = HttpUtil.URL_LOAD;
					String query ="";
					query+="user.id="+myApp.getLoginKey();
					url+=query;


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					jsonString=result;

					handler.sendEmptyMessage(0x1256);

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();



	}

	//设置textview图片
	private Drawable changeBtnTop(int id){
		Drawable drawableTop  = getResources().getDrawable(id);
		drawableTop .setBounds(0, 0, drawableTop .getMinimumWidth(), drawableTop .getMinimumHeight());
		return drawableTop ;
	}
	@OnClick(R.id.top_back)
	public void back(View view) {
		finish();
	}
	@OnClick(R.id.zan)
	public void dianzan(View view) {
        if(iszan){
        	cancelzan(id);
		}else{
			dianzan(id);
		}
		

	}
	@OnClick(R.id.shoucang1)
	public void shoucang1(View view) {
		if(isfolder){
			cancelshoucang(id);
		}else{
			shoucang(id);
		}

	}
	@OnClick(R.id.share1)
	public void share1(View view) {
		ArrayList<CharSequence> imageUris = new ArrayList<>();
		imageUris.add(image_url_w);
		imageUris.add(conntent_w);

		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
		shareIntent.putCharSequenceArrayListExtra(Intent.EXTRA_STREAM, imageUris);
		shareIntent.setType("text/*");
		startActivity(Intent.createChooser(shareIntent, "分享"));
	}

	/**
	 * 评论dialog
	 */
	private DialogPlus mCommentsDialog;
	@OnClick(R.id.sixin1)
	public void sixin1(View view) {
		if(!ismsg){
//			cancelshoucang(id);
			MyCommentsDialog dialog1 = new MyCommentsDialog(InfoDetail.this);
			mCommentsDialog = dialog1.createDialog(commentsCallback);
			mCommentsDialog.show();
		}else{
			Util.showToast(InfoDetail.this,"已私聊");
		}

	}

	public void addXD(final String content) {


		new Thread() {
			public void run() {
				try {


					String url = HttpUtil.URL_MESSAGESADD;
					String query ="";
					query+="message.content="+ URLEncoder.encode(content,"utf-8")+"&";
					query+="message.luxianid="+id+"&";
					query+="message.author="+author+"&";
					query+="message.username="+myApp.getLoginName()+"&";
					query+="message.userid="+myApp.getLoginKey();

					url+=query;


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					try {
						JSONObject obj = new JSONObject(result);
						String arrlist = obj.optString("jsonString");
						// JSONObject obj = new JSONObject(json);
						if(arrlist.equals("1")){
							handler.sendEmptyMessage(0x151);

						}else{
							handler.sendEmptyMessage(0x152);

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
	private BaseCallback commentsCallback = new BaseCallback() {
		@Override
		public void sendMessage(final Object obj) {
			addXD(obj.toString());

		}
	};
	@OnClick(R.id.pinglun1)
	public void pinglun1(View view) {

//		if(ispinglun){
//			Util.showToast(InfoDetail.this,"已评论");
//		}else{
			Intent intent = new Intent(InfoDetail.this, com.common.activity.CommentsList.class);
			intent.putExtra("bioid", id);
			intent.putExtra("flag", ispinglun);
			startActivity(intent);
//		}
		

	}
	@OnClick(R.id.jubao1)
	public void jubao1(View view) {

		if(isjubao){
			Util.showToast(InfoDetail.this,"已举报");
		}else{
			jubao(id);
		}


	}
	

	public void loadingCommentsData(final String id) {
		
		new Thread() {
			public void run() {
				try {
					
					lists =new ArrayList<CommentsList>();
					String url = HttpUtil.URL_DUANZICOMMENTS + id;


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

				Toast.makeText(InfoDetail.this, "发布成功!", Toast.LENGTH_LONG)
						.show();
				finish();
			}
			if (msg.what == 0x1256) {
				//

				JSONObject obj;
				try {
					obj = new JSONObject(jsonString);
					String arrlist = obj.optString("jsonString");
					JSONObject user = new JSONObject(arrlist);
					if(obj.length()> 0){
						 age = user.optString("age");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			if (msg.what == 0x126) {
				//
//				ArrayList<CommentsList> goods_list = CommentsList
//						.newInstanceList(jsonString);
//				lists.addAll(goods_list);
//
//				gooddetailcommentsAdapter.setGoodsDatas(goods_list);
//				gooddetailcommentsAdapter.notifyDataSetChanged();
				loaddata();
			}
			if (msg.what == 0x127) {
				//
				
			}
			if (msg.what == 0x158) {
				//

			}
			if (msg.what == 0x159) {
				//
				Toast.makeText(InfoDetail.this, "服务器错误", Toast.LENGTH_SHORT)
						.show();
			}
			if (msg.what == 0x128) {
				//
				Toast.makeText(InfoDetail.this, "点赞成功", Toast.LENGTH_SHORT)
				.show();
				iszan=true;
				loadingGoodsDetailData(id);
			}
			if (msg.what == 0x131) {
				//
				iszan=true;

				zan.setCompoundDrawables(null,changeBtnTop(R.drawable.like_red_50),null,null);
//				Util.showToast(InfoDetail.this,"点赞成功");
//				loadingGoodsDetailData(id);
			}
			if (msg.what == 0x132) {
				//
				zan.setCompoundDrawables(null,changeBtnTop(R.drawable.like_normal_50),null,null);
				iszan=false;
			}
			if (msg.what == 0x133) {
				//
				isfolder=true;
				shoucang1.setCompoundDrawables(null,changeBtnTop(R.drawable.favorite_red_50),null,null);
//				loadingGoodsDetailData(id);
			}
			if (msg.what == 0x134) {
				//
				isfolder=false;
				shoucang1.setCompoundDrawables(null,changeBtnTop(R.drawable.favorite_normal_50),null,null);
			}
			if (msg.what == 0x135) {
				//
				ispinglun=true;
			}
			if (msg.what == 0x136) {
				//
				ispinglun=false;
			}
			if (msg.what == 0x145) {
				//
				ismsg=true;
			}
			if (msg.what == 0x146) {
				//
				ismsg=false;
			}
			if (msg.what == 0x151) {
				//
				ismsg=true;
				Util.showToast(InfoDetail.this,"私聊成功");

				mCommentsDialog.dismiss();
				loadingGoodsDetailData(id);
			}if (msg.what == 0x152) {
				//
				ismsg=false;
			}
			if (msg.what == 0x147) {
				//
				isjubao=true;
			}
			if (msg.what == 0x148) {
				//
				isjubao=false;
			}
			if (msg.what == 0x137) {
				//
				iszan=false;
				zan.setCompoundDrawables(null,changeBtnTop(R.drawable.like_normal_50),null,null);
				Util.showToast(InfoDetail.this,"已取消");
				loadingGoodsDetailData(id);

			}
			if (msg.what == 0x138) {
				//
//				ispinglun=false;
			}
			if (msg.what == 0x129) {
				//
				Toast.makeText(InfoDetail.this, "点赞失败", Toast.LENGTH_SHORT)
				.show();
			}
			if (msg.what == 0x141) {
				//
				shoucang1.setCompoundDrawables(null,changeBtnTop(R.drawable.favorite_normal_50),null,null);
				Util.showToast(InfoDetail.this,"已取消");
				isfolder=false;
				loadingGoodsDetailData(id);
			}
			if (msg.what == 0x142) {
				//
//				Toast.makeText(InfoDetail.this, "点赞失败", Toast.LENGTH_SHORT)
//				.show();
			}
			if (msg.what == 0x143) {
				//
				Toast.makeText(InfoDetail.this, "举报成功!", Toast.LENGTH_SHORT);
				isjubao=true;
				loadingGoodsDetailData(id);
//				.show();
			}	if (msg.what == 0x144) {
				//
//				Toast.makeText(InfoDetail.this, "点赞失败", Toast.LENGTH_SHORT)
//				.show();
			}
			if (msg.what == 0x121) {
				//
				Toast.makeText(InfoDetail.this, "报名成功", Toast.LENGTH_SHORT)
				.show();
				loadingGoodsDetailData(id);
			}
			if (msg.what == 0x122) {
				//
				Toast.makeText(InfoDetail.this, "报名失败", Toast.LENGTH_SHORT)
				.show();
			}
			if (msg.what == 0x155) {
				//
				Toast.makeText(InfoDetail.this, "请勿重复报名", Toast.LENGTH_SHORT)
				.show();
			}
			if (msg.what == 0x123) {
				//
				Toast.makeText(InfoDetail.this, "收藏成功", Toast.LENGTH_SHORT)
				.show();
				shoucang1.setCompoundDrawables(null,changeBtnTop(R.drawable.favorite_red_50),null,null);
				loadingGoodsDetailData(id);
			}
			if (msg.what == 0x124) {
				//
				Toast.makeText(InfoDetail.this, "收藏失败", Toast.LENGTH_SHORT)
				.show();
			}
			if (msg.what == 0x111) {
				//
				try {
					JSONObject obj = new JSONObject(jsonString);
					String jsonStr = obj.optString("jsonString");
					System.out.println(jsonStr);
					JSONObject goods = new JSONObject(jsonStr);
					count_ = goods.optInt("count_");
					author = goods.optString("author");
					String type = goods.optString("type");
					count_1 = goods.optInt("count_1");
					if(!type.equals("2")){
						title11.setText( goods.optString("title"));
						content.setText(goods.optString("content"));
					}else{
						title11.setText(goods.optString("title")+"\n已有"+count_1+"人报名\n地点:"+goods.optString("huodong_addr"));
						content.setText("年级:"+goods.optString("age")+"\n"+goods.optString("content"));
					}

					add_history(author,id,goods.optString("title"),myApp.getLoginName(),myApp.getLoginKey());
					zan.setText("赞("+goods.optString("zan")+")");
					shoucang1.setText("收藏("+goods.optString("favorite")+")");
					pinglun1.setText("评价("+goods.optString("comments")+")");
					share1.setText("分享");
					jubao1.setText("举报("+goods.optString("jubao")+")");
					sixin1.setText("私信("+goods.optString("message")+")");

//						zan.setCompoundDrawables(null,changeBtnTop(R.drawable.like_red_50),null,null);



					username.setText("作者:" + goods.optString("author"));
					address=goods.optString("huodong_addr");
					status = goods.optString("status");
					huodongage = goods.optString("age");
					String imageurl_ = goods.optString("image_url").split(
							"\\\\")[1];
					file_link = HttpUtil.URL_BASEUPLOAD + imageurl_;
					String imageurl = HttpUtil.URL_BASEUPLOAD + imageurl_;

					image_url_w=imageurl;
					conntent_w=goods.optString("title")+"\n"+goods.optString("content");
					
//					MyBackAsynaTask asynaTask = new MyBackAsynaTask(imageurl,
//							imageView1);

					Glide.with(InfoDetail.this)
							.load(imageurl)
							.into(imageView1);
				/*	if(status.equals("0")){
						play.setVisibility(View.VISIBLE);
					}else{
						play.setVisibility(View.GONE);
					}*/
//					asynaTask.execute();
					time.setText("发布日期：" + goods.optString("pubdate"));
					zuobiao = goods.optString("zuobiao");

					checkfolder(id);
					checkjubao(id);
					checkmsg(id);
					checkpingjia(id);
					checkzan(id);
					loaddata();

//					loadingCommentsData(id);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			if (msg.what == 0x112) {
				//
			}
		};
	};
	public void checkzan(final String goods_id) {


		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_CHECKZAN + goods_id+"&zan.userid="+myApp.getLoginKey();


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					try {
						JSONObject obj = new JSONObject(result);
						String jsonStr = obj.optString("jsonString");
						if(jsonStr.equals("1")){

							handler.sendEmptyMessage(0x131);
						}else{
							handler.sendEmptyMessage(0x132);
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
	public void jubao(final String goods_id) {


		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_JUBAO + goods_id+"&jubao.userid="+myApp.getLoginKey();


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					try {
						JSONObject obj = new JSONObject(result);
						String jsonStr = obj.optString("jsonString");
						if(jsonStr.equals("1")){

							handler.sendEmptyMessage(0x143);
						}else{
							handler.sendEmptyMessage(0x144);
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
	public void cancelzan(final String goods_id) {


		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_CANCELZAN + goods_id+"&zan.userid="+myApp.getLoginKey();


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					try {
						JSONObject obj = new JSONObject(result);
						String jsonStr = obj.optString("jsonString");
						if(jsonStr.equals("1")){

							handler.sendEmptyMessage(0x137);
						}else{
							handler.sendEmptyMessage(0x138);
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
	public void checkfolder(final String goods_id) {


		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_CHECKFOLDER + goods_id+"&folder.userid="+myApp.getLoginKey();


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					try {
						JSONObject obj = new JSONObject(result);
						String jsonStr = obj.optString("jsonString");
						if(jsonStr.equals("1")){

							handler.sendEmptyMessage(0x133);
						}else{
							handler.sendEmptyMessage(0x134);
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
	public void checkpingjia(final String goods_id) {


		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_CHECKPINGJIA + goods_id+"&comments.userid="+myApp.getLoginKey();


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					try {
						JSONObject obj = new JSONObject(result);
						String jsonStr = obj.optString("jsonString");
						if(jsonStr.equals("1")){

							handler.sendEmptyMessage(0x135);
						}else{
							handler.sendEmptyMessage(0x136);
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
	public void checkmsg(final String goods_id) {


		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_CHECKMSG + goods_id+"&message.userid="+myApp.getLoginKey();


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					try {
						JSONObject obj = new JSONObject(result);
						String jsonStr = obj.optString("jsonString");
						if(jsonStr.equals("1")){

							handler.sendEmptyMessage(0x145);
						}else{
							handler.sendEmptyMessage(0x146);
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
	public void checkjubao(final String goods_id) {


		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_CHECKJUBAO + goods_id+"&jubao.userid="+myApp.getLoginKey();


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					try {
						JSONObject obj = new JSONObject(result);
						String jsonStr = obj.optString("jsonString");
						if(jsonStr.equals("1")){

							handler.sendEmptyMessage(0x147);
						}else{
							handler.sendEmptyMessage(0x148);
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
	public void dianzan(final String goods_id) {
		
		
		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_ZANDIAN + goods_id+"&zan.userid="+myApp.getLoginKey();


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

							try {
								JSONObject obj = new JSONObject(result);
								String jsonStr = obj.optString("jsonString");
								if(jsonStr.equals("1")){
									
									handler.sendEmptyMessage(0x128);
								}else{
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
	public void baoming(final String goods_id) {
		
		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_BAOMING + "baoming.bioid="+goods_id+"&baoming.userid="+myApp.getLoginKey()+"&baoming.username="+myApp.getLoginName();



					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);
							try {
								JSONObject obj = new JSONObject(result);
								String jsonStr = obj.optString("jsonString");
								if(jsonStr.equals("1")){
								
									handler.sendEmptyMessage(0x121);
								}else if(jsonStr.equals("3")){
									handler.sendEmptyMessage(0x155);
								}else{
									handler.sendEmptyMessage(0x122);
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
	public void checkbaoming(final String bioid) {
		
		final boolean[] value = new boolean[1];
		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_CHECKBAOMING + "bioid="+bioid+"&userid="+myApp.getLoginKey();


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

							
							try {
								JSONObject obj = new JSONObject(result);
								String jsonStr = obj.optString("jsonString");
								if(jsonStr.equals("1")){
									value[0]=true;
									isbbaoming=true;
								}else{
									value[0]=false;
									isbbaoming=false;
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
	public void shoucang(final String goods_id) {
		
		
		
		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_SHOUCANG +"folder.duanziid="+goods_id+"&folder.userid="+myApp.getLoginKey();


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);
							try {
								JSONObject obj = new JSONObject(result);
								String jsonStr = obj.optString("jsonString");
								if(jsonStr.equals("1")){
									handler.sendEmptyMessage(0x123);
								}else{
					
									handler.sendEmptyMessage(0x124);
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


	public void add_history(final String author,final String bioid,final String title
			,final String username,final String userid) {



		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_ADD_HISTORY
							+"history.author="+author
							+"&history.bioid="+bioid
							+"&history.title="+title
							+"&history.userid="+userid
							+"&history.username="+username;


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);
							try {
								JSONObject obj = new JSONObject(result);
								String jsonStr = obj.optString("jsonString");
								if(jsonStr.equals("1")){
									handler.sendEmptyMessage(0x158);
								}else{

									handler.sendEmptyMessage(0x159);
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


				public void cancelshoucang(final String goods_id) {



		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_CANCELSHOUCANG +"folder.duanziid="+goods_id+"&folder.userid="+myApp.getLoginKey();


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);
							try {
								JSONObject obj = new JSONObject(result);
								String jsonStr = obj.optString("jsonString");
								if(jsonStr.equals("1")){
									handler.sendEmptyMessage(0x141);
								}else{

									handler.sendEmptyMessage(0x142);
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

		loadingGoodsDetailData(id);

	}

	public void loadingGoodsDetailData(final String goods_id) {
		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_BIODETAIL + goods_id;


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);
							jsonString=result;
							handler.sendEmptyMessage(0x111);

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}
	
	
}
