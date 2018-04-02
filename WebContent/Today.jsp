<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your tasks today</title>
</head>
<body>
	<jsp:include page="index.jsp" />
	<div class="heders"
		style="margin-bottom: 0px; color: white; background-color: #4aa1f3;">
		<h2 class="text-center" style="font-size: 50px; font-weight: 600;">Task
			by Today</h2>
	</div>
	<div class="tasks"
		style="margin-bottom: 0px; color: white; background-color: #4aa1f3;">
		<form action="TodayServlet" class="row form-group"
			id="rowDiv">
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
				<tbody>
					<c:forEach var="tasks" items="${tasks}">
						<td></td>
						<td>${tasks.dateCreate}</td>
						<td>${tasks.header}</td>
						<td>${tasks.description}</td>
						<td>${tasks.report}</td>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>