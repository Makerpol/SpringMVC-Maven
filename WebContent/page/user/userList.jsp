<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.makerpol.entity.User"%>
<%@ page import="net.sf.json.JSONObject"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)session.getAttribute("user");
String userInfo = JSONObject.fromObject(user).toString();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>用户管理--后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="/css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="/css/news.css" media="all" />
	<script type="text/javascript" src="/js/modules/jquery.min.js"></script>
	<script type="text/javascript">
		var user = '<%=userInfo%>';
		console.log(user);
	</script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-inline">
		    <div class="layui-input-inline">
		    	<input type="text" value="" placeholder="请输入关键字" class="layui-input search_input">
		    </div>
		    <a class="layui-btn search_btn">查询</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn linksAdd_btn" style="background-color:#5FB878">添加用户</a>
		</div>
	</blockquote>
	<div class="layui-form links_list">
	  	<table class="layui-table">
		    <colgroup>
				<col width="50">
				<col width="30%">
				<col>
				<col>
				<col>
				<col>
				<col>
				<col width="13%">
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<th style="text-align:left;">用户名称</th>
					<th>用户组</th>
					<th>邮箱</th>
					<th>电话</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${userList}" var="user">  
				    <tr>  
				     <td><input type="checkbox" name="checked" value="${user.id}" lay-skin="primary" lay-filter="choose"></td> 
				     <td align="left">${user.name}</td>  
				     <td><c:choose><c:when test="${user.grade == 0}">超级管理员</c:when><c:when test="${user.grade == 1}">编辑人员</c:when><c:when test="${user.grade == 2}">问题维护</c:when></c:choose></td>  
				     <td>${user.email}</td>  
				     <td>${user.phone}</td> 
				     <td><c:choose><c:when test="${user.status == 0}">正常用户</c:when><c:when test="${user.grade == 1}">限制用户</c:when></c:choose></td>
					<td><a class="layui-btn layui-btn-mini links_edit" data-id="${user.id }"><i class="iconfont icon-edit"></i> 编辑</a> 
					<a class="layui-btn layui-btn-danger layui-btn-mini links_del" data-id="${user.id }"><i class="layui-icon">&#xe640;</i> 删除</a>
				    </td>
				    </tr>  
				</c:forEach>  
		    </thead>
		    <tbody class="links_content"></tbody>
		</table>
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="/layui/layui.js"></script>
	<script type="text/javascript" src="/page/user/userList.js"></script>
</body>
</html>