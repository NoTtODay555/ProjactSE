package com.example.napat.trade.detail

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.napat.trade.R
import com.example.napat.trade.SentExchange
import com.example.napat.trade.data.figerAll
import com.example.napat.trade.data.userXXX
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.item_crad.view.*
import java.util.*

class ViewHoldersDetail(val view: View,var context : Activity) : RecyclerView.ViewHolder(view){
    private var mDatabase: DatabaseReference? = null
    private var mUserDetailReference: DatabaseReference? = null
    private var user: FirebaseUser? = null
    private var figer = figerAll()
    var userId = userXXX()
    fun onBindData(figerAll: figerAll, y: String ,types : String) {
        val optional = RequestOptions().error(R.drawable.icon_main)
        mDatabase = FirebaseDatabase.getInstance().reference
        mUserDetailReference = FirebaseDatabase.getInstance().getReference("users")
        user = FirebaseAuth.getInstance().currentUser
        callDataFiger(types,figerAll)
        dataUser(user)
        Log.e("tset",figerAll.toString())
        view.name_itme.text = figerAll?.itemName
        view.ex_con.text = figerAll.ExchangeConditions
        if (figerAll.image.isNotEmpty()){
            Glide.with(view).load(figerAll?.image[0]).apply(optional)
                .into(view.item_image)
        }

        view.setOnClickListener{
            FirebaseDatabase.getInstance().reference.child("figer").child(UUID.randomUUID().toString()).setValue(figer)
            context.finish()
            val i = Intent(context, SentExchange::class.java)
            context.startActivity(i)

        }


    }

    private fun dataUser(user: FirebaseUser?) {

        // User data change listener
        user!!.uid?.let {
            mDatabase!!.child("users").child(it).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(userXXX::class.java)
                    userId = user!!

                }
                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }

    fun callDataFiger(x: String, figerAll: figerAll){
        mUserDetailReference = FirebaseDatabase.getInstance().getReference("figer/"+x+"/")
        val childEventListener = object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val message = p0.getValue(com.example.napat.trade.data.figerAll::class.java)
                if(figerAll.itemName == message?.itemName){
                    figer = message
                    Toast.makeText(context,userId.password,Toast.LENGTH_LONG)
                    userId.paddingException.remove(figer)
                    userId.sentExchange.add(figer)
                    userId.request.add(figer)
                    user?.uid?.let { it1 -> FirebaseDatabase.getInstance().reference.child("user").child(it1).setValue(userId) }
                }

            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }
        }
        mUserDetailReference!!.addChildEventListener(childEventListener)
    }

}