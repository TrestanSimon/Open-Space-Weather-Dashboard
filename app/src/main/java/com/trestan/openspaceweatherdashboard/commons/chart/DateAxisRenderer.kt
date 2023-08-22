package com.trestan.openspaceweatherdashboard.commons.chart

import android.graphics.Canvas
import android.text.Html
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.renderer.XAxisRenderer
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Transformer
import com.github.mikephil.charting.utils.ViewPortHandler

class DateAxisRenderer(
    private var referenceTime: Long,
    viewPortHandler: ViewPortHandler?,
    xAxis: XAxis?,
    trans: Transformer?
) : XAxisRenderer(viewPortHandler, xAxis, trans) {
    override fun computeAxisValues(min: Float, max: Float) {
        // milliseconds per unit (of min, max)
        val MS_PER_UNIT = 60000L
        val UNIT_PER_DAY = 86400000L / MS_PER_UNIT

        val lineCount = 3

        val dayFractionList = listOf(1, 2, 3, 4, 6, 8, 12, 24, 48, 72, 96, 288, 720, 1440)
        var dayFractionIndex = dayFractionList.size - 1
        var dayFraction: Long  // = UNIT_PER_DAY / dayFractionList[dayFractionIndex]

        var interval = 0L
        var labelCount = 0

        // Convert to units since 1970-1-1
        val min0 = min.toLong() + (referenceTime / MS_PER_UNIT)
        val max0 = max.toLong() + (referenceTime / MS_PER_UNIT)

        // Crop to nearest day
        var min1 = min0 + UNIT_PER_DAY - (min0 % UNIT_PER_DAY)
        var max1 = max0 - max0 % UNIT_PER_DAY

        val days = ((max1 - min1) / UNIT_PER_DAY).toInt()
        val daysInterval = days / lineCount.toLong()

        if (daysInterval > 0) {
            interval = daysInterval * UNIT_PER_DAY
            labelCount = ((max1 - min1) / interval).toInt()
        }

        if (daysInterval == 0L || labelCount < lineCount) {
            do {
                dayFraction = UNIT_PER_DAY / dayFractionList[dayFractionIndex]
                max1 = max0 - (max0 % dayFraction)
                min1 = min0 + dayFraction - (min0 % dayFraction)
                labelCount = ((max1 - min1) / dayFraction).toInt()
            } while (labelCount > lineCount + 1 && dayFractionIndex-- > 0)

            if (labelCount <= 0) return
            interval = (max1 - min1) / labelCount
        }

        labelCount++

        mAxis.mEntries = FloatArray(labelCount)
        var entry = min1 - (referenceTime / MS_PER_UNIT)

        for (j in 0 until labelCount) {
            mAxis.mEntries[j] = entry.toFloat()
            entry += interval
        }

        mAxis.mCenteredEntries = FloatArray(0)
        mAxis.mEntryCount = labelCount
    }
}