package com.santiagoperdomo.movies.movies

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MoviesPresenter(moviesModel: MoviesMVP.Model): MoviesMVP.Presenter {

    lateinit var moviesView: MoviesMVP.View
    var moviesModel: MoviesMVP.Model
    private var suscription: Disposable? = null

    init {
        this.moviesModel = moviesModel
    }

    override fun setView(view: MoviesMVP.View) {
        moviesView = view
    }

    override fun loadData() {
        suscription = moviesModel.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Movie>(){
                override fun onComplete() {
                    moviesView.successRequest()
                }
                override fun onNext(movie: Movie) {
                    moviesView.updateData(movie)
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    moviesView.showToast("Error, fallo al obtener los datos")
                }
            })
    }

    override fun unsuscribe() {
        if(suscription == null || suscription!!.isDisposed){
            suscription!!.dispose()
        }
    }
}