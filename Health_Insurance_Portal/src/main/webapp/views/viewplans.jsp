<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<link
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css">
	
<script>

	function deleteConfirm() {
		return confirm("Are you sure! you want to delete?")
	}
	$(document).ready(function() {
		$('#Act_dtls').DataTable({
			"pagingType" : "full_numbers"
		});
	});
</script>
</head>
<body>
	<h1>view Plans</h1>
	<h2 align="center">${status}</h2>
	<table border="1" id="Act_dtls">

		<thead>
			<tr>
				<th>S.NO</th>
				<th>Plan Name</th>
				<th>Plan Description</th>
				<th>Plan Start date</th>
				<th>Plan End date</th>
				<th>Action</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${allPlans}" var="plan" varStatus="index">
				<tr>
					<td><c:out value="${plan.planId}" /></td>
					<td><c:out value="${plan.planName}" /></td>
					<td><c:out value="${plan.planDescription}" /></td>
					<td><c:out value="${plan.startDate}" /></td>
					<td><c:out value="${plan.endDate}" /></td>

					<td><a href="editPlan?planId=${plan.planId}">Edit</a>|<c:if
							test="${plan.activeSwitch=='N' }">
							<a href="deletePlan?planId=${plan.planId}"
								onclick="{ return deleteConfirm();}">delete</a>
						</c:if> <c:if test="${plan.activeSwitch=='Y' }">
							<a href="deletePlan?planId=${plan.planId}"
								onclick="{ return deleteConfirm();}">Activate</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>