package com.example.weatherappprojects.dependency_injection

import com.example.weatherappprojects.storage.SharedPreferenceManager
import com.google.gson.Gson
import org.koin.dsl.module

val storageModule = module{
    single{ SharedPreferenceManager(context = get(), gson = get()) }
}