package com.lionem.tacos

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class Taco (
    @NotNull
    @get:Size(min=5, message="Name must be at least 5 characters long")
    val name: String = "",
    @get:Size(min=1, message="You must choose at least 1 ingredient")
    val ingredients: List<String> = emptyList()
)