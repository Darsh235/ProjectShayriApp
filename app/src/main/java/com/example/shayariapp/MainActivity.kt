package com.example.shayariapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shayariapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var baxkpoint = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview() {

        binding.includeLayout.imBack.setOnClickListener { onBackPressed() }
        binding.includeLayout.title.text = "Home"

        var lnrTablaout = binding.lnrTablaout

        binding.lnrTablaout.addTab(
            binding.lnrTablaout.newTab().setText("Home").setIcon(R.drawable.hicon)
        )
        binding.lnrTablaout.addTab(
            binding.lnrTablaout.newTab().setIcon(R.drawable.categ).setText("Category")
        )
        binding.lnrTablaout.addTab(
            binding.lnrTablaout.newTab().setIcon(R.drawable.view).setText("Status")
        )

        var lnrTablaoutAdpter = TabLayoutAdapter(supportFragmentManager, lnrTablaout.tabCount)
        binding.vPager.adapter = lnrTablaoutAdpter

        binding.lnrTablaout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                binding.vPager.currentItem = tab?.position!!
                if (tab.position == 0) {
                    binding.includeLayout.title.text = "Home"
                } else if (tab.position == 1) {
                    binding.includeLayout.title.text = "Category"
                } else {
                    binding.includeLayout.title.text = "Favourite"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        binding.vPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.lnrTablaout))

    }

    override fun onBackPressed() {
        if (baxkpoint) {
            super.onBackPressed()
            return
        }
        this.baxkpoint = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            baxkpoint = false
        }, 3000)
    }
}