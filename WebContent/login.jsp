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
  <link rel="stylesheet" href="<%=path%>/css/login.css">
  <link rel="icon" type="image/x-icon" href="images/Tech.ico"/> 
  <script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.min.js"></script> 
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script type="text/javascript" src="<%=path%>/layui/layui.js" charset="utf-8"></script>
</head>
<body class="login-bg">
    <canvas id="fullstarbg">你的浏览器不支持canvas标签</canvas>
    <div class="login">
        <div class="message">后台系统登录</div>
        <div id="darkbannerwrap"></div>   
        <form  action="userLogin.do" mo class="layui-form" method="post" onsubmit="return checkInput()">
            <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input id="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <input type="hidden" id="password_md5" name="password">
            <hr class="hr15">
            <input value="登录"  lay-submit lay-filter="login"  style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
        <div >${LoginMessige}</div>
        <!-- <div class="Fmsg"><a href="verifyCode.jsp">忘记密码？</a></div> -->
    </div>
    <script>
    layui.use(['form']);
    function checkInput(){
    	var password_input = document.getElementById('password');
        var password_md5 = document.getElementById('password_md5');

        // set password
        password_md5.value =  md5(password_input.value).toLocaleUpperCase();
        return true;
    }
    </script>
</body>
</html>