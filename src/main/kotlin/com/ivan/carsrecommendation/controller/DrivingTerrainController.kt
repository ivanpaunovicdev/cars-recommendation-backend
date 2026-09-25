package com.ivan.carsrecommendation.controller

import com.ivan.carsrecommendation.model.DrivingTerrain
import com.ivan.carsrecommendation.repository.DrivingTerrainRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/drivingterrains")
class DrivingTerrainController(
    private val drivingTerrainRepository: DrivingTerrainRepository
) {
    @GetMapping
    fun getAllDrivingTerrains(): List<DrivingTerrain> {
        return drivingTerrainRepository.findAll()
    }
}