/**
 * 
 */
var LoginUser = $.parseJSON(user);
layui.use(["form","jquery","layer","laypage"],function(){
	var form = layui.form(),
		$ = layui.jquery,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
	
	
	var total = 0;
	var num = 13;
	var start = 0;
	var currPage = 1;
	
	ajaxGetCopiesList(start,num);
	function ajaxGetCopiesList(start,num){
		var param = $(".search_input").val();
		$.ajax({
			url: "getCopiesList.do?param="+param+"&start="+start+"&num="+num,
			type:"get",
			success:function(data){
				total = data.total;
				renderDate(data.copiesList);
				toPage();
			}
		});
	}
	
	function getOldStatus(s){
		var OldStatus = 0;
		switch(s){
		case "未审核":
			OldStatus = 0;
			break;
		case "已初审":
			OldStatus = 1;
			break;
		case "已复审":
			OldStatus = 2;
			break;
		case "已终审":
			OldStatus = 3;
			break;
		}
		return OldStatus;
	}
	
	$(".audit_btn").click(function(){	//审核
		var grade = LoginUser.grade;
		var $checkbox = $('.copies_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.copies_list tbody input[type="checkbox"][name="checked"]:checked');
		
		if($checkbox.is(":checked")){
			var index = layer.msg('审核中，请稍候',{icon: 16,time:false,shade:0.8});
			
			setTimeout(function(){
            	for(var j=0;j<$checked.length;j++){
        			var id = $checked.eq(j).parents("tr").find(".news_del").attr("data-id");
        			var oldStatus = getOldStatus($checked.eq(j).parents("tr").find("td:eq(5)").text());
        			var msg = auditAjax(id,oldStatus);
        			console.log(msg);
        			
        			if(msg!="error"){
        				var text = "";
        				switch(oldStatus){
        				case 0:
        					text = "未审核";
        					var color = LoginUser.grade==2?"color:#f00":'';
        					$checked.eq(j).parents("tr").find("td:eq(6)").text(text).attr("style",color);
        					break;
        				case 1:
        					text = "已初核";
        					var color = LoginUser.grade==1?"color:#f00":'';
        					$checked.eq(j).parents("tr").find("td:eq(6)").text(text).attr("style",color);
        					break;
        				case 2:
        					text = "已复审";
        					var color = LoginUser.grade==0?"color:#f00":'';
        					$checked.eq(j).parents("tr").find("td:eq(6)").text(text).attr("style",color);
        					break;
        				case 3:
        					text = "已终审";
        					$checked.eq(j).parents("tr").find("td:eq(6)").text(text).removeAttr("style");
        					break;
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
		var msg = null;
		var param = {};
		param.id = id;
		param.status = oldStatus==0?1:0;
		
		$.ajax({
			url : "updateCopies.do",
			type : "post",
			dataType : "json",
			async: false,
			'contentType':'application/json',
			data : JSON.stringify(param),
			success : function(data){
				msg = data.message;
			}
		})
		return msg;
	}
	
	$(".newsAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加新稿件",
			type : 2,
			content : "toCopiesAddPage.do",
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
	
	function getTypeText(v){
		var type = null;
		switch(v){
		case 0:
			type="科技创新";
			break;
		case 1:
			type="河南科技";
			break;
		case 2:
			type="乡村科技";
			break;
		case 3:
			type="研究报告";
			break;
		case 4:
			type="创新方法";
			break;	
		}
		return type;
	}
	
	function getStatusText(v){
		var status = null;
		switch(v){
		case 0:
			status=LoginUser.grade==2?'<td style:"color:#f00">未审核</td>':'<td>未审核</td>';
			break;
		case 1:
			status=LoginUser.grade==1?'<td style:"color:#f00">已初审</td>':'<td>已初审</td>';
			break;
		case 2:
			status=LoginUser.grade==0?'<td style:"color:#f00">已复审</td>':'<td>已复审</td>';
			break;
		case 3:
			status='<td>已终审</td>';
			break;
		}
		return status;
	}
	
	function renderDate(data){
		var dataHtml = '';
		if(data!=null && data.length>0){
			for(var i=0;i<data.length;i++){
				dataHtml = '<tr>';
				dataHtml += '<td><input type="checkbox" name="checked" value="'+data[i].id+'" lay-skin="primary" lay-filter="choose">';
				dataHtml += '<td>'+data[i].papername+"</td>";
				dataHtml += '<td>'+data[i].author+'</td>';
				dataHtml += '<td>'+getTypeText(data[i].type)+'</td>';
				dataHtml += '<td>'+data[i].data+'</td>';
				dataHtml += getStatusText(data[i].status);
				dataHtml += '<td>';
				dataHtml += '<a class="layui-btn layui-btn-mini news_edit" data-id="'+data[i].id+'"><i class="iconfont icon-edit"></i> 编辑</a>';
				dataHtml += '<a class="layui-btn layui-btn-danger layui-btn-mini news_del" data-id="'+data[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>';
				dataHtml += '<a class="layui-btn layui-btn-mini news_text" data-id="'+data[i].id+'" href="'+data[i].filepath+'"><i class="iconfont icon-text"></i> 下载</a>'
				dataHtml += '</td>';
				dataHtml += '</tr>';
			}
		}else{
			dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
		}
		$(".copies_content").html(dataHtml);
		$('.copies_list thead input[type="checkbox"]').prop("checked",false);
		form.render();
	}
	
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
					ajaxGetCopiesList(start,num);
				}
			}
		})
	}
	
})