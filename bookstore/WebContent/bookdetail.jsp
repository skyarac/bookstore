<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><bean:message key="bookdetail.title" /></title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<logic:notPresent name="book">
	<logic:forward name="frontPage" />
</logic:notPresent>
<body>
<table width="470" height="230" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td class="title"><bean:message key="bookdetail.title" /></td>
	</tr>
	<tr>
		<td align="center" class="body">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="text">
			<tr>
				<td width="26%" rowspan="6" align="center" valign="top"><logic:notEmpty
					name="book" property="picture">
					<img src='upload/<bean:write name="book" property="picture"/>'>
				</logic:notEmpty> <logic:empty name="book" property="picture">
					<img src="image/nopic.gif">
				</logic:empty></td>
				<td height="25" align="right"><bean:message
					key="orderdetail.bookname" /></td>
				<td height="25">
					<bean:write name="book" property="bookName" />
					<a href='<%=basePath%>ShoppingCart.do?bookid=<bean:write name="book" property="id" />'>
						<bean:message key="bookdetail.shoppingCart" />
					</a>
				</td>
			</tr>
			<tr>
				<td width="17%" height="25" align="right"><bean:message
					key="index.writer" /></td>
				<td width="57%" height="25"><bean:write name="book"
					property="writer" /></td>
			</tr>
			<tr>
				<td height="25" align="right"><bean:message key="index.publisher" /></td>
				<td height="25"><bean:write name="book" property="publisher" /></td>
			</tr>
			<tr>
				<td height="25" align="right"><bean:message key="index.price" /></td>
				<td height="25"><bean:write name="book" property="price" /></td>
			</tr>
			<tr>
				<td height="25" colspan="2" align="left">
				<hr style="height:1pt " width="90%" align="left">
				<bean:write name="book" property="intro" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html:html>

