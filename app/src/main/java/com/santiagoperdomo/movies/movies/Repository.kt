package com.santiagoperdomo.movies.movies

import com.santiagoperdomo.movies.http.models.Result
import io.reactivex.Observable

interface Repository {

    fun getResultData(): Observable<Result>
    fun getResultFromCache(): Observable<Result>
    fun getResultFromNetwork(): Observable<Result>

    fun getCountryData(): Observable<String>
    fun getCountryFromCache(): Observable<String>
    fun getCountryFromNetwork(): Observable<String>

}