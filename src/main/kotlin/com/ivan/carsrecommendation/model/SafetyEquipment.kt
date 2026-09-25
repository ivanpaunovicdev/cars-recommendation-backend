package com.ivan.carsrecommendation.model

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("SigurnosnaOprema")
data class SafetyEquipment(
    @Id
    val naziv: String
)
