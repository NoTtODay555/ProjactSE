package com.example.napat.trade

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.napat.trade.Login.Login
import com.example.napat.trade.Sreach.Sreach
import com.example.napat.trade.padding.Padding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.layoutmain.*

class Profile : AppCompatActivity(),View.OnClickListener {
    private val fireBaseAuth = FirebaseAuth.getInstance()!!
    private val user = fireBaseAuth.currentUser
    private val databaseReference = FirebaseDatabase.getInstance().reference
    override fun onClick(p0: View?) {
        when(p0){
            search_bar_main -> {
                this.finish()
                startActivity(Intent(this, Sreach::class.java))
            }
            requested -> {
            }
            panding -> {
                this.finish()
                startActivity(Intent(this, Padding::class.java))
            }
            awaiting -> {
                this.finish()
                startActivity(Intent(this,WaittingConfirmation::class.java))
            }
            completed -> {
                this.finish()
                startActivity(Intent(this,Completed::class.java))
            }
            suggestions -> {
                this.finish()
                startActivity(Intent(this,Suggestions::class.java))
            }
            singOut -> {
                fireBaseAuth.signOut()
                this.finish()
                startActivity(Intent(this,Login::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        search_bar_main.setOnClickListener(this)
        requested.setOnClickListener(this)
        panding.setOnClickListener(this)
        awaiting.setOnClickListener(this)
        completed.setOnClickListener(this)
        suggestions.setOnClickListener(this)
        singOut.setOnClickListener(this)


    }
}
