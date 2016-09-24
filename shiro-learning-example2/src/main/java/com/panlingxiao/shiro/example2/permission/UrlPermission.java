package com.panlingxiao.shiro.example2.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;

/**
 * 自定义的Permission，基于URL来判断是否具有操作权限
 */
public class UrlPermission implements Permission {
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UrlPermission() {

	}
	
	public UrlPermission(String url) {
		this.url = url;
	}

	@Override
	public boolean implies(Permission p) {
		if(!(p instanceof UrlPermission)) return false;
		UrlPermission up = (UrlPermission)p;
		// /admin/role/**
		PatternMatcher patternMatcher = new AntPathMatcher();
		return patternMatcher.matches(this.getUrl(), up.getUrl());
	}

}
