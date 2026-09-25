package com.ivan.carsrecommendation.model

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("Auto")
data class Car(

    @Id
    val model: String,

    val marka: String? = null,
    val cena: Int? = null,
    val godiste: Int? = null,
    val kilometraza: Int? = null,
    val kubikaza: Int? = null,
    val snagaKS: Int? = null,
    val snagaKw: Int? = null,
    val klima: String? = null,
    val stanje: String? = null,
    val poreklo: String? = null,
    val brojSedista: String? = null,
    val brojVrata: String? = null,
    val ostecenje: String? = null,
    val valuta: String? = null,

    val gorivo: String? = null,
    val karoserija: String? = null,
    val menjac: String? = null,
    val pogon: String? = null,
    val skor: Int? = null
)