package com.trestan.openspaceweatherdashboard.commons.chart

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.time.ZoneId
import java.util.Date

class DateAxisFormatter(
    private var referenceTime: Long
) : IndexAxisValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val msSince1970Time = referenceTime + value.toLong() * 60000
        val date = Date(msSince1970Time).toInstant().atZone(ZoneId.of("UTC"))
        return if (date.minute == 0 && date.hour == 0) {
            "${date.dayOfMonth} ${formatMonth(date.monthValue)}"
        } else {
            val minute = (date.minute / 10).toString() + (date.minute % 10).toString()
            val hour = (date.hour / 10).toString() + (date.hour % 10).toString()
            "${hour}:${minute}"
        }
    }

    private fun formatMonth(month: Int): String {
        val monthString = listOf(
            "Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec"
        )
        return monthString[month - 1]
    }
}