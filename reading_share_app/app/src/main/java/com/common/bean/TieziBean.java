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

import android.widget.TextView;

/**
 * @author KingKong·HE
 * @Time 2014年1月17日 下午4:44:35
 */
public class TieziBean {
		public static class Attr{
			/**
			 * 
			 * 	TextView ck_name;
				TextView ck_phone;
				TextView ck_status;
				TextView ck_group;
				TextView ck_gws;
				TextView ck_content;
			 */
			public static final String TYPE_ID = "id";
			public static final String TYPE_NAME = "title";
			public static final String TYPE_STATUS = "status";
			public static final String TYPE_ZAN = "zan";
			public static final String TYPE_ZUOBIAO = "content";
			public static final String TYPE_AUTHOR = "author";
			public static final String TYPE_IMAGE = "image_url";
			public static final String TYPE_DESC = "pubdate";
			public static final String TYPE_TYPE = "type2";
			
			public static final String PRO_USER = "proj_user";
			public static final String PRO_GROUP = "proj_group";
			public static final String PRO_NAME = "proj_name";
			public static final String PRO_CONTENT = "proj_content";
			
			public static final String CK_NAME = "ck_name";
			public static final String CK_PHONE = "ck_phone";
			public static final String CK_STATUS = "ck_status";
			public static final String CK_GROUP = "ck_group";
			public static final String CK_GWS = "ck_gws";
			public static final String CK_CONTENT = "ck_content";
		
		}
		private String id;
		private String title;
		private String content;
		private String status;
		private String zan;
		private String author;
		private String image_url;
		private String updatetime;
	
		private String proj_user;
		private String proj_group;
		private String proj_name;
		private String proj_content;
		
		private String ck_name;
		private String ck_phone;
		private String ck_status;
		private String ck_group;
		private String ck_gws;
		private String ck_content;
		private String type2;
		
		
		public TieziBean() {
		}

		public TieziBean(String id, String title, String content,
				String image_url, String updatetime,String status
				,String author,String zan,String proj_user,String proj_group,String proj_name,String proj_content
				,String ck_name,String ck_phone,String ck_status,String ck_group,String ck_gws,String ck_content
				,String type2) {
			super();
			this.id = id;
			this.title = title;
			this.content = content;
			this.image_url = image_url;
			this.updatetime = updatetime;
			this.status =status;
			this.author = author;
			this.zan= zan;
			this.proj_user= proj_user;
			this.proj_group= proj_group;
			this.proj_name= proj_name;
			this.proj_content= proj_content;
			
			this.ck_name = ck_name;
			this.ck_phone= ck_phone;
			this.ck_status= ck_status;
			this.ck_group= ck_group;
			this.ck_gws= ck_gws;
			this.ck_content= ck_content;
			this.type2= type2;
		}

		public static ArrayList<TieziBean> newInstanceList(String jsonDatas){
			ArrayList<TieziBean> AdvertDatas = new ArrayList<TieziBean>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String type_id = obj.optString(Attr.TYPE_ID);
					String title = obj.optString(Attr.TYPE_NAME);
					String content = obj.optString(Attr.TYPE_ZUOBIAO);
					String type_image = obj.optString(Attr.TYPE_IMAGE);
					String updatetime = obj.optString(Attr.TYPE_DESC);
					String status = obj.optString(Attr.TYPE_STATUS);
					String zan = obj.optString(Attr.TYPE_ZAN);
					String author = obj.optString(Attr.TYPE_AUTHOR);
				
					String proj_user = obj.optString(Attr.PRO_USER);
					String proj_group = obj.optString(Attr.PRO_GROUP);
					String proj_name = obj.optString(Attr.PRO_NAME);
					String proj_content = obj.optString(Attr.PRO_CONTENT);
					
					String ck_name = obj.optString(Attr.CK_NAME);
					String ck_phone = obj.optString(Attr.CK_PHONE);
					String ck_status = obj.optString(Attr.CK_STATUS);
					String ck_group = obj.optString(Attr.CK_GROUP);
					String ck_gws = obj.optString(Attr.CK_GWS);
					String ck_content = obj.optString(Attr.CK_CONTENT);
					String type2 = obj.optString(Attr.TYPE_TYPE);
					
					TieziBean bean =new TieziBean(type_id,title,content,type_image,updatetime,status
							,author,zan,proj_user,proj_group,proj_name,proj_content
							,ck_name,ck_phone,ck_status,ck_group,ck_gws,ck_content,type2
							);
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
















		public String getType2() {
			return type2;
		}

		public void setType2(String type2) {
			this.type2 = type2;
		}

		public String getCk_name() {
			return ck_name;
		}

		public void setCk_name(String ck_name) {
			this.ck_name = ck_name;
		}

		public String getCk_phone() {
			return ck_phone;
		}

		public void setCk_phone(String ck_phone) {
			this.ck_phone = ck_phone;
		}

		public String getCk_status() {
			return ck_status;
		}

		public void setCk_status(String ck_status) {
			this.ck_status = ck_status;
		}

		public String getCk_group() {
			return ck_group;
		}

		public void setCk_group(String ck_group) {
			this.ck_group = ck_group;
		}

		public String getCk_gws() {
			return ck_gws;
		}

		public void setCk_gws(String ck_gws) {
			this.ck_gws = ck_gws;
		}

		public String getCk_content() {
			return ck_content;
		}

		public void setCk_content(String ck_content) {
			this.ck_content = ck_content;
		}

		public String getProj_user() {
			return proj_user;
		}
















		public void setProj_user(String proj_user) {
			this.proj_user = proj_user;
		}
















		public String getProj_group() {
			return proj_group;
		}
















		public void setProj_group(String proj_group) {
			this.proj_group = proj_group;
		}
















		public String getProj_name() {
			return proj_name;
		}
















		public void setProj_name(String proj_name) {
			this.proj_name = proj_name;
		}
















		public String getProj_content() {
			return proj_content;
		}
















		public void setProj_content(String proj_content) {
			this.proj_content = proj_content;
		}
















		public String getZan() {
			return zan;
		}
















		public void setZan(String zan) {
			this.zan = zan;
		}
















		public String getTitle() {
			return title;
		}
















		public String getAuthor() {
			return author;
		}
















		public void setAuthor(String author) {
			this.author = author;
		}
















		public String getStatus() {
			return status;
		}
















		public void setStatus(String status) {
			this.status = status;
		}
















		public void setTitle(String title) {
			this.title = title;
		}
















		public String getContent() {
			return content;
		}
















		public void setContent(String content) {
			this.content = content;
		}
















		public String getImage_url() {
			return image_url;
		}
















		public void setImage_url(String image_url) {
			this.image_url = image_url;
		}
















		public String getUpdatetime() {
			return updatetime;
		}
















		public void setUpdatetime(String updatetime) {
			this.updatetime = updatetime;
		}


















		

}
