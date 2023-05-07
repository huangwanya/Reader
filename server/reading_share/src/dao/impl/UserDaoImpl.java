package dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.AdminDAO;
import dao.UserDAO;
import entity.Admin;
import entity.Friends;
import entity.History;
import entity.Sign;
import entity.User;

public class UserDaoImpl extends BaseDAO implements UserDAO {

	@Override
	public User login(String username, String password) {
		StringBuffer sb = new StringBuffer("FROM User WHERE username = '")
				.append(username).append("' and password = '").append(password)
				.append("'");

		List<User> lst = findByHQL(sb.toString());
		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean reg(User user) {
		// TODO Auto-generated method stub
		return this.saveOrUpdate(user);
	}

	@Override
	public List<User> list() {
		StringBuffer sb = new StringBuffer("FROM User order by score desc");

		List<User> lst = findByHQL(sb.toString());

		return lst;
	}

	@Override
	public boolean del(User user) {
		// TODO Auto-generated method stub
		return deleteById(User.class, user.getId());
	}

	@Override
	public User load(int id) {
		// TODO Auto-generated method stub
		return get(User.class, id);
	}

	@Override
	public void addscore(int score, int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("update User set score = score + ")
				.append(score).append(" where  id = ").append(userid);
		try {
			this.executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addhours(int hours, int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("update User set hours = hours + ")
				.append(hours).append(" where  id = ").append(userid);
		try {
			this.executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deducthours(int hours, int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("update User set hours = hours - ")
				.append(hours).append(" where  id = ").append(userid);
		try {
			this.executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean save_(Sign sign) {
		// TODO Auto-generated method stub
		return this.saveOrUpdate(sign);
	}

	@Override
	public List<Sign> list_() {
		StringBuffer sb = new StringBuffer("FROM Sign order by id desc");

		List<Sign> lst = findByHQL(sb.toString());

		return lst;
	}

	@Override
	public boolean check_(int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("FROM Sign where userid=")
				.append(userid).append(" and signdate='")
				.append(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
				.append("'");

		List<Sign> lst = findByHQL(sb.toString());
		if (lst.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean shenhe(User user) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer(
				"update User set status=1 where  id = ").append(user.getId());
		try {
			this.executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean uploadphoto(User user) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("update User set image_url= '")
				.append(user.getImage_url()).append("' where  id = ")
				.append(user.getId());
		try {
			this.executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public boolean save_friend(Friends friends) {
		// TODO Auto-generated method stub
		return this.saveOrUpdate(friends);
	}

	@Override
	public List<Friends> listFriendsbyUser(int userid,String name) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("FROM Friends  where (userid1= ")
				.append(userid).append(" or userid2=").append(userid).append(") and black_status=0");
		List<Friends> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public boolean check_friends(int userid1, int userid2) {
		// TODO Auto-generated method stub

		StringBuffer sb = new StringBuffer("FROM Friends  where (userid1= ")
				.append(userid1).append(" or userid1=").append(userid2)
				.append(") and (userid2 =").append(userid2).append(" or userid2=").append(userid1).append(")");
		List<Friends> lst = findByHQL(sb.toString());
		if(lst.size()>0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean del_friends(int userid1, int userid2) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("delete from Friends  where (userid1= ")
		.append(userid1).append(" or userid1=").append(userid2)
		.append(") and (userid2 =").append(userid2).append(" or userid2=").append(userid1).append(")");
		
		try {
			this.executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean update_friends(int userid1, int userid2, int status) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("update  Friends set status=").append(status).append("  where userid1= ")
		.append(userid1).append(" and userid2=").append(userid2);
		try {
			this.executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<User> listbyusername(String username) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("FROM User WHERE username = '")
		.append(username).append("'");

        List<User> lst = findByHQL(sb.toString());
        
        if(lst ==null){
        	lst=new ArrayList<User>();
        	return lst;
        }else{
        	return lst;
        }
	}

	@Override
	public boolean update_blacklist(int userid1,int userid2) {
		// TODO Auto-generated method stub
		//return this.saveOrUpdate();
		StringBuffer sb = new StringBuffer("update  Friends set black_status=1  where userid1= ")
		.append(userid1).append(" and userid2=").append(userid2);
		try {
			this.executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Friends> listBlackListbyUser(int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("FROM Friends WHERE (userid1=").append(userid).append(" or userid2=").append(userid).append(") and black_status=1");

        List<Friends> lst = findByHQL(sb.toString());
        
        if(lst ==null){
        	lst=new ArrayList<Friends>();
        	return lst;
        }else{
        	return lst;
        }
	}

	@Override
	public boolean check_blacklist(int userid1, int userid2) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("FROM Friends WHERE userid1 =")
		.append(userid1).append(" and userid2=").append(userid2).append(" and black_status=1");

        List<Friends> lst = findByHQL(sb.toString());
        
        if(lst.size()==0){
        	return false;
        }else{
        	return true;
        }
	}

	@Override
	public boolean del_blacklist(int userid1, int userid2) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("update  Friends set black_status=0  where userid1= ")
		.append(userid1).append(" and userid2=").append(userid2);
		try {
			this.executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public int get_blacklist_count_user(int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM Friends where (userid1=").append(userid).append(" or userid2=").append(userid).append(") and black_status=1");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}

	@Override
	public List<Friends> listBlackNFrindsListbyUser(int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("FROM Friends WHERE (userid1=").append(userid).append(" or userid2=").append(userid).append(") and status=1");

        List<Friends> lst = findByHQL(sb.toString());
        
        if(lst ==null){
        	lst=new ArrayList<Friends>();
        	return lst;
        }else{
        	return lst;
        }
	}

	@Override
	public boolean save_history(History history) {
		// TODO Auto-generated method stub
		return this.saveOrUpdate(history);
	}

	@Override
	public List<History> listHistoryByVisit(String author) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("FROM History WHERE author ='")
		.append(author).append("' order by id desc");
        List<History> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public List<History> listHistoryByCheck(int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("FROM History WHERE userid =")
		.append(userid).append("  order by id desc");
        List<History> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public int get_visit_count_user(String author) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM History where author ='")
		.append(author).append("'");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}

	@Override
	public int get_check_count_user(int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM History where userid =")
		.append(userid).append("");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}

}
