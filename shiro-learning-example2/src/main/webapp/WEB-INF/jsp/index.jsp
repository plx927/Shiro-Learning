<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2016/9/24
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout-default-latest.css">
</head>
<>
    <shiro:authenticated>
        <iframe name="content" class="ui-layout-center" frameborder="0" scrolling="auto" ></iframe>

        <div class="ui-layout-north">
            <h1>基于Shiro与URL的权限控制</h1>
            Hello: <span style="color: #ff7f50"><shiro:principal property="nickname"/></span>
            <a href="${pageContext.request.contextPath}/logout">退出</a>
            <a href="${pageContext.request.contextPath}/admin/user/changePwd" target="content">修改密码</a>
        </div>

        <div class="ui-layout-south">
            获取源码：<a href="https://github.com/plx927/Shiro-Learning" target="_blank">https://github.com/plx927/Shiro-Learning</a>
        </div>

        <!--显示所有菜单-->
        <div class="ui-layout-west">
            功能菜单<br/>
            <shiro:hasPermission name="/admin/res/list">
                <a href="<%=request.getContextPath() %>/admin/res/list"  target="content">资源管理</a><br/>
            </shiro:hasPermission>


            <shiro:hasPermission name="/admin/role/list">
                <a href="<%=request.getContextPath() %>/admin/role/list"  target="content">角色管理</a><br/>
            </shiro:hasPermission>

            <shiro:hasPermission name="/admin/user/list">
                <a href="<%=request.getContextPath() %>/admin/user/list" target="content">用户管理</a><br/>
            </shiro:hasPermission>
        </div>
    </shiro:authenticated>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.layout-latest.min.js"></script>
    <script>
        $(function () {
            $(document).ready(function () {
                $('body').layout({ applyDemoStyles: true });
            });
        });
    </script>
    <%--<jsp:include page="/WEB-INF/jsp/common.jsp"/>--%>

</body>
</html>
