<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="index.jsp" />
	<div class="heders"
		style="margin-bottom: 0px; color: white; background-color: #4aa1f3;">
		<h2 class="text-center" style="font-size: 50px; font-weight: 600;">Recycle
			Bin</h2>
	</div>
	<div class="tasks"
		style="display: block; margin-bottom: 0px; color: white; background-color: #4aa1f3;">
		<table width="100%" cellpadding="5">
			<thead>
				<tr>
					<th align="left">#</th>
					<th align="left">date Create</th>
					<th align="left">Caption</th>
					<th align="left">description</th>
					<th align="left">report</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tasks" items="${tasks}">
					<tr>
						<td><input type="checkbox" name="task-${tasks.idtasks}"></td>
						<td align="left">${tasks.dateCreate}</td>
						<td align="left">${tasks.header}</td>
						<td align="left">${tasks.description}</td>
						<td align="left">${tasks.report}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


</body>
</html>