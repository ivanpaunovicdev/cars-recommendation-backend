package com.ivan.carsrecommendation.controller

import org.springframework.data.neo4j.core.Neo4jClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/models")
class ModelController(
    private val neo4jClient: Neo4jClient
) {

    @GetMapping
    fun getAllModels(): List<String> {
        return neo4jClient.query("""
            MATCH (a:Auto)
            WHERE a.model IS NOT NULL
            RETURN DISTINCT a.model AS model
            ORDER BY model ASC
        """.trimIndent())
            .fetchAs(String::class.java)
            .mappedBy { _, record ->
                record["model"].asString()
            }
            .all()
            .toList()
    }
}