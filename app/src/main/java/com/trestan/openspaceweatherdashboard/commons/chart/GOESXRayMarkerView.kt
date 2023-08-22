package com.trestan.openspaceweatherdashboard.commons.chart

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.trestan.openspaceweatherdashboard.R
import com.trestan.openspaceweatherdashboard.commons.chart.Helper.xRayFluxFormatSpanned

@SuppressLint("ViewConstructor")
class GOESXRayMarkerView(context: Context, layoutResource: Int): MarkerView(context, layoutResource) {
    private val tvContent = findViewById<TextView>(R.id.tvContent)
    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        if (e != null) {
            tvContent.text = xRayFluxFormatSpanned(e.y, context)
        }
        super.refreshContent(e, highlight)
    }
}