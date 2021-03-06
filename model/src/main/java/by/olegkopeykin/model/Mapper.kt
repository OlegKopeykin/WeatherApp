package by.olegkopeykin.model

import by.olegkopeykin.model.db.CityEntity
import by.olegkopeykin.model.db.WeatherEntity
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.model.network.CityResponse
import by.olegkopeykin.model.network.weather.current.CurrentWeatherResponse
import by.olegkopeykin.model.network.weather.onweek.Daily

fun CityResponse.toDomain(): CityModel {
    return CityModel(name = name, lat = lat, lon = lon, country = country)
}

fun CityEntity.toDomain(): CityModel{
    return CityModel(
        name=name,
        lat = lat,
        lon = lon,
        country=countryCode,
        isFavorite = isFavoriteCity)
}

fun CityModel.toDomain():CityEntity{
    return CityEntity(
        name=name,
        lat = lat,
        lon = lon,
        countryCode = country,
        isFavoriteCity = isFavorite)
}

fun WeatherModel.toDomain():WeatherEntity{
    return WeatherEntity(
        nameCity = nameCity,
        lat = lat?:0.0,
        lon = lon?:0.0,
        country = country,
        isFavorite = isFavorite,
        weatherTitle = weatherTitle?:"",
        weatherDescription = weatherDescription?:"",
        temp =  temp?:0.0,
        minTemp = minTemp?:0.0,
        maxTemp = maxTemp?:0.0,
        feelLike =  feelLike?:0.0,
        humidity =  humidity?:0,
        clouds = clouds?:0,
        windDeg = windDeg?:0,
        windSpeed = windSpeed?:0.0,
        date = date)
}

fun WeatherEntity.toDomain():WeatherModel{
    return WeatherModel(
        nameCity = nameCity,
        lat = lat?:0.0,
        lon = lon?:0.0,
        country = country,
        isFavorite = isFavorite,
        weatherTitle = weatherTitle?:"",
        weatherDescription = weatherDescription?:"",
        temp =  temp?:0.0,
        minTemp = minTemp?:0.0,
        maxTemp = maxTemp?:0.0,
        feelLike =  feelLike?:0.0,
        humidity =  humidity?:0,
        clouds = clouds?:0,
        windDeg = windDeg?:0,
        windSpeed = windSpeed?:0.0,
        date = date)
}

fun CurrentWeatherResponse.toDomain(city:CityModel): WeatherModel{
    return WeatherModel(
        nameCity = city.name,
        lat = city.lat?:0.0,
        lon = city.lon?:0.0,
        country = city.country,
        isFavorite = city.isFavorite,
        weatherTitle = weather?.first()?.main?:"",
        weatherDescription = weather?.first()?.description?:"",
        temp = main?.temp?:0.0,
        minTemp = main?.temp_min?:0.0,
        maxTemp = main?.temp_max?:0.0,
        feelLike =  main?.feels_like?:0.0,
        humidity =  main?.humidity?:0,
        clouds = clouds?.all?:0,
        windDeg = wind?.deg?:0,
        windSpeed = wind?.speed?:0.0,
        date = dt)
}

fun Daily.toDomain(city:CityModel): WeatherModel{
    return WeatherModel(
        nameCity = city.name,
        lat = city.lat?:0.0,
        lon = city.lon?:0.0,
        country = city.country,
        isFavorite = city.isFavorite,
        weatherTitle = weather?.first()?.main?:"",
        weatherDescription = weather?.first()?.description?:"",
        temp = temp?.day?:0.0,
        minTemp = temp?.min?:0.0,
        maxTemp = temp?.max?:0.0,
        feelLike =  feels_like?.day?:0.0,
        humidity =  humidity?:0,
        clouds = clouds?:0,
        windDeg = wind_deg?:0,
        windSpeed = wind_speed?:0.0,
        date = dt)
}