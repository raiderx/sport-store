<%--
    @author Pavel Karpukhin
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
                                    <div class="title"> Добавить новую запись </div>
                                    <form action="<%=contextPath%><%=servletPath%>" method="post">
                                        <input type="hidden" name="action" value="<%=skiBoot.getId() == 0 ? "create" : "edit"%>" />
                                        <input type="hidden" name="id" value="<%=FormatUtils.encode(skiBoot.getId())%>" />
                                        <table>
                                            <tr> <td> Брэнд: </td> <td> <input type="text" name="brand" value="<%=FormatUtils.encode(skiBoot.getBrand())%>" autocomplete="off" class="input" /> </td> </tr>
                                            <tr> <td> Наименование: </td> <td> <input type="text" name="name" value="<%=FormatUtils.encode(skiBoot.getName())%>" autocomplete="off" class="input" /> </td> </tr>
                                            <tr> <td> Артикул: </td> <td> <input type="text" name="article" value="<%=FormatUtils.encode(skiBoot.getArticle())%>" autocomplete="off" class="input" /> </td> </tr>
                                            <tr> <td> Flex Index: </td> <td> <input type="text" name="flexIndex" value="<%=FormatUtils.encode(skiBoot.getFlexIndex())%>" autocomplete="off" class="input" /> </td> </tr>
                                            <tr> <td> Размеры: </td> <td> <input type="text" name="minSize" value="<%=FormatUtils.encode(skiBoot.getMinSize())%>" size="8" autocomplete="off" class="size_input" /> &mdash; <input type="text" name="maxSize" value="<%=FormatUtils.encode(skiBoot.getMaxSize())%>" size="7" autocomplete="off" class="size_input" /> </td> </tr>
                                            <tr> <td> Описание: </td> <td> <textarea name="description" class="input edit_panel_textarea"><%=FormatUtils.encode(skiBoot.getDescription())%></textarea> </td> </tr>
                                            <tr> <td> Цвет: </td> <td> <input type="text" name="color" value="<%=FormatUtils.encode(skiBoot.getColor())%>" autocomplete="off" class="input" /> </td> </tr>
                                            <tr> <td> Цена: </td> <td> <input type="text" name="price" value="<%=FormatUtils.encode(skiBoot.getPrice())%>" autocomplete="off" class="input" /> </td> </tr>
                                        </table>
                                        <input type="submit" name="" value="Сохранить" class="edit_panel_button" />
                                    </form>
                                    Вернуться <a href="<%=contextPath%><%=servletPath%>?action=list"> назад </a>
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
