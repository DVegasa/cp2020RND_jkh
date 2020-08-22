package io.github.dvegasa.cp2020rnd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadBottomNav()
    }

    private fun loadBottomNav() {
        val view = LayoutInflater.from(this).inflate(R.layout.view_bottomnav_0, flBottomnavHolder, false)
        flBottomnavHolder.removeAllViews()
        flBottomnavHolder.addView(view)
    }
}