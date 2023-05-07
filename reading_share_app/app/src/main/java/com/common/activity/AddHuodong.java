package com.common.activity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
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
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.blueberry.activity.R;
import com.common.bean.ShetuanBean;
import com.common.util.BasicFuns;
import com.common.util.BitmapUtil;
import com.common.util.FileUtil;
import com.common.util.FormFile;
import com.common.util.HttpUtil;
import com.common.util.IAsynTask;
import com.common.util.MyApp;
import com.common.util.SendGETRequest;
import com.common.util.SocketHttpRequester;
import com.common.util.Util;
import com.common.util.Web;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddHuodong extends Activity    implements View.OnTouchListener{
	@ViewInject(R.id.gamename)
	private EditText gamename;
	@ViewInject(R.id.huodong_count)
	private EditText huodong_count;
	@ViewInject(R.id.huodong_date)
	private EditText huodong_date;
	@ViewInject(R.id.editage)
	private Spinner editage;
	@ViewInject(R.id.huodong_addr)
	private EditText huodong_addr;
	@ViewInject(R.id.gamecontext)
	private TextView gamecontext;
	@ViewInject(R.id.hours)
	private TextView hours;
	@ViewInject(R.id.zuobiao)
	private TextView zuobiao_;
	@ViewInject(R.id.imageMyAvator)
	private CircleImageView imageMyAvator;
	private String jsonString;
	@ViewInject(R.id.edittype)
	private Spinner edittype;
	private MyApp myApp;
	  private List<ShetuanBean> basemarkBeans = new ArrayList<ShetuanBean>();
		private GameAdapter adapter;
	// 创建一个以当前时间为名称的文件
	public static File tempFile = new File(
			Environment.getExternalStorageDirectory(),
			FileUtil.getPhotoFileName());


	private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
	private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 3;// 结果

	Bitmap mBitmap = null;
	
    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private LocationMode mCurrentMode;
    // UI相关
    OnCheckedChangeListener radioButtonListener;
    Button requestLocButton;
    boolean isFirstLoc = true; // 是否首次定位
    private String zuobiao;

	private String path;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) AddHuodong.this.getApplication();
		/*StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());*/
		setContentView(R.layout.add_game_xd);
		ViewUtils.inject(this);
		huodong_date.setOnTouchListener(this);
		   // 定位初始化
/*        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标分类
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();*/
        
    	/*adapter = new GameAdapter();
		edittype.setAdapter(adapter);
		 getGameXD();*/
	}
	
	public void getGameXD() {
		
		new Thread() {
			public void run() {
				try {
					
					String url = HttpUtil.URL_GOODSTYPELIST_;
					

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
									
									
									
									handler.sendEmptyMessage(0x126);
								} else {
									handler.sendEmptyMessage(0x127);
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}

						}
					} catch (ClientProtocolException e) {
						e.printStackTrace();
						result = "网络异常！";
						Toast.makeText(AddHuodong.this, result, Toast.LENGTH_SHORT).show();
						;
					} catch (IOException e) {
						e.printStackTrace();
						result = "网络异常！";
						Toast.makeText(AddHuodong.this, result, Toast.LENGTH_SHORT).show();
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

				Toast.makeText(AddHuodong.this, "发布成功!", Toast.LENGTH_LONG)
						.show();
				finish();
			}
			
			if (msg.what == 0x126) {
				//
				basemarkBeans = new ArrayList<ShetuanBean>();
				ArrayList<ShetuanBean> goods_list = ShetuanBean
						.newInstanceList(jsonString);
				basemarkBeans.addAll(goods_list);
				edittype.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
			}
			if (msg.what == 0x127) {
				//
				basemarkBeans.addAll(new ArrayList<ShetuanBean>());
				edittype.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
			}
		};
	};
	@OnClick({ R.id.add_new_xd, R.id.top_back, R.id.imageMyAvator })
	public void onclick(View view) {
		switch (view.getId()) {
		case R.id.add_new_xd:
			if (TextUtils.isEmpty(gamename.getText().toString().trim())) {
				gamename.setError("标题不得为空");
				return;
			}
			if (TextUtils.isEmpty(gamecontext.getText().toString().trim())) {
				gamecontext.setError("内容不得为空");
				return;
			}
			if (TextUtils.isEmpty(hours.getText().toString().trim())) {
				hours.setError("时间不得为空");
				return;
			}
			addXD();
			break;

		case R.id.top_back:
			finish();
			break;
		case R.id.imageMyAvator:
			showDialog();
			break;
		}
	}
	
	
	// 提示对话框方法
	private void showDialog() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		/* 开启Pictures画面Type设定为image */
		intent.setType("image/*");
		/* 使用Intent.ACTION_GET_CONTENT这个Action */
		intent.setAction(Intent.ACTION_GET_CONTENT);
		/* 取得相片后返回本画面 */
		startActivityForResult(intent, 1);
			
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) { 
			   
            AlertDialog.Builder builder = new AlertDialog.Builder(this); 
            View view = View.inflate(this, R.layout.date_time_dialog, null); 
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker); 
            final TimePicker timePicker = (android.widget.TimePicker) view.findViewById(R.id.time_picker); 
            timePicker.setIs24HourView(true);
            builder.setView(view); 
   
            Calendar cal = Calendar.getInstance(); 
            cal.setTimeInMillis(System.currentTimeMillis()); 
            datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null); 
   
   
            if (v.getId() == R.id.huodong_date) { 
                final int inType = huodong_date.getInputType(); 
                huodong_date.setInputType(InputType.TYPE_NULL); 
                huodong_date.onTouchEvent(event); 
                huodong_date.setInputType(inType); 
                huodong_date.setSelection(huodong_date.getText().length()); 
               
                builder.setTitle("设置日期"); 
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() { 
   
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
   
                        StringBuffer sb = new StringBuffer(); 
                        sb.append(String.format("%d-%02d-%02d",  
                                datePicker.getYear(),  
                                datePicker.getMonth() + 1, 
                                datePicker.getDayOfMonth())); 
   
                        huodong_date.setText(sb); 
//                        editEndTime.requestFocus(); 
                           
                        dialog.cancel(); 
                    } 
                }); 
                   
            } 
               
            Dialog dialog = builder.create(); 
            dialog.show(); 
        } 
   
        return true; 
	}
    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null ) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(15.0f);
                
                zuobiao=location.getLongitude()+","+location.getLatitude();
                zuobiao_.setText("当前坐标:"+zuobiao);
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
	/**
	 * 添加新的游戏心得
	 */
	public void addXD() {
		final String title = gamename.getText().toString();
		final String content = gamecontext.getText().toString();
		final String huodong_date_ = huodong_date.getText().toString();
		final String huodong_addr_ = huodong_addr.getText().toString();
		final String huodong_count_ = huodong_count.getText().toString();
		final String age = editage.getSelectedItem().toString().trim();
		final String hours_ = hours.getText().toString();
		final String author = myApp.getLoginName();
		
		
		final String actionUrl = HttpUtil.URL_BIO_ADD;
		new Thread() {
			public void run() {
				try {

					try {
						// 请求普通信息
						Map<String, String> params = new HashMap<String, String>();
						// params.put("filename", "张三");
			    		params.put("biotech.title", URLEncoder.encode(title));
			    		params.put("biotech.content", URLEncoder.encode(content));
			    		params.put("biotech.author", URLEncoder.encode(author));
			    		params.put("biotech.author", URLEncoder.encode(author));
			    		params.put("biotech.huodong_date", URLEncoder.encode(huodong_date_));
			    		params.put("biotech.huodong_addr", URLEncoder.encode(huodong_addr_));
			    		//params.put("biotech.zuobiao", URLEncoder.encode(zuobiao));
			    		params.put("biotech.hours", hours_);
			    		params.put("biotech.age", age);
			    		params.put("biotech.count_", huodong_count_);
			    		params.put("biotech.type", 2+"");
			    		params.put("biotech.type_id", myApp.getShetuanid());
						File imageFile = new File(path);
						FormFile formfile = new FormFile(imageFile.getName(),
								imageFile, "biotech.file",
								"application/octet-stream");
						SocketHttpRequester.post(actionUrl, params, formfile);

					} catch (Exception e) {
						Toast.makeText(AddHuodong.this, "上传失败!",
								Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}

					handler.sendEmptyMessage(0x125);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Uri uri = data.getData();
			Log.e("uri", uri.toString());
			// path =uri.toString();
			/*
			 * ContentResolver cr = this.getContentResolver(); try { Bitmap
			 * bitmap = BitmapFactory.decodeStream(cr .openInputStream(uri));
			 * 将Bitmap设定到ImageView imageMyAvator.setImageBitmap(bitmap); } catch
			 * (FileNotFoundException e) { Log.e("Exception", e.getMessage(),
			 * e); }
			 */

			try {
				String[] pojo = { MediaStore.Images.Media.DATA };
				Cursor cursor = managedQuery(uri, pojo, null, null, null);
				ContentResolver cr = this.getContentResolver();
				if (cursor != null) {

					int colunm_index = cursor
							.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToFirst();
					String path1 = cursor.getString(colunm_index);
					/***
					 * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
					 * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
					 */
					if (path1.endsWith("jpg") || path1.endsWith("png")|| path1.endsWith("jpeg")) {
						path = path1;
						Bitmap bitmap = BitmapFactory.decodeStream(cr
								.openInputStream(uri));
						imageMyAvator.setImageBitmap(bitmap);
					} else {
						//alert();
					}
				} else {
					//alert();
					String path1 = uri.getPath();
					/***
					 * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
					 * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
					 */
					if (path1.endsWith("jpg") || path1.endsWith("png")|| path1.endsWith("jpeg")) {
						path = path1;
						Bitmap bitmap = BitmapFactory.decodeStream(cr
								.openInputStream(uri));
						imageMyAvator.setImageBitmap(bitmap);
					}
				}

			} catch (Exception e) {

			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}


	// 将进行剪裁后的图片显示到UI界面上
	private void setPicToView(Intent picdata) {
		Bundle bundle = picdata.getExtras();
		if (bundle != null) {
			Bitmap photo = bundle.getParcelable("data");
			mBitmap = photo;
			String uriString = System.currentTimeMillis() + "";
			BitmapUtil.saveBitmapFile(photo, uriString);
			imageMyAvator.setImageBitmap(photo);
			path = "/sdcard/pic/" + uriString + ".jpg";

		}
	}
	
	
	private class GameAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<ShetuanBean> duanziList;

		private GameAdapter() {
			inflater = LayoutInflater.from(AddHuodong.this);
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
						.inflate(R.layout.type_list_item_, null);
				holder = new Holder();
				holder.title = (TextView) convertView.findViewById(R.id.title);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			
			holder.title.setText(basemarkBeans.get(position).getName());
			
			
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
	}
}
