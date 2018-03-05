<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.makerpol.entity.User"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) session.getAttribute("LoginUser");
	String userInfo = JSONObject.fromObject(user).toString();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>后台管理模板</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../../css/common.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form">
		<blockquote class="layui-elem-quote news_search">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" value="" placeholder="请输入关键字"
						class="layui-input search_input">
				</div>
				<a class="layui-btn search_btn">查询</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn file-addBtn">添加PDF</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-danger file-delBtn">批量删除</a>
			</div>
		</blockquote>
		<ul id="Files">
			<li>
				<img class="pdflistimg" src="/upload/file/20180364034919.jpg">
				<div class="layui-hide">
					<input type="text" id="pdfPath" value="/upload/file/20180364034919.pdf">
				</div>
			
				<div class="operate">
					<div class="check">
						<input type="checkbox" name="belle" lay-filter="choose" lay-skin="primary" title="weixixxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx">
					</div>
					<i class="layui-icon img_del"></i>
				</div>
			</li>


		</ul>
	</form>
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<script type="text/javascript" src="/page/file/file.js"></script>
</body>
</html>