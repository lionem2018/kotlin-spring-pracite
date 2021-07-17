package com.lionem.tacos

data class Ingredient (
    val id: String = "",
    val name: String = "",
    val type: Type = Type.WRAP,
) {

    enum class Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}