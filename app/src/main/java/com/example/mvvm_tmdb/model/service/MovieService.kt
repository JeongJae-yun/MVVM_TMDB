package com.example.mvvm_tmdb.model.service

import com.example.mvvm_tmdb.model.response.MovieResponse
import com.example.mvvm_tmdb.model.response.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService {

    //https://api.themoviedb.org/3/movie/now_playing?api_key=dd3529cb48a78d9d2e775be63596398a&language=ko-KR&page=1&page=2

    @GET("movie/now_playing")
    fun getNowMovie(
        @Query("api_key") api_key:String,
        @Query("language") language:String,
        @Query("page") page:Int,
        @Query("region") region : String
    ): Single<MovieResponse>

    @GET("search/movie")
    fun getSearchMovie(
        @Query("api_key") api_key:String,
        @Query("language") language:String,
        @Query("query") query:String,
        @Query("page") page:Int,
        @Query("region") region : String
    ): Single<SearchResponse>


}