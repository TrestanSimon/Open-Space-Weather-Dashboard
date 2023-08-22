package com.trestan.openspaceweatherdashboard.features.goesxray.adapter

import android.text.Html
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.trestan.openspaceweatherdashboard.R
import com.trestan.openspaceweatherdashboard.commons.GOESXRayItem
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewType
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewTypeDelegateAdapter
import com.trestan.openspaceweatherdashboard.commons.chart.GOESXRayMarkerView
import com.trestan.openspaceweatherdashboard.commons.chart.DateAxisFormatter
import com.trestan.openspaceweatherdashboard.commons.chart.DateAxisRenderer
import com.trestan.openspaceweatherdashboard.commons.chart.GOESXRayFluxFormatter
import com.trestan.openspaceweatherdashboard.commons.extensions.inflate
import com.trestan.openspaceweatherdashboard.commons.chart.Helper.processGOESXRayData
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

            binding.yAxisLabel.text = Html.fromHtml(
                "${resources.getString(R.string.goes_xray_flux_axis_label)}&nbsp;&nbsp;(${resources.getString(R.string.watts_per_m2)})",
                Html.FROM_HTML_MODE_LEGACY
            )

            binding.chart.data = processGOESXRayData(item.data)
            binding.chart.data.setValueFormatter(GOESXRayFluxFormatter())

            // Left axis
            binding.chart.axisLeft.axisMaximum = -2f
            binding.chart.axisLeft.axisMinimum = -9f
            binding.chart.axisLeft.textSize = 14f
            binding.chart.axisLeft.valueFormatter = GOESXRayFluxFormatter()

            // Right axis
            binding.chart.axisRight.isEnabled = false

            // X axis
            binding.chart.setXAxisRenderer(DateAxisRenderer(
                item.data[0].time.time, // reference time
                binding.chart.viewPortHandler,
                binding.chart.xAxis,
                binding.chart.getTransformer(null)
            ))
            binding.chart.xAxis.valueFormatter = DateAxisFormatter(
                item.data[0].time.time  // reference time
            )
            binding.chart.xAxis.textSize = 14f
            binding.chart.extraBottomOffset = 10f
            binding.chart.xAxis.position = XAxis.XAxisPosition.BOTTOM

            // Class limit lines
            val classes = arrayOf("A", "B", "C", "M", "X")
            var ll: LimitLine
            for (i in classes.indices) {
                ll = LimitLine(i - 8f, classes[i])
                ll.lineWidth = 0f
                ll.textColor = android.graphics.Color.parseColor("#dd3300")
                ll.textSize = 16f
                ll.labelPosition = LimitLine.LimitLabelPosition.LEFT_TOP
                binding.chart.axisLeft.addLimitLine(ll)
            }

            // Add log horizontal grid lines
            /*
            for (i in -9..-3) {
                for (j in 1..9) {
                    ll = LimitLine(log(j * 10.0.pow(i.toDouble()), 10.0).toFloat())
                    ll.lineWidth = 0.5f
                    ll.lineColor = Color.Gray.toArgb()
                    binding.chart.axisLeft.addLimitLine(ll)
                }
            }*/

            binding.chart.isScaleYEnabled = false
            binding.chart.isDragYEnabled = false
            binding.chart.setDrawBorders(true)

            binding.chart.setNoDataText("No data loaded")
            binding.chart.description = null

            binding.chart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
            binding.chart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            binding.chart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            binding.chart.legend.yOffset = 10f


            binding.chart.marker = GOESXRayMarkerView(this.context, R.layout.goes_xray_marker)

            binding.chart.invalidate()
        }
    }
}