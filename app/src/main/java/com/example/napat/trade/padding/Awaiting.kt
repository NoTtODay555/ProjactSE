package com.example.napat.trade.padding

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.napat.trade.padding.addexchange.AddExchange
import com.example.napat.trade.Profile
import com.example.napat.trade.R
import com.example.napat.trade.Sreach.RecycleView
import com.example.napat.trade.data.userXXX
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_exchange_offer.*
import kotlinx.android.synthetic.main.layout_padding.*

class Awaiting : AppCompatActivity(),View.OnClickListener {
    var userId = userXXX()
    private var mDatabase: DatabaseReference? = null
    private val fireBaseAuth = FirebaseAuth.getInstance()
    private val user = fireBaseAuth.currentUser
    override fun onClick(p0: View?) {
        when(p0){
            back_button -> {
                this.finish()
                startActivity(Intent(this, Profile::class.java))
            }
            add_padding -> {
                this.finish()
                startActivity(Intent(this, AddExchange::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_offer)
        back_button.setOnClickListener(this)
        add_padding.setOnClickListener(this)
        mDatabase = FirebaseDatabase.getInstance().reference
        TEXTBAR.text = "Awaiting Exchange"
        callDataUser()
        rv_padding.apply {
            layoutManager = LinearLayoutManager(this@Awaiting)
            val recyclableAdapter = RecycleView(this@Awaiting,userId.paddingException,"1")
            adapter = recyclableAdapter
        }

    }
    private fun callDataUser(){
        user!!.uid?.let {
            mDatabase!!.child("users").child(it).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(userXXX::class.java)
                    if (user == null) {
                        Toast.makeText(this@Awaiting, "onDataChange: User data is null!", Toast.LENGTH_SHORT).show()
                        return
                    }
                    userId = user
                    rv_padding.apply {
                        layoutManager = LinearLayoutManager(this@Awaiting)
                        val recyclableAdapter = RecycleView(this@Awaiting,userId.paddingException,"1")
                        adapter = recyclableAdapter
                    }
                }
                override fun onCancelled(error: DatabaseError) {}
            })
        }

    }
}
