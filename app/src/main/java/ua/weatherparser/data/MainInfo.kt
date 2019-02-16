package ua.weatherparser.data


data class MainInfo(
    val temp: Int,
    val pressure: Int,
    val humidity: Int,
    val temp_min: Int,
    val temp_max: Int
)