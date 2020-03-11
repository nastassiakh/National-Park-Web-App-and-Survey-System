<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section id="main-content">
	<table>
		<c:forEach var="park" items="${parks}">
			<tr>
				<td><a
					href="<c:url value="/parkdetail?parkCode=${park.parkCode}" />">
						<img class="one"
						src="<c:url value="img/parks/${park.parkCode.toLowerCase()}.jpg" />" />
				</a></td>
				<td>
					<h2>${park.parkName}</h2>
					<div>${park.description}</div>
				</td>
			</tr>

		</c:forEach>



	</table>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />