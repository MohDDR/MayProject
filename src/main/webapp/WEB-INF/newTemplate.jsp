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
	<title>Create Skill</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/css/main.css"> <!-- change to match your file/naming structure -->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class=" container border border-primary ">
		<div class=" nav justify-content-center border border-primary position-sticky top-0 ">
			<h1>Welcome, <c:out value="${user.firstName}"></c:out>!</h1>
			<div>
				<a href="/dashboard"><button>Dashboard</button></a>
				<a href="/logout"><button>Log Out</button></a>
				<a href="/view/account"><button>View Account</button></a>
			</div>
		</div>
		<div class=" row border border-primary ">
			<div class=" col-sm-2 border border-primary ">
				<p>(owned skills/similar skills(*through main stats and actions))</p>
			</div>
			<div class=" col-sm-8 border border-primary ">
				<form:form action="/new-template" method="post" modelAttribute="template">
				    <div class="">
				        <form:label path="name">Name: </form:label>
				        <form:input type="name" path="name"/>
				        <form:errors class="error" path="name"/>
				    </div>
				    <div class="">
				    	<form:label path="description">Description: </form:label>
				        <form:textarea path="description"/>
				        <form:errors class="error" path="description"/>
				    </div>
				    <div class="">
				        <form:label path="lv_inc">points increments: </form:label>
				        <form:input type="number" path="lv_inc"/>
				        <form:errors class="error" path="lv_inc"/>
				    </div>
				    <div class="">
				        <form:label path="lv_limit">Level Limit: </form:label>
				        <form:input type="number" path="lv_limit"/>
				        <form:errors class="error" path="lv_limit"/>
				    </div>
				    <div class="">
				        <form:label path="gate">Gate: </form:label>
				        <form:input type="number" path="gate"/>
				        <form:errors class="error" path="gate"/>
				    </div>
				    <div class="">
				        <form:label path="exp">Exp(?): </form:label>
				        <form:input type="number" path="exp"/>
				        <form:errors class="error" path="exp"/>
				    </div>
				    <div class="">
					    <input type="submit" value="Submit" />
				    </div>
				</form:form>
			</div>
			<div class=" col-sm-2 border border-primary ">
				<div>
					<h6>Actions related to template</h6>
					<p> limit max amount that can be shown at a time </p>
				</div>
				<div>
					<h6>Titles related to template</h6>
					<p> limit max amount that can be shown at a time </p>
				</div>
				<div>
					<h6>Template tags</h6>
					<p> limit max amount that can be shown at a time </p>
				</div>
			</div>
		</div>
	</div>
</body>
</html> 