/**
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
public class FriendBean {
		public static class Attr{
			public static final String TYPE_ID = "id";
			public static final String TYPE_NAME = "age";
			public static final String TYPE_HOURS = "hours";
			public static final String TYPE_USER = "username";
			public static final String TYPE_QQ = "qqnum";
			public static final String TYPE_PHONE = "phone";
			public static final String TYPE_ADDRESS = "address";
			public static final String TYPE_STATUS = "baoming_status";
			public static final String TYPE_IMAGE = "image_url";

		}
		private String id;
		private String name;
		private String qqnum;
		private String phone;
		private String address;
		private String username;
		private String hours;
		private String baoming_status;
		private String image_url;

		public FriendBean() {
		}
		





		public FriendBean(String id, String name, String qqnum, String phone,
				String address, String username, String hours, String baoming_status, String image_url) {
			super();
			this.id = id;
			this.name = name;
			this.qqnum = qqnum;
			this.phone = phone;
			this.address = address;
			this.username = username;
			this.hours = hours;
			this.baoming_status = baoming_status;
			this.image_url = image_url;
		}






		public static ArrayList<FriendBean> newInstanceList(String jsonDatas){
			ArrayList<FriendBean> AdvertDatas = new ArrayList<FriendBean>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String type_id = obj.optString(Attr.TYPE_ID);
					String name = obj.optString(Attr.TYPE_NAME);
					String qq = obj.optString(Attr.TYPE_QQ);
					String phone = obj.optString(Attr.TYPE_PHONE);
					String address = obj.optString(Attr.TYPE_ADDRESS);
					String username = obj.optString(Attr.TYPE_USER);
					String hours = obj.optString(Attr.TYPE_HOURS);
					String baoming_status = obj.optString(Attr.TYPE_STATUS);
					String image_url = obj.optString(Attr.TYPE_IMAGE);
					FriendBean bean =new FriendBean(type_id,name,qq,phone,address,username,hours,baoming_status,image_url);
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






		public String getName() {
			return name;
		}






		public String getHours() {
			return hours;
		}

	public String getBaoming_status() {
		return baoming_status;
	}

	public void setBaoming_status(String baoming_status) {
		this.baoming_status = baoming_status;
	}

	public void setHours(String hours) {
			this.hours = hours;
		}






		public void setName(String name) {
			this.name = name;
		}






		public String getQqnum() {
			return qqnum;
		}






		public void setQqnum(String qqnum) {
			this.qqnum = qqnum;
		}






		public String getPhone() {
			return phone;
		}






		public String getUsername() {
			return username;
		}






		public void setUsername(String username) {
			this.username = username;
		}






		public void setPhone(String phone) {
			this.phone = phone;
		}






		public String getAddress() {
			return address;
		}


	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public void setAddress(String address) {
			this.address = address;
		}



		

}
