package com.example.mvvm_tmdb.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_tmdb.R
import com.example.mvvm_tmdb.model.data.NowplayItem
import kotlinx.android.synthetic.main.item_nowplay.view.*

class MovieNowPlayAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val util = com.example.mvvm_tmdb.util.util()
    }

    private val nowplayItemList = ArrayList<NowplayItem>()

    class nowplayHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_nowplay, parent, false)
    ) {
        fun onBind(item: NowplayItem) {
            itemView.run {
                item_nowplay_view.run {
                    val urls = util.changeNowMovieUrl(item.poster_path)
                    Glide.with(this)
                        .load(urls)
                        .fitCenter()
                        .into(this)

                    setOnClickListener {
                        //item click시 doc url에 따라 Action View 넘겨줌
                        Log.d("MovieNowPlayAdapter", "${item.title} 클릭됨")
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        nowplayHolder(
            parent
        )

    override fun getItemCount(): Int = nowplayItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? nowplayHolder)?.onBind(nowplayItemList[position])
    }

    fun addNowPlayItem(title: String, poster_path: String) {
        nowplayItemList.add(NowplayItem(title, poster_path))
    }

}


