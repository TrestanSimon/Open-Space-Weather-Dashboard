package com.trestan.openspaceweatherdashboard.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.trestan.openspaceweatherdashboard.model.GOESXRay
import okhttp3.ResponseBody

class GOESXRayService {
    private val retrofit = RetrofitClient.getClient()
    private val goesXRayAPI = retrofit.create(GOESXRayAPI::class.java)

    fun getBody(): List<GOESXRay>? {
        val usersResponse = goesXRayAPI.getGOESXRay()
            .execute()

        val successful = usersResponse.isSuccessful
        val httpStatusCode = usersResponse.code()
        val httpStatusMessage = usersResponse.message()

        val body: List<GOESXRay>? = usersResponse.body()
        val errorBody: ResponseBody? = usersResponse.errorBody()

        val mapper = ObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val mappedBody: ErrorResponse? = errorBody?.let { notNullErrorBody ->
            mapper.readValue(notNullErrorBody.toString(), ErrorResponse::class.java)
        }

        val headers = usersResponse.headers()
        val customHeaderValue = headers["custom-header"]

        return body
    }
}


