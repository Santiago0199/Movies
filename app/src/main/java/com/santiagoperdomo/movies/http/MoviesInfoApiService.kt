package com.santiagoperdomo.movies.http

import com.santiagoperdomo.movies.http.models.OmdbApi
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesInfoApiService {

    @GET("/")
    fun getMovieInfo(@Query("t") t: String): Observable<OmdbApi>

}