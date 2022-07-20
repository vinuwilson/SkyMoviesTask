package com.example.skymoviestask.movielist.network

import com.example.skymoviestask.movielist.model.MovieList
import retrofit2.http.GET

interface MovieListAPI {

   @GET("movies")
   suspend fun fetchAllMovieList() : MovieList
}
