<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/11
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加催还信息</title>
</head>
<body>
<div class="container" style="background-image: url(/statics/image/AddReturn.jpg);width: 1828px;height: 820px;">
    <form action="${pageContext.request.contextPath}/Return/AddReturnBook" method="post">
        <div class="form-group">
            <label for="brid">ID</label>
            <input type="text" id="brid" name="brId" class="form-group" >
        </div>
        <div class="form-group">
            <label for="brName">RName</label>
            <input type="text" id="brName" name="brName" class="form-group" >
        </div>
        <div class="form-group">
            <label for="brbName">BName</label>
            <input type="text" id="brbName" name="brbName" class="form-group">
        </div>
        <div class="form-group">
            <label for="bPrice">Price</label>
            <input type="text" id="bPrice" name="brPrice" class="form-group">
        </div>
        <div class="form-group">
            <label for="bDate">Date</label>
            <input type="text" id="bDate" name="brDate" class="form-group">
        </div>
        <span style="color: #ff0000;font-weight: bold">${error}</span>
        <div>
            <input name="aid" value="${paid}" type="hidden">
            <button type="submit" class="btn btn-default">添加</button>
        </div>
    </form>


</div>


</body>
</html>
