var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;
 	
	//验证登录名是否存在
 	function checkName(name){
 		var msg = null;
 		$.ajax({
 			url:"checkName.do?name="+name,
 			async: false,
 			success:function(data){
 				if(data.msg=="existed"){
 					msg="登录名已经存在！";
 				}
 			}
 		});	
 		return msg;
 	}
 	
 	function checkIDCard(IDCard){
 		var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
        var tip = "";
        var pass= true;

        if(!IDCard || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(IDCard)){
            tip = "身份证号格式错误";
            pass = false;
        }

       else if(!city[IDCard.substr(0,2)]){
            tip = "地址编码错误";
            pass = false;
        }
        else{
            //18位身份证需要验证最后一位校验位
            if(IDCard.length == 18){
            	IDCard = IDCard.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                //校验位
                var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++)
                {
                    ai = IDCard[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if(parity[sum % 11] != IDCard[17]){
                    tip = "校验位错误";
                    pass =false;
                }
            }
        }
        //if(!pass) alert(tip);
        return pass;
    }
 	
 	
 	//添加验证规则
    form.verify({
    	username : function(value, item){
    		var re = /^[^ ]+$/;
    		if(!re.test(value)){
    			return "用户名中不能包含空格！";
    		}
    		return checkName(value);
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
        date:[/^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/,'时间格式不正确'],
        IDCard: function(value, item){
        	if(!checkIDCard(value)){
        		return "身份证输入错误！";
        	}
        },
        realname: function(value,item){
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
    
 	form.on("submit(addUser)",function(data){
 		
 		var param = {};
 		param.name = $(".userName").val();
 		param.realname = $(".realname").val();
 		param.idcard = $(".IDCard").val();
 		param.phone = $(".userphone").val();
 		param.sex = data.field.sex;
 		param.email = $(".userEmail").val();
 		param.grade = $(".userGrade").val()
 		param.status = $(".userStatus").val()
 		
 		$.ajax({
 			url:"addUser.do",
        	data: JSON.stringify(param),
        	type:"post",
        	dataType:"json",
        	contentType:'application/json',
        	success:function(data){
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
