package com.ivan.carsrecommendation.repository

import com.ivan.carsrecommendation.model.DailyRoute
import org.springframework.data.neo4j.repository.Neo4jRepository

interface DailyRouteRepository : Neo4jRepository<DailyRoute, String>