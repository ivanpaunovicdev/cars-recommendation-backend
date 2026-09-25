package com.ivan.carsrecommendation.model

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("Karoserija")
data class BodyType(
    @Id
    val naziv: String
)
