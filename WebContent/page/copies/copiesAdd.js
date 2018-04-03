
layui.config({
	base:"../../js/"
}).use(['flow','form','layer','upload'],function(){
	var flow = layui.flow,
    form = layui.form(),
    layer = parent.layer === undefined ? layui.layer : parent.layer,
    upload = layui.upload;
    $ = layui.jquery;
    
    form.render("checkbox");
    $(".layui-btn").attr("disabled","true");
   
    layui.upload({
    	elem:"",
    	url:"uploadFile.do",
    	type:"file",
    	success:function(data){
    		window.path = data.URL;
    		$(".path").attr("value",data.URL);
    		//window.icon = data.icon;
    		//$(".PDFimage").attr("src",data.icon);
    		$(".layui-btn").removeAttr("disabled");
    		$(".layui-upload-file").attr("disabled","true");
    		$(".layui-upload-file").removeAttr("name");
    	}
    })
   
    form.verify({
    	 phone: function(value, item){
         	var re = /^1\d{10}$/;
         	if(!re.test(value)){
         		return "手机必须11位，只能是数字！";
         	}
         },
         mail: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '邮箱格式不对'],
         author: function(value,item){
         	var re = /^[^ ]+$/;
     		if(!re.test(value)){
     			return "真实姓名中不能包含空格！";
     		}
     		
         	if(value.length < 2){
         		return "真实姓名不能少于两个字！";
         	}else if(value.length > 20){
         		return "真实姓名不能大于二十个字！";
         	}
         	
         	var hre = /^[\u4e00-\u9fa5]+$/;
         	if(!hre.test(value)){
         		return "请输入汉字！";
         	}
         }
    })
    
   $(".submit_btn").click(function(){
    	if(window.path==null){
    		layer.msg("未选择视频文件，请先选择上传文件！");
    		return;
    	}
    	
    	var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	var param = {};
    	
    	param.papername = $(".fileName").val();
    	param.author = $(".author").val();
    	param.phone = $(".phone").val();
    	param.email = $(".mail").val();
    	param.adds = $(".adds").val();
    	param.type = $(".type").val();
    	param.status = $(".status").val();
    	param.remark = $(".remark").val();
    	//param.icon = window.icon;
    	param.filepath = window.path;
    	
    	$.ajax({'url':"addCopies.do",
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