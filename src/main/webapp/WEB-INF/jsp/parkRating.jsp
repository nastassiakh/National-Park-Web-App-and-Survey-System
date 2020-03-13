<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="title" value="Park Rating" />
</c:import>

<div class="rating-table">
	<table>
		<tr>
			<th>National Park</th>
			<th>Park Name</th>
			<th>Park Rating</th>
		</tr>
		<c:forEach items="${surveyMap }" var="survey">
			<tr>
				<td><img id="rating-img" src="<c:url value="img/parks/${survey.parkCode.toLowerCase() }.jpg" />" /></td>

				<td><c:out value="${survey.parkName }"></c:out></td>

				<td><c:out value="${survey.numberOfVotes }"></c:out></td>
			</tr>

		</c:forEach>
	</table>
</div>
</body>
</html>