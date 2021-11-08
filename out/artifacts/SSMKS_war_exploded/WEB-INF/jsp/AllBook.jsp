<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/9
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书库存</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container" style="background-image: url(/statics/image/AllBook.jpg);width: 1873px;height: 2840px">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1 style="text-align: center">
                    <small>书籍列表------------显示所有书籍</small>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/Book/toAddBook?aid=${stuAid}">新增书籍</a>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/Book/allBook/${stuAid}">显示全部书籍</a>
            </div>
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/Administer/backAdminFuc?submit=${stuAid}">
                   回到功能页
                </a>
            </div>
            <div class="col-md-8 column">
                <form class="form-inline" action="${pageContext.request.contextPath}/Book/queryBookByName" method="post" style="float: right">
                    <span style="color: #ff0000;font-weight: bold">${error}</span>
                    <input type="text" name="searchBookName" class="form-control" placeholder="请输入书名">
                    <input type="submit" value="查询" class="btn btn-primary">
                </form>
            </div>
        </div>
    </div>


    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <tr>
                    <th>bId</th>
                    <th>bName</th>
                    <th>bIsbn</th>
                    <th>bAuthor</th>
                    <th>bPublisher</th>
                    <th>bDate</th>
                    <th>bPrice</th>
                    <th>bNum</th>
                    <th>operation</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach  var="book" items="${pageBook}">
                    <tr>
                        <td>${book.BId}</td>
                        <td>${book.BName}</td>
                        <td>${book.BIsbn}</td>
                        <td>${book.BAuthor}</td>
                        <td>${book.BPublisher}</td>
                        <td><fmt:formatDate value="${book.BDate}" pattern="yyyy-MM-dd"/></td>
                        <td>${book.BPrice}</td>
                        <td>${book.BNum}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/Book/toUpdateBook/${book.BId}?aid=${stuAid}">修改</a>
                            <a href="${pageContext.request.contextPath}/Book/deleteBook/${book.BId}?aid=${stuAid}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <div>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li>
                                <a href="/Book/allBook/${stuAid}?pageNum=1" aria-label="Previous"><span aria-label="true">首页</span> </a>
                            </li>
                            <li>
                                <a href="/Book/allBook/${stuAid}?pageNum=${pageInfo.pageNum-1}">上一页</a>
                            </li>
                            <c:forEach begin="1" end="${pageInfo.pages}" var="page">
                                <li><a href="/Book/allBook/${stuAid}?pageNum=${page}">${page}</a> </li>
                            </c:forEach>
                            <li><a href="/Book/allBook/${stuAid}?pageNum=${pageInfo.pageNum+1}">下一页</a></li>
                            <li>
                                <a href="/Book/allBook/${stuAid}?pageNum=${pageInfo.pages}" aria-label="Next">
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
