package dao;



import java.util.List;

import entity.Admin;
import entity.User;



public interface AdminDAO {

	public Admin load(String username,String password);
	public Admin load(int id);
	public List<Admin> list();
}
