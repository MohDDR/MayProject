<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>All Skill</title>
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
				<a href=""><button>???</button></a>
			</div>
		</div>
		<div class=" row border border-primary ">
			<div class=" col-sm-2 border border-primary ">
				<p>(owned skills/similar skills(*through main stats and actions))</p>
			</div>
			<div class=" col-sm-8 border border-primary ">
			
				<h6>Skill</h6>
				<c:forEach var="skill" items="${ skills }">
					<div>
						<p><c:out value="${ skill.name }"/></p>
						<a href="/view-skill/${ skill.id }">View Skill</a>
					</div>
				</c:forEach>
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