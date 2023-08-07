package com.trestan.openspaceweatherdashboard.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GOESXRay(
    @JsonProperty("time_tag") var time: String,
    @JsonProperty("satellite") var satellite: Number,
    @JsonProperty("flux") var flux: Number,
    @JsonProperty("observed_flux") var obsFlux: Number,
    @JsonProperty("electron_correction") var electronCorrection: Number,
    @JsonProperty("electron_contaminaton") var electronContamination: Boolean, // Not a typo
    @JsonProperty("energy") var energy: String,
)