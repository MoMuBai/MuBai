package com.lzw.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class KotlinActivity : AppCompatActivity() {

    var a: String = "3";

    var b = 4;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin);
    }

    fun getInt(a: Int, b: Int): Int {
        val i: Int;
        i = a + b;
        return i;
    }

    fun getStr(a: String): String {
        val s: String;
        s = a;
        return s;
    }

}
