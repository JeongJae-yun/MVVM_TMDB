package com.example.mvvm_tmdb.ui.Now_Play

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvm_tmdb.R
import com.example.mvvm_tmdb.adapter.MovieNowPlayAdapter
import com.example.mvvm_tmdb.base.BaseViewFragment
import com.example.mvvm_tmdb.databinding.FragNowplayBinding
import kotlinx.android.synthetic.main.frag_nowplay.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class NowPlayFragment : BaseViewFragment<FragNowplayBinding, NowPlayViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.frag_nowplay

    override val viewModel: NowPlayViewModel by viewModel()

    private val movieNowPlayAdapter : MovieNowPlayAdapter by inject()

    override fun initStartView() {
        movie_activity_recycler_view.run {
            adapter = movieNowPlayAdapter
            layoutManager = GridLayoutManager(context,2).apply {
                orientation = GridLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }



    override fun initDataBinding() {
        movieNowPlayAdapter.refresh()
        viewModel.movieResponseLiveData.observe(viewLifecycleOwner, Observer {
            it.results.forEach {
                movieNowPlayAdapter.addNowPlayItem(it.id,it.adult, it.backdrop_path, it.original_language, it.original_title, it.overview, it.poster_path, it.release_date, it.title)
            }
            movieNowPlayAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        //여기서 클릭 리스너 설정
        val API_KEY = "84301bd818cef2f63643e7dffa8998ab"
        val lang : String = "ko-KR"
        val region : String = "kr"
        viewModel.getMovieData(API_KEY, lang,1, region)
    }

    /*
    * 496243
    * */



}
