<%--
  Created by IntelliJ IDEA.
  User: 鱼酥不是叔
  Date: 2021/6/12
  Time: 0:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新催还信息</title>
</head>
<body>
<div class="container" style="background-image: url(/statics/image/updateReturn.jpg);width: 1679px;height: 1004px;">
    <form action="${pageContext.request.contextPath}/Return/updateReturnBook" method="post">
        <div class="form-group">
            <label for="brid">brId</label>
            <input type="text" id="brid" name="brId" class="form-group" value="${pageReturn.brId}">
        </div>
        <div class="form-group">
            <label for="brname">brName</label>
            <input type="text" id="brname" name="brName" class="form-group" value="${pageReturn.brName}">
        </div>
        <div class="form-group">
            <label for="brbname">brbName</label>
            <input type="text" id="brbname" name="brbName" class="form-group" value="${pageReturn.brbName}">
        </div>
        <div class="form-group">
            <label for="brprice">brPrice</label>
            <input type="text" id="brprice" name="brPrice" class="form-group" value="${pageReturn.brPrice}">
        </div>
        <div class="form-group">
            <label for="brdate">brDate</label>
            <input type="text" id="brdate" name="brDate" class="form-group" value="${pageReturn.brDate}">
        </div>
        <div>
            <input name="aid" value="${paid}" type="hidden">
            <button type="submit" class="btn btn-default">更新</button>
        </div>

    </form>
</div>


</body>
</html>
