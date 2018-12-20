package com.example.napat.trade.Login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.napat.trade.Profile
import com.example.napat.trade.R
import com.example.napat.trade.data.userXXX
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login_main.*

class Login : AppCompatActivity(), android.view.View.OnClickListener {
    private var mDatabase: DatabaseReference? = null
    private var mUserDetailReference: DatabaseReference? = null
    private var mUserDetailListener: ChildEventListener? = null
    private var user: FirebaseUser? = null
    val messageList = ArrayList<userXXX>()
    override fun onClick(p0: android.view.View?) {
       when(p0){
           sign_in_button -> {
               this.finish()
               startActivity(Intent(this,SigeIn::class.java))
           }
           sign_up_button -> {
               Log.e(TAG, "createAccount: Success!")
               var email = email.text.toString().trim()
               var password = password.text.toString().trim()
               signIn(email,password)

           }
       }
    }

    private val TAG = "LoginActivity"

    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)
        sign_in_button.setOnClickListener(this)
        sign_up_button.setOnClickListener(this)
        mDatabase = FirebaseDatabase.getInstance().reference
        mUserDetailReference = FirebaseDatabase.getInstance().getReference("user")
        user = FirebaseAuth.getInstance().currentUser
        mAuth = FirebaseAuth.getInstance()

    }
    private fun dataUser(user: FirebaseUser?) {

        // User data change listener
        user!!.uid?.let {
            mDatabase!!.child("users").child(it).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.e(TAG,dataSnapshot.toString())
                    val user = dataSnapshot.getValue(userXXX::class.java)

                    if (user == null) {
                        Log.e(TAG, "onDataChange: User data is null!")
                        Toast.makeText(this@Login, "onDataChange: User data is null!", Toast.LENGTH_SHORT).show()
                        return
                    }
                    this@Login.finish()
                    this@Login.startActivity(Intent(this@Login,Profile::class.java))

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.e(TAG, "onCancelled: Failed to read user!")
                }
            })
        }
    }

    private fun firebaseListenerInit(user: FirebaseUser?) {
        val childEventListener = object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.e(TAG, "postMessages:onCancelled", p0!!.toException())
                Toast.makeText(this@Login, "Failed to load Message.", Toast.LENGTH_SHORT).show()
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                Log.e(TAG, "onChildMoved:" + p0!!.key)

                // A message has changed position
                val message = p0.getValue(userXXX::class.java)
                Toast.makeText(this@Login, "onChildMoved: " + message!!.address, Toast.LENGTH_SHORT).show()
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                Log.e(TAG, "onChildChanged:" + p0!!.key)

                // A message has changed
                val message = p0.getValue(userXXX::class.java)
                Toast.makeText(this@Login, "onChildChanged: " + message!!.address, Toast.LENGTH_SHORT).show()
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val message = p0.getValue(userXXX::class.java)
                messageList.add(message!!)
                Log.e(TAG, "onChildAdded:" + message.facebookName + p1)
                Log.e(TAG,messageList.toString())
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                Log.e(TAG, "onChildRemoved:" + p0!!.key)

                // A message has been removed
                val message = p0.getValue(userXXX::class.java)
                Toast.makeText(this@Login, "onChildRemoved: " + message!!.address, Toast.LENGTH_SHORT).show()
            }

        }
        mUserDetailReference!!.addChildEventListener(childEventListener)

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
    private fun signIn(email: String, password: String) {
        Log.e(TAG, "signIn:" + email)
        if (!validateForm(email, password)) {
            return
        }

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.e(TAG, "signIn: Success!")
                    // update UI with the signed-in user's information
                    val user = mAuth!!.currentUser
                    dataUser(user)


                } else {
                    Log.e(TAG, "signIn: Fail!", task.exception)
                    Toast.makeText(applicationContext, "Authentication failed!", Toast.LENGTH_SHORT).show()
                }

                if (!task.isSuccessful) {
                    Toast.makeText(this,"Successful",Toast.LENGTH_LONG)
                }
            }
    }
}
