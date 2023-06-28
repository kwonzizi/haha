package com.acha.haha
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

        var register_button = findViewById<Button>(R.id.regi_button)

        register_button.setOnClickListener {
            var regiIntent = Intent (this, Register::class.java)
            startActivity(regiIntent)
        }

        var loginButton = findViewById<Button>(R.id.log_in_button)
        var idEditText = findViewById<EditText>(R.id.id_EditText)
        var passwordEditText = findViewById<EditText>(R.id.password_EditText)
        loginButton.setOnClickListener {
            signIn(idEditText.text.toString(),passwordEditText.text.toString())
        }
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
    public override fun onStart() {
        super.onStart()
        moveMainPage(auth?.currentUser)
    }
    private fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    startActivity(Intent(this, MainActivity::class.java))
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
    // 로그인
    private fun signIn(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "로그인에 성공하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        moveMainPage(auth?.currentUser)
                    } else {
                        Toast.makeText(
                            baseContext, "로그인에 실패하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    // 유저정보 넘겨주고 메인 액티비티 호출
    fun moveMainPage(user: FirebaseUser?){
        if( user!= null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}