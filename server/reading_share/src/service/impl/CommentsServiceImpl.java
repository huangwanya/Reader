package service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import service.CommentsService;
import utils.JsonValueProcessorImplTest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;


import dao.AdminDAO;
import dao.CommentsDAO;
import dao.UserDAO;
import entity.Admin;
import entity.Comments;
import entity.Message;
import entity.Shetuan;
import entity.User;

public class CommentsServiceImpl implements CommentsService {
	private CommentsDAO commentsdao;
	private UserDAO userdao;
	private AdminDAO admindao;

	@Override
	public boolean save(Comments comments) {
		// TODO Auto-generated method stub
		return commentsdao.save(comments);
	}

	@Override
	public boolean del(Comments comments) {
		// TODO Auto-generated method stub
		return commentsdao.del(comments);
	}

	@Override
	public List<Comments> list() {
		// TODO Auto-generated method stub
		List<Comments> list2= new ArrayList<Comments>();
		List<Comments> list = commentsdao.list();
		
		for(Comments c :list){
			User user = userdao.load(c.getUserid());
			c.setUsername(user.getUsername());
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getCommitdate());
			c.setCdate(date);
			list2.add(c);
		}
		return list2;
	}

	@Override
	public String listbyluxian(int luxianid) {
		// TODO Auto-generated method stub
		List<Comments> list2= new ArrayList<Comments>();
		List<Comments> list = commentsdao.listbyluxian(luxianid);
		
		for(Comments c :list){
			User user = userdao.load(c.getUserid());
			c.setUsername(user.getUsername());
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getCommitdate());
			c.setCdate(date);
			list2.add(c);
		}
		
		//注册date类型的转化方式
		 JsonConfig jsonConfig = new JsonConfig();
		 jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessorImplTest());
		
		JSONArray js = JSONArray.fromObject(list2,jsonConfig);
		
		return js.toString();
	}

	public CommentsDAO getCommentsdao() {
		return commentsdao;
	}

	public void setCommentsdao(CommentsDAO commentsdao) {
		this.commentsdao = commentsdao;
	}

	public UserDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}
		
		
		


	public AdminDAO getAdmindao() {
		return admindao;
	}

	public void setAdmindao(AdminDAO admindao) {
		this.admindao = admindao;
	}

	
	@Override
	public boolean save_(Shetuan type) {
		// TODO Auto-generated method stub
		return commentsdao.save_(type);
	}

	@Override
	public boolean del_(Shetuan type) {
		// TODO Auto-generated method stub
		return commentsdao.del_(type);
	}

	@Override
	public Shetuan load_(int id) {
		// TODO Auto-generated method stub
		return commentsdao.load_(id);
	}

	@Override
	public List<Shetuan> list_() {
		// TODO Auto-generated method stub
		return commentsdao.list_();
	}

	@Override
	public String listjson_() {
		List<Shetuan> list = commentsdao.list_();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public boolean save(Message message) {
		// TODO Auto-generated method stub
		return commentsdao.save(message);
	}

	@Override
	public String listbyuserid(int touserid) {
		List<Message> list = commentsdao.listbyuserid(touserid);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public List<Message> listmsg() {
		// TODO Auto-generated method stub
		List<Message> list = commentsdao.listmsg();
		return list;
	}

	@Override
	public boolean delmsg(Message message) {
		// TODO Auto-generated method stub
		return commentsdao.delmsg(message);
	}

	@Override
	public boolean reply(Message message) {
		// TODO Auto-generated method stub
		return commentsdao.reply(message);
	}

	@Override
	public boolean checkcomments(Comments comments) {
		// TODO Auto-generated method stub
		return commentsdao.checkcomments(comments);
	}

	@Override
	public boolean checkmsg(Message message) {
		// TODO Auto-generated method stub
		return commentsdao.checkmsg(message);
	}

	@Override
	public String listbyauthor(String author) {
		// TODO Auto-generated method stub
		List<Message> list = commentsdao.listbyauthor(author);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

}
