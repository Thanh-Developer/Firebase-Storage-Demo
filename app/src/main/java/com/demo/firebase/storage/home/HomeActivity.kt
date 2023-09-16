package com.demo.firebase.storage.home

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.firebase.storage.databinding.HomeActivityBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}