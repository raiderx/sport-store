<%--
    @author Pavel Karpukhin
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="org.karpukhin.sportstore.core.model.SkiBoot" %>
<%@ page import="org.karpukhin.sportstore.web.FormatUtils" %>
<%
    String contextPath = (String)request.getContextPath();
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
        <h1> <% if (skiBoot.getId() == 0) { %>Добавить новую<% } else { %>Редактировать<% } %> запись </h1>
        <form action="<%=contextPath%><%=servletPath%>" method="post">
            <input type="hidden" name="action" value="<%=skiBoot.getId() == 0 ? "create" : "edit"%>" />
            <input type="hidden" name="id" value="<%=skiBoot.getId() == 0 ? "" : skiBoot.getId()%>" />
            <table>
                <tr> <td> Брэнд: </td> <td> <input type="text" name="brand" value="<%=FormatUtils.encode(skiBoot.getBrand())%>" autocomplete="off" /> </td> </tr>
                <tr> <td> Наименование: </td> <td> <input type="text" name="name" value="<%=FormatUtils.encode(skiBoot.getName())%>" autocomplete="off" /> </td> </tr>
                <tr> <td> Артикул: </td> <td> <input type="text" name="article" value="<%=FormatUtils.encode(skiBoot.getArticle())%>" autocomplete="off" /> </td> </tr>
                <tr> <td> Flex Index: </td> <td> <input type="text" name="flexIndex" value="<%=FormatUtils.encode(skiBoot.getFlexIndex())%>" autocomplete="off" /> </td> </tr>
                <tr> <td> Размеры: </td> <td> <input type="text" name="minSize" value="<%=FormatUtils.encode(skiBoot.getMinSize())%>" size="8" autocomplete="off" /> &mdash; <input type="text" name="maxSize" value="<%=FormatUtils.encode(skiBoot.getMaxSize())%>" size="7" autocomplete="off" /> </td> </tr>
                <tr> <td> Описание: </td> <td> </td> </tr>
                <tr> <td colspan="2"> <textarea name="description" cols="31" rows="5" autocomplete="off"><%=FormatUtils.encode(skiBoot.getDescription())%></textarea> </td> </tr>
                <tr> <td> Цвет: </td> <td> <input type="text" name="color" value="<%=FormatUtils.encode(skiBoot.getColor())%>" autocomplete="off" /> </td> </tr>
                <tr> <td> Цена: </td> <td> <input type="text" name="price" value="<%=FormatUtils.encode(skiBoot.getPrice())%>" autocomplete="off" /> </td> </tr>
            </table>
            <input type="submit" value="Сохранить" />
        </form>
        Вернуться <a href="<%=contextPath%><%=servletPath%>"> назад </a>
    </body>
</html>
