package utils;


import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpServletResponseWrapper;  
  
/** 
 * 功能： 
 *  
 * @author: Dell_lipeng 
 * @dateTime : 2014-11-30 下午6:44:05 
 */  
public class CharacterEncodingHttpServletResponseWrapper extends  
        HttpServletResponseWrapper {  
    private String ecoding = null;  
  
    public CharacterEncodingHttpServletResponseWrapper(  
            HttpServletResponse response, String encoding) {  
        super(response);  
        this.ecoding = encoding;  
    }  
  
    public void setContentType(String value) {  
        super.setContentType("text/html;charset=" + ecoding);  
    }  
  
} 