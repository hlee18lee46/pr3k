package com.luna.flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    private val movies = mutableListOf<Movie>()
    private lateinit var moviePanel: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moviePanel = findViewById(R.id.moviePanel)

        val movieAdapter = MovieAdapter(this, movies)
        moviePanel.adapter = movieAdapter
        moviePanel.layoutManager = LinearLayoutManager(this) //puts movies top to bottom

        val client = AsyncHttpClient()
        client.get("https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed", object : JsonHttpResponseHandler(){
            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                try {
                    val movieJsonArray = json.jsonObject.getJSONArray("results")
                    movies.addAll(Movie.fromJsonArray(movieJsonArray))
                    movieAdapter.notifyDataSetChanged()
                }
                catch (e: JSONException){
                }
            }

        })
    }
}