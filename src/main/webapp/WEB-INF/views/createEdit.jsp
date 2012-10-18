<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <% Ski ski = (Ski)request.getAttribute("model"); %>
        <h1> <% if (ski.getId() == 0) { %>Добавить новую<% } else { %>Редактировать<% } %> запись </h1>
        <form action="<%=contextPath%><%=servletPath%>" method="post">
            <input type="hidden" name="action" value="<%=ski.getId() == 0 ? "create" : "edit"%>" />
            <input type="hidden" name="id" value="<%=FormatUtils.encode(ski.getId())%>" />
            <table>
                <tr> <td> Брэнд: </td> <td> <input type="text" name="brand" value="<%=FormatUtils.encode(ski.getBrand())%>" autocomplete="off" /> </td> </tr>
                <tr> <td> Наименование: </td> <td> <input type="text" name="name" value="<%=FormatUtils.encode(ski.getName())%>" autocomplete="off" /> </td> </tr>
                <tr> <td> Артикул: </td> <td> <input type="text" name="article" value="<%=FormatUtils.encode(ski.getArticle())%>" autocomplete="off" /> </td> </tr>
                <tr> <td> Описание: </td> <td> </td> </tr>
                <tr> <td colspan="2"> <textarea name="description" cols="31" rows="5" autocomplete="off"><%=FormatUtils.encode(ski.getDescription())%></textarea> </td> </tr>
                <tr> <td> Цена: </td> <td> <input type="text" name="price" value="<%=FormatUtils.encode(ski.getPrice())%>" autocomplete="off" /> </td> </tr>
            </table>
            <input type="submit" value="Сохранить" />
        </form>
        Вернуться <a href="<%=contextPath%><%=servletPath%>?action=list"> назад </a>
    </body>
</html>
