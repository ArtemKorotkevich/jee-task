<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h3>Login with login and password</h3>
	<form action="Login" method="post">
	<strong>Login</strong>:<input type="text" name="login"><br>
	<strong>Password</strong>:<input type="text" name="pass"><br>
	<input type="submit" value="login">
	</form>
	<br>
	If you are new user, please <a href="register.jsp">register</a>.
	</body>

</html>