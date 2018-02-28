<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.makerpol.entity.User"%>
<%@ page import="net.sf.json.JSONObject"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)session.getAttribute("LoginUser");
String userInfo = JSONObject.fromObject(user).toString();
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>文章列表--layui后台管理模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="/css/font_eolqem241z66flxr.css" media="all" />
    <link rel="stylesheet" href="/css/news.css" media="all" />
</head>

<body class="childrenBody">
    <blockquote class="layui-elem-quote news_search">
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="text" value="" placeholder="请输入关键字" class="layui-input search_input">
            </div>
            <a class="layui-btn search_btn">查询</a>
        </div>
    </blockquote>
    <div class="layui-form">
        <ul >
            <li style="padding-top: 20px;clear: both;">
               <div id="div1" style="float: left;margin-right: 50px;background-color: #369e4c;width: 360px;height: 200px;">
               	<embed type="application/x-shockwave-flash" class="edui-faked-video" pluginspage="http://www.macromedia.com/go/getflashplayer" src="http://v.ifeng.com/include/exterior.swf?guid=b0c019ac-d594-4c6b-8c69-3ac84788d11b&amp;fromweb=baiduvideo&amp;AutoPlay=true" width="360" height="200" wmode="transparent" play="true" loop="false" menu="false" allowscriptaccess="never" allowfullscreen="true">
               </div>
               <div id="div2" style="float:left;position:relative;width:600px;height:200px; border-bottom: 1px solid #e5e9ef;">
               		<a href="1517879946026041650.mp4" title="重启后只能用密码解锁">重启后只能用密码解锁</a>
               		<div style="padding-top: 14px;color: #99a2aa;">内容简介</div>
               </div>
            </li>
            
            <li style="padding-top: 20px;clear: both;">
               <div id="div1" style="float: left;margin-right: 50px;background-color: #369e4c;width: 360px;height: 200px;">
               	
					<video width="360" height="200" controls>
					    <source src="https://bpic.588ku.com/video_listen/588ku_preview/18/01/12/11/22/03/46/video5a5829db5c2d8.mp4" type="video/mp4">
					    
					    您的浏览器不支持 video 标签。
					</video>

               </div>
               <div id="div2" style="float:left;position:relative;width:600px;height:200px; border-bottom: 1px solid #e5e9ef;">
               		<a href="1517879946026041650.mp4" title="重启后只能用密码解锁">重启后只能用密码解锁</a>
               		<div style="padding-top: 14px;color: #99a2aa;">内容简介</div>
               </div>
            </li>
             <li style="padding-top: 20px;clear: both;">
               <div id="div1" style="float: left;margin-right: 50px;background-color: #369e4c;width: 360px;height: 200px;">
               	
					<video width="360" height="200" controls>
					    <source src="https://bpic.588ku.com/video_listen/588ku_preview/18/01/12/11/22/03/46/video5a5829db5c2d8.mp4" type="video/mp4">
					    
					    您的浏览器不支持 video 标签。
					</video>

               </div>
               <div id="div2" style="float:left;position:relative;width:600px;height:200px; border-bottom: 1px solid #e5e9ef;">
               		<a href="1517879946026041650.mp4" title="重启后只能用密码解锁">重启后只能用密码解锁</a>
               		<div style="padding-top: 14px;color: #99a2aa;">内容简介</div>
               </div>
            </li>
             <li style="padding-top: 20px;clear: both;">
               <div id="div1" style="float: left;margin-right: 50px;background-color: #369e4c;width: 360px;height: 200px;">
               	
					<video width="360" height="200" controls>
					    <source src="https://bpic.588ku.com/video_listen/588ku_preview/18/01/12/11/22/03/46/video5a5829db5c2d8.mp4" type="video/mp4">
					    
					    您的浏览器不支持 video 标签。
					</video>

               </div>
               <div id="div2" style="float:left;position:relative;width:600px;height:200px; border-bottom: 1px solid #e5e9ef;">
               		<a href="1517879946026041650.mp4" title="重启后只能用密码解锁">重启后只能用密码解锁</a>
               		<div style="padding-top: 14px;color: #99a2aa;">内容简介</div>
               </div>
            </li>
        </ul>
    </div>
    <div id="page"></div>
    <script type="text/javascript" src="/layui/layui.js"></script>
    <script type="text/javascript" src="videoList.js"></script>
</body>

</html>