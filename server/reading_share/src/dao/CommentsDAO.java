package dao;


import java.util.List;

import entity.Comments;
import entity.Message;
import entity.Shetuan;



public interface CommentsDAO {

	public boolean save(Comments comments);
	public boolean checkcomments(Comments comments);
	public boolean del(Comments comments);
	public List<Comments> list();
	public List<Comments> listbyluxian(int luxian_id);
//----------------------------------------------------------
	
	
//----------------------------------------------------------
	
	
	public boolean save_(Shetuan type);
	public boolean del_(Shetuan type);
	public Shetuan load_(int id);
	public Shetuan load_by_name(String name);
	public boolean update_(Shetuan type);
	public List<Shetuan> list_();
	
	///--------------
	public boolean save(Message message);
	public boolean checkmsg(Message message);
	public List<Message> listbyuserid(int touserid);
	public List<Message> listbyauthor(String author);
	public List<Message> listmsg();
	public boolean delmsg(Message message);
	public boolean reply(Message message);
	
}
