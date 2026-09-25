package com.ivan.carsrecommendation.repository

import com.ivan.carsrecommendation.model.DrivingTerrain
import org.springframework.data.neo4j.repository.Neo4jRepository

interface DrivingTerrainRepository : Neo4jRepository<DrivingTerrain, String>