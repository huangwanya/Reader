<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<HTML>
<HEAD>
<TITLE>Management Page</TITLE>
<STYLE type=text/css>
BODY {
	BACKGROUND: #799ae1;
	MARGIN: 0px;
	FONT: 9pt 宋体
}

TABLE {
	BORDER-RIGHT: 0px;
	BORDER-TOP: 0px;
	BORDER-LEFT: 0px;
	BORDER-BOTTOM: 0px
}

TD {
	FONT: 12px 宋体
}

IMG {
	BORDER-RIGHT: 0px;
	BORDER-TOP: 0px;
	VERTICAL-ALIGN: bottom;
	BORDER-LEFT: 0px;
	BORDER-BOTTOM: 0px
}

A {
	FONT: 12px 宋体;
	COLOR: #000000;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: #428eff;
	TEXT-DECORATION: underline
}

.sec_menu {
	BORDER-RIGHT: white 1px solid;
	BACKGROUND: #d6dff7;
	OVERFLOW: hidden;
	BORDER-LEFT: white 1px solid;
	BORDER-BOTTOM: white 1px solid
}

.menu_title {
	
}

.menu_title SPAN {
	FONT-WEIGHT: bold;
	LEFT: 7px;
	COLOR: #215dc6;
	POSITION: relative;
	TOP: 2px
}

.menu_title2 {
	
}

.menu_title2 SPAN {
	FONT-WEIGHT: bold;
	LEFT: 8px;
	COLOR: #428eff;
	POSITION: relative;
	TOP: 2px
}
</STYLE>

<SCRIPT language=javascript1.2>
function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
}
}

 
</SCRIPT>

<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2180" name=GENERATOR>
</HEAD>

<%
	
		//response.sendRedirect("admin_login.jsp");
 %>

<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
	%>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" align=left border=0>
		<TBODY>
			<% if(session.getAttribute("loginname")!=null){%>
			<TR>
				<TD vAlign=top bgColor=#799ae1>
					<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
						<TBODY>
							<TR>
								
							</TR>
						</TBODY>
					</TABLE>
					<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
						<TBODY>
							<TR>
								<TD class=menu_title onMouseOver="this.className='menu_title2';"
									onmouseout="this.className='menu_title';" background="red"
									height=25><SPAN><A href="../exit/exit.jsp"
										target="_parent"><B>Exit</B>
									</A>
								</SPAN>
								</TD>
							</TR>
							
						</TBODY>
					</TABLE>
					<TABLE cellSpacing=0 cellPadding=0 width=158>
						<TBODY>
							<TR>
								<TD class=menu_title id=menuTitle1
									onmouseover="this.className='menu_title2';"
									onclick=showsubmenu(0)
									onmouseout="this.className='menu_title';"
									background=../../left.files/admin_left_1.gif height=25><span><B>Look Book</B>
								</span>
								</TD>
							</TR>
							<TR>
								<TD id=submenu0>
									<DIV class=sec_menu style="WIDTH: 158px ">
										<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
											<TBODY>
												<TR>
													<TD height=20><A href="UserInfoAlter.asp"
														target=mainFrame></A><a href="../book/list.jsp"
														target="mainFrame">Book List</a>
													</TD>
												</TR>
												<TR>
													<TD height=20><a href="../book/searchlist.jsp"
														target="mainFrame">Search Book</a>
													</TD>
												</TR>


											</TBODY>
										</TABLE>
									</DIV>
									<DIV style="WIDTH: 158px">
										<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
											<TBODY>
												<TR>
													<TD height=20></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</TD>
							</TR>
						</TBODY>
					</TABLE>



					<TABLE cellSpacing=0 cellPadding=0 width=158>
						<TBODY>
							<TR>
								<TD class=menu_title id=menuTitle1
									onmouseover="this.className='menu_title2';"
									onclick=showsubmenu(1)
									onmouseout="this.className='menu_title';"
									background=../../left.files/admin_left_2.gif height=25><SPAN>Order Manage</SPAN>
								</TD>
							</TR>
							<TR>
								<TD id=submenu1><DIV class=sec_menu style="WIDTH: 158px">
										<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
											<TBODY>

												<TR>
													<TD height=20><a href="../cart/list.jsp"
														target="mainFrame">My Cart</a>
													</TD>
												</TR>
												<TR>
													<TD height=20><a href="../ordered/list.jsp"
														target="mainFrame">Have Payed Order</a>
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
									<DIV style="WIDTH: 158px">
										<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
											<TBODY>
												<TR>
													<TD height=20></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</TD>
							</TR>
						</TBODY>
					</TABLE>


					<TABLE cellSpacing=0 cellPadding=0 width=158>
						<TBODY>
							<TR>
								<TD class=menu_title id=menuTitle1
									onmouseover="this.className='menu_title2';"
									onclick=showsubmenu(2)
									onmouseout="this.className='menu_title';"
									background=../../left.files/admin_left_2.gif height=25><SPAN>Personal Manage</SPAN>
								</TD>
							</TR>
							<TR>
								<TD id=submenu2>
									<DIV class=sec_menu style="WIDTH: 158px">
										<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
											<TBODY>
												<TR>
													<TD height=20><a href=../personal/update.jsp
														target="mainFrame">Modify Information</a>
													</TD>
												</TR>
											
												
												</TR>

											</TBODY>
										</TABLE>
									</DIV>
									<DIV style="WIDTH: 158px">
										<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
											<TBODY>
												<TR>
													<TD height=20></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</TD>
							</TR>
						</TBODY>
					</TABLE>

					

					
			</TR>
			<%} else  {%>
			<TR>
			<TABLE cellSpacing=0 cellPadding=0 width=158 align=center>
						<TBODY>
							<TR>
								<TD class=menu_title onMouseOver="this.className='menu_title2';"
									onmouseout="this.className='menu_title';" background="red"
									height=25><SPAN><A href="../../login.jsp"
										target="_parent"><B>Login</B>
									</A>
								</SPAN>
								</TD>
							</TR>
							
						</TBODY>
					</TABLE>
					<TABLE cellSpacing=0 cellPadding=0 width=158>
						<TBODY>
							<TR>
								<TD class=menu_title id=menuTitle1
									onmouseover="this.className='menu_title2';"
									onclick=showsubmenu(0)
									onmouseout="this.className='menu_title';"
									background=../../left.files/admin_left_1.gif height=25><span><B>Look Book</B>
								</span>
								</TD>
							</TR>
							<TR>
								<TD id=submenu0>
									<DIV class=sec_menu style="WIDTH: 158px ">
										<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
											<TBODY>
												<TR>
													<TD height=20><A href="UserInfoAlter.asp"
														target=mainFrame></A><a href="../book/list.jsp"
														target="mainFrame">Book List</a>
													</TD>
												</TR>
												<TR>
													<TD height=20><a href="../book/searchlist.jsp"
														target="mainFrame">Search Book</a>
													</TD>
												</TR>


											</TBODY>
										</TABLE>
									</DIV>
									<DIV style="WIDTH: 158px">
										<TABLE cellSpacing=0 cellPadding=0 width=135 align=center>
											<TBODY>
												<TR>
													<TD height=20></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
			<%}%>
			</TR>
		</TBODY>
	</TABLE>


</BODY>
</HTML>
