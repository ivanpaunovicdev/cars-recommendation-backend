package com.ivan.carsrecommendation.model

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("FilozofijaVoznje")
data class DrivingPhilosophy(

    @Id
    val id: String? = null,

    val naziv: String,
    val opis: String
)