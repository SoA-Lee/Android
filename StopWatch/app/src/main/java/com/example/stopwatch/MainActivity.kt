package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.concurrent.timer

class MainActivity() : AppCompatActivity() {

    private var time = 0
    private var timerTask: Timer? = null
    private var isRunning = false

    lateinit var secTextView: TextView
    lateinit var milliTextView: TextView
    lateinit var setEditTextView: TextView
    lateinit var timeButton: Button

    constructor(parcel: Parcel) : this() {
        time = parcel.readInt()
        isRunning = parcel.readByte() != 0.toByte()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        secTextView = findViewById<TextView>(R.id.secTextView)
        milliTextView = findViewById<TextView>(R.id.milliTextView)
        setEditTextView = findViewById<EditText>(R.id.setEditText)
        timeButton = findViewById<Button>(R.id.timeButton)

        timeButton.setOnClickListener {
            if (setEditTextView.text.toString().toInt() != 0) {
                time = setEditTextView.text.toString().toInt() * 100

                timerTask = timer(period = 10) {
                    time--
                    val sec = time / 100
                    val milli = time % 100

                    runOnUiThread {
                        secTextView.text = "$sec"
                        milliTextView.text = "$milli"
                    }
                    if (time == 0) {
                        runOnUiThread {
                            secTextView.text = "0"
                            milliTextView.text = "0"
                            timerTask?.cancel()
                        }
                    }
                }
            }
        }
    }
}


