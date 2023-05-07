package actions;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import service.AdminService;

import entity.Admin;

public class LoginAction extends BaseAction {
	private AdminService adminservice;
	private Admin admin;
	private boolean flag;
	private String message;
	private String jsonString;
	private HttpSession session;

	public String login() {
		session = ServletActionContext.getRequest().getSession();

		admin = adminservice.login(admin.getUsername(), admin.getPassword());
		session.putValue("admin", admin);
		if(admin != null){
			return SUCCESS;
		}else{
			message="用户名或密码错误";
			return "login";
		}
		
	}
	
	public String listjson() {
		jsonString = adminservice.listjson();
		return SUCCESS;
	}

	public AdminService getAdminservice() {
		return adminservice;
	}

	public void setAdminservice(AdminService adminservice) {
		this.adminservice = adminservice;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
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

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

}
