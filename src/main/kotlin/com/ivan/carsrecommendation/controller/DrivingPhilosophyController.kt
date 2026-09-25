package com.ivan.carsrecommendation.controller

import com.ivan.carsrecommendation.model.DrivingPhilosophy
import com.ivan.carsrecommendation.repository.DrivingPhilosophyRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/drivingphilosophies")
class DrivingPhilosophyController(
    private val drivingPhilosophyRepository: DrivingPhilosophyRepository
) {
    @GetMapping
    fun getAllDrivingPhilosophies(): List<DrivingPhilosophy> {
        return drivingPhilosophyRepository.findAll()
    }
}