package com.ivan.carsrecommendation.controller


import com.ivan.carsrecommendation.model.Fuel
import com.ivan.carsrecommendation.model.SafetyEquipment
import com.ivan.carsrecommendation.repository.FuelRepository
import com.ivan.carsrecommendation.repository.SafetyEquipmentRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/safetyequipments")
class SafetyEquipmentController(
    private val safetyEquipmentRepository: SafetyEquipmentRepository
) {
    @GetMapping
    fun getAllSafetyEquipments(): List<SafetyEquipment> {
        return safetyEquipmentRepository.findAll()
    }
}