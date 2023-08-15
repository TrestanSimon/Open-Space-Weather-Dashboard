package com.trestan.openspaceweatherdashboard.features.goesxray.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trestan.openspaceweatherdashboard.R
import com.trestan.openspaceweatherdashboard.commons.GOESXRay
import com.trestan.openspaceweatherdashboard.commons.GOESXRayData
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewType
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewTypeDelegateAdapter
import com.trestan.openspaceweatherdashboard.commons.extensions.inflate
import com.trestan.openspaceweatherdashboard.data.ProcessGOESXRayData
import com.trestan.openspaceweatherdashboard.databinding.GoesXrayItemBinding

class GOESXRayDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder {
        return GOESXrayViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as GOESXrayViewHolder
        holder.bind(item as GOESXRayData)
    }

    class GOESXrayViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.goes_xray_item)
    ) {
        fun bind(item: GOESXRayData) = with(itemView) {
            val binding = GoesXrayItemBinding.bind(this)
            binding.chart.data = ProcessGOESXRayData(item.data)
            binding.chart.invalidate()
        }
    }
}