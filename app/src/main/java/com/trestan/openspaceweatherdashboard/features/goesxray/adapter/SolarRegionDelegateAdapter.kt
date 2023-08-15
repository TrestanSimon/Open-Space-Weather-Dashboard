package com.trestan.openspaceweatherdashboard.features.goesxray.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trestan.openspaceweatherdashboard.R
import com.trestan.openspaceweatherdashboard.commons.SolarRegionsItem
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewType
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewTypeDelegateAdapter
import com.trestan.openspaceweatherdashboard.commons.extensions.inflate
import com.trestan.openspaceweatherdashboard.databinding.SolarRegionItemBinding

class SolarRegionDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder {
        return SolarRegionViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as SolarRegionViewHolder
        holder.bind(item as SolarRegionsItem)
    }

    class SolarRegionViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.solar_region_item)
    ) {
        fun bind(item: SolarRegionsItem) = with(itemView) {
            val binding = SolarRegionItemBinding.bind(this)
            binding.description.text = item.data[0].obsDate
        }
    }
}