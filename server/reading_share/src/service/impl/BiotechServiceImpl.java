package service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import service.BiotechService;


import dao.BiotechDAO;
import dao.CommentsDAO;
import dao.UserDAO;
import entity.Baoming;
import entity.Biotech;
import entity.Folder;
import entity.Jubao;
import entity.Shetuan;
import entity.User;
import entity.Zan;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class BiotechServiceImpl implements BiotechService {
	private BiotechDAO biotechdao;
	private UserDAO userdao;
	private CommentsDAO commentsdao;

	@Override
	public String detail(int goodsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Biotech biotech) {
		// TODO Auto-generated method stub
		biotech.setPubdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return biotechdao.save(biotech);
	}



	@Override
	public List<Biotech> search(String name) {
		// TODO Auto-generated method stub
		return biotechdao.list(name);
	}

	@Override
	public Biotech load(int goodsId) {
		// TODO Auto-generated method stub
		return biotechdao.load(goodsId);
	}

	@Override
	public void del(Biotech jingdian) {
		// TODO Auto-generated method stub
		
		jingdian = biotechdao.load(jingdian.getId());
		if(jingdian.getType().equals("2")){
			List<User> list =biotechdao.selectUser(jingdian.getId());
			
			for(User user:list){
				userdao.deducthours(jingdian.getHours(), user.getId());
			}
		}
		biotechdao.del(jingdian);
	}

	@Override
	public String loadAllJson() {
		List<Biotech> list = biotechdao.list(null);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public String detailjson(String id) {
		// TODO Auto-generated method stub
		Integer goodid= Integer.parseInt(id);
		Biotech good = this.load(goodid);
		if(good !=null){
			good.setComments(biotechdao.get_comments_count(goodid));
			good.setFavorite(biotechdao.get_folder_count(goodid));
			good.setJubao(biotechdao.get_jubao_count(goodid));
			good.setZan(biotechdao.get_zan_count(goodid));
			good.setMessage(biotechdao.get_msg_count(goodid));
		}
		if(good != null){
			JSONObject obj = JSONObject.fromObject(good);
			return obj.toString();
		}else{
			return null;
		}
		
	}




	@Override
	public List<Biotech> listbiotech() {
		// TODO Auto-generated method stub
		return biotechdao.listall(null);
	}

	public BiotechDAO getBiotechdao() {
		return biotechdao;
	}

	public void setBiotechdao(BiotechDAO biotechdao) {
		this.biotechdao = biotechdao;
	}




	@Override
	public boolean shenhe(Biotech biotech) {
		// TODO Auto-generated method stub
		return biotechdao.shenhe(biotech);
	}

	@Override
	public boolean dianzan(Biotech biotech) {
		// TODO Auto-generated method stub
		return biotechdao.dianzan(biotech);
	}

	@Override
	public String loadbyauthor(String author) {
		List<Biotech> list = biotechdao.listbyauthor(author);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	@Override
	public String loadbyauthor1(String author) {
		List<Biotech> list = biotechdao.listbyauthor1(author);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	@Override
	public String listbyauthor_(String author) {
		List<Biotech> list = biotechdao.listbyauthor_(author);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public boolean addfolder(Folder folder) {
		// TODO Auto-generated method stub
		return biotechdao.addfolder(folder);
	}

	@Override
	public String loadbyfolder(int userid) {
		List<Biotech> list = biotechdao.selectfolderids(userid);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public String loadAllJson0() {
		List<Biotech> list = biotechdao.listtiezi();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public String loadAllJson1() {
		List<Biotech> list = biotechdao.listnews();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	@Override
	public String loadAllJson2() {
		List<Biotech> list = biotechdao.listhuodong();
		List<Biotech> list2 = new ArrayList<Biotech>();
		String now =new SimpleDateFormat("yyyyMMdd").format(new Date());
		int now_ = Integer.parseInt(now);
		for(Biotech b:list){
			String huodong_date = b.getHuodong_date();
			if(huodong_date !=null){
				Date huodong_date_ = null;
				try {
					huodong_date_ = new SimpleDateFormat("yyyy-MM-dd").parse(huodong_date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(huodong_date_ !=null){
					String then__ =new SimpleDateFormat("yyyyMMdd").format(huodong_date_);
					int then_=Integer.parseInt(then__);
					if(now_<then_){
						list2.add(b);
					}
				}
			}else{
				b.setHuodong_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				b.setHuodong_addr("");
				list2.add(b);
			}
		}
		
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list2);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	@Override
	public String loadAllJson3() {
		List<Biotech> list = biotechdao.listck();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}
	@Override
	public List<Biotech> listnews() {
		List<Biotech> list = biotechdao.listnews();
		return list;
	}

	@Override
	public List<Biotech> listtiezi() {
		List<Biotech> list = biotechdao.listtiezi();
		return list;
	}
	@Override
	public List<Biotech> list3() {
		List<Biotech> list = biotechdao.list3();
//		List<Biotech> list2=new ArrayList<Biotech>(); 
//		for(Biotech b:list){
//			if(b.getType_id()>0){
//				Shetuan shetuan =commentsdao.load_(b.getType_id());
//				if(shetuan !=null){
//					b.setShetuan(shetuan.getName());
//					list2.add(b);
//				}
//				
//			}
//		}
		
		return list;
	}

	@Override
	public List<Biotech> list4() {
		List<Biotech> list = biotechdao.list4();
		List<Biotech> list2=new ArrayList<Biotech>(); 
		for(Biotech b:list){
			if(b.getType_id()>0){
				Shetuan shetuan =commentsdao.load_(b.getType_id());
				if(shetuan !=null){
					b.setShetuan(shetuan.getName());
					list2.add(b);
				}
				
			}
		}
		
		return list2;
	}

	@Override
	public boolean shenheck(Biotech biotech) {
		// TODO Auto-generated method stub
		return biotechdao.shenheck(biotech);
	}

	public UserDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}

	@Override
	public List<Biotech> listhuodong() {
		List<Biotech> list = biotechdao.listhuodong();
		return list;
	}

	@Override
	public String loadJson0() {
		List<Biotech> list = biotechdao.list0();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public String loadJson1() {
		List<Biotech> list = biotechdao.list1();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public String loadJson2() {
		List<Biotech> list = biotechdao.list2();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public String loadJson3() {
		List<Biotech> list = biotechdao.list3();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public String loadJson4() {
		List<Biotech> list = biotechdao.list4();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public boolean addBaoming(Baoming baoming) {
		// TODO Auto-generated method stub
	    Biotech b = biotechdao.load(baoming.getBioid());
	    if(b !=null){
	    	userdao.addhours(b.getHours(), baoming.getUserid());
	    	biotechdao.update_count( b);
	    }
	    
	    User user = userdao.load(baoming.getUserid());
	    if(user !=null){
	    	baoming.setTel(user.getPhone());
	    }
		return biotechdao.addBaoming(baoming);
	}

	@Override
	public String selectBaomingids(int userid) {
		List<Biotech> list = biotechdao.selectBaomingids(userid);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public List<Baoming> selectBaomings(int biotechid) {
		// TODO Auto-generated method stub
		return biotechdao.selectBaomings(biotechid);
	}

	@Override
	public boolean checkbaoming(int bioid, int userid) {
		// TODO Auto-generated method stub
		return biotechdao.checkbaoming(bioid, userid);
	}

	@Override
	public boolean delbaoming(int bioid, int userid) {
		// TODO Auto-generated method stub
		
		  Biotech b = biotechdao.load(bioid);
		    if(b !=null){
		    	userdao.deducthours(b.getHours(), userid);
		    }
		return biotechdao.delbaoming(bioid, userid);
	}

	@Override
	public String selectUser(int bioid) {
		// TODO Auto-generated method stub
		List<User> list =biotechdao.selectUser(bioid);
		List<User> list2 =new ArrayList<User>();
		
		for(User user:list){
			if(biotechdao.checkbaomingshenhe(bioid, user.getId())){
				user.setBaoming_status(1);
			}else{
				user.setBaoming_status(0);
			}
			
			list2.add(user);
		}
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list2);
			return jsonarr.toString();
		}else{
			return null;
		}
	}


	@Override
	public String listbytype0(int type_id, String author) {
		List<Biotech> list = biotechdao.listbytype0(type_id,author);
		if (list.size() > 0) {
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		} else {
			return null;
		}
	}

	@Override
	public String listbytype1(int type_id, String author) {
		List<Biotech> list = biotechdao.listbytype1(type_id,author);
		if (list.size() > 0) {
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		} else {
			return null;
		}
	}
	@Override
	public String listbytype2(int type_id, String author) {
		List<Biotech> list = biotechdao.listbytype2(type_id,author);
		if (list.size() > 0) {
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		} else {
			return null;
		}
	}

	@Override
	public String list_all() {
			return null;
	}

	@Override
	public String list_(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loadbyauthor3(String author) {
		// TODO Auto-generated method stub
		List<Biotech> list = biotechdao.listbyauthor3(author);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public String loadbyauthor4(String author) {
		// TODO Auto-generated method stub
		List<Biotech> list = biotechdao.listbyauthor4(author);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	public CommentsDAO getCommentsdao() {
		return commentsdao;
	}

	public void setCommentsdao(CommentsDAO commentsdao) {
		this.commentsdao = commentsdao;
	}

	@Override
	public boolean shenhebaoming(int bioid, int userid) {
		// TODO Auto-generated method stub
		return biotechdao.shenhebaoming(bioid, userid);
	}

	@Override
	public String selecttuijian(int userid) {
		// TODO Auto-generated method stub
		List<Biotech> list = biotechdao.selecttuijian(userid);
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public String loadJson0_head() {
		// TODO Auto-generated method stub
		List<Biotech> list = biotechdao.list0_head();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public String listbytype2_head() {
		// TODO Auto-generated method stub
		List<Biotech> list = biotechdao.listbytype2_head();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public boolean checkfolder(Folder folder) {
		// TODO Auto-generated method stub
		return biotechdao.checkfolder(folder);
	}

	@Override
	public boolean cancelfolder(Folder folder) {
		// TODO Auto-generated method stub
		return biotechdao.cancelfolder(folder);
	}

	@Override
	public int get_folder_count(int id) {
		// TODO Auto-generated method stub
		return biotechdao.get_folder_count(id);
	}

	@Override
	public boolean checkzan(Zan zan) {
		// TODO Auto-generated method stub
		return biotechdao.checkzan(zan);
	}

	@Override
	public boolean cancelzan(Zan zan) {
		// TODO Auto-generated method stub
		return biotechdao.cancelzan(zan);
	}

	@Override
	public boolean addjubao(Jubao jubao) {
		// TODO Auto-generated method stub
		return biotechdao.addjubao(jubao);
	}

	@Override
	public boolean checkjubao(Jubao jubao) {
		// TODO Auto-generated method stub
		return biotechdao.checkjubao(jubao);
	}

	@Override
	public boolean addzan(Zan zan) {
		// TODO Auto-generated method stub
		return biotechdao.addzan(zan);
	}

	@Override
	public String list3_head() {
		// TODO Auto-generated method stub
		List<Biotech> list = biotechdao.list3_head();
		if(list.size()>0){
			JSONArray jsonarr = JSONArray.fromObject(list);
			return jsonarr.toString();
		}else{
			return null;
		}
	}

	@Override
	public int getuseridbybioid(int bioid) {
		// TODO Auto-generated method stub
		return biotechdao.getuseridbybioid(bioid);
	}

}
