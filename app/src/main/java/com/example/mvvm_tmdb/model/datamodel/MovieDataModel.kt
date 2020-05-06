package com.example.mvvm_tmdb.model.datamodel

import com.example.mvvm_tmdb.model.response.MovieResponse
import io.reactivex.Single

interface MovieDataModel {
    //https://api.themoviedb.org/3/movie/now_playing?api_key=dd3529cb48a78d9d2e775be63596398a&language=ko-KR&page=1
    fun getMovieData(api_key : String, language : String, page : Int, page2 : Int) : Single<MovieResponse>
}