package com.example.mvvm_tmdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_tmdb.R
import com.example.mvvm_tmdb.model.data.ImageItem
import kotlinx.android.synthetic.main.item_search_image.view.*

class ImageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val util = com.example.mvvm_tmdb.util.util()
    }

    private val imageItemList = ArrayList<ImageItem>()

    class imageHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_search_image, parent, false)
    ) {
        fun onBind(item: ImageItem) {
            itemView.run {
                search_image_item.run {
                    val urls = util.changeNowMovieUrl(item.file_path)
                    Glide.with(this)
                        .load(urls)
                        .override(item.width,item.height)
                        .fitCenter()
                        .placeholder(R.drawable.phimg)
                        .into(this)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        imageHolder(
            parent
        )

    override fun getItemCount(): Int = imageItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? imageHolder)?.onBind(imageItemList[position])
    }

    fun refresh(){
        imageItemList.clear()
    }

    fun addImageItem(
        file_path: String,
        width: Int,
        height: Int
    ) {
        imageItemList.add(
            ImageItem(file_path, width, height)
        )
    }

}