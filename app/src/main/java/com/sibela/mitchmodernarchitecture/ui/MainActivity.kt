package com.sibela.mitchmodernarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sibela.mitchmodernarchitecture.R

class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}