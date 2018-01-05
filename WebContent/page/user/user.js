var areaData = address;
var $form;
var form;
var $;
layui.config({
	base : "../../js/"
}).use(['form','layer','upload','laydate'],function(){
	form = layui.form();
	var layer = parent.layer === undefined ? layui.layer : parent.layer;
		$ = layui.jquery;
		$form = $('form');
		laydate = layui.laydate;
        loadProvince();
        layui.upload({
        	url : "../../json/userface.json",
        	success: function(res){
        		var num = parseInt(4*Math.random());  //生成0-4的随机数
        		//随机显示一个头像信息
		    	userFace.src = res.data[num].src;
		    	window.sessionStorage.setItem('userFace',res.data[num].src);
		    }
        });

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

        //判断是否修改过头像，如果修改过则显示修改后的头像，否则显示默认头像
        if(window.sessionStorage.getItem('userFace')){
        	$("#userFace").attr("src",window.sessionStorage.getItem('userFace'));
        }else{
        	$("#userFace").attr("src","../../images/face.jpg");
        }

        //提交个人资料
        form.on("submit(updataUser)",function(data){
        	var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        	var param = {};
        	param.id = $("#id").val();
        	param.name = $("#alias").val();
        	param.grade = $("#grade").val();
        	param.phone = $("#phone").val();
        	param.birthday = $("#birthday").val();
        	param.email = $("#email").val();
        	param.name = $("#name").val();
        	console.log(param);
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
        	param.name = $("#name").val();
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
                layer.msg("密码修改成功！");
                $(".pwd").val('');
            },2000);
        	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        })

})

 //加载省数据
function loadProvince() {
    var proHtml = '';
    for (var i = 0; i < areaData.length; i++) {
        proHtml += '<option value="' + areaData[i].provinceCode + '_' + areaData[i].mallCityList.length + '_' + i + '">' + areaData[i].provinceName + '</option>';
    }
    //初始化省数据
    $form.find('select[name=province]').append(proHtml);
    form.render();
    form.on('select(province)', function(data) {
        $form.find('select[name=area]').html('<option value="">请选择县/区</option>');
        var value = data.value;
        var d = value.split('_');
        var code = d[0];
        var count = d[1];
        var index = d[2];
        if (count > 0) {
            loadCity(areaData[index].mallCityList);
        } else {
            $form.find('select[name=city]').attr("disabled","disabled");
        }
    });
}
 //加载市数据
function loadCity(citys) {
    var cityHtml = '<option value="">请选择市</option>';
    for (var i = 0; i < citys.length; i++) {
        cityHtml += '<option value="' + citys[i].cityCode + '_' + citys[i].mallAreaList.length + '_' + i + '">' + citys[i].cityName + '</option>';
    }
    $form.find('select[name=city]').html(cityHtml).removeAttr("disabled");
    form.render();
    form.on('select(city)', function(data) {
        var value = data.value;
        var d = value.split('_');
        var code = d[0];
        var count = d[1];
        var index = d[2];
        if (count > 0) {
            loadArea(citys[index].mallAreaList);
        } else {
            $form.find('select[name=area]').attr("disabled","disabled");
        }
    });
}
 //加载县/区数据
function loadArea(areas) {
    var areaHtml = '<option value="">请选择县/区</option>';
    for (var i = 0; i < areas.length; i++) {
        areaHtml += '<option value="' + areas[i].areaCode + '">' + areas[i].areaName + '</option>';
    }
    $form.find('select[name=area]').html(areaHtml).removeAttr("disabled");
    form.render();
    form.on('select(area)', function(data) {
        //console.log(data);
    });
}