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
	<link rel="stylesheet" href="<%=path%>/layui/css/layui.css" media="all" />
	<style type="text/css">
		.layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
		@media(max-width:1240px){
			.layui-form-item .layui-inline{ width:100%; float:none; }
		}
		.tips{
			margin-left:20px;
			font-size:12px;
			color:red;
		}
	</style>
</head>
<body class="childrenBody">
	<form class="layui-form" style="width:80%;">
		<div class="layui-form-item">
			<label class="layui-form-label">登录名</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input userName" lay-verify="required|username" placeholder="请输入登录名">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">真实姓名</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input realname" lay-verify="required|realname" placeholder="请输入真实姓名">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">身份证号</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input IDCard" lay-verify="required|IDCard" placeholder="请输入身份证号">
			</div>
		</div>
		<!-- <div class="layui-form-item">
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
		</div> -->
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
			<label class="layui-form-label">栏目</label>
			<div class="layui-input-block column">
				<input type="checkbox" name="" class="tzgg"  value="0" title="通知公告">
				<input type="checkbox" name="" class="tpxw" value="1" title="图片新闻">
				<input type="checkbox" name="" class="gzdt" value="2" title="工作动态">
				<input type="checkbox" name="" class="kjzx" value="3" title="科技资讯">
				<input type="checkbox" name="" class="kjwz" value="4" title="科技文摘">
				<input type="checkbox" name="" class="zj" value="5" title="札记">
				<input type="checkbox" name="" class="suib" value="6" title="随笔">
				<input type="checkbox" name="" class="gd" value="7" title="观点">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
			    <label class="layui-form-label">性别</label>
			    <div class="layui-input-block userSex">
			      	<input type="radio" name="sex" value="1" title="男" checked>
			      	<input type="radio" name="sex" value="0" title="女">
			    </div>
		    </div>
		    <div class="layui-inline">
			    <label class="layui-form-label">用户等级</label>
				<div class="layui-input-block">
					<select name="userGrade" class="userGrade" lay-filter="userGrade">
						<option value="0">管理员</option>
						<option value="1">主编主任</option>
				        <option value="2">编辑记者</option>
				        <option value="3">普通人员</option>
				    </select>
				</div>
		    </div>
		    <div class="layui-inline">
			    <label class="layui-form-label">用户状态</label>
				<div class="layui-input-block">
					<select name="userStatus" class="userStatus" lay-filter="userStatus">
						<option value="0">正常使用</option>
						<option value="1">禁止使用</option>
				    </select>
				</div>
		    </div> 
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="addUser">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				<span class="tips">默认密码：123456</span>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="<%=path%>/layui/layui.js"></script>
	<script type="text/javascript" src="<%=path%>/page/user/addUser.js"></script>
</body>
</html>