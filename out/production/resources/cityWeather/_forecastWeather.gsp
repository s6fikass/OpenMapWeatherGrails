

<g:if test="${forecastWeather}">
    <g:each in="${forecastWeather.forecastList}" var="weather">
        <div class="weatherBlock">
            <h2><b>${forecastWeather.cityName}</b></h2>
            <h3>${weather.main?.temperature} </h3>
        </div>
    </g:each>
</g:if>
