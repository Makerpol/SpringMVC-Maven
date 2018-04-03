<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>PDF添加</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css" media="all" />
<style type="text/css">
.PDFimage{
	width:200px;
	height:250px;
	margin-left:15px;
}
.layui-inline{
	margin-bottom:20px;
}
</style>
</head>
<body class="childrenBody">
	<div class="layui-form-item">
		<div class="layui-inline">
				<label class="layui-form-label">选择文件</label>
				<div class="layui-input-block">
					<input type="file" name="upfile" class="layui-upload-file" lay-title="选择视频文件">
				</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">文件路径</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input layui-disabled path">
			</div>
		</div>
	</div>
	<div class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">文件名字</label>
			<div class="layui-input-block">
				<input type="text" maxlength="50" class="layui-input fileName"
					lay-verify="required" placeholder="请输入文章标题">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">作者姓名</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input author"
						lay-verify="required|author" placeholder="请输入作者姓名">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">联系电话</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input phone"
						lay-verify="required|phone" placeholder="请输入联系电话">
				</div>
			</div>
			<div class="layui-inline">
				<lable class="layui-form-label">地址</lable>
				<div class="layui-input-block">
					<input type="text" class="layui-input adds"
						lay-verify="required" placeholder="请输入联系地址">
				</div>
			</div>
			<div class="layui-inline">
				<lable class="layui-form-label">邮箱</lable>
				<div class="layui-input-block">
					<input type="text" class="layui-input mail"
						lay-verify="required|mail" placeholder="请输入联系邮箱">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">文章类型</label>
				<div class="layui-input-inline" id="fileType">
					<select name="type" class="type" lay-filter="type" >
						<option value="0">自然科学</option>
						<option value="1">工程技术</option>
						<option value="2">医药卫生</option>
						<option value="3">农业科学</option>
						<option value="4">哲学政法</option>
						<option value="5">社会科学</option>
						<option value="6">科教文艺</option>
						<option value="7">新闻报道</option>
					</select>
				</div>
			</div>

			<div class="layui-inline">
				<label class="layui-form-label">审核状态</label>
				<div class="layui-input-inline">
					<select name="browseLook" class="status" lay-filter="status">
						<option value="0">未审核</option>
						<option value="1">已初审</option>
						<option value="2">已复核</option>
						<option value="3">已终审</option>
					</select>
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">作者备注</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入作者备注" class="layui-textarea remark"></textarea>
			</div>
		</div>
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<a class="layui-btn submit_btn" >立即提交</a>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=path%>/layui/layui.js"></script>
	<script type="text/javascript" src="<%=path%>/page/copies/copiesAdd.js"></script>
</body>
</html>