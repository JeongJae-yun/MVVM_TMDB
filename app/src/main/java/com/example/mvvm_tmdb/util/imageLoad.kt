package com.example.mvvm_tmdb.util

import android.app.Activity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.mvvm_tmdb.R

class imageLoad() {

    fun backgroundLoad(view : ImageView, activity : Activity, path : String){
        Glide.with(activity)
            .load(path)
            .centerCrop()
            .placeholder(R.drawable.phimg)
            .into(view)
    }

    fun mainLoad(view : ImageView, activity : Activity, path : String){
        Glide.with(activity)
            .load(path)
            .fitCenter()
            .placeholder(R.drawable.phimg)
            .into(view)
    }

}