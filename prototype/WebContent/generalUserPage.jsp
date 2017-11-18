<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- this jsp can be use in the 2017-11-14 think 2 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>generalUserPage</title>
</head>
<body>
<h1>this is a genneral User Page to test the limit of authority</h1>
<div id="user_info" style="margin-left: 0px;margin-top: 0px;">
  <div id="welcome">欢迎${sessionScope.loginUserName},使用本系统</div>
  <div id="logout"><a href="<%=path %>/users/Users_logout.action">安全退出</a></div>
</div></div>
</body>
</html>