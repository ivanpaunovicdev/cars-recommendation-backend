package com.ivan.carsrecommendation.controller

import com.ivan.carsrecommendation.model.Transmission
import com.ivan.carsrecommendation.repository.TransmissionRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transmissions")
class TransmissionController(
    private val transmissionRepository: TransmissionRepository)
{
    @GetMapping
    fun getAllTransmissions(): List<Transmission> {
        return transmissionRepository.findAll()
    }
}