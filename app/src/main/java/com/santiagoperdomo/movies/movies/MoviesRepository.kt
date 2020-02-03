package com.santiagoperdomo.movies.movies

import android.view.ActionMode
import com.santiagoperdomo.movies.http.MoviesInfoApiService
import com.santiagoperdomo.movies.http.MoviesTitleApiService
import com.santiagoperdomo.movies.http.models.OmdbApi
import com.santiagoperdomo.movies.http.models.Result
import com.santiagoperdomo.movies.http.models.TopMoviesRated
import io.reactivex.Observable

class MoviesRepository(moviesTitleApiService: MoviesTitleApiService, moviesInfoApiService: MoviesInfoApiService): Repository {

    private val moviesTitleApiService: MoviesTitleApiService
    private val moviesInfoApiService: MoviesInfoApiService
    private var lastTimesTamp: Long
    private val CACHE_LIFETIME: Long
    private var listResults: ArrayList<Result>
    private var listCountrys: ArrayList<String>
    private var actionMode: ActionMode? = null

    init {
        this.moviesTitleApiService = moviesTitleApiService
        this.moviesInfoApiService = moviesInfoApiService
        lastTimesTamp = System.currentTimeMillis()
        CACHE_LIFETIME = 20 * 1000
        listResults = ArrayList()
        listCountrys = ArrayList()
    }

    private fun isUpdated(): Boolean{
        return (System.currentTimeMillis() - lastTimesTamp) < CACHE_LIFETIME
    }

    override fun getResultData(): Observable<Result> {
        return getResultFromCache().switchIfEmpty(getResultFromNetwork())
    }

    override fun getResultFromCache(): Observable<Result> {
        return if (isUpdated()){
            Observable.fromIterable(listResults)
        }else{
            lastTimesTamp = System.currentTimeMillis()
            listResults.clear()
            Observable.empty()
        }
    }

    override fun getResultFromNetwork(): Observable<Result> {
        return moviesTitleApiService.getMovieTopRated("en-US", 1).concatMap { topMoviesRated ->
            Observable.fromIterable(topMoviesRated.results)
        }.doOnNext { result ->
            listResults.add(result)
        }
    }


    override fun getCountryData(): Observable<String> {
        return getCountryFromCache().switchIfEmpty(getCountryFromNetwork())
    }

    override fun getCountryFromCache(): Observable<String> {
        return if(isUpdated()){
            Observable.fromIterable(listCountrys)
        }else{
            lastTimesTamp = System.currentTimeMillis()
            listCountrys.clear()
            Observable.empty()
        }
    }

    override fun getCountryFromNetwork(): Observable<String> {
        return getResultFromNetwork().concatMap { result ->
            moviesInfoApiService.getMovieInfo(result.title!!).concatMap { OmdbApi ->
                if(OmdbApi.title == null || OmdbApi.title == ""){
                    Observable.just("Desconocido")
                }else{
                    Observable.just(OmdbApi.country)
                }
            }.doOnNext { title ->
                listCountrys.add(title)
            }
        }
    }
}