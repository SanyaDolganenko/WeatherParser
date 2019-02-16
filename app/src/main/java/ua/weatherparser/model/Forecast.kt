package ua.weatherparser.model


data class Forecast(
    val coord: Coordinations,
    val weather: Array<Weather>,
    val base: String,
    val main: MainInfo,
    val visibility: Long,
    val wind: Wind,
    val clouds: Cloudiness,
    val dt: Long,
    val id: Long,
    val name: String,
    val cod: Int
)