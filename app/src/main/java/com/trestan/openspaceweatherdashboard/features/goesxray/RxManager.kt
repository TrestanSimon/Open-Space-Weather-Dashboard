package com.trestan.openspaceweatherdashboard.features.goesxray

import rx.Observable
import com.trestan.openspaceweatherdashboard.api.RestAPI
import com.trestan.openspaceweatherdashboard.commons.GOESXRayItem
import com.trestan.openspaceweatherdashboard.commons.SolarProbabilitiesItem
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewType

class RxManager(private val api: RestAPI = RestAPI()) {
    fun getObservables(): Observable<List<ViewType>> {
        return Observable.create {
                subscriber ->
            val body = listOf(
                GOESXRayItem(api),
                SolarProbabilitiesItem(api)
            )
            subscriber.onNext(body)
            subscriber.onCompleted()
        }
    }
}