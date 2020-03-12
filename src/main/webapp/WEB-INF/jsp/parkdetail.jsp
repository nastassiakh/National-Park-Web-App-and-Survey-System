<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section id="main-content">
	<h2>${park.parkName}</h2>
	<img src="img/parks/${parkpic}.jpg" />
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


	<table>
		<tr>
			<c:forEach var="day" items="${forecast}">
				<td>
				<img src="img/weather/${day.forecast}.png" />
					<p>Day ${day.day}</p>
					<p>Low ${day.low}F</p>
					<p>High ${day.high}F</p>
					<p>${day.advisory}</p>
				</td>
			</c:forEach>
		</tr>
	</table>
	
	
	
	
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />