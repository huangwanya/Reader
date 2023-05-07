package dao;



import java.util.List;

import entity.Admin;
import entity.Friends;
import entity.History;
import entity.Sign;
import entity.User;



public interface UserDAO {

	public User login(String username,String password);
	public User load(int id);
	public boolean reg(User user);
	public boolean uploadphoto(User user);
	public boolean shenhe(User user);
	public boolean del(User user);
	public List<User> list();
	public List<User> listbyusername(String username);
	public void addscore(int score,int userid);
	public void addhours(int hours,int userid);
	public void deducthours(int hours,int userid);
	
	public boolean save_(Sign sign);
	public List<Sign> list_();
	public boolean check_(int userid);
	
	public boolean update_blacklist(int userid1,int userid2);
	public List<Friends> listBlackListbyUser(int userid);
	
	
	public List<Friends> listBlackNFrindsListbyUser(int userid);
	
	
	public boolean check_blacklist(int userid1,int userid2);
	public boolean del_blacklist(int userid1,int userid2);
	public int get_blacklist_count_user(int userid);
	
	
	public boolean save_history(History history);
	public List<History> listHistoryByVisit(String author);
	public List<History> listHistoryByCheck(int userid);
	public int get_visit_count_user(String  author);
	public int get_check_count_user(int userid);

	public boolean save_friend(Friends friends);
	public List<Friends> listFriendsbyUser(int userid,String name);
	public boolean check_friends(int userid1,int userid2);
	public boolean update_friends(int userid1,int userid2,int status);
	public boolean del_friends(int userid1,int userid2);
	
    }
