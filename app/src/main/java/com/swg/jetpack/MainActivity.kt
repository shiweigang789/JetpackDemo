package com.swg.jetpack

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.swg.jetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val test = Test()
        binding.doubleClick.setOnClickListener {
            if (!DoubleClickUtils.canClick()) return@setOnClickListener
            test.test()
        }

        val brand = Build.BRAND
        val model = Build.MODEL
        val serial = Build.SERIAL
        Log.d("TIME_PRINT", brand)
        Log.d("TIME_PRINT", model)
        Log.d("TIME_PRINT", serial)
    }

}