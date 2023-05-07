package service;

import java.util.List;

import entity.Friends;
import entity.History;
import entity.Sign;
import entity.User;


public interface UserService {
	public User login(String username,String password);
	public boolean reg(User user);
	public List<User> list();
	public void del(User user); 
	public User load(int userid);
	public String listjson();
	public String listbyusername(String username);
	public boolean shenhe(User user);
	
	public boolean save_(Sign sign);
	public String list_();
	public boolean check_(int userid);
	public boolean uploadphoto(User user);
	
	
	
	public boolean save_history(History history);
	public String listHistoryByVisit(String author);
	public String listHistoryByCheck(int userid);
	
	public boolean save_blacklist(int userid1,int userid2);
	public String  listBlackListbyUser(int userid);
	public String  listBlackNFrindsListbyUser(int userid);
	public boolean check_blacklist(int userid1,int userid2);
	public boolean del_blacklist(int userid1,int userid2);
	public int get_blacklist_count_user(int userid);
	
	
	public boolean save_friend(Friends friends);
	public String listFriendsbyUser(int userid,String name);
	public boolean check_friends(int userid1,int userid2);
	public boolean del_friends(int userid1,int userid2);
	public boolean update_friends(int userid1,int userid2,int status);
	
	
	
}
