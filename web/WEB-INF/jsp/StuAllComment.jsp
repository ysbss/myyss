<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/13
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>留言板</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container" style="background-image: url(/statics/image/StuAllComment.png);width: 828px;height: 1104px;">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1 style="text-align: center">
                    <small>留言板</small>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/Comment/toStuAllComment?sid=${sId}&sname=${sName}">显示全部留言</a>
            </div>
            <div class="col-md-4 column">
                <form  action="${pageContext.request.contextPath}/Student/backSelfInfo">
                    <input type="hidden" name="psId" value="${sId}">
                    <input type="hidden" name="psName" value="${sName}">
                    <button type="submit"  class="btn btn-primary">回到功能页面</button>
                </form>
            </div>
            <div class="col-md-8 column">
                <form class="form-inline" action="${pageContext.request.contextPath}/Comment/StuQuerryCommentById?sid=${sId}&sname=${sName}" method="post" style="float: right">
                    <span style="color: #ff0000;font-weight: bold">${error}</span>
                    <input type="text" name="searchCommentId" class="form-control" placeholder="请输入留言人id">
                    <input type="submit" value="查询" class="btn btn-primary">
                </form>
            </div>
        </div>
    </div>


    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <tr>
                    <th>cId</th>
                    <th>cIdentity</th>
                    <th>cWord</th>
                    <th>cDate</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach  var="comment" items="${pageComment}">
                    <tr>
                        <td>${comment.CId}</td>
                        <td>${comment.CIdentity}</td>
                        <td>${comment.CWord}</td>
                        <td><fmt:formatDate value="${comment.CDate}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                </c:forEach>
                </tbody>
                <div>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li>
                                <a href="/Comment/toStuAllComment?pageNum=1&sid=${sId}&sname=${sName}" aria-label="Previous"><span aria-label="true">首页</span> </a>
                            </li>
                            <li>
                                <a href="/Comment/toStuAllComment?pageNum=${pageInfo.pageNum-1}&sid=${sId}&sname=${sName}">上一页</a>
                            </li>
                            <c:forEach begin="1" end="${pageInfo.pages}" var="page">
                                <li><a href="/Comment/toStuAllComment?pageNum=${page}&sid=${sId}&sname=${sName}">${page}</a> </li>
                            </c:forEach>
                            <li><a href="/Comment/toStuAllComment?pageNum=${pageInfo.pageNum+1}&sid=${sId}&sname=${sName}">下一页</a></li>
                            <li>
                                <a href="/Comment/toStuAllComment?pageNum=${pageInfo.pages}&sid=${sId}&sname=${sName}" aria-label="Next">
                                    <span aria-hidden="true">尾页</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </table>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/Comment/StuAddComment">
        <input type="hidden" name="paid" class="text" value="${sId}">
        <textarea wrap="virtual" name="cWord" class="text-area"></textarea>
        <span style="color: #ff0000;font-weight: bold">${error1}</span>
        <button type="submit" class="btn btn-primary">留言</button>
    </form>


</div>

</body>
</html>
