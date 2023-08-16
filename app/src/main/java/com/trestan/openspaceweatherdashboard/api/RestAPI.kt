package com.trestan.openspaceweatherdashboard.api

import com.trestan.openspaceweatherdashboard.commons.GOESXRay
import com.trestan.openspaceweatherdashboard.commons.SolarProbabilities
import com.trestan.openspaceweatherdashboard.commons.SolarRegions
import retrofit2.Call

class RestAPI {

    private val goesXRayAPI: GOESXRayAPI
    private val solarRegionAPI: SolarRegionAPI
    private val solarProbabilitiesAPI: SolarProbabilitiesAPI

    init {
        val retrofit = RetrofitClient.getClient()
        goesXRayAPI = retrofit.create(GOESXRayAPI::class.java)
        solarRegionAPI = retrofit.create(SolarRegionAPI::class.java)
        solarProbabilitiesAPI = retrofit.create(SolarProbabilitiesAPI::class.java)
    }

    fun getGOESXRay(): Call<List<GOESXRay>> {
        return goesXRayAPI.getData()
    }

    fun getSolarRegions(): Call<List<SolarRegions>> {
        return solarRegionAPI.getData()
    }

    fun getSolarProbabilities(): Call<List<SolarProbabilities>> {
        return solarProbabilitiesAPI.getData()
    }
}