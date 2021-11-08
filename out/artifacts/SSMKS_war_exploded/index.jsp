<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/7
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>主页</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

  </head>
  <body >
  <div style="height: 1400px; width: 1800px; background-image: url(/statics/image/index.jpg)" class="container">
    <div class="column-md-6" style="float: left;font-size: large;font-weight: bolder">
      <a style="color: red" href="${pageContext.request.contextPath}/Student/toStuLogin">进入学生登陆注册界面</a>
    </div>
    <div class="column-md-6" style="float: right;font-size: large;font-weight: bolder">
      <a style="color: red" href="${pageContext.request.contextPath}/Administer/toAdminLogin">进入管理员登录界面</a>
    </div>
  </div>
   </body>
</html>
