package dao.impl;

import java.util.List;


import dao.CommentsDAO;
import entity.Comments;
import entity.Message;
import entity.Shetuan;
import entity.Zan;

public class CommentsDaoImpl extends BaseDAO implements CommentsDAO {

	@Override
	public boolean save(Comments comments) {
		return saveOrUpdate(comments);
	}

	@Override
	public boolean del(Comments comments) {
		// TODO Auto-generated method stub
		return deleteById(Comments.class, comments.getId());
	}

	@Override
	public List<Comments> list() {
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("FROM Comments ");

		List<Comments> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public List<Comments> listbyluxian(int luxian_id) {
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("FROM Comments where luxianid =").append(luxian_id);

		List<Comments> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public boolean save_(Shetuan type) {
		// TODO Auto-generated method stub
		return saveOrUpdate(type);
	}

	@Override
	public boolean del_(Shetuan type) {
		// TODO Auto-generated method stub
		return deleteById(Shetuan.class, type.getId());
	}

	@Override
	public Shetuan load_(int id) {
		// TODO Auto-generated method stub
		return get(Shetuan.class, id);
	}

	@Override
	public boolean update_(Shetuan type) {
		// TODO Auto-generated method stub
		return saveOrUpdate(type);
	}

	@Override
	public List<Shetuan> list_() {
		 StringBuffer sb;
		 sb = new StringBuffer("FROM Shetuan ");
		 List<Shetuan> lst = findByHQL(sb.toString());
	     return lst;
	}

	@Override
	public Shetuan load_by_name(String name) {
		 StringBuffer sb;
		 sb = new StringBuffer("FROM Shetuan where name='").append(name).append("'");
		 List<Shetuan> lst = findByHQL(sb.toString());
	     if(lst.size()>0){
	    	 return lst.get(0);
	     }else{
	    	 return null;
	     }
	}

	@Override
	public boolean save(Message message) {
		// TODO Auto-generated method stub
		return saveOrUpdate(message);
	}

	@Override
	public List<Message> listbyuserid(int touserid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("FROM Message where userid =").append(touserid).append(" order by id desc");

		List<Message> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public List<Message> listmsg() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("FROM Message  order by id desc");

		List<Message> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public boolean delmsg(Message message) {
		// TODO Auto-generated method stub
		return deleteById(Message.class, message.getId());
	}

	@Override
	public boolean reply(Message message) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("update Message set reply='").append(message.getContent()).append("'  where id = ")
				.append(message.getId());

		try {
			executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Message> listbyauthor(String author) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("FROM Message where author ='").append(author).append("' or username like '%").append(author).append("%' order by id desc");

		List<Message> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public boolean checkcomments(Comments comments) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Comments where luxianid=").append(comments.getLuxianid()).append(" and userid =").append(comments.getUserid());

		List<Comments> lst = findByHQL(sb.toString());
		
		if(lst.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean checkmsg(Message message) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Message where luxianid=").append(message.getLuxianid()).append(" and userid =").append(message.getUserid());

		List<Message> lst = findByHQL(sb.toString());
		
		if(lst.size()>0){
			return true;
		}else{
			return false;
		}
	}


}
