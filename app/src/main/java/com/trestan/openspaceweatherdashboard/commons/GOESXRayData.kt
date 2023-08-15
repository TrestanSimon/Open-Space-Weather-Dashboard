package com.trestan.openspaceweatherdashboard.commons

import com.trestan.openspaceweatherdashboard.api.RestAPI
import com.trestan.openspaceweatherdashboard.commons.adapter.AdapterConstants
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewType

class GOESXRayData(private val api: RestAPI = RestAPI()) : ViewType {
    var data: List<GOESXRay> = api.getGOESXRay().execute().body()!!

    override fun getViewType() = AdapterConstants.GOESXRAY
}