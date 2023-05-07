/**
 */
package com.common.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author KingKong·HE
 */
public class LoginBean {
		public static class Attr{
			public static final String KEY = "key";
			public static final String USERNAME = "username";
			public static final String PWD = "password";
			public static final String NAME = "name";
			public static final String STATUS = "status";
			public static final String HOURS = "hours";
			public static final String SHETUANID = "shetuan_id";
			public static final String ADDRESS = "address";
		}
		
		private String key;
		private String username;
		private String password;
		private String name;
		private String hours;
		private String address;
		private String shetuan_id;
		private String status;
		
		public LoginBean() {
		}


		public LoginBean(String key, String username, String name, String hours, String address, String status, String shetuan_id, String password) {
			super();
			this.key = key;
			this.username = username;
			this.name = name;
			this.hours = hours;
			this.address = address;
			this.status = status;
			this.shetuan_id = shetuan_id;
			this.password = password;
		}


		public static LoginBean  newInstanceList(String json){
			LoginBean bean = null;
			try {
				JSONObject obj = new JSONObject(json);
				String userstr = obj.optString("jsonString");
				JSONObject user = new JSONObject(userstr);
				
				
//				System.out.println(obj.optString("username"));
				if(obj.length()> 0){
					String username = user.optString("username");
					String name = user.optString("name");
					String key = user.optString("id");
					String hours = user.optString("hours");
					String address = user.optString("address");
					String status = user.optString("status");
					String shetuan_id = user.optString("shetuan_id");
					String password = user.optString("password");
					 bean = new LoginBean(key, username,name,hours,address,status,shetuan_id,password);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return bean;
		}


		public String getKey() {
			return key;
		}


		public void setKey(String key) {
			this.key = key;
		}


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getHours() {
			return hours;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public void setHours(String hours) {
			this.hours = hours;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		public String getShetuan_id() {
			return shetuan_id;
		}


		public void setShetuan_id(String shetuan_id) {
			this.shetuan_id = shetuan_id;
		}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
		public String toString() {
			return "Login [key=" + key + ", username=" + username + "]";
		}
		
}
