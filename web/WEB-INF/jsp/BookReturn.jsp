<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/10
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书催还界面</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container" style="background-image: url(/statics/image/BookReturn.jsp.jpg);height: 1125px;width: 1125px">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1 style="text-align: center">
                    <small>催还信息列表------------显示所有催还信息</small>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/Return/toAddReturnBook?aid=${stuAid}">新增催还信息</a>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/Return/toBookReturn/${stuAid}">显示全部催还信息</a>
            </div>
            <div class="col-md-4 column">
                <a class="btn btn-primary"  href="${pageContext.request.contextPath}/Administer/backAdminFuc?submit=${stuAid}">
                    回到功能页
                </a>
            </div>
            <div class="col-md-8 column">
                <form class="form-inline" action="${pageContext.request.contextPath}/Return/querryReturnByName" method="post" style="float: right">
                    <span style="color: #ff0000;font-weight: bold">${error}</span>
                    <input type="text" name="searchRetBName" class="form-control" placeholder="请输入查询的借阅人名">
                    <input type="submit" value="查询" class="btn btn-primary">
                </form>
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
                            <a href="${pageContext.request.contextPath}/Return/toUpdateReturn/${returns.brId}/${returns.brbName}?aid=${stuAid}">修改</a>
                            <a href="${pageContext.request.contextPath}/Return/deleteReturnById/${returns.brId}/${returns.brbName}?aid=${stuAid}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <div>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li>
                                <a href="/Return/toBookReturn/${stuAid}?pageNum=1" aria-label="Previous"><span aria-label="true">首页</span> </a>
                            </li>
                            <li>
                                <a href="/Return/toBookReturn/${stuAid}?pageNum=${pageInfo.pageNum-1}">上一页</a>
                            </li>
                            <c:forEach begin="1" end="${pageInfo.pages}" var="page">
                                <li><a href="/Return/toBookReturn/${stuAid}?pageNum=${page}">${page}</a> </li>
                            </c:forEach>
                            <li><a href="/Return/toBookReturn/${stuAid}?pageNum=${pageInfo.pageNum+1}">下一页</a></li>
                            <li>
                                <a href="/Return/toBookReturn/${stuAid}?pageNum=${pageInfo.pages}" aria-label="Next">
                                    <span aria-hidden="true">尾页</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </table>
        </div>
    </div>
</div>





</body>
</html>
