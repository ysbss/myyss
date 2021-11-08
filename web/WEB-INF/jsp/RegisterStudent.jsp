<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/8
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册读者</title>
</head>
<body>
<div class="container" style="background-image: url(/statics/image/RegisterStudent.jsp.jpg);width: 1922px;height: 1014px;">
    <form action="${pageContext.request.contextPath}/Student/registerStu" method="post">
        <div class="form-group">
            <label for="rid">ID</label>
            <input type="text" id="rid" name="sId" class="form-group" >
        </div>
        <div class="form-group">
            <label for="rName">Name</label>
            <input type="text" id="rName" name="sName" class="form-group" >
        </div>
        <div class="form-group">
            <label for="rPassword">Password</label>
            <input type="text" id="rPassword" name="sPassword" class="form-group">
        </div>
        <div class="form-group">
            <label for="rGender">Gender</label>
            <input type="text" id="rGender" name="sGender" class="form-group">
        </div>
        <span style="color: #ff0000;font-weight: bold">${error}</span>
        <div>
            <button type="submit" class="btn btn-default">注册</button>
        </div>
    </form>
</div>

</body>
</html>
