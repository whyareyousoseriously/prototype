<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>VerificationCode test</title>
<script type="text/javascript">
		function reloadCode(){
			var time = new Date().getTime();
			document.getElementById("imagecode").src="<%=request.getContextPath() %>/servlet/ImageServlet?d="+time;
		}
	</script>
</head>
<body>
	<form action="<%=request.getContextPath() %>/servlet/LoginServlet" method="get">
		<div>
			<label>验证码:</label>
		</div>
		<div>
			<input type="text" name="checkCode" /> <img alt="验证码" id="imaecode"
				src="<%=request.getContextPath()%>/servlet/ImageServlet" /> <a
				href="javascript: reloadCode();">看不清楚</a><br> <input
				type="submit" value="提交">
		</div>
	</form>
</body>
</html>