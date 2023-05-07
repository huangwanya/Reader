package com.common.activity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URLEncoder;
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
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.blueberry.activity.R;
import com.common.bean.ShetuanBean;
import com.common.util.BasicFuns;
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

@SuppressLint("NewApi") public class AddTiezi extends Activity {
	@ViewInject(R.id.gamename)
	private EditText gamename;
	@ViewInject(R.id.gamecontext)
	private TextView gamecontext;
	@ViewInject(R.id.imageMyAvator)
	private CircleImageView imageMyAvator;
	@ViewInject(R.id.edittype)
	private Spinner edittype;
	private MyApp myApp;
	private String jsonString;
	private String path;
    private List<ShetuanBean> basemarkBeans = new ArrayList<ShetuanBean>();
	private GameAdapter adapter;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) AddTiezi.this.getApplication();
		/*
		 * StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		 * .detectDiskReads().detectDiskWrites().detectNetwork()
		 * .penaltyLog().build()); StrictMode.setVmPolicy(new
		 * StrictMode.VmPolicy.Builder()
		 * .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
		 * .penaltyLog().penaltyDeath().build());
		 */
		setContentView(R.layout.add_tiezi);
		ViewUtils.inject(this);
		
		adapter = new GameAdapter();
		edittype.setAdapter(adapter);
		 getGameXD();
	}
	public void getGameXD() {
		
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
	@OnClick({ R.id.add_new, R.id.top_back, R.id.imageMyAvator })
	public void onclick(View view) {
		switch (view.getId()) {
		case R.id.add_new:
			if (TextUtils.isEmpty(gamename.getText().toString().trim())) {
//				Util.Toast(this, "请输入标题");
				gamename.setError("请输入标题");
				return;
			}
			if (TextUtils.isEmpty(gamecontext.getText().toString().trim())) {
//				Util.Toast(this, "请输入内容");
				gamecontext.setError("请输入内容");
				return;
			}
			addXD();
			break;

		case R.id.top_back:
			finish();
			break;
		case R.id.imageMyAvator:
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			/* 开启Pictures画面Type设定为image */
			intent.setType("image/*");
			/* 使用Intent.ACTION_GET_CONTENT这个Action */
			intent.setAction(Intent.ACTION_GET_CONTENT);
			/* 取得相片后返回本画面 */
			startActivityForResult(intent, 1);
			break;
		}
	}

	/**
	 * 添加新的游戏心得
	 */
	public void addXD() {

	/*	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);*/
		final String title = gamename.getText().toString();
		final String content = gamecontext.getText().toString();
		final String type = ((ShetuanBean)edittype.getSelectedItem()).getName();
		String author = myApp.getLoginName();
		
		
		
		Intent intent = new Intent(AddTiezi.this, MainActivity.class);
		startActivity(intent);
		finish();
		final String actionUrl = HttpUtil.URL_BIO_ADD_;
		new Thread() {
			public void run() {
				try {

					try {
						// 请求普通信息
						Map<String, String> params = new HashMap<String, String>();
						// params.put("filename", "张三");
						params.put("biotech.title", title);
			    		params.put("biotech.content", content);
			    		params.put("biotech.author", myApp.getLoginName());
			    		params.put("biotech.type2", type);
			    		params.put("biotech.type", 0+"");
			    		params.put("biotech.type_id", ((ShetuanBean)edittype.getSelectedItem()).getId());

						File imageFile = new File(path);
						FormFile formfile = new FormFile(imageFile.getName(),
								imageFile, "biotech.file",
								"application/octet-stream");
						SocketHttpRequester.post(actionUrl, params, formfile);

					} catch (Exception e) {
						Toast.makeText(AddTiezi.this, "上传失败!",
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

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x125) {
				//

				Toast.makeText(AddTiezi.this, "发布成功!", Toast.LENGTH_LONG)
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

	
	private class GameAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<ShetuanBean> duanziList;

		private GameAdapter() {
			inflater = LayoutInflater.from(AddTiezi.this);
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
