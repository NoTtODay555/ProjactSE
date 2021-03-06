package com.example.napat.trade

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.napat.trade.Sreach.RecycleView
import com.example.napat.trade.data.userXXX
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_sent_exchange.*
import kotlinx.android.synthetic.main.layout_signin.*

class Completed : AppCompatActivity(),View.OnClickListener {
    private val fireBaseAuth = FirebaseAuth.getInstance()!!
    private val user = fireBaseAuth.currentUser
    private val databaseReference = FirebaseDatabase.getInstance().reference

    override fun onClick(p0: View?) {
        when(p0){
            imageView4 -> {
                this.finish()
                startActivity(Intent(this,Profile::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        TEXTBAR.text = "Completed"
        imageView4.setOnClickListener(this)
    }

    private fun dataUser(user: FirebaseUser?) {

        // User data change listener
        user!!.uid?.let {
            databaseReference!!.child("users").child(it).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(userXXX::class.java)
                    rv_complate.apply {
                        val recyclableAdapter = user?.sentExchange?.let { it1 ->
                            RecycleView(this@Completed,
                                it1,"1")
                        }
                        adapter = recyclableAdapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }
}
