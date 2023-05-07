package actions;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import service.CommentsService;
import utils.CreateId;
import utils.Utils;


import entity.Admin;
import entity.Comments;
import entity.Message;
import entity.Shetuan;

public class CommentsAction extends BaseAction{
	private CommentsService commentsservice;
	private Comments comments;
	private Message message;
	private String jsonString;
	private String comment;
	private int userid;
	private int luxianid;
	private String author;
	private List<Comments> list = new ArrayList<Comments>();
	private List<Message> listmsg = new ArrayList<Message>();
	private HttpSession session;
	
	private Shetuan type;
	private List<Shetuan> list_=new ArrayList<Shetuan>();
	
	public String checkcomments() {
		boolean flag = commentsservice.checkcomments(comments);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	public String checkmsg() {
		boolean flag = commentsservice.checkmsg(message);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	
	public String add_(){
		return SUCCESS;
	}
	public String edit_(){
		type = commentsservice.load_(type.getId());
		return SUCCESS;
	}
	public String save_(){
		commentsservice.save_(type);
		return SUCCESS;
	}
	public String del_(){
		commentsservice.del_(type);
		return SUCCESS;
	}
	public String list_(){
		list_=commentsservice.list_();
		return SUCCESS;
	}
	public String listjson_(){
		jsonString=commentsservice.listjson_();
		return SUCCESS;
	}
	
	public String save(){
		comments= new Comments();
		comments.setUserid(userid);
		try {
			comments.setContent(URLDecoder.decode(comment, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comments.setCommitdate(new Date());
		comments.setLuxianid(luxianid);
		boolean flag = commentsservice.save(comments);
		if(flag){
			jsonString="1";
		}else{
			jsonString="0";
		}
		
		return SUCCESS;
	}
	
	public String list(){
		list = commentsservice.list();
		return SUCCESS;
	}

	
	public String add(){
		return SUCCESS;
	}

	
	
	public String listjson(){
		jsonString = commentsservice.listbyluxian(luxianid);
		return SUCCESS;
}
	public String listbyauthor(){
		jsonString = commentsservice.listbyauthor(author);
		return SUCCESS;
	}
	
	
	public String delete(){
		commentsservice.del(comments);
		return SUCCESS;
	}




	public String savemsg(){
		message.setCdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		try {
			message.setContent(URLDecoder.decode(message.getContent(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = commentsservice.save(message);
		if(flag){
			jsonString="1";
		}else{
			jsonString="0";
		}
		
		return SUCCESS;
	}
	public String listmsgjson(){
		jsonString = commentsservice.listbyuserid(userid);
		return SUCCESS;
}
	
	public String listmsg(){
		listmsg = commentsservice.listmsg();
		return SUCCESS;
	}
	
	public String delmsg(){
		commentsservice.delmsg(message);
		return SUCCESS;
	}
	public String delmsg_client(){
		boolean flag=commentsservice.delmsg(message);
		if(flag){
			jsonString="1";
		}else{
			jsonString="0";
		}
		return SUCCESS;
	}
	public String reply(){
		try {
			message.setContent(URLDecoder.decode(message.getContent(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag=commentsservice.reply(message);
		if(flag){
			jsonString="1";
		}else{
			jsonString="0";
		}
		return SUCCESS;
	}
	public String replyin(){
		
		return SUCCESS;
	}

	

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}



	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public CommentsService getCommentsservice() {
		return commentsservice;
	}

	public void setCommentsservice(CommentsService commentsservice) {
		this.commentsservice = commentsservice;
	}

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Comments> getList() {
		return list;
	}

	public void setList(List<Comments> list) {
		this.list = list;
	}


	public int getLuxianid() {
		return luxianid;
	}

	public void setLuxianid(int luxianid) {
		this.luxianid = luxianid;
	}
	public Shetuan getType() {
		return type;
	}
	public void setType(Shetuan type) {
		this.type = type;
	}
	public List<Shetuan> getList_() {
		return list_;
	}
	public void setList_(List<Shetuan> list_) {
		this.list_ = list_;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public List<Message> getListmsg() {
		return listmsg;
	}
	public void setListmsg(List<Message> listmsg) {
		this.listmsg = listmsg;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}






	
	
	

}
