package com.luna.flixster

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

@Parcelize

data class Movie (
    val movieId: Int,
    private val subURL: String,
    val title: String,
    val overview: String,
        ) : Parcelable
{
    @IgnoredOnParcel
    val posterImageURL = "https://image.tmdb.org/t/p/w500/$subURL"
    companion object {
        fun fromJsonArray(arr: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for(i in 0 until arr.length()){
                val movieJson = arr.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview")
                    )
                )
            }
            return movies
        }
    }
}
