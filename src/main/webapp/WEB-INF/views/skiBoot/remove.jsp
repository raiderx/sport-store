<%--
    @author Pavel Karpukhin
--%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="org.karpukhin.sportstore.core.model.SkiBoot" %>
<%@ page import="org.karpukhin.sportstore.web.FormatUtils" %>
<%
    String contextPath = (String)request.getAttribute("javax.servlet.forward.context_path");
    String servletPath = (String)request.getAttribute("javax.servlet.forward.servlet_path");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title> Спортивные товары </title>
    </head>
    <body>
        <% SkiBoot skiBoot = (SkiBoot)request.getAttribute("model"); %>
        <h1> Вы действительно хотите удалить запись? </h1>
        <form action="<%=contextPath%><%=servletPath%>" method="post">
            <input type="hidden" name="action" value="remove" />
            <input type="hidden" name="id" value="<%=skiBoot.getId()%>" />
            <table>
                <tr> <td> Брэнд: </td> <td> <%=skiBoot.getBrand()%> </td> </tr>
                <tr> <td> Наименование: </td> <td> <%=skiBoot.getName()%> </td> </tr>
                <tr> <td> Артикул: </td> <td> <%=skiBoot.getArticle()%> </td> </tr>
                <tr> <td> Flex Index: </td> <td> <%=skiBoot.getFlexIndex()%> </td> </tr>
                <tr> <td> Размеры: </td> <td> <%=skiBoot.getMinSize()%>&mdash;<%=skiBoot.getMaxSize()%> </td> </tr>
                <tr> <td> Описание: </td> <td> <%=FormatUtils.encodeValue(skiBoot.getDescription())%> </td> </tr>
                <tr> <td> Цвет: </td> <td> <%=FormatUtils.encodeValue(skiBoot.getColor())%> </td> </tr>
                <tr> <td> Цена: </td> <td> <%=skiBoot.getPrice()%> </td> </tr>
            </table>
            <input type="submit" value="Да" />
            <input type="button" value="Нет" onclick="window.location='<%=contextPath%><%=servletPath%>?action=list'" />
        </form>
    </body>
</html>