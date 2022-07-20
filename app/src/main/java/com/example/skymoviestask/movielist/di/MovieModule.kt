package com.example.skymoviestask.movielist.di

import android.content.Context
import com.example.skymoviestask.movielist.network.MovieListAPI
import com.example.skymoviestask.movielist.db.MovieDao
import com.example.skymoviestask.movielist.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class)
class MovieModule {

    private val BASE_URL = "https://movies-sample.herokuapp.com/api/"

    @Provides
    fun movieAPI(retrofit: Retrofit): MovieListAPI = retrofit.create(MovieListAPI::class.java)

    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun movieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return MovieDatabase.invoke(context)
    }

    @Provides
    fun movieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }
}