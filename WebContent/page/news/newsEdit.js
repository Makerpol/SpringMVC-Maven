var UE = window.UE;
layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		upload = layui.upload;
		$ = layui.jquery;
		
	window.UEDITOR_HOME_URL = "/UEditor/";
	var ue = UE.getEditor("paper_content");
		
 	function renderCheckbox(paper){
 		if(paper.status==1){
			$(".shenhe").removeAttr("checked");
		}
 		
 		if(paper.show==1){
			$(".show").removeAttr("checked");
		}
 	}
 	
 	form.on("submit(addNews)",function(data){
 		var index = layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
 		
 		var param = {};
 		param.id = $("#id").val();
 		param.paperName = $(".newsName").val();
 		param.power = $(".browseLook").val();
 		param.date = $(".newsTime").val();
 		param.author = $(".newsAuthor").val();
 		param.type = $(".type").val();
 		param.show = data.field.show=="on" ? 0 : 1;
 		param.status = data.field.shenhe=="on" ? 0 : 1;
 		param.text = ue.getContent();
 		
 		$.ajax({
			url : "upDataPaper.do",
			type : "post",
			dataType : "json",
			contentType:'application/json',
			data:JSON.stringify(param),
			success : function(data){
				if("error"==data.message){
					setTimeout(function(){
						layer.close(index);
						layer.msg("提交失败！");
					},2000);
				}
			}
 		});
 		
        setTimeout(function(){
            layer.close(index);
			layer.msg("文章编辑成功！");
        },2000);
        
        var FrameIndex = layer.getFrameIndex(window.name);
        layer.close(FrameIndex); //再执行关闭 
 		return false;
 	})
	
})
