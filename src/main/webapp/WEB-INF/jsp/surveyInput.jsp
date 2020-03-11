
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" >
   <c:param name="title" value="Survey" />
</c:import>
<br><br>
<form method="POST" action="submitsurvey">
	<div class="formInputGroup">
		<label for="nationalPark">Favorite National Park: </label>
		 <select name="parkCode" id="allParks">
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
		<label for="email">Your Email</label> 
		<input type="text" name="email" id="email" />
	</div>
	
	<div class="formInputGroup">
		<label for="states">State of residence: </label>
		 <select name="states" id="states">
			<option value="AK">Alaska</option>
			<option value="CA">California</option>
			<option value="CO">Colorado</option>
			<option value="FL">Florida</option>
			<option value="HI">Hawaii</option>
			<option value="MN">Minnesota</option>
			<option value="MT">Montana</option>
			<option value="NJ">New Jersey</option>
			<option value="NY">New York</option>
			<option value="PA">Pennsylvania[</option>
			<option value="TE">Texas</option>
		</select>
	</div>
	
	<div class="formInputGroup">
		 <input type="radio" id="inactive" name="inactive" value="inactive">
		 <label for="inactive">Inactive</label><br>
		 <input type="radio" id="sedentary" name="sedentary" value="sedentary">
		 <label for="sedentary">Sedentary</label><br>
		 <input type="radio" id="active" name="active" value="active">
		 <label for="active">Active</label><br>
		 <input type="radio" id="exactive" name="exactive" value="exactive">
		 <label for="exactive">Extremely Active</label><br>
	</div>	
			 
	<input class="submitButton" type="submit" value="Submit" />
</form>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />