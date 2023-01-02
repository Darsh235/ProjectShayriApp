package com.example.shayariapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFrgment : Fragment() {

    lateinit var v: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v =inflater.inflate(R.layout.fragment_home_frgment, container, false)

        iniotview()

        return  v
    }

    private fun iniotview() {

        var Homerecycler =v.findViewById<RecyclerView>(R.id.Homerecycler)
        var listShow =MyDatabase(requireActivity()).homedata()
        var adapter= ShayariApdter(requireActivity(),listShow )
        var linearLayout=LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
        Homerecycler.layoutManager=linearLayout
        Homerecycler.adapter=adapter

    }


}