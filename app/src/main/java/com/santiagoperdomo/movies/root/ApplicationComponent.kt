package com.santiagoperdomo.movies.root

import com.santiagoperdomo.movies.MainActivity
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

}