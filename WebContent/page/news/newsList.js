var LoginUser = $.parseJSON(user);
layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var newsData = '';
	
	//文章总数
	var paperCount = 0;
	//当前页
	var currPage = 1;
	//开始
	var start = 0;
	//每页显示数据
	var limit = 13;
	
	getPaperList(start,limit);
	
	function getPaperList(start,limit){
		var param = $(".search_input").val();
		$.get("getAllPaper.do?param="+param+"&start="+start+"&num="+limit, function(data){
			paperCount = data.total;
			renderDate(data.paperList);
			toPage();
		})
	}
	
	//查询
	$(".search_btn").click(function(){
		var newArray = [];
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
            	getPaperList(start,limit);
            	toPage();
                layer.close(index);
            },2000);
	})

	//添加文章
	$(".newsAdd_btn").click(function(){
		if(LoginUser.grade==2){
			layer.open({
				content: '没有权限添加文章！'
			});     
			return false;
		}
		
		var index = layui.layer.open({
			title : "添加文章",
			type : 2,
			content : "toAddPaper.do",
			success : function(layero, index){
				layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
					tips: 3
				});
			},
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

	//审核文章
	$(".audit_btn").click(function(){
		if(LoginUser.grade==2){
			layer.open({
				content: '没有权限审核文章！'
			});     
			return false;
		}
		var $checkbox = $('.news_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.news_list tbody input[type="checkbox"][name="checked"]:checked');
		
		if($checkbox.is(":checked")){
			var index = layer.msg('审核中，请稍候',{icon: 16,time:false,shade:0.8});
			console.log($checked);
            setTimeout(function(){
            	for(var j=0;j<$checked.length;j++){
        			var id = $checked.eq(j).parents("tr").find(".news_del").attr("data-id");
        			var oldStatus = $checked.eq(j).parents("tr").find("td:eq(3)").text()=="审核通过"?0:1;
        			var msg = auditAjax(id,oldStatus);
        			console.log(msg);
        			
        			if(msg!="error"){
        				var text = "";
        				if(oldStatus==1){
        					text = "审核通过";
        					//修改列表中的文字
    						$checked.eq(j).parents("tr").find("td:eq(3)").text(text).removeAttr("style");
        				}else{
        					text = "未审核";
        					//修改列表中的文字
    						$checked.eq(j).parents("tr").find("td:eq(3)").text(text).attr("style","color:#f00");
        				}
        				
						//将选中状态删除
						$checked.eq(j).parents("tr").find('input[type="checkbox"][name="checked"]').prop("checked",false);
						form.render();
        			}	
            	}
                layer.close(index);
				layer.msg("审核成功");
            },2000);
		}else{
			layer.msg("请选择需要审核的文章");
		}
	})

	function auditAjax(id,oldStatus){
		var param = {};
		param.id = id;
		param.status = oldStatus==0?1:0;
		
		$.ajax({
			url : "upDataPaper.do",
			type : "post",
			dataType : "json",
			'contentType':'application/json',
			data : JSON.stringify(param),
			success : function(data){
				return data.message;
			}
		})
	}

	//全选
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});

	//通过判断文章是否全部选中来确定全选按钮是否选中
	form.on("checkbox(choose)",function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})
 
	//操作
	$("body").on("click",".news_edit",function(){  //编辑
		if(LoginUser.grade==2){
			layer.open({
				content: '没有权限编辑文章！'
			});     
			return false;
		}
		var _this = $(this);
		var id = _this.attr("data-id");
		var index = layui.layer.open({
			title : "编辑文章信息",
			type : 2,
			content : "toUpdataPaper.do?id="+id,
			area : ['1400px', '600px'],
			success : function(layero, index){
				
			},
			end: function () {
                location.reload();
            }
		})
	})

	$("body").on("click",".news_collect",function(){  //收藏.
		if($(this).text().indexOf("已收藏") > 0){
			layer.msg("取消收藏成功！");
			$(this).html("<i class='layui-icon'>&#xe600;</i> 收藏");
		}else{
			layer.msg("收藏成功！");
			$(this).html("<i class='iconfont icon-star'></i> 已收藏");
		}
	})

	$("body").on("click",".news_del",function(){  //删除
		if(LoginUser.grade==2){
			layer.open({
				content: '没有权限删除文章！'
			});     
			return false;
		}
		var _this = $(this);
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
			var id = _this.attr("data-id");
			$.ajax({'url':"deletePaper.do",
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
	
	function toPage(){
		laypage({
			cont : "page",
			pages : paperCount,
			curr : currPage,
			skip: true,
			jump : function(obj,first){
				currPage = obj.curr;
				start = (obj.curr-1)*limit;
				
				if(!first){
					getPaperList(start,limit);
				}
			}
		})
	}
	
	//渲染数据
	function renderDate(data){
		var dataHtml = '';
		
		if(data.length > 0){
			for(var i=0;i<data.length;i++){
				dataHtml += '<tr>'
		    	+'<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
		    	+'<td align="left">'+data[i].paperName+'</td>';
				
				switch(data[i].type)
				{
				case 0:
					dataHtml += '<td>自然科学</td>';
					break;
				case 1:
					dataHtml += '<td>工程技术</td>';
					break;
				case 2:
					dataHtml += '<td>医药卫生</td>';
					break;
				case 3:
					dataHtml += '<td>农业科学</td>';
					break;
				case 4:
					dataHtml += '<td>哲学政法</td>';
					break;
				case 5:
					dataHtml += '<td>社会科学</td>';
					break;
				case 6:
					dataHtml += '<td>科教文艺</td>';
					break;
				default:
					dataHtml += '<td>自然科学</td>';
				}
				
				dataHtml += '<td>'+data[i].author+'</td>';
		    	if(data[i].status == 0){
		    		dataHtml += '<td >审核通过</td>';
		    	}else{
		    		dataHtml += '<td style="color:#f00">未审核</td>';
		    	}
		    	if(data[i].power == 1){
		    		dataHtml += '<td>开放浏览</td>';
		    	}else{
		    		dataHtml += '<td>会员浏览</td>';
		    	}
		    	
		    	dataHtml += ''
		    	+'<td>'+data[i].date+'</td>'
		    	+'<td>'
				+  '<a class="layui-btn layui-btn-mini news_edit" data-id="'+data[i].id+'"><i class="iconfont icon-edit"></i> 编辑</a>'
				+  '<a class="layui-btn layui-btn-danger layui-btn-mini news_del" data-id="'+data[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
		        +'</td>'
		    	+'</tr>';
			}
		}else{
			dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
		}
		
		$(".news_content").html(dataHtml);
		$('.news_list thead input[type="checkbox"]').prop("checked",false);
    	form.render();
	}
})
