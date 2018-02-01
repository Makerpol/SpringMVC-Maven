<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.makerpol.entity.User"%>
<%@ page import="net.sf.json.JSONObject"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)session.getAttribute("user");
String userInfo = JSONObject.fromObject(user).toString();
%>
<html>
<head>
<script type="text/javascript" src="/js/modules/jquery.min.js"></script>
<style type="text/css">
.main{
width : 100%;
height : 100%;
}

.content{
	width : 1400px;
	height : auto;
	/* background-color : #c1bcbc; */
	top: 5px;
	bottom: 0px;
	left: 0px;
	right: 0px;
	margin-left: auto;
	margin-right: auto;
}

.title h2{
	text-align:center;
}
.paperInfo{
	text-align:center;
	padding-bottom:25px;
	font-size:12px;
	color:gray;
}
.h-author, .aticle-src{
	margin-right:45px;
}

</style>
<script type="text/javascript">
var id = window.location.search.substring(1);

$.ajax({
	"url":"getPaper.do?id="+id,
	"dataType" : "json",
	"success":function(data){
		console.log(data.paper);
		
		$(".title h2").html(data.paper.paperName);
		$(".h-author").append(data.paper.author);
		$(".aticle-src").append(data.paper.date);
		$(".clickCount").append(data.paper.clickCount);
		$(".txt").html(data.paper.text);
	}
})


</script>
</head>
<body>

<div class="main">
	<div class="content">
		<div class="title"><h2></h2></div>
		<div class="paperInfo">
			<span class="sub-author">作者：
			<span class="h-author"></span>
			</span>
			<span class="sub-src">
			发表时间：
			<span class="aticle-src">
			</span>
			</span>
			<span class="sub-clickCount">
			访问量：
			<span class="clickCount">
			</span>次
			</span>
		</div>
		<div class="txt"></div>
	</div>
</div>

</body>
</html>