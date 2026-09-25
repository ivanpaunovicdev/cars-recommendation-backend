//package com.ivan.carsrecommendation
//
//import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.boot.runApplication
//
//@SpringBootApplication
//class CarsRecommendationApplication
//
//fun main(args: Array<String>) {
//    runApplication<CarsRecommendationApplication>(*args)
//}
package com.ivan.carsrecommendation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class CarsRecommendationApplication {

    @Bean
    fun runRecommendationTests(): CommandLineRunner {
        return CommandLineRunner {
            val restTemplate = RestTemplate()

            val tests = listOf(
                "http://localhost:8080/recommendations?brand=Audi&budgetMax=15000",
                "http://localhost:8080/recommendations?budgetMin=5000&budgetMax=15000&bodyType=He%C4%8Dbek",
                "http://localhost:8080/recommendations?fuel=Dizel&transmission=Automatski%20/%20poluautomatski",
                "http://localhost:8080/recommendations?driveType=Prednji&fuel=Benzin",
                "http://localhost:8080/recommendations?brand=BMW&budgetMin=8000&budgetMax=20000",
                "http://localhost:8080/recommendations?model=A4&budgetMax=18000",
                "http://localhost:8080/recommendations?bodyType=Karavan&maxMileage=180000",
                "http://localhost:8080/recommendations?minYear=2015&fuel=Dizel",
                "http://localhost:8080/recommendations?brand=Volkswagen&model=Golf%207",
                "http://localhost:8080/recommendations?budgetMin=10000&budgetMax=25000&driveType=Zadnji",
                "http://localhost:8080/recommendations?brand=Mercedes-Benz&transmission=Automatski%20/%20poluautomatski",
                "http://localhost:8080/recommendations?fuel=Benzin&bodyType=Limuzina",
                "http://localhost:8080/recommendations?budgetMax=10000&maxMileage=150000",
                "http://localhost:8080/recommendations?brand=Audi&fuel=Dizel&bodyType=Limuzina",
                "http://localhost:8080/recommendations?budgetMin=7000&budgetMax=17000&fuel=Dizel&bodyType=He%C4%8Dbek&driveType=Prednji"
            )

            tests.forEachIndexed { index, url ->
                println("========== TEST ${index + 1} ==========")
                println("URL: $url")

                val response = restTemplate.getForObject(url, String::class.java)

                println("RESULT:")
                println(response)
                println()
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CarsRecommendationApplication>(*args)
}