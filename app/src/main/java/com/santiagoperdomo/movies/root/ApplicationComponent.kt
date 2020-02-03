package com.santiagoperdomo.movies.root

import com.santiagoperdomo.movies.movies.MoviesActivity
import com.santiagoperdomo.movies.http.MovieTitleApiModule
import com.santiagoperdomo.movies.http.MoviesInfoApiModule
import com.santiagoperdomo.movies.movies.MoviesModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =
    [ApplicationModule::class,
        MoviesModule::class,
        MovieTitleApiModule::class,
        MoviesInfoApiModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MoviesActivity)

}