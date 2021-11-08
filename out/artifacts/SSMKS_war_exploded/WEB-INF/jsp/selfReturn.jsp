<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/12
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人催还信息表</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container" style="background-image: url(/statics/image/selfReturn.png);width: 828px;height: 828px;">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1 style="text-align: center">
                    <small>催还信息列表------------显示个人催还信息</small>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 column">
                <span style="color: #ff0000;font-weight: bold">${error}</span>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/Return/toSelfReturn/${pageSid}/${pagePwd}">显示全部催还信息</a>

            </div>
            <div class="col-md-4 column">
                <form  action="${pageContext.request.contextPath}/Student/backSelfInfo">
                    <input type="hidden" name="psId" value="${pageSid}">
                    <input type="hidden" name="psPwd" value="${pagePwd}">
                    <button type="submit"  class="btn btn-primary">回到功能页面</button>
                </form>
            </div>
            <div class="col-md-8 column">
            </div>
        </div>
    </div>




    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <tr>
                    <th>brId</th>
                    <th>brName</th>
                    <th>brbName</th>
                    <th>brPrice</th>
                    <th>brDate</th>
                    <th>operation</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach  var="returns" items="${pageReturns}">
                    <tr>
                        <td>${returns.brId}</td>
                        <td>${returns.brName}</td>
                        <td>${returns.brbName}</td>
                        <td>${returns.brPrice}</td>
                        <td><fmt:formatDate value="${returns.brDate}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/Return/backBook/${returns.brId}/${returns.brbName}?psId=${pageSid}&psPwd=${pagePwd}">还书</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>




</body>
</html>
