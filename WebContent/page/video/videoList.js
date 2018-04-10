var path = path;
layui.config({
	base:"/js"
}).use(["form","layer","jquery","laypage"],function(){
	var layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery,
		form = layui.form();
	
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
	
	function renderDate(data){	//渲染
		var html = "";
		for(var i=0;i<data.length;i++){
			html += '<tr>';
			html += '<td><input type="checkbox" name="checked" value="'+ data[i].id+'" lay-skin="primary" lay-filter="choose"></td>';
			html += '<td style="text-align:left;">'+data[i].videoname+'</td>';
			html += '<td>'+data[i].username+'</td>';
			html += '<td>'+data[i].date+'</td>';
			
			if(data[i].paperid==0){
				html += '<td>'+"无"+'</td>';
			}else{
				html += '<td style="color:red;">'+data[i].paperid+'</td>';
			}
			
			html += '<td>';
			html += '<a class="layui-btn layui-btn-mini links_edit" data-id="'+data[i].id+'"><i class="iconfont icon-edit"></i> 编辑</a>'
			+  '<a class="layui-btn layui-btn-danger layui-btn-mini news_del" data-id="'+data[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			+  '<a class="layui-btn layui-btn-mini news_text" data-id="'+data[i].id+'"><i class="iconfont icon-text"></i> 下载</a>'
			html += '</td>';
			html += '</tr>';
		}
		$(".video_content").html(html);
		$('.video_list thead input[type="checkbox"]').prop("checked",false);
		form.render();
	}
	
	function toPage(){	//分页
		laypage({
			cont : "topage",
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
	
	document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) {
        	var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
            	getVideoList(start,num);
                layer.close(index);
            },500);
        }
    };
	
	//查询
	$(".search_btn").click(function(){
		var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
        	getVideoList(start,num);
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
	
	$("body").on("click",".links_edit",function(){	//编辑
		var _this = $(this);
		var id = _this.attr("data-id");
		var index = layui.layer.open({
			title : "编辑视频信息",
			type : 2,
			shadeClose: false,
			content : "toVideoInfo.do?id="+id,
			area : ['1500px', '620px'],
			success : function(layero, index){
				
			},
			end: function () {
                location.reload();
            }
		})
	})
	
	$("body").on("click",".news_del",function(){	//删除
		var _this = $(this);
		var t = _this.parents("tr").find("td:eq(4)").html();
		if(t!="无"){
			layer.open({
				content: '当前视频已经被文章引用，无法直接删除！'
			});     
			return false;
		}
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
			var id = _this.attr("data-id");
			$.ajax({'url':"deleteVideo.do",
				'data': {'id':id},
				'success':function(data){
					if("error"==data.message){
						setTimeout(function(){
							layer.close(index);
							layer.msg("提交失败！");
						},2000);
					}
					_this.parents("tr").remove();
				}
			});
			layer.close(index);
		});
	})
	
})