/**
 * ProjectName:
 * PackageName:net.shopnc.android.model
 * FileNmae:Login.java
 */
package com.common.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author KingKong·HE
 */
public class ShetuanBean {
		public static class Attr{
			public static final String ID = "id";
			public static final String NAME = "name";
			public static final String CONTENT = "content";
		}
		
		private String id;
		private String name;
		private String content;
		
		public ShetuanBean() {
		}




		public ShetuanBean(String id, String name, String content) {
			super();
			this.id = id;
			this.name = name;
			this.content = content;
		}




		public static ArrayList<ShetuanBean> newInstanceList(String jsonDatas){
			ArrayList<ShetuanBean> AdvertDatas = new ArrayList<ShetuanBean>();
			
			try {
				JSONArray arr = new JSONArray(jsonDatas);
				int size = null == arr ? 0 : arr.length();
				System.out.println("size-->" + size);
				for(int i = 0; i < size; i++){
					JSONObject obj = arr.getJSONObject(i);
					String id = obj.optString(Attr.ID);
					String name = obj.optString(Attr.NAME);
					String content = obj.optString(Attr.CONTENT);
					ShetuanBean bean =new ShetuanBean(id,name,content);
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




		public void setName(String name) {
			this.name = name;
		}




		public String getContent() {
			return content;
		}




		public void setContent(String content) {
			this.content = content;
		}


}
