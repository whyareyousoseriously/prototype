<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> <!-- struts2的标签库 -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>prototype1.3</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="description"
	content="Login and Registration Form with HTML5 and CSS3">
<meta name="keywords"
	content="html5, css3, form, switch, animation, :target, pseudo-class">
<meta name="author" content="Codrops">
<link rel="shortcut icon" href="<%=basePath %>/login_files/favicon.ico">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/login_files/demo.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/login_files/style.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath %>/login_files/animate-custom.css">
<script src="https://static.geetest.com/static/tools/gt.js"></script>
<script src="https://api.geetest.com/get.php"></script>
<script src="<%=path%>/js/changeR.js"></script>
</head>
<body>
	<div class="container">
		<section>
		<div id="container_demo">
			<!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
			<a class="hiddenanchor" id="toregister"></a> <a class="hiddenanchor"
				id="tologin"></a>
			<div id="wrapper" style="margin-top: 100px;">
				<div id="login" class="animate form">
					<form action="<%=path%>/users/Users_login.action" method="post"
						autocomplete="on">
						<h1>XX缴费系统登陆</h1>
						<p>
							<label for="username" class="uname" data-icon="u">邮箱或用户名</label>
							<input id="username" name="username" required="required"
								placeholder="myusername or mymail@mail.com" value="admin"
								type="text">
						</p>
						<p>
							<label for="password" class="youpasswd" data-icon="p">密码</label>
							<input id="password" name="password" required="required"
								placeholder="eg. X8df!90EO" type="password">
						</p>
						<p>
							<img alt="random" src="randomcode.jpg" onclick="changeR(this)" style="cursor: pointer;">
							<input type="text" name="checkCode">
						</p>
						<p class="login button">
							<input value="登陆" type="submit">
						</p>
						<p class="keeplogin">
							<input name="loginkeeping" id="loginkeeping" value="loginkeeping"
								type="checkbox"> <label for="loginkeeping">记住密码</label>
						</p>
						<%-- <div>
							<s:fielderror/><!-- 显示表单验证的出错信息 -->
						</div> --%>
						<p class="change_link">
							还没有账号？<a href="#toregister" class="to_register">注册</a>
						</p>
					</form>
				</div>

				<div id="register" class="animate form">
					<form action="servlet/RegisterServlet" autocomplete="on">
						<h1>Sign up</h1>
						<p>
							<label for="usernamesignup" class="uname" data-icon="u">你的用户名</label> <input id="usernamesignup" name="usernamesignup"
								required="required" placeholder="mysuperusername690" type="text">
						</p>
						<p>
							<label for="emailsignup" class="youmail" data-icon="e">
								你的邮箱</label> <input id="emailsignup" name="emailsignup"
								required="required" placeholder="mysupermail@mail.com"
								type="email">
						</p>
						<p>
							<label for="passwordsignup" class="youpasswd" data-icon="p">你的密码 </label> <input id="passwordsignup" name="passwordsignup"
								required="required" placeholder="eg. X8df!90EO" type="password">
						</p>
						<p>
							<label for="passwordsignup_confirm" class="youpasswd"
								data-icon="p">重复输入你的密码</label> <input
								id="passwordsignup_confirm" name="passwordsignup_confirm"
								required="required" placeholder="eg. X8df!90EO" type="password">
						</p>
						<p class="signin button">
							<input value="注册" type="submit">
						</p>
						<p class="change_link">
							已经有账号了? <a href="#tologin" class="to_register">走起去登录</a>
						</p>
					</form>
				</div>

			</div>
		</div>
		</section>
	</div>
</body>
</html>