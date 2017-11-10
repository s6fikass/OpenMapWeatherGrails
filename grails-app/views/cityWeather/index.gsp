<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta name="layout" content="main"/>
    <title>Home Weather</title>


</head>
<body>
<h1>Weather</h1>

<g:form controller="CityWeather">
    <label>City Name: </label>
    <g:textField name="city" required="true"/><br/><br/>
    <g:actionSubmit value="Get current weather" action="currentWeather"/> <br/>
    <g:actionSubmit value="Get forecast" action="forecastWeather"/>
</g:form>

</body>
</html>
