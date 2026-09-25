package com.ivan.carsrecommendation.repository

import com.ivan.carsrecommendation.model.BodyType
import org.springframework.data.neo4j.repository.Neo4jRepository

interface BodyTypeRepository : Neo4jRepository<BodyType, String>