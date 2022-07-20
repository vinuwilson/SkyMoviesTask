package com.example.skymoviestask.movielist.model

import androidx.room.Entity

@Entity
data class MovieList(
    val data: List<Movie>
)