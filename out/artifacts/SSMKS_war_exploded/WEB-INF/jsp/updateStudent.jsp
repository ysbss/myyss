<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/8
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新读者</title>
</head>
<body>
<div class="container" style="background-image: url(/statics/image/updateStudent.jsp.png);width: 1828px;height: 828px;">
    <form action="${pageContext.request.contextPath}/Student/updateStudent" method="post">
        <div class="form-group">
            <label for="rid">ID</label>
            <input type="text" id="rid" name="sId" class="form-group" value="${updateStu.SId}">
        </div>
        <div class="form-group">
            <label for="rName">Name</label>
            <input type="text" id="rName" name="sName" class="form-group" value="${updateStu.SName}">
        </div>
        <div class="form-group">
            <label for="rPassword">Password</label>
            <input type="text" id="rPassword" name="sPassword" class="form-group" value="${updateStu.SPassword}">
        </div>
        <div class="form-group">
            <label for="rGender">Gender</label>
            <input type="text" id="rGender" name="sGender" class="form-group" value="${updateStu.SGender}">
        </div>

        <div>
            <button type="submit" class="btn btn-default">更新</button>
        </div>
    </form>
</div>

</body>
</html>
