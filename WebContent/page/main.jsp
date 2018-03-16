<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<title>后台管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=path%>/css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="<%=path%>/css/main.css" media="all" />
</head>
<body class="childrenBody">
	<div class="panel_box row">
		<div class="panel col">
			<a>
				<div class="panel_icon" style="background-color:#FF5722;">
					<i class="iconfont icon-dongtaifensishu" data-icon="icon-dongtaifensishu"></i>
				</div>
				<div class="panel_word userAll">
					<span></span>
					<cite>新增人数</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a >
				<div class="panel_icon" style="background-color:#009688;">
					<i class="layui-icon" data-icon="&#xe613;">&#xe613;</i>
				</div>
				<div class="panel_word userAll">
					<span></span>
					<cite>用户总数</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a>
				<div class="panel_icon" style="background-color:#5FB878;">
					<i class="layui-icon" data-icon="&#xe64a;">&#xe64a;</i>
				</div>
				<div class="panel_word imgAll">
					<span></span>
					<cite>PDF总数</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a >
				<div class="panel_icon" style="background-color:#F7B824;">
					<i class="iconfont icon-wenben" data-icon="icon-wenben"></i>
				</div>
				<div class="panel_word waitNews">
					<span></span>
					<cite>待审核文章</cite>
				</div>
			</a>
		</div>
		<div class="panel col max_panel">
			<a >
				<div class="panel_icon" style="background-color:#2F4056;">
					<i class="iconfont icon-text" data-icon="icon-text"></i>
				</div>
				<div class="panel_word allNews">
					<span></span>
					<em>文章总数</em>
					<cite>文章列表</cite>
				</div>
			</a>
		</div>
	</div>
	<fieldset class="layui-elem-field layui-field-title site-title">
		<legend>
			<a name="default">信息统计</a>
		</legend>
	</fieldset>
	<div class="row">
		 <div>
         <section class="panel">
             <div class="panel-heading">区域统计</div>
             <div class="panel-body">
                 <div class="echarts" id="main" style="height:300px; width:500px"></div>
             </div>
          </section>
        </div>
         <div>
         <section class="panel">
             <div class="panel-heading">区域统计</div>
             <div class="panel-body">
                 <div class="echarts" id="main" style="height:300px; width:500px"></div>
             </div>
          </section>
        </div>
	</div>
	
	<script type="text/javascript" src="<%=path%>/layui/layui.js"></script>
	<script type="text/javascript" src="<%=path%>/js/modules/echarts.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/main.js"></script>
</body>
</html>