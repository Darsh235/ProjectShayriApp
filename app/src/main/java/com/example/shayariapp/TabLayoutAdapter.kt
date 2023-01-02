package com.example.shayariapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabLayoutAdapter(supportFragmentManager: FragmentManager, val tabCount: Int) :
    FragmentPagerAdapter(supportFragmentManager) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFrgment()
            1 -> CategoryFrgment()
            else -> FavouriteShararyFragment()
        }
    }
}