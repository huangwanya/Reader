package com.common.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blueberry.activity.R;
import com.common.bean.CommentsList;
import com.common.bean.DuanziBean;
import com.common.bean.messageList;
import com.common.util.BaseCallback;
import com.common.util.HttpUtil;
import com.common.util.MyApp;
import com.common.util.Util;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.orhanobut.dialogplus.DialogPlus;

import net.travel.android.common.MyCommentsDialog;
import net.travel.android.common.MyMSGDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MessageList extends Activity {
	@ViewInject(R.id.listView1)
	private ListView listView1;
	private List<messageList> basemarkBeans = new ArrayList<messageList>();
	@ViewInject(R.id.add_new_xd)
	private TextView add_new_xd;
	private MyApp myApp;
	private GameAdapter adapter;
	private String jsonString,itemid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApp) MessageList.this.getApplication();
		setContentView(R.layout.message_list);
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
		Util.toIntent(MessageList.this, AddMessage.class);
	}

	public void getGameXD() {
		basemarkBeans=new ArrayList<>();
		new Thread() {
			public void run() {
				try {
					String url = HttpUtil.URL_MESSAGELISTBYAUTHOR+myApp.getLoginName();
					

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

				ArrayList<messageList> goods_list = messageList
						.newInstanceList(jsonString);
				basemarkBeans.addAll(goods_list);
				adapter.notifyDataSetChanged();
			}
			if (msg.what == 0x127) {
				//
				
			}
			if (msg.what == 0x128) {
				//
				Toast.makeText(MessageList.this, "操作成功",
						Toast.LENGTH_SHORT).show();
				getGameXD();
			}
			if (msg.what == 0x129) {
				//
				Toast.makeText(MessageList.this, "操作失败",
						Toast.LENGTH_SHORT).show();
			}


			if (msg.what == 0x151) {
				//
				Util.showToast(MessageList.this,"回复成功");

				mCommentsDialog.dismiss();

				getGameXD();
			}if (msg.what == 0x152) {
				//
			}
		};
	};

	public void del(final String bioid) {


		new Thread() {
			public void run() {
				try {

					String url = HttpUtil.URL_DEL_MSG+"message.id="+bioid;


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
	private class GameAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<DuanziBean> duanziList;

		private GameAdapter() {
			inflater = LayoutInflater.from(MessageList.this);
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
						.inflate(R.layout.message_list_item, null);
				holder = new Holder();
				holder.content = (TextView) convertView.findViewById(R.id.content);
				holder.time = (TextView) convertView.findViewById(R.id.times);
				holder.username = (TextView) convertView
						.findViewById(R.id.username);
				holder.reply = (TextView) convertView
						.findViewById(R.id.reply);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
//			holder.time.setText("反馈时间:"+basemarkBeans.get(position).getCommitdate());
//			holder.username.setText("反馈人:"+basemarkBeans.get(position).getUsername());
			holder.content.setText(basemarkBeans.get(position).getUsername()+"在"+basemarkBeans.get(position).getCommitdate()+",\n对"+basemarkBeans.get(position).getAuthor()+"说：\n"+basemarkBeans.get(position).getContent());
			if(basemarkBeans.get(position).getReply()!=null && !basemarkBeans.get(position).getReply().equals("")&&!basemarkBeans.get(position).getReply().equals("null")){
				holder.reply.setVisibility(View.VISIBLE);
				holder.reply.setText(basemarkBeans.get(position).getAuthor()+"进行了回复：\n"+basemarkBeans.get(position).getReply());
			}else{
				holder.reply.setVisibility(View.GONE);
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
		TextView content;
		TextView time;
		TextView username;
		TextView reply;
	}
	/**
	 * 评论dialog
	 */
	private DialogPlus mCommentsDialog;
	@OnItemClick(R.id.listView1)
	public void onItemClick(AdapterView<?> parent, View view, final int position,
							long id) {
		// TODO Auto-generated method stub
		itemid=basemarkBeans.get(position).getId();

		if(basemarkBeans.get(position).getReply()!=null && !basemarkBeans.get(position).getReply().equals("")&&!basemarkBeans.get(position).getReply().equals("null")){
			Util.showToast(MessageList.this,"无需再次答复!");
		}else{
			if(basemarkBeans.get(position).getAuthor().equals(myApp.getLoginName())){
				MyMSGDialog dialog1 = new MyMSGDialog(MessageList.this);
				mCommentsDialog = dialog1.createDialog(commentsCallback);
				mCommentsDialog.show();
			}else{
				Util.showToast(MessageList.this,"私聊对方方可答复!");
			}


		}

	}

	private BaseCallback commentsCallback = new BaseCallback() {
		@Override
		public void sendMessage(final Object obj) {
			addXD(obj.toString());

		}
	};

	public void addXD(final String content) {


		new Thread() {
			public void run() {
				try {


					String url = HttpUtil.URL_MESSAGEREPLY;
					String query ="";
					query+="message.content="+ URLEncoder.encode(content,"utf-8")+"&";
					query+="message.id="+itemid+"";

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
}
