package com.trestan.openspaceweatherdashboard.data

import android.graphics.Color.parseColor
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.trestan.openspaceweatherdashboard.commons.GOESXRay
import java.text.DecimalFormat
import java.time.ZoneId
import java.util.Date
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.log
import kotlin.math.pow

fun processGOESXRayData(xRayData: List<GOESXRay>?): LineData {
    val dataSets = ArrayList<ILineDataSet>()
    val (longData, shortData) = sortGOESXRayData(xRayData)

    val longDataSet = LineDataSet(longData, "Long wave")
    longDataSet.lineWidth = 1f
    longDataSet.setDrawCircles(false)
    longDataSet.setDrawCircleHole(false)
    longDataSet.color = parseColor("#dd3300")
    longDataSet.highlightLineWidth = 1f
    longDataSet.highLightColor = parseColor("#118844")
    longDataSet.setDrawHorizontalHighlightIndicator(false)

    val shortDataSet = LineDataSet(shortData, "Short wave")
    shortDataSet.lineWidth = 1f
    shortDataSet.setDrawCircles(false)
    shortDataSet.setDrawCircleHole(false)
    shortDataSet.color = parseColor("#4466ff")
    shortDataSet.highlightLineWidth = 1f
    shortDataSet.highLightColor = parseColor("#118844")
    shortDataSet.setDrawHorizontalHighlightIndicator(false)

    dataSets.add(longDataSet)
    dataSets.add(shortDataSet)

    return LineData(dataSets)
}

private fun sortGOESXRayData(xRayData: List<GOESXRay>?): Pair<List<Entry>, List<Entry>> {
    val longData = ArrayList<Entry>()
    val shortData = ArrayList<Entry>()
    val referenceTime = xRayData!![0].time.time

    for (i in xRayData.indices) {
        if (xRayData[i].energy.contentEquals("0.1-0.8nm")) {
            longData.add(
                Entry(
                    (xRayData[i].time.time - referenceTime) / 1000f, // seconds
                    log(xRayData[i].flux.toDouble(), 10.toDouble()).toFloat()
                )
            )
        } else {
            shortData.add(
                Entry(
                    (xRayData[i].time.time - referenceTime) / 1000f, // seconds
                    log(xRayData[i].flux.toDouble(), 10.toDouble()).toFloat()
                )
            )
        }
    }
    return longData.toList() to shortData.toList()
}

open class GOESXRayFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return xRayFluxFormat(value)
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return xRayFluxFormat(value)
    }
}

class GOESXRayXAxisFormatter(private var referenceTime: Long) : IndexAxisValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val msSince1970Time = referenceTime + value.toLong() * 1000
        val date = Date(msSince1970Time).toInstant().atZone(ZoneId.of("UTC"))
        val minute = (date.minute / 10).toString() + (date.minute % 10).toString()
        val hour = (date.hour / 10).toString() + (date.hour % 10).toString()
        return "${hour}:${minute}"
    }
}

fun xRayFluxFormat(value: Float): String {
    val raw = 10f.pow(value)
    val expo = floor(value)
    val coef = raw * 10f.pow(-expo)
    val coefFormatted = DecimalFormat("0.00").format(coef)

    // expo is assumed negative
    val expoFormatted = abs(expo).toInt()
    return if (coef == 1f)
        "1e\u2212$expoFormatted"
    else
        "${coefFormatted}e\u2212$expoFormatted"
}