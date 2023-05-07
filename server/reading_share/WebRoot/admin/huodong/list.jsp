<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<html>
<head>
<title>阅读分享活动</title>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="keywords"
	content="Discuz!,Board,Comsenz,forums,bulletin board,">

<meta name="generator" content="Discuz! 4.0.0RC4 with Templates 4.0.0">
<meta name="MSSmartTagsPreventParsing" content="TRUE">
<meta http-equiv="MSThemeCompatible" content="Yes">

<style type="text/css">
<!--
a {
	text-decoration: none;
	color: #000000
}

a:hover {
	text-decoration: underline
}

body {
	scrollbar-base-color: #F3F6FA;
	scrollbar-arrow-color: #4D76B3;
	font-size: 12px;
	background-color: #ffffff
}

table {
	font: 12px Verdana, Tahoma;
	color: #000000
}

input,select,textarea {
	font: 11px Verdana, Tahoma;
	color: #000000;
	font-weight: normal;
	background-color: #F3F6FA
}

select {
	font: 11px Verdana, Tahoma;
	color: #000000;
	font-weight: normal;
	background-color: #F3F6FA
}

.nav {
	font: 12px Verdana, Tahoma;
	color: #000000;
	font-weight: bold
}

.nav a {
	color: #000000
}

.header {
	font: 11px Verdana, Tahoma;
	color: #000000;
	font-weight: bold;
	background-image: url("images/green/bg01.gif")
}

.header a {
	color: #FFFFFF
}

.category {
	font: 11px Verdana, Tahoma;
	color: #000000;
	background-color: #EFEFEF
}

.tableborder {
	background: #4D76B3;
	border: 0px solid #4D76B3
}

.singleborder {
	font-size: 0px;
	line-height: 0px;
	padding: 0px;
	background-color: #F3F6FA
}

.smalltxt {
	font: 11px Verdana, Tahoma
}

.outertxt {
	font: 12px Verdana, Tahoma;
	color: #000000
}

.outertxt a {
	color: #000000
}

.bold {
	font-weight: bold
}

.altbg1 {
	background: #F3F6FA
}

.altbg2 {
	background: #FFFFFF
}
-->
</style>
<script language="JavaScript" src="images/common.js"></script>
<style type="text/css" id="defaultPopStyle">
.cPopText {
	font-family: Tahoma, Verdana;
	background-color: #FFFFCC;
	border: 1px #000000 solid;
	font-size: 12px;
	padding-right: 4px;
	padding-left: 4px;
	height: 20px;
	padding-top: 2px;
	padding-bottom: 2px;
	visibility: hidden;
	filter: Alpha(Opacity =   80)
}
</style>

<script>
function confirmd() {  
            var msg = "Are You Sure Delete?";  
            if (confirm(msg)==true){  
                return true;  
            }else{  
                return false;  
            }  
           }
</script>
</head>

<body leftmargin="0" rightmargin="0" topmargin="0"
>


	
	<br>

	<table class="tableborder" align="center" cellpadding="4"
		cellspacing="1" width="97%">
		<tbody>
			<tr class="header">

				<td align="center" width="5%">编号</td>
				<td align="center" width="12%">阅读分享活动标题</td>
				<td align="center" width="10%">作者</td>
				<td align="center" width="10%">状态</td>
				<td align="center" width="10%">年级</td>
				<td align="center" width="18.5%">媒体类型</td>
				<td align="center" >操作</td>
			</tr>
			<c:forEach var="tmp" items="${list}"  varStatus="itemindex">
						<tr>
							
							
							<td class="altbg1" align="center">		${itemindex.count}</td>
							<td class="altbg1" align="center">	${tmp.title }</td>
							<td class="altbg1" align="center">	${tmp.author }</td>
							<td class="altbg1" align="center">	
								<c:if test="${tmp.status2 ==0}">
									<font color="red">未通过认证</font>
								</c:if>
								<c:if test="${tmp.status2 ==1}">
										<font color="green">通过认证</font>
								</c:if>
							</td>
						   <td class="altbg1" align="center">	${tmp.age }</td>
							<td class="altbg1" align="center">	
							   <c:if test="${tmp.status ==1 }">
							  
							   </c:if>
							    	<img alt="图片下载" src="${pageContext.request.contextPath}/${tmp.image_url }" width="100px" height="100px">
								</img>
							   <!--
							     <c:if test="${tmp.status ==0 }">
							     
							     <video src="${pageContext.request.contextPath}/${tmp.image_url }" controls="controls" width="300" height="200" >
									您的浏览器不支持 video 标签。
									</video>
							     </c:if>
							
							--></td>
							<td class="altbg1" align="center"><!--
										
										
								<a target="mainFrame"
										href="${pageContext.request.contextPath}/biotech_edithuodong.action?biotech.id=${tmp.id }">修改
									</a>
									--><a target="mainFrame"
										href="${pageContext.request.contextPath}/biotech_deletehuodong.action?biotech.id=${tmp.id }">删除
									</a><!--
									<a target="mainFrame"
										href="${pageContext.request.contextPath}/biotech_listbaoming.action?bioid=${tmp.id }">查看报名情况
									</a>
									
									--><c:if test="${tmp.status2 ==0}">
									<a target="mainFrame"
										href="${pageContext.request.contextPath}/biotech_shenhe.action?biotech.id=${tmp.id }">认证</a>
							</c:if>
							
							</td>
						
			
						</tr>
			</c:forEach>
		</tbody>
	</table>

	<a name="bottom" />
</body>
</html>
