

<g:if test="${currentWeather.weatherList}">
    <g:each in="${currentWeather.weatherList}" var="weather">
        <div class="weatherBlock">
            <h2><b>${currentWeather.cityName}</b></h2>
            <h3>${currentWeather.main?.temperature} </h3>
            <h4>${weather.description}</h4>
        </div>
    </g:each>
</g:if>
