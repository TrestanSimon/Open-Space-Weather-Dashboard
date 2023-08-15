package com.trestan.openspaceweatherdashboard.commons.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trestan.openspaceweatherdashboard.features.goesxray.adapter.SolarRegionDelegateAdapter

interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}