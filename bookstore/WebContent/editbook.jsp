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
<body>
<%@include file="header.jsp"%>
<html:form method="POST" action="SaveBook.do" enctype="multipart/form-data">
<table width="759" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><table width="600" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="title"><bean:message key="editbook.title" /></td>
      </tr>
      <tr>
        <td class="body"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="40%" height="25" align="right"><bean:message key="editbook.bookName" /></td>
            <td width="60%" height="25"><html:text name="book" property="id" style="display: none" />
			<html:text name="book" property="bookName" styleClass="login"></html:text></td>
          </tr>
          <tr>
            <td width="40%" height="25" align="right"><bean:message key="editbook.writer" /></td>
            <td width="60%" height="25"><html:text name="book" property="writer" styleClass="login"></html:text></td>
          </tr>
          <tr>
            <td width="40%" height="25" align="right"><bean:message key="editbook.publisher" /></td>
            <td width="60%" height="25"><html:text name="book" property="publisher" styleClass="login"></html:text></td>
          </tr>
          <tr>
            <td width="40%" height="25" align="right"><bean:message key="editbook.intro" /></td>
            <td width="60%" height="25"><html:text name="book" property="intro" styleClass="login"></html:text></td>
          </tr>
          <tr>
            <td width="40%" height="25" align="right"><bean:message key="editbook.price" /></td>
            <td width="60%" height="25"><html:text name="book" property="price" styleClass="login"></html:text></td>
          </tr>
          <tr>
            <td width="40%" height="25" align="right"><bean:message key="editbook.count" /></td>
            <td width="60%" height="25"><html:text name="book" property="remaining" styleClass="login"></html:text></td>
          </tr>
          <tr>
            <td width="40%" height="25" align="right"><bean:message key="editbook.cover" /></td>
            <td width="60%" height="25"><html:file property="picture" styleClass="login"></html:file></td>
          </tr>
          <tr>
            <td colspan="2"><hr width="100%"></td>
          </tr>
          <tr>
            <td colspan="2" align="center"><html:submit><bean:message key="editbook.save" /></html:submit></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</html:form>
<%@include file="footer.jsp"%>
</body>
</html>

