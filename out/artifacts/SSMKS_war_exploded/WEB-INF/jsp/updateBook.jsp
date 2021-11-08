<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/9
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍更新</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container" style="background-image: url(/statics/image/updateBook.jpg);width: 2000px;height: 1234px;">
    <form action="${pageContext.request.contextPath}/Book/updateBook" method="post">
        <div class="form-group">
            <label for="pid">bId</label>
            <input type="text" id="pid" name="bId" class="form-group" value="${pageBook.BId}">
        </div>
        <div class="form-group">
            <label for="pname">bName</label>
            <input type="text" id="pname" name="bName" class="form-group" value="${pageBook.BName}">
        </div>
        <div class="form-group">
            <label for="pisbn">bIsbn</label>
            <input type="text" id="pisbn" name="bIsbn" class="form-group" value="${pageBook.BIsbn}">
        </div>
        <div class="form-group">
            <label for="pauthor">bAuthor</label>
            <input type="text" id="pauthor" name="bAuthor" class="form-group" value="${pageBook.BAuthor}">
        </div>
        <div class="form-group">
            <label for="ppublisher">bPublisher</label>
            <input type="text" id="ppublisher" name="bPublisher" class="form-group" value="${pageBook.BPublisher}">
        </div>
        <div class="form-group">
            <label for="pdate">bDate</label>
            <input type="text" id="pdate" name="bDate" class="form-group" value="${pageBook.BDate}">
        </div>
        <div class="form-group">
            <label for="pprice">bPrice</label>
            <input type="text" id="pprice" name="bPrice" class="form-group" value="${pageBook.BPrice}">
        </div>
        <div class="form-group">
            <label for="pnum">bNum</label>
            <input type="text" id="pnum" name="bNum" class="form-group" value="${pageBook.BNum}">
        </div>
        <div>
            <input name="aid" value="${paid}" type="hidden">
            <button type="submit" class="btn btn-default">更新</button>
        </div>

    </form>
</div>
</body>
</html>
