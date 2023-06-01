package com.kiwizitos.login.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.kiwizitos.login.R
import com.kiwizitos.login.databinding.ActivityHomeBinding
import com.kiwizitos.login.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()
        var currentUser = auth.currentUser

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (currentUser == null ) {
            finish()
        }

        val txtUser = binding.txtUser
        val btnExit = binding.btnExit

        txtUser.text = currentUser?.email

        btnExit.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}