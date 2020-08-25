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
	<h1 align="center"><b>view Accounts</b></h1>
	<h1 align="center">${status}</h1>
	<table border="1" id="Act_dtls">

		<thead>
			<tr>
				<th>S.NO</th>
				<th>Name</th>
				<th>Email</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user" varStatus="index">
				<tr>
					<td><c:out value="${index.count}" /></td>
					<td><c:out value="${user.firstName}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><a href="editAccount?accountId=${user.id}">Edit</a>| <c:if
							test="${user.accountSwitch=='Y'}">
							<a href="deleteAccount?accountId=${user.id}"
								onclick="{ return deleteConfirm();}">Activate</a>
						</c:if> <c:if test="${user.accountSwitch=='N'}">
							<a href="deleteAccount?accountId=${user.id}"
								onclick="{ return deleteConfirm();}">Delete</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>