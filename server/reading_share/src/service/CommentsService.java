package service;

import java.util.List;

import entity.Comments;
import entity.Message;
import entity.Shetuan;




public interface CommentsService {
	
	public boolean save(Comments comments);
	public boolean del(Comments comments);
	public List<Comments> list();
	public String listbyluxian(int luxianid);
	public boolean checkcomments(Comments comments);
	//----------------------------------------
	//----------------------------------------------------------
	public boolean save_(Shetuan type);
	public boolean del_(Shetuan type);
	public Shetuan load_(int id);
	public List<Shetuan> list_();
	public String listjson_();
	
	//-----
	///--------------
	public boolean save(Message message);
	public boolean checkmsg(Message message);
	public String listbyuserid(int touserid);
	public String listbyauthor(String author);
	public List<Message> listmsg();
	public boolean delmsg(Message message);
	public boolean reply(Message message);
	

}
