package com.trestan.openspaceweatherdashboard.commons

import com.trestan.openspaceweatherdashboard.api.RestAPI
import com.trestan.openspaceweatherdashboard.commons.adapter.AdapterConstants
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewType

class SolarRegionsItem(private val api: RestAPI = RestAPI()) : ViewType {
    var data: List<SolarRegions> = api.getSolarRegions().execute().body()!!

    override fun getViewType() = AdapterConstants.SOLARREGIONS
}