package com.mob.mubai.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mob.mubai.R
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        tv.text = "Jell"
    }
}
