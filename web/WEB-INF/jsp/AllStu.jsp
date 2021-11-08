<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>学生-读者-界面</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container" style="background-image: url(/statics/image/AllStu.jpg);height: 1011px;width: 1011px">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1 style="text-align: center">
                    <small>读者学生列表------------显示所有读者学生</small>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/Administer/toAddStu?aid=${stuAid}">新增学生</a>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/Administer/toAllStu/${stuAid}">显示全部学生</a>
            </div>
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/Administer/backAdminFuc?submit=${stuAid}">
                    回到功能页
                </a>
            </div>
            <div class="col-md-8 column">
                <form class="form-inline" action="${pageContext.request.contextPath}/Administer/querryStuByName" method="post" style="float: right">
                    <span style="color: #ff0000;font-weight: bold">${error}</span>
                    <input type="text" name="searchStuName" class="form-control" placeholder="请输入人名">
                    <input type="submit" value="查询" class="btn btn-primary">
                </form>
            </div>
        </div>
    </div>


    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <tr>
                    <th>sId</th>
                    <th>sName</th>
                    <th>sPassword</th>
                    <th>sGender</th>
                    <th>operation</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach  var="stu" items="${pageStudents}">
                    <tr>
                        <td>${stu.SId}</td>
                        <td>${stu.SName}</td>
                        <td>${stu.SPassword}</td>
                        <td><c:if test="${stu.SGender==0}">男</c:if>
                        <c:if test="${stu.SGender==1}">女</c:if>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/Administer/AdminToUpdateStu/${stu.SId}?aid=${stuAid}">修改</a>
                            <a href="${pageContext.request.contextPath}/Administer/AdminDeleteStu/${stu.SId}?aid=${stuAid}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <div>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li>
                                <a href="/Administer/toAllStu/${stuAid}?pageNum=1" aria-label="Previous"><span aria-label="true">首页</span> </a>
                            </li>
                            <li>
                                <a href="/Administer/toAllStu/${stuAid}?pageNum=${pageInfo.pageNum-1}">上一页</a>
                            </li>
                            <c:forEach begin="1" end="${pageInfo.pages}" var="page">
                                <li><a href="/Administer/toAllStu/${stuAid}?pageNum=${page}">${page}</a> </li>
                            </c:forEach>
                            <li><a href="/Administer/toAllStu/${stuAid}?pageNum=${pageInfo.pageNum+1}">下一页</a></li>
                            <li>
                                <a href="/Administer/toAllStu/${stuAid}?pageNum=${pageInfo.pages}" aria-label="Next">
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
