package com.example.mycalc

data class Fuel(
    val type: String,
    val quantity: Double, // в тоннах для вугілля та мазуту, кубометри для газу
    val carbonContent: Double, // у %
    val hydrogenContent: Double, // у %
    val sulfurContent: Double, // у %
    val lowerHeatingValue: Double // МДж/кг для вугілля та мазуту, МДж/м³ для газу
)
