package ua.weatherparser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.beust.klaxon.Klaxon
import com.beust.klaxon.KlaxonException
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import ua.weatherparser.model.Forecast

class MainActivity : AppCompatActivity() {
    var weatherFetcher = WeatherFetcher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        edit_text_city.setText("Kharkiv")
    }

    private fun initComponents() {
        button_get_weather.setOnClickListener {
            processFetch()
        }
        swipe_refresh_layout.setOnRefreshListener {
            processFetch()
        }
        card_view.visibility = View.GONE
    }

    private fun processFetch() {
        card_view.visibility = View.GONE
        text_view_output.visibility = View.GONE
        weatherFetcher.fetch(edit_text_city.text.toString().trim(), object : WeatherFetcher.FetchCallback {
            override fun onResponse(jsonObject: JSONObject?) {
                swipe_refresh_layout.isRefreshing = false
                try {
                    val parsedForecast = Klaxon().parse<Forecast>(jsonObject.toString())
                    displayForecast(parsedForecast)
                } catch (e: KlaxonException) {
                    e.printStackTrace()
                    var message = "Error"
                    when {
                        jsonObject?.getInt("cod") == 404 -> message = jsonObject.getString("message")
                    }
                    text_view_output.visibility = View.VISIBLE
                    text_view_output.text = message
                }
            }
        })
    }

    private fun displayForecast(forecast: Forecast?) {
        if (forecast != null) {
            card_view.visibility = View.VISIBLE
            text_view_output.visibility = View.GONE
            text_view_city.text = forecast.name
            text_view_temperature.text = "${forecast.main.temp}°С"
            text_view_clouds.text = "Clouds: ${forecast.clouds.all}%"
            text_view_humidity.text = "Humidity: ${forecast.main.humidity}%"
            text_view_wind.text = "Wind: ${forecast.wind.speed} m/s"
            val imageUrl = "http://openweathermap.org/img/w/" + forecast.weather[0].icon + ".png"
            Glide.with(this).load(imageUrl).into(image_view_icon)
        } else {
            card_view.visibility = View.GONE
        }
    }


}
