package com.example.mvvm_tmdb.ui.Search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_tmdb.R
import com.example.mvvm_tmdb.adapter.MovieSearchAdapter
import com.example.mvvm_tmdb.base.BaseViewFragment
import com.example.mvvm_tmdb.databinding.FragSearchBinding
import kotlinx.android.synthetic.main.frag_search.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseViewFragment<FragSearchBinding, SearchViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.frag_search
    override val viewModel: SearchViewModel by viewModel()

    private val movieSearchAdapter : MovieSearchAdapter by inject()

    override fun initStartView() {
        //adapter 연결
        val linearLayoutManager = LinearLayoutManager(this@SearchFragment.context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        search_frag_recycler_view.run {
            adapter = movieSearchAdapter
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        movieSearchAdapter.refresh()
        viewModel.searchResponseLiveData.observe(this, Observer {
            it.results.forEach { item->
                movieSearchAdapter.addSearchItem(
                    item.overview,
                    item.poster_path,
                    item.release_date,
                    item.title
                )
            }
            movieSearchAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        //여기서 클릭 리스너 설정
        val API_KEY = "dd3529cb48a78d9d2e775be63596398a"
        val lang : String = "ko-KR"
        val region : String = "kr"
        btnSearch.setOnClickListener {
            viewModel.getSearchMovie(API_KEY, lang, etKeyword.text.toString(),1, region)
        }
    }




}
