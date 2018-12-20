package com.example.napat.trade.Login

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.napat.trade.R
import com.example.napat.trade.data.User
import com.example.napat.trade.data.userXXX
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sige_in.*

class SigeIn : AppCompatActivity() {

    private val fireBaseAuth = FirebaseAuth.getInstance()
    private val user = fireBaseAuth.currentUser
    private val databaseReference = FirebaseDatabase.getInstance().reference
    private val progressDialog: ProgressDialog? = null
    private var mAuth: FirebaseAuth? = null
    private val TAG = "LoginActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sige_in)


        bt_signUp.setOnClickListener {
            mAuth = FirebaseAuth.getInstance()
            val user = mAuth!!.currentUser
            val username = et_username.text.toString().trim()
            val password = et_password.text.toString().trim()
            val surname = et_surname.text.toString().trim()
            val tele = et_tele.text.toString().trim()
            val facebookName = et_facebook.text.toString().trim()
            val address = et_address.text.toString().trim()
            val district = et_District.text.toString().trim()
            val provice = et_provice.text.toString().trim()
            val zipcode = et_zipCode.text.toString().trim()
            val idCard = et_idCard.text.toString().trim()
            val Username  = userXXX("0",username,password,surname,tele,facebookName, address, district, provice,zipcode,idCard)
            createAccount(username,password,Username)

        }
    }
    private fun registerUser(
        Username: userXXX,
        user: FirebaseUser?
    ) {
        progressDialog?.setMessage("Registering Please Wait...")
        progressDialog?.show()
//        val userValues = Username.toMap()
//        val chuildUpdates = HashMap<String,Any>()
//        val key = databaseReference!!.child("user").push().key

        user?.uid?.let { FirebaseDatabase.getInstance().reference.child("users").child(it).setValue(Username) }
    }
    private fun createAccount(
        email: String,
        password: String,
        username: userXXX
    ) {
        Log.e(TAG, "createAccount:" + (user?.uid ?: 0) + username.id)
        if (!validateForm(email, password)) {
            return
        }

        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.e(TAG, "createAccount: Success!")
                    val users1 = fireBaseAuth.currentUser
                    Log.e(TAG, "createAccount:" + (user?.uid ?: 0))
                    users1?.uid?.let { FirebaseDatabase.getInstance().reference.child("users").child(it).setValue(username) }
                    this.finish()
                    this.startActivity(Intent(this,Login::class.java))
                } else {
                    Log.e(TAG, "createAccount: Fail!", task.exception)
                    Toast.makeText(applicationContext, "Authentication failed!", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun writeNewUser(userId: String, username: String?, email: String?) {
        val user = User(username, email)

        FirebaseDatabase.getInstance().reference.child("users").child(userId).setValue(user)
    }
    private fun getUsernameFromEmail(email: String?): String {
        return if (email!!.contains("@")) {
            email.split("@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        } else {
            email
        }
    }
    private fun validateForm(email: String, password: String): Boolean {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(applicationContext, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }




}
