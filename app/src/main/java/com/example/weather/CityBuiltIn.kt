package com.example.weather

class CityBuiltIn {
    companion object {
        fun getCities() : List<String> =
            listOf(
                "Samara",
                "Togliatti",
                "Moscow",
                "Владивосток"
            )
        fun getDefaultCity() : String = getCities().find { city -> city.equals("Togliatti") } ?: "Togliatti"
        fun getLondon() : String = "London"
    }
}