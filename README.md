# Car Recommendation System - Backend

> 🚧 **Work in Progress** — This backend is currently under active development.

## About the Project

This backend application was developed as part of my Master's project in Computer Engineering and is currently being further developed and improved. It provides the server-side logic for a personalized vehicle recommendation system based on user preferences, budget, vehicle specifications, and driving habits.

The backend exposes a REST API used by the Android application and uses a Neo4j graph database to model vehicles, their characteristics, and user preferences in order to generate personalized recommendations.

## Tech Stack

- Kotlin
- Spring Boot
- Spring Data Neo4j
- Neo4j
- REST API
- Gradle

## Features

- Personalized vehicle recommendations based on multiple user preferences
- Filtering by brand, model, fuel type, body type, transmission, and drive type
- Budget, production year, and mileage filtering
- Driving habit preferences including daily route, terrain, and driving philosophy
- Graph-based vehicle and preference modeling with Neo4j
- REST API integration with the Android client

## API Endpoints

The backend provides REST endpoints for retrieving vehicle data and generating personalized recommendations.

### Recommendations

`GET /recommendations`

Supports filtering by:

- Minimum and maximum budget
- Minimum production year
- Maximum mileage
- Brand and model
- Fuel type
- Body type
- Transmission
- Drive type

### Additional Endpoints

- `GET /brands` — Retrieves available vehicle brands
- `GET /models` — Retrieves available vehicle models
- `GET /fuels` — Retrieves available fuel types
- `GET /body-types` — Retrieves available body types
- `GET /transmissions` — Retrieves available transmission types
- `GET /drive-types` — Retrieves available drive types
- `GET /daily-routes` — Retrieves available daily route options
- `GET /driving-terrain` — Retrieves available driving terrain options
- `GET /driving-philosophy` — Retrieves available driving philosophy options
- `GET /safety-equipment` — Retrieves available safety equipment options

## Database

The application uses Neo4j as a graph database for storing and connecting vehicle data, user preferences, and recommendation-related entities.

The graph model includes entities such as:

- Car
- Brand
- Fuel
- Body Type
- Transmission
- Drive Type
- Safety Equipment
- Daily Route
- Driving Terrain
- Driving Philosophy

Relationships between these entities are used to model vehicle characteristics and user preferences, enabling the recommendation system to generate personalized results.

## Android Application

This repository contains the backend of the Car Recommendation System.

The Android client is available in a separate repository:

[cars-recommendation-android](https://github.com/ivanpaunovicdev/cars-recommendation-android)

## Project Status

🚧 This project is currently under active development.

The core recommendation system, Neo4j integration, REST API, and Android client integration are implemented. Further improvements, testing, and refinements are planned as development continues.
