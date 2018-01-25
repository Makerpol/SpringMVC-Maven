# 
###  项目架构
#### 后台框架：spring+springmvc+mybatis
#### 前端框架：layui+juqery
#### 依赖管理：maven
#### 服务器： jetty
#### 富文本编辑器：UEditor

###  整合UEditor简介
准备：
1. 下载[UEditor](http://ueditor.baidu.com/website/download.html)的JSP版
2. 在WebContent目录下创建UEditor文件夹，将解压后文件夹下的utf8-jsp目录下的文件拷贝到UEditor目录下。（根据自己情况而定）

前端配置：

引用UEditor的文件，注意顺序一定是config先引用。
```js
<script type="text/javascript" src="/UEditor/ueditor.config.js"></script>
<script type="text/javascript" src="/UEditor/ueditor.all.js"></script>
```

创建富文本编辑器的DOM对象
```js
<script id="paper_content" type="text/plain" style="width:1260px;height:500px;"></script>
```

window.UEDITOR_HOME_URL的值是UEditor文件夹的路径
根据id创建富文本编辑器
```js
window.UEDITOR_HOME_URL = "/UEditor/";
var ue = UE.getEditor("paper_content");
```

ueditor.config.js中的配置说明：
```js
(function () {

    /**
     * 编辑器资源文件根路径。它所表示的含义是：以编辑器实例化页面为当前路径，指向编辑器资源文件（即dialog等文件夹）的路径。
     * 鉴于很多同学在使用编辑器的时候出现的种种路径问题，此处强烈建议大家使用"相对于网站根目录的相对路径"进行配置。
     * "相对于网站根目录的相对路径"也就是以斜杠开头的形如"/myProject/ueditor/"这样的路径。
     * 如果站点中有多个不在同一层级的页面需要实例化编辑器，且引用了同一UEditor的时候，此处的URL可能不适用于每个页面的编辑器。
     * 因此，UEditor提供了针对不同页面的编辑器可单独配置的根路径，具体来说，在需要实例化编辑器的页面最顶部写上如下代码即可。当然，需要令此处的URL等于对应的配置。
     * window.UEDITOR_HOME_URL = "/xxxx/xxxx/";  //也可以在这里自己配置路径，或者在使用的时候覆盖。
     */
    var URL = window.UEDITOR_HOME_URL || getUEBasePath();

    /**
     * 配置项主体。注意，此处所有涉及到路径的配置别遗漏URL变量。
     */
    window.UEDITOR_CONFIG = {

        //为编辑器实例添加一个路径，这个不能被注释
        UEDITOR_HOME_URL: URL

        // 服务器统一请求接口路径
        , serverUrl: URL + "jsp/controller.jsp"   //此处可以配置为自己的的Controller接口，用来加载config.json文件

```

config.json配置文件说明：

```js
    "imageActionName": "uploadimage",    //处理图片上传的标志，是请求中参数action的值，后台获取参数action的值判断执行图片上传
    "imageFieldName": "upfile",          //提交的图片表单的名称，如果要自己实现上传功能，注意参数名要一致upfile 
    "imageMaxSize": 2048000, 
    "imageAllowFiles": [".png", ".jpg", ".jpeg", ".gif", ".bmp"], 
    "imageCompressEnable": true, 
    "imageCompressBorder": 1600,
    "imageInsertAlign": "none", 
    "imageUrlPrefix": "http://192.168.211.16:8080", //访问图片的前缀，
    "imagePathFormat": "/upload/{yyyy}{mm}{dd}/{time}{rand:6}", //图片保存地址格式
```


加载config.json文件的入口类说明：
```java
request.setCharacterEncoding( "utf-8" );
response.setHeader("Content-Type" , "text/html");
	
String rootPath = application.getRealPath( "/" ); //config.json的路径
	
out.write( new ActionEnter( request, rootPath ).exec() );
```
通过ActionEnter的构造函数，把config.json的路径传入，让ConfigManager.readFile方法读取config.json文件。


后端代码：

ConfigManager.java

修改前
```java
import org.json.JSONArray;
import org.json.JSONObject;

······

JSONObject jsonConfig = new JSONObject( configContent );

······

String[] result = new String[ jsonArray.length() ];
		
for ( int i = 0, len = jsonArray.length(); i < len; i++ ) {

```


修改后
```java
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

······

JSONObject jsonConfig = JSONObject.fromObject(configContent);

······

String[] result = new String[ jsonArray.size() ];
		
for ( int i = 0, len = jsonArray.size(); i < len; i++ ) {

```
这部分的修改，是由于UEditor依赖的org.json和项目net.json冲突，所以把UEditor中使用org.json代码替换成net.json
<br>

[BinaryUploader.java](https://github.com/Makerpol/SpringMVC-Maven/blob/dev/src/main/java/com/baidu/ueditor/upload/BinaryUploader.java)中上传代码和springmvc上传冲突，参照现在的上传修改原文件。

FileManager.java

修改前
```java
return path.replace( this.rootPath, "/" );
```

修改后
```java
return path.replace( this.rootPath, "" );
```
否则访问上传文件时，路径会多一个**/**


#### 问题

找不到AllContent方法的问题
>Caused by: java.lang.NoSuchMethodError: com.baidu.ueditor.ConfigManager.getAllConfig()Lorg/json/JSONObject;

解决：

ConfigManager.java
```java
public String getAllConfig() {
    return this.jsonConfig.toString();
}
```

ActionEnter.java
```java
switch (actionCode) {
            case 0: {
                return this.configManager.getAllConfig();
            }
```

原因猜测：应该是和spring框架中的方法冲突导致，改变返回值类型，避免调用失败。

#

### 创建本地仓库，链接远程仓库，推送到远程仓库
#### $ git init   							//初始化仓库
#### $ git add README.md 					//添加文件到本地仓库  
#### $ git commit -m "first commit"			//添加文件描述信息
#### $ git remote add origin git@github.com:Makerpol/SpringMVC-Maven.git 	//链接远程仓库，创建主分支
#### $ git pull origin master 				// 把本地仓库的变化连接到远程仓库主分支
#### $ git push -u origin master 			//把本地仓库的文件推送到远程仓库		


#### $ git fetch origin master //从远程的origin仓库的master分支下载代码到本地的origin master

#### $ git log -p master.. origin/master//比较本地的仓库和远程参考的区别

#### $ git merge origin/master//把远程下载下来的代码合并到本地仓库，远程的和本地的合并

###  拉取远程分支到本地分支
#### $ git fetch origin 分支名
#### $ git checkout -b 创建的本地分支名
#### $ git checkout -b 本地分支名 origin/远程分支名
