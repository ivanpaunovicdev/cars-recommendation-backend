package com.ivan.carsrecommendation.repository

import com.ivan.carsrecommendation.model.Brand
import org.springframework.data.neo4j.repository.Neo4jRepository

interface BrandRepository : Neo4jRepository<Brand, String>