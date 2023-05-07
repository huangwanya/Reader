package service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import service.AdminService;

import dao.AdminDAO;
import entity.Admin;
import entity.User;


public class AdminServiceImpl implements AdminService {
	private AdminDAO admindao;
	@Override
	public Admin login(String username, String password) {
		// TODO Auto-generated method stub
		Admin admin = admindao.load(username, password);
		return admin;
	}
	public AdminDAO getAdmindao() {
		return admindao;
	}
	public void setAdmindao(AdminDAO admindao) {
		this.admindao = admindao;
	}
	@Override
	public String listjson() {
		List<Admin> list = admindao.list();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	
	
}
