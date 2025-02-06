package com.example.weatherappprojects.dependency_injection

import com.example.weatherappprojects.network.repository.WeatherDataRepository
import org.koin.dsl.module
val repositoryModule = module{
    single{WeatherDataRepository(weatherApi = get())}
}