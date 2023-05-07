package entity;

import java.io.File;

public class User {
	private int id;
	private String username;
	private String password;
	private String qqnum;
	private String phone;
	private String name;
	private String address;
	private String age;
	private String image_url;
	
	private int hours;
	private int score;
	
	private int shetuan_id;
	
	private int status;
	
	private int baoming_status;
	
	
	private File file;

	private String fileFileName;
	
	
	private int count0,count1,count2,count_baoming,count_msg,count_folder,count_blacklist,count_visit,count_check;
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public int getCount_visit() {
		return count_visit;
	}

	public void setCount_visit(int count_visit) {
		this.count_visit = count_visit;
	}

	public int getCount_check() {
		return count_check;
	}

	public void setCount_check(int count_check) {
		this.count_check = count_check;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQqnum() {
		return qqnum;
	}

	public int getCount0() {
		return count0;
	}

	public void setCount0(int count0) {
		this.count0 = count0;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

	public int getCount_baoming() {
		return count_baoming;
	}

	public void setCount_baoming(int count_baoming) {
		this.count_baoming = count_baoming;
	}

	public int getCount_msg() {
		return count_msg;
	}

	public void setCount_msg(int count_msg) {
		this.count_msg = count_msg;
	}

	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getShetuan_id() {
		return shetuan_id;
	}

	public void setShetuan_id(int shetuan_id) {
		this.shetuan_id = shetuan_id;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public int getBaoming_status() {
		return baoming_status;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getCount_folder() {
		return count_folder;
	}

	public void setCount_folder(int count_folder) {
		this.count_folder = count_folder;
	}

	public void setBaoming_status(int baoming_status) {
		this.baoming_status = baoming_status;
	}

	public int getCount_blacklist() {
		return count_blacklist;
	}

	public void setCount_blacklist(int count_blacklist) {
		this.count_blacklist = count_blacklist;
	}

}
