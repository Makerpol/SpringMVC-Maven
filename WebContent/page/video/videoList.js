/**
 * 
 */

var LoginUser = $.parseJSON(user);
layui.config({
	base:"/js"
}).use(["form","layer","jquery","laypage"],function(){
	var layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery,
		form = layer.form;
	
	//总数
	var total = 0;
	//开始位置
	var start = 0;
	//每页显示数
	var num = 13;
	//当前页
	var currPage = 1;
	
	function getVideoList(start,num){
		var param = $(".search_input").val();
		$.ajax({
			url: "getVideoList.do?param="+param+"&start="+start+"&num="+num,
			type:"get",
			success:function(data){
				total = data.total;
				renderDate(data.videos);
				toPage();
			}
		});
	}
	
	function renderDate(data){
		
	}
	
	function toPage(){
		laypage({
			cont : "page",
			pages : total,
			curr : currPage,
			skip: true,
			jump : function(obj,first){
				currPage = obj.curr;
				start = (obj.curr-1)*num;
				
				if(!first){
					getVideoList(start,num);
				}
			}
		})
	}
	
	//查询
	$(".search_btn").click(function(){
		var newArray = [];
		var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
        	getVideoList(start,limit);
        	toPage();
            layer.close(index);
        },2000);
	})
	
})