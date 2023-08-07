package com.trestan.openspaceweatherdashboard.service

import com.trestan.openspaceweatherdashboard.model.GOESXRay
import retrofit2.Call
import retrofit2.http.GET

interface GOESXRayAPI {
    @GET("goes/primary/xrays-6-hour.json")
    fun getGOESXRay(): Call<List<GOESXRay>>
}