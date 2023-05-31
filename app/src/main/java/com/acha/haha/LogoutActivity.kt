package com.acha.haha

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class LogoutActivity : AppCompatActivity() {
    var auth : FirebaseAuth ?= null
    var googleSignInClient : GoogleSignInClient ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("214277382494-c628fogpbpcr53i7r2950ek2k4hs8o6p.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = FirebaseAuth.getInstance()

        var google_sign_out_button = findViewById<Button>(R.id.sign_out_button)

        google_sign_out_button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            googleSignInClient?.signOut()

            var logoutIntent = Intent (this, LoginActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(logoutIntent)
        }
    }
}