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
	
	getVideoList(start,num);
	function getVideoList(start,num){
		var param = $(".search_input").val();
		$.ajax({
			url: "getVideoList.do?param="+param+"&start="+start+"&num="+num,
			type:"get",
			success:function(data){
				console.log(data);
				total = data.total;
				renderDate(data.videos);
				toPage();
			}
		});
	}
	
	function renderDate(data){
		var html = "";
		for(var i=0;i<data.length;i++){
			html += '<li>';
			html += '<div  style="float: left;margin-right: 50px;background-color: #369e4c;width: 360px;height: 200px;margin-bottom:20px;">';
			html += '<video class="video-js vjs-default-skin  vjs-big-play-centered" width="360" height="200" poster="" controls preload="metadata" data-setup="{}">';
			html += ' <source src="'+data[i].path+'" type="video/mp4">';
			html += '</video></div>';
			html += '<div style="float:left;position:relative;width:250px;height:200px; border-bottom: 1px solid #e5e9ef;">';
			html += '<a href="'+data[i].path+'" title="'+data[i].videoname+'">'+data[i].videoname+'</a>';
			html += '<div style="padding-top: 14px;color: #99a2aa;">'+data[i].date+'</div>';
			html += '</div></li>';
		}
		$("#videoList").html(html);
		//form.render();
	}
	
	function toPage(){
		laypage({
			cont : "PDFpage",
			pages : Math.ceil(total/num),
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
	
	//添加
	$(".file-addBtn").click(function(){
		var index = layui.layer.open({
    		title : "添加视频",
			type : 2,
			content : "toAddVideoPage.do",
			success : function(layero, index){},
			end: function () {
                location.reload();
            }
    	})
    	//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	})
	
	
	
})