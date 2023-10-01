package com.demo.firebase.storage.addNewBook

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demo.firebase.storage.databinding.AddNewBookActivityBinding
import com.google.firebase.firestore.FirebaseFirestore

class AddNewBookActivity : AppCompatActivity() {
    private lateinit var binding: AddNewBookActivityBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddNewBookActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleClick()
    }

    private fun handleClick() {
        binding.btnAddBook.setOnClickListener {
            handleStoreData()
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun handleStoreData() {
        // Create a new user with a first and last name
        val book = hashMapOf(
            "bookName" to binding.edtBook.text.toString(),
            "author" to binding.edtAuthor.text.toString()
        )

        // Add a new document with a generated ID
        db.collection("books")
            .add(book)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Add book success.", Toast.LENGTH_SHORT).show()
                onBackPressed()
                Log.d("---->", "BookSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Add book failed.", Toast.LENGTH_SHORT).show()
                Log.w("---->", "Error adding book", e)
            }
    }

}