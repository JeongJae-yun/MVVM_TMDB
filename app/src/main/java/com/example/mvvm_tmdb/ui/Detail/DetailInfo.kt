package com.example.mvvm_tmdb.ui.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm_tmdb.R

class DetailInfo : AppCompatActivity() {
    private var movie_id : Int = 0

    /*
    * 이후 viewmodel 데이터 바인딩 작업..
    *
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_info)
        getInit()

    }

    fun getInit(){
        intent.extras?.let {
            movie_id = it.getInt("id")
        }
    }
}
