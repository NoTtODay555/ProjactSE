package com.example.napat.trade.detail

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.napat.trade.R
import com.example.napat.trade.Sreach.RecycleView
import com.example.napat.trade.Sreach.Sreach
import com.example.napat.trade.data.figerAll
import com.example.napat.trade.data.figerDetail
import com.example.napat.trade.data.userXXX
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail_item.*
import kotlinx.android.synthetic.main.activity_sent_exchange.*
import kotlinx.android.synthetic.main.item_select.*
import kotlinx.android.synthetic.main.layout_signin.*


class DetailItem : AppCompatActivity() {

    private var mDatabase: DatabaseReference? = null
    private var mUserDetailReference: DatabaseReference? = null
    private var mUserDetailListener: ChildEventListener? = null
    private var user: FirebaseUser? = null
    var typeItem : String =""
    var mydialof : Dialog? = null
    var item = figerAll()
    var userfiger = userXXX()
    var b = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_item)
        val iin = intent
        mDatabase = FirebaseDatabase.getInstance().reference
        user = FirebaseAuth.getInstance().currentUser
        mUserDetailReference = FirebaseDatabase.getInstance().getReference("user")
        imageView4.setOnClickListener {
            this.finish()
            startActivity(Intent(this,Sreach::class.java))
        }
        TEXTBAR.text = "Detail"
        b = iin.getStringExtra("figerAll")
        et_name.text = b
        dataUser(user)
        Log.e("XXXXXXXX",b)
        val type = arrayListOf("Onepiece","SAO","Gundum","Other","Vocaloid")
        for(a in type) {
            callDataFiger(a)
        }

        button2.setOnClickListener{
            if (item.userId.isNullOrEmpty()){
                Toast.makeText(this,"This figer can't trade",Toast.LENGTH_LONG)
            }else {
                if(userfiger.paddingException.isNotEmpty()){
                    for (a in userfiger.paddingException){
                        mydialof =  Dialog(this)
                        mydialof!!.setContentView(R.layout.item_select)
                        mydialof!!.rv_select.apply {
                            layoutManager = LinearLayoutManager(this@DetailItem)
                            val recyclableAdapter = recycleDetail(this@DetailItem,userfiger.paddingException ,typeItem,item.userId)
                            adapter = recyclableAdapter
                            mydialof?.show()
                        }
                    }
                }

            }

        }
    }
    fun callDataFiger(x : String){
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

                Log.e("test",message?.itemName?.trim())
                if(message?.itemName == b) {
                    typeItem = x
                    et_name.text = message.itemName
                    good.text = message.blamed
                    defect.text = message.condition
                    size.text = message.itemSize
                    other.text = x
                    val optional = RequestOptions().error(R.drawable.icon_main)
                    if (message.image.isNotEmpty()){
                        Glide.with(this@DetailItem).load(message.image[0]).apply(optional)
                            .into(image1)
                        Glide.with(this@DetailItem).load(message.image[1]).apply(optional)
                            .into(image2)
                        Glide.with(this@DetailItem).load(message.image[2]).apply(optional)
                            .into(image3)
                    }

                    item = message
                }
                et_name.text = message?.itemName
                Log.e("test",et_name.text.toString().trim())

            }

            override fun onChildRemoved(p0: DataSnapshot) {
               }
        }
        mUserDetailReference!!.addChildEventListener(childEventListener)
    }
     private fun dataUser(user: FirebaseUser?) {

        // User data change listener
        user!!.uid?.let {
            mDatabase!!.child("users").child(it).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.e("TAG",dataSnapshot.toString())
                    val user = dataSnapshot.getValue(userXXX::class.java)
                    userfiger = user!!

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.e("TAG", "onCancelled: Failed to read user!")
                }
            })
        }
    }
    class  MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}

