package com.acha.haha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 9001
    private var googleSignInClient: GoogleSignInClient?=null
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("214277382494-c628fogpbpcr53i7r2950ek2k4hs8o6p.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val signInGoogleBtn: SignInButton = findViewById(R.id.sign_in_button)

        signInGoogleBtn.setOnClickListener{
            googleSignInClient = GoogleSignIn.getClient(this, gso)
            var signInIntent = googleSignInClient!!.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onDestroy(){
        super.onDestroy()
        firebaseAuthSignOut()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    val user = auth.currentUser
                    user?.let{
                        val name = user.displayName
                        val email = user.email
                        val displayName = user.displayName
                        val photoUrl = user.photoUrl
                        val emailVerified = user.isEmailVerified
                        val uid = user.uid
                        Log.d("xxxx name", name.toString())
                        Log.d("xxxx email", email.toString())
                        Log.d("xxxx displayName", displayName.toString())
                    }
                } else{
                    Log.d("xxxx", "signInWithCredential:failure", task.exception)
                }
            }
    }

    private fun firebaseAuthSignOut(){
        Firebase.auth.signOut()
    }
}