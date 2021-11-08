<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/11
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员更新学生读者</title>
</head>
<body>
<div class="container" style="background-image: url(/statics/image/AdminUpdateStu.jpg);width: 1880px;height: 1440px;">
    <form action="${pageContext.request.contextPath}/Administer/AdminUpdateStu" method="post">
        <div class="form-group">
            <label for="rid">ID</label>
            <input type="text" id="rid" name="sId" class="form-group" value="${pageStudent.SId}">
        </div>
        <div class="form-group">
            <label for="rName">Name</label>
            <input type="text" id="rName" name="sName" class="form-group" value="${pageStudent.SName}">
        </div>
        <div class="form-group">
            <label for="rPassword">Password</label>
            <input type="text" id="rPassword" name="sPassword" class="form-group" value="${pageStudent.SPassword}">
        </div>
        <div class="form-group">
            <label for="rGender">Gender</label>
            <input type="text" id="rGender" name="sGender" class="form-group" value="${pageStudent.SGender}">
        </div>

        <div>
            <input name="aid" value="${paid}" type="hidden">
            <button type="submit" class="btn btn-default">更新</button>
        </div>
    </form>
</div>


</body>
</html>
