package com.ivan.carsrecommendation.controller

import com.ivan.carsrecommendation.model.Recommendation
import org.springframework.data.neo4j.core.Neo4jClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/recommendations")
class RecommendationController(
    private val neo4jClient: Neo4jClient
) {

    @GetMapping
    fun getRecommendations(
        @RequestParam(defaultValue = "0")
        budgetMin: Int,

        @RequestParam(defaultValue = "100000")
        budgetMax: Int,

        @RequestParam(defaultValue = "0")
        minYear: Int,

        @RequestParam(defaultValue = "2147483647")
        maxMileage: Int,

        @RequestParam(defaultValue = "")
        brand: List<String>,

        @RequestParam(defaultValue = "")
        model: String,

        @RequestParam(defaultValue = "")
        fuel: List<String>,

        @RequestParam(defaultValue = "")
        bodyType: List<String>,

        @RequestParam(defaultValue = "")
        transmission: String,

        @RequestParam(defaultValue = "")
        driveType: String,

        @RequestParam(defaultValue = "")
        dailyRoute: String,

        @RequestParam(defaultValue = "")
        drivingTerrain: String,

        @RequestParam(defaultValue = "")
        drivingPhilosophy: String
    ): List<Recommendation> {

        val brands = brand.filter { it.isNotBlank() }
        val fuels = fuel.filter { it.isNotBlank() }
        val bodyTypes = bodyType.filter { it.isNotBlank() }


        val query = """
            MATCH (a:Auto)-[:IMA_KAROSERIJU]->(kar:Karoserija)
            MATCH (a)-[:KORISTI_GORIVO]->(g:Gorivo)
            MATCH (a)-[:IMA_MENJAC]->(m:Menjac)
            MATCH (a)-[:IMA_POGON]->(p:Pogon)

            WHERE a.cena >= ${'$'}budgetMin
              AND a.cena <= ${'$'}budgetMax
              AND a.godiste >= ${'$'}minYear
              AND a.kilometraza <= ${'$'}maxMileage

              AND (
                  size(${'$'}brands) = 0
                  OR a.marka IN ${'$'}brands
              )

              AND (
                  ${'$'}model = ''
                  OR a.model = ${'$'}model
              )

              AND (
                  size(${'$'}fuels) = 0
                  OR g.naziv IN ${'$'}fuels
              )

              AND (
                  size(${'$'}bodyTypes) = 0
                  OR kar.naziv IN ${'$'}bodyTypes
              )

              AND (
                  ${'$'}transmission = ''
                  OR m.naziv = ${'$'}transmission
              )

              AND (
                  ${'$'}driveType = ''
                  OR p.naziv = ${'$'}driveType
              )

            RETURN
                id(a) AS id,
                a.marka AS marka,
                a.model AS model,
                a.godiste AS godiste,
                a.kilometraza AS kilometraza,
                a.cena AS cena,
                g.naziv AS gorivo,
                kar.naziv AS karoserija,
                m.naziv AS menjac,
                p.naziv AS pogon,

                (
                    CASE
                        WHEN size(${'$'}brands) > 0
                             AND a.marka IN ${'$'}brands
                        THEN 2
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}model <> ''
                             AND a.model = ${'$'}model
                        THEN 3
                        ELSE 0
                    END +

                    CASE
                        WHEN size(${'$'}fuels) > 0
                             AND g.naziv IN ${'$'}fuels
                        THEN 3
                        ELSE 0
                    END +

                    CASE
                        WHEN size(${'$'}bodyTypes) > 0
                             AND kar.naziv IN ${'$'}bodyTypes
                        THEN 2
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}transmission <> ''
                             AND m.naziv = ${'$'}transmission
                        THEN 2
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}driveType <> ''
                             AND p.naziv = ${'$'}driveType
                        THEN 1
                        ELSE 0
                    END +

                    // Dnevna ruta

                    CASE
                        WHEN ${'$'}dailyRoute = 'Kratka'
                             AND g.naziv IN [
                                 'Benzin',
                                 'Hibrid',
                                 'Električni'
                             ]
                        THEN 3
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}dailyRoute = 'Kratka'
                             AND a.kubikaza <= 1600
                        THEN 1
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}dailyRoute = 'Umerena'
                             AND g.naziv IN [
                                 'Benzin',
                                 'Hibrid',
                                 'Dizel'
                             ]
                        THEN 2
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}dailyRoute = 'Duga'
                             AND g.naziv = 'Dizel'
                        THEN 3
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}dailyRoute = 'Veoma duga'
                             AND g.naziv = 'Dizel'
                        THEN 4
                        ELSE 0
                    END +

                    // Teren vožnje

                    CASE
                        WHEN ${'$'}drivingTerrain =
                             'Grad i urbane sredine'
                             AND kar.naziv IN [
                                 'Hečbek',
                                 'Limuzina'
                             ]
                        THEN 3
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingTerrain =
                             'Grad i urbane sredine'
                             AND a.kubikaza <= 1600
                        THEN 1
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingTerrain =
                             'Autoput i predgrađa'
                             AND a.snagaKS >= 150
                        THEN 3
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingTerrain =
                             'Autoput i predgrađa'
                             AND m.naziv =
                             'Automatski / poluautomatski'
                        THEN 2
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingTerrain =
                             'Off-road i makadam'
                             AND kar.naziv = 'SUV'
                        THEN 3
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingTerrain =
                             'Off-road i makadam'
                             AND p.naziv = '4x4'
                        THEN 3
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingTerrain =
                             'Planinski i snežni'
                             AND p.naziv = '4x4'
                        THEN 4
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingTerrain =
                             'Planinski i snežni'
                             AND a.snagaKS >= 150
                        THEN 2
                        ELSE 0
                    END +

                    // Filozofija vožnje

                    CASE
                        WHEN ${'$'}drivingPhilosophy =
                             'Eko / Pametna efikasnost'
                             AND g.naziv IN [
                                 'Hibrid',
                                 'Električni'
                             ]
                        THEN 5
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingPhilosophy =
                             'Eko / Pametna efikasnost'
                             AND a.kubikaza <= 1600
                        THEN 2
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingPhilosophy =
                             'Performans / Sportska'
                             AND a.snagaKS >= 200
                        THEN 5
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingPhilosophy =
                             'Performans / Sportska'
                             AND p.naziv = 'Zadnji'
                        THEN 2
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingPhilosophy =
                             'Performans / Sportska'
                             AND kar.naziv = 'Kupe'
                        THEN 2
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingPhilosophy =
                             'Opuštena / Komfor'
                             AND m.naziv =
                             'Automatski / poluautomatski'
                        THEN 4
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingPhilosophy =
                             'Opuštena / Komfor'
                             AND kar.naziv IN [
                                 'Limuzina',
                                 'Karavan',
                                 'Monovolumen'
                             ]
                        THEN 2
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingPhilosophy =
                             'Robusna / Teret'
                             AND kar.naziv IN [
                                 'SUV',
                                 'Karavan',
                                 'Monovolumen'
                             ]
                        THEN 3
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingPhilosophy =
                             'Robusna / Teret'
                             AND p.naziv = '4x4'
                        THEN 3
                        ELSE 0
                    END +

                    CASE
                        WHEN ${'$'}drivingPhilosophy =
                             'Robusna / Teret'
                             AND a.kubikaza >= 2000
                        THEN 2
                        ELSE 0
                    END
                ) AS skor

            ORDER BY skor DESC, cena ASC
            LIMIT 10
        """.trimIndent()

        return neo4jClient.query(query)
            .bind(budgetMin).to("budgetMin")
            .bind(budgetMax).to("budgetMax")
            .bind(minYear).to("minYear")
            .bind(maxMileage).to("maxMileage")
            .bind(brands).to("brands")
            .bind(model).to("model")
            .bind(fuels).to("fuels")
            .bind(bodyTypes).to("bodyTypes")
            .bind(transmission).to("transmission")
            .bind(driveType).to("driveType")
            .bind(dailyRoute).to("dailyRoute")
            .bind(drivingTerrain).to("drivingTerrain")
            .bind(drivingPhilosophy).to("drivingPhilosophy")
            .fetchAs(Recommendation::class.java)
            .mappedBy { _, record ->
                Recommendation(
                    id = record["id"].asLong(),
                    marka = record["marka"].asString(),
                    model = record["model"].asString(),
                    godiste = record["godiste"].asInt(),
                    kilometraza = record["kilometraza"].asInt(),
                    cena = record["cena"].asInt(),
                    gorivo = record["gorivo"].asString(),
                    karoserija = record["karoserija"].asString(),
                    menjac = record["menjac"].asString(),
                    pogon = record["pogon"].asString(),
                    skor = record["skor"].asInt()
                )
            }
            .all()
            .toList()
    }
}