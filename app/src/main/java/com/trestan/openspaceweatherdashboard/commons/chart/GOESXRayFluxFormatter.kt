package com.trestan.openspaceweatherdashboard.commons.chart

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.trestan.openspaceweatherdashboard.commons.chart.Helper.xRayFluxFormat


open class GOESXRayFluxFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return xRayFluxFormat(value)
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return xRayFluxFormat(value)
    }

    override fun getPointLabel(entry: Entry?): String {
        return ""
    }
}