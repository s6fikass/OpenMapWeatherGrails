package openweathermap

import grails.rest.RestfulController

class RestCurrentWeatherController extends RestfulController{
    static responseFormats = ['json', 'xml']

    RestCurrentWeatherController(){
        super(currentWeather)
    }
    def index() { }
}
