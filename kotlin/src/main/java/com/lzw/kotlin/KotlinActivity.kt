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
        if (a < 0) {
            return a;
        } else {
            i = a + b;
            return i;
        }
    }

    fun getStr(a: String): String? {
        if (a != null) {
            return "0";
        } else {
            val s: String;
            s = a;
            return s;
        }
    }

    fun main(a: String): Unit {
        val s: String;
        print(a)
    }

    fun main(a: String, b: String) {
        print(a + b)
    }

    fun main(a: Int, b: Int) = a + b;

    fun main(b: Int) = a + b;


    fun main(args: Array<String>) {
        for (arg in args) {
            print(arg)
        }

        for (i in args.indices) {
            print(args[i])
        }
    }
}
