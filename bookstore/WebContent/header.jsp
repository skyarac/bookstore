<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="/tags/struts-html" prefix="html"%>
<%@taglib uri="/tags/struts-logic" prefix="logic"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style type="text/css">
<!--
.menu {
	font-family: "方正舒体";
	font-size: 9pt;
	background-color: #006699;
	border: 1px dashed #000066;
	color: #CCCCCC;
}
#header a:link {
	font-family: "宋体";
	font-size: 9pt;
	color: #CCCCCC;
}
#header a:visited {
	font-family: "宋体";
	font-size: 9pt;
	color: #CCCCCC;
}
#header a:hover {
	font-family: "宋体";
	font-size: 9pt;
	color: #FFFFFF;
}
#header a:active {
	font-family: "宋体";
	font-size: 9pt;
	color: #FFFFFF;
}
-->
</style>
<div align="center">
	<table width="759" border="0" cellspacing="0" cellpadding="0" id="header">
		<!--表格的这一行是一幅图片-->
		<tr>
			<td>
				<img src="image/title.jpg">
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="text">
					<tr>
						<!--“首页”链接-->
						<td width="120" align="center" class="menu">
							<a href="<%=basePath%>index.jsp"><bean:message key="menu.frontPage" /></a>
						</td>
						<!--“图书检索”链接-->
						<td width="120" align="center" class="menu">
							<a href="<%=basePath%>SearchBook.do"><bean:message key="menu.search" /></a>
						</td>
						<!--只有当user对象存在时才显示如下内容，即只有用户登录后才会有如下内容-->
						<logic:present name="user">
							<!--“个人资料”链接-->
							<td width="120" align="center" class="menu">
								<a href="<%=basePath%>modify.jsp"><bean:message key="menu.personal" /> </a>
							</td>
							<!--“购物车”链接-->
							<td width="120" align="center" class="menu">
								<a href="<%=basePath%>ViewCart.do"> <bean:message key="menu.shoppingCart" /></a>
							</td>
							<!--“注销”链接-->
							<td width="120" align="center" class="menu">
								<a href="<%=basePath%>Logout.do"><bean:message key="menu.logout" /></a>
							</td>
						</logic:present>
						<td class="menu">
							&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	