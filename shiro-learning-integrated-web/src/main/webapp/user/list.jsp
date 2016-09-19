<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/18
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <h1>User list jsp</h1>
    <shiro:authenticated>
      你好:<shiro:principal/>
    </shiro:authenticated>

</body>
</html>
