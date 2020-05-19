package com.example.homework04

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val moviesList: List<MovieInfos>) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val movieItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false) as CardView
        return MyViewHolder(movieItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cardView = holder.cardView
        val movieItem = moviesList[position]
        cardView.findViewById<TextView>(R.id.movie_name).text = "Title: " + movieItem.title
        cardView.findViewById<TextView>(R.id.movie_description).text = movieItem.overview
        cardView.findViewById<TextView>(R.id.movie_rate).text = "Rating: " + movieItem.vote_average.toString()

        val moviePost = holder.cardView.findViewById<ImageView>(R.id.movie_post)
        val imgUrl = "https://image.tmdb.org/t/p/w500/" + moviesList[position].poster_path
        Glide.with(moviePost).load(imgUrl).into(moviePost);
    }

    override fun getItemCount() = moviesList.size
}