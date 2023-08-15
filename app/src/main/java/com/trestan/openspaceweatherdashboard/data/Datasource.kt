package com.trestan.openspaceweatherdashboard.data

import android.graphics.Color
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.trestan.openspaceweatherdashboard.api.RestAPI
import com.trestan.openspaceweatherdashboard.commons.GOESXRay
import kotlin.math.log

fun ProcessGOESXRayData(xRayData: List<GOESXRay>?): LineData {
    val dataSets = ArrayList<ILineDataSet>()
    val (longData, shortData) = sortGOESXRayData(xRayData)

    val longDataSet = LineDataSet(longData, "Long wave")
    longDataSet.lineWidth = 3f
    longDataSet.setDrawCircles(false)
    longDataSet.setDrawCircleHole(false)
    longDataSet.color = Color.rgb(255, 0, 0)

    val shortDataSet = LineDataSet(shortData, "Short wave")
    shortDataSet.lineWidth = 2f
    shortDataSet.setDrawCircles(false)
    shortDataSet.setDrawCircleHole(false)
    shortDataSet.color = Color.rgb(0, 255, 0)

    dataSets.add(longDataSet)
    dataSets.add(shortDataSet)

    return LineData(dataSets)
}

private fun sortGOESXRayData(xRayData: List<GOESXRay>?): Pair<List<Entry>, List<Entry>> {
    val longData = ArrayList<Entry>()
    val shortData = ArrayList<Entry>()

    for (i in 0 until xRayData!!.size) {
        if (xRayData[i].energy.contentEquals("0.1-0.8nm")) {
            longData.add(
                Entry(
                    i.toFloat(),
                    log(xRayData[i].flux.toDouble(), 10.toDouble()).toFloat()
                )
            )
        } else {
            shortData.add(
                Entry(
                    i.toFloat(),
                    log(xRayData[i].flux.toDouble(), 10.toDouble()).toFloat()
                )
            )
        }
    }
    return longData.toList() to shortData.toList()
}