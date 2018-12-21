package com.example.napat.trade

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.napat.trade.Login.Login
import com.example.napat.trade.Sreach.Sreach
import com.example.napat.trade.data.userXXX
import com.example.napat.trade.padding.Awaiting
import com.example.napat.trade.request.Requested
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.layoutmain.*

class Profile : AppCompatActivity(),View.OnClickListener {
    private val fireBaseAuth = FirebaseAuth.getInstance()!!
    private val user = fireBaseAuth.currentUser
    private val databaseReference = FirebaseDatabase.getInstance().reference
    override fun onClick(p0: View?) {
        when (p0) {
            search_bar_main -> {
                this.finish()
                startActivity(Intent(this, Sreach::class.java))
            }
            requested -> {
                this.finish()
                startActivity(Intent(this, Requested::class.java))
            }
            panding -> {
                this.finish()
                startActivity(Intent(this, Padding::class.java))
            }
            awaiting -> {
                this.finish()
                startActivity(Intent(this, Awaiting::class.java))
            }
            completed -> {
                this.finish()
                startActivity(Intent(this, Completed::class.java))
            }
            suggestions -> {
                this.finish()
                startActivity(Intent(this, Suggestions::class.java))
            }
            singOut -> {
                fireBaseAuth.signOut()
                this.finish()
                startActivity(Intent(this, Login::class.java))
            }
            sentExchange -> {
                this.finish()
                startActivity(Intent(this, SentExchange::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        search_bar_main.setOnClickListener(this)
        dataUser(user)
        requested.setOnClickListener(this)
        panding.setOnClickListener(this)
        awaiting.setOnClickListener(this)
        completed.setOnClickListener(this)
        suggestions.setOnClickListener(this)
        singOut.setOnClickListener(this)
        sentExchange.setOnClickListener (this)


    }

    private fun dataUser(user: FirebaseUser?) {

        // User data change listener
        user!!.uid?.let {
            databaseReference!!.child("users").child(it).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(userXXX::class.java)
                    textView6.text = user?.surname
                    textView7.text = user?.address
                    tel_profile.text = user?.telephone
                    FB_Profile.text = user?.facebookName
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }
}
