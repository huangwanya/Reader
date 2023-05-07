package dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.BiotechDAO;
import entity.Baoming;
import entity.Biotech;
import entity.Folder;
import entity.Jubao;
import entity.User;
import entity.Zan;

public class BiotechDaoimpl extends BaseDAO implements BiotechDAO {

	@Override
	public boolean save(Biotech biotech) {
		// TODO Auto-generated method stub
		return saveOrUpdate(biotech);
	}

	@Override
	public boolean del(Biotech biotech) {
		this.deleteById(Biotech.class, biotech.getId());
		return true;
	}

	@Override
	public Biotech load(int id) {
		StringBuffer sb = new StringBuffer("FROM Biotech WHERE id = ")
				.append(id);

		List<Biotech> lst = findByHQL(sb.toString());
		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean update(Biotech biotech) {
		// TODO Auto-generated method stub
		return saveOrUpdate(biotech);
	}

	@Override
	public List<Biotech> list(String name) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE status = 1");

		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public List<Biotech> list0() {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech where  type = '").append("1")
				.append("'");
		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public List<Biotech> list1() {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech where  type = '").append(" ")
				.append("'");
		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}
	@Override
	public List<Biotech> list2() {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech where  type = '").append(" ")
				.append("'");
		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}
	@Override
	public List<Biotech> list3() {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech where  type = '").append("3")
				.append("'");
		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}
	@Override
	public List<Biotech> list4() {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech where  type = '").append("4")
				.append("'");
		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public boolean shenhe(Biotech biotech) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("update Biotech set status2 = 1  where id = ")
				.append(biotech.getId());

		try {
			executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Biotech> listall(String name) {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech ");

		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public boolean dianzan(Biotech biotech) {
		StringBuffer sb;
		sb = new StringBuffer("update Biotech set zan =zan + 1  where id = ")
				.append(biotech.getId());

		try {
			executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Biotech> listbyauthor(String author) {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE type='2' and  author = '").append(author)
				.append("'");

		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}
	@Override
	public List<Biotech> listbyauthor1(String author) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE type='1' and  author = '").append(author)
				.append("'");

		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}
	@Override
	public List<Biotech> listbyauthor3(String author) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE type='3' and  author = '").append(author)
				.append("'");

		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public List<Biotech> listbyauthor4(String author) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE type='4' and  author = '").append(author)
				.append("'");

		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}
	
	
	
	
	
	@Override
	public List<Biotech> listbyauthor_(String author) {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE type='0' and  author = '").append(author)
		.append("'");
		
		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public boolean addfolder(Folder folder) {
		// TODO Auto-generated method stub
		return saveOrUpdate(folder);
	}

	@Override
	public List<Biotech> selectfolderids(int userid) {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE id in (select distinct(duanziid) FROM Folder WHERE userid = ").append(userid).append(")");

		List<Biotech> lst = findByHQL(sb.toString());
		
		return lst;
	}

	@Override
	public List<Biotech> listnews() {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE type = '").append(1)
				.append("'");

		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public List<Biotech> listtiezi() {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE type = '").append(0)
				.append("'");

		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public List<Biotech> listproj() {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE type = '").append(2)
				.append("'");

		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public List<Biotech> listck() {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE type = '").append(3)
				.append("'");

		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public boolean shenheck(Biotech biotech) {
		StringBuffer sb;
		sb = new StringBuffer("update Biotech set ck_status = 1  where id = ")
				.append(biotech.getId());

		try {
			executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Biotech> listhuodong() {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE type = '").append(2)
				.append("'");

		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}

	@Override
	public boolean addBaoming(Baoming baoming) {
		// TODO Auto-generated method stub
		return saveOrUpdate(baoming);
	}

	@Override
	public List<Biotech> selectBaomingids(int userid) {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE id in (select distinct(bioid) FROM Baoming WHERE userid = ").append(userid).append(")");

		List<Biotech> lst = findByHQL(sb.toString());
		
		return lst;
	}

	@Override
	public List<Baoming> selectBaomings(int biotechid) {
		StringBuffer sb;
		sb = new StringBuffer("FROM Baoming where bioid=").append(biotechid);

		List<Baoming> lst = findByHQL(sb.toString());
		
		return lst;
	}

	@Override
	public boolean checkbaoming(int bioid, int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Baoming where bioid=").append(bioid).append(" and userid =").append(userid);

		List<Baoming> lst = findByHQL(sb.toString());
		if(lst.size()>0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean delbaoming(int bioid, int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("delete from Baoming where bioid=").append(bioid).append(" and userid =").append(userid);
		try {
			this.executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public List<User> selectUser(int bioid) {
		StringBuffer sb;
		sb = new StringBuffer("FROM User WHERE id in (select distinct(userid) FROM Baoming WHERE bioid = ").append(bioid).append(")");
		List<User> lst = findByHQL(sb.toString());
		return lst;
	}
	
	@Override
	public List<Biotech> selecttuijian(int userid) {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech WHERE status2=1 and type_id in(select  distinct(type_id) FROM Biotech WHERE id in (select distinct(bioid) FROM Baoming WHERE userid = ").append(userid).append("))");

		List<Biotech> lst = findByHQL(sb.toString());
		
		return lst;
	}
	

	@Override
	public List<Biotech> listbytype0(int type_id,String author) {
		 StringBuffer sb;
		 sb = new StringBuffer("FROM Biotech where type_id=").append(type_id).append(" and status=0 and author ='").append(author).append("' order by id desc");
		 List<Biotech> lst = findByHQL(sb.toString());
	     return lst;
	}
	@Override
	public List<Biotech> listbytype1(int type_id,String author) {
		 StringBuffer sb;
		 String now =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		 sb = new StringBuffer("FROM Biotech where type_id=").append(type_id).append(" and type='2' and huodong_date >=now()  order by id desc");
		 List<Biotech> lst = findByHQL(sb.toString());
	     return lst;
	}
	@Override
	public List<Biotech> listbytype2(int type_id,String author) {
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech where type='0' order by id desc");
		List<Biotech> lst = findByHQL(sb.toString());
		return lst;
	}


	@Override
	public boolean update_count(Biotech biotech) {
		StringBuffer sb;
		sb = new StringBuffer("update Biotech set count_1 = count_1+1  where id = ")
				.append(biotech.getId());

		try {
			executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean shenhebaoming(int bioid, int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("update Baoming set status=1 where bioid=").append(bioid).append(" and userid =").append(userid);
		try {
			this.executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean checkbaomingshenhe(int bioid, int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Baoming where bioid=").append(bioid).append(" and userid =").append(userid);

		List<Baoming> lst = findByHQL(sb.toString());
		if(lst.size()>0){
			Baoming baoming=lst.get(0);
			if(baoming.getStatus()==1){
				return true;
			}else{
				return false;
			}
			
		}else{
			return false;
		}
	}

	@Override
	public List<Biotech> list0_head() {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech where  type = '").append("1")
				.append("'");
		List<Biotech> lst = findTop(sb.toString(),2);
		return lst;
	}

	@Override
	public List<Biotech> listbytype2_head() {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech where type='0' order by id desc");
		List<Biotech> lst = findTop(sb.toString(),2);
		return lst;
	}

	@Override
	public boolean checkfolder(Folder folder) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Folder where duanziid=").append(folder.getDuanziid()).append(" and userid =").append(folder.getUserid());

		List<Folder> lst = findByHQL(sb.toString());
		
		if(lst.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int get_folder_count(int id) {
		// TODO Auto-generated method stub
		
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM Folder where duanziid=").append(id).append("");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}

	@Override
	public boolean cancelfolder(Folder folder) {
		// TODO Auto-generated method stub
		
		StringBuffer sb = new StringBuffer();
		sb.append("delete from Folder where duanziid=").append(folder.getDuanziid()).append(" and userid =").append(folder.getUserid());
		try {
			this.executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean addzan(Zan zan) {
		// TODO Auto-generated method stub
		return saveOrUpdate(zan);
	}

	@Override
	public boolean checkzan(Zan zan) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Zan where duanziid=").append(zan.getDuanziid()).append(" and userid =").append(zan.getUserid());

		List<Zan> lst = findByHQL(sb.toString());
		
		if(lst.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean cancelzan(Zan zan) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("delete from Zan where duanziid=").append(zan.getDuanziid()).append(" and userid =").append(zan.getUserid());
		try {
			this.executeHql(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public int get_zan_count(int id) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM Zan where duanziid=").append(id).append("");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}

	@Override
	public int get_comments_count(int id) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM Comments where luxianid=").append(id).append("");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}

	@Override
	public int get_msg_count(int id) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM Message where luxianid=").append(id).append("");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}

	@Override
	public boolean addjubao(Jubao jubao) {
		// TODO Auto-generated method stub
		return saveOrUpdate(jubao);
	}

	@Override
	public boolean checkjubao(Jubao jubao) {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Jubao where duanziid=").append(jubao.getDuanziid()).append(" and userid =").append(jubao.getUserid());

		List<Jubao> lst = findByHQL(sb.toString());
		
		if(lst.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int get_jubao_count(int id) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM Jubao where duanziid=").append(id).append("");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}

	@Override
	public List<Biotech> list3_head() {
		// TODO Auto-generated method stub
		StringBuffer sb;
		sb = new StringBuffer("FROM Biotech where type='3' order by id desc");
		List<Biotech> lst = findTop(sb.toString(),2);
		return lst;
	}

	@Override
	public int get_folder_count_user(int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM Folder where userid=").append(userid).append("");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}
	@Override
	public int get_count_byauthor2(String author) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM Biotech WHERE type='2' and  author = '").append(author)
		.append("'");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}

	@Override
	public int get_count_byauthor1(String author) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM Biotech WHERE type='1' and  author = '").append(author)
		.append("'");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}

	@Override
	public int get_count_byauthor3(String author) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM Biotech WHERE type='0' and  author = '").append(author)
		.append("'");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}
	
	@Override
	public int get_baoming_count_byauthor(int userid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM  Baoming WHERE userid = ").append(userid).append("");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}

	@Override
	public int get_msg_countbyuser(String author) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("select count(*) FROM Message where author='").append(author).append("' or username ='").append(author).append("'");
		return Integer.parseInt(getCountByHQL(sb.toString())+"");
	}

	@Override
	public int getuseridbybioid(int bioid) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb = new StringBuffer("FROM User WHERE  username in (select author from Biotech where id=").append(bioid)
		.append(")");
		
		List<User> users=findByHQL(sb.toString());
		if(users.size() >0){
			return users.get(0).getId();
		}else{
			return 0;
		}
		
	}





}
