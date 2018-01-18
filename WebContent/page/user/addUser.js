var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;

 	var addUserArray = [],addUser;
 	
 	//添加验证规则
    form.verify({
    	username : function(value, item){
    		var re = /^[^ ]+$/;
    		if(!re.test(value)){
    			return "用户名中不能包含空格！";
    		}
    	},
        oldPwd : function(value, item){
        	var oldpwd = $("#password").val();
        	
            if(value != oldpwd){
                return "密码错误，请重新输入！";
            }
        },
        password : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        confirmPwd : function(value, item){
            if(!new RegExp($("#firstPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        },
        phone: function(value, item){
        	var re = /^1\d{10}$/;
        	if(!re.test(value)){
        		return "手机必须11位，只能是数字！";
        	}
        },
        email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '邮箱格式不对'],
        date:[/^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/,'时间格式不正确']
    })
    
 	form.on("submit(addUser)",function(data){
 		
 		var param = {};
 		param.name = $(".userName").val();
 		param.password = $(".userpassword").val();
 		param.phone = $(".userphone").val();
 		param.sex = $(".userSex").val();
 		param.email = $(".userEmail").val();
 		param.grade = $(".userGrade").val()
 		param.status = $(".userStatus").val()
 		
 		$.ajax({'url':"addUser.do",
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
 		
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            top.layer.close(index);
			top.layer.msg("用户添加成功！");
 			layer.closeAll("iframe");
	 		//刷新父页面
	 		parent.location.reload();
        },2000);
 		return false;
 	})
	
})

//格式化时间
function formatTime(_time){
    var year = _time.getFullYear();
    var month = _time.getMonth()+1<10 ? "0"+(_time.getMonth()+1) : _time.getMonth()+1;
    var day = _time.getDate()<10 ? "0"+_time.getDate() : _time.getDate();
    var hour = _time.getHours()<10 ? "0"+_time.getHours() : _time.getHours();
    var minute = _time.getMinutes()<10 ? "0"+_time.getMinutes() : _time.getMinutes();
    return year+"-"+month+"-"+day+" "+hour+":"+minute;
}
