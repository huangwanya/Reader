/**
 * ProjectName:
 * PackageName:net.shopnc.android.model
 * FileNmae:GoodsList.java
 */
package com.common.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author KingKong·HE
 * @Time 2021下午4:44:35
 */
public class HistoryBean {
		public static class Attr{
			public static final String TYPE_ID = "id";
			public static final String TYPE_BIOID = "bioid";
			public static final String TYPE_TITLE = "title";
			public static final String TYPE_AUTHOR = "author";
			public static final String TYPE_USERID = "userid";
			public static final String TYPE_UPDATEDATE = "updatedate";
			public static final String TYPE_USERNAME = "username";

		}
		private String id;
		private String bioid;
		private String title;
		private String author;
		private String userid;
		private String updatedate;
		private String username;

		public HistoryBean() {
		}






		public HistoryBean(String id, String bioid, String title, String author
				, String userid, String updatedate, String username) {
			super();
			this.id = id;
			this.bioid = bioid;
			this.title = title;
			this.author = author;
			this.userid = userid;
			this.updatedate = updatedate;
			this.username = username;

		}
		public static ArrayList<HistoryBean> newInstanceList(String jsonDatas){
			ArrayList<HistoryBean> AdvertDatas = new ArrayList<HistoryBean>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String id = obj.optString(Attr.TYPE_ID);
					String bioid = obj.optString(Attr.TYPE_BIOID);
					String title = obj.optString(Attr.TYPE_TITLE);
					String author = obj.optString(Attr.TYPE_AUTHOR);
					String userid = obj.optString(Attr.TYPE_USERID);
					String updatedate = obj.optString(Attr.TYPE_UPDATEDATE);
					String username = obj.optString(Attr.TYPE_USERNAME);
					HistoryBean bean =new HistoryBean(id,bioid,title,author,userid,updatedate,username);
					AdvertDatas.add(bean);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}



		public String getId() {
			return id;
		}



		public void setId(String id) {
			this.id = id;
		}






		public String getUsername() {
			return username;
		}






		public void setUsername(String username) {
			this.username = username;
		}


	public String getBioid() {
		return bioid;
	}

	public void setBioid(String bioid) {
		this.bioid = bioid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
}
