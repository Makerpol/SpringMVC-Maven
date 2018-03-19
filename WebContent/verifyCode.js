layui.config({
	base:"js/"
}).use(["form","layer","jquery"],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	
	
	function checkEmail(value){
		var re = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/;
		return re.test(value);
	}
	
	var verifyCode = null;
	$("#verifyCodeBtn").click(function(){
		var mail = $("#userMail").val();
		console.log(mail);
		if(checkEmail(mail)){
			$.ajax({
				url:"sendVerifyCode.do?userMail="+mail,
				success:function(data){
					if(data.msg=="error"){
						layer.msg("输入的邮箱未使用！");
						return;
					}
					verifyCode = data.verifyCode
				}
			})
		}else{
			layer.msg("邮箱格式错误！");
		}
	})
	
	$("#chengePWD").click(function(){
		var code = $("#verifyCode").val();
		if(verifyCode != code){
			layer.msg("验证码输入错误！");
			return;
		}
		window.location.href="toReSetPwd.do";
	})
	
	
	
})