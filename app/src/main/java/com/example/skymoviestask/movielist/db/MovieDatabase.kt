package com.example.skymoviestask.movielist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.skymoviestask.movielist.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao

    companion object {
        @Volatile private var instance : MovieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "moviedatabase"
        ).build()
    }
}
