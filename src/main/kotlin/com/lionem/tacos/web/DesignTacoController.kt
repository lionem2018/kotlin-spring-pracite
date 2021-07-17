package com.lionem.tacos.web

import com.lionem.tacos.Ingredient
import com.lionem.tacos.Taco
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid
import kotlin.streams.toList

@Slf4j
@Controller
@RequestMapping("/design")
class DesignTacoController {

    private val log = LoggerFactory.getLogger(DesignTacoController::class.java)

    @GetMapping
    fun showDesignFrom(model: Model): String {
        val ingredients = listOf<Ingredient>(
            Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
            Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
            Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
            Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
            Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
            Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
            Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
            Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
            Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
            Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE),
            )

        val types = Ingredient.Type.values()
        for (type in types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type))
        }

        model.addAttribute("taco", Taco())

        return "design"
    }

    @PostMapping
    fun processDesign(@Valid design: Taco, errors: Errors): String {
        if (errors.hasErrors()) {
            return "design"
        }

        // 이 지점에서 타코 디자인(선택된 식자재 내역)을 저장한다.
        // 이 작업은 3장에서 할 것임
        log.info("Processing design: $design")

        return "redirect:/orders/current"
    }

    private fun filterByType (
        ingredients: List<Ingredient>, type: Ingredient.Type
    ): List<Ingredient> {
        return ingredients.stream()
            .filter { it.type == type }
            .toList()
    }
}