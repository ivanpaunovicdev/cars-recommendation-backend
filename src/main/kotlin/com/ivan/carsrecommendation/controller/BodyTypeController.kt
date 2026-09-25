package com.ivan.carsrecommendation.controller

import com.ivan.carsrecommendation.model.BodyType
import com.ivan.carsrecommendation.repository.BodyTypeRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bodytypes")
class BodyTypeController(
    private val bodyTypeRepository: BodyTypeRepository
) {
    @GetMapping
    fun getAllBodyTypes(): List<BodyType> {
        return bodyTypeRepository.findAll()
    }
}