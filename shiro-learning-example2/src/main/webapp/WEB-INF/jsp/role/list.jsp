<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div id="content">
    <shiro:hasPermission name="/admin/role/add">
        <a href="<%=request.getContextPath() %>/admin/role/add" class="admin_link">添加角色</a>
    </shiro:hasPermission>

    <table width="800" cellspacing="0" cellPadding="0" id="listTable" border="1">
        <thead>
        <tr>
            <td>角色标识</td>
            <td>角色名称</td>
            <td>角色描述</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${roles }" var="role">
            <tr>
                <td>${role.id }</td>
                <td>${role.name}</td>
                <td>${role.description }</td>
                <td>
                    <shiro:hasPermission name="/admin/role/update/${role.id}">
                        <a href="${pageContext.request.contextPath}/admin/role/update/${role.id }">更新</a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="/admin/role/delete/${role.id}">
                        <a href="${pageContext.request.contextPath}/admin/role/delete/${role.id }" >删除</a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>