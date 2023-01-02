package com.example.shayariapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CategoryFrgment : Fragment() {

    lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for requireActivity() fragment
        v = inflater.inflate(R.layout.fragment_category_frgment, container, false)

        initview()

        return v


    }

    private fun initview() {
        var lnrRecycle = v.findViewById<RecyclerView>(R.id.lnrRecycle)
        var list = MyDatabase(requireActivity()).readData()

        var categoryAdpter = CategoryAdpter(requireActivity(), list)
        var laymanger = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        lnrRecycle.layoutManager = laymanger
        lnrRecycle.adapter = categoryAdpter
    }

}