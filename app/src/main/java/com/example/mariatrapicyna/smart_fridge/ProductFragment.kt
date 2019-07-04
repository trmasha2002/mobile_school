package com.example.mariatrapicyna.smart_fridge

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class ProductFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_list, container, false)
        val saveB = rootView.findViewById<Button>(R.id.save)
        val deleteB = rootView.findViewById<Button>(R.id.delete)



        return inflater.inflate(R.layout.fragment_product, container, false)
    }
}