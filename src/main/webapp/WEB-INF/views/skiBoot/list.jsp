<%--
    @author Pavel Karpukhin
--%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.List" %>
<%@ page import="org.karpukhin.sportstore.core.model.SkiBoot" %>
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
        <h1> <a href="<%=contextPath%>"> Спортивные товары </a> / Ботинки </h1>
        <a href="<%=contextPath%><%=servletPath%>?action=create"> Добавить </a>
        <table>
            <tr>
                <th> Брэнд </th>
                <th> Наименование </th>
                <th> Артикул </th>
                <th> Flex Index </th>
                <th> Размеры </th>
                <th> Описание </th>
                <th> Цвет </th>
                <th> Цена </th>
                <th> </th>
                <th> </th>
            </tr>
            <%
                List<SkiBoot> skiBoots = (List<SkiBoot>)request.getAttribute("model");
                for (SkiBoot skiBoot : skiBoots) {
            %>
            <tr>
                <td> <%=FormatUtils.formatString(skiBoot.getBrand())%> </td>
                <td> <%=FormatUtils.formatString(skiBoot.getName())%> </td>
                <td> <%=FormatUtils.formatString(skiBoot.getArticle())%> </td>
                <td> <%=skiBoot.getFlexIndex()%> </td>
                <td> <%=skiBoot.getMinSize()%>&mdash;<%=skiBoot.getMaxSize()%> </td>
                <td> <%=FormatUtils.formatString(skiBoot.getDescription())%> </td>
                <td> <%=FormatUtils.formatString(skiBoot.getColor())%> </td>
                <td> <%=skiBoot.getPrice()%> </td>
                <td> <a href="<%=contextPath%><%=servletPath%>?action=edit&id=<%=skiBoot.getId()%>"> Изменить </a> </td>
                <td> <a href="<%=contextPath%><%=servletPath%>?action=remove&id=<%=skiBoot.getId()%>"> Удалить </a> </td>
            </tr>
            <% } %>
            <% if (skiBoots.isEmpty()) { %>
            <tr> <td colspan="10" align="center"> Пусто </td> </tr>
            <% } %>
        </table>
    </body>
</html>