package com.example.napat.trade

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.napat.trade.Sreach.RecycleView
import com.example.napat.trade.data.figerAll
import com.example.napat.trade.data.userXXX
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sent_exchange.*
import kotlinx.android.synthetic.main.layout_signin.*

class SentExchange : AppCompatActivity() {
    private val fireBaseAuth = FirebaseAuth.getInstance()!!
    private var mUserDetailReference: DatabaseReference? = null
    private val user = fireBaseAuth.currentUser
    var figerList = ArrayList<figerAll>()

    private val databaseReference = FirebaseDatabase.getInstance().reference
    override fun onCreate(savedInstanceState: Bundle?) {
        val type = arrayListOf("Onepiece","SAO","Gundum","Other","Vocaloid")
        for (a in type) callDataFiger(a)
        super.onCreate(savedInstanceState)
        dataUser(user)
        setContentView(R.layout.activity_sent_exchange)
        TEXTBAR.text = "Sent Exchange"
        imageView4.setOnClickListener {
            this.finish()
            startActivity(Intent(this,Profile::class.java))
            rv_sent.apply {
                val x = ArrayList<figerAll>()
                val recyclableAdapter = RecycleView(this@SentExchange,x,"1")
                adapter = recyclableAdapter
            }
        }

    }
    private fun dataUser(user: FirebaseUser?) {

        // User data change listener
        user!!.uid?.let {
            databaseReference!!.child("users").child(it).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(userXXX::class.java)
                    rv_sent.apply {
                        layoutManager = LinearLayoutManager(this@SentExchange)
                        val recyclableAdapter = user?.sentExchange?.let { it1 ->
                            RecycleView(this@SentExchange,
                                it1,"1")
                        }
                        adapter = recyclableAdapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }
    fun callDataFiger(x : String){
        mUserDetailReference = FirebaseDatabase.getInstance().getReference("figer/"+x+"/")
        val childEventListener = object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

                // A message has changed position
                val message = p0.getValue(figerAll::class.java)
                Toast.makeText(this@SentExchange, "onChildMoved: " + message!!.itemName, Toast.LENGTH_SHORT).show()
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

                // A message has changed
                val message = p0.getValue(figerAll::class.java)
                Toast.makeText(this@SentExchange, "onChildChanged: " + message!!.itemName, Toast.LENGTH_SHORT).show()
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val message = p0.getValue(figerAll::class.java)
                figerList.add(message!!)

                rv_sent.apply {
                    val recyclableAdapter = RecycleView(this@SentExchange,figerList,"1")
                    adapter = recyclableAdapter
                }
            }

            override fun onChildRemoved(p0: DataSnapshot) {


                // A message has been removed
                val message = p0.getValue(userXXX::class.java)
                Toast.makeText(this@SentExchange, "onChildRemoved: " + message!!.address, Toast.LENGTH_SHORT).show()
            }
        }
        mUserDetailReference!!.addChildEventListener(childEventListener)
    }
}
