package com.ivan.carsrecommendation.model

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("Pogon")
data class DriveType(
    @Id
    val naziv: String
)
