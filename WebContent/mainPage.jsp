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
		<form method="POST"   id="rowDiv" >
			<h4>user name: ${user.login}</h4>
			<h4>
				your tasks: <input type="button" value="Today"
					onclick='location.href = "tasks?section=today"'> <input
					type="button" value="Tomorrow"
					onclick='location.href = "tasks?section=tomorrow"'> <input
					type="button" value="Someday"
					onclick='location.href = "tasks?section=someday"'> <input
					type="button" value="Fixed"
					onclick='location.href = "tasks?section=fixed"'>
					 <input
					type="button" value="Recycle Bin"
					onclick='location.href = "tasks?section=recycle_bin"'>
			</h4>
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
								<td><input type="button" value="add file"
					onclick='location.href = "file.jsp"'/></td>
						<input type="hidden" name="taskId" value="${tasks.idtasks}" />
							</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
		
		<c:if test="${section == 'RECYCLE_BIN'}">
		<c:set var="Display" value="1" />
		</c:if>
		<c:choose>
			<c:when test="${Display == 1}">
        <input type = "submit" value = "Delet in Recycle bin"  formaction ="deletes"/>
    </c:when>
    <c:otherwise>
       <input type="button" value="add task"
					onclick='location.href = "addTasks.jsp"'/>
			<h3>
				<input type="submit" value="done" formaction = "done"/>
			</h3>
			
			<h3>
		<input type="submit" value="delete" formaction = "delete"/>		
	</h3>
    </c:otherwise>      
</c:choose>
		
		</form>
	</div>
	<script type="text/javascript">
	function onsubmit(e){
		e.stopPropagation();
	}
	</script>

</body>
</html>