package com.example.skymoviestask.movielist

import retrofit2.http.GET

interface MovieListAPI {

   @GET("movies")
   suspend fun fetchAllMovieList() : MovieList
}
