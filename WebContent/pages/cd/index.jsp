<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>阳光成单系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/weblib/layui/css/layui.css" media="all">
<link rel="stylesheet" href="/css/x-admin.css" media="all">
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header header header-demo">
    <div class="layui-main">
    <div class="admin-logo-box">
				<a class="logo"  title="logo">阳光成单系统</a>
				<div class="larry-side-menu">
					<i class="fa fa-th-large" aria-hidden="true"></i>
				</div>
			</div>
            <ul class="layui-nav layui-layout-left layui-ygyd-menu" style="position:absolute; left:250px;">
      <li class="layui-nav-item"><a href="">合作机构</a></li>
      <li class="layui-nav-item"><a href="javascript:;">订单管理</a></li>
      <li class="layui-nav-item"><a href="">统计报表</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">系统管理</a>
        <dl class="layui-nav-child">
          <dd><a href="">角色管理</a></dd>
          <dd><a href="">权限设置</a></dd>
          <dd><a href="">日志管理</a></dd>
        </dl>
      </li>
    </ul>
    
      <ul class="layui-nav" lay-filter="">
      <!-- <li class="layui-nav-item">
                        <a href="" title="消息">
                            <i class="layui-icon" style="top: 1px;">&#xe63a;</i>
                        </a>
                        </li> -->
        <li class="layui-nav-item"><img src="images/logo.png" class="layui-circle" style="border: 2px solid #A9B7B7;" width="35px" alt=""></li>
        <li class="layui-nav-item"> <a href="javascript:;">${user.name }</a>
          <dl class="layui-nav-child">
            <!-- 二级菜单 -->
            <dd><a href="javascript:;" _href="admin-edit.jsp"><i class="iconfont icon-zhanghu" data-icon="icon-zhanghu"></i><cite>个人资料</cite></a></dd>
			<dd><a href="javascript:;" data-url="<%=path%>/page/user/changePwd.html"><i class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i><cite>修改密码</cite></a></dd>
			<dd><a href="javascript:;"><i class="iconfont icon-loginout"></i><cite>退出</cite></a></dd>
          </dl>
        </li>
        <li class="layui-nav-item x-index"><a href="/">前台首页</a></li>
      </ul>
    </div>
  </div>
  <div class="layui-side layui-bg-black x-side" style="left:-200px;">
    <div class="layui-side-scroll">
      <ul class="layui-nav layui-nav-tree site-demo-nav" lay-filter="side">
        <li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe607;</i><cite>机构管理</cite> </a>
          <dl class="layui-nav-child">
            <dd class="">
            <dd class=""> <a href="javascript:;" _href="./question-list.html"> <cite>机构列表</cite> </a> </dd>
            </dd>
          </dl>
        </li>
        <li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe62d;</i><cite>产品管理</cite> </a>
          <dl class="layui-nav-child">
            <dd class="">
            <dd class=""> <a href="javascript:;" _href="./welcome.html"> <cite>产品列表（待开发）</cite> </a> </dd>
            </dd>
            <dd class="">
            <dd class=""> <a href="javascript:;" _href="./welcome.html"> <cite>品牌管理（待开发）</cite> </a> </dd>
            </dd>
            <dd class="">
            <dd class=""> <a href="javascript:;" _href="./welcome.html"> <cite>类型管理（待开发）</cite> </a> </dd>
            </dd>
            <dd class="">
            <dd class=""> <a href="javascript:;" _href="./welcome.html"> <cite>类型属性（待开发）</cite> </a> </dd>
            </dd>
            <dd class="">
            <dd class=""> <a href="javascript:;" _href="./category.html"> <cite>产品分类</cite> </a> </dd>
            </dd>
          </dl>
        </li>
        <li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe634;</i><cite>轮播管理</cite> </a>
          <dl class="layui-nav-child">
            <dd class="">
            <dd class=""> <a href="javascript:;" _href="./banner-list.html"> <cite>轮播列表</cite> </a> </dd>
            </dd>
          </dl>
        </li>
        <li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe642;</i><cite>订单管理</cite> </a>
          <dl class="layui-nav-child">
            <dd class="">
            <dd class=""> <a href="javascript:;" _href="./welcome.html"> <cite>订单列表（待开发）</cite> </a> </dd>
            </dd>
          </dl>
        </li>
        <li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe630;</i><cite>分类管理</cite> </a>
          <dl class="layui-nav-child">
            <dd class=""> <a href="javascript:;" _href="./category.html"> <cite>分类列表</cite> </a> </dd>
          </dl>
        </li>
        <li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe606;</i><cite>评论管理</cite> </a>
          <dl class="layui-nav-child">
            <dd class=""> <a href="javascript:;" _href="./comment-list.html"> <cite>评论列表</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./feedback-list.html"> <cite>意见反馈</cite> </a> </dd>
          </dl>
        </li>
        <li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe612;</i><cite>会员管理</cite> </a>
          <dl class="layui-nav-child">
            <dd class=""> <a href="javascript:;" _href="member-list.html"> <cite>会员列表</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./member-del.html"> <cite>删除会员</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./member-level.html"> <cite>等级管理</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./member-kiss.html"> <cite>积分管理</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./member-view.html"> <cite>浏览记录</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./member-view.html"> <cite>分享记录</cite> </a> </dd>
          </dl>
        </li>
        <li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe613;</i><cite>管理员管理</cite> </a>
          <dl class="layui-nav-child">
            <dd class=""> <a href="javascript:;" _href="./admin-list.html"> <cite>管理员列表</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./admin-role.html"> <cite>角色管理</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./admin-cate.html"> <cite>权限分类</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./admin-rule.html"> <cite>权限管理</cite> </a> </dd>
          </dl>
        </li>
        <li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe629;</i><cite>系统统计</cite> </a>
          <dl class="layui-nav-child">
            <dd class=""> <a href="javascript:;" _href="./echart.html"> <cite>统计报表</cite> </a> </dd>
          </dl>
        </li>
        <li class="layui-nav-item"> <a class="javascript:;" href="javascript:;"> <i class="layui-icon" style="top: 3px;">&#xe614;</i><cite>系统设置</cite> </a>
          <dl class="layui-nav-child">
            <dd class=""> <a href="javascript:;" _href="./sys-set.html"> <cite>系统设置</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./sys-data.html"> <cite>数字字典</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./sys-shield.html"> <cite>屏蔽词</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./sys-log.html"> <cite>系统日志</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./sys-link.html"> <cite>友情链接</cite> </a> </dd>
            <dd class=""> <a href="javascript:;" _href="./sys-qq.html"> <cite>第三方登录</cite> </a> </dd>
          </dl>
        </li>
        <li class="layui-nav-item" style="height: 30px; text-align: center"> </li>
      </ul>
    </div>
  </div>
  <div class="layui-tab layui-tab-card site-demo-title x-main" lay-filter="x-tab" lay-allowclose="true" style="left: 0px;border-left: solid 2px #2299ee;">
    <ul class="layui-tab-title">
      <li class="layui-this"> 我的桌面 <i class="layui-icon layui-unselect layui-tab-close">ဆ</i> </li>
    </ul>
    <div class="layui-tab-content site-demo site-demo-body">
      <div class="layui-tab-item layui-show">
        <iframe frameborder="0" src="/pages/welcome.jsp" class="x-iframe"></iframe>
      </div>
    </div>
  </div>
  <div class="site-mobile-shade"> </div>
</div>
<script src="/weblib/layui/layui.js" charset="utf-8"></script> 
<script src="/js/x-admin.js"></script> 
<script>
layui.use('element', function () {
    var $ = layui.jquery
    , element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
    //触发事件
    var active = {
        tabAdd: function () {
            //新增一个Tab项
            element.tabAdd('demo', {
                title: '新选项<i class="layui-icon layui-unselect layui-tab-close">&#x1006;</i>' //用于演示
              , content: '内容' + (Math.random() * 1000 | 0)
              , id: new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
            });
            //增加点击关闭事件  
            var r = $(".layui-tab-title").find("li");
            //每次新打开tab都是最后一个，所以只对最后一个tab添加点击关闭事件  
            r.eq(r.length - 1).children("i").on("click", function () {
                alert($(this).parent("li").attr('lay-id'));
                element.tabDelete("demo", $(this).parent("li").attr('lay-id'));
            }), element.tabChange("demo", r.length - 1);
            element.init();
        }
      , tabDelete: function (othis) {
          //删除指定Tab项
          element.tabDelete('demo', '44'); //删除：“商品管理”

          othis.addClass('layui-btn-disabled');
      }
      , tabChange: function () {
          //切换到指定Tab项
          element.tabChange('demo', '22'); //切换到：用户管理
      }
    };
    $('.site-demo-active').on('click', function () {
        var othis = $(this), type = othis.data('type');
        active[type] ? active[type].call(this, othis) : '';
    });
    //Hash地址的定位
    var layid = location.hash.replace(/^#test=/, '');
    element.tabChange('test', layid);
    element.on('tab(test)', function (elem) {
        location.hash = 'test=' + $(this).attr('lay-id');
    });
});
</script>

</body>
</html>