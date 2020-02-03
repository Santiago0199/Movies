package com.santiagoperdomo.movies.movies

import com.santiagoperdomo.movies.http.models.Result
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class MoviesModel(repository: Repository): MoviesMVP.Model {

    var repository: Repository

    init {
        this.repository = repository
    }

    override fun getData(): Observable<Movie> {
        return Observable.zip(repository.getResultData(), repository.getCountryData(),
            BiFunction<Result, String, Movie> { result, country ->
                Movie(result.title!!, result.overview!!, country, result.voteAverage!!)
            })
    }
}