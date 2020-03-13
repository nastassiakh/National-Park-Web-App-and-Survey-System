<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section id="main-content">
	<h2>${park.parkName}</h2>
	<table id="detail-table">
		<tr>
			<td><img src="img/parks/${parkpic}.jpg" /></td>
			<td>
				<div>State: ${park.state}</div>
				<div>Acreage: ${park.acreage}</div>
				<div>Elevation: ${park.elevation}</div>
				<div>Miles of trail: ${park.milesOfTrail}</div>
				<div>Number of Campsites: ${park.numberOfCampsites}</div>
				<div>Climate: ${park.climate}</div>
				<div>Year founded: ${park.yearFounded}</div>
				<div>Annual visitor count: ${park.annualVisitorCount}</div>
				<div>Quote: ${park.quote}</div>
				<div>Quote source: ${park.quoteSource}</div>
				<div>Description: ${park.description}</div>
				<div>Entry Fee: ${park.entryFee}</div>
				<div>Number of animal species: ${park.numberOfAnimalSpecies}</div>
			</td>
		</tr>
	</table>

	<table id="detail-table-weather">
		<tr>
			<c:forEach var="day" items="${forecast}">
				<td><img src="img/weather/${day.forecast}.png" />
					<p>Day ${day.day}</p>
					<p>Low ${day.low}</p>
					<p>High ${day.high}</p>
					<p>${day.advisory}</p></td>
			</c:forEach>
		</tr>
	</table>

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

</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />