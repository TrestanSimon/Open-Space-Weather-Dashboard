package com.trestan.openspaceweatherdashboard.features.goesxray

import rx.Observable
import com.trestan.openspaceweatherdashboard.api.RestAPI
import com.trestan.openspaceweatherdashboard.commons.GOESXRay
import com.trestan.openspaceweatherdashboard.commons.GOESXRayData
import com.trestan.openspaceweatherdashboard.commons.SolarRegions
import com.trestan.openspaceweatherdashboard.commons.SolarRegionsItem
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewType

class RxManager(private val api: RestAPI = RestAPI()) {
    fun getObservables(): Observable<List<ViewType>> {
        return Observable.create {
                subscriber ->
            val body = listOf(SolarRegionsItem(), GOESXRayData())
            subscriber.onNext(body)
            subscriber.onCompleted()
        }
    }
}