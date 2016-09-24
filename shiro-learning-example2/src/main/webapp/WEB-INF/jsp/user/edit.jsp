<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery-1.11.3.min.js"></script>
</head>
<body>
<div id="content">
	<form:form method="post" modelAttribute="user" id="addForm">
	<table width="800" cellspacing="0" cellPadding="0">
		<tr>
			<td class="rightTd" width="200px">用户名:</td>
			<td class="leftTd"><form:input path="username" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">显示名称:</td><td class="leftTd"><form:input path="nickname" size="30"/></td>
		</tr>
		<c:if test="${user.id eq null}">
			<tr>
				<td class="rightTd">用户密码:</td><td><form:password path="password" size="30"/></td>
			</tr>
		</c:if>

		<tr>
			<td class="rightTd">状态:</td>
			<td>
				<form:select path="status">
					<form:option value="1">停用</form:option>
					<form:option value="0">启用</form:option>
				</form:select>
			</td>
		</tr>
		<tr>
			<td class="rightTd">角色:</td>
			<td>
				<form:select path="roleIds" items="${roles}" itemLabel="name" itemValue="id">
					<c:forEach items="${user.roleIds}" var="roleId">
						<form:option value="${roleId}"/>
					</c:forEach>
				</form:select>
			</td>

		</tr>
		<tr>
			<td colspan="2" class="centerTd"><input type="submit" value="${btnText}"/></td>
		</tr>
	</table>
	</form:form>
</div>
</body>
</html>