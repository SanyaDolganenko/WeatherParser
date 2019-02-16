package ua.weatherparser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.beust.klaxon.Klaxon
import com.beust.klaxon.KlaxonException
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import ua.weatherparser.data.Forecast

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
    }

    private fun processFetch() {
        weatherFetcher.fetch(edit_text_city.text.toString().trim(), object : WeatherFetcher.FetchCallback {
            override fun onResponse(jsonObject: JSONObject?) {
                try {
                    val parsedForecast = Klaxon().parse<Forecast>(jsonObject.toString())
                    displayForecast(parsedForecast)
                } catch (e: KlaxonException) {
                    e.printStackTrace()
                    text_view_output.text = jsonObject.toString()
                }
            }
        })
    }

    private fun displayForecast(forecast: Forecast?) {
        if (forecast != null) {

        } else {
            
        }
    }
}
