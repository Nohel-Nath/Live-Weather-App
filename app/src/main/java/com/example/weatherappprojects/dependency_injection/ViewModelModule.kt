package com.example.weatherappprojects.dependency_injection

import com.example.weatherappprojects.Fragment.Home.HomeViewModel
import com.example.weatherappprojects.Fragment.Location.LocationViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val viewModelModule = module {
    viewModel { HomeViewModel(weatherDataRepository = get()) }
    viewModel { LocationViewModel(weatherDataRepository = get()) }
}