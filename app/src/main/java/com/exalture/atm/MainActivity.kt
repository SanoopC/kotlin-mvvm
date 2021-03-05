package com.exalture.atm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exalture.atm.details.di.DetailsComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}