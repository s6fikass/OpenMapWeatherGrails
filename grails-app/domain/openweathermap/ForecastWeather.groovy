package openweathermap
import groovy.transform.CompileStatic

@CompileStatic
class ForecastWeather {

    List<CurrentWeather> forecastList
    String cityName
    Integer numberOfForecasts

}
