package com.example.mvvm_tmdb.model.datamodel

import com.example.mvvm_tmdb.model.response.MovieResponse
import com.example.mvvm_tmdb.model.response.SearchResponse
import com.example.mvvm_tmdb.model.service.MovieService
import io.reactivex.Single

class MovieDataModelImpl(private val service: MovieService): MovieDataModel {

    /*val API_KEY = "dd3529cb48a78d9d2e775be63596398a"*/
    override fun getMovieData(api_key: String, language: String, page: Int, region:String): Single<MovieResponse> {
        return service.getNowMovie(api_key = api_key, language = language, page = page, region=region )
    }

    override fun getSearchData(api_key: String, language: String, query: String, page: Int, region: String): Single<SearchResponse> {
        return service.getSearchMovie(api_key = api_key, language = language,query = query, page = page, region=region)
    }


}