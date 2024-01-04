<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2022/8/28
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>测试</h1>
<form action="${pageContext.request.contextPath}/test1" method="post">
  <input type="text" name="name"/>
  <input type="submit" name="提交" />
</form>
</body>
</html>
