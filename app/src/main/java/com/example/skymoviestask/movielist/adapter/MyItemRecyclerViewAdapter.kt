package com.example.skymoviestask.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skymoviestask.databinding.FragmentMovieBinding
import com.example.skymoviestask.loadImage
import com.example.skymoviestask.movielist.model.Movie

class MyItemRecyclerViewAdapter(
    private val values: List<Movie>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>(), Filterable {

    var movieList = values

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filteredMovieList = values.filter {
                    it.genre.contains(
                        constraint,
                        ignoreCase = true
                    ) || it.title.contains(constraint, ignoreCase = true)
                }
                val filterResults = FilterResults()
                filterResults.values = filteredMovieList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                movieList = results?.values as List<Movie>
                notifyDataSetChanged()
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movieList[position]
        holder.movieImage.loadImage(item.poster)
        holder.movieTitle.text = item.title
        holder.movieGenre.text = item.genre
        holder.movieYear.text = item.year
    }

    override fun getItemCount(): Int = movieList.size

    fun filterList(newText: String?) {
        movieList.filter { it.genre.contains(newText!!, ignoreCase = true) }
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: FragmentMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val movieImage: ImageView = binding.movieImage
        val movieTitle: TextView = binding.movieTitle
        val movieGenre: TextView = binding.movieGenre
        val movieYear: TextView = binding.movieYear

    }

}