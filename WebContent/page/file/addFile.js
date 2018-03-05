layui.config({
	base:"../../js/"
}).use(['flow','form','layer','upload'],function(){
	var flow = layui.flow,
    form = layui.form(),
    layer = parent.layer === undefined ? layui.layer : parent.layer,
    upload = layui.upload;
    $ = layui.jquery;
    
   
    layui.upload({
    	elem:"",
    	url:"uploadFile.do",
    	type:"file",
    	ext:"pdf|PDF",
    	success:function(data){
    		console.log(data.URL);
    		console.log(data.icon);
    		window.path = data.URL;
    		window.icon = data.icon;
    		$(".PDFimage").attr("src",data.icon);
    	}
    })
    
    form.on("submit(addPDF)",function(){
    	var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	var param = {};
    	
    	param.filename = $(".fileName").val();
    	param.type = $(".type").val();
    	param.summary = $(".summary").val();
    	param.icon = window.icon;
    	param.path = window.path;
    	
    	$.ajax({'url':"addFile.do",
			'data': JSON.stringify(param),
			'type':"POST",
			'dataType':"json",
			'contentType':'application/json',
			'success':function(data){
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
            layer.msg("提交成功！");
        },2000);
        
        var index = layer.getFrameIndex(window.name);
        layer.close(index); //再执行关闭 
    	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
    
	
})