package com.example.mvvm_tmdb.util

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class util() {
    fun changeNowMovieUrl(url: String): String {
        return "https://image.tmdb.org/t/p/w500$url"
    }

    fun cutWordLimit(txt : String) : String {
        var t = ""
        val s = txt.length
        if (s >= 15){
            val range = IntRange(0,15) //15번째 문자까지만 자름.
            t = txt.slice(range)
        }else{
            t = txt
        }
        return t
    }
}