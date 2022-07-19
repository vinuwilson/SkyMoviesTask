package com.example.skymoviestask.movielist

import android.content.Context
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

    @Provides
    fun movieAPI(retrofit: Retrofit): MovieListAPI = retrofit.create(MovieListAPI::class.java)

    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://movies-sample.herokuapp.com/api/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun movieDatabase(@ApplicationContext context: Context): MovieDatabase{
        return MovieDatabase.invoke(context)
    }

    @Provides
    fun movieDao(movieDatabase: MovieDatabase) :MovieDao{
        return movieDatabase.movieDao()
    }
}