var $form;
var form;
var $;
md5;
layui.config({
	base : "../../js/"
}).use(['form','layer','upload','laydate'],function(){
	form = layui.form();
	var layer = parent.layer === undefined ? layui.layer : parent.layer;
		$ = layui.jquery;
		$form = $('form');
		laydate = layui.laydate;

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
            	console.log(oldpwd);
            	var  p=md5(value);
            	console.log(p.toUpperCase());
                if(p.toUpperCase() != oldpwd){
                    return "密码错误，请重新输入！";
                }
            },
            newPwd : function(value, item){
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
        
        layui.upload({
        	elem:"",
        	url:"uploadUserImage.do",
        	success:function(data){
        		console.log(data);
        		$("#userFace").attr("src",data.URL);
        		parent.location.reload(true);
        	}
        });
        

        //提交个人资料
        form.on("submit(updataUser)",function(data){
        	var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        	var param = {};
        	param.id = $("#id").val();
        	param.realname = $("#realname").val();
        	param.grade = $("#grade").val();
        	param.phone = $("#phone").val();
        	param.birthday = $("#birthday").val();
        	param.email = $("#email").val();
        	//
        	$.ajax({'url':"updataUser.do",
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

        

        //修改密码
        form.on("submit(changePwd)",function(data){
        	var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        	var param = {};
        	param.password = $("#firstPwd").val();
        	param.id = $("#id").val();
        	
        	$.ajax({'url':"updataUser.do",
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
            	layer.open({
					 type:1,
					 offset:'auto',
		          	 title: '信息提示',
		          	 content:'<div style="padding: 20px 85px;">密码修改成功，请重新登录！</div>',
		          	 btn:'确定',
		          	 btnAlign: 'c',
		          	 shade: 0,
		          	 yes: function(){
		                layer.closeAll();
		                window.parent.location.href = "/login.jsp";
		             }
		        });
            },2000);
        	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        })

})
