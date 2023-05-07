<%@ page contentType="text/html; charset=gb2312" %>


<%
	request.getSession().removeAttribute("loginname");
	response.sendRedirect("../../login.jsp");
	
%>

