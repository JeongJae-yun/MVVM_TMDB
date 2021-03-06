package com.example.mvvm_tmdb.ui.Detail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.Observer
import com.example.mvvm_tmdb.R
import com.example.mvvm_tmdb.base.BaseViewActivity
import com.example.mvvm_tmdb.databinding.ActivityDetailInfoBinding
import com.example.mvvm_tmdb.eventbus.BusProvider
import com.example.mvvm_tmdb.ui.ImageSearch.ImageActivity
import com.example.mvvm_tmdb.util.imageLoad
import kotlinx.android.synthetic.main.activity_detail_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailInfo : BaseViewActivity<ActivityDetailInfoBinding, DetailViewModel>() {
    private var m_id : Int = 0
    private var listGenre = ""
    private var movie_title = ""
    private var date = ""
    private var photoPath = ""

    companion object{
        var util = com.example.mvvm_tmdb.util.util()
        var imgLoad = imageLoad()
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_detail_info

    override val viewModel: DetailViewModel by viewModel()

    override fun initStartView() {
        getInit()
    }

    @SuppressLint("SetTextI18n")
    override fun initDataBinding() {
        viewModel.detailResponseLiveData.observe(this, Observer { it ->
            movie_title = it.title
            tvTitle.text = movie_title

            tvOverview.text = it.overview

            date = it.release_date
            tvReleaseDate.text = date

            tvRuntime.text = it.runtime.toString() +"분"
            tvVoteAverage.text = it.vote_average.toString()
            tvVoteCount.text = it.vote_count.toString()
            tvNowAdult.text = it.adult.toString()
            it.genres.forEach {
                listGenre +=  "#"+it.name+" "
            }
            tvGenre.text = listGenre

            val b_path = util.changeNowMovieUrl(it.backdrop_path)
            val p_path = util.changeNowMovieUrl(it.poster_path)

            photoPath = p_path

            imgLoad.backgroundLoad(ivBackDrop,this,b_path)
            imgLoad.mainLoad(ivMainPoster,this,p_path)
        })
    }

    override fun initAfterBinding() {
        val API_KEY = "84301bd818cef2f63643e7dffa8998ab"
        val movie_id = m_id
        val language = "ko-KR"
        viewModel.getDetailData(movie_id,API_KEY,language)

        btnMoreImage.setOnClickListener {
            val intent = Intent(applicationContext, ImageActivity::class.java)
            intent.putExtra("id", m_id)
            startActivity(intent)
        }

        btnAddFavorite.setOnClickListener {
            val intent = Intent(applicationContext, ImageActivity::class.java)
            intent.putExtra("id", m_id)
            intent.putExtra("title", movie_title)
            intent.putExtra("date", date)
            intent.putExtra("photoPath", photoPath)
            startActivity(intent)
        }
    }



    fun getInit(){
        intent.extras?.let {
            m_id = it.getInt("id")
            BusProvider.setId(m_id)
        }
    }
}
