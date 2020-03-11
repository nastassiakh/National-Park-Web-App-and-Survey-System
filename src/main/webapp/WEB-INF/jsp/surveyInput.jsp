
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="title" value="Survey" />
</c:import>
<br>
<br>
<form method="POST" action="submitsurvey">
	<div class="formInputGroup">
		<label for="nationalPark">Favorite National Park: </label> <select
			name="parkCode" id="allParks">
			<option value="CVNP">Cuyahoga Valley National Park</option>
			<option value="ENP">Everglades National Park</option>
			<option value="GCNP">Grand Canyon National Park</option>
			<option value="GNP">Glacier National Park</option>
			<option value="GSMNP">Great Smoky Mountains National Park</option>
			<option value="GTNP">Grand Teton National Park</option>
			<option value="MRNP">Mount Rainier National Park</option>
			<option value="RMNP">Rocky Mountain National Park</option>
			<option value="YNP">Yellowstone National Park</option>
			<option value="YNP2">Yosemite National Park</option>
		</select>
	</div>

	<div class="formInputGroup">
		<label for="emailaddress">Your Email</label> <input type="text"
			name="emailaddress" id="emailaddress" />
	</div>

	<div class="formInputGroup">
		<label for="state">State of residence: </label> <select name="state"
			id="states">
			<option>Alabama</option>
			<option>Alaska</option>
			<option>Arizona</option>
			<option>Arkansas</option>
			<option>California</option>
			<option>Colorado</option>
			<option>Connecticut</option>
			<option>Delaware</option>
			<option>Florida</option>
			<option>Georgia</option>
			<option>Hawaii</option>
			<option>Idaho</option>
			<option>Illinois</option>
			<option>Kansas</option>
			<option>Kentucky</option>
			<option>Louisiana</option>
			<option>Maine</option>
			<option>Maryland</option>
			<option>Massachusetts</option>
			<option>Michigan</option>
			<option>Minnesota</option>
			<option>Mississippi</option>
			<option>Missouri</option>
			<option>Montana</option>
			<option>Nebraska</option>
			<option>Nevada</option>
			<option>New Hampshire</option>
			<option>New Jersey</option>
			<option>New Mexico</option>
			<option>New York</option>
			<option>North Carolina</option>
			<option>North Dakota</option>
			<option>Ohio</option>
			<option>Oklahoma</option>
			<option>Oregon</option>
			<option>Pennsylvania</option>
			<option>Puerto Rico</option>
			<option>Rhode Island</option>
			<option>South Carolina</option>
			<option>South Dakota</option>
			<option>Tennessee</option>
			<option>Texas</option>
			<option>Utah</option>
			<option>Vermont</option>
			<option>Virginia</option>
			<option>Washington</option>
			<option>West Virginia</option>
			<option>Wisconsin</option>
			<option>Wyoming</option>
		</select>
	</div>

	<div class="formInputGroup">
		<label for="activityLevel">Activity Level: </label> <br> <input
			type="radio" id="inactive" name="activityLevel" value="inactive">
		<label for="activityLevel">Inactive</label><br> <input
			type="radio" id="sedentary" name="activityLevel" value="sedentary">
		<label for="activityLevel">Sedentary</label><br> <input
			type="radio" id="active" name="activityLevel" value="active">
		<label for="activityLevel">Active</label><br> <input type="radio"
			id="exactive" name="activityLevel" value="exactive"> <label
			for="activityLevel">Extremely Active</label><br>
	</div>

	<input class="submitButton" type="submit" value="Submit" />
</form>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />