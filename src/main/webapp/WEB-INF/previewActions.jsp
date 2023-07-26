<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Action Preview</title>
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
				<a href="/view/account"><button>View Account</button></a>
				<a href="/logout"><button>Log Out</button></a>
			</div>
		</div>
		<div class=" row border border-primary ">
			<div class=" col-sm-2 border border-primary ">
				<div class="">
					<h5>Charisma: <c:out value="${ user.charisma.average_cha }"/></h5>
					<h6>Speech: <c:out value="${ user.charisma.speech }"/></h6>
					<h6>Conduct: <c:out value="${ user.charisma.conduct }"/></h6>
					<h6>Reputation: <c:out value="${ user.charisma.reputation }"/></h6>
					<h6>Ability: <c:out value="${ user.charisma.ability }"/></h6>
					<h6>network: <c:out value="${ user.charisma.network }"/></h6>
				</div>
				<div class="">
					<h5>Constitution: <c:out value="${ user.consti.average_con }"/></h5>
					<h6>Health: <c:out value="${ user.consti.health }"/></h6>
					<h6>Diet: <c:out value="${ user.consti.diet }"/></h6>
					<h6>Training: <c:out value="${ user.consti.training }"/></h6>
					<h6>Intensity: <c:out value="${ user.consti.intensity }"/></h6>
					<h6>Consistency: <c:out value="${ user.consti.consistency }"/></h6>
					<h6>Length: <c:out value="${ user.consti.length }"/></h6>
				</div>
				<div class="">
					<h5>Willpower: <c:out value="${ user.will.average_will }"/></h5>
					<h6>Mood: <c:out value="${ user.will.mood }"/></h6>
					<h6>Discipline: <c:out value="${ user.will.discipline }"/></h6>
					<h6>Faith: <c:out value="${ user.will.faith }"/></h6>
					<h6>Mentality: <c:out value="${ user.will.mentality }"/></h6>
					<h6>Focus: <c:out value="${ user.will.focus }"/></h6>
					<h6>Drive: <c:out value="${ user.will.drive }"/></h6>
				</div>
			</div>
			<div class=" col-sm-8 border border-primary ">
				<h6>Actions</h6>
				<a href="/new-action"><button>Create action</button></a>
				<c:forEach var="action" items="${ actions }">
					<div>
						<p><c:out value="${ action.name }"/></p>
						<p><c:out value="${ action.maxLimit }"/></p>
						<a href="/view-action/${ action.id }">View Action</a>
						<a href="/dashboard/${ action.id }">Select Action</a>
					</div>	
				</c:forEach>
			</div>
			<div class=" col-sm-2 border border-primary ">
				<h6>Completed Actions</h6>
				<c:forEach var="compAct" items="${ compActs }">
				
					<p> <c:out value="${ compAct.key.name }"/> </p>
					<p> <c:out value="${ compAct.value }"/> </p>
					
				</c:forEach>
				<a href="/actions-preview">preview Action results</a>
			</div>
		</div>
	</div>
</body>
</html>