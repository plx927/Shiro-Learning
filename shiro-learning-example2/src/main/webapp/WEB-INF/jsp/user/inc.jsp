<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common.jsp"%>
<span>
<shiro:hasPermission name="admin:user:add">
<a href="<%=request.getContextPath() %>/admin/user/add" class="admin_link">添加用户</a>
</shiro:hasPermission>
</span>