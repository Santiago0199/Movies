package com.santiagoperdomo.movies.root

import android.app.Application
import android.content.Context
import com.santiagoperdomo.movies.http.MovieTitleApiModule
import com.santiagoperdomo.movies.http.MoviesInfoApiModule
import com.santiagoperdomo.movies.movies.MoviesModule

class MyApp: Application() {

    lateinit var component: ApplicationComponent

    companion object{
        private lateinit var instance: MyApp

        fun getContext(): Context? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .moviesModule(MoviesModule())
            .movieTitleApiModule(MovieTitleApiModule())
            .moviesInfoApiModule(MoviesInfoApiModule())
            .build()
    }

}