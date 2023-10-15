package com.bcold.sdev264_courseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.preference.PreferenceManager
import kotlin.math.max

class HistoryActivity : AppCompatActivity() {

    // Instantiation
    lateinit var historyText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // Initialization
        historyText = findViewById(R.id.historyText)

        // Enables scrolling
        historyText.movementMethod = ScrollingMovementMethod()

        // Grab the intent string sent by the main activity
        val unit = intent.getStringExtra("Unit")
        // Grab the saved sharedPreference string from onPause, which contains user conversion history
        val historySharedPreferences = getSharedPreferences("historySharedPref", MODE_PRIVATE)
        val s1 = historySharedPreferences.getString("historyText", "")

        historyText.text = s1
        historyText.text = (unit + historyText.text)

    }

    override fun onResume() {
        super.onResume()
        historySettings()
    }

    // Grab the maximum amount of lines defined by the user and set historyText.maxLines to that number
    private fun historySettings() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val maxLines = prefs.getString("maxLines", "10")

        if (maxLines != null) {
            historyText.maxLines = maxLines.toInt()
        }
    }

    override fun onPause() {
        super.onPause()
        // Create the shared pref object
        val sharedPreferences = getSharedPreferences("historySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()

        // Write all the conversion data from historyText to a sharedPref string and apply
        myEdit.putString("historyText", historyText.text.toString())
        myEdit.apply()

    }
}