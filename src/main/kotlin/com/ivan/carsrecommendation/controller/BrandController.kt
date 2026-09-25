package com.ivan.carsrecommendation.controller

import com.ivan.carsrecommendation.model.Brand
import com.ivan.carsrecommendation.repository.BrandRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/brands")
class BrandController(
    private val brandRepository: BrandRepository
) {
    @GetMapping
    fun getAllBrand(): List<Brand> {
        return brandRepository.findAll()
    }
}