package dao.impl;

import java.util.List;

import dao.AdminDAO;
import entity.Admin;
import entity.User;

public class AdminDaoImpl extends BaseDAO implements AdminDAO {

	@Override
	public Admin load(String username, String password) {
		StringBuffer sb = new StringBuffer("FROM Admin WHERE username = '")
				.append(username).append("' and password = '").append(password)
				.append("'");

		List<Admin> lst = findByHQL(sb.toString());
		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Admin> list() {
		StringBuffer sb = new StringBuffer("FROM Admin order by id desc");

		List<Admin> lst = findByHQL(sb.toString());

		return lst;
	}

	@Override
	public Admin load(int id) {
		// TODO Auto-generated method stub
		return get(Admin.class, id);
	}

}
