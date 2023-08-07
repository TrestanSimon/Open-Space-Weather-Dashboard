package com.trestan.openspaceweatherdashboard.service

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(
    @JsonProperty("message") val message: String
)