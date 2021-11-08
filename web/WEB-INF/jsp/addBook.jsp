<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/10
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增书籍</title>
</head>
<body>
<div class="container"style="background-image: url(/statics/image/addBook.jpg);height: 1619px;width: 2080px;">
    <form action="${pageContext.request.contextPath}/Book/AddBook" method="post">
        <div class="form-group">
            <label for="bid">ID</label>
            <input type="text" id="bid" name="bId" class="form-group" >
        </div>
        <div class="form-group">
            <label for="bName">Name</label>
            <input type="text" id="bName" name="bName" class="form-group" >
        </div>
        <div class="form-group">
            <label for="bisbn">Isbn</label>
            <input type="text" id="bisbn" name="bIsbn" class="form-group">
        </div>
        <div class="form-group">
            <label for="bauthor">Author</label>
            <input type="text" id="bauthor" name="bAuthor" class="form-group">
        </div>
        <div class="form-group">
            <label for="bpublisher">Publisher</label>
            <input type="text" id="bpublisher" name="bPublisher" class="form-group">
        </div>
        <div class="form-group">
            <label for="bdate">Date</label>
            <input type="text" id="bdate" name="bDate" class="form-group">
        </div>
        <div class="form-group">
            <label for="bprice">Price</label>
            <input type="text" id="bprice" name="bPrice" class="form-group">
        </div>
        <div class="form-group">
            <label for="bnum">Num</label>
            <input type="text" id="bnum" name="bNum" class="form-group">
        </div>
        <span style="color: #ff0000;font-weight: bold">${error}</span>
        <div>
            <input name="aid" value="${paid}" type="hidden">
            <button type="submit" class="btn btn-default">添加书籍</button>
        </div>
    </form>

</div>
</body>
</html>
