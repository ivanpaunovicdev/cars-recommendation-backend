package com.ivan.carsrecommendation.model

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("TerenVoznje")
data class DrivingTerrain(

    @Id
    val id: String? = null,

    val naziv: String,
    val opis: String
)