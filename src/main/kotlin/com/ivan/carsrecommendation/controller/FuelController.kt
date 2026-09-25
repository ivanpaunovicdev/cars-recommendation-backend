package com.ivan.carsrecommendation.controller


import com.ivan.carsrecommendation.model.Fuel
import com.ivan.carsrecommendation.repository.FuelRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fuels")
class FuelController(
    private val fuelRepository: FuelRepository
) {
    @GetMapping
    fun getAllFuels(): List<Fuel> {
        return fuelRepository.findAll()
    }
}