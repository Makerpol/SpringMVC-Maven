<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>文章编辑--后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=path%>/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="<%=path%>/css/font_eolqem241z66flxr.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">文章标题</label>
			<div class="layui-input-block">
				<input type="text" maxlength="50" class="layui-input newsName" lay-verify="required" placeholder="请输入文章标题" value="${paper.paperName}">
			</div>
			<div class="layui-hide">
				<input type="text" value="${paper.id}" id="id">
			</div>
		</div>
		<div class="layui-form-item">
			
			<div class="layui-inline">		
				<label class="layui-form-label">文章作者</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input newsAuthor" lay-verify="required" placeholder="请输入文章作者" value="${paper.author}">
				</div>
			</div>
			<div class="layui-inline">		
				<label class="layui-form-label">发布时间</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input newsTime" lay-verify="date" onclick="layui.laydate({elem:this,format:'YYYY-MM-DD hh:mm:ss',max:laydate.now()})" value="${paper.date}">
				</div>
			</div>
			
		</div>
		<div class="layui-form-item">
		<div class="layui-inline">
				<label class="layui-form-label">文章类型</label>
				<div class="layui-input-inline" id="paperType">
					<select name="type" class="type" lay-filter="type">
						<option value="0" <c:choose><c:when test='${paper.type == 0}'>selected</c:when></c:choose>>自然科学</option>
						<option value="1" <c:choose><c:when test='${paper.type == 1}'>selected</c:when></c:choose>>工程技术</option>
						<option value="2" <c:choose><c:when test='${paper.type == 2}'>selected</c:when></c:choose>>医药卫生</option>
						<option value="3" <c:choose><c:when test='${paper.type == 3}'>selected</c:when></c:choose>>农业科学</option>
						<option value="4" <c:choose><c:when test='${paper.type == 4}'>selected</c:when></c:choose>>哲学政法</option>
						<option value="5" <c:choose><c:when test='${paper.type == 5}'>selected</c:when></c:choose>>社会科学</option>
						<option value="6" <c:choose><c:when test='${paper.type == 6}'>selected</c:when></c:choose>>科教文艺</option>
						<option value="7" <c:choose><c:when test='${paper.type == 7}'>selected</c:when></c:choose>>新闻报道</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">浏览权限</label>
				<div class="layui-input-inline">
					<select name="browseLook" class="newsLook" lay-filter="browseLook">
				        <option value="0" <c:choose><c:when test='${paper.power == 0}'>selected</c:when></c:choose>>开放浏览</option>
				        <option value="1" <c:choose><c:when test='${paper.power == 1}'>selected</c:when></c:choose>>会员浏览</option>
				    </select>
				</div>
			</div>
		</div>
		
		<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">自定义属性</label>
			<div class="layui-input-block" >
				<input type="checkbox" class="tuijian" name="tuijian" title="推荐" >
				<input type="checkbox"  class="newsStatus" name="shenhe" title="审核" <c:choose><c:when test='${paper.status == 0}'>checked</c:when></c:choose>>
				<input type="checkbox"  class="isShow" name="show" title="展示" <c:choose><c:when test='${paper.show == 0}'>checked</c:when></c:choose>>
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
				<script id="paper_content" type="text/plain" style="width:1260px;height:500px;">${paper.text}</script>
				<%-- <textarea id="paper_content" class="layui-textarea" name="content" lay-verify="content" >${paper.text}</textarea> --%>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="addNews">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="<%=path%>/UEditor/ueditor.config.js"></script>
	<script type="text/javascript" src="<%=path%>/UEditor/ueditor.all.js"></script>
	<script type="text/javascript" src="<%=path%>/layui/layui.js"></script>
	<script type="text/javascript" src="<%=path%>/layui/lay/modules/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/page/news/newsEdit.js"></script>
</body>
</html>