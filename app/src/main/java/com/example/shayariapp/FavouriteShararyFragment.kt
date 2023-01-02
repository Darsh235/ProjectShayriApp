package com.example.shayariapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shayariapp.databinding.FragmentFavouriteShararyBinding

class FavouriteShararyFragment : Fragment() {
lateinit var v:View
lateinit var binding :FragmentFavouriteShararyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentFavouriteShararyBinding.inflate(inflater, container, false)

        iniotview()

        return binding.root
    }

    private fun iniotview() {


        var listShow =MyDatabase(requireActivity()).likedata()
        var adapter= ShayariApdter(requireActivity(),listShow )
        var linearLayout= LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL,false)
        binding.Recycleshow.layoutManager=linearLayout
        binding.Recycleshow.adapter=adapter

    }

}