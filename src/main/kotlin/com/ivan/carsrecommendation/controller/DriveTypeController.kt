package com.ivan.carsrecommendation.controller


import com.ivan.carsrecommendation.model.DriveType
import com.ivan.carsrecommendation.model.Fuel
import com.ivan.carsrecommendation.repository.DriveTypeRepository
import com.ivan.carsrecommendation.repository.FuelRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/drivetypes")
class DriveTypeController(
    private val driveTypeRepository: DriveTypeRepository
) {
    @GetMapping
    fun getAllDriveTypes(): List<DriveType> {
        return driveTypeRepository.findAll()
    }
}