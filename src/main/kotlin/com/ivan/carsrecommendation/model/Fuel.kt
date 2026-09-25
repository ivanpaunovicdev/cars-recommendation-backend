package com.ivan.carsrecommendation.model

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("Gorivo")
data class Fuel(
    @Id
    val id: String ?= null,
    val naziv: String
)
