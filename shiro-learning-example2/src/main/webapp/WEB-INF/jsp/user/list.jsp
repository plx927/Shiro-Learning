<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div id="content">
	<shiro:hasPermission name="/admin/user/add">
		<a href="<%=request.getContextPath() %>/admin/user/add" class="admin_link">添加用户</a>
	</shiro:hasPermission>

	<table width="800" cellspacing="0" cellPadding="0" id="listTable" border="1">
		<thead>
		<tr>
			<td>用户标识</td>
			<td>用户名称</td>
			<td>用户昵称</td>
			<td>用户状态</td>
			<td>用户操作</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${users }" var="user">
			<tr>
				<td>${user.id }</td>
				<td>${user.username }</td>
				<td>${user.nickname }&nbsp;</td>
				<td>
					<c:if test="${user.status eq 1 }">
						<span class="emp">停用</span>
						<shiro:hasPermission name="/admin/user/updateStauts/${user.id}">
							<a href="updateStatus/${user.id }" class="list_op">启用</a>
						</shiro:hasPermission>
					</c:if>
					<c:if test="${user.status eq 0 }">
						<span>启用</span>
						<shiro:hasPermission name="/admin/user/updateStatus/${user.id}">
							<a href="updateStatus/${user.id }" class="list_op">停用</a>
						</shiro:hasPermission>
					</c:if>
					&nbsp;
				</td>
				<td>
					<shiro:hasPermission name="/admin/user/update/${user.id}">
						<a href="update/${user.id }" class="list_op">更新</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>