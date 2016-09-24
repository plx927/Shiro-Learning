<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div id="content">
	<form:form method="post" modelAttribute="role" >
	<table width="800" cellspacing="0" cellPadding="0">
		<tr>
			<td class="rightTd" width="200px">角色名称:</td>
			<td class="leftTd"><form:input path="name" size="30"/></td>
		</tr>

		<tr>
			<td class="rightTd">角色描述:</td>
			<td class="leftTd"><form:input path="description" size="30"/></td>
		</tr>

		<tr>
			<td>权限:</td>
			<td >
				<form:select path="resIds" items="${resources}" itemLabel="name" itemValue="id" >
					<c:forEach var="resId" items="${hasResIds}">
						<form:option value="${resId}"/>
					</c:forEach>
				</form:select>
			</td>
		</tr>

		<tr>
			<td colspan="2" class="centerTd">
				<input type="submit" value="${btnText}"/>
			</td>
		</tr>

	</table>
	</form:form>
</div>
</body>
</html>