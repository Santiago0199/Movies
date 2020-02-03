package com.santiagoperdomo.movies.movies

import io.reactivex.Observable

interface MoviesMVP {

    interface View{
        fun updateData(movie: Movie)
        fun showToast(message: String)
        fun successRequest()
    }

    interface Presenter{
        fun setView(view: View)
        fun loadData()
        fun unsuscribe()
    }

    interface Model{
        fun getData(): Observable<Movie>
    }

}