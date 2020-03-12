
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="title" value="Survey" />
</c:import>
<br><br>

<c:url value="/submitsurvey" var = "submitFormUrl"/>
<form:form method="POST" action="${submitFormUrl}" modelAttribute="surveyModel">


       <div class="formInputGroup">
		<form:label path="parkCode">Favorite National Park: </form:label>
		
		 <select name="parkCode" id="allParks" >
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
		<form:errors path="parkCode"/>
	</div>

	<div class="formInputGroup">
	    <form:label path="emailaddress">Your Email: </form:label>
		<form:input type="text" path="emailaddress" id="emailaddress" placeholder="Enter your email"/>
		<form:errors path="emailaddress"/>
	</div>

	<div class="formInputGroup">
		<form:label path="state">State of residence: </form:label>
		
		 <select name="state" id="states">
		    <option disabled="" selected="">Select your State</option>
			<option value="Arizona">Arizona</option>
			<option value="California">California</option>
			<option value="Colorado">Colorado</option>
			<option value="Florida">Florida</option>
			<option value="Montana">Montana</option>
			<option value="Wyoming">Wyoming</option>
			<option value="Washington">Washington</option>
			<option value="Tennessee">Tennessee</option>
			<option value="Ohio">Ohio</option>
		</select>
		<form:errors path="state"/>
	</div>

	<div class="formInputGroup">
	<form:label path="activityLevel">Activity Level: </form:label>
	<br>
		 <input type="radio" id="inactive" name="activityLevel" value="inactive" >
		 <label for="activityLevel">Inactive</label><br>
		 <input type="radio" id="sedentary" name="activityLevel" value="sedentary">
		 <label for="activityLevel">Sedentary</label><br>
		 <input type="radio" id="active" name="activityLevel" value="active">
		 <label for="activityLevel">Active</label><br>
		 <input type="radio" id="exactive" name="activityLevel" value="exactive">
		 <label for="activityLevel">Extremely Active</label><br>
		 <form:errors path="activityLevel"/>
	</div>	
			 
	<input class="btn btn-danger newstyle" type="submit" value="Submit" />
</form:form>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />