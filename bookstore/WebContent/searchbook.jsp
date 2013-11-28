<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript">
<!--
	function orderDetail(id) {
		window.open("OrderDetail.do?id=" + id, "", "location=no,toolbar=no,status=no,height=250,width=500");
	}
	function bookDetail(id) {
		window.open("BookDetail.do?id=" + id, "", "location=no,toolbar=no,status=no,height=250,width=500");
	}
	function delBook(id) {
	}
//-->
</script>
<body>
<%@include file="header.jsp"%>
<html:form method="GET" action="SearchBook.do">
	<table width="759" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" class="text"><bean:message key="search.name" /> <html:text
				property="name"></html:text> <html:submit>
				<bean:message key="search.start" />
			</html:submit></td>
		</tr>
		<logic:present name="result">
			<logic:iterate id="aBook" name="result" scope="request">
				<tr>
					<td height="20" colspan="2" class="text">
						<a href='javascript:bookDetail(<bean:write name="aBook" property="id" />)' class="text">
							<bean:write name="aBook" property="bookName" />
							<span class="date">
								(<bean:write name="aBook" property="writer" />
								<bean:write name="aBook" property="publisher" />)
							</span>
						</a>&nbsp;
						<a href='ShoppingCart.do?bookid=<bean:write name="aBook" property="id"/>' class="text">
							<bean:message key="bookdetail.shoppingCart" />
						</a>
						<logic:present name="admin">
							<a href='EditBook.do?bookid=<bean:write name="aBook" property="id"/>'><bean:message key="searchbook.edit"/></a>
							<a href='DeleteBook.do?bookid=<bean:write name="aBook" property="id"/>'>
								<bean:message key="searchbook.delete"/>
							</a>
						</logic:present>
					</td>
				</tr>
			</logic:iterate>
		</logic:present>
		<tr>
			<td align="right" class="text">
				<logic:present name="admin">
					<a href='EditBook.do?bookid=0'><bean:message key="searchbook.add"/></a>
				</logic:present>
				<a href='?name=<bean:write name="bookname"/>&page=1'>
					<bean:message key="searchBook.first" />
				</a>
				<logic:present name="prevPage">
					<a href='?name=<bean:write name="bookname"/>&page=<bean:write name="prevPage" />'>
						<bean:message key="searchBook.prev" />
					</a>
				</logic:present>
				<logic:present name="nextPage">
					<a href='?name=<bean:write name="bookname"/>&page=<bean:write name="nextPage" />'>
						<bean:message key="searchBook.next" />
					</a>
				</logic:present>
				<a href='?name=<bean:write name="bookname"/>&page=<bean:write name="pageCount" />'>
					<bean:message key="searchBook.last" />
				</a>
			</td>
		</tr>
	</table>
</html:form>
<%@include file="footer.jsp"%>
</body>
</html>

