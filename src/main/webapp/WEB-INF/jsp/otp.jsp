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
		<form action="/otp" method="post">
			<div class="form-group">
				<label> Enter the pass code : <input class="form-control"
					type="text" name="otp" />
				</label>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div>
				<input class="btn btn-primary" type="submit" value="Verify"/> <a id="sendSmsLink"
					class="btn btn-success" href="#">Send SMS again</a>
			</div>
		</form>
	</c:if>

	<script src="webjars/jquery/1.11.1/jquery.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>

	<script>
		$(document).ready(function() {
			$("#sendSmsLink").click(function(event) {
				$.post("/otp/sendSMS", {
					_csrf:'${_csrf.token}'
				}, function(data, status) {
				});
				event.preventDefault();
			});
		});
	</script>
</body>
</html>