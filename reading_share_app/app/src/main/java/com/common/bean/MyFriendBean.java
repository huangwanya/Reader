/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.model
 * FileNmae:GoodsList.java
 */
package com.common.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 */
public class MyFriendBean {
		public static class Attr{
			public static final String TYPE_ID = "id";
			public static final String TYPE_USER1 = "userid1";
			public static final String TYPE_USER2 = "userid2";
			public static final String TYPE_STATUS = "status";
			public static final String TYPE_BLACKSTATUS = "black_status";
			public static final String TYPE_NAME1 = "username1";
			
			public static final String TYPE_ADDRESS1 = "address1";
			public static final String QQNUM1 = "qqnum1";
			public static final String PHONE1 = "phone1";
			public static final String AGE1 = "age1";
			public static final String IMAGEURL1 = "image_url1";
			
			
			
			
			
			public static final String TYPE_NAME2 = "username2";
			
			public static final String TYPE_ADDRESS2 = "address2";
			public static final String QQNUM2 = "qqnum2";
			public static final String PHONE2 = "phone2";
			public static final String AGE2 = "age2";
			public static final String IMAGEURL2 = "image_url2";
		
		}
		private String id;
		private String userid1;
		private String userid2;
		private String status;
		private String black_status;
		private String username1;
		private String username2;

	private String qqnum1;
	private String phone1;
	private String address1;
	private String age1;
	private String image_url1;

	private String qqnum2;
	private String phone2;
	private String address2;
	private String age2;
	private String image_url2;
		
		public MyFriendBean() {
		}
		


		public MyFriendBean(String id, String userid1, String userid2,
                            String status, String username1, String address1,
                            String qqnum1, String phone1, String age1,
                             String address2, String qqnum2,
                            String phone2, String age2,String image_url1, String image_url2, String username2,String black_status) {
			super();
			this.id = id;
			this.userid1 = userid1;
			this.userid2 = userid2;
			this.status = status;
			this.username1 = username1;
			this.address1 = address1;
			this.qqnum1 = qqnum1;
			this.phone1 = phone1;
			this.age1 = age1;
			this.address2 = address2;
			this.qqnum2 = qqnum2;
			this.phone2 = phone2;
			this.age2 = age2;
			this.image_url2 = image_url2;
			this.image_url1 = image_url1;
			this.username2 = username2;
			this.black_status = black_status;
		}















		/**
		 * 
		 * 
	
		private String address1;
		private String qqnum1;
		private String phone1;
		private String age1;
		private String gerenshuoming1;
	
	
	
	
		 */


		public static ArrayList<MyFriendBean> newInstanceList(String jsonDatas){
			ArrayList<MyFriendBean> AdvertDatas = new ArrayList<MyFriendBean>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String type_id = obj.optString(Attr.TYPE_ID);
					String userid1 = obj.optString(Attr.TYPE_USER1);
					String userid2 = obj.optString(Attr.TYPE_USER2);
					String status = obj.optString(Attr.TYPE_STATUS);
					String username1 = obj.optString(Attr.TYPE_NAME1);
					String address1 = obj.optString(Attr.TYPE_ADDRESS1);
					String qqnum1 = obj.optString(Attr.QQNUM1);
					String phone1 = obj.optString(Attr.PHONE1);
					String age1 = obj.optString(Attr.AGE1);
					String image_url1 = obj.optString(Attr.IMAGEURL1);
					
					String username2 = obj.optString(Attr.TYPE_NAME2);
					
					String address2 = obj.optString(Attr.TYPE_ADDRESS2);
					String qqnum2 = obj.optString(Attr.QQNUM2);
					String phone2 = obj.optString(Attr.PHONE2);
					String age2 = obj.optString(Attr.AGE2);
					String image_url2 = obj.optString(Attr.IMAGEURL2);
					String black_status = obj.optString(Attr.TYPE_BLACKSTATUS);

					
					/**
					 * 
					 * 
						public MyFriendBean(String id, String userid1, String userid2,
				String status, String username1, String address1,
				String qqnum1, String phone1, String age1,
				String gerenshuoming1, String address2, String qqnum2,
				String phone2, String age2, String image_url2) {
					 * 
					 */
				
					MyFriendBean bean =new MyFriendBean(type_id,userid1,userid2,status
							,username1,address1,qqnum1,phone1,age1,address2,qqnum2,phone2,age2,image_url1,image_url2,username2,black_status);
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











		public String getUserid1() {
			return userid1;
		}











		public void setUserid1(String userid1) {
			this.userid1 = userid1;
		}











		public String getUserid2() {
			return userid2;
		}











		public void setUserid2(String userid2) {
			this.userid2 = userid2;
		}











		public String getStatus() {
			return status;
		}











		public void setStatus(String status) {
			this.status = status;
		}











		public String getUsername1() {
			return username1;
		}











		public void setUsername1(String username1) {
			this.username1 = username1;
		}



		public String getAddress1() {
			return address1;
		}



		public void setAddress1(String address1) {
			this.address1 = address1;
		}



		public String getqqnum1() {
			return qqnum1;
		}



		public void setqqnum1(String qqnum1) {
			this.qqnum1 = qqnum1;
		}



		public String getphone1() {
			return phone1;
		}



		public void setphone1(String phone1) {
			this.phone1 = phone1;
		}


	public String getUsername2() {
		return username2;
	}

	public void setUsername2(String username2) {
		this.username2 = username2;
	}

	public String getQqnum1() {
		return qqnum1;
	}

	public void setQqnum1(String qqnum1) {
		this.qqnum1 = qqnum1;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getAge1() {
		return age1;
	}

	public void setAge1(String age1) {
		this.age1 = age1;
	}

	public String getImage_url1() {
		return image_url1;
	}

	public void setImage_url1(String image_url1) {
		this.image_url1 = image_url1;
	}

	public String getQqnum2() {
		return qqnum2;
	}

	public void setQqnum2(String qqnum2) {
		this.qqnum2 = qqnum2;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAge2() {
		return age2;
	}

	public void setAge2(String age2) {
		this.age2 = age2;
	}

	public String getImage_url2() {
		return image_url2;
	}

	public void setImage_url2(String image_url2) {
		this.image_url2 = image_url2;
	}

	public String getBlack_status() {
		return black_status;
	}

	public void setBlack_status(String black_status) {
		this.black_status = black_status;
	}
}
