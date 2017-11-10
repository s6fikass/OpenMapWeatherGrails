package openweathermap

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import openweathermap.*
import org.grails.web.json.JSONElement

@CompileStatic
class OpenweathermapParser {

    @CompileDynamic
    static Coordinate coordinateFromJsonElement(JSONElement json) {
        Coordinate coordinate = new Coordinate()
        if ( json.long ) {
            coordinate.longitude = json.long as BigDecimal
        }
        if ( json.lat ) {
            coordinate.latitude = json.lat as BigDecimal
        }
        coordinate
    }

    @CompileDynamic
    static MainWeather mainFromJsonElement(JSONElement json) {
        MainWeather main = new MainWeather()
        if ( json.temp ) {
            main.temperature = json.temp as BigDecimal
        }
        if ( json.pressure ) {
            main.pressure = json.pressure as BigDecimal
        }
        if ( json.humidity ) {
            main.humidity = json.humidity as Integer
        }
        if ( json.temp_min ) {
            main.tempMin = json.temp_min as BigDecimal
        }
        if ( json.temp_max ) {
            main.tempMax = json.temp_max as BigDecimal
        }
        if ( json.seaLevel ) {
            main.seaLevel = json.seaLevel as BigDecimal
        }
        if ( json.groundLevel ) {
            main.groundLevel = json.groundLevel as BigDecimal
        }
        main
    }

    @CompileDynamic
    static Wind windFromJsonElement(JSONElement json) {
        Wind wind = new Wind()
        if ( json.speed ) {
            wind.speed = json.speed as BigDecimal
        }
        if ( json.deg ) {
            wind.deg = json.deg as BigDecimal
        }
        wind
    }

    @CompileDynamic
    static Sys sysFromJsonElement(JSONElement json) {
        Sys sys = new Sys()
        if ( json.id ) {
            sys.id = json.id as Long
        }
        if ( json.type ) {
            sys.type = json.type
        }
        if ( json.message ) {
            sys.message = json.message
        }
        if ( json.country ) {
            sys.country = json.country
        }
        if ( json.sunrise ) {
            sys.sunrise = json.sunrise as Integer
        }
        if ( json.sunset ) {
            sys.sunset = json.sunset as Integer
        }
        sys
    }

    @CompileDynamic
    static Weather weatherFromJsonElement(JSONElement json) {
        Weather weather = new Weather()
        if ( json.id ) {
            weather.id = json.id as Long
        }
        if ( json.main ) {
            weather.main = json.main
        }
        if ( json.description ) {
            weather.description = json.description
        }
        if ( json.icon ) {
            weather.icon = json.icon
        }
        weather
    }

    @CompileDynamic
    static CurrentWeather currentWeatherFromJSONElement(JSONElement json) {
        CurrentWeather currentWeather = new CurrentWeather()

        if ( json.coord ) {
            currentWeather.coordinate = coordinateFromJsonElement(json.coord)
        }
        if ( json.main ) {
            currentWeather.main = mainFromJsonElement(json.main)
        }
        if ( json.wind ) {
            currentWeather.wind = windFromJsonElement(json.wind)
        }
        if ( json.clouds ) {
            currentWeather.clouds = new Clouds()
            if ( json.clouds.all ) {
                currentWeather.clouds.cloudiness = json.clouds.all as Integer
            }
        }
        if ( json.sys ) {
            currentWeather.sys = sysFromJsonElement(json.sys)
        }
        if ( json.id ) {
            currentWeather.cityId = json.id as Long
        }
        if ( json.base ) {
            currentWeather.base = json.base
        }
        if ( json.name ) {
            currentWeather.cityName = json.name
        }
        if ( json.cod ) {
            currentWeather.code = json.cod as Integer
        }
        if ( json.visibility ) {
            currentWeather.visibility = json.visibility
        }
        if ( json.dt ) {
            currentWeather.dt = json.dt as Integer
            currentWeather.dt_txt= json.dt as String
        }


        if ( json.weather ) {
            currentWeather.weatherList = []
            for ( Object obj : json.weather ) {
                Weather weather = weatherFromJsonElement(obj)
                currentWeather.weatherList << weather
            }
        }
        currentWeather
    }

    @CompileDynamic
    static ForecastWeather forecastWeatherFromJSONElement(JSONElement json, Integer limit) {
        if(limit==0){
            limit=json.cnt
        }
        ForecastWeather forecast=new ForecastWeather()

        if ( json.coord ) {
            forecast.coordinate = coordinateFromJsonElement(json.coord)
        }

        CurrentWeather[] forecastList = new CurrentWeather[limit]

        for(Integer i=0;i<limit;i++) {

            CurrentWeather currentWeather = new CurrentWeather()
            if ( json.coord ) {
                currentWeather.coordinate = coordinateFromJsonElement(json.coord)
            }
            if ( json.list[i].main ) {
                currentWeather.main = mainFromJsonElement(json.list[i].main)
            }
            if ( json.list[i].wind ) {
                currentWeather.wind = windFromJsonElement(json.list[i].wind)
            }
            if ( json.list[i].clouds ) {
                currentWeather.clouds = new Clouds()
                if ( json.list[i].clouds.all ) {
                    currentWeather.clouds.cloudiness = json.list[i].clouds.all as Integer
                }
            }
            if ( json.list[i].sys ) {
                currentWeather.sys = sysFromJsonElement(json.list[i].sys)
            }
            if ( json.list[i].dt ) {
                currentWeather.dt = json.list[i].dt as Integer
            }

            if ( json.list[i].weather ) {
                currentWeather.weatherList = []
                for ( Object obj : json.list[i].weather ) {
                    Weather weather = weatherFromJsonElement(obj)
                    currentWeather.weatherList << weather
                }
            }
            if ( json.list[i].dt_txt ) {
                currentWeather.dt_txt=json.list[i].dt_txt
            }

            forecastList[i] = currentWeather
        }
        forecast.forecastList = forecastList
        forecast.cityName=json.city.name
        forecast.numberOfForecasts=limit

        forecast
    }


}



