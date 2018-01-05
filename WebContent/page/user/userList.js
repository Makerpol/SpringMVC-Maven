 layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var linksData = '';
	/*$.ajax({
		url : "../../json/linksList.json",
		type : "get",
		dataType : "json",
		success : function(data){
			linksData = data;
			if(window.sessionStorage.getItem("addLinks")){
				var addLinks = window.sessionStorage.getItem("addLinks");
				linksData = JSON.parse(addLinks).concat(linksData);
			}
			//执行加载数据的方法
			linksList();
		}
	})*/

	//查询
	$(".search_btn").click(function(){
		var newArray = [];
		var param = $(".search_input").val();
		
		if( param != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
            	$.ajax({
					url : "searchUserByLike.do?param="+param,
					type : "get",
					//dataType : "json",
					success : function(data){
						console.log(data);
						var userList = data.userList;
						if(userList.length>0){
							$(".layui-table tr:not(:first)").remove();
							var table = $(".layui-table");
							var trs = '';
							for(var i = 0; i<userList.length;i++){
								var user = userList[i];

								var tr='<tr><td><input type="checkbox" name="checked" value="'+user.id+'"'+' '+'lay-skin="primary" lay-filter="choose">';
								tr= tr+'<div class="layui-unselect layui-form-checkbox" lay-skin="primary"><i class="layui-icon"></i></div></td>';
								tr= tr+'<td align="left">'+user.name+'</td> ';
								tr= tr+'<td>'+getGrade(user.grade)+'</td>';
								tr= tr+'<td>'+user.email+'</td>';
								tr= tr+'<td>'+user.phone+'</td>';
								tr= tr+'<td>'+getStatus(user.status)+'</td>';
								tr= tr+'<td><a class="layui-btn layui-btn-mini links_edit" data-id=\"'+user.id+'\"><i class="iconfont icon-edit"></i> 编辑</a>'; 
								tr= tr+'<a class="layui-btn layui-btn-danger layui-btn-mini links_del" data-id=\"'+user.id +'\">';
								tr= tr+'<i class="layui-icon">&#xe640;</i> 删除</a></td>';
								tr= tr+'</tr>';
								trs= trs+tr;
							}
							console.log(trs);
							table.append(trs);
						}
					}
				})
            	
                layer.close(index);
            },2000);
		}else{
			layer.msg("请输入需要查询的内容");
		}
	})
	
	function getStatus(status){
		var msg;
		if(status == 0){
			msg = "正常用户";
		}else if(status == 1){
			msg = "限制用户";
		}
		return msg;
	};
	
	function getGrade(grade){
		var msg;
		if(grade==0){
			msg= "超级管理员";
		}else if(grade==1){
			msg= "编辑人员";
		}else if(grade==2){
			msg= "问题维修";
		}
		return msg;
	}
	//添加新用户
	$(".linksAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加新用户",
			type : 2,
			content : "toAddUser.do",
			success : function(layero, index){
				/*layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
					tips: 3
				});*/
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
 
	//操作
	$("body").on("click",".links_edit",function(){  //编辑
		var _this = $(this);
		var id = _this.attr("data-id");
		var index = layui.layer.open({
			title : "编辑用户信息",
			type : 2,
			shadeClose: false,
			content : "toUpdataUser.do?id="+id,
			area : ['1400px', '600px'],
			success : function(layero, index){
				
			},
			end: function () {
                location.reload();
            }
		})
	})

	$("body").on("click",".links_del",function(){  //删除
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

	function linksList(that){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = linksData.concat().splice(curr*nums-nums, nums);
			}else{
				currData = that.concat().splice(curr*nums-nums, nums);
			}
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					dataHtml += '<tr>'
			    	+'<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			    	+'<td align="left">'+currData[i].linksName+'</td>'
			    	+'<td><a style="color:#1E9FFF;" target="_blank" href="'+currData[i].linksUrl+'">'+currData[i].linksUrl+'</a></td>'
			    	+'<td>'+currData[i].masterEmail+'</td>'
			    	+'<td>'+currData[i].linksTime+'</td>'
			    	+'<td>'+currData[i].showAddress+'</td>'
			    	+'<td>'
					+  '<a class="layui-btn layui-btn-mini links_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
					+  '<a class="layui-btn layui-btn-danger layui-btn-mini links_del" data-id="'+data[i].linksId+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +'</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="7">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		//分页
		var nums = 13; //每页出现的数据量
		if(that){
			linksData = that;
		}
		laypage({
			cont : "page",
			pages : Math.ceil(linksData.length/nums),
			jump : function(obj){
				$(".links_content").html(renderDate(linksData,obj.curr));
				$('.links_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}
})
