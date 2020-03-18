package com.lzw.kotlin.mvi

/**
 * Author: lzw
 * Date: 2019/7/25
 * Description: This is WeatherView
 */
interface WeatherView {
    fun getWeather()

    fun renderToUi()
}