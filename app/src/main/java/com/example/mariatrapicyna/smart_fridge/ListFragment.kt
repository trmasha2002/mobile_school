package com.example.mariatrapicyna.smart_fridge

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_list, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.adapter = ProductAdapter()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        return rootView
    }
}
