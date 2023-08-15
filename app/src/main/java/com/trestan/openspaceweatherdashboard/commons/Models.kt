package com.trestan.openspaceweatherdashboard.commons

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.trestan.openspaceweatherdashboard.commons.adapter.AdapterConstants
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewType

@JsonIgnoreProperties(ignoreUnknown = true)
data class GOESXRay(
    @JsonProperty("time_tag") val time: String,
    @JsonProperty("satellite") val satellite: Number,
    @JsonProperty("flux") val flux: Number,
    @JsonProperty("observed_flux") val obsFlux: Number,
    @JsonProperty("electron_correction") val electronCorrection: Number,
    @JsonProperty("electron_contaminaton") val electronContamination: Boolean, // Not a typo
    @JsonProperty("energy") val energy: String,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SolarRegions(
    @JsonProperty("observed_date") val obsDate: String
)
