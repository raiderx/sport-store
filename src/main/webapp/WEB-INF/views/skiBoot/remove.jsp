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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title> Каталог спортивных товаров </title>
        <link rel="stylesheet" href="<%=contextPath%>/css/style.css" type="text/css" media="screen, projection" />
        <!--[if lte IE 6]><link rel="stylesheet" href="<%=contextPath%>/css/style_ie.css" type="text/css" media="screen, projection" /><![endif]-->
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="h1" class="header"> </div>
                <div id="h3" class="header"> </div>
                <div id="h2" class="header"> </div>
                <div id="title"> </div>
                <div id="brands"> </div>
            </div> <!-- #header-->

            <div id="middle">
                <div id="container">
                    <div id="edit_panel_content">

                        <% SkiBoot skiBoot = (SkiBoot)request.getAttribute("model"); %>

                        <div class="elem">
                            <div class="elem_top"> </div>
                            <div class="elem_midle">

                                <div id="edit_panel" align="center">
                                    <div class="title"> Вы действительно хотите удалить запись? </div>
                                    <form action="<%=contextPath%><%=servletPath%>" method="post">
                                        <input type="hidden" name="action" value="remove" />
                                        <input type="hidden" name="id" value="<%=skiBoot.getId()%>" />
                                        <table>
                                            <tr> <td> Брэнд: </td> <td> <%=skiBoot.getBrand()%> </td> </tr>
                                            <tr> <td> Наименование: </td> <td> <%=skiBoot.getName()%> </td> </tr>
                                            <tr> <td> Артикул: </td> <td> <%=skiBoot.getArticle()%> </td> </tr>
                                            <tr> <td> Flex Index: </td> <td> <%=skiBoot.getFlexIndex()%> </td> </tr>
                                            <tr> <td> Размеры: </td> <td> <%=skiBoot.getMinSize()%> &mdash; <%=FormatUtils.encode(skiBoot.getMaxSize())%> </td> </tr>
                                            <tr> <td> Описание: </td> <td> <%=FormatUtils.encodeValue(skiBoot.getDescription())%> </td> </tr>
                                            <tr> <td> Цвет: </td> <td> <%=skiBoot.getColor()%> </td> </tr>
                                            <tr> <td> Цена: </td> <td> <%=skiBoot.getPrice()%> </td> </tr>
                                        </table>
                                        <input type="submit" value="Да" class="edit_panel_button" />
                                        <input type="button" value="Нет" onclick="window.location='<%=contextPath%><%=servletPath%>?action=list'" class="edit_panel_button" />
                                    </form>
                                </div>
                            </div>
                            <div class="elem_bottom"> </div>
                        </div> <!-- #elem -->

                    </div> <!-- #content-->
                </div> <!-- #container-->

            </div> <!-- #middle-->

        </div> <!-- #wrapper -->

        <div id="footer">
            <div id="f1" class="footer"> </div>
            <div id="f3" class="footer"> </div>
            <div id="f2" class="footer"> </div>
            <div id="copy"> &copy; Каталог спортивных товаров </div>
        </div> <!-- #footer -->
    </body>
</html>
