package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
	  
	/** 
	 * 功能： 
	 * @author: Dell_lipeng 
	 * @dateTime : 2014-11-30 下午6:33:04 
	 */  
	public class CharacterEncodingHttpServletRequestWrapper extends  
	        HttpServletRequestWrapper {  
	  
	    private String ecoding = null;  
	    private String oldEncoding = null;  
	  
	    public CharacterEncodingHttpServletRequestWrapper(  
	            HttpServletRequest request, String encoding) {  
	        super(request);  
	        this.ecoding = encoding;  
	        this.oldEncoding = request.getCharacterEncoding();  
	    }  
	  
	    @Override  
	    public String getParameter(String value) {  
	        try {  
	            if ((oldEncoding == null || isIOS88591(oldEncoding))  
	                    && super.getParameter(value) != null) {  
	                return new String(super.getParameter(value).getBytes(  
	                        "iso-8859-1"), ecoding);  
	            } else {  
	                return super.getParameter(value);  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            return null;  
	        }  
	    }  
	  
	    private boolean isIOS88591(String endcoding) {  
	        endcoding = endcoding.toLowerCase();  
	        return endcoding.startsWith("iso") && (endcoding.indexOf("8859") != -1)  
	                && endcoding.endsWith("1");  
	    }  
	  
	}  
