package com.example.mvvm_tmdb.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_tmdb.R
import com.example.mvvm_tmdb.model.data.NowplayItem
import com.example.mvvm_tmdb.ui.Now_Play.NowPlayFragment
import com.example.mvvm_tmdb.ui.Now_Play.NowPlay_Detail
import kotlinx.android.synthetic.main.item_nowplay.view.*

class MovieNowPlayAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val util = com.example.mvvm_tmdb.util.util()
        var n_adult : Boolean = false
        var n_backdropPath : String = ""
        var n_oLanguage : String = ""
        var n_oTitle : String = ""
        var n_overview : String = ""
        var n_posterPath : String = ""
        var n_releaseDate : String = ""
        var n_title : String = ""
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
                        .centerCrop()
                        .placeholder(R.drawable.phimg)
                        .into(this)

                    setOnClickListener {
                        //item click시 doc url에 따라 Action View 넘겨줌
                        Log.d("MovieNowPlayAdapter", "${item.title} 클릭됨")
                        val intent = Intent(context, NowPlay_Detail::class.java)
                        intent.putExtra("adult","${item.adult}")
                        intent.putExtra("backdropPath", item.backdrop_path)
                        intent.putExtra("originalLanguage", item.original_language)
                        intent.putExtra("originalTitle",item.original_title)
                        intent.putExtra("overview",item.overview)
                        intent.putExtra("title",item.title)
                        intent.putExtra("releaseDate",item.release_date)
                        intent.putExtra("posterPath",item.poster_path)
                        context.startActivity(intent)
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

    fun refresh(){
        nowplayItemList.clear()
    }

    fun addNowPlayItem(
        adult: Boolean?,
        backdropPath: String?,
        originalLanguage: String?,
        originalTitle: String?,
        overview: String?,
        posterPath: String?,
        releaseDate: String?,
        title: String?
    ) {
        adult?.let { n_adult = adult }
        backdropPath?.let { n_backdropPath = backdropPath }
        originalLanguage?.let { n_oLanguage = originalLanguage }
        originalTitle?.let{ n_oTitle = originalTitle}
        overview?.let{ n_overview = overview}
        posterPath?.let{ n_posterPath = posterPath}
        releaseDate?.let{ n_releaseDate = releaseDate}
        title?.let{ n_title = title}

        nowplayItemList.add(
            NowplayItem(n_adult,n_backdropPath,n_oLanguage,n_oTitle,n_overview,n_posterPath,n_releaseDate,n_title)
        )
    }

}


