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
}