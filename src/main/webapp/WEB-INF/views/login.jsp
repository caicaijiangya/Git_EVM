<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>EVM后台管理系统</title>
    <meta name="keywords" content="品萃,链妆,蓝豚,美顾激励,相宜本草,相宜荟,小程序，专注于电商小程序研发">
    <meta name="description" content="Java语言技术只有之一，是最好的语言">
    <meta name="viewport" content="width=device-width">
    <%@ include file="/commons/basejs.jsp" %>
    <link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/login.css?nocache=<%=new Date().getTime() %>" />
    <script type="text/javascript" src="${staticPath }/static/js/login.js?nocache=<%=new Date().getTime() %>" charset="utf-8"></script>
</head>

<body onkeydown="enterlogin();">
<form id="loginform" name="loginform" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="container-fluid login"> &nbsp;
   		<div class="login_logo"><img src="${staticPath }/static/style/images/login_logo.png?t=1.0.4"></div>
    	<div class="clearfix login_box">
        	<div class="login_timg pull-left fl"><img src="${staticPath }/static/style/images/login_timg.jpg" style="width: 300px;height: 200px;"></div>
            <div class="login_form pull-right fr">
            	
                <div class="login_title">
                	<span>用户登录 USER LOGIN</span>
                </div>
                <div class="form-horizontal">
                	<div class="form-group">
                    	<span class="col-md-3 col-lg-3 col-xs-3 col-sm-3 control-label">用户名：</span>
                        <div class="col-md-9 col-lg-9 col-xs-9 col-sm-9">
                        	<input type="text" class="form-control" name="username" id="username" placeholder="请输入账号" />
                        </div>
                    </div>
                	<div class="form-group">
                    	<span class="col-md-3 col-lg-3 col-xs-3 col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
                        <div class="col-md-9 col-lg-9 col-xs-9 col-sm-9">
                        	<input type="password" class="form-control" name="password"	id="password" placeholder="请输入密码">
                        </div>
                    </div> 
                    <div class="form-group">
                    	<span class="col-md-3 col-lg-3 col-xs-3 col-sm-3 control-label">验证码：</span>
        					<P style="position: relative;margin-top:12px;">
            					<input class="captcha" type="text" name="captcha" placeholder="请输入验证码" style="width:100px;overflow:hidden;margin-right:10px;"/>
            					<img id="captcha" alt="验证码" src="${path }/captcha.jpg" data-src="${path }/captcha.jpg?t=" style="vertical-align:middle;border-radius:4px;width:94.5px;height:35px;cursor:pointer;float: left;">
        					</P>
                  	</div>
                    <div class="form-group">
                      <input class="rememberMe" type="checkbox" name="rememberMe" value="1" checked style="vertical-align:middle;margin-left:40px;height:20px;"/> 记住用户名和密码
					</div>
                  <input type="button" class="btn btn-block btn-primary" id="loginBtn"  value="立即登录" onclick="submitForm()">  
              </div>
                
            </div>
        </div>
        <div class="container login_copy">@ ©2018 版权所有 上海品萃信息科技有限公司</div>
   </div>
</form>

</body>
</html>
