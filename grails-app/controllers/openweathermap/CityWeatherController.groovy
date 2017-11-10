package openweathermap

import grails.rest.RestfulController

class CityWeatherController {
    OpenweathermapService openweathermapService

    def index() {

    }

    def currentWeather(){
        CurrentWeather currentWeather = openweathermapService.currentWeather(params.city)
        [currentWeather: currentWeather, city: params.city]

    }

    def forecastWeather(){
        ForecastWeather forecastWeather = openweathermapService.forecastWeather(params.city)
        [forecastWeather: forecastWeather]
    }
}
