package com.gregspitz.kelvinwidget

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gregspitz.kelvinwidget.temperature.TemperatureActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        // http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID={APIKEY}
        temperatureButton.setOnClickListener({
            startActivity(Intent(this@MainActivity, TemperatureActivity::class.java))
        })
    }
}
