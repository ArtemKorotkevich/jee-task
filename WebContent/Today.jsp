<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="by.gsu.epamlab.controller.TasksServlet"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<table>
			<thead>
				<tr>
					<th>#</th>
					<th>date Create</th>
					<th>Caption</th>
					<th>description</th>
					<th>report</th>
				</tr>
			</thead>
		</table>
	</div>
	<c:forEach var="task" items="${tasks}">
		<table>
			<tr>
				<td></td>
				<td>${task.dateCreate}</td>
				<td>${task.hader}</td>
				<td>${task.description}</td>
				<td></td>
			</tr>
		</table>
	</c:forEach>

</body>
</html>