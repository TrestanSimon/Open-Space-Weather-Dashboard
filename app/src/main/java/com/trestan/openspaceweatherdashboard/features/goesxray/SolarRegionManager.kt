package com.trestan.openspaceweatherdashboard.features.goesxray

import rx.Observable
import com.trestan.openspaceweatherdashboard.api.RestAPI
import com.trestan.openspaceweatherdashboard.commons.SolarRegions

class SolarRegionManager(private val api: RestAPI = RestAPI()) {
    fun getObservables(): Observable<List<SolarRegions>> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getSolarRegions()
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val body = response.body()
                subscriber.onNext(body)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}