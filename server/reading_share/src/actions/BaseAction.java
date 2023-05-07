package actions;
/**
 * @author sux
 * @date 2022-01-10
 * @class BaseAction
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	Log log = LogFactory.getLog("BaseAction.class");
	
	/**
	 * by ServletActionContext get Response
	 * @return response
	 */
	public HttpServletResponse getResponse(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		return response;
	}
	
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	/**
	 * get session
	 * @return
	 */
	public Map<String, Object> getSession(){
		return ActionContext.getContext().getSession();
	}
	/**
	 * Open PrintWriter
	 * @return
	 */
	public PrintWriter getWriter(){
		HttpServletResponse response = this.getResponse();
		PrintWriter pw = null;
		try {
			 pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pw;
	}
	/**
	 * close PrintWriter
	 * @param out
	 */
	public void close(PrintWriter out){
		if(out != null){
			out.close();
		}
	}
	/**
	 * write json string
	 * @param json
	 */
	public void out(String json){
		PrintWriter out = this.getWriter();
		out.write(json);
		this.close(out);
	}
	
	public void write(String json) throws IOException{   
	    HttpServletResponse response=ServletActionContext.getResponse();   
	    /*  
	     * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),  
	     * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会  
	     * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。  
	     * */  
	    response.setContentType("text/html;charset=utf-8");   
	    //response.setCharacterEncoding("UTF-8");   
	    PrintWriter out = response.getWriter();   
	    out.println(json);   
	    out.flush();   
	    out.close();   
	}  
}
