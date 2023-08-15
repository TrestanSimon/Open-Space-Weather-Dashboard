package com.trestan.openspaceweatherdashboard.features.goesxray

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.trestan.openspaceweatherdashboard.commons.RxBaseFragment
import com.trestan.openspaceweatherdashboard.databinding.FragmentBinding
import com.trestan.openspaceweatherdashboard.features.goesxray.adapter.Adapter
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class RxFragment: Fragment() {
    private val manager by lazy { RxManager() }
    private var _binding: FragmentBinding? = null
    private val binding get() = _binding!!
    private var subscriptions = CompositeSubscription()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerList.setHasFixedSize(true)
        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        initAdapter()

        if (savedInstanceState == null) {
            requestItems()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }

    private fun requestItems() {
        val subscription = manager.getObservables()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { retrieveItems ->
                    (binding.recyclerList.adapter as Adapter).addItems(retrieveItems)
                },
                { e ->
                    Snackbar.make(binding.recyclerList, e.message ?: "", Snackbar.LENGTH_LONG).show()
                }
            )
        subscriptions.add(subscription)
    }

    private fun initAdapter() {
        if (binding.recyclerList.adapter == null) {
            binding.recyclerList.adapter = Adapter()
        }
    }
}