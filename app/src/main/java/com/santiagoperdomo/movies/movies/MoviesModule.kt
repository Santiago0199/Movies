package com.santiagoperdomo.movies.movies

import com.santiagoperdomo.movies.http.MoviesInfoApiService
import com.santiagoperdomo.movies.http.MoviesTitleApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesModule {

    @Provides
    fun provideMoviePresenter(model: MoviesMVP.Model): MoviesMVP.Presenter{
        return MoviesPresenter(model)
    }

    @Provides
    fun provideMovieModel(repository: Repository): MoviesMVP.Model{
        return MoviesModel(repository)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(moviesTitleApiService: MoviesTitleApiService, moviesInfoApiService: MoviesInfoApiService): Repository{
        return MoviesRepository(moviesTitleApiService, moviesInfoApiService)
    }

}