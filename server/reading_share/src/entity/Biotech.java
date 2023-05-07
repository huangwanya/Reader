package entity;

import java.io.File;

public class Biotech {
	private int id;
	private String title;
	private String content;

	private String type;//0 分享、1 攻略、2 活动、3 阅读精选、4 反馈
	private String author;
	private String pubdate;
	private String huodong_date;
	private String huodong_addr;
	
	private File file;
	private String image_url;
	private String fileFileName;
	
	private int status;
	private int hours;

	private String type2;//类型二
	private String zuobiao;//坐标信息
	private int zan,favorite,jubao,message,comments;
	private int count_;//报名数量
	private int count_1;//实际报名
	private int age;//年级限制
	
	private int type_id;
	
	private int status2;//0 已发起， 1 已审批
	
	private String shetuan;
	
	
	private String fuzeren;
	private String tel;
	private String xingzhi;
	private String ishaibao;
	
	
	
	
	
	
	

	public String getFuzeren() {
		return fuzeren;
	}

	public void setFuzeren(String fuzeren) {
		this.fuzeren = fuzeren;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getXingzhi() {
		return xingzhi;
	}

	public void setXingzhi(String xingzhi) {
		this.xingzhi = xingzhi;
	}

	public String getIshaibao() {
		return ishaibao;
	}

	public void setIshaibao(String ishaibao) {
		this.ishaibao = ishaibao;
	}

	public int getCount_() {
		return count_;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getCount_1() {
		return count_1;
	}

	public void setCount_1(int count_1) {
		this.count_1 = count_1;
	}

	public void setCount_(int count_) {
		this.count_ = count_;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public String getHuodong_date() {
		return huodong_date;
	}

	public void setHuodong_date(String huodong_date) {
		this.huodong_date = huodong_date;
	}

	public String getHuodong_addr() {
		return huodong_addr;
	}

	public void setHuodong_addr(String huodong_addr) {
		this.huodong_addr = huodong_addr;
	}

	public String getZuobiao() {
		return zuobiao;
	}

	public void setZuobiao(String zuobiao) {
		this.zuobiao = zuobiao;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public int getStatus2() {
		return status2;
	}

	public void setStatus2(int status2) {
		this.status2 = status2;
	}

	public String getShetuan() {
		return shetuan;
	}

	public void setShetuan(String shetuan) {
		this.shetuan = shetuan;
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public int getJubao() {
		return jubao;
	}

	public void setJubao(int jubao) {
		this.jubao = jubao;
	}

	public int getMessage() {
		return message;
	}

	public void setMessage(int message) {
		this.message = message;
	}
	
	


}
