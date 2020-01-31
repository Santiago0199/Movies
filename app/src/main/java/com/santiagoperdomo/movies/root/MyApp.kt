package com.santiagoperdomo.movies.root

import android.app.Application

class MyApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(this))
                    .build()
    }

}