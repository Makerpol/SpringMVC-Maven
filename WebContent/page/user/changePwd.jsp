<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>修改密码--layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=path%>/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="<%=path%>/css/user.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form changePwd" action="updataUser.do" model="post">
		<div style="margin:0 0 15px 110px;color:#f00;">新密码必须两次输入一致</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">用户名</label>
		    <div class="layui-input-block">
		    	<input type="text" id="name" value="${user.name}" disabled class="layui-input layui-disabled">
		    </div>
		    <div class="layui-hide">
			    <input type="text" value="${user.id}" id="id" >
			    <input type="text" value="${user.password}" id="password" >
			</div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">旧密码</label>
		    <div class="layui-input-block">
		    	<input type="password" value="" placeholder="请输入旧密码" lay-verify="required|oldPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">新密码</label>
		    <div class="layui-input-block">
		    	<input type="password" value="" placeholder="请输入新密码" lay-verify="required|newPwd" id="firstPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">确认密码</label>
		    <div class="layui-input-block">
		    	<input type="password" value="" placeholder="请确认密码" lay-verify="required|confirmPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-input-block">
		    	<button class="layui-btn" lay-submit="" lay-filter="changePwd">立即修改</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.min.js"></script>  
	<script type="text/javascript" src="<%=path%>/layui/layui.js"></script>
	<script type="text/javascript" src="<%=path%>/page/user/user.js"></script>
</body>
</html>