package com.trestan.openspaceweatherdashboard

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.trestan.openspaceweatherdashboard.service.GOESXRayService


class MainActivity : ComponentActivity() {

    private lateinit var chart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chart = findViewById(R.id.chart)

        graphXRays(chart)
    }
}

fun getGOESXRayData(): Pair<List<Entry>, List<Entry>> {
    val goesXRayData = GOESXRayService().getBody()
    val longData = ArrayList<Entry>()
    val shortData = ArrayList<Entry>()

    for (i in 0 until goesXRayData?.size!!) {
        if (goesXRayData[i].energy.contentEquals("0.1-0.8nm")) {
            longData.add(Entry(
                i.toFloat(),
                kotlin.math.log(goesXRayData[i].flux.toDouble(), 10.toDouble()).toFloat()
            ))
        } else {
            shortData.add(Entry(
                i.toFloat(),
                kotlin.math.log(goesXRayData[i].flux.toDouble(), 10.toDouble()).toFloat()
            ))
        }
    }
    return longData.toList() to shortData.toList()
}

fun graphXRays(chart: LineChart) {
    Thread {
        val dataSets = ArrayList<ILineDataSet>()
        val (longData, shortData) = getGOESXRayData()

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

        val data = LineData(dataSets)

        chart.data = data
        chart.invalidate()
    }.start()
}