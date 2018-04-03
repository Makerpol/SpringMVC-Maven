<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>PDF添加</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css" media="all" />
<style type="text/css">
.icon{
	width:200px;
	height:250px;
	margin-left:15px;
}
.layui-inline{
	margin-bottom:20px;
}
</style>
</head>
<body class="childrenBody">
	<div class="layui-inline">
			<label class="layui-form-label">选择文件</label>
			<div class="layui-input-block">
				<input type="file" name="upfile" class="layui-upload-file" lay-title="选择视频文件">
				<img src="${video.icon}" class="icon">
			</div>
	</div>
	<div class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">文件名字</label>
			<div class="layui-input-block">
				<input type="text" maxlength="50" class="layui-input fileName" value="${video.videoname}"
					lay-verify="required" placeholder="请输入文章标题">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">文章类型</label>
				<div class="layui-input-inline" id="fileType">
					<select name="type" class="type" lay-filter="type" >
						<option value="0" <c:choose><c:when test="${video.type == 0}">selected='selected'</c:when></c:choose>>自然科学</option>
						<option value="1" <c:choose><c:when test="${video.type == 1}">selected='selected'</c:when></c:choose>>工程技术</option>
						<option value="2" <c:choose><c:when test="${video.type == 2}">selected='selected'</c:when></c:choose>>医药卫生</option>
						<option value="3" <c:choose><c:when test="${video.type == 3}">selected='selected'</c:when></c:choose>>农业科学</option>
						<option value="4" <c:choose><c:when test="${video.type == 4}">selected='selected'</c:when></c:choose>>哲学政法</option>
						<option value="5" <c:choose><c:when test="${video.type == 5}">selected='selected'</c:when></c:choose>>社会科学</option>
						<option value="6" <c:choose><c:when test="${video.type == 6}">selected='selected'</c:when></c:choose>>科教文艺</option>
						<option value="7" <c:choose><c:when test="${video.type == 7}">selected='selected'</c:when></c:choose>>新闻报道</option>
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
			<label class="layui-form-label">内容摘要</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容摘要"  class="layui-textarea summary">${video.summary}</textarea>
			</div>
		</div>
		
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<a class="layui-btn submit_btn" >立即提交</a>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=path%>/layui/layui.js"></script>
	<script type="text/javascript" src="<%=path%>/page/video/videoInfo.js"></script>
</body>
</html>