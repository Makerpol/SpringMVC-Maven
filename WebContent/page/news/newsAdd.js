Date.prototype.format = function(format)
{
 var o = {
 "M+" : this.getMonth()+1, //month
 "d+" : this.getDate(),    //day
 "h+" : this.getHours(),   //hour
 "m+" : this.getMinutes(), //minute
 "s+" : this.getSeconds(), //second
 "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
 "S" : this.getMilliseconds() //millisecond
 }
 if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
 (this.getFullYear()+"").substr(4 - RegExp.$1.length));
 for(var k in o)if(new RegExp("("+ k +")").test(format))
 format = format.replace(RegExp.$1,
 RegExp.$1.length==1 ? o[k] :
 ("00"+ o[k]).substr((""+ o[k]).length));
 return format;
}

var LoginUser = $.parseJSON(user);
layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		$ = layui.jquery;
	
	urlArray = null;
	
	$(".newsTime").attr("value",new Date().format("yyyy-MM-dd hh:mm:ss"));
	
	window.UEDITOR_HOME_URL = "/UEditor/";
	var ue = UE.getEditor("paper_content");
	ue.addListener('afterUpVideo',function(t, arg) {
		for(var i=0;i<arg.length;i++){
			window.path = arg[i].url;
			window.icon = arg[i].icon;
		}
	})
	
	renderSelect();
	
	function renderSelect(){
		var colu = LoginUser.colu;
		console.log(LoginUser);
		colu = colu.replace(new RegExp(",","g"),"");
		for(var i=0;i<colu.length;i++){
			checkboxSet(colu.charAt(i));
		}
		form.render("select");
		$("#paperType dl").css("height","130px");
	}
	
	function checkboxSet(value){
		
		var options = $(".type option");
		var v = value;
		switch (v){
		case '0':
			console.log(v);
			$(options[0]).removeAttr('disabled');
			break;
		case '1':
			$(options[1]).removeAttr('disabled');
			break;
		case '2':
			$(options[2]).removeAttr('disabled');
			break;
		case '3':
			$(options[3]).removeAttr('disabled');
			break;
		case '4':
			$(options[4]).removeAttr('disabled');
			break;
		case '5':
			$(options[5]).removeAttr('disabled');
			break;
		case '6':
			$(options[6]).removeAttr('disabled');
			break;
		case '7':
			$(options[7]).removeAttr('disabled');
			break;
		}
	}
	
	function checkContent(str,is_global){
		var result;
		console.log(result);
		result = str.replace(/(^\s+)|(\s+$)/g,"");
		if(is_global.toLowerCase()=="g")
		{
			result = result.replace(/\s/g,"");
		}
		console.log(result);
	   return result;
	}
	
	//提交文章
 	form.on("submit(addNews)",function(data){
 		
 		if(checkContent(ue.getContent(),"g").length==0){
 			layer.open({ 
 				title:'错误提示',
				content: '文章内容不能为空！'
			});
 			return false;
 		}
 		
 		var index = layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
 		var param = {};
 		param.paperName = $(".newsName").val();
 		param.power = $(".browseLook").val();
 		param.date = $(".newsTime").val();
 		param.author = $(".newsAuthor").val();
 		param.type = $(".type").val();
 		param.text = ue.getContent();
 		param.show = data.field.show=="on" ? 1 : 0;
 		param.status = data.field.shenhe=="on" ? 0 : 1;
 		param.top = data.field.top=="on"?1:0;
 		
 		param.images = getFirstImg(param.text);
 		window.path = window.path==null?getVideoPath(param.text):window.path;
 		$.ajax({
			url : "addPaper.do",
			type : "post",
			dataType : "json",
			contentType:'application/json',
			data:JSON.stringify(param),
			success : function(data){
				
				if("success" == data.message){
					if(window.path!=null){
						addVideoContent(param.paperName,data.paperID,window.path,window.icon);
					}
				}else{
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
        },2000);
        
        var FrameIndex = layer.getFrameIndex(window.name);
        layer.close(FrameIndex); //再执行关闭 
 		return false;
 	})
 	
 	//同步更新视频信息
 	function addVideoContent(videoname,paperID, path,icon){
		var param = {};
		param.videoname = videoname;
		param.path = path;
		param.icon = icon;
		param.paperid = paperID;
		$.ajax({
			url:"addVideo.do",
			data:JSON.stringify(param),
			type:'post',
			dataType : "json",
			contentType:'application/json',
			success:function(data){
			}
		})
	}
	
	function getFirstImg(content){
		var imgReg = /<img.*?(?:>|\/>)/gi;
		var srcReg = /src=[\'\"]?([^\'\"]*)[\'\"]?/i;
		
		if(imgReg.test(content)){
			var arr = content.match(imgReg);
			var imgPath = arr[0].match(srcReg);
			var path = imgPath[0].replace(/src=/i, "");
			path = path.replace("\"","");
			return path;
		}
	}
	
	function getVideoPath(content){
 		var imgReg = /<source.*?(?:>|\/>)/gi;
		var srcReg = /src=[\'\"]?([^\'\"]*)[\'\"]?/i;
		
		if(imgReg.test(content)){
			var arr = content.match(imgReg);
			var imgPath = arr[0].match(srcReg);
			var path = imgPath[0].replace(/src=/i, "");
			path = path.replace("\"","");
			return path;
		}
 	}
})
