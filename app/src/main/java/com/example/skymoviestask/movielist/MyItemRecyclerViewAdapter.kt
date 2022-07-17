package com.example.skymoviestask.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skymoviestask.databinding.FragmentMovieBinding

class MyItemRecyclerViewAdapter(
    private val values: List<Data>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
//        holder.movieImage.seti = item.id
        holder.movieTitle.text = item.title
        holder.movieGenre.text = item.genre
        holder.movieYear.text = item.year
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val movieImage: ImageView = binding.movieImage
        val movieTitle: TextView = binding.movieTitle
        val movieGenre: TextView = binding.movieGenre
        val movieYear: TextView = binding.movieYear

    }

}