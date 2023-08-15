package com.trestan.openspaceweatherdashboard.features.goesxray.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trestan.openspaceweatherdashboard.R
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewType
import com.trestan.openspaceweatherdashboard.commons.adapter.ViewTypeDelegateAdapter
import com.trestan.openspaceweatherdashboard.commons.extensions.inflate

class LoadingDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = LoadingViewHolder(parent)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {}

    class LoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        parent.inflate(R.layout.item_loading)
    )
}