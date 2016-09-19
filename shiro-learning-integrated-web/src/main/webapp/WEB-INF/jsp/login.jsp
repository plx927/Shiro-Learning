<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/18
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <meta charset="utf-8">
    <title>用户登入</title>
</head>
<body>
   <h2>${authError}</h2>
    <form method="post">
      username:<input name="username"/><br/>
      password:<input type="password" name="password"><br/>
      <input type="submit" value="login"/>
    </form>
</body>
</html>
