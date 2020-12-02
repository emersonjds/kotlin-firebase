package com.example.meuprimeiroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    //    on ready
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLogin.setOnClickListener { login() }
        tvForgotPassword.setOnClickListener { forgotPassword() }
        tvSignIn.setOnClickListener { signIn() }
    }

    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPassword.text.toString()

        if (email.isBlank()) {
            Toast.makeText(this, getString(R.string.email_required), Toast.LENGTH_LONG).show()
            return
        }

        if (pass.isBlank()) {
            Toast.makeText(this, "a senha é obrigatoria", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass.length < 6) {
            Toast.makeText(this, "A senha deve ter mais de 5 digitos", Toast.LENGTH_SHORT).show()
            return
        }

        //CONNECT FIREBASE
        val auth = FirebaseAuth.getInstance()
        val operation = auth.signInWithEmailAndPassword(email, pass)
        operation.addOnCompleteListener { result ->
            if (result.isSuccessful) {
                val intentHome = Intent(this, HomeActivity::class.java)
                startActivity(intentHome)
            } else {
                Toast.makeText(this, "Erro na autenticação: ${result.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun forgotPassword() {

    }

    private fun signIn() {
        val intentSignIn = Intent(this, SignInActivity::class.java)
        startActivity(intentSignIn)
    }
}