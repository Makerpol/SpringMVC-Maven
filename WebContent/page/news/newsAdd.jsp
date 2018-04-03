<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.makerpol.entity.User"%>
<%@ page import="net.sf.json.JSONObject"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User)session.getAttribute("LoginUser");
	String userInfo = JSONObject.fromObject(user).toString();
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
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=path%>/css/font_eolqem241z66flxr.css" media="all" />
<script type="text/javascript" src="<%=path%>/js/modules/jquery.min.js"></script>
<script type="text/javascript">
	var user = '<%=userInfo%>';
</script>
</head>
<body class="childrenBody">
	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">文章标题</label>
			<div class="layui-input-block">
				<input type="text" maxlength="50" class="layui-input newsName"
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
						onclick="layui.laydate({elem:this,format:'YYYY-MM-DD hh:mm:ss'})">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">栏目类型</label>
				<div class="layui-input-inline" id="paperType">
					<select name="type" class="type" lay-filter="type" >
						<option value="0" disabled>通知公告</option>
						<option value="1" disabled>图片新闻</option>
						<option value="2" disabled>工作动态</option>
						<option value="3" disabled>科技资讯</option>
						<option value="4" disabled>科技文摘</option>
						<option value="5" disabled>札记</option>
						<option value="6" disabled>随笔</option>
						<option value="7" disabled>观点</option>
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
				<script id="paper_content" type="text/plain" style="width:1260px;height:500px;"></script>
			
				<!-- <textarea id="news_content" class="layui-textarea" name="content"
					lay-verify="content"></textarea> -->
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
	<script type="text/javascript" src="<%=path%>/page/news/newsAdd.js"></script>
</body>
</html>