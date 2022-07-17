package com.example.skymoviestask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.skymoviestask.movielist.MovieFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, MovieFragment.newInstance())
            .commit()
    }
}