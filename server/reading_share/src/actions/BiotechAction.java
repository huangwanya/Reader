package actions;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import service.BiotechService;
import utils.CreateId;
import utils.Utils;

import entity.Baoming;
import entity.Biotech;
import entity.Folder;
import entity.Jubao;
import entity.Zan;

public class BiotechAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BiotechService bioservice;
	private Biotech biotech;
	private String jsonString;
	private String id;
	private List<Biotech> list = new ArrayList<Biotech>();
	private List<Baoming> blist = new ArrayList<Baoming>();
	private String keyword;
	private String author;
	private Folder folder;
	private int bioid;
	private int hours;
	private String realpath1;
	private Baoming baoming;
	private int userid,userid2,type_id;
	
	private Zan zan;
	private Jubao jubao;
	
//	public boolean checkfolder(Folder folder);
//	public boolean cancelfolder(Folder folder);
//	public int get_folder_count(int id);
//	//
//	public boolean checkzan(Zan zan);
//	public boolean cancelzan(Zan zan);
//	//
//	//
//	public boolean addjubao(Jubao jubao);
//	public boolean checkjubao(Jubao jubao);
	
	
	public String checkfolder() {
		boolean flag = bioservice.checkfolder(folder);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	public String cancelfolder() {
		boolean flag = bioservice.cancelfolder(folder);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	public String checkzan() {
		boolean flag = bioservice.checkzan(zan);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	public String cancelzan() {
		boolean flag = bioservice.cancelzan(zan);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	public String dianzan() {

		boolean flag = bioservice.addzan(zan);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	public String addjubao() {
		boolean flag = bioservice.addjubao(jubao);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	public String checkjubao() {
		boolean flag = bioservice.checkjubao(jubao);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	
	  private ByteArrayInputStream inputStream;  
	  
	    public ByteArrayInputStream getInputStream() {  
	        return inputStream;  
	    }  
	  
	    public void setInputStream(ByteArrayInputStream inputStream) {  
	        this.inputStream = inputStream;  
	    }  
		public String qrcode() {
			int good_id = biotech.getId();
			 String realpath = ServletActionContext.getServletContext().getRealPath(
			"/qrcode");
			 
			 realpath1 = realpath+"/core.gif";
			
			int BLACK = 0xFF000000;
			int WHITE = 0xFFFFFFFF;
			File f = new File(realpath);
			int width = 300;
			int height = 300;
			//二维码的图片格式  
			Hashtable hints = new Hashtable();
			//内容所使用编码  
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = null;
			try {
				bitMatrix = new MultiFormatWriter().encode(good_id+"",
						BarcodeFormat.QR_CODE, width, height, hints);
			} catch (WriterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
				}
			}
			
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();  
			   try {  
			   ImageIO.write(image, "JPEG", outputStream);  
			   
			   ByteArrayInputStream input=new ByteArrayInputStream(outputStream.toByteArray());  
			   this.setInputStream(input); 
			   } catch (Exception e) {  
			       e.printStackTrace();  
			   }  
			return SUCCESS;
		}
	public String save() {
		String realpath = ServletActionContext.getServletContext().getRealPath(
				"/upload");

		File file_ = new File(realpath);
		if (!file_.exists()) {
			file_.mkdirs();
		}
		String filename = biotech.getFileFileName();
		String extention = Utils.getExtensionName(filename);
		filename = CreateId.getId() + "." + extention;
		// jingdian.setImage_url(filename);

		try {
			FileUtils.copyFile(biotech.getFile(), new File(file_, filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		biotech.setImage_url("upload" + "\\" + filename);
		biotech.setStatus(1);
		bioservice.save(biotech);

		return SUCCESS;
	}

	public String savenews() {
		String realpath = ServletActionContext.getServletContext().getRealPath(
				"/upload");

		File file_ = new File(realpath);
		if (!file_.exists()) {
			file_.mkdirs();
		}
		String filename = biotech.getFileFileName();
		String extention = Utils.getExtensionName(filename);
		filename = CreateId.getId() + "." + extention;
		// jingdian.setImage_url(filename);

		try {
			FileUtils.copyFile(biotech.getFile(), new File(file_, filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		biotech.setImage_url("upload" + "\\" + filename);
		bioservice.save(biotech);

		return SUCCESS;
	}
	public String save3() {
		String realpath = ServletActionContext.getServletContext().getRealPath(
		"/upload");
		
		File file_ = new File(realpath);
		if (!file_.exists()) {
			file_.mkdirs();
		}
		String filename = biotech.getFileFileName();
		String extention = Utils.getExtensionName(filename);
		filename = CreateId.getId() + "." + extention;
		// jingdian.setImage_url(filename);
		
		try {
			FileUtils.copyFile(biotech.getFile(), new File(file_, filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		biotech.setImage_url("upload" + "\\" + filename);
		bioservice.save(biotech);
		
		return SUCCESS;
	}
	public String savehuodong() {
		String realpath = ServletActionContext.getServletContext().getRealPath(
		"/upload");
		
		File file_ = new File(realpath);
		if (!file_.exists()) {
			file_.mkdirs();
		}
		String filename = biotech.getFileFileName();
		String extention = Utils.getExtensionName(filename);
		filename = CreateId.getId() + "." + extention;
		// jingdian.setImage_url(filename);
		
		try {
			FileUtils.copyFile(biotech.getFile(), new File(file_, filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		biotech.setImage_url("upload" + "\\" + filename);
		bioservice.save(biotech);
		
		return SUCCESS;
	}

	public String saveproj() {
		bioservice.save(biotech);
		boolean flag = bioservice.save(biotech);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	public String checkbaoming() {
		
//		biotech.setType("2");
		boolean flag =bioservice.checkbaoming(bioid,userid);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	public String delbaoming() {
		
//		biotech.setType("2");
		boolean flag =bioservice.delbaoming(bioid,userid);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	public String shenhebaoming() {
		
//		biotech.setType("2");
		int userid3=bioservice.getuseridbybioid(bioid);
		if(userid3>0){
			if(userid3 !=userid2){
				jsonString="3";
			}else{
				boolean flag =bioservice.shenhebaoming(bioid,userid);
				if (flag) {
					jsonString = "1";
				} else {
					jsonString = "0";
				}	
			}
		}else{
			jsonString="0";
		}
		
		
		return SUCCESS;
	}



	public String addfolder() {

		boolean flag = bioservice.addfolder(folder);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	public String addbaoming() {
		baoming.setUpdatedate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		boolean flag2=bioservice.checkbaoming(baoming.getBioid(), baoming.getUserid());
		
		if(flag2){
			jsonString="3";
		}
		else{
			boolean flag = bioservice.addBaoming(baoming);
			if (flag) {
				jsonString = "1";
			} else {
				jsonString = "0";
			}
		}

		return SUCCESS;
	}

	public String list() {
		list = bioservice.listbiotech();
		
		return SUCCESS;
	}
	public String listbaoming() {
		blist = bioservice.selectBaomings(bioid);

		return SUCCESS;
	}
	public String listbaomingjson() {
		jsonString = bioservice.selectBaomingids(userid);
		
		return SUCCESS;
	}
	public String selecttuijian() {
		jsonString = bioservice.selecttuijian(userid);
		
		return SUCCESS;
	}
	public String listuserjson() {
		jsonString = bioservice.selectUser(bioid);
		
		return SUCCESS;
	}
	
	
		 public String list_type(){
			 jsonString=bioservice.list_(userid);
			 return SUCCESS;
		 }
		 
		 public String list_all(){
			 jsonString=bioservice.list_all();
			 return SUCCESS;
		 }
		 
		 public String listbytype0(){
			 jsonString=bioservice.listbytype0(type_id, author);
			 return SUCCESS;
		 }
		 public String listbytype1(){
			 jsonString=bioservice.listbytype1(type_id, author);
			 return SUCCESS;
		 }
		 public String listbytype2(){
			 jsonString=bioservice.listbytype2(type_id, author);
			 return SUCCESS;
		 }
		 public String listbytype2_head(){
			 jsonString=bioservice.listbytype2_head();
			 return SUCCESS;
		 }
		 public String list3_head(){
			 jsonString=bioservice.list3_head();
			 return SUCCESS;
		 }

	public String uploadarticle() {
		String realpath = ServletActionContext.getServletContext().getRealPath(
				"/upload");

		File file_ = new File(realpath);
		if (!file_.exists()) {
			file_.mkdirs();
		}
		String filename = biotech.getFileFileName();
		String extention = Utils.getExtensionName(filename);
		filename = CreateId.getId() + "." + extention;
		// jingdian.setImage_url(filename);

		try {
			FileUtils.copyFile(biotech.getFile(), new File(file_, filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if(biotech.getFuzeren()!=null){
				if(!biotech.getFuzeren().equals("")&&biotech.getFuzeren()!=null){
					biotech.setFuzeren(URLDecoder.decode(biotech.getFuzeren(), "utf-8"));
				}
				if(!biotech.getTel().equals("")&&biotech.getTel()!=null){
					biotech.setTel(URLDecoder.decode(biotech.getTel(), "utf-8"));
				}
				if(!biotech.getXingzhi().equals("")&&biotech.getXingzhi()!=null){
					biotech.setXingzhi(URLDecoder.decode(biotech.getXingzhi(), "utf-8"));
				}
				if(!biotech.getIshaibao().equals("")&&biotech.getIshaibao()!=null){
					biotech.setIshaibao(URLDecoder.decode(biotech.getIshaibao(), "utf-8"));
				}
			}
	
			biotech.setHuodong_date(URLDecoder.decode(biotech.getHuodong_date(), "utf-8"));
			biotech.setHuodong_addr(URLDecoder.decode(biotech.getHuodong_addr(), "utf-8"));
//			biotech.setZuobiao(URLDecoder.decode(biotech.getZuobiao(), "utf-8"));
			biotech.setAuthor(URLDecoder.decode(biotech.getAuthor(), "utf-8"));
			biotech.setContent(URLDecoder.decode(biotech.getContent(), "utf-8"));
			biotech.setTitle(URLDecoder.decode(biotech.getTitle(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		biotech.setPubdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date()));
		biotech.setImage_url("upload" + "\\" + filename);
		biotech.setStatus(0);
		boolean flag = bioservice.save(biotech);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	public String uploadarticle_() {
		String realpath = ServletActionContext.getServletContext().getRealPath(
				"/upload");

		File file_ = new File(realpath);
		if (!file_.exists()) {
			file_.mkdirs();
		}
		String filename = biotech.getFileFileName();
		String extention = Utils.getExtensionName(filename);
		filename = CreateId.getId() + "." + extention;
		// jingdian.setImage_url(filename);

		try {
			FileUtils.copyFile(biotech.getFile(), new File(file_, filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			biotech.setAuthor(URLDecoder.decode(biotech.getAuthor(), "utf-8"));
			biotech.setContent(URLDecoder.decode(biotech.getContent(), "utf-8"));
			biotech.setType2(URLDecoder.decode(biotech.getType2(), "utf-8"));
			biotech.setTitle(URLDecoder.decode(biotech.getTitle(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		biotech.setPubdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date()));
		biotech.setImage_url("upload" + "\\" + filename);
		biotech.setStatus(1);
		boolean flag = bioservice.save(biotech);
		if (flag) {
			jsonString = "1";
		} else {
			jsonString = "0";
		}
		return SUCCESS;
	}
	public String add() {
		return SUCCESS;
	}
	public String add3() {
		return SUCCESS;
	}

	public String addnews() {
		return SUCCESS;
	}
	public String addhuodong() {
		return SUCCESS;
	}
	public String addproj() {
		return SUCCESS;
	}

	public String listjson() {

		jsonString = bioservice.loadAllJson();
		return SUCCESS;
	}

	public String listjsonbyuser() {

		jsonString = bioservice.loadbyauthor(author);
		return SUCCESS;
	}
	public String listjsonbyuser3() {
		
		jsonString = bioservice.loadbyauthor3(author);
		return SUCCESS;
	}
	public String listjsonbyuser4() {
		
		jsonString = bioservice.loadbyauthor4(author);
		return SUCCESS;
	}
	public String listbyauthor_() {
		
		jsonString = bioservice.listbyauthor_(author);
		return SUCCESS;
	}
	public String loadbyauthor1() {
		
		jsonString = bioservice.loadbyauthor1(author);
		return SUCCESS;
	}

	public String listjsonbyfolder() {

		jsonString = bioservice.loadbyfolder(userid);
		return SUCCESS;
	}

	public String listjson0() {
		jsonString = bioservice.loadAllJson0();

		return SUCCESS;
	}
	public String listjson_00() {
		jsonString = bioservice.loadJson0();
		
		return SUCCESS;
	}
	public String listjson_00_head() {
		jsonString = bioservice.loadJson0_head();
		
		return SUCCESS;
	}
	public String listjson_01() {
		jsonString = bioservice.loadJson1();
		
		return SUCCESS;
	}
	public String listjson_02() {
		jsonString = bioservice.loadJson2();
		
		return SUCCESS;
	}
	public String listjson_03() {
		jsonString = bioservice.loadJson3();
		
		return SUCCESS;
	}
	public String listjson_04() {
		jsonString = bioservice.loadJson4();
		
		return SUCCESS;
	}

	public String listjson1() {
		jsonString = bioservice.loadAllJson1();

		return SUCCESS;
	}

	public String listjson2() {
		jsonString = bioservice.loadAllJson2();

		return SUCCESS;
	}

	public String listjson3() {
		jsonString = bioservice.loadAllJson3();

		return SUCCESS;
	}

	public String listnews() {
		list = bioservice.listnews();

		return SUCCESS;
	}
	public String listhuodong() {
		list = bioservice.listhuodong();
		
		return SUCCESS;
	}

	public String listtiezi() {
		list = bioservice.listtiezi();

		return SUCCESS;
	}

	public String list3() {
		list = bioservice.list3();

		return SUCCESS;
	}

	public String list4() {
		list = bioservice.list4();

		return SUCCESS;
	}

	public String detailjson() {
		jsonString = bioservice.detailjson(id);
		return SUCCESS;
	}

	public String delete() {
		bioservice.del(biotech);
		return SUCCESS;
	}
	
	public String delete3() {
		bioservice.del(biotech);
		return SUCCESS;
	}
	public String delete4() {
		bioservice.del(biotech);
		return SUCCESS;
	}
	public String deletenews() {
		bioservice.del(biotech);
		return SUCCESS;
	}
	public String deletehuodong() {
		bioservice.del(biotech);
		return SUCCESS;
	}
	public String deletehuodongjson() {
		bioservice.del(biotech);
		jsonString="1";
		return SUCCESS;
	}

	public List<Baoming> getBlist() {
		return blist;
	}

	public void setBlist(List<Baoming> blist) {
		this.blist = blist;
	}

	public String deletetiezi() {
		bioservice.del(biotech);
		return SUCCESS;
	}

	public String deleteproj() {
		bioservice.del(biotech);
		return SUCCESS;
	}

	public String deleteck() {
		bioservice.del(biotech);
		return SUCCESS;
	}

	public String shenhe() {

		bioservice.shenhe(biotech);

		return SUCCESS;
	}
	public String shenhe3() {
		
		bioservice.shenhe(biotech);
		
		return SUCCESS;
	}
	public String shenhe4() {
		
		bioservice.shenhe(biotech);
		
		return SUCCESS;
	}

	public String shenheck() {

		bioservice.shenheck(biotech);

		return SUCCESS;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getRealpath1() {
		return realpath1;
	}

	public void setRealpath1(String realpath1) {
		this.realpath1 = realpath1;
	}

	public String edit() {
		biotech = bioservice.load(biotech.getId());
		return SUCCESS;
	}
	public String edit3() {
		biotech = bioservice.load(biotech.getId());
		return SUCCESS;
	}

	public String editnews() {
		biotech = bioservice.load(biotech.getId());
		return SUCCESS;
	}
	public String edithuodong() {
		biotech = bioservice.load(biotech.getId());
		return SUCCESS;
	}
	public String editproj() {
		biotech = bioservice.load(biotech.getId());
		return SUCCESS;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Biotech getXianlu() {
		return biotech;
	}

	public void setXianlu(Biotech biotech) {
		this.biotech = biotech;
	}

	public Biotech getJingdian() {
		return biotech;
	}

	public void setJingdian(Biotech jingdian) {
		this.biotech = jingdian;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Biotech> getList() {
		return list;
	}

	public void setList(List<Biotech> list) {
		this.list = list;
	}

	public BiotechService getBioservice() {
		return bioservice;
	}

	public void setBioservice(BiotechService bioservice) {
		this.bioservice = bioservice;
	}

	public Biotech getBiotech() {
		return biotech;
	}

	public void setBiotech(Biotech biotech) {
		this.biotech = biotech;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getBioid() {
		return bioid;
	}

	public void setBioid(int bioid) {
		this.bioid = bioid;
	}

	public Baoming getBaoming() {
		return baoming;
	}

	public void setBaoming(Baoming baoming) {
		this.baoming = baoming;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public Zan getZan() {
		return zan;
	}

	public void setZan(Zan zan) {
		this.zan = zan;
	}

	public Jubao getJubao() {
		return jubao;
	}

	public void setJubao(Jubao jubao) {
		this.jubao = jubao;
	}
	public int getUserid2() {
		return userid2;
	}
	public void setUserid2(int userid2) {
		this.userid2 = userid2;
	}


}
