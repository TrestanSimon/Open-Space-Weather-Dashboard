package com.trestan.openspaceweatherdashboard.commons

import com.trestan.openspaceweatherdashboard.api.RestAPI
import com.trestan.openspaceweatherdashboard.commons.adapter.AdapterConstants
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewType

class SolarProbabilitiesItem(api: RestAPI) : ViewType {
    var data: List<SolarProbabilities> = api.getSolarProbabilities().execute().body()!!

    override fun getViewType() = AdapterConstants.SOLARPROBS
}