package com.example.weather

class CityBuiltIn {
    companion object {
        fun getCities() : List<String> =
            listOf(
                "Samara",
                "Togliati",
                "Moscow",
                "Владивосток"
            )
        fun getDefaultCity() : String = getCities().find { city -> city.equals("Moscow") } ?: "Moscow"
        fun getLondon() : String = "London"
    }
}