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

	<c:if test="${pageContext.request.remoteUser ne null}">
		<h4>
			Hello <b><c:out value="${pageContext.request.remoteUser}" /></b>
		</h4>

		<form action="/logout" method="post">
			<input class="btn btn-primary" type="submit" value="Sign Out" /> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</c:if>


	<c:if test="${pageContext.request.remoteUser eq null}">
		<form action="/login" method="post">
			<div class="form-group">
				<label> User Name : <input class="form-control" type="text"
					name="username" placeholder="Username" />
				</label>
			</div>
			<div class="form-group">
				<label> Password: <input class="form-control"
					type="password" name="password" placeholder="Password" />
				</label>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div>
				<input class="btn btn-primary" type="submit" value="Sign In" />
			</div>
		</form>
	</c:if>

	<c:if test="${param.error ne null}">
		<div>Invalid username and password.</div>
	</c:if>

	<script src="webjars/jquery/1.11.1/jquery.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>
</body>
</html>