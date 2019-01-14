package com.jv.pushnotificationapp

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Configura la version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationUtils(this)
        }
    }
    
}
