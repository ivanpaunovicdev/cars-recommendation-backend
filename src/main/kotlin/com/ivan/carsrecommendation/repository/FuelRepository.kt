package com.ivan.carsrecommendation.repository

import com.ivan.carsrecommendation.model.Fuel
import org.springframework.data.neo4j.repository.Neo4jRepository

interface FuelRepository : Neo4jRepository<Fuel, String>