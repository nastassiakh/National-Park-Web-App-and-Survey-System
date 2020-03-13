
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="title" value="Survey" />
</c:import>
<br>
<br>

<c:url value="/submitsurvey" var="submitFormUrl" />
<form:form method="POST" action="${submitFormUrl}"
	modelAttribute="surveyModel">


	<div class="formInputGroup">
		<form:label path="parkCode">Favorite National Park: </form:label>

		<select name="parkCode" id="allParks">
			<option disabled="" selected="">Select National Park</option>
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
		<form:errors path="parkCode" />
	</div>

	<div class="formInputGroup">
		<form:label path="emailaddress">Your Email: </form:label>
		<form:input type="text" path="emailaddress" id="emailaddress"
			placeholder="Enter your email" />
		<form:errors path="emailaddress" />
	</div>

	<div class="formInputGroup">
		<form:label path="state">State of residence: </form:label>

		<select name="state" id="states">
			<option disabled="" selected="">Select your State</option>
			<option value="Alabama">Alabama</option>
			<option value="Alaska">Alaska</option>
			<option value="Arizona">Arizona</option>
			<option value="Arkansas">Arkansas</option>
			<option value="California">California</option>
			<option value="Colorado">Colorado</option>
			<option value="Connecicut">Connecticut</option>
			<option value="Delaware">Delaware</option>
			<option value="Florida">Florida</option>
			<option value="Georgia">Georgia</option>
			<option value="Hawaii">Hawaii</option>
			<option value="Idaho">Idaho</option>
			<option value="Illinois">Illinois</option>
			<option value="Kansas">Kansas</option>
			<option value="Kentucky">Kentucky</option>
			<option value="Louisiana">Louisiana</option>
			<option value="Maine">Maine</option>
			<option value="Maryland">Maryland</option>
			<option value="Massachusetts">Massachusetts</option>
			<option value="Michigan">Michigan</option>
			<option value="Minnesota">Minnesota</option>
			<option value="Mississippi">Mississippi</option>
			<option value="Missouri">Missouri</option>
			<option value="Montana">Montana</option>
			<option value="Nebraska">Nebraska</option>
			<option value="Nevada">Nevada</option>
			<option value="New Hampshire">New Hampshire</option>
			<option value="New Jersey">New Jersey</option>
			<option value="New Mexico">New Mexico</option>
			<option value="New York">New York</option>
			<option value="North Carolina">North Carolina</option>
			<option value="North Dakota">North Dakota</option>
			<option value="Ohio">Ohio</option>
			<option value="Oklahoma">Oklahoma</option>
			<option value="Orgeon">Oregon</option>
			<option value="Pennsylvania">Pennsylvania</option>
			<option value="Rhode Island">Rhode Island</option>
			<option value="South Carolina">South Carolina</option>
			<option value="South Dakota">South Dakota</option>
			<option value="Tennessee">Tennessee</option>
			<option value="Texas">Texas</option>
			<option value="Utah">Utah</option>
			<option value="Vermont">Vermont</option>
			<option value="Virginia">Virginia</option>
			<option value="Washington">Washington</option>
			<option value="West Virginia">West Virginia</option>
			<option value="Wisconsin">Wisconsin</option>
			<option value="Wyoming">Wyoming</option>
		</select>
		<form:errors path="state" />
	</div>

	<div class="formInputGroup">
		<form:label path="activityLevel">Activity Level: </form:label>
		<br> <input type="radio" id="inactive" name="activityLevel"
			value="inactive"> <label for="activityLevel">Inactive</label><br>
		<input type="radio" id="sedentary" name="activityLevel"
			value="sedentary"> <label for="activityLevel">Sedentary</label><br>
		<input type="radio" id="active" name="activityLevel" value="active">
		<label for="activityLevel">Active</label><br> <input type="radio"
			id="exactive" name="activityLevel" value="exactive"> <label
			for="activityLevel">Extremely Active</label><br>
		<form:errors path="activityLevel" />
	</div>

	<input class="btn btn-danger newstyle" type="submit" value="Submit" />
</form:form>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />