/**
 *
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
 */
public class messageList {
		public static class Attr{
			public static final String COMMENT_ID = "id";
			public static final String COMMENT_USERID = "userid";
			public static final String COMMENT_USERNAME = "username";
			public static final String COMMENT_AUTHOR = "author";
			public static final String COMMENT_DATE = "cdate";
			public static final String COMMENT_COMENT = "content";
			public static final String COMMENT_REPLY = "reply";

		}
		private String id;
		private String userid;
		private String username;
		private String commitdate;
		private String content;
		private String reply;
		private String author;

		public messageList() {
		}
		














		public messageList(String id,String userid,String username, String commitdate, String content, String reply, String author) {
			super();
			this.id = id;
			this.userid = userid;
			this.username = username;
			this.commitdate = commitdate;
			this.content = content;
			this.reply = reply;
			this.author = author;
		}















		public static ArrayList<messageList> newInstanceList(String jsonDatas){
			ArrayList<messageList> AdvertDatas = new ArrayList<messageList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String id = obj.optString(Attr.COMMENT_ID);
					String userid = obj.optString(Attr.COMMENT_USERID);
					String username = obj.optString(Attr.COMMENT_USERNAME);
					String commitdate = obj.optString(Attr.COMMENT_DATE);
					String content = obj.optString(Attr.COMMENT_COMENT);
					String reply = obj.optString(Attr.COMMENT_REPLY);
					String author = obj.optString(Attr.COMMENT_AUTHOR);
					messageList bean =new messageList(id,userid,username,commitdate,content,reply,author);
					AdvertDatas.add(bean);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUsername() {
			return username;
		}















		public void setUsername(String username) {
			this.username = username;
		}















		public String getCommitdate() {
			return commitdate;
		}















		public void setCommitdate(String commitdate) {
			this.commitdate = commitdate;
		}















		public String getContent() {
			return content;
		}















		public void setContent(String content) {
			this.content = content;
		}


	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
		public String toString() {
			return "CommentsList [username=" + username + ", commitdate="
					+ commitdate + ", content=" + content + "]";
		}















		

}
