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
	<title>个人资料--layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="/css/user.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form" action="updataUser.do" method="post">
		<div class="user_left">
			<div class="layui-hide">
			    <input type="text" value="${user.id }" id="id" >
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">用户名</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${user.alias}" id="alias" class="layui-input layui-disabled">
			    </div>
			</div>
			<div class="layui-form-item">
				<label for="role" class="layui-form-label">
                    	用户组
                </label>
                    <div class="layui-input-inline">
                      <select id="grade" name="role" <c:choose><c:when test="${user.grade != 0}">disabled="disabled"</c:when></c:choose>>
                        <option value="">请选择用户组</option>
                        <option value="0" <c:choose><c:when test="${user.grade == 0}">selected='selected'</c:when></c:choose>>超级管理员</option>
                        <option value="1" <c:choose><c:when test="${user.grade == 1}">selected='selected'</c:when></c:choose>>编辑人员</option>
                        <option value="2" <c:choose><c:when test="${user.grade == 2}">selected='selected'</c:when></c:choose>>问题维护</option>
                      </select>
                 </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">真实姓名</label>
			    <div class="layui-input-block">
			    	<input type="text" id="name" value="${user.name }" placeholder="请输入真实姓名" lay-verify="required" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">手机号码</label>
			    <div class="layui-input-block">
			    	<input type="tel" id="phone" value="<c:choose><c:when test="${user.phone!=0}">${user.phone}</c:when></c:choose>" placeholder="请输入手机号码" lay-verify="phone" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">出生年月</label>
			    <div class="layui-input-block">
			    	<input type="text" id="birthday" value="${user.birthday}" placeholder="请输入出生年月" lay-verify="required|date" onclick="layui.laydate({elem: this,max: laydate.now()})" class="layui-input">
			    </div>
			</div>
			
			<div class="layui-form-item">
			    <label class="layui-form-label">邮箱</label>
			    <div class="layui-input-block">
			    	<input type="text" id="email" value="${user.email}" placeholder="请输入邮箱" lay-verify="required|email" class="layui-input">
			    </div>
			</div>
		</div>
		<div class="user_right">
			<input type="file" name="upfile" class="layui-upload-file" lay-title="我要换一个头像">
			<p>预览</p>
			<img src="${user.image}" class="layui-circle" id="userFace">
		</div>
		<div class="layui-form-item" style="margin-left: 5%;">
		    <div class="layui-input-block">
		    	<button class="layui-btn" lay-submit="" lay-filter="updataUser">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.min.js"></script>  
	<script type="text/javascript" src="/layui/layui.js"></script>
	<script type="text/javascript" src="/page/user/address.js"></script>
	<script type="text/javascript" src="/page/user/user.js"></script>
</body>
</html>