package ua.weatherparser

import android.os.AsyncTask
import org.json.JSONObject

const val API_KEY = "5cdc912b0c2c5a7fdefc5f24f12c14b7"

class WeatherFetcher {
    fun fetch(city: String, callback: FetchCallback) {
        FetchTask(callback, city).execute()
    }

    private class FetchTask(val callback: FetchCallback, val city: String) : AsyncTask<Void, Void, JSONObject>() {
        override fun doInBackground(vararg params: Void?): JSONObject {
//            val response = "http://api.openweathermap.org/data/2.5/weather?q=$city,uk&APPID=$API_KEY".httpGet()
            var response =
                khttp.get("http://api.openweathermap.org/data/2.5/weather?q=$city&APPID=$API_KEY&units=metric")
            return response.jsonObject
        }

        override fun onPostExecute(result: JSONObject?) {
            callback.onResponse(result)
            super.onPostExecute(result)
        }
    }

    interface FetchCallback {
        fun onResponse(jsonObject: JSONObject?)
    }

}