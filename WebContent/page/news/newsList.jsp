<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.makerpol.entity.User"%>
<%@ page import="net.sf.json.JSONObject"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)session.getAttribute("LoginUser");
String userInfo = JSONObject.fromObject(user).toString();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>文章列表--layui后台管理模板</title>
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
			<a class="layui-btn layui-btn-normal newsAdd_btn">添加文章</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn audit_btn">审核文章</a>
		</div>
	</blockquote>
	<div class="layui-form news_list">
	  	<table class="layui-table">
		    <colgroup>
				<col width="50">
				<col>
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="15%">
				<col width="15%">
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<th style="text-align:left;">文章标题</th>
					<th>文章类型</th>
					<th>作者</th>
					<th>审核状态</th>
					<th>浏览权限</th>
					<th>发布时间</th>
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content"></tbody>
		</table>
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="/layui/layui.js"></script>
	<script type="text/javascript" src="/page/news/newsList.js"></script>
</body>
</html>