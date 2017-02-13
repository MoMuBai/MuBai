package com.lzw.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.lzw.kotlin.adapter.MainRecyclerAdapter
import com.lzw.kotlin.data.Forecast
import com.lzw.kotlin.data.Student
import com.lzw.kotlin.data.User
import java.util.*

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  lzw
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2017/2/7
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
class MainActivity : AppCompatActivity() {
    private val items = listOf<String>(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainRecyclerAdapter(items)
        test("Example")
    }


    /**
     * 一切都是对象
     */
    fun test(S: String) {
        val a: Char = 'a'
        val b: Int = a.toInt()
        val c: Double = b.toDouble()
        val i = 1
        val d = 3.5
        val f = 2f
        val l = 1L

        //一个String可以像数组那样访问，并且被迭代
        val s = S
        val m = s[2] //:a
        To(this, m + "")
        for (c in s) {
            To(this, c + "")
        }

        val student = Student()
        student.height = "180"

        //修改Forecast其中一个属性
        val f1 = Forecast(Date(), 20f, "杭州")
        val f2 = f1.copy(temperature = 30f)
        //映射对象到变量中
        val (date, temperature, details) = f1

        //多声明
        val date2 = f1.component1()

        val temperature2 = f1.component2()

        val details2 = f1.component3()

    }
}
