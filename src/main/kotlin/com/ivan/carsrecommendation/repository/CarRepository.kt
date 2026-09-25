package com.ivan.carsrecommendation.repository

import com.ivan.carsrecommendation.model.Car
import org.springframework.data.neo4j.repository.Neo4jRepository

interface CarRepository : Neo4jRepository<Car, String>