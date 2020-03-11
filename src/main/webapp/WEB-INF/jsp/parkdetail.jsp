<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section id="main-content">
	<h2>Park Detail</h2>

	<c:forEach var="day" items="${forecast}">
<p>${day.day}</p>
<p>${day.low} F</p>
<p>${day.high} F</p>
<p>${day.forecast}</p>


	</c:forEach>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />