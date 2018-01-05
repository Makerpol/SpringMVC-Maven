layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;

	//创建一个编辑器
 	var editIndex = layedit.build('news_content');
 	var addNewsArray = [],addNews;
 	form.on("submit(addNews)",function(data){
 		var index = layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
 		var param = {};
 		param.paperName = $(".newsName").val();
 		param.power = $(".browseLook").val();
 		param.date = $(".newsTime").val();
 		param.author = $(".newsAuthor").val();
 		param.show = data.field.show=="on" ? 1 : 0;
 		param.status = data.field.shenhe=="on" ? 0 : 1;
 		param.text = layedit.getText(editIndex);
 		
 		
 		$.ajax({
			url : "addPaper.do",
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
			layer.msg("文章添加成功！");
 			//layer.closeAll("iframe");
	 		//刷新父页面
	 		//parent.location.reload();
        },2000);
        
        var FrameIndex = layer.getFrameIndex(window.name);
        layer.close(FrameIndex); //再执行关闭 
 		return false;
 	})
	
})
