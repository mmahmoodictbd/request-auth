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
	<h1>Welcome!</h1>
	<p>
		Click <a href="<spring:url value='/login' />">here</a> to see a
		greeting.
	</p>
</body>
</html>