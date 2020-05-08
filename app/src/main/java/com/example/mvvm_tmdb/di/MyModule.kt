package com.example.mvvm_tmdb.di

import com.example.mvvm_tmdb.adapter.MovieNowPlayAdapter
import com.example.mvvm_tmdb.adapter.MovieSearchAdapter
import com.example.mvvm_tmdb.model.datamodel.MovieDataModel
import com.example.mvvm_tmdb.model.datamodel.MovieDataModelImpl
import com.example.mvvm_tmdb.model.service.MovieService
import com.example.mvvm_tmdb.ui.Now_Play.NowPlayViewModel
import com.example.mvvm_tmdb.ui.Search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitMovie = module{
    single<MovieService>{
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }
}

var movieAdapter = module{
    factory {
    MovieNowPlayAdapter()
}
}

var searchAdapter = module {
    factory {
        MovieSearchAdapter()
    }
}

var modelMovie = module {
    factory<MovieDataModel>{
        MovieDataModelImpl(get())
    }
}

var viewMovieModel = module{
    viewModel {
        NowPlayViewModel(get())
    }
}

var viewSearchModel = module {
    viewModel {
        SearchViewModel(get())
    }
}


var myDiModule
        = listOf(retrofitMovie, movieAdapter,  searchAdapter, modelMovie,  viewMovieModel, viewSearchModel)