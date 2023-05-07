package service.impl;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import service.UserService;


import dao.BiotechDAO;
import dao.CommentsDAO;
import dao.UserDAO;
import entity.Biotech;
import entity.Friends;
import entity.History;
import entity.Shetuan;
import entity.Sign;
import entity.User;


public class UserServiceImpl implements UserService {
	private UserDAO userdao;
	private CommentsDAO commentsdao;
	private BiotechDAO biotechdao;
	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User user = userdao.login(username, password);
		
		
		if(user == null){
			return null;
		}else{
			if(user.getAddress().length()>0){
				Shetuan shetuan=commentsdao.load_by_name(user.getAddress());
				
				if(shetuan !=null){
					user.setShetuan_id(shetuan.getId());
				}
			}
			return user;
		}
	}
	@Override
	public boolean reg(User user) {
		// TODO Auto-generated method stub
		return userdao.reg(user);
	}
	public UserDAO getUserdao() {
		return userdao;
	}
	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}
	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return userdao.list();
	}
	@Override
	public void del(User user) {
		userdao.del(user);
		
	}
	@Override
	public User load(int userid) {
		// TODO Auto-generated method stub
		User user =userdao.load(userid);
		if(user !=null){
			user.setCount0(biotechdao.get_count_byauthor3(user.getUsername()));
			user.setCount1(biotechdao.get_count_byauthor1(user.getUsername()));
			user.setCount2(biotechdao.get_count_byauthor2(user.getUsername()));
			user.setCount_baoming(biotechdao.get_baoming_count_byauthor(userid));
			user.setCount_msg(biotechdao.get_msg_countbyuser(user.getUsername()));
			user.setCount_folder(biotechdao.get_folder_count_user(userid));
			user.setCount_blacklist(userdao.get_blacklist_count_user(userid));
			user.setCount_visit(userdao.get_visit_count_user(user.getUsername()));
			user.setCount_check(userdao.get_check_count_user(userid));
		}else{
			user =new User();
		}
		return user;
	}
	@Override
	public String listjson() {
		List<User> list = userdao.list();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	@Override
	public boolean save_(Sign sign) {
		// TODO Auto-generated method stub
		User user =userdao.load(sign.getUserid());
		if(user !=null){
			sign.setSigndate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			sign.setUsername(user.getUsername());
		}
		return userdao.save_(sign);
	}
	@Override
	public String list_() {
		List<Sign> list = userdao.list_();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	@Override
	public boolean check_(int userid) {
		// TODO Auto-generated method stub
		return userdao.check_(userid);
	}
	@Override
	public boolean shenhe(User user) {
		// TODO Auto-generated method stub
		return userdao.shenhe(user);
	}
	public CommentsDAO getCommentsdao() {
		return commentsdao;
	}
	public void setCommentsdao(CommentsDAO commentsdao) {
		this.commentsdao = commentsdao;
	}
	@Override
	public boolean uploadphoto(User user) {
		// TODO Auto-generated method stub
		return userdao.uploadphoto(user);
	}
	public BiotechDAO getBiotechdao() {
		return biotechdao;
	}
	public void setBiotechdao(BiotechDAO biotechdao) {
		this.biotechdao = biotechdao;
	}
	
	@Override
	public boolean save_friend(Friends friends) {
		// TODO Auto-generated method stub
		return userdao.save_friend(friends);
	}
	@Override
	public String listFriendsbyUser(int userid,String username) {
		// TODO Auto-generated method stub
		List<Friends> list = userdao.listFriendsbyUser(userid, username);
		List<Friends> list2 =new ArrayList<Friends>();
		for(Friends f:list){
			int userid1=f.getUserid1();
			int userid2=f.getUserid2();
			User user1 =userdao.load(userid1);
			User user2=userdao.load(userid2);
			if(user1 !=null){
				f.setUsername1(user1.getUsername());
				f.setAddress1(user1.getAddress());
				f.setAge1(user1.getAge());
				f.setImage_url1(user1.getImage_url());
				f.setPhone1(user1.getPhone());
				f.setQqnum1(user1.getQqnum());
				
			}
			if(user2 !=null){
				f.setUsername2(user2.getUsername());
				f.setAddress2(user2.getAddress());
				f.setAge2(user2.getAge());
				f.setImage_url2(user2.getImage_url());
				f.setPhone2(user2.getPhone());
				f.setQqnum2(user2.getQqnum());
			}
			if(username==null || username.equals("")){
				list2.add(f);
			}else{
				if(userid==userid1){
					if(f.getUsername2().equals(username) ){
						list2.add(f);
					}
				}
				else if(userid ==userid2){
					if(f.getUsername1().equals(username)){
						list2.add(f);
					}
				}
				
			}
		
		}
		
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list2);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	@Override
	public boolean check_friends(int userid1, int userid2) {
		// TODO Auto-generated method stub
		return userdao.check_friends(userid1, userid2);
	}
	@Override
	public boolean del_friends(int userid1, int userid2) {
		// TODO Auto-generated method stub
		return userdao.del_friends(userid1, userid2);
	}
	@Override
	public boolean update_friends(int userid1, int userid2, int status) {
		// TODO Auto-generated method stub
		return userdao.update_friends(userid1, userid2, status);
	}
	@Override
	public String listbyusername(String username) {
		// TODO Auto-generated method stub
		List<User> list = userdao.listbyusername(username);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	@Override
	public boolean save_blacklist(int userid1,int userid2) {
		// TODO Auto-generated method stub
		return userdao.update_blacklist(userid1,userid2);
	}
	@Override
	public String listBlackListbyUser(int userid) {
		// TODO Auto-generated method stub
		List<Friends> list = userdao.listBlackListbyUser(userid);
		List<Friends> list2=new ArrayList<Friends>();
		for(Friends f:list){
			int userid1=f.getUserid1();
			int userid2=f.getUserid2();
			User user1 =userdao.load(userid1);
			User user2=userdao.load(userid2);
			if(user1 !=null){
				f.setUsername1(user1.getUsername());
				f.setAddress1(user1.getAddress());
				f.setAge1(user1.getAge());
				f.setImage_url1(user1.getImage_url());
				f.setPhone1(user1.getPhone());
				f.setQqnum1(user1.getQqnum());
				
			}
			if(user2 !=null){
				f.setUsername2(user2.getUsername());
				f.setAddress2(user2.getAddress());
				f.setAge2(user2.getAge());
				f.setImage_url2(user2.getImage_url());
				f.setPhone2(user2.getPhone());
				f.setQqnum2(user2.getQqnum());
			}
			
			list2.add(f);
		}
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list2);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	@Override
	public boolean check_blacklist(int userid1, int userid2) {
		// TODO Auto-generated method stub
		return userdao.check_blacklist(userid1, userid2);
	}
	@Override
	public boolean del_blacklist(int userid1, int userid2) {
		// TODO Auto-generated method stub
		return userdao.del_blacklist(userid1, userid2);
	}
	@Override
	public int get_blacklist_count_user(int userid) {
		// TODO Auto-generated method stub
		return userdao.get_blacklist_count_user(userid);
	}
	@Override
	public String listBlackNFrindsListbyUser(int userid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<Friends> list = userdao.listBlackNFrindsListbyUser(userid);
		List<Friends> list2=new ArrayList<Friends>();
		for(Friends f:list){
			int userid1=f.getUserid1();
			int userid2=f.getUserid2();
			User user1 =userdao.load(userid1);
			User user2=userdao.load(userid2);
			if(user1 !=null){
				f.setUsername1(user1.getUsername());
				f.setAddress1(user1.getAddress());
				f.setAge1(user1.getAge());
				f.setImage_url1(user1.getImage_url());
				f.setPhone1(user1.getPhone());
				f.setQqnum1(user1.getQqnum());
				
			}
			if(user2 !=null){
				f.setUsername2(user2.getUsername());
				f.setAddress2(user2.getAddress());
				f.setAge2(user2.getAge());
				f.setImage_url2(user2.getImage_url());
				f.setPhone2(user2.getPhone());
				f.setQqnum2(user2.getQqnum());
			}
			
			list2.add(f);
		}
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list2);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	@Override
	public boolean save_history(History history) {
		// TODO Auto-generated method stub
		return userdao.save_history(history);
	}
	@Override
	public String listHistoryByVisit(String author) {
		// TODO Auto-generated method stub
		List<History> list = userdao.listHistoryByVisit(author);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	@Override
	public String listHistoryByCheck(int userid) {
		// TODO Auto-generated method stub
		List<History> list = userdao.listHistoryByCheck(userid);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	
	
	
	
	
}
