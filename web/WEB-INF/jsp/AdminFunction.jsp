<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/10
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员功能</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container" style="background-image: url(/statics/image/AdminFunction.jpg);width: 1228px;height: 1104px">
    <div class="row">
        <div class="column-md-4">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/Administer/toAllStu/${aid}">查看所有读者学生</a>
        </div>

        <div class="column-md-4">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/Return/toBookReturn/${aid}">查看催还信息</a>
        </div>
        <div class="column-md-4">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/Book/allBook/${aid}">查看所有图书</a>
        </div>

        <div class="column-md-4">
            <form  action="${pageContext.request.contextPath}/Comment/toAllComment">
                <input type="hidden" name="submit" value="${aid}">
                <button type="submit" class="btn btn-primary">查看留言版</button>
            </form>
        </div>

        <div class="column-md-4">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/Administer/toAdminLogin">回到登录页</a>
        </div>
    </div>

</div>

</body>
</html>
