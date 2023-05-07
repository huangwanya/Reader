package service;

import java.util.List;

import entity.Baoming;
import entity.Biotech;
import entity.Folder;
import entity.Jubao;
import entity.Zan;






public interface BiotechService {
	
	public boolean addfolder(Folder folder);
	public boolean save(Biotech biotech);
	public boolean shenhe(Biotech biotech);
	public boolean shenheck(Biotech biotech);
	public void del(Biotech biotech);
	public String list();
	public List<Biotech> listbiotech();
	public List<Biotech> listnews();
	public List<Biotech> listhuodong();
	public String list3_head();
	public List<Biotech> listtiezi();
	public List<Biotech> list3();
	public List<Biotech> list4();
	public int getuseridbybioid(int bioid);
	
	
	
	public String detail(int id);
	public Biotech load(int id);
	public List<Biotech> search(String name);
	public String loadAllJson();
	public String loadbyfolder(int userid);
	public String loadbyauthor(String author);
	public String loadbyauthor1(String author);
	public String loadbyauthor3(String author);
	public String loadbyauthor4(String author);
	public String listbyauthor_(String author);
	public String loadAllJson0();
	public String loadAllJson1();
	public String loadAllJson2();
	public String loadAllJson3();
	public String detailjson(String id);;
	
	public boolean dianzan(Biotech biotech);
	
	public String loadJson0();
	public String loadJson0_head();
	public String loadJson1();
	public String loadJson2();
	public String loadJson3();
	public String loadJson4();
	
	public boolean addBaoming(Baoming baoming);
	public String selectBaomingids(int userid);
	public String selecttuijian(int userid);
	public List<Baoming> selectBaomings(int biotechid);
	public boolean checkbaoming(int bioid, int userid);
	public boolean delbaoming(int bioid, int userid);
    public String selectUser(int bioid);
    public boolean shenhebaoming(int bioid, int userid) ;
    
    
	public String list_(int userid);
	public String list_all();
	public String listbytype0(int type_id ,String author);
	public String listbytype1(int type_id,String author);
	public String listbytype2(int type_id,String author);
	public String listbytype2_head();
	
	
	
	//
	//
	public boolean checkfolder(Folder folder);
	public boolean cancelfolder(Folder folder);
	public int get_folder_count(int id);
	//
	public boolean checkzan(Zan zan);
	public boolean cancelzan(Zan zan);
	public boolean addzan(Zan zan);
	//
	
	//
	//
	public boolean addjubao(Jubao jubao);
	public boolean checkjubao(Jubao jubao);
	
	

}
