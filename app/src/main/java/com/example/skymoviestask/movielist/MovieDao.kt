package com.example.skymoviestask.movielist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(movie: List<Movie>)

    @Query("SELECT * FROM movie")
    suspend fun getMovies(): List<Movie>
}