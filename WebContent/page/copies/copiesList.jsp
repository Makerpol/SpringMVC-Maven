<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.makerpol.entity.User"%>
<%@ page import="net.sf.json.JSONObject"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User)session.getAttribute("LoginUser");
	String userInfo = JSONObject.fromObject(user).toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=path%>/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="<%=path%>/css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="<%=path%>/css/news.css" media="all" />
	<script type="text/javascript" src="<%=path%>/js/modules/jquery.min.js"></script>
	<script type="text/javascript">
		var user = '<%=userInfo%>';
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
			<a class="layui-btn layui-btn-normal newsAdd_btn">添加稿件</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn audit_btn">稿件审核</a>
		</div>
	</blockquote>
	<div class="layui-form copies_list">
	  	<table class="layui-table">
		    <colgroup>
				<col width="50">
				<col width="30%">
				<col>
				<col>
				<col>
				<col>
				<col width="20%">
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<th>稿件标题</th>
					<th>作者</th>
					<th>投稿期刊</th>
					<th>投稿时间</th>
					<th>审核状态</th>
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="copies_content"></tbody>
		</table>
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="<%=path%>/layui/layui.js"></script>
	<script type="text/javascript" src="<%=path%>/page/copies/copiesList.js"></script>
</body>
</html>