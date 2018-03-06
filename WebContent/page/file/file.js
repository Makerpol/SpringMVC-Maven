layui.config({
	base : "../../js/"
}).use(['form','layer','laypage'],function(){
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    
	//总数
	var total = 0;
	//开始位置
	var start = 0;
	//每页显示数
	var num = 20;
	//当前页
	var currPage = 1;
    
	getFileList(start, num);
	
    function getFileList(start,num){
    	var param = $(".search_input").val();
    	
    	$.ajax({
    		url:"getFileList.do?param="+param+"&start="+start+"&num="+num,
    		type:"get",
    		success:function(data){
    			console.log(data);
    			total = data.total;
    			renderDate(data.fileList);
    			toPage();
    		}
    	})
    }
    
    //预览
	$(document).on("click",".pdflistimg",function(){
		   var pdfpath = getPDFPath($(this));
		   console.log(pdfpath);
		   var index = layui.layer.open({
			   title:"预览",
			   type:2,
			   area:['1200px','600px'],
			   content:"PDFJS/web/viewer.html?file="+pdfpath
		   })
	  })
   
    
   function getPDFPath(dom){
	   var path = dom[0].attributes[1].value;
	   return path.replace(".jpg",".pdf");
   }
   
   //添加
    $(".file-addBtn").click(function(){
    	var index = layui.layer.open({
    		title : "添加文件",
			type : 2,
			content : "toAddPDFPage.do",
			success : function(layero, index){},
			end: function () {
                location.reload();
            }
    	})
    	//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
    })
    
    //渲染列表
    function renderDate(fileList){
    	var html = "";
    	
    	if(fileList.length>0){
    		for(var i = 0;i<fileList.length;i++){
        		html += "<li>";
        		html += '<img class="pdflistimg" src="'+fileList[i].icon+'">';
        		html += '<div class="operate">';
        		html += '<p class="check">'+fileList[i].filename+'</p>';
        		html += '<i class="layui-icon pdfdel" data-id="'+fileList[i].id+'"></i>';
        		html += '</div></li>';
        	}
    	}
    	
    	$("#Images").html(html);
    	form.render();
    }
        
    //分页
    function toPage(){
		laypage({
			cont : "PDFpage",
			pages : total,
			curr : currPage,
			skip: false,
			jump : function(obj,first){
				currPage = obj.curr;
				start = (obj.curr-1)*num;
				
				if(!first){
					getFileList(start,num);
				}
			}
		})
	}
    
    //删除
    $("body").on("click",".pdfdel",function(){
        var _this = $(this);
        var id = _this.attr("data-id");
        layer.confirm('确定删除此文件？',{icon:3, title:'提示信息'},function(index){
        	$.ajax({
        		'url':"deleteFile.do",
				'data': {'id':id},
				'success':function(data){
					if("error"==data.message){
						setTimeout(function(){
							layer.close(index);
							layer.msg("提交失败！");
						},2000);
					}
					_this.parents("li").remove();
				}
			});
            layer.close(index);
        });
    })

})