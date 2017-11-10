<html>
<head>
    <title>Current Weather</title>
    <meta name="layout" content="main" />
</head>
<body>
<div id="content" role="main">
    <h1>${city}</h1>
    <section class="row colset-2-its">
        <g:if test="${currentWeather}">
            <g:render template="/cityWeather/currentWeather"
                      model="[currentWeather: currentWeather]"/>
        </g:if>
    </section>
</div>
</body>
</html>
