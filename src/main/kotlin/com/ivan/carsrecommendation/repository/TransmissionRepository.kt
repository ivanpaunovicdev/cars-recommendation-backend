package com.ivan.carsrecommendation.repository
import com.ivan.carsrecommendation.model.Transmission
import org.springframework.data.neo4j.repository.Neo4jRepository
interface TransmissionRepository : Neo4jRepository<Transmission, String>