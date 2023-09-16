package com.demo.firebase.storage.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.demo.firebase.storage.databinding.HomeActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: HomeActivityBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        handleClick()
    }

    private fun initData() {
        db.collection("books")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("---->", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("---->", "Error getting documents.", exception)
            }
    }

    private fun handleClick() {
        binding.imgSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            openLoginActivity()
        }

        binding.fab.setOnClickListener {
            openAddNewBookActivity()
        }
    }

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun openAddNewBookActivity() {
        startActivity(Intent(this, AddNewBookActivity::class.java))
        finish()
    }

}