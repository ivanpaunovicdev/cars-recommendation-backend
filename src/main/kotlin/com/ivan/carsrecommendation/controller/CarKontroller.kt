package com.ivan.carsrecommendation.controller

import com.ivan.carsrecommendation.model.Car
import com.ivan.carsrecommendation.repository.CarRepository
import org.springframework.data.neo4j.core.Neo4jClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cars")
class CarController(
    private val carRepository: CarRepository,
    private val neo4jClient: Neo4jClient
) {

    @GetMapping
    fun getAllCars(): List<Car> {
        return carRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getCarById(
        @PathVariable id: Long
    ): Car? {

        val query = """
    MATCH (a:Auto)-[:IMA_KAROSERIJU]->(kar:Karoserija)
    MATCH (a)-[:KORISTI_GORIVO]->(g:Gorivo)
    MATCH (a)-[:IMA_MENJAC]->(m:Menjac)
    MATCH (a)-[:IMA_POGON]->(p:Pogon)

    WHERE id(a) = $id

    RETURN
        a.marka AS marka,
        a.model AS model,
        a.cena AS cena,
        a.godiste AS godiste,
        a.kilometraza AS kilometraza,
        a.kubikaza AS kubikaza,
        a.snagaKS AS snagaKS,
        a.snagaKw AS snagaKw,
        a.klima AS klima,
        a.stanje AS stanje,
        a.poreklo AS poreklo,
        a.brojSedista AS brojSedista,
        a.brojVrata AS brojVrata,
        a.ostecenje AS ostecenje,
        a.valuta AS valuta,
        g.naziv AS gorivo,
        kar.naziv AS karoserija,
        m.naziv AS menjac,
        p.naziv AS pogon
""".trimIndent()

        return neo4jClient.query(query)
            .fetchAs(Car::class.java)
            .mappedBy { _, record ->

                Car(
                    model = record["model"].asString(),

                    marka = record["marka"]
                        .takeIf { !it.isNull }
                        ?.asString(),

                    cena = record["cena"]
                        .takeIf { !it.isNull }
                        ?.asInt(),

                    godiste = record["godiste"]
                        .takeIf { !it.isNull }
                        ?.asInt(),

                    kilometraza = record["kilometraza"]
                        .takeIf { !it.isNull }
                        ?.asInt(),

                    kubikaza = record["kubikaza"]
                        .takeIf { !it.isNull }
                        ?.asInt(),

                    snagaKS = record["snagaKS"]
                        .takeIf { !it.isNull }
                        ?.asInt(),

                    snagaKw = record["snagaKw"]
                        .takeIf { !it.isNull }
                        ?.asInt(),

                    klima = record["klima"]
                        .takeIf { !it.isNull }
                        ?.asString(),

                    stanje = record["stanje"]
                        .takeIf { !it.isNull }
                        ?.asString(),

                    poreklo = record["poreklo"]
                        .takeIf { !it.isNull }
                        ?.asString(),

                    brojSedista = record["brojSedista"]
                        .takeIf { !it.isNull }
                        ?.asString(),

                    brojVrata = record["brojVrata"]
                        .takeIf { !it.isNull }
                        ?.asString(),

                    ostecenje = record["ostecenje"]
                        .takeIf { !it.isNull }
                        ?.asString(),

                    valuta = record["valuta"]
                        .takeIf { !it.isNull }
                        ?.asString(),
                    gorivo = record["gorivo"]
                        .takeIf { !it.isNull }
                        ?.asString(),

                    karoserija = record["karoserija"]
                        .takeIf { !it.isNull }
                        ?.asString(),

                    menjac = record["menjac"]
                        .takeIf { !it.isNull }
                        ?.asString(),

                    pogon = record["pogon"]
                        .takeIf { !it.isNull }
                        ?.asString(),

                    skor = 0
                )
            }
            .one()
            .orElse(null)
    }
}