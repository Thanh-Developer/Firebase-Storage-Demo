package com.demo.firebase.storage.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demo.firebase.storage.databinding.RegisterActivityBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * Account demo
 * thanh@gmail.com
 * 123456
 */
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: RegisterActivityBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFirebase()
        handleClick()
    }

    private fun initFirebase() {
        auth = FirebaseAuth.getInstance()
    }

    private fun handleClick() {
        binding.btnRegister.setOnClickListener {
            registerLogic()
        }
    }

    private fun registerLogic() {
        val email = binding.edtUsername.text.toString()
        val password = binding.edtPassword.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("---->", "createUserWithEmail:success")
                    val user = auth.currentUser
                    openLoginActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("---->", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}