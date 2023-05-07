package com.common.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blueberry.activity.R;
import com.common.adapter.GoodsDetailCommentsListViewAdapter;
import com.common.bean.SignBean;
import com.common.util.HttpUtil;
import com.common.util.MyApp;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class CommentsList extends Activity {
	@ViewInject(R.id.listView1)
	private ListView listView1;
	@ViewInject(R.id.add_new_xd)
	private TextView add_new_xd;
	@ViewInject(R.id.commentEdit)
	private EditText commentEdit;
	@ViewInject(R.id.commentButton)
	private EditText commentButton;
	private boolean ispinglun;
	private MyApp myApp;
	private GoodsDetailCommentsListViewAdapter gooddetailcommentsAdapter;
	private ArrayList<com.common.bean.CommentsList> lists = new ArrayList<com.common.bean.CommentsList>();
	private String bioid;
	private String jsonString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) CommentsList.this.getApplication();
		setContentView(R.layout.comments_list);
		ViewUtils.inject(this);
		add_new_xd.setVisibility(View.INVISIBLE);
		gooddetailcommentsAdapter = new GoodsDetailCommentsListViewAdapter(CommentsList.this);
		listView1.setAdapter(gooddetailcommentsAdapter);
		bioid=getIntent().getStringExtra("bioid");
		ispinglun=getIntent().getBooleanExtra("flag",false);
//		listView1.setAdapter(adapter);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		loadingCommentsData(bioid);
		super.onResume();

	}

	@OnClick(R.id.top_back)
	public void back(View view) {
		finish();
	}

	@OnClick(R.id.commentButton)
	public void commentButton(View view) {

		String comments_=commentEdit.getText().toString();
		if(comments_==null || comments_.equals("")){
			commentEdit.setError("消息不能为空!");
			return;
		}
		if(!ispinglun){
			addXD(comments_);
		}else{
			commentEdit.setError("您已评论过了，不要重复评论!");
			return;
		}

//		finish();
	}

	public void addXD(final String content) {


		new Thread() {
			public void run() {
				try {


					String url = HttpUtil.URL_COMMENTSADD;
					String query ="";
					query+="comment="+ URLEncoder.encode(content,"utf-8")+"&";
					query+="luxianid="+bioid+"&";
					query+="userid="+myApp.getLoginKey();

					url+=query;


					String result = null;
					result = HttpUtil.queryStringConnectForPost(url);

					try {
						JSONObject obj = new JSONObject(result);
						String arrlist = obj.optString("jsonString");
						// JSONObject obj = new JSONObject(json);
						if(arrlist.equals("1")){
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

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x126) {
				//
				ArrayList<com.common.bean.CommentsList> goods_list = com.common.bean.CommentsList
						.newInstanceList(jsonString);
				lists.addAll(goods_list);

				gooddetailcommentsAdapter.setGoodsDatas(goods_list);
				gooddetailcommentsAdapter.notifyDataSetChanged();
//				loaddata();
			}
			if (msg.what == 0x127) {
				//

			}
			if (msg.what == 0x128) {
				Toast.makeText(CommentsList.this, "评论成功", Toast.LENGTH_SHORT).show();;
				finish();
			}
			if (msg.what == 0x129) {
			}
		};
	};
	public void loadingCommentsData(final String id) {

		new Thread() {
			public void run() {
				try {

					lists =new ArrayList<com.common.bean.CommentsList>();
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
}
