<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Advanced Authentication</title>
<link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.css" />
<link rel="stylesheet"
	href="webjars/font-awesome/4.3.0/css/font-awesome.css" />
<link rel="stylesheet" href="css/styles.css" />
</head>


<body>

	<div class="container">
		<%@include file="fragment/logout.jsp"%>

		<h2>Users</h2>

		<form action="/users" method="post">

			<input type="hidden" name="id" value="${userForm.id}" />

			<div class="form-group">
				<label> Username : </label><input class="form-control" type="text"
					name="username" value="${userForm.username}" />

			</div>

			<div class="form-group">
				<label> Password : </label><input class="form-control"
					type="password" name="password" />

			</div>

			<div class="form-group">
				<label> Repeat Password : </label><input class="form-control"
					type="password" name="repeatPassword" />

			</div>

			<div class="form-group">
				<label> Moble No. : </label><input class="form-control" type="text"
					name="mobileno" value="${userForm.mobileno}" />

			</div>

			<div>
				<c:choose>
					<c:when test="${userForm.enabled}">
						<label class="checkbox-inline"><input type="checkbox"
							name="enabled" value="true" checked>Enable</label>
					</c:when>
					<c:otherwise>
						<label class="checkbox-inline"><input type="checkbox"
							name="enabled" value="true">Enable</label>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${userForm.enable2fa}">
						<label class="checkbox-inline"><input type="checkbox"
							name="enable2fa" value="true" checked>Enable 2FA</label>
					</c:when>
					<c:otherwise>
						<label class="checkbox-inline"><input type="checkbox"
							name="enable2fa" value="true">Enable 2FA</label>
					</c:otherwise>
				</c:choose>

			</div>


			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <br />
			<div>
				<input class="btn btn-primary" type="submit" value="Create" />
			</div>
		</form>

		<br />



		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Username</th>
					<th>Mobile No.</th>
					<th>Enable</th>
					<th>Enable 2FA</th>
					<th>OAuth ClientID</th>
					<th>OAuth Secret</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userPage.content}" var="user">
					<tr>
						<td>${user.username}</td>
						<td>${user.mobileno}</td>
						<td>${user.enabled}</td>
						<td>${user.enable2fa}</td>
						<td>${user.oAuthClientDetails.clientId}</td>
						<td>${user.oAuthClientDetails.clientSecret}</td>
						<td><a class="btn btn-primary"
							href="/users?username=${user.username}">Update</a>
							<button class="btn btn-danger"
								onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
						</td>
					</tr>
				</c:forEach>


			</tbody>
		</table>

		<c:url var="firstUrl" value="/users?page=1" />
		<c:url var="lastUrl" value="/users?page=${userPage.totalPages}" />
		<c:url var="prevUrl" value="/users?page=${currentIndex - 1}" />
		<c:url var="nextUrl" value="/users?page=${currentIndex + 1}" />

		<div class="pagination">
			<ul class="pagination" style="list-style-type: none;">
				<c:choose>
					<c:when test="${currentIndex == 1}">
						<li style="display: inline;" class="disabled"><a class="btn"
							href="#">&lt;&lt;</a></li>
						<li style="display: inline;" class="disabled"><a href="#">&lt;</a></li>
					</c:when>
					<c:otherwise>
						<li style="display: inline;"><a href="${firstUrl}">&lt;&lt;</a></li>
						<li style="display: inline;"><a href="${prevUrl}">&lt;</a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
					<c:url var="pageUrl" value="/users?page=${i}" />
					<c:choose>
						<c:when test="${i == currentIndex}">
							<li style="display: inline;" class="active"><a
								href="${pageUrl}"><c:out value="${i}" /></a></li>
						</c:when>
						<c:otherwise>
							<li style="display: inline;"><a href="${pageUrl}"><c:out
										value="${i}" /></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${currentIndex == userPage.totalPages}">
						<li style="display: inline;" class="disabled"><a href="#">&gt;</a></li>
						<li style="display: inline;" class="disabled"><a href="#">&gt;&gt;</a></li>
					</c:when>
					<c:otherwise>
						<li style="display: inline;"><a href="${nextUrl}">&gt;</a></li>
						<li style="display: inline;"><a href="${lastUrl}">&gt;&gt;</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>

	</div>

	<script src="webjars/jquery/1.11.1/jquery.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>
</body>
</html>