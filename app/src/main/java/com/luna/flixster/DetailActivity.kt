package com.luna.flixster

import android.os.Bundle
import android.widget.TextView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.android.youtube.player.YouTubeBaseActivity
import okhttp3.Headers

class DetailActivity : YouTubeBaseActivity() {
    private lateinit var movieTitle: TextView
    private lateinit var movieDesc: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        movieTitle = findViewById(R.id.movieTitle)
        movieDesc = findViewById(R.id.movieDesc)
        val movie = intent.getParcelableExtra<Movie>(MOVIE_EXTRA) as Movie
        movieTitle.text = movie.title
        movieDesc.text = movie.overview

        val client = AsyncHttpClient()
        client.get("https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed".format(movie.movieId), object: JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
            }
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                val results = json.jsonObject.getJSONArray("results")
                if (results.length() == 0){
                    return
                }
            }
        })
    }


}