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
<script language="javascript">
<!--
	function emulatePay() {
		alert("fee paid!");
		submit();
	}
//-->
</script>
</head>
<body>
<%@include file="header.jsp"%>
<html:form action="Pay.do">
<table width="600" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" class="title"><bean:message key="menu.shoppingCart" /></td>
  </tr>
  <logic:present name="books"> <logic:iterate id="aBook" name="books" scope="session">
  <tr>
    <td width="530" class="text"><bean:write name="aBook" property="bookName" /> <span class="date">(<bean:write name="aBook" property="writer" /> <bean:write name="aBook" property="publisher" />) </span> </td>
    <td width="70">&nbsp;</td>
  </logic:iterate>
  <tr>
    <td colspan="2"><table width="100%"  border="0" cellpadding="0" cellspacing="0" class="text">
	  <tr>
        <td><hr width="100%"></td>
      </tr>
      <tr>
        <td><bean:message key="viewcart.address" /><html:text name="user" property="address" />
		<bean:message key="viewcart.postcode" /><html:text name="user" property="postcode" />
		</td>
      </tr>
      <tr>
      	<td><html:errors/></td>
      </tr>
    </table></td>
  </tr>
  <tr align="center">
    <td colspan="2" class="body"><html:submit onclick="emulatePay()"><bean:message key="viewcart.pay" /></html:submit></td>
  </tr>
  </logic:present>
</table>
</html:form>
<%@include file="footer.jsp"%>
</body>
</html>

