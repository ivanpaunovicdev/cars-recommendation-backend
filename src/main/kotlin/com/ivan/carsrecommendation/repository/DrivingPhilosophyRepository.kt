package com.ivan.carsrecommendation.repository

import com.ivan.carsrecommendation.model.DrivingPhilosophy
import org.springframework.data.neo4j.repository.Neo4jRepository

interface DrivingPhilosophyRepository : Neo4jRepository<DrivingPhilosophy, String>