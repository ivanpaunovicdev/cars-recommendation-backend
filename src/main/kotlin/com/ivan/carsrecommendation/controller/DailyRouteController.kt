package com.ivan.carsrecommendation.controller

import com.ivan.carsrecommendation.model.DailyRoute
import com.ivan.carsrecommendation.repository.DailyRouteRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dailyroutes")
class DailyRouteController(
    private val dailyRouteRepository: DailyRouteRepository
) {
    @GetMapping
    fun getAllDailyRoutes(): List<DailyRoute> {
        return dailyRouteRepository.findAll()
    }
}