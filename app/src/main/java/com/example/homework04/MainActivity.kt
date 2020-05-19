package com.example.homework04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


data class MovieItem (val movieName: String, val movieDescription: String)


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)

        val request = ServiceBuilder.buildService(MovieDetail::class.java)
        val call = request.getMovies()
        call.enqueue(object : Callback, retrofit2.Callback<MovieInfos> {
            override fun onResponse(call: Call<MovieInfos>, response: Response<MovieInfos>) {
                if (response.isSuccessful){
                    val movieItem: MovieInfos = response.body()!!
                    val movieList = listOf(movieItem,movieItem,movieItem)
                    viewAdapter = MovieAdapter(movieList)
                    recyclerView = findViewById<RecyclerView>(R.id.movie_list).apply {
                        setHasFixedSize(true)
                        layoutManager = viewManager
                        adapter = viewAdapter
                    }
                }
            }

            override fun onFailure(call: Call<MovieInfos>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Connection Failure",Toast.LENGTH_SHORT).show()
            }

        })
    }
}

