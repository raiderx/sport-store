<%--
    @author Pavel Karpukhin
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title> Спортивные товары </title>
    </head>
    <body>
        <h1> Вы действительно хотите удалить запись? </h1>
        <form action="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}" method="post">
            <input type="hidden" name="action" value="remove" />
            <input type="hidden" name="id" value="${model.id}" />
            <table>
                <tr> <td> Брэнд: </td> <td> ${model.brand} </td> </tr>
                <tr> <td> Наименование: </td> <td> ${model.name} </td> </tr>
                <tr> <td> Артикул: </td> <td> ${model.article} </td> </tr>
                <tr> <td> Описание: </td> <td> </td> </tr>
                <tr> <td colspan="2"> <div>${model.description}</div> </td> </tr>
                <tr> <td> Цена: </td> <td> ${model.price} </td> </tr>
            </table>
            <input type="submit" value="Да" />
            <input type="button" value="Нет" onclick="window.location='${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}?action=list'" />
        </form>
    </body>
</html>
