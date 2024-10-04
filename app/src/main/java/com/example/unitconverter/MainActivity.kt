package com.example.unitconverter

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // References to UI elements
        val spinnerFrom: Spinner = findViewById(R.id.spinnerFrom)
        val spinnerTo: Spinner = findViewById(R.id.spinnerTo)
        val editTextValue: EditText = findViewById(R.id.editTextValue)
        val btnConvert: Button = findViewById(R.id.btnConvert)
        val textViewResult: TextView = findViewById(R.id.textViewResult)

        // Sample conversion units array
        val unitsArray = resources.getStringArray(R.array.units_array)

        // Set onClickListener for Convert button
        btnConvert.setOnClickListener {
            val inputValue = editTextValue.text.toString()

            // Ensure that a valid input is provided
            if (inputValue.isNotEmpty()) {
                val fromUnit = spinnerFrom.selectedItem.toString()
                val toUnit = spinnerTo.selectedItem.toString()
                val inputDouble = inputValue.toDouble()

                // Perform conversion based on units
                val result = convertUnits(fromUnit, toUnit, inputDouble)

                // Display the result
                textViewResult.text = "Result: $result"
            } else {
                // Handle empty input
                Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Conversion logic handling multiple types (length, weight, temperature, volume)
    private fun convertUnits(fromUnit: String, toUnit: String, value: Double): Double {
        return when {
            // Length Conversions
            fromUnit == "Meters" && toUnit == "Kilometers" -> value / 1000
            fromUnit == "Kilometers" && toUnit == "Meters" -> value * 1000
            fromUnit == "Meters" && toUnit == "Miles" -> value / 1609.34
            fromUnit == "Miles" && toUnit == "Meters" -> value * 1609.34

            // Weight Conversions
            fromUnit == "Grams" && toUnit == "Kilograms" -> value / 1000
            fromUnit == "Kilograms" && toUnit == "Grams" -> value * 1000
            fromUnit == "Pounds" && toUnit == "Kilograms" -> value * 0.453592
            fromUnit == "Kilograms" && toUnit == "Pounds" -> value / 0.453592

            // Temperature Conversions
            fromUnit == "Celsius" && toUnit == "Fahrenheit" -> (value * 9/5) + 32
            fromUnit == "Fahrenheit" && toUnit == "Celsius" -> (value - 32) * 5/9
            fromUnit == "Celsius" && toUnit == "Kelvin" -> value + 273.15
            fromUnit == "Kelvin" && toUnit == "Celsius" -> value - 273.15

            // Volume Conversions
            fromUnit == "Liters" && toUnit == "Milliliters" -> value * 1000
            fromUnit == "Milliliters" && toUnit == "Liters" -> value / 1000
            fromUnit == "Liters" && toUnit == "Cubic Meters" -> value / 1000
            fromUnit == "Cubic Meters" && toUnit == "Liters" -> value * 1000

            // Add more conversions as needed...
            else -> value // Return the same value if no conversion is needed
        }
    }
}
