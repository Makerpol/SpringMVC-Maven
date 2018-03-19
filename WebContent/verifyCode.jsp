<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>后台管理系统</title>
  <link rel="stylesheet" href="css/login.css">
  <link rel="icon" type="image/x-icon" href="images/Tech.ico"/>  
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script type="text/javascript" src="/layui/layui.js" charset="utf-8"></script>
</head>
<body class="login-bg">
    <canvas id="fullstarbg">你的浏览器不支持canvas标签</canvas>
    <div class="login" style="min-height:auto;">
        	<input id="userMail" placeholder="绑定邮箱"  type="text"  class="layui-input" >
        	<hr class="hr15">
			<input id="verifyCode" lay-verify="required" placeholder="验证码"  type="text" style="width:120px;" class="layui-input">
			<input id="verifyCodeBtn" value="获取验证码"  lay-submit lay-filter="verifyCode"  style="width:140px;background-color:gray;" type="submit">
			<hr class="hr15">
        	<input id="chengePWD" value="修改密码"  lay-submit lay-filter="chengePWD"   type="submit">
        
    </div>
	<script type="text/javascript" src="verifyCode.js"></script>
</body>
</html>