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
			status='<td>已复审</td>';
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