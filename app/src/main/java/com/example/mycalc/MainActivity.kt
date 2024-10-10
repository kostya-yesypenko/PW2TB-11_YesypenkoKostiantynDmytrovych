package com.example.mycalc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var emissionsCalculator: EmissionsCalculator
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emissionsCalculator = EmissionsCalculator()

        resultTextView = findViewById(R.id.resultTextView)
        val calculateButton: Button = findViewById(R.id.calculateButton)

        calculateButton.setOnClickListener { onCalculateButtonClick() }
    }

    private fun onCalculateButtonClick() {
        // Get input values from EditText fields
        val coalQuantityInput: EditText = findViewById(R.id.coalQuantityInput)
        val oilQuantityInput: EditText = findViewById(R.id.oilQuantityInput)
        val gasQuantityInput: EditText = findViewById(R.id.gasQuantityInput)

        // Convert user input to Double and handle potential errors
        val coalQuantity = coalQuantityInput.text.toString().toDoubleOrNull() ?: 0.0
        val oilQuantity = oilQuantityInput.text.toString().toDoubleOrNull() ?: 0.0
        val gasQuantity = gasQuantityInput.text.toString().toDoubleOrNull() ?: 0.0

        // Fuels list based on user input
        val fuels = listOf(
            Fuel("coal", coalQuantity, 81.0, 5.4, 4.4, 31.98),
            Fuel("oil", oilQuantity, 85.5, 11.2, 2.5, 40.40),
            Fuel("gas", gasQuantity, 98.9, 0.12, 0.0, 33.08)
        )

        // Calculate emissions
        val emissions = emissionsCalculator.calculateEmissions(fuels, 1.0)
        displayResults(emissions)
    }


    private fun displayResults(emissions: Map<String, Double>) {
        val results = StringBuilder()

        results.append("1.1. Показник емісії твердих частинок при спалюванні вугілля становитиме: 150 г/ГДж;\n")
        results.append("1.2. Валовий викид при спалюванні вугілля становитиме: ${emissions["coal"]?.format(2)} т.;\n")
        results.append("1.3. Показник емісії твердих частинок при спалюванні мазуту становитиме: 0,57 г/ГДж;\n")
        results.append("1.4. Валовий викид при спалюванні мазуту становитиме: ${emissions["oil"]?.format(2)} т.;\n")
        results.append("1.5. Показник емісії твердих частинок при спалюванні природного газу становитиме: 0 г/ГДж;\n")
        results.append("1.6. Валовий викид при спалюванні природного газу становитиме: ${emissions["gas"]?.format(2)} т.\n")

        resultTextView.text = results.toString()
    }


    private fun Double.format(digits: Int) = "%.${digits}f".format(this)
}
