package com.trestan.openspaceweatherdashboard.features.goesxray.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trestan.openspaceweatherdashboard.R
import com.trestan.openspaceweatherdashboard.commons.SolarProbabilitiesItem
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewType
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewTypeDelegateAdapter
import com.trestan.openspaceweatherdashboard.commons.extensions.inflate
import com.trestan.openspaceweatherdashboard.databinding.SolarProbabilitiesItemBinding
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class SolarProbabilitiesDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder {
        return SolarProbabilitiesViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as SolarProbabilitiesViewHolder
        holder.bind(item as SolarProbabilitiesItem)
    }

    class SolarProbabilitiesViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.solar_probabilities_item)
    ) {
        fun bind(item: SolarProbabilitiesItem) = with(itemView) {
            val binding = SolarProbabilitiesItemBinding.bind(this)

            binding.day1.text = LocalDate.now().plus(1, ChronoUnit.DAYS).toString()
            binding.day2.text = LocalDate.now().plus(2, ChronoUnit.DAYS).toString()
            binding.day3.text = LocalDate.now().plus(3, ChronoUnit.DAYS).toString()

            binding.cFlare1.text = String.format(resources.getString(R.string.percent), item.data[0].c1Day)
            binding.cFlare2.text = String.format(resources.getString(R.string.percent), item.data[0].c2Day)
            binding.cFlare3.text = String.format(resources.getString(R.string.percent), item.data[0].c3Day)

            binding.mFlare1.text = String.format(resources.getString(R.string.percent), item.data[0].m1Day)
            binding.mFlare2.text = String.format(resources.getString(R.string.percent), item.data[0].m2Day)
            binding.mFlare3.text = String.format(resources.getString(R.string.percent), item.data[0].m3Day)

            binding.xFlare1.text = String.format(resources.getString(R.string.percent), item.data[0].x1Day)
            binding.xFlare2.text = String.format(resources.getString(R.string.percent), item.data[0].x2Day)
            binding.xFlare3.text = String.format(resources.getString(R.string.percent), item.data[0].x3Day)

            binding.proton1.text = String.format(resources.getString(R.string.percent), item.data[0].p1Day)
            binding.proton2.text = String.format(resources.getString(R.string.percent), item.data[0].p2Day)
            binding.proton3.text = String.format(resources.getString(R.string.percent), item.data[0].p3Day)

            binding.pca1.text = item.data[0].pcaf
        }
    }
}