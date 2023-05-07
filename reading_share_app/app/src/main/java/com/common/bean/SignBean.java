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
 * @Time 2021下午4:44:35
 */
public class SignBean {
		public static class Attr{
			public static final String TYPE_ID = "id";
			public static final String TYPE_NAME = "username";
			public static final String TYPE_HOURS = "signdate";
		
		}
		private String id;
		private String username;
		private String signdate;
		
		public SignBean() {
		}
		





		public SignBean(String id, String username, String signdate) {
			super();
			this.id = id;
			this.username = username;
			this.signdate = signdate;
	
		}






		public static ArrayList<SignBean> newInstanceList(String jsonDatas){
			ArrayList<SignBean> AdvertDatas = new ArrayList<SignBean>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String id = obj.optString(Attr.TYPE_ID);
					String username = obj.optString(Attr.TYPE_NAME);
					String signdate = obj.optString(Attr.TYPE_HOURS);
					SignBean bean =new SignBean(id,username,signdate);
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






		public String getSigndate() {
			return signdate;
		}






		public void setSigndate(String signdate) {
			this.signdate = signdate;
		}






		

}
