package com.santiagoperdomo.movies.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.santiagoperdomo.movies.R

class MoviesAdapter(listMovies: List<Movie>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>(), View.OnClickListener{

    val listMovies: List<Movie>
    lateinit var listenerClick:View.OnClickListener

    init {
        this.listMovies = listMovies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_movies, parent, false)
        view.setOnClickListener(this)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.txtTitle.setText(movie.title)
        holder.txtCountry.setText(movie.country)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun initOnClickListener(listenerClick: View.OnClickListener){
        this.listenerClick = listenerClick
    }

    override fun onClick(p0: View?) {
        this.listenerClick.onClick(p0)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var txtTitle: TextView
        var txtCountry: TextView

        init {
            txtTitle = view.findViewById(R.id.txtTitle)
            txtCountry = view.findViewById(R.id.txtCountry)
        }
    }

}