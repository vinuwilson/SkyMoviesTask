package com.example.skymoviestask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.skymoviestask.movielist.MovieFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}