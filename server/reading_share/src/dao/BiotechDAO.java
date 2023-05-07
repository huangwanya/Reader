package dao;


import java.util.List;

import entity.Baoming;
import entity.Biotech;
import entity.Folder;
import entity.Jubao;
import entity.User;
import entity.Zan;





public interface BiotechDAO {
//
	public boolean addfolder(Folder folder);
	public boolean checkfolder(Folder folder);
	public boolean cancelfolder(Folder folder);
	public int get_folder_count(int id);
	public int get_folder_count_user(int userid);
	
	public int getuseridbybioid(int bioid);
	//
	public boolean addzan(Zan zan);
	public boolean checkzan(Zan zan);
	public boolean cancelzan(Zan zan);
	public int get_zan_count(int id);
	//
	
	public int get_comments_count(int id);
	//
	public int get_msg_count(int id);
	public int get_msg_countbyuser(String author);
	//
	public boolean addjubao(Jubao jubao);
	public boolean checkjubao(Jubao jubao);
	public int get_jubao_count(int id);
	
	
	
	public boolean save(Biotech biotech);
	public boolean del(Biotech biotech);
	public boolean shenhe(Biotech biotech);
	public boolean update_count(Biotech biotech);
	public boolean shenheck(Biotech biotech);
	public boolean dianzan(Biotech biotech);
	public Biotech load(int id);
	public boolean update(Biotech biotech);
	public List<Biotech> list(String name);
	public List<Biotech> listnews();
	public List<Biotech> listtiezi();
	public List<Biotech> listhuodong();
	public List<Biotech> listproj();
	public List<Biotech> listck();
	public List<Biotech> selectfolderids(int userid);
	
	public List<Biotech> listbyauthor(String author);
	public int get_count_byauthor2(String author);
	public int get_count_byauthor1(String author);
	public int get_count_byauthor3(String author);
	public int get_baoming_count_byauthor(int userid);
	
	
	public List<Biotech> listbyauthor1(String author);
	public List<Biotech> listbyauthor3(String author);
	public List<Biotech> listbyauthor4(String author);
	public List<Biotech> listbyauthor_(String author);
	public List<Biotech> listall(String name);
	public List<Biotech> list0();
	public List<Biotech> list0_head();
	public List<Biotech> list3_head();
	public List<Biotech> list1();
	public List<Biotech> list2();
	public List<Biotech> list3();
	public List<Biotech> list4();
	
	public boolean addBaoming(Baoming baoming);
	public boolean checkbaoming(int bioid,int userid);
	public boolean checkbaomingshenhe(int bioid,int userid);
	public boolean delbaoming(int bioid,int userid);
	public boolean shenhebaoming(int bioid,int userid);
	public List<Biotech> selectBaomingids(int userid);
	public List<Biotech> selecttuijian(int userid);
	public List<User> selectUser(int bioid);
	public List<Baoming> selectBaomings(int biotechid);
	
	
	public List<Biotech> listbytype0(int type_id ,String author);
	public List<Biotech> listbytype1(int type_id,String author);
	public List<Biotech> listbytype2(int type_id,String author);
	public List<Biotech> listbytype2_head();
	
	
}
