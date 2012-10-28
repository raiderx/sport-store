<%--
    @author Pavel Karpukhin
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
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
                    <div id="content">
                        <a href="<%=contextPath%><%=servletPath%>?action=create"> Добавить </a>

                        <%
                            List<SkiBoot> skiBoots = (List<SkiBoot>)request.getAttribute("model");
                            for (SkiBoot skiBoot : skiBoots) {
                        %>
                        <div class="elem">
                            <div class="elem_top"> </div>
                            <div class="elem_midle">
                                <div class="elem_header"> <%=skiBoot.getName()%> </div>
                                <div class="elem_middle">
                                    <div class="row img">
                                        <img src="images/elem_img.png" width="100" height="100" />
                                    </div>
                                    <div class="row info">
                                        <p> <%=FormatUtils.encodeValue(skiBoot.getDescription())%> </p>
                                    </div>
                                    <div class="row dop_info">
                                        <table cellpadding="0" cellspacing="0" border="0">
                                            <tr> <td width="60"> Брэнд </td> <td> <%=skiBoot.getBrand()%> </td> </tr>
                                            <tr> <td> Артикул </td> <td> <%=skiBoot.getArticle()%> </td> </tr>
                                            <tr> <td> Flex Index </td> <td> <%=skiBoot.getFlexIndex()%> </td> </tr>
                                            <tr> <td> Размеры </td> <td> <%=skiBoot.getMinSize()%>&mdash;<%=skiBoot.getMaxSize()%> </td> </tr>
                                            <tr> <td> Цвет </td> <td> <%=skiBoot.getColor()%> </td> </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="elem_footer">
                                    <div class="cost"> <%=skiBoot.getPrice()%> p.</div>
                                    <div class="row edit"> <a href="<%=contextPath%><%=servletPath%>?action=edit&id=<%=skiBoot.getId()%>"> Изменить </a> </div>
                                    <div class="row delete"> <a href="<%=contextPath%><%=servletPath%>?action=remove&id=<%=skiBoot.getId()%>"> Удалить </a> </div>
                                </div>
                            </div>
                            <div class="elem_bottom"> </div>
                        </div> <!-- #elem -->
                        <% } %>

                    </div> <!-- #content-->
                </div> <!-- #container-->

                <div class="sidebar" id="sideLeft">
                    <div id="s1" class="menu"> </div>
                    <div id="s2" class="menu">
                        <div id="menu">
                            <div id="menu_title"> Категории товаров </div>

                            <div class="link"> <a href="<%=contextPath%>/ski"> Горные лыжи </a> </div>
                            <div class="link"> <a href="<%=contextPath%>/skiBoot"> Ботинки </a> </div>

                        </div>
                    </div>
                    <div id="s3" class="menu"> </div>
                </div> <!-- .sidebar#sideLeft -->

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
