package com.santiagoperdomo.movies.http

import com.santiagoperdomo.movies.http.models.TopMoviesRated
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesTitleApiService {

    @GET("movie/top_rated")
    fun getMovieTopRated(@Query("language") language: String, @Query("page") page: Int): Observable<TopMoviesRated>

}