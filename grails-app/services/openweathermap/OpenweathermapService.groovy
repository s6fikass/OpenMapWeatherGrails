package openweathermap

import grails.gorm.transactions.Transactional
import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic


@Transactional
class OpenweathermapService implements GrailsConfigurationAware{

    String appid
    String openWeatherUrl

    @Override
    void setConfiguration(Config co) {
        openWeatherUrl = co.getProperty('openweather.url', String, 'http://api.openweathermap.org')
        appid = co.getProperty('openweather.appid', String)
    }

    @CompileDynamic
    CurrentWeather currentWeather(String cityName) {
        RestBuilder rest = new RestBuilder()
        String url = "${openWeatherUrl}/data/2.5/weather?q={city}&appid={appid}"
        Map params = [city: cityName, appid: appid]

        RestResponse restResponse = rest.get(url) {
            urlVariables params
        }

        if ( restResponse.statusCode.value() == 200 && restResponse.json ) {
            return OpenweathermapParser.currentWeatherFromJSONElement(restResponse.json)
        }
        null
    }

    ForecastWeather forecastWeather(String cityName,Integer limit=0){

        RestBuilder rest = new RestBuilder()
        String url = "${openWeatherUrl}/data/2.5/forecast?q={city}&appid={appid}"
        Map params = [city: cityName, appid: appid]

        RestResponse restResponse = rest.get(url) {
            urlVariables params
        }

        if ( restResponse.statusCode.value() == 200 && restResponse.json ) {
            return OpenweathermapParser.forecastWeatherFromJSONElement(restResponse.json,limit)
        }
        null
    }
}
