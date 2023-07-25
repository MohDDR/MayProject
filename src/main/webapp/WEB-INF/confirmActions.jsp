<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Confirm Actions</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/css/secondary.css"> <!-- change to match your file/naming structure -->
	<link rel="stylesheet" type="text/css" href="/css/main.css">
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class=" container border border-primary ">
		<div class=" nav justify-content-center border border-primary position-sticky top-0 ">
			<h1><c:out value="${user.firstName}"></c:out></h1>
			<div>
				<a href="/dashboard"><button>Dashboard</button></a>
				<a href="/logout"><button>Log Out</button></a>
				<a href="/view/account"><button>View Account</button></a>
				current daily limit
			</div>
		</div>
		<div class=" row border border-primary ">
			<div class=" col-sm-2 border border-primary ">
				current stats with preview of stats after confirmation
				with users daily limit after confirmation
			</div>
			<div class=" col-sm-8 border border-primary ">
				actions being confirmed - thorough detail of actions involved
				also include skills that will interfere with actions
				action and skill reset times
			</div>	
			<div class=" col-sm-2 border border-primary ">
				suggestions of action replacements
			</div>
		</div>
	</div>
</body>
</html> 