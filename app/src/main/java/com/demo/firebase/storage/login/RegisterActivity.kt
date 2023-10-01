package com.demo.firebase.storage.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demo.firebase.storage.databinding.RegisterActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

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

        if (email.isEmpty()) {
            showToast("Please input email first.")
        } else if (password.isEmpty()) {
            showToast("Please input password.")
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        finish()
//                        openLoginActivity()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("---->", "signInWithEmail:failure", task.exception)

                        when (task.exception) {
                            is FirebaseAuthUserCollisionException ->
                                showToast("The email address is already in use by another account.")

                            is FirebaseAuthWeakPasswordException ->
                                showToast("Password should be at least 6 characters.")

                            is FirebaseAuthInvalidCredentialsException ->
                                showToast("The email address is badly formatted.")

                            else ->
                                showToast(task.exception?.message ?: "")
                        }
                    }
                }
        }
    }

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}