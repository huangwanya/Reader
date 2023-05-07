package com.common.activity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import com.common.bean.ShetuanBean;
import com.common.bean.TieziBean;
import com.common.util.BitmapCache;
import com.common.util.HttpUtil;
import com.common.util.IAsynTask;
import com.common.util.MyApp;
import com.common.util.MyBackAsynaTask;
import com.common.util.SendGETRequest;
import com.common.util.SystemHelper;
import com.common.util.Util;
import com.common.util.Web;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShetuanList extends Activity {
	@ViewInject(R.id.listView1)
	private GridView listView1;

	@ViewInject(R.id.listView2)
	private ListView listView2;

	@ViewInject(R.id.listView3)
	private ListView listView3;

	@ViewInject(R.id.listView4)
	private ListView listView4;

	private List<ShetuanBean> basemarkBeans = new ArrayList<ShetuanBean>();
	private List<DuanziBean> basemarkBeans2 = new ArrayList<DuanziBean>();
	private List<TieziBean> basemarkBeans3 = new ArrayList<TieziBean>();
	private List<TieziBean> basemarkBeans4 = new ArrayList<TieziBean>();

	@ViewInject(R.id.add_new_xd)
	private ImageView add_new_xd;
	@ViewInject(R.id.clickmore2)
	private ImageView clickmore2;

	@ViewInject(R.id.clickmore3)
	private ImageView clickmore3;

	@ViewInject(R.id.clickmore4)
	private ImageView clickmore4;

	@ViewInject(R.id.textView2)
	private TextView textView2;
	private MyApp myApp;
	private GameAdapter adapter;
	private GameAdapter2 adapter2;
	private GameAdapter3 adapter3;
	private GameAdapter4 adapter4;
	private int type;
	private String jsonString;
	@ViewInject(R.id.scrollview_)
	private ScrollView scrollview_;

	private LinearLayout ll_point;
	private ArrayList<View> arrayList;
	private ArrayList<ImageView> imageViews;
	private Timer timer;
	private LayoutInflater HeadlayoutInflater;
	private ViewPager viewPager;
	private int i;
	private int count;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) ShetuanList.this.getApplication();
		setContentView(R.layout.shetuan_list);
		ViewUtils.inject(this);
	/*	if (TextUtils.isEmpty(myApp.getLoginName())) {
			add_new_xd.setVisibility(View.INVISIBLE);
		}*/
/*		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());*/

		adapter = new GameAdapter();
		listView1.setAdapter(adapter);

		adapter2 = new GameAdapter2();
		listView2.setAdapter(adapter2);


		adapter3 = new GameAdapter3();
		listView3.setAdapter(adapter3);

		adapter4 = new GameAdapter4();
		listView4.setAdapter(adapter4);


		loadingHomeData();


		listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				Intent intent = new Intent(ShetuanList.this, HuodongList.class);
				intent.putExtra("type", basemarkBeans.get(position).getId());
				startActivity(intent);
			}
		});
		listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				Intent intent = new Intent(ShetuanList.this, InfoDetail.class);
				intent.putExtra("id", basemarkBeans2.get(position).getId());
				intent.putExtra("tag", "通知公告");
				startActivity(intent);
			}
		});
		listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				Intent intent = new Intent(ShetuanList.this, InfoDetail.class);
				intent.putExtra("id", basemarkBeans3.get(position).getId());
				intent.putExtra("tag", "社区分享");
				startActivity(intent);
			}
		});
		listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				Intent intent = new Intent(ShetuanList.this, InfoDetail.class);
				intent.putExtra("id", basemarkBeans4.get(position).getId());
				intent.putExtra("tag", "阅读精选");
				startActivity(intent);
			}
		});



//		handler = new Handler();

	}
	private Runnable runnable = new Runnable() {

		@Override
		public void run() {
			scrollview_.fullScroll(ScrollView.FOCUS_UP);
		}
	};
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub


		getGameXD();

		super.onResume();

	}
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x125) {
				//
				ArrayList<ShetuanBean> goods_list = ShetuanBean
						.newInstanceList(jsonString);
				basemarkBeans.addAll(goods_list);
				adapter.notifyDataSetChanged();

				//自动调整gridview高度
				Util.setGridViewMatchParent(listView1);

				getGameXD2();

				handler.postDelayed(runnable, 200);
			}

			if (msg.what == 0x126) {
				//
				ArrayList<DuanziBean> goods_list = DuanziBean
						.newInstanceList(jsonString);
				basemarkBeans2.addAll(goods_list);
				adapter2.notifyDataSetChanged();
				//自动调整gridview高度
				Util.setListViewMatchParent(listView2);

				getGameXD3();
			}
			if (msg.what == 0x127) {
				basemarkBeans2.addAll(new ArrayList<DuanziBean>());
				adapter2.notifyDataSetChanged();
			}
			if (msg.what == 0x128) {

				ArrayList<TieziBean> goods_list = TieziBean
						.newInstanceList(jsonString);
				basemarkBeans3.addAll(goods_list);
				adapter3.notifyDataSetChanged();
				Util.setListViewMatchParent(listView3);

				getGameXD4();
			}
			if (msg.what == 0x129) {
			}

			if (msg.what == 0x131) {

				ArrayList<TieziBean> goods_list = TieziBean
						.newInstanceList(jsonString);
				basemarkBeans4.addAll(goods_list);
				adapter4.notifyDataSetChanged();
				Util.setListViewMatchParent(listView4);

			}
			if (msg.what == 0x132) {
			}
		};
	};
	@OnClick(R.id.top_back)
	public void back(View view) {
		finish();
	}

	@OnClick(R.id.add_new_xd)
	public void addNew(View view) {
		Intent intent =new Intent(ShetuanList.this,MainActivity.class);
		startActivity(intent);
	}
	@OnClick(R.id.clickmore2)
	public void clickmore2(View view) {
		Intent intent =new Intent(ShetuanList.this,XinwenList.class);
		startActivity(intent);
	}
	@OnClick(R.id.clickmore3)
	public void clickmore3(View view) {
		Intent intent =new Intent(ShetuanList.this,TieziList.class);
		startActivity(intent);
	}

	@OnClick(R.id.clickmore4)
	public void clickmore4(View view) {
		Intent intent =new Intent(ShetuanList.this,JiudianList.class);
		startActivity(intent);
	}
	public void loadingHomeData() {
		initHeadImage();
	}
	/***
	 * 初始化 HeadImage
	 */
	public void initHeadImage() {
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		ll_point = (LinearLayout) findViewById(R.id.ll_point);
		initPagerChild();
		int sw = SystemHelper.getScreenInfo(ShetuanList.this).widthPixels;
		int sh = SystemHelper.getScreenInfo(ShetuanList.this).heightPixels;
		int h = 350;
		RelativeLayout.LayoutParams childLinerLayoutParames = new RelativeLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, h);
		viewPager.setLayoutParams(childLinerLayoutParames);

		viewPager.setAdapter(new ViewPagerAdapter(arrayList));
		draw_Point(0);// 默认首次进入

		timer = new Timer(true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						int index = viewPager.getCurrentItem();
						if (index == arrayList.size() - 1)
							index = 0;
						else
							index++;
						viewPager.setCurrentItem(index);

					}
				});
			}
		}, 5000, 5000);

		/***
		 * viewpager PageChangeListener
		 */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				draw_Point(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	/***
	 * 更新选中点
	 *
	 * @param index
	 */
	public void draw_Point(int index) {
		for (int i = 0; i < imageViews.size(); i++) {
			imageViews.get(i).setImageResource(R.drawable.point_gray);
		}
		imageViews.get(index).setImageResource(R.drawable.point_red);
	}

	/***
	 * 对图片处理
	 *
	 * @author zhangjia
	 */
	public Bitmap getBitmap(Bitmap bitmap, int width) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scale = (float) width / w;
		// 保证图片不变形.
		matrix.postScale(scale, scale);
		// w,h是原图的属性.
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}

	/***
	 * 初始化 PagerChild
	 */
	@SuppressLint("NewApi")
	public void initPagerChild() {
		arrayList = new ArrayList<View>();
		ImageView imageView0 = new ImageView(ShetuanList.this);
		imageView0.setScaleType(ImageView.ScaleType.FIT_XY);
		imageView0.setImageResource(R.drawable.banner01);

		ImageView imageView1 = new ImageView(ShetuanList.this);
		imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
		imageView1.setImageResource(R.drawable.banner02);

		ImageView imageView2 = new ImageView(ShetuanList.this);
		imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
		imageView2.setImageResource(R.drawable.banner03);


		arrayList.add(imageView0);
		arrayList.add(imageView1);
		arrayList.add(imageView2);
		initPoint();
	}

	public void initPoint() {
		imageViews = new ArrayList<ImageView>();
		ImageView imageView;

		for (i = 0; i < 3; i++) {
			imageView = new ImageView(this);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
							ViewGroup.LayoutParams.WRAP_CONTENT));
			layoutParams.leftMargin = 10;
			layoutParams.rightMargin = 10;
			ll_point.addView(imageView, layoutParams);

			imageViews.add(imageView);
		}
	}
	public class ViewPagerAdapter extends PagerAdapter {
		// 界面列表
		private List<View> views;

		public ViewPagerAdapter(List<View> views) {
			this.views = views;
		}

		// 销毁arg1位置的界面
		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(views.get(arg1));
		}

		// 获得当前界面数
		@Override
		public int getCount() {
			if (views != null) {
				// 返回一个比较大的数字
				return views.size();
			}
			return 0;
		}

		// 初始化arg1位置的界面
		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(views.get(arg1));
			return views.get(arg1);
		}

		// 判断是否由对象生成界面
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return (arg0 == arg1);
		}
	}


	public void getGameXD2() {


		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_XINWENLIST;
					url = HttpUtil.URL_LIST0_HEAD;
//					textView2.setText("通知公告");
					basemarkBeans2=new ArrayList<DuanziBean>();


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

	public void getGameXD() {

		basemarkBeans =new ArrayList<>();
		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_GOODSTYPELIST_;


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
	@SuppressLint("NewApi") public void getGameXD3() {

		new Thread() {
			public void run() {
				try {

					basemarkBeans3 = new ArrayList<TieziBean>();
					/*		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
									.permitAll().build();
							StrictMode.setThreadPolicy(policy);*/
					String url = HttpUtil.URL_DUANZILISTBYTYPE2_HEAD;


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					try {
						JSONObject obj = new JSONObject(result);
						String arrlist = obj.optString("jsonString");
						// JSONObject obj = new JSONObject(json);
						if (arrlist != "" && !arrlist.equals("arrlist")
								&& arrlist != null && !arrlist.equals("[]")) {
							jsonString =arrlist;



							handler.sendEmptyMessage(0x128);
						} else {
							handler.sendEmptyMessage(0x129);
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

	@SuppressLint("NewApi") public void getGameXD4() {

		new Thread() {
			public void run() {
				try {

					basemarkBeans4 = new ArrayList<TieziBean>();
					/*		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
									.permitAll().build();
							StrictMode.setThreadPolicy(policy);*/
					String url = HttpUtil.URL_DUANZILISTBYTYPE4_HEAD;


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					try {
						JSONObject obj = new JSONObject(result);
						String arrlist = obj.optString("jsonString");
						// JSONObject obj = new JSONObject(json);
						if (arrlist != "" && !arrlist.equals("arrlist")
								&& arrlist != null && !arrlist.equals("[]")) {
							jsonString =arrlist;



							handler.sendEmptyMessage(0x131);
						} else {
							handler.sendEmptyMessage(0x132);
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
	private class GameAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<ShetuanBean> duanziList;

		private GameAdapter() {
			inflater = LayoutInflater.from(ShetuanList.this);
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
						.inflate(R.layout.type_list_item, null);
				holder = new Holder();
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.content = (TextView) convertView.findViewById(R.id.content);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			
			holder.title.setText(basemarkBeans.get(position).getName());
			holder.content.setText(basemarkBeans.get(position).getContent());
			
			
			return convertView;
		}

		public ArrayList<ShetuanBean> getDuanziList() {
			return duanziList;
		}

		public void setDuanziList(ArrayList<ShetuanBean> duanziList) {
			this.duanziList = duanziList;
		}


	}

	private class Holder {
		TextView title;
		TextView content;
	}



	private class GameAdapter2 extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<DuanziBean> duanziList;

		private GameAdapter2() {
			inflater = LayoutInflater.from(ShetuanList.this);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return basemarkBeans2.size();
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
			ShetuanList.Holder2 holder;
			if (convertView == null) {
				convertView = inflater
						.inflate(R.layout.game_xd_list_item, null);
				holder = new ShetuanList.Holder2();
				holder.image_view = (CircleImageView) convertView
						.findViewById(R.id.image_view);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.time = (TextView) convertView.findViewById(R.id.times);
				holder.username = (TextView) convertView
						.findViewById(R.id.username);
				holder.status = (TextView) convertView.findViewById(R.id.status);
				convertView.setTag(holder);
			} else {
				holder = (ShetuanList.Holder2) convertView.getTag();
			}
			holder.time.setText("发布时间:"
					+ basemarkBeans2.get(position).getUpdatetime());
			holder.username.setText("发布人:"
					+ basemarkBeans2.get(position).getAuthor());
			holder.title
					.setText( basemarkBeans2.get(position).getTitle());
			holder.status .setVisibility(View.GONE);
			if(basemarkBeans2.get(position).getStatus2().equals("0")){
				holder.status .setText("未通过审核");
			}
			if(basemarkBeans2.get(position).getStatus2().equals("1")){
				holder.status .setText("已通过审核");
				holder.status .setTextColor(R.color.green);
			}
			String imagename = basemarkBeans2.get(position).getImage_url()
					.split("\\\\")[1];
//			MyBackAsynaTask asynaTask = new MyBackAsynaTask(
//					HttpUtil.URL_BASEUPLOAD + imagename, holder.image_view);
//			asynaTask.execute();

			Glide.with(ShetuanList.this)
					.load(HttpUtil.URL_BASEUPLOAD + imagename)
					.into(holder.image_view);
			return convertView;
		}

		public ArrayList<DuanziBean> getDuanziList() {
			return duanziList;
		}

		public void setDuanziList(ArrayList<DuanziBean> duanziList) {
			this.duanziList = duanziList;
		}

	}

	private class Holder2 {
		CircleImageView image_view;
		TextView title;
		TextView time;
		TextView username;
		TextView status;
	}


	private class GameAdapter3 extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<DuanziBean> duanziList;
		private RequestQueue queue;
		private ImageLoader imageLoader;
		private GameAdapter3() {
			inflater = LayoutInflater.from(ShetuanList.this);
			queue = Volley.newRequestQueue(ShetuanList.this);
			imageLoader = new ImageLoader(queue, new BitmapCache());
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return basemarkBeans3.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return basemarkBeans3.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ShetuanList.Holder3 holder;
			String imageurl=HttpUtil.URL_BASEUPLOAD+basemarkBeans3.get(position).getImage_url()
					.split("\\\\")[1];
			if (convertView == null) {
				convertView = inflater
						.inflate(R.layout.game_xd_list_item, null);
				holder = new ShetuanList.Holder3();
				holder.image_view = (CircleImageView) convertView
						.findViewById(R.id.image_view);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.time = (TextView) convertView.findViewById(R.id.times);
				holder.status = (TextView) convertView.findViewById(R.id.status);
				holder.username = (TextView) convertView
						.findViewById(R.id.username);
				convertView.setTag(holder);
			} else {
				holder = (ShetuanList.Holder3) convertView.getTag();
			}
			holder.time.setText("发布时间:"
					+ basemarkBeans3.get(position).getUpdatetime());
			holder.username.setText("发布人:"
					+ basemarkBeans3.get(position).getAuthor());

			holder.status.setVisibility(View.GONE);
			holder.title
					.setText( basemarkBeans3.get(position).getTitle()+"\n分类:"+basemarkBeans3.get(position).getType2());








			if (imageurl != null && !imageurl.equals("")) {
				String imagename = basemarkBeans3.get(position).getImage_url()
						.split("\\\\")[1];
//			MyBackAsynaTask asynaTask = new MyBackAsynaTask(
//					HttpUtil.URL_BASEUPLOAD + imagename, holder.image_view);
//			asynaTask.execute();

				Glide.with(ShetuanList.this)
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

	private class Holder3 {
		CircleImageView image_view;
		TextView title;
		TextView time;
		TextView status;
		TextView username;
	}

	private class GameAdapter4 extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<DuanziBean> duanziList;
		private RequestQueue queue;
		private ImageLoader imageLoader;
		private GameAdapter4() {
			inflater = LayoutInflater.from(ShetuanList.this);
			queue = Volley.newRequestQueue(ShetuanList.this);
			imageLoader = new ImageLoader(queue, new BitmapCache());
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return basemarkBeans4.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return basemarkBeans4.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ShetuanList.Holder4 holder;
			String imageurl=HttpUtil.URL_BASEUPLOAD+basemarkBeans4.get(position).getImage_url()
					.split("\\\\")[1];
			if (convertView == null) {
				convertView = inflater
						.inflate(R.layout.game_xd_list_item, null);
				holder = new ShetuanList.Holder4();
				holder.image_view = (CircleImageView) convertView
						.findViewById(R.id.image_view);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.time = (TextView) convertView.findViewById(R.id.times);
				holder.status = (TextView) convertView.findViewById(R.id.status);
				holder.username = (TextView) convertView
						.findViewById(R.id.username);
				convertView.setTag(holder);
			} else {
				holder = (ShetuanList.Holder4) convertView.getTag();
			}
			holder.time.setText("发布时间:"
					+ basemarkBeans4.get(position).getUpdatetime());
			holder.username.setText("发布人:"
					+ basemarkBeans4.get(position).getAuthor());

			holder.status.setVisibility(View.GONE);
			holder.title
					.setText( basemarkBeans4.get(position).getTitle());








			if (imageurl != null && !imageurl.equals("")) {
				String imagename = basemarkBeans4.get(position).getImage_url()
						.split("\\\\")[1];
//			MyBackAsynaTask asynaTask = new MyBackAsynaTask(
//					HttpUtil.URL_BASEUPLOAD + imagename, holder.image_view);
//			asynaTask.execute();

				Glide.with(ShetuanList.this)
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

	private class Holder4 {
		CircleImageView image_view;
		TextView title;
		TextView time;
		TextView status;
		TextView username;
	}
}
