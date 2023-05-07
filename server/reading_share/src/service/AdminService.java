package service;

import entity.Admin;


public interface AdminService {
	public Admin login(String username,String password);
	public String listjson();
}
