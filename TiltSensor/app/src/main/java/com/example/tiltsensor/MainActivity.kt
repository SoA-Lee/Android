package com.example.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager }

    private lateinit var tiltView: TiltView

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE //가로모드
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) //꺼지지 않도록 구성
        super.onCreate(savedInstanceState)

        tiltView=TiltView(this)//초기화 해주기
        setContentView(tiltView)
    }

    override fun onResume(){ //센서등록
        super.onResume()
        sensorManager.registerListener(this,
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
        SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() { //센서해지
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent?) {

        event?.let {
            Log.d("MainActivity", "onSensorChanged : x: " + "${event.values[0]}, y : ${event.values[1]}, z : ${event.values[2]}")
            tiltView.onSensorEvent(event)
        } //로그캣 가져와서 기록하기.
   }
}