<%--
    @author Pavel Karpukhin
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title> Спортивные товары </title>
    </head>
    <body>
        <h1> ${model.id == 0 ? 'Добавить новую' : 'Редактировать'} запись </h1>
        <form action="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}" method="post">
            <input type="hidden" name="action" value="${model.id == 0 ? 'create' : 'edit'}" />
            <input type="hidden" name="id" value="${model.id == 0 ? '' : model.id}" />
            <table>
                <tr> <td> Брэнд: </td> <td> <input type="text" name="brand" value="${model.brand}" autocomplete="off" /> </td> </tr>
                <tr> <td> Наименование: </td> <td> <input type="text" name="name" value="${model.name}" autocomplete="off" /> </td> </tr>
                <tr> <td> Артикул: </td> <td> <input type="text" name="article" value="${model.article}" autocomplete="off" /> </td> </tr>
                <tr> <td> Описание: </td> <td> </td> </tr>
                <tr> <td colspan="2"> <textarea name="description" cols="31" rows="5" autocomplete="off">${model.description}</textarea> </td> </tr>
                <tr> <td> Цена: </td> <td> <input type="text" name="price" value="${model.price}" autocomplete="off" /> </td> </tr>
            </table>
            <input type="submit" value="Сохранить" />
        </form>
        Вернуться <a href="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}"> назад </a>
    </body>
</html>
