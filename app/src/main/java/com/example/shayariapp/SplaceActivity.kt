package com.example.shayariapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splace)

        Handler().postDelayed({
            val i = Intent(
                this,
                MainActivity::class.java
            )
            //Intent is used to switch from one activity to another.
            startActivity(i)
            //invoke the SecondActivity.
            finish()
            //the current activity will get finished.
        }, 4000)

    }
}