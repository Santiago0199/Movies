package com.santiagoperdomo.movies.movies

import android.app.Dialog
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

import com.santiagoperdomo.movies.R
import com.santiagoperdomo.movies.root.Const

class MovieDetailFragment : DialogFragment() {

    private lateinit var raiting: RatingBar
    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        val view = activity!!.layoutInflater.inflate(R.layout.fragment_movie_detail, null)

        raiting = view.findViewById(R.id.ratingMovie)
        tvTitle = view.findViewById(R.id.tvTitle)
        tvDescription = view.findViewById(R.id.tvDescription)

        val movie = arguments!!.getSerializable(Const.MOVIE) as Movie

        tvTitle.setText(movie.title)
        tvDescription.setText(movie.overview)
        raiting.rating = (movie.vote_average/2).toFloat()

        builder.setView(view)
        return builder.create()
    }
}
