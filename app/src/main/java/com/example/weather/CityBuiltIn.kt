package com.example.weather

class CityBuiltIn {
    companion object {
        fun getCities() : List<String> =
            listOf(
                "Самара",
                "Тольятти",
                "Москва",
                "Владивосток"
            )
        fun getDefaultCity() : String = getCities().find { it == "Тольятти" } ?: "Тольятти"
//        fun getLondon() : String = "London"
    }
}