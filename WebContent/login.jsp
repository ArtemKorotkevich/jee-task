<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link href="style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="heders" style=" position: relative; left:30%; margin-top:15%; width: 600px; color: white; background-color: #4aa1f3;">
<h2 class= "text-center" style="text-align: center; font-size: 50px;"> log in </h2>
<div class="log" style="padding-left: 200px">
	<h2>Login with login and password</h2>
	<form action="Login" method="post">
	<div class = "loginText" style=" width: auto; margin: 7px;">
	<div class = "login">
	<h2>Login</h2>
	</div>
	<input  type="text" name="login" maxlength= "150" style="padding-bottom: 10px; padding-top 0; border-radius: 8px;"><br>
	</div>
	<div class = "password"></div>
	<h2 >Password</h2>
	<input type="text" name="pass" maxlength="15" style="padding-top: 10px; border-radius: 8px;"><br>
	<input type="submit" value="login">
	</form>
	<br>
	If you are new user, please <a href="register.jsp">register</a>.
	</div>
</div>	
	</body>
	

</html>