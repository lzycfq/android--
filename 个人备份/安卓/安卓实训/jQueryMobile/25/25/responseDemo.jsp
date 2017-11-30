<%@ page language="java" import="java.net.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>jQuery Mobile</title>
		<link rel="stylesheet" href="css/jquery.mobile-1.4.5.min.css">
		<!--<link rel="stylesheet" href="css/theme-classic.css" />-->
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery.mobile-1.4.5.min.js"></script>
    </head>
    <body>
 	<div data-role="page" id="home">
			<div data-role="header">
				<h1>欢迎页</h1>
			</div>
			<div data-role="content">
				欢迎你：${param.fname}·${param.lname}
 	
 	</div>

			<div data-role="footer" class="ui-footer-fixed">
				<h1>CopyRight</h1>
			</div>
		</div>
 	</body>
</html>