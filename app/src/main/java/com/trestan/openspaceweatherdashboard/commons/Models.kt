package com.trestan.openspaceweatherdashboard.commons

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
data class GOESXRay(
    @JsonProperty("time_tag")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="UTC")
    val time: Date,
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

@JsonIgnoreProperties(ignoreUnknown = true)
data class SolarProbabilities(
    @JsonProperty("date") val date: String,
    @JsonProperty("c_class_1_day") val c1Day: Number,
    @JsonProperty("c_class_2_day") val c2Day: Number,
    @JsonProperty("c_class_3_day") val c3Day: Number,
    @JsonProperty("m_class_1_day") val m1Day: Number,
    @JsonProperty("m_class_2_day") val m2Day: Number,
    @JsonProperty("m_class_3_day") val m3Day: Number,
    @JsonProperty("x_class_1_day") val x1Day: Number,
    @JsonProperty("x_class_2_day") val x2Day: Number,
    @JsonProperty("x_class_3_day") val x3Day: Number,
    @JsonProperty("10mev_protons_1_day") val p1Day: Number,
    @JsonProperty("10mev_protons_2_day") val p2Day: Number,
    @JsonProperty("10mev_protons_3_day") val p3Day: Number,
    @JsonProperty("polar_cap_absorption") val pcaf: String
)
