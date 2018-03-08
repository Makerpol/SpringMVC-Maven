var echarts;

layui.config({
	base : "js/"
}).use(['form','element','layer','jquery'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		element = layui.element(),
		$ = layui.jquery;
	
	var dataList;
	
	//动态获取文章总数和待审核文章数量,最新文章
	$.get("getPaperListInfo.do",function(data){
		$(".allNews span").text(data.total);
		$(".waitNews span").text(data.wait);
	})

	//图片总数
	$.get("getPDFCount.do",function(data){
		$(".imgAll span").text(data.total);
	})

	//用户数
	$.get("getUserCount.do",function(data){
		$(".userAll span").text(data.total);
	})
})
