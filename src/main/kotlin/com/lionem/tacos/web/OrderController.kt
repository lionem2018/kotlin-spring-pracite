package com.lionem.tacos.web

import com.lionem.tacos.Order
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Slf4j
@Controller
@RequestMapping("/orders")
class OrderController {

    private val log = LoggerFactory.getLogger(OrderController::class.java)

    @GetMapping("/current")
    fun orderForm(model: Model): String {
        model.addAttribute("order", Order())
        return "orderForm"
    }

    @PostMapping
    fun processOrder(@Valid order: Order, errors: Errors): String {
        if (errors.hasErrors()) {
            return "orderForm"
        }

        log.info("Order submitted: $order")
        return "redirect:/"
    }
}