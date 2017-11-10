<html>
<head>
    <title>Current Weather</title>
    <meta name="layout" content="main" />
</head>
<body>
<div id="content" role="main">
    <section class="row colset-2-its">
        <g:if test="${forecastWeather}">
            <g:render template="/cityWeather/forecastWeather"
                      model="[forecastWeather: forecastWeather]"/>
        </g:if>
    </section>
</div>
</body>
</html>
