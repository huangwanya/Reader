/**
 * ProjectName:
 * PackageName:net.shopnc.android.model
 * FileNmae:GoodsList.java
 */
package com.common.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author KingKong·HE
 * @Time 2021 下午4:44:35
 */
public class CommentsList {
		public static class Attr{
			public static final String COMMENT_USERNAME = "username";
			public static final String COMMENT_DATE = "cdate";
			public static final String COMMENT_COMENT = "content";
			public static final String COMMENT_TOUSERNAME = "tousername";
			public static final String COMMENT_REPLY = "reply";
			
			
		
		}
		private String username;
		private String commitdate;
		private String content;
		private String tousername;
		private String reply;
		
		public CommentsList() {
		}
		














		public CommentsList(String username, String commitdate, String content,String tousername,String reply) {
			super();
			this.username = username;
			this.commitdate = commitdate;
			this.content = content;
			this.tousername = tousername;
			this.reply = reply;
		}















		public static ArrayList<CommentsList> newInstanceList(String jsonDatas){
			ArrayList<CommentsList> AdvertDatas = new ArrayList<CommentsList>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String username = obj.optString(Attr.COMMENT_USERNAME);
					String commitdate = obj.optString(Attr.COMMENT_DATE);
					String content = obj.optString(Attr.COMMENT_COMENT);
					String tousername = obj.optString(Attr.COMMENT_TOUSERNAME);
					String reply = obj.optString(Attr.COMMENT_REPLY);
					CommentsList bean =new CommentsList(username,commitdate,content,tousername,reply);
					AdvertDatas.add(bean);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return AdvertDatas;
		}















		public String getUsername() {
			return username;
		}















		public void setUsername(String username) {
			this.username = username;
		}















		public String getTousername() {
			return tousername;
		}















		public void setTousername(String tousername) {
			this.tousername = tousername;
		}















		public String getReply() {
			return reply;
		}















		public void setReply(String reply) {
			this.reply = reply;
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















		@Override
		public String toString() {
			return "CommentsList [username=" + username + ", commitdate="
					+ commitdate + ", content=" + content + "]";
		}















		

}
