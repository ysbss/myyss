<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/10
  Time: 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生查看书籍</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container" style="background-image: url(/statics/image/stuAllBook.png);width: 828px;height: 1104px;">
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
                <form action="${pageContext.request.contextPath}/Student/toStuAllBook">
                    <input type="hidden" name="sid" value="${sId}">
                    <input type="hidden" name="sname" value="${sName}">
                    <button type="submit" class="btn btn-primary">显示全部库存</button>
                </form>

            </div>
            <div class="col-md-2 column">
                <form  action="${pageContext.request.contextPath}/Student/backSelfInfo">
                    <input type="hidden" name="psId" value="${sId}">
                    <input type="hidden" name="psName" value="${sName}">
                    <button type="submit"  class="btn btn-primary">回到功能页面</button>
                </form>
            </div>
            <div class="col-md-6 column">
                <form class="form-inline" action="${pageContext.request.contextPath}/Student/queryStuBookByName/${sId}/${sName}" method="post" style="float: right">
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
                    <th>bNum</th>
                    <th>operation</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach  var="book" items="${pageBook}">
                    <tr>
                        <td>${book.BId}</td>
                        <td>${book.BName}</td>
                        <td>${book.BNum}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/Student/borrowBook/${book.BId}/${sId}/${sName}">借书</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

                <div>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li>
                                <a href="/Student/toStuAllBook?pageNum=1&sid=${sId}&sname=${sName}" aria-label="Previous"><span aria-label="true">首页</span> </a>
                            </li>
                            <li>
                                <a href="/Student/toStuAllBook?pageNum=${pageInfo.pageNum-1}&sid=${sId}&sname=${sName}">上一页</a>
                            </li>
                            <c:forEach begin="1" end="${pageInfo.pages}" var="page">
                                <li><a href="/Student/toStuAllBook?pageNum=${page}&sid=${sId}&sname=${sName}">${page}</a> </li>
                            </c:forEach>
                            <li><a href="/Student/toStuAllBook?pageNum=${pageInfo.pageNum+1}&sid=${sId}&sname=${sName}">下一页</a></li>
                            <li>
                                <a href="/Student/toStuAllBook?pageNum=${pageInfo.pages}&sid=${sId}&sname=${sName}" aria-label="Next">
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
