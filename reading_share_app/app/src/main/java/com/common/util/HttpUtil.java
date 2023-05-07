package com.common.util;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpUtil {
//	public static final String URL_BASE = "http://10.0.2.2:8080/schoolclub_activity/";
	public static final String URL_BASE = "http://10.0.2.2:8080/reading_share/";
	public static final String URL_MYFRIENDSLISTALL = URL_BASE + "user_listFriendsbyUser.action?userid=";
	public static final String URL_MYBLACKLIST = URL_BASE + "user_listBlackListbyUser.action?userid=";
	public static final String URL_MYMANLIST = URL_BASE + "user_listBlackNFrindsListbyUser.action?userid=";
	public static final String URL_LOGIN = URL_BASE + "user_login.action?";
	public static final String URL_REGISTER = URL_BASE + "user_reg.action?";
	public static final String URL_SAVEPROJ = URL_BASE + "biotech_saveproj.action?";
	public static final String URL_SAVECK = URL_BASE + "biotech_saveck.action?";
	public static final String URL_USERLISTALL = URL_BASE + "user_listjson.action";
	public static final String URL_USERLISTQUERY = URL_BASE + "user_listbyusername.action?username=";

	public static final String URL_DELFRIENDS = URL_BASE
			+ "user_del_friends.action?";
	public static final String URL_DELBLACKLIST = URL_BASE
			+ "user_del_blacklist.action?";
	public static final String URL_ADDFRIENDS = URL_BASE
			+ "user_save_friend.action?";
	public static final String URL_UPDATE_FRIENDS = URL_BASE
			+ "user_update_friends.action?";
	public static final String URL_TO_BLACKLIST = URL_BASE
			+ "user_save_blacklist.action?";
	public static final String URL_SAVETP = URL_BASE + "user_saveprog.action?";
	public static final String URL_DUANZILIST = URL_BASE + "biotech_listjson0.action";
	public static final String URL_XINWENLIST = URL_BASE + "biotech_listjson1.action";
	public static final String URL_LIST0 = URL_BASE + "biotech_listjson_00.action";
	public static final String URL_LIST0_HEAD = URL_BASE + "biotech_listjson_00_head.action";
	public static final String URL_LIST1 = URL_BASE + "biotech_listjson_01.action";
	public static final String URL_LIST2 = URL_BASE + "biotech_listjson_02.action";
	public static final String URL_LIST3 = URL_BASE + "biotech_listjson_03.action";
	public static final String URL_LIST4 = URL_BASE + "biotech_listjson_04.action";
	public static final String URL_PROJLIST = URL_BASE + "biotech_listjson2.action?userid=";
	public static final String URL_CKLIST = URL_BASE + "biotech_listjson3.action";
	public static final String URL_MESSAGELIST = URL_BASE + "comments_listmsgjson?userid=";
	public static final String URL_MESSAGELISTBYAUTHOR = URL_BASE + "comments_listbyauthor?author=";
	public static final String URL_BOOKLIST = URL_BASE + "comments_listbookjson?userid=";
	public static final String URL_DUANZILISTBYTYPE = URL_BASE + "biotech_listbytype.action?type=";
	public static final String URL_DUANZILISTBYTYPE1 = URL_BASE + "biotech_listbytype1.action?type_id=";
	public static final String URL_HUODONGTUIJIAN = URL_BASE + "biotech_selecttuijian.action?userid=";
	public static final String URL_DUANZILISTBYTYPE2 = URL_BASE + "biotech_listbytype2.action?type_id=";
	public static final String URL_DUANZILISTBYTYPE2_HEAD = URL_BASE + "biotech_listbytype2_head.action";
	public static final String URL_DUANZILISTBYTYPE4_HEAD = URL_BASE + "biotech_list3_head.action";
	public static final String URL_FAXIANLIST = URL_BASE + "biotech_listjson1.action";
	public static final String URL_FRIENDSLIST = URL_BASE + "biotech_listuserjson.action?";
	public static final String URL_SIGNLIST = URL_BASE + "user_list_.action";
	public static final String URL_VISITLIST = URL_BASE + "user_listHistoryByVisit.action?username=";
	public static final String URL_CHECKLIST = URL_BASE + "user_listHistoryByCheck.action?userid=";
	public static final String URL_DUANZILISTUSER_ = URL_BASE + "biotech_listbyauthor_.action?";
	public static final String URL_DUANZILISTUSER_1 = URL_BASE + "biotech_loadbyauthor1.action?";
	public static final String URL_DUANZILISTUSER = URL_BASE + "biotech_listjsonbyuser.action?";
	public static final String URL_DUANZILISTUSER3 = URL_BASE + "biotech_listjsonbyuser3.action?";
	public static final String URL_DUANZILISTUSER4 = URL_BASE + "biotech_listjsonbyuser4.action?";
	public static final String URL_DUANZILISTUSERFOLDER = URL_BASE + "biotech_listjsonbyfolder.action?userid=";
	public static final String URL_BAOMINGLIST = URL_BASE + "biotech_listbaomingjson.action?userid=";
	public static final String URL_BAOMINGDEL = URL_BASE + "biotech_delbaoming.action?";
	public static final String URL_BAOMINGSHENHE = URL_BASE + "biotech_shenhebaoming.action?";
	public static final String URL_DEL_MSG = URL_BASE + "comments_delmsg_client.action?";
	public static final String URL_HUODONGDEL = URL_BASE + "biotech_deletehuodongjson.action?";
	public static final String URL_GOODSTYPELIST_ = URL_BASE + "comments_listjson_.action";
	public static final String URL_BASEUPLOAD = URL_BASE + "upload/";
	public static final String URL_BIO_ADD = URL_BASE
			+ "biotech_uploadarticle.action?";
	public static final String URL_PHOTO_ADD = URL_BASE
			+ "user_uploadphoto.action?";
	public static final String URL_BIO_ADD_ = URL_BASE
			+ "biotech_uploadarticle_.action?";
	public static final String URL_BIODETAIL = URL_BASE
			+ "biotech_detailjson.action?id=";
	public static final String URL_ZANDIAN = URL_BASE
			+ "biotech_dianzan.action?zan.duanziid=";
	public static final String URL_CHECKZAN = URL_BASE
			+ "biotech_checkzan.action?zan.duanziid=";
	public static final String URL_JUBAO = URL_BASE
			+ "biotech_addjubao.action?jubao.duanziid=";
	public static final String URL_CANCELZAN = URL_BASE
			+ "biotech_cancelzan.action?zan.duanziid=";
	public static final String URL_CHECKFOLDER = URL_BASE
			+ "biotech_checkfolder.action?folder.duanziid=";
	public static final String URL_CHECKPINGJIA = URL_BASE
			+ "comments_checkcomments.action?comments.luxianid=";
	public static final String URL_CHECKMSG = URL_BASE
			+ "comments_checkmsg.action?message.luxianid=";
	public static final String URL_CHECKJUBAO = URL_BASE
			+ "biotech_checkjubao.action?jubao.duanziid=";
	public static final String URL_BAOMING = URL_BASE
			+ "biotech_addbaoming.action?";
	public static final String URL_SIGN= URL_BASE
			+ "user_save_.action?";
	public static final String URL_CHECKBAOMING = URL_BASE
			+ "biotech_checkbaoming.action?";
	public static final String URL_SHOUCANG = URL_BASE
			+ "biotech_addfolder.action?";
	public static final String URL_ADD_HISTORY = URL_BASE
			+ "user_save_history.action?";
	public static final String URL_CANCELSHOUCANG = URL_BASE
			+ "biotech_cancelfolder.action?";
	public static final String URL_COMMENTSADD = URL_BASE
			+ "comments_save.action?";
	public static final String URL_SIGNSAVE = URL_BASE
			+ "user_save_.action?sign.userid=";
	public static final String URL_MESSAGESADD = URL_BASE
			+ "comments_savemsg.action?";
	public static final String URL_MESSAGEREPLY = URL_BASE
			+ "message_reply.action?";
	public static final String URL_BOOKADD = URL_BASE
			+ "comments_savebook.action?";
	public static final String URL_DUANZICOMMENTS = URL_BASE
			+ "comments_listjson.action?luxianid=";
	public static final String URL_LOAD = URL_BASE + "user_load.action?";
	// public static final String BASE_URL="http://192.168.0.164:8080/kycheck/";
	// 获得Get请求对象request
	public static HttpGet getHttpGet(String url) {
		HttpGet request = new HttpGet(url);
		return request;
	}

	// 获得Post请求对象request
	public static HttpPost getHttpPost(String url) {
		HttpPost request = new HttpPost(url);
		return request;
	}

	// 根据请求获得响应对象response
	public static HttpResponse getHttpResponse(HttpGet request)
			throws ClientProtocolException, IOException {
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}

	// 根据请求获得响应对象response
	public static HttpResponse getHttpResponse(HttpPost request)
			throws ClientProtocolException, IOException {
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}
	// 发送Post请求，获得响应查询结果
	public static String queryStringConnectForPost(String url) {
		// request method is POST

		String charset = "UTF-8";
		HttpURLConnection conn = null;
		//DataOutputStream wr;
		StringBuilder result = null;
		URL urlObj;
		JSONObject jObj = null;
		StringBuilder sbParams;
		String paramsString;


		try {
			urlObj = new URL(url);
			conn = (HttpURLConnection) urlObj.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept-Charset", charset);
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.connect();




			//wr = new DataOutputStream(conn.getOutputStream());

			//wr.writeBytes();

			//wr.flush();

			//wr.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			//Receive the response from the server
			InputStream in = new BufferedInputStream(conn.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			result = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			Log.d("JSON Parser", "result: " + result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}



		conn.disconnect();



		return result.toString();
	}
	// 发送Post请求，获得响应查询结果
	public static String queryStringForPost(String url) {
		// 根据url获得HttpPost对象
		HttpPost request = HttpUtil.getHttpPost(url);
		String result = null;
		try {
			// 获得响应对象
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判断是否请求成功
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		}
		return null;
	}

	// 获得响应查询结果
	public static String queryStringForPost(HttpPost request) {
		String result = null;
		try {
			// 获得响应对象
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判断是否请求成功
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		}
		return null;
	}

	// 发送Get请求，获得响应查询结果
	public static String queryStringForGet(String url) {
		// 获得HttpGet对象
		HttpGet request = HttpUtil.getHttpGet(url);
		String result = null;
		try {
			// 获得响应对象
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判断是否请求成功
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		}
		return null;
	}
}
