package com.trestan.openspaceweatherdashboard.features.goesxray.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.trestan.openspaceweatherdashboard.R
import com.trestan.openspaceweatherdashboard.commons.GOESXRayItem
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewType
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewTypeDelegateAdapter
import com.trestan.openspaceweatherdashboard.commons.extensions.inflate
import com.trestan.openspaceweatherdashboard.data.GOESXRayFormatter
import com.trestan.openspaceweatherdashboard.data.GOESXRayXAxisFormatter
import com.trestan.openspaceweatherdashboard.data.processGOESXRayData
import com.trestan.openspaceweatherdashboard.databinding.GoesXrayItemBinding


class GOESXRayDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder {
        return GOESXrayViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as GOESXrayViewHolder
        holder.bind(item as GOESXRayItem)
    }

    class GOESXrayViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.goes_xray_item)
    ) {
        fun bind(item: GOESXRayItem) = with(itemView) {
            val binding = GoesXrayItemBinding.bind(this)

            binding.chart.data = processGOESXRayData(item.data)
            binding.chart.data.setValueFormatter(GOESXRayFormatter())

            binding.chart.axisLeft.axisMaximum = -2f
            binding.chart.axisLeft.axisMinimum = -9f
            binding.chart.fitScreen()

            binding.chart.axisLeft.valueFormatter = GOESXRayFormatter()

            val classes = arrayOf("A", "B", "C", "M", "X")
            var ll: LimitLine
            for (i in classes.indices) {
                ll = LimitLine(i-8f, classes[i])
                ll.lineWidth = 0f
                ll.textColor = android.graphics.Color.parseColor("#dd3300")
                ll.textSize = 10f
                ll.labelPosition = LimitLine.LimitLabelPosition.LEFT_TOP
                binding.chart.axisLeft.addLimitLine(ll)
            }

            binding.chart.setNoDataText("No data loaded")
            binding.chart.description = null

            binding.chart.xAxis.setDrawGridLines(false)
            binding.chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            binding.chart.xAxis.valueFormatter = GOESXRayXAxisFormatter(
                item.data[0].time.time
            )

            binding.chart.axisLeft.textSize = 10f

            binding.chart.axisRight.isEnabled = false
            binding.chart.isScaleYEnabled = false
            binding.chart.isDragYEnabled = false
            binding.chart.setDrawBorders(true)

            binding.chart.invalidate()
        }
    }
}