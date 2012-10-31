<%--
    @author Pavel Karpukhin
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.karpukhin.sportstore.core.model.Ski" %>
<%@ page import="org.karpukhin.sportstore.web.FormatUtils" %>
<%
    String contextPath = request.getContextPath();
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
        <% Ski ski = (Ski)request.getAttribute("model"); %>
        <h1> Вы действительно хотите удалить запись? </h1>
        <form action="<%=contextPath%><%=servletPath%>" method="post">
            <input type="hidden" name="action" value="remove" />
            <input type="hidden" name="id" value="<%=FormatUtils.encode(ski.getId())%>" />
            <table>
                <tr> <td> Брэнд: </td> <td> <%=FormatUtils.encode(ski.getBrand())%> </td> </tr>
                <tr> <td> Наименование: </td> <td> <%=FormatUtils.encode(ski.getName())%> </td> </tr>
                <tr> <td> Артикул: </td> <td> <%=FormatUtils.encode(ski.getArticle())%> </td> </tr>
                <tr> <td> Описание: </td> <td> </td> </tr>
                <tr> <td colspan="2"> <div><%=FormatUtils.encode(ski.getDescription())%></div> </td> </tr>
                <tr> <td> Цена: </td> <td> <%=FormatUtils.encode(ski.getPrice())%> </td> </tr>
            </table>
            <input type="submit" value="Да" />
            <input type="button" value="Нет" onclick="window.location='<%=contextPath%><%=servletPath%>'" />
        </form>
    </body>
</html>
