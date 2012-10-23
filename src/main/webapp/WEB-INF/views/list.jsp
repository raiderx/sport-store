<%--
    @author Pavel Karpukhin
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.karpukhin.sportstore.core.model.Ski" %>
<%@ page import="org.karpukhin.sportstore.web.FormatUtils" %>
<%
	String contextPath = (String)request.getAttribute("javax.servlet.forward.context_path");
	String servletPath = (String)request.getAttribute("javax.servlet.forward.servlet_path");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title> Спортивные товары </title>
</head>
<body>

<h1> <a href="<%=contextPath%>"> Спортивные товары </a> / Горные лыжи </h1>

<a href="<%=contextPath%><%=servletPath%>?action=create"> Добавить </a>
<table>
	<tr>
		<th> Брэнд </th>
		<th> Наименование </th>
		<th> Артикул </th>
		<th> Описание </th>
		<th> Цена </th>
		<th> </th>
		<th> </th>
	</tr>
	<%
		List<Ski> skis = (List<Ski>)request.getAttribute("model");
		for (Ski ski : skis) {
	%>
	<tr>
		<td> <%=FormatUtils.formatString(ski.getBrand())%> </td>
		<td> <%=FormatUtils.formatString(ski.getName())%> </td>
		<td> <%=FormatUtils.formatString(ski.getArticle())%> </td>
		<td> <%=FormatUtils.formatString(ski.getDescription())%> </td>
		<td> <%=ski.getPrice()%> </td>
		<td> <a href="<%=contextPath%><%=servletPath%>?action=edit&id=<%=ski.getId()%>"> Изменить </a> </td>
		<td> <a href="<%=contextPath%><%=servletPath%>?action=remove&id=<%=ski.getId()%>"> Удалить </a> </td>
	</tr>
	<% } %>
	<% if (skis.isEmpty()) { %>
	<tr> <td colspan="7" align="center"> Пусто </td> </tr>
	<% } %>
</table>

</body>
</html>