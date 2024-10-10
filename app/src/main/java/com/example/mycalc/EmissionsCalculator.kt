package com.example.mycalc

class EmissionsCalculator {

    fun calculateEmissions(fuels: List<Fuel>, operatingTime: Double): Map<String, Double> {
        val emissions = mutableMapOf<String, Double>()

        for (fuel in fuels) {
            val emission = when (fuel.type) {
                "coal" -> calculateCoalEmissions(fuel, operatingTime)
                "oil" -> calculateOilEmissions(fuel, operatingTime)
                "gas" -> calculateGasEmissions(fuel)
                else -> 0.0
            }
            emissions[fuel.type] = emission
        }

        return emissions
    }

    private fun calculateCoalEmissions(fuel: Fuel, operatingTime: Double): Double {
        val emissionsPerGJ = 150.0 // г/ГДж
        val totalEnergyOutput = fuel.quantity * fuel.lowerHeatingValue / 1000 // МДж -> ГДж
        // Calculate total emissions in grams and convert to tons
        return (emissionsPerGJ * totalEnergyOutput) / 1000 // т.
    }

    private fun calculateOilEmissions(fuel: Fuel, operatingTime: Double): Double {
        val emissionsPerGJ = 0.57 // г/ГДж
        val totalEnergyOutput = fuel.quantity * fuel.lowerHeatingValue / 1000 // МДж -> ГДж
        // Calculate total emissions in grams and convert to tons
        return (emissionsPerGJ * totalEnergyOutput) / 1000 // т.
    }

    private fun calculateGasEmissions(fuel: Fuel): Double {
        // For natural gas, emissions of solid particles are zero
        return 0.0 // т.
    }
}
