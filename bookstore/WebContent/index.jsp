<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="/tags/struts-html" prefix="html"%>
<%@taglib uri="/tags/struts-logic" prefix="logic"%>
<%
String path2 = request.getContextPath();
String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path2+"/";
%>


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><bean:message key="index.title" /></title>
		<link href="style.css" rel="stylesheet" type="text/css">
	</head>
	<%//从session中取得用户信息
			bookstore.database.Userinfo user = (bookstore.database.Userinfo) session.getAttribute("user");

			//查询得到所有有现货供应（库存数>0）的图书
			org.hibernate.Session dbSession = bookstore.database.HibernateUtil
					.currentSession();
			bookstore.database.HibernateUtil.beginTransaction();
			org.hibernate.Query query = dbSession
					.createQuery("from Bookinfo as b where b.remaining >0 order by b.id asc");

			//此次查询最多只取3条结果
			query.setMaxResults(3);
			java.util.List result = query.list();
			bookstore.database.HibernateUtil.commitTransaction();
			for(int i=0;i<result.size();i++){
		//	System.out.println("book=="+i+"名称是"+((bookstore.database.Bookinfo)result.get(i)).getBookName());
			}

			//将查询出来的结果集及第一条结果保存在request范围内
			request.setAttribute("books", result);
			request.setAttribute("newest", result.get(0));

			// 查询得到当前用户的所有订单
			bookstore.database.HibernateUtil.beginTransaction();
			query = dbSession
					.createQuery("from Order as o where o.userinfo=:userinfo order by o.id desc");
			query.setParameter("userinfo", user);

			//此次查询最多只取4条结果
			query.setMaxResults(4);
			result = query.list();
			bookstore.database.HibernateUtil.commitTransaction();

			//将查询出来的结果集保存在request范围内。
			request.setAttribute("orders", result);

		%>
	<!--下面的Java Script代码定义了两个方法-->
	<script language="javascript">
<!--
	//新开一个窗口，显示订单详细信息
	function orderDetail(id) {
		window.open("<%=basePath2%>OrderDetail.do?id=" + id, "", "location=no,toolbar=no,status=no,height=250,width=500");
	}
	//新开一个窗口，显示图书详细信息
	function bookDetail(id) {
		window.open("<%=basePath2%>BookDetail.do?id=" + id, "", "location=no,toolbar=no,status=no,height=250,width=500");
	}
//-->
</script>
	<body>
		<!--导入文件header.jsp，以显示页面上部内容-->
		<%@include file="/header.jsp"%>
		<table width="759" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="200" valign="top">
					<br>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							 <td class="title">
								<bean:message key="index.userinfo" />
							</td>
						</tr>
						
						<tr>
							<td valign="bottom" class="body">
								<br>
								<!--只有当user对象存在时才显示如下内容，即只有用户登录后才会有如下内容-->
								<logic:present name="user">
									<div align="center" class="text">
										<!--文本：“欢迎光临，”-->
										<bean:message key="index.welcome" />
										<!--写出user对象的username属性-->
										<bean:write name="user" property="username" />
										<hr style="height:1pt" width="90%" />
										<!--“修改资料”链接-->
										<html:link href="modify.jsp">
											<bean:message key="index.modify" />
										</html:link>
										<!--“注销”链接-->
										<html:link href="Logout.do">
											<bean:message key="index.logout" />
										</html:link>
									</div>
								</logic:present>
								<html:form action="Login.do">
									<!--只有当user对象不存在时才显示如下内容，即只有用户尚未登录时才会有如下内容-->
									<logic:notPresent name="user">
										<div align="center" class="text">
											<!--文本：“用户名：”-->
											<bean:message key="index.username" />
											<!--文本框，property属性名为userName-->
											<html:text property="userName" styleClass="login" />
											<br>
											<!--文本：“密码：”-->
											<bean:message key="index.password" />
											<!--文本框，property属性名为password”-->
											<html:password property="password" styleClass="login" />
											<br>
											<!--“免费注册”链接-->
											<html:link href="register.jsp">
												<bean:message key="index.register" />
											</html:link>
											<!--显示当请求转发到此页面时，ActionMessage对象封装的提示信息-->
											<html:errors property="inputErrors" />
											<hr style="height:1pt" width="90%" />

											<!--“登录”按钮-->
											<html:submit styleClass="button">
												<bean:message key="index.login" />
											</html:submit>
											<!--“重置”按钮-->
											<html:reset styleClass="button">
												<bean:message key="index.reset" />
											</html:reset>
										</div>
									</logic:notPresent>
								</html:form>
							</td>
						</tr>
					</table>
					<!--只有当user对象存在时才显示如下内容，即只有用户登录后才会有如下内容-->
					<logic:present name="user">
						<br>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<!--标题：“订购信息”-->
								<td class="title">
									<bean:message key="index.order" />
								</td>
							</tr>
							<tr>
								<td class="body">
									<!--滚动信息，方向为向上-->
									<marquee direction="up" scrollamount="2">
										<!--取出之前存入在request范围内的orders所代表的内容-->
										<!--name是作为集合的bean名称，scope是bean的作用域-->
										<!--id是作用域为“page”的bean或者脚本变量的名称。它类似于一个游标，在遍历过程中总是记录着集合中当前元素的名柄-->
										<logic:iterate id="anOrder" name="orders" scope="request">
											<!--id是新定义的变量的名称。name是目标bean的名称，property是name属性定义的bean的属性名称，用于定义新的bean-->
											<bean:define id="book" name="anOrder" property="bookinfo" />
											<!--用于新开一个窗口的链接，该窗口用于显示某条订单的详情-->
											<!--bean:write标签中name是要进行属性显示的bean名称，property是要显示的属性名-->
											<!--bean:write标签中将新定义的bean，即book的bookName属性值打印出来-->
											<a href='javascript:orderDetail(<bean:write name="anOrder" property="id" />)'> <bean:write name="book" property="bookName" /> <span class="date"> (<bean:write name="anOrder" property="orderdate" />) </span> </a>
											<br>
										</logic:iterate>
									</marquee>
								</td>
							</tr>
						</table>
					</logic:present>
				</td>
				<td align="center" valign="top">
					<br>
<table width="97%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<!--标题：“新书推荐”-->
		<td class="title">
			<bean:message key="index.newbook" />
		</td>
	</tr>
	<tr>
		<td class="body">
			<br>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="text">
				<tr>
					<td width="18%" rowspan="6" align="center" valign="top">
						<!--用于新开一个窗口的链接，该窗口显示某本图书的详情-->
						<a href='javascript:bookDetail(<bean:write name="newest" property="id" />)'> <!--当属性值非空的时候才显示下面的内容--> <logic:notEmpty name="newest" property="picture">
								<img border="0" src='upload/<bean:write name="newest" property="picture"/>'>
							</logic:notEmpty> <!--当属性值为空的时候才显示下面的内容--> <logic:empty name="newest" property="picture">
								<img border="0" src="image/nopic.gif">
							</logic:empty> </a>
					</td>
					<!--文本：“书名：”-->
					<td width="82%" height="25">
						<bean:message key="orderdetail.bookname" />
						<bean:write name="newest" property="bookName" />
					</td>
				</tr>
				<tr>
					<!--文本：“作者：”-->
					<td height="25">
						<bean:message key="index.writer" />
						<bean:write name="newest" property="writer" />
					</td>
				</tr>
				<tr>
					<!--文本：“出版社：”-->
					<td height="25">
						<bean:message key="index.publisher" />
						<bean:write name="newest" property="publisher" />
					</td>
				</tr>
				<tr>
					<!--文本：“价格：”-->
					<td height="25">
						<bean:message key="index.price" />
						<bean:write name="newest" property="price" />
					</td>
				</tr>
				<tr>
					<td height="25">
						<hr style="height:1pt " width="90%" align="left">
						<bean:write name="newest" property="intro" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr style="height:1pt " width="90%">
					</td>
				</tr>
				<!--遍历一个集合，此处是显示books集合中的信息，即查询得到的图书信息-->
				<logic:iterate id="aBook" name="books" scope="request">
					<tr>
						<td colspan="2" height="20">
							<a href='javascript:bookDetail(<bean:write name="aBook" property="id" />)'> <bean:write name="aBook" property="bookName" /> <bean:write name="aBook" property="writer" /> <bean:write name="aBook" property="publisher" /> </a>
						</td>
					</tr>
				</logic:iterate>
				<!--链接，用于图书检索功能-->
				<tr>
					<td colspan="2" align="right">
						<a href="<%=basePath2%>SearchBook.do">更多>></a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
				</td>
			</tr>
		</table>
		<%@include file="/footer.jsp"%>
	</body>
</html>
