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
 */
public class DuanziBean {
	public static class Attr {
		/**
		 * 
		 * TextView ck_name; TextView ck_phone; TextView ck_status; TextView
		 * ck_group; TextView ck_gws; TextView ck_content;
		 */
		public static final String TYPE_ID = "id";
		public static final String TYPE_NAME = "title";
		public static final String TYPE_STATUS = "status";
		public static final String TYPE_STATUS2 = "status2";
		public static final String TYPE_ZAN = "zan";
		public static final String TYPE_ZUOBIAO = "content";
		public static final String TYPE_AUTHOR = "author";
		public static final String TYPE_IMAGE = "image_url";
		public static final String TYPE_DESC = "pubdate";
		public static final String TYPE_HOURS = "hours";
		public static final String TYPE_TYPE = "type";
		public static final String TYPE_DATE = "huodong_date";
		public static final String TYPE_ADDR = "huodong_addr";

		public static final String PRO_USER = "proj_user";
		public static final String PRO_GROUP = "proj_group";
		public static final String PRO_NAME = "proj_name";
		public static final String PRO_CONTENT = "proj_content";
		public static final String PRO_STATUS = "proj_status";

		public static final String CK_NAME = "count_";
		public static final String CK_PHONE = "count_1";
		public static final String CK_STATUS = "ck_status";
		public static final String CK_GROUP = "ck_group";
		public static final String CK_GWS = "ck_gws";
		public static final String CK_CONTENT = "ck_content";
		public static final String CK_AGE= "age";

	}

	private String id;
	private String title;
	private String content;
	private String status;
	private String status2;
	private String zan;
	private String author;
	private String image_url;
	private String updatetime;
	private String huodong_date;
	private String huodong_addr;

	private String proj_user;
	private String proj_group;
	private String proj_name;
	private String proj_content;
	private String proj_status;

	private String ck_name;
	private String ck_phone;
	private String ck_status;
	private String ck_group;
	private String ck_gws;
	private String ck_content;
	private String hours;
	private String type;
	private String age;

	public DuanziBean() {
	}

	public DuanziBean(String id, String title, String content,
			String image_url, String updatetime, String status, String author,
			String zan, String proj_user, String proj_group, String proj_name,
			String proj_content, String ck_name, String ck_phone,
			String ck_status, String ck_group, String ck_gws,
			String ck_content, String proj_status, String hours, String type
			, String huodong_date, String huodong_addr, String status2, String age
			) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.image_url = image_url;
		this.updatetime = updatetime;
		this.status = status;
		this.status2 = status2;
		this.author = author;
		this.zan = zan;
		this.proj_user = proj_user;
		this.proj_group = proj_group;
		this.proj_name = proj_name;
		this.proj_content = proj_content;
		this.proj_status = proj_status;

		this.ck_name = ck_name;
		this.ck_phone = ck_phone;
		this.ck_status = ck_status;
		this.ck_group = ck_group;
		this.ck_gws = ck_gws;
		this.ck_content = ck_content;
		this.hours = hours;
		this.type = type;
		this.huodong_date = huodong_date;
		this.huodong_addr = huodong_addr;
		this.status2 = status2;
		this.age = age;
	}

	public static ArrayList<DuanziBean> newInstanceList(String jsonDatas) {
		ArrayList<DuanziBean> AdvertDatas = new ArrayList<DuanziBean>();

		try {
			JSONArray arr = new JSONArray(jsonDatas);
			int size = null == arr ? 0 : arr.length();
			System.out.println("size-->" + size);
			for (int i = 0; i < size; i++) {
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
				String proj_status = obj.optString(Attr.PRO_STATUS);

				String ck_name = obj.optString(Attr.CK_NAME);
				String ck_phone = obj.optString(Attr.CK_PHONE);
				String ck_status = obj.optString(Attr.CK_STATUS);
				String ck_group = obj.optString(Attr.CK_GROUP);
				String ck_gws = obj.optString(Attr.CK_GWS);
				String ck_content = obj.optString(Attr.CK_CONTENT);
				String hours = obj.optString(Attr.TYPE_HOURS);
				String type = obj.optString(Attr.TYPE_TYPE);
				String huodong_date = obj.optString(Attr.TYPE_DATE);
				String huodong_addr = obj.optString(Attr.TYPE_ADDR);
				String status2 = obj.optString(Attr.TYPE_STATUS2);
				String age = obj.optString(Attr.CK_AGE);

				DuanziBean bean = new DuanziBean(type_id, title, content,
						type_image, updatetime, status, author, zan, proj_user,
						proj_group, proj_name, proj_content, ck_name, ck_phone,
						ck_status, ck_group, ck_gws, ck_content, proj_status,
						hours, type,huodong_date,huodong_addr,status2,age);
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
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

	public String getProj_status() {
		return proj_status;
	}

	public void setProj_status(String proj_status) {
		this.proj_status = proj_status;
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

	public String getHuodong_date() {
		return huodong_date;
	}

	public void setHuodong_date(String huodong_date) {
		this.huodong_date = huodong_date;
	}

	public String getHuodong_addr() {
		return huodong_addr;
	}

	public void setHuodong_addr(String huodong_addr) {
		this.huodong_addr = huodong_addr;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
