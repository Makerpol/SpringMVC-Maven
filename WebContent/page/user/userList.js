var LoginUser = $.parseJSON(user);
layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var userData = '';
	
	//总数
	var total = 0;
	//开始位置
	var start = 0;
	//每页显示数
	var num = 13;
	//当前页
	var currPage = 1;
	
	getUserList(start, num);
	
	function getUserList(start,num){
		var param = $(".search_input").val();
		$.ajax({
			url: "getUserList.do?param="+param+"&start="+start+"&num="+num,
			type:"get",
			success:function(data){
				total = data.total;
				renderDate(data.userList);
				toPage();
			}
		});
	}
	
	document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) {
        	var param = $(".search_input").val();
    		
    		var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
            	getUserList(start,num);
            	toPage();
                layer.close(index);
            },2000);
        }
    };
    
	//查询
	$(".search_btn").click(function(){
		var param = $(".search_input").val();
		
		var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
        	getUserList(start,num);
        	toPage();
            layer.close(index);
        },2000);
	})
	
	function getStatus(status){
		var msg;
		if(status == 0){
			msg = "正常使用";
		}else if(status == 1){
			msg = "禁用状态";
		}
		return msg;
	};
	
	function getGrade(grade){
		var msg;
		if(grade==0){
			msg= "管理员";
		}else if(grade==1){
			msg= "责任主编";
		}else if(grade==2){
			msg= "编辑记者";
		}else if(grade==3){
			msg= "普通用户";
		}
		return msg;
	}
	
	//添加新用户
	$(".linksAdd_btn").click(function(){
		if(LoginUser.grade==2|| LoginUser.grade==1){
			layer.open({
				content: '没有权限添加用户！'
			});     
			return false;
		}
		var index = layui.layer.open({
			title : "添加新用户",
			type : 2,
			content : "toAddUser.do",
			success : function(layero, index){
			
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

	//批量删除
	$(".batchDel").click(function(){
		var $checkbox = $('.links_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.links_list tbody input[type="checkbox"][name="checked"]:checked');
		$(".layui-unselect layui-form-checkbox layui-form-checked")
		if($checkbox.is(":checked")){
			layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
	            setTimeout(function(){
	            	//删除数据
	            	for(var j=0;j<$checked.length;j++){
	            		var id = $checked.eq(j).parents("tr").find(".links_del").attr("data-id");
	            		console.log(id);
	            		/*for(var i=0;i<linksData.length;i++){
							if(linksData[i].linksId == ){
								linksData.splice(i,1);
								linksList(linksData);
							}
						}*/
	            	}
	            	$('.links_list thead input[type="checkbox"]').prop("checked",false);
	            	form.render();
	                layer.close(index);
					layer.msg("删除成功");
	            },2000);
	        })
		}else{
			layer.msg("请选择需要删除的文章");
		}
	})

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
		data.elem.checked;
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})
	
	//密码重置
	$("body").on("click",".password_reset",function(){
		if(LoginUser.grade!=0){
			layer.open({
				content: '没有权限重置密码！'
			});     
			return false;
		}
		
		var _this = $(this);
		var id = _this.attr("data-id");
		layer.confirm("确定重置此用户的密码？",{icon:3,title:"提示信息"},function(index){
			$.ajax({
				"url":"reSetPassword.do?id="+id,
				success:function(data){
					if(data.msg!="error"){
						layer.msg("密码重置成功！");
					}
				}
			})
			
			layer.close(index);
		})
	})
	
	//操作
	$("body").on("click",".links_edit",function(){  //编辑

		if(LoginUser.grade!=0){
			layer.open({
				content: '没有权限编辑用户信息！'
			});     
			return false;
		}
		
		var _this = $(this);
		var id = _this.attr("data-id");
		var index = layui.layer.open({
			title : "编辑用户信息",
			type : 2,
			shadeClose: false,
			content : "searchUser.do?id="+id,
			area : ['1500px', '620px'],
			success : function(layero, index){
				
			},
			end: function () {
                location.reload();
            }
		})
	})

	$("body").on("click",".links_del",function(){  //删除
		
		if(LoginUser.grade!=0){
			layer.open({
				content: '没有权限删除用户！'
			});     
			return false;
		}
		
		var _this = $(this);
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
			var id = _this.attr("data-id");
			$.ajax({'url':"deleteUser.do",
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
			pages : Math.ceil(total/num),
			curr : currPage,
			skip: true,
			jump : function(obj,first){
				currPage = obj.curr;
				start = (obj.curr-1)*num;
				
				if(!first){
					getUserList(start,num);
				}
			}
		})
	}
	
	//渲染数据
	function renderDate(data){
		var dataHtml = '';
		if(data!=null && data.length != 0){
			for(var i=0;i<data.length;i++){
				
				if(data[i].status==2){
					break;
				}
				
				dataHtml += '<tr>'
		    	+'<td><input type="checkbox" name="checked" value="'+ data[i].id+'" lay-skin="primary" lay-filter="choose"></td>'
		    	+'<td>'+data[i].realname+'</td>'
		    	+'<td>'+data[i].name+'</td>';
		    	
				var text;
		    	switch(data[i].grade){
		    	case 0:
		    		text="管理员";
		    		break;
		    	case 1:
		    		text="责任主编";
		    		break;
		    	case 2:
		    		text="编辑记者";
		    		break;
		    	case 3:
		    		text="普通用户";
		    		break;
		    	default:
		    		text="编辑记者";
		    	}
		    	
		    	dataHtml += '<td>'+text+'</td>'
		    	+'<td>'+data[i].email+'</td>'
		    	+'<td>'+data[i].phone+'</td>';
		    	
		    	switch(data[i].status){
		    	case 0:
		    		text="正常使用";
		    		break;
		    	case 1:
		    		text="禁用状态";
		    		break;
		    	default:
		    		text="正常使用";
		    	}
		    	
		    	dataHtml += '<td>'+text+'</td>'
		    	+'<td>'
				+  '<a class="layui-btn layui-btn-mini password_reset" data-id="'+data[i].id+'"><i class="iconfont icon-shuaxin1"></i> 密码重置</a>'
				+  '<a class="layui-btn layui-btn-mini links_edit" data-id="'+data[i].id+'"><i class="iconfont icon-edit"></i> 编辑</a>'
				+  '<a class="layui-btn layui-btn-danger layui-btn-mini links_del" data-id="'+data[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
		        +'</td>'
		    	+'</tr>';
			}
		}else{
			dataHtml = '<tr><td colspan="7">暂无数据</td></tr>';
		}
		$(".user_content").html(dataHtml);
		$('.users_list thead input[type="checkbox"]').prop("checked",false);
    	form.render();
	}
})
