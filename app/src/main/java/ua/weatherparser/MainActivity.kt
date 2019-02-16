package ua.weatherparser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var weatherFetcher = WeatherFetcher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        processFetch()
    }

    private fun initComponents() {

    }

    private fun processFetch() {
        weatherFetcher.fetch("Kharkiv", object : WeatherFetcher.FetchCallback {
            override fun onResponse(jsonObject: JSONObject?) {
                Toast.makeText(this@MainActivity, "Processed fetch $jsonObject", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
