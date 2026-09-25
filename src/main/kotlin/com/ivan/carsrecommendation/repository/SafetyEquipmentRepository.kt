package com.ivan.carsrecommendation.repository
import com.ivan.carsrecommendation.model.SafetyEquipment
import org.springframework.data.neo4j.repository.Neo4jRepository
interface SafetyEquipmentRepository : Neo4jRepository<SafetyEquipment, String>