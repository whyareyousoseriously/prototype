<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%><!-- 启用EL语言 -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<script src="<%=path%> /js/showAndHidden.js"></script>
<title>prototype1.3</title>
</head>
<body>
	<div>
		<h1 style="">缴费列表</h1>
		<div id="user_info" style="margin-left: 0px; margin-top: 0px;">
			<div id="welcome">欢迎${sessionScope.loginUserName},使用本系统</div>
			<div id="logout">
				<a href="<%=path%>/users/Users_logout.action">安全退出</a>
			</div>
		</div>
	</div>
	<hr>
	<div>
		<div>
			<!-- test the limit of authority 测试权限显示 的div 等界面正式设计出来可以替换-->
			<%
				if (session.getAttribute("loginPower").equals("0")) {
					out.println("<script>showDiv('authority0,authority1,authority2','1')</script>");
				}
				if (session.getAttribute("loginPower") == "1") {
					out.println("<script>showDiv('authority0,authority1,authority2','2')</script>");
				}
				if (session.getAttribute("loginPower") == "2") {
					out.println("<script>showDiv('authority0,authority1,authority2','3')</script>");
				}
			%>
			<div id="authority0">
				<label>权限0</label>
			</div>
			<div id="authority1">
				<label>权限1</label>
			</div>
			<div id="authority2">
				<label>权限2</label>
			</div>
		</div>
		<div>
			<label>选择你要缴费的项目</label>
		</div>
		<form action="" method="post">
			<label>缴费项目1：</label><input checked="checked" value="Cost1"
				name="Cost" type="checkbox"> <br> <label>缴费项目2：</label><input
				value="Cost2" name="Cost" type="checkbox"> <br> <label>缴费项目3：</label><input
				value="Cost3" name="Cost" type="checkbox"> <br> <br>
			<input value="支付结算" type="submit">
		</form>
	</div>
</body>
</html>