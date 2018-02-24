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
  <link rel="stylesheet" href="/layui/css/layui.css" media="all" />
  <link rel="stylesheet" href="/css/login.css">
  <link rel="icon" type="image/x-icon" href="images/Tech.ico"/>  
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script type="text/javascript" src="/layui/layui.js" charset="utf-8"></script>
</head>
<body class="login-bg">
    <canvas id="fullstarbg">你的浏览器不支持canvas标签</canvas>
    <div class="login" style="min-height:auto;">
    	<form class="layui-form">
    		<input type="text" style="display:none;" value="${sessionScope.verifyUser.id}" id="id" >
        	
        	<input type="password" value="" placeholder="请输入新密码" lay-verify="required|newPwd" id="firstPwd" class="layui-input pwd">

        	<hr class="hr15">
        	<input type="password" value="" placeholder="请确认密码" lay-verify="required|confirmPwd" class="layui-input pwd">
			
			<hr class="hr15">
        	<input id="chengePWD" value="立即修改"  lay-submit="" lay-filter="changePwd"   type="submit">
    	
    	</form>
    		
        
    </div>
	<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.min.js"></script>  
	<script type="text/javascript" src="/page/user/user.js"></script>

</body>
</html>