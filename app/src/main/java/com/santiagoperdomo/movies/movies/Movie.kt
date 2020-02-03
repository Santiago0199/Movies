package com.santiagoperdomo.movies.movies

import java.io.Serializable

class Movie(title: String, overview: String, country: String, vote_average: Double): Serializable {

    var title: String
    var overview: String
    var country: String
    var vote_average: Double

    init {
        this.title = title
        this.country = country
        this.overview = overview
        this.vote_average = vote_average
    }

}