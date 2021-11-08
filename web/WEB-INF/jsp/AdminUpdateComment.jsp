<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/13
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改发言</title>
</head>
<body>
<div class="container" style="background-image: url(/statics/image/AdminUpdateComment.jpg);width: 2080px;height: 1921px;">
    <form action="${pageContext.request.contextPath}/Comment/AdminUpdateComment" method="post">
        <div class="form-group">
            <label for="cid"></label>
            <input type="hidden" id="cid" name="cId" class="form-group" value="${upComment.CId}">
        </div>
        <div class="form-group">
            <label for="cidentity"></label>
            <input type="hidden" id="cidentity" name="cIdentity" class="form-group" value="${upComment.CIdentity}">
        </div>
        <div class="form-group">
            <label for="cword">留言</label>
            <input type="text" id="cword" name="cWord" class="form-group" value="${upComment.CWord}">
        </div>
        <div class="form-group">
            <label for="cdate"></label>
            <input type="hidden" id="cdate" name="cDate" class="form-group" value="${upComment.CDate}">
        </div>

        <div>
            <input name="aid" value="${paid}" type="hidden">
            <button type="submit" class="btn btn-default">更新</button>
        </div>
    </form>

</div>
</body>
</html>
