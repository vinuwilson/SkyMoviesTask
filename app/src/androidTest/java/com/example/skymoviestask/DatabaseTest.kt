package com.example.skymoviestask

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.skymoviestask.movielist.db.MovieDao
import com.example.skymoviestask.movielist.db.MovieDatabase
import com.example.skymoviestask.utils.BaseUITest
import com.example.skymoviestask.utils.provideTestList
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest :BaseUITest() {

    private lateinit var database: MovieDatabase
    private lateinit var movieDao: MovieDao

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, MovieDatabase::class.java
        ).build()
        movieDao = database.movieDao()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadAlbum() {
        runBlocking {
            movieDao.insertRecord(provideTestList())
            val movieListFromDb = movieDao.getAllRecords()
            assert(movieListFromDb.size == 3)
            assert(movieListFromDb[0].id == provideTestList()[0].id)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }
}