package com.luna.flixster

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

const val MOVIE_EXTRA = "MOVIE_EXTRA"
class MovieAdapter(private val context : Context, private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val posterImg = itemView.findViewById<ImageView>(R.id.posterImg)
        private val movieName = itemView.findViewById<TextView>(R.id.movieName)
        private val movieSummary = itemView.findViewById<TextView>(R.id.movieSummary)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(movie: Movie){
            movieName.text = movie.title
            movieSummary.text = movie.overview
            Glide.with(context)
                .load(movie.posterImageURL)
                    .centerCrop()
                    .transform(RoundedCornersTransformation(30,10))
                .into(posterImg)
        }

        override fun onClick(p0: View?) {
        }
    }
}