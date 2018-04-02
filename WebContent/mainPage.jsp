<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Page</title>
</head>
<body>
	<jsp:include page="index.jsp" />
	<div class="heders"
		style="margin-bottom: 0px; color: white; background-color: #4aa1f3;">
		<h2 class="text-center" style="font-size: 50px; font-weight: 600;">Task
			by User</h2>
	</div>
	<div class="tasks"
		style="margin-bottom: 0px; color: white; background-color: #4aa1f3;">
		<form action="tasksServlet" class="row form-group form-inline"
			id="rowDiv">
			<h4>user name: ${user.login}</h4>
			<h4>
				your tasks: <input type="button" value="Today"
					onclick='location.href = "today"'> <input type="button"
					value="Tomorrow" onclick='location.href = "tomorrow"'> <input
					type="button" value="Someday"
					onclick='location.href = "someday"'> <input
					type="button" value="Fixed" onclick='location.href = "Fixed.jsp"'>
				<input type="button" value="Recycle Bin"
					onclick='location.href = "Recycle Bin.jsp"'>
			</h4>


			<h3>
				<input type="button" value="add task"
					onclick='location.href = "addTasks.jsp"'>
			</h3>
		</form>



	</div>

</body>
</html>