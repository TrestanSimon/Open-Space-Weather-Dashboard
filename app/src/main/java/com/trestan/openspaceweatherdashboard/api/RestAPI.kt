package com.trestan.openspaceweatherdashboard.api

import com.trestan.openspaceweatherdashboard.commons.GOESXRay
import com.trestan.openspaceweatherdashboard.commons.SolarRegions
import retrofit2.Call

class RestAPI {

    private val goesXRayAPI: GOESXRayAPI
    private val solarRegionAPI: SolarRegionAPI

    init {
        val retrofit = RetrofitClient.getClient()
        goesXRayAPI = retrofit.create(GOESXRayAPI::class.java)
        solarRegionAPI = retrofit.create(SolarRegionAPI::class.java)
    }

    fun getGOESXRay(): Call<List<GOESXRay>> {
        return goesXRayAPI.getData()
    }

    fun getSolarRegions(): Call<List<SolarRegions>> {
        return solarRegionAPI.getData()
    }
}