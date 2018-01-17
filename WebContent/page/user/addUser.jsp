<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>用户添加--后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="/layui/css/layui.css" media="all" />
	<style type="text/css">
		.layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
		@media(max-width:1240px){
			.layui-form-item .layui-inline{ width:100%; float:none; }
		}
	</style>
</head>
<body class="childrenBody">
	<form class="layui-form" style="width:80%;" action="addUser.do" model="post">
		<div class="layui-form-item">
			<label class="layui-form-label">登录名</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input userName" lay-verify="required" placeholder="请输入登录名">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">登录密码</label>
			<div class="layui-input-block">
				<input type="password" class="layui-input userpassword" lay-verify="required|newPwd" id="firstPwd" placeholder="请输入密码">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-block">
				<input type="password" class="layui-input userpassword" lay-verify="required|confirmPwd" placeholder="请确认输入密码">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">电话</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input userphone" lay-verify="phone" placeholder="请输入电话">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">邮箱</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input userEmail" lay-verify="email" placeholder="请输入邮箱">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
			    <label class="layui-form-label">性别</label>
			    <div class="layui-input-block userSex">
			      	<input type="radio" name="sex" value="男" title="男" checked>
			      	<input type="radio" name="sex" value="女" title="女">
			      	<input type="radio" name="sex" value="保密" title="保密">
			    </div>
		    </div>
		    <div class="layui-inline">
			    <label class="layui-form-label">用户等级</label>
				<div class="layui-input-block">
					<select name="userGrade" class="userGrade" lay-filter="userGrade">
						<option value="0">超级管理员</option>
						<option value="1">编辑人员</option>
				        <option value="2">问题维修</option>
				    </select>
				</div>
		    </div>
		    <div class="layui-inline">
			    <label class="layui-form-label">用户状态</label>
				<div class="layui-input-block">
					<select name="userStatus" class="userStatus" lay-filter="userStatus">
						<option value="0">正常使用</option>
						<option value="1">限制用户</option>
				    </select>
				</div>
		    </div> 
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="addUser">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="/layui/layui.js"></script>
	<script type="text/javascript" src="/page/user/addUser.js"></script>
</body>
</html>