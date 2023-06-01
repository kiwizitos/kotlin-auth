package com.kiwizitos.login.features

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.kiwizitos.login.core.Validator
import com.kiwizitos.login.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        var currentUser = auth.currentUser

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        val emailField = binding.edtRegisterEmail
        val passwordField = binding.edtRegisterPassword
        val confirmPasswordField = binding.edtPasswordConfirm
        val btnGotoLogin = binding.txtLogin
        val btnRegister = binding.btnSubmitRegister


        btnGotoLogin.setOnClickListener {
            finish()
        }

        btnRegister.setOnClickListener {
            Validator().validateRegister(emailField, passwordField, confirmPasswordField)

            auth.createUserWithEmailAndPassword(
                emailField.text.toString(), passwordField.text.toString()
            ).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }
    }
}