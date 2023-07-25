<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>User Account</title>
	
	<!-- for Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- YOUR own local CSS -->
	<link rel="stylesheet" type="text/css" href="/css/secondary.css"> <!-- change to match your file/naming structure -->
	<link rel="stylesheet" type="text/css" href="/css/main.css">
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
	<div class=" container border border-primary">
		<div class=" nav justify-content-center border border-primary">
			<h1>Welcome, <c:out value="${user.firstName}"></c:out>!</h1>
			<div>
				<a href="/dashboard"><button>Dashboard</button></a>
				<a href="/logout"><button>Log Out</button></a>
				<a href="/view-all/templates"><button>All Templates</button></a>
				<a href="/view-all/titles"><button>All Titles</button></a>
				<a href="/view-all/skills"><button>All Skills</button></a>
			</div>
		</div>
		<div class=" row border border-primary">
			<div class=" col-sm-2 border border-primary">
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
			<div class=" col-sm-5 border border-primary">
				<h4>Titles</h4>
				<c:forEach var="title" items="${ user.titles }">
					<p> <c:out value="${ title.name }"/> </p>
				</c:forEach>
			</div>
			<div class=" col-sm-5 border border-primary">
				<h4>Owned Skills</h4>
				<a href="/new-template"><button>Create a skill</button></a>
				<c:forEach var="skill" items="${ user.skills }">
					<p> <c:out value="${ skill.name }"/> </p>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>