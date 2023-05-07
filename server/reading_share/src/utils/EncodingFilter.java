package utils;

import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
/** 
 * 功能： 
 *  
 * @author: Dell_lipeng 
 * @dateTime : 2014-11-30 下午6:04:09 
 */  
public class EncodingFilter implements Filter {  
  
    private String encoding = null;  
  
    @Override  
    public void destroy() {  
    }  
  
    @Override  
    public void doFilter(ServletRequest req, ServletResponse res,  
            FilterChain chain) throws IOException, ServletException {  
        HttpServletRequest request = (HttpServletRequest) req;  
        HttpServletResponse response = (HttpServletResponse) res;  
  
        CharacterEncodingHttpServletRequestWrapper requestWapper = new CharacterEncodingHttpServletRequestWrapper(  
                request, encoding);  
        CharacterEncodingHttpServletResponseWrapper responseWapper = new CharacterEncodingHttpServletResponseWrapper(  
                response, encoding);  
        chain.doFilter(requestWapper, responseWapper);  
    }  
  
    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
        this.encoding = filterConfig.getInitParameter("encoding");  
    }  
  
}  