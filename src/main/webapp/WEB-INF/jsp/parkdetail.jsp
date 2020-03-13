<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section id="main-content">
	<h2>${park.parkName}</h2>
	<table id="detail-table">
		<tr>
			<td><img src="img/parks/${parkpic}.jpg" /></td>
			<td>
				<div>
					<span class="bold">State: </span>${park.state}</div>
				<div>
					<span class="bold">Acreage: </span>${park.acreage}</div>
				<div>
					<span class="bold">Elevation: </span>${park.elevation}</div>
				<div>
					<span class="bold">Miles of trail: </span>${park.milesOfTrail}</div>
				<div>
					<span class="bold">Number of Campsites: </span>${park.numberOfCampsites}</div>
				<div>
					<span class="bold">Climate: </span>${park.climate}</div>
				<div>
					<span class="bold">Year founded: </span>${park.yearFounded}</div>
				<div>
					<span class="bold">Annual visitor count: </span>${park.annualVisitorCount}</div>
				<div>
					<span class="bold">Quote: </span>${park.quote}</div>
				<div>
					<span class="bold">Quote source: </span>${park.quoteSource}</div>
				<div>
					<span class="bold">Description: </span>${park.description}</div>
				<div>
					<span class="bold">Entry Fee: </span>$${park.entryFee}.00
				</div>
				<div>
					<span class="bold">Number of animal species: </span>${park.numberOfAnimalSpecies}</div>
			</td>
		</tr>
	</table>

	<table id="detail-table-weather">
		<tr>
			<td id="today">TODAY</td>
		</tr>
		<tr>
			<c:forEach var="day" items="${forecast}">
				<td><img src="img/weather/${day.forecast}.png" />
					<p>Low ${day.low}</p>
					<p>High ${day.high}</p> <c:if test="${day.day == 1}">
						<p>${day.advisory}</p>
					</c:if></td>
			</c:forEach>
		</tr>
	</table>

	<div id="tempButton">
		<c:url var="formAction" value="/parkdetail" />
		<form method="POST" action="${formAction}">
			<div class="formInputGroup">
				<label for="tempScale">Convert to</label> <select name="tempScale"
					id="tempScale">
					<option value="F">Fahrenheit</option>
					<option value="C">Celsius</option>


				</select>
			</div>
			<input type="hidden" name="detailURL" value="${detailURL}" /> <input
				class="submitButton" type="submit" value="Submit" />

		</form>
	</div>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />