<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>National Park Geek  <c:out value="${param.title}" /></title>
<c:url value="/css/nationalparkgeek.css" var="cssHref" />
<link rel="stylesheet" href="${cssHref}">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body id="body">
	<header>
		<c:url value="/" var="homePageHref" />
		<c:url value="/img/logo.png" var="logoSrc" />
		<a href="${homePageHref}"> <img src="${logoSrc}"
			alt="National Park Geek logo" />
		</a>
	</header>
	<nav>
		<ul>
			<li><a href="http://localhost:8080/45-m3-java-capstone/">Home</a></li>
			<li><a
				href="http://localhost:8080/45-m3-java-capstone/submitsurvey">Submit
					Survey</a></li>
			<li><a
				href="http://localhost:8080/45-m3-java-capstone/viewsurvey">View
					Surveys</a></li>
		</ul>
	</nav>