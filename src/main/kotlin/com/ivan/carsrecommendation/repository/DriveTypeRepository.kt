package com.ivan.carsrecommendation.repository

import com.ivan.carsrecommendation.model.DriveType
import org.springframework.data.neo4j.repository.Neo4jRepository

interface DriveTypeRepository : Neo4jRepository<DriveType, String>