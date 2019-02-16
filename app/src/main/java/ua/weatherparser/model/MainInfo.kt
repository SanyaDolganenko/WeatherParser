package ua.weatherparser.model


data class MainInfo(
    val temp: Float,
    val pressure: Int,
    val humidity: Float,
    val temp_min: Float,
    val temp_max: Float
)