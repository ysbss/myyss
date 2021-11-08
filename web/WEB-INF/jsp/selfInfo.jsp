<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/8
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>读者个人信息查看</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container" style="background-image: url(/statics/image/selfInfo.png);width: 828px;height: 1104px;">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>sId</th>
                    <th>sName</th>
                    <th>sPassword</th>
                    <th>sGender</th>
                </tr>
                </thead>
                <tr>
                    <td>${info.SId}</td>
                    <td>${info.SName}</td>
                    <td>${info.SPassword}</td>
                    <td><c:if test="${info.SGender==0}">男</c:if>
                        <c:if test="${info.SGender==1}">女</c:if>
                    </td>
                    <td>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/Student/toUpdateStudent/${info.SId}">修改信息</a>
                        <form action="${pageContext.request.contextPath}/Student/toStuAllBook">
                            <input type="hidden" name="sid" value="${info.SId}">
                            <button type="submit" class="btn btn-primary">查看图书库存</button>
                            <input type="hidden" name="sname" value="${info.SName}">
                        </form>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/Return/toSelfReturn/${info.SId}/${info.SPassword}">查看个人催还信息</a>
                        <form action="${pageContext.request.contextPath}/Comment/toStuAllComment">
                            <input type="hidden" name="sid" value="${info.SId}">
                            <button type="submit" class="btn btn-primary">查看留言版</button>
                            <input type="hidden" name="sname" value="${info.SName}">
                        </form>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/Student/toStuLogin">回到登录页</a>
                    </td>
                </tr>
            </table>

        </div>

    </div>
</div>


</body>
</html>