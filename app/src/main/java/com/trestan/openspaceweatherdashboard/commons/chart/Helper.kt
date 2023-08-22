package com.trestan.openspaceweatherdashboard.commons.chart

import android.content.Context
import android.graphics.Color
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.trestan.openspaceweatherdashboard.R
import com.trestan.openspaceweatherdashboard.commons.GOESXRay
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.log
import kotlin.math.pow

object Helper {
    fun processGOESXRayData(xRayData: List<GOESXRay>?): LineData {
        val dataSets = ArrayList<ILineDataSet>()
        val (longData, shortData) = sortGOESXRayData(xRayData)

        val lineWidth = 1.5f
        val highlightLineWidth = 1f

        val longDataSet = LineDataSet(longData, "Long wave (0.1–0.8 nm)")
        longDataSet.lineWidth = lineWidth
        longDataSet.setDrawCircles(false)
        longDataSet.setDrawCircleHole(false)
        longDataSet.color = Color.parseColor("#dd3300")
        longDataSet.highlightLineWidth = highlightLineWidth
        longDataSet.highLightColor = Color.parseColor("#118844")
        longDataSet.setDrawHorizontalHighlightIndicator(false)

        val shortDataSet = LineDataSet(shortData, "Short wave (0.05–0.4 nm)")
        shortDataSet.lineWidth = lineWidth
        shortDataSet.setDrawCircles(false)
        shortDataSet.setDrawCircleHole(false)
        shortDataSet.color = Color.parseColor("#4466ff")
        shortDataSet.highlightLineWidth = highlightLineWidth
        shortDataSet.highLightColor = Color.parseColor("#118844")
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
                        (xRayData[i].time.time - referenceTime) / 60000f, // minutes
                        log(xRayData[i].flux.toDouble(), 10.toDouble()).toFloat()
                    )
                )
            } else {
                shortData.add(
                    Entry(
                        (xRayData[i].time.time - referenceTime) / 60000f, // minutes
                        log(xRayData[i].flux.toDouble(), 10.toDouble()).toFloat()
                    )
                )
            }
        }
        return longData.toList() to shortData.toList()
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

    fun xRayFluxFormatSpanned(value: Float, context: Context): Spanned {
        val raw = 10f.pow(value)
        val expo = floor(value)
        val coef = raw * 10f.pow(-expo)
        val coefFormatted = DecimalFormat("0.00").format(coef)

        // expo is assumed negative
        val expoFormatted = abs(expo).toInt()
        return if (coef == 1f)
            Html.fromHtml(
                String.format("10<sup><small>&minus;$expoFormatted</small></sup>&nbsp;&nbsp;"
                        + context.resources.getString(R.string.watts_per_m2),
                Html.FROM_HTML_MODE_LEGACY)
            )
        else
            Html.fromHtml(
                "$coefFormatted &sdot; 10<sup><small>&minus;$expoFormatted</small></sup>&nbsp;&nbsp;"
                        + context.resources.getString(R.string.watts_per_m2),
                Html.FROM_HTML_MODE_LEGACY
            )
    }
}