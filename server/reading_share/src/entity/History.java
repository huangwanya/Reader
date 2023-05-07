package entity;

public class History {
	private int id;
	private int bioid;//浏览目标ID
	private String title;//浏览目标名称
	private String author;//浏览目标作者
	private int userid;//来访者ID
	private String updatedate;//来访或者浏览时间
	private String username;//来访者账号
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBioid() {
		return bioid;
	}
	public void setBioid(int bioid) {
		this.bioid = bioid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}


}
