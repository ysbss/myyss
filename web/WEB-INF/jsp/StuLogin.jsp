<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/8
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生登录界面</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container" style="background-image: url(/statics/image/StuLogin.png);width: 828px;height: 1104px;">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    学生登陆注册界面
                </h1>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/Student/selfinfo" method="post">
            用户名<input type="text" name="submit" class="form-control">
            密码<input type="password" name="password" class="form-control">
            <button type="submit" class="btn btn-default">登录
            </button><span style="color: #ff0000;font-weight: bold">${noner}</span>
        </form>
        <form action="${pageContext.request.contextPath}/Student/toRegister">
            <button type="submit" class="btn btn-default">注册</button>
        </form>
    </div>
    <a class="button btn-default" href="${pageContext.request.contextPath}/index.jsp">回到主页</a>
</div>


</body>

</html>
