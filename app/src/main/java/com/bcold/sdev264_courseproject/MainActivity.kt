package com.bcold.sdev264_courseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import androidx.annotation.ContentView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.preference.PreferenceManager
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    // Instantiations
    lateinit var errorText : TextView
    lateinit var baseUnitText: EditText
    lateinit var convertedUnitText: EditText
    lateinit var convertBtn: ImageButton

    lateinit var miles: RadioButton
    lateinit var kilometers: RadioButton
    lateinit var fahrenheit : RadioButton
    lateinit var celsius : RadioButton
    lateinit var pounds : RadioButton
    lateinit var kilograms: RadioButton
    lateinit var gallons :RadioButton
    lateinit var liters : RadioButton
    lateinit var feet : RadioButton
    lateinit var meters : RadioButton

    lateinit var historyBtn : Button

    lateinit var prefBtn : Button

    lateinit var tempHistory : TextView
    var activeLayout = 0

    override fun onResume() {
        super.onResume()
        // Call userSettings function to assign the appropriate layout
        userSettings()

        // Initializations
        errorText = findViewById(R.id.errorText)
        baseUnitText = findViewById(R.id.baseUnitText)
        convertedUnitText = findViewById(R.id.convertedUnitText)
        convertBtn = findViewById(R.id.convertBtn)

        miles = findViewById(R.id.milesRadio)
        kilometers = findViewById(R.id.kilometersRadio)
        fahrenheit = findViewById(R.id.fahrenheitRadio)
        celsius = findViewById(R.id.celsiusRadio)
        pounds = findViewById(R.id.poundsRadio)
        kilograms = findViewById(R.id.kilogramsRadio)
        gallons = findViewById(R.id.gallonsRadio)
        liters = findViewById(R.id.litersRadio)
        feet = findViewById(R.id.feetRadio)
        meters = findViewById(R.id.metersRadio)


        historyBtn = findViewById(R.id.historyBtn)
        prefBtn = findViewById(R.id.prefBtn)

        tempHistory = findViewById(R.id.tempHistory)
    }
    // The below "check" functions ensure that the appropriate counterpart radio buttons are selected
    fun checkMeters(view:View){
        if(feet.isChecked){meters.isChecked = true}
        else if(meters.isChecked){feet.isChecked = true}
    }

    fun checkKilograms(view:View){
        if(pounds.isChecked){kilograms.isChecked = true}
        else if(kilograms.isChecked){pounds.isChecked = true}
    }

    fun checkCelsius(view:View){
        if(fahrenheit.isChecked){celsius.isChecked = true}
        else if(celsius.isChecked){fahrenheit.isChecked = true}
    }

    fun checkLiters(view:View){
        if(gallons.isChecked){liters.isChecked = true}
        else if(liters.isChecked){gallons.isChecked = true}
    }

    fun checkKilometers(view:View){
        if(miles.isChecked){kilometers.isChecked = true}
        else if(kilometers.isChecked){miles.isChecked = true}
    }

    // Checks which radio buttons are selected and then calls the appropriate conversion function
    fun convertItem(view:View){

        if(miles.isChecked && kilometers.isChecked)
        {
            convertMiles()
        }

        if(pounds.isChecked && kilograms.isChecked)
        {
            convertPounds()
        }

        if(gallons.isChecked && liters.isChecked)
        {
            convertLiters()
        }

        if(feet.isChecked && meters.isChecked)
        {
            convertFeet()
        }

        if(fahrenheit.isChecked && celsius.isChecked)
        {
            convertCelsius()
        }
    }

    // The below "convert" functions perform the appropriate conversion arithmetic based upon the active layout and sends the results to the invisible tempHistory TextView
    private fun convertMiles() {
        if(baseUnitText.text != null)
        {
            try {
                errorText.isVisible = false

                if(activeLayout == 0)
                {

                    var baseUnit = baseUnitText.text.toString().toDouble()
                    var convertedUnit = baseUnit * 1.609344
                    convertedUnitText.setText("%.4f".format(convertedUnit.toString().toDouble()))
                    tempHistory.text = "$baseUnit miles = " + "%.4f".format(convertedUnit.toString().toDouble()) + " kilometers\n" + tempHistory.text
                }
                else if(activeLayout == 1)
                {
                    var baseUnit = baseUnitText.text.toString().toDouble()
                    var convertedUnit = baseUnit * 0.6213712
                    convertedUnitText.setText("%.4f".format(convertedUnit.toString().toDouble()))
                    tempHistory.text = "$baseUnit kilometers = " + "%.4f".format(convertedUnit.toString().toDouble()) + " miles\n" + tempHistory.text
                }

            }
            catch (exception: java.lang.RuntimeException){errorText.isVisible = true}
        }
    }

    private fun convertPounds() {
        if(baseUnitText.text != null)
        {
            try {
                errorText.isVisible = false

                if(activeLayout == 0)
                {

                    var baseUnit = baseUnitText.text.toString().toDouble()
                    var convertedUnit = baseUnit * 0.4536
                    convertedUnitText.setText("%.4f".format(convertedUnit.toString().toDouble()))
                    tempHistory.text = "$baseUnit pounds = " + "%.4f".format(convertedUnit.toString().toDouble()) + " kilograms\n" + tempHistory.text
                }
                else if(activeLayout == 1)
                {

                    var baseUnit = baseUnitText.text.toString().toDouble()
                    var convertedUnit = baseUnit * 2.204623
                    convertedUnitText.setText("%.4f".format(convertedUnit.toString().toDouble()))
                    tempHistory.text = "$baseUnit kilograms = " + "%.4f".format(convertedUnit.toString().toDouble()) + " pounds\n" + tempHistory.text
                }
            }
            catch (exception: java.lang.RuntimeException){errorText.isVisible = true}
        }
    }

    private fun convertLiters(){
        if(baseUnitText.text != null)
        {
            try {
                errorText.isVisible = false

                if(activeLayout == 0)
                {

                    var baseUnit = baseUnitText.text.toString().toDouble()
                    var convertedUnit = baseUnit * 3.7854
                    convertedUnitText.setText("%.4f".format(convertedUnit.toString().toDouble()))
                    tempHistory.text = "$baseUnit gallons = " + "%.4f".format(convertedUnit.toString().toDouble()) + " liters\n" + tempHistory.text
                }
                else if(activeLayout == 1)
                {

                    var baseUnit = baseUnitText.text.toString().toDouble()
                    var convertedUnit = baseUnit * 0.2641729
                    convertedUnitText.setText("%.4f".format(convertedUnit.toString().toDouble()))
                    tempHistory.text = "$baseUnit liters = " + "%.4f".format(convertedUnit.toString().toDouble()) + " gallons\n" + tempHistory.text
                }
            }
            catch (exception: java.lang.RuntimeException){errorText.isVisible = true}
        }
    }

    private fun convertFeet() {
        if(baseUnitText.text != null)
        {
            try {
                errorText.isVisible = false

                if(activeLayout == 0)
                {

                    var baseUnit = baseUnitText.text.toString().toDouble()
                    var convertedUnit = baseUnit * 0.3048
                    convertedUnitText.setText("%.4f".format(convertedUnit.toString().toDouble()))
                    tempHistory.text = "$baseUnit feet = " + "%.4f".format(convertedUnit.toString().toDouble()) + " meters\n" + tempHistory.text
                }
                else if(activeLayout == 1)
                {

                    var baseUnit = baseUnitText.text.toString().toDouble()
                    var convertedUnit = baseUnit * 3.28084
                    convertedUnitText.setText("%.4f".format(convertedUnit.toString().toDouble()))
                    tempHistory.text = "$baseUnit meters = " + "%.4f".format(convertedUnit.toString().toDouble()) + " feet\n" + tempHistory.text
                }
            }
            catch (exception: java.lang.RuntimeException){errorText.isVisible = true}
        }
    }

    private fun convertCelsius(){
        if(baseUnitText.text != null)
        {
            try {
                errorText.isVisible = false

                if(activeLayout == 0)
                {

                    var baseUnit = baseUnitText.text.toString().toDouble()
                    var convertedUnit = (baseUnit - 32) * 5 / 9
                    convertedUnitText.setText("%.4f".format(convertedUnit.toString().toDouble()))
                    tempHistory.text = "$baseUnit fahrenheit = " + "%.4f".format(convertedUnit.toString().toDouble()) + " celsius\n" + tempHistory.text
                }
                else if(activeLayout == 1)
                {

                    var baseUnit = baseUnitText.text.toString().toDouble()
                    var convertedUnit = (baseUnit * 9) / 5 + 32
                    convertedUnitText.setText("%.4f".format(convertedUnit.toString().toDouble()))
                    tempHistory.text = "$baseUnit celsius = " + "%.4f".format(convertedUnit.toString().toDouble()) + " fahrenheit\n" + tempHistory.text
                }
            }
            catch (exception: java.lang.RuntimeException){errorText.isVisible = true}
        }

    }

    // Handles the onClick action of the historyBtn
    fun openHistory(view: View) {
        val historyIntent = Intent(this, HistoryActivity::class.java)
        historyIntent.putExtra("Unit", tempHistory.text)
        startActivity(historyIntent)

    }

    // Handles the onClick action of the prefBtn
    fun openPrefs(view:View){
        val prefIntent = Intent(this, PreferencesActivity::class.java)
        startActivity(prefIntent)
    }

    // Handles the onClick action of the aboutBtn
    fun openHelp(view:View){
        val helpIntent = Intent(this,HelpActivity::class.java)
        startActivity(helpIntent)
    }

    // Assigns the appropriate layout to the this activity based upon user preference
    private fun userSettings() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val switchLayout = prefs.getBoolean("layoutSwap",false)
        if(switchLayout)
        {
            setContentView(R.layout.activity_alt_main)
            activeLayout = 1
        }
        else if(!switchLayout)
        {
            setContentView(R.layout.activity_main)
            activeLayout = 0
        }
    }
}
