package com.santiagoperdomo.movies.http.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.media.Rating

class OmdbApi {

    @SerializedName("Title")
    @Expose
    val title: String? = null
    @SerializedName("Year")
    @Expose
    val year: String? = null
    @SerializedName("Rated")
    @Expose
    val rated: String? = null
    @SerializedName("Released")
    @Expose
    val released: String? = null
    @SerializedName("Runtime")
    @Expose
    val runtime: String? = null
    @SerializedName("Genre")
    @Expose
    val genre: String? = null
    @SerializedName("Director")
    @Expose
    val director: String? = null
    @SerializedName("Writer")
    @Expose
    val writer: String? = null
    @SerializedName("Actors")
    @Expose
    val actors: String? = null
    @SerializedName("Plot")
    @Expose
    val plot: String? = null
    @SerializedName("Language")
    @Expose
    val language: String? = null
    @SerializedName("Country")
    @Expose
    val country: String? = null
    @SerializedName("Awards")
    @Expose
    val awards: String? = null
    @SerializedName("Poster")
    @Expose
    val poster: String? = null
    @SerializedName("Ratings")
    @Expose
    val ratings: List<Rating>? = null
    @SerializedName("Metascore")
    @Expose
    val metascore: String? = null
    @SerializedName("imdbRating")
    @Expose
    val imdbRating: String? = null
    @SerializedName("imdbVotes")
    @Expose
    val imdbVotes: String? = null
    @SerializedName("imdbID")
    @Expose
    val imdbID: String? = null
    @SerializedName("Type")
    @Expose
    val type: String? = null
    @SerializedName("DVD")
    @Expose
    val dVD: String? = null
    @SerializedName("BoxOffice")
    @Expose
    val boxOffice: String? = null
    @SerializedName("Production")
    @Expose
    val production: String? = null
    @SerializedName("Website")
    @Expose
    val website: String? = null
    @SerializedName("Response")
    @Expose
    val response: String? = null

}