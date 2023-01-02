package com.example.shayariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shayariapp.databinding.ActivityMainBinding
import com.example.shayariapp.databinding.ActivityShayariShowBinding

class ShayariShowActivity : AppCompatActivity() {
    lateinit var binding : ActivityShayariShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityShayariShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intView()

    }

    private fun intView() {

        var list1 = MyDatabase(this).readData()
        binding.includeLayout.title.text=list1.get(intent.getIntExtra("cate",0)).Category

        var list = MyDatabase(this).showData()
        var shayariApdter = ShayariApdter(this,list)
        var l=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.Recycleshow.layoutManager=l
        binding.Recycleshow.adapter=shayariApdter


    }
}