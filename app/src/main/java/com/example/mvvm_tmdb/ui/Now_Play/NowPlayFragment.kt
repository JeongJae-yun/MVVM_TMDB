package com.example.mvvm_tmdb.ui.Now_Play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mvvm_tmdb.R
import com.example.mvvm_tmdb.adapter.MovieNowPlayAdapter
import com.example.mvvm_tmdb.base.BaseViewFragment
import com.example.mvvm_tmdb.databinding.FragNowplayBinding
import kotlinx.android.synthetic.main.frag_nowplay.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class NowPlayFragment : BaseViewFragment<FragNowplayBinding,NowPlayViewModel>() {

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


            /*layoutManager = StaggeredGridLayoutManager(2,1).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                orientation = StaggeredGridLayoutManager.VERTICAL
            }*/
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        //DataBinding/ obersing/ adapter data add

        viewModel.movieResponseLiveData.observe(this, Observer {
            it.results.forEach {
                movieNowPlayAdapter.addNowPlayItem(it.title, it.poster_path)
            }
            movieNowPlayAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        //여기서 클릭 리스너 설정
        val API_KEY = "dd3529cb48a78d9d2e775be63596398a"
        val lang : String = "ko-KR"
        viewModel.getMovieData(API_KEY, lang,1, 2)
    }


}
