package com.trestan.openspaceweatherdashboard.api

import com.trestan.openspaceweatherdashboard.commons.GOESXRay
import com.trestan.openspaceweatherdashboard.commons.SolarProbabilities
import com.trestan.openspaceweatherdashboard.commons.SolarRegions
import retrofit2.Call
import retrofit2.http.GET

interface GOESXRayAPI {
    @GET("json/goes/primary/xrays-6-hour.json")
    fun getData(): Call<List<GOESXRay>>
}

interface SolarRegionAPI {
    @GET("json/solar_regions.json")
    fun getData(): Call<List<SolarRegions>>
}

interface SolarProbabilitiesAPI {
    @GET("json/solar_probabilities.json")
    fun getData(): Call<List<SolarProbabilities>>
}