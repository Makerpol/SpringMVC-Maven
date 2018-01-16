<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>文章添加--layui后台管理模板</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/css/font_eolqem241z66flxr.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">文章标题</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input newsName"
					lay-verify="required" placeholder="请输入文章标题">
			</div>
		</div>
		<div class="layui-form-item">

			<div class="layui-inline">
				<label class="layui-form-label">文章作者</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input newsAuthor"
						lay-verify="required" placeholder="请输入文章作者">
				</div>
			</div>



			<div class="layui-inline">
				<label class="layui-form-label">发布时间</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input newsTime" lay-verify="date"
						onclick="layui.laydate({elem:this})">
				</div>
			</div>


		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">文章类型</label>
				<div class="layui-input-inline">
					<select name="type" class="type" lay-filter="type">
						<option value="0">自然科学</option>
						<option value="1">工程技术</option>
						<option value="2">医药卫生</option>
						<option value="3">农业科学</option>
						<option value="4">哲学政法</option>
						<option value="5">社会科学</option>
						<option value="6">科教文艺</option>
					</select>
				</div>
			</div>

			<div class="layui-inline">
				<label class="layui-form-label">浏览权限</label>
				<div class="layui-input-inline">
					<select name="browseLook" class="newsLook" lay-filter="browseLook">
						<option value="0">开放浏览</option>
						<option value="1">会员浏览</option>
					</select>
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">自定义属性</label>
				<div class="layui-input-block">
					<input type="checkbox" name="tuijian" class="tuijian" title="推荐">
					<input type="checkbox" name="shenhe" class="newsStatus" title="审核">
					<input type="checkbox" name="show" class="isShow" title="展示">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">内容摘要</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容摘要" class="layui-textarea"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">文章内容</label>
			<div class="layui-input-block">
				<textarea id="news_content" class="layui-textarea" name="content"
					lay-verify="content"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="addNews">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<script type="text/javascript" src="/layui/layui.js"></script>
	<script type="text/javascript" src="/page/news/newsAdd.js"></script>
</body>
</html>