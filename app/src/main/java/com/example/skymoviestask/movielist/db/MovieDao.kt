package com.example.skymoviestask.movielist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.skymoviestask.movielist.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(movie: List<Movie>)

    @Query("SELECT * FROM movie")
    suspend fun getAllRecords(): List<Movie>

    @Query("DELETE FROM movie")
    suspend fun deleteAllRecords()
}