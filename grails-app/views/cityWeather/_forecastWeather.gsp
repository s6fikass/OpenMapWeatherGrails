

<g:if test="${forecastWeather}">
    <g:each in="${forecastWeather.forecastList}" var="weather">
        <div class="weatherBlock">
            <h2><b>${forecastWeather.cityName}</b></h2>
            <h3>${weather.main?.temperature} </h3>
            <h3>${weather.dt_txt} </h3>
        <g:each in="${weather.weatherList}" var="wthr">
            <h3>${wthr.description}</h3>
        </g:each>
        </div>
    </g:each>
</g:if>
