<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><bean:message key="orderdetail.title" /></title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<logic:notPresent name="order">
	<logic:forward name="frontPage" />
</logic:notPresent>
<body>
<table width="470" height="230" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td class="title"><bean:message key="orderdetail.title" /></td>
	</tr>
	<tr>
		<td align="center" class="body">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="text">
			<tr>
				<td width="26%" rowspan="6" align="center"><bean:define id="book"
					name="order" property="bookinfo" /> <logic:notEmpty name="book"
					property="picture">
					<img src='upload/<bean:write name="book" property="picture"/>'>
				</logic:notEmpty> <logic:empty name="book" property="picture">
					<img src="image/nopic.gif">
				</logic:empty></td>
				<td height="25" align="right"><bean:message
					key="orderdetail.bookname" /></td>
				<td height="25"><bean:write name="book" property="bookName" /> <span
					class="date">(<bean:message key="orderdetail.number" /> <bean:write
					name="order" property="number" />)</span></td>
			</tr>
			<tr>
				<td width="17%" height="25" align="right"><bean:message
					key="orderdetail.address" /></td>
				<td width="57%" height="25"><bean:write name="order"
					property="address" /></td>
			</tr>
			<tr>
				<td height="25" align="right"><bean:message
					key="orderdetail.postcode" /></td>
				<td height="25"><bean:write name="order" property="postcode" /></td>
			</tr>
			<tr>
				<td height="25" align="right"><bean:message key="orderdetail.date" /></td>
				<td height="25"><bean:write name="order" property="orderdate" /></td>
			</tr>
			<tr>
				<td height="25" align="right"><bean:message key="orderdetail.status" /></td>
				<td height="25"><logic:equal name="order" property="status"
					value="0">
					<bean:message key="orderdetail.notpayed" />
				</logic:equal> <logic:equal name="order" property="status" value="1">
					<bean:message key="orderdetail.payed" />
				</logic:equal> <logic:equal name="order" property="status" value="2">
					<bean:message key="orderdetail.sent" />
				</logic:equal></td>
			</tr>
			<tr>
				<td align="right">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html:html>

