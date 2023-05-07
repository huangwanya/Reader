package actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import service.UserService;
import utils.CreateId;
import utils.Utils;

import net.sf.json.JSONObject;


import entity.Friends;
import entity.History;
import entity.Sign;
import entity.User;

public class UserAction extends BaseAction {
	private UserService userservice;
	private User user;
	private String jsonString;
	private boolean flag;
	private String message;
	private Sign sign;
	private List<User> lst = new ArrayList<User>();
	
	private Friends friends;
	
	private int userid1;
	private int userid2;
	private int status;
	
	private String username;
	private int userid;
	
	private History history;
	
	
	
	private String keyword;
	
	
	public String save_history(){
		    history.setUpdatedate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			boolean flag =userservice.save_history(history);
			if(flag){
				jsonString="1";
			}else{
				jsonString="0";
			}
		return SUCCESS;
	}
	
	public String listHistoryByVisit(){
		jsonString=userservice.listHistoryByVisit(username);
		return SUCCESS;
	}
	public String listHistoryByCheck(){
		jsonString=userservice.listHistoryByCheck(userid);
		return SUCCESS;
	}
	
//	public boolean save_blacklist(int userid1,int userid2);
//	public String  listBlackListbyUser(int userid);
//	public boolean check_blacklist(int userid1,int userid2);
//	public boolean del_blacklist(int userid1,int userid2);
//	public int get_blacklist_count_user(int userid);
	
	public String save_blacklist(){
		boolean flag2= userservice.check_blacklist(friends.getUserid1(), friends.getUserid2());
		if(!flag2){
			boolean flag =userservice.save_blacklist(friends.getUserid1(), friends.getUserid2());
			if(flag){
				jsonString="1";
			}else{
				jsonString="0";
			}
		}
		return SUCCESS;
	}
	public String listBlackListbyUser(){
		jsonString=userservice.listBlackListbyUser(userid);
		return SUCCESS;
	}
	public String listBlackNFrindsListbyUser(){
		jsonString=userservice.listBlackNFrindsListbyUser(userid);
		return SUCCESS;
	}
//	public String get_blacklist_count_user(){
//		jsonString=userservice.get_blacklist_count_user(userid);
//		return SUCCESS;
//	}
	
	public String del_blacklist(){
			boolean flag =userservice.del_blacklist(friends.getUserid1(), friends.getUserid2());
			if(flag){
				jsonString="1";
			}else{
				jsonString="0";
			}
		return SUCCESS;
	}
	
	
	public String save_friend(){
		boolean flag2= userservice.check_friends(friends.getUserid1(), friends.getUserid2());
		if(flag2){
			jsonString="2";
		}else{
			boolean flag =userservice.save_friend(friends);
			if(flag){
				jsonString="1";
			}else{
				jsonString="0";
			}
		}
	
		return SUCCESS;
	}
	public String listFriendsbyUser(){
		jsonString =userservice.listFriendsbyUser(userid,keyword);
		
		return SUCCESS;
	}
	public String listbyusername(){
		jsonString =userservice.listbyusername(username);
		
		return SUCCESS;
	}
	public String del_friends(){
		boolean flag =userservice.del_friends(userid1,userid2);
		if(flag){
			jsonString="1";
		}else{
			jsonString="0";
		}
		return SUCCESS;
	}
	
	public String update_friends(){
		boolean flag =userservice.update_friends(userid1,userid2,status);
		if(flag){
			jsonString="1";
		}else{
			jsonString="0";
		}
		return SUCCESS;
	}

	public String login() {

		user = userservice.login(user.getUsername(), user.getPassword());

		JSONObject jo = JSONObject.fromObject(user);

		jsonString = jo.toString();

		return SUCCESS;

	}
	
	public String save_(){
		flag = userservice.check_(sign.getUserid());

		if (flag) {
			jsonString = "3";
		} else {
			boolean flag2=userservice.save_(sign);
			if(flag2){
				jsonString="1";
			}else{
				jsonString = "0";
			}
			
		}
		return SUCCESS;
	}
	public String list_(){
		jsonString=userservice.list_();
		return SUCCESS;
	}

	public String reg() {
		user.setName("管理员");
		flag = userservice.reg(user);

		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}

		return SUCCESS;

	}
	public String uploadphoto() {
		String realpath = ServletActionContext.getServletContext().getRealPath(
		"/upload");

		File file_ = new File(realpath);
		if (!file_.exists()) {
			file_.mkdirs();
		}
		String filename = user.getFileFileName();
		String extention = Utils.getExtensionName(filename);
		filename = CreateId.getId() + "." + extention;
		// jingdian.setImage_url(filename);
		
		try {
			FileUtils.copyFile(user.getFile(), new File(file_, filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setImage_url("upload" + "\\\\" + filename);
		flag = userservice.uploadphoto(user);
		
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		
		return SUCCESS;
		
	}
	public String listjson() {
		jsonString = userservice.listjson();
		return SUCCESS;
	}
	public String load() {
		
		user = userservice.load(user.getId());
		
		JSONObject jo = JSONObject.fromObject(user);
		
		jsonString = jo.toString();
		
		return SUCCESS;
		
	}
	public String list() {
		
		lst =userservice.list();
		
		return SUCCESS;
		
	}
	public String delete() {
		
		userservice.del(user);
		return SUCCESS;
		
		
	}
	public String shenhe() {
		
		userservice.shenhe(user);
		return SUCCESS;
		
		
	}

	public Sign getSign() {
		return sign;
	}

	public void setSign(Sign sign) {
		this.sign = sign;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<User> getLst() {
		return lst;
	}

	public void setLst(List<User> lst) {
		this.lst = lst;
	}

	public Friends getFriends() {
		return friends;
	}

	public void setFriends(Friends friends) {
		this.friends = friends;
	}

	public int getUserid1() {
		return userid1;
	}

	public void setUserid1(int userid1) {
		this.userid1 = userid1;
	}

	public int getUserid2() {
		return userid2;
	}

	public void setUserid2(int userid2) {
		this.userid2 = userid2;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}
	
	
	

}
