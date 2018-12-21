package com.example.napat.trade.Sreach

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.napat.trade.Profile
import com.example.napat.trade.R
import com.example.napat.trade.data.figerAll
import com.example.napat.trade.data.userXXX
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_sreach.*
import kotlinx.android.synthetic.main.layoutsreach.*

class Sreach : AppCompatActivity(),View.OnClickListener {
    var x = "Onepiece"
    private var mUserDetailReference: DatabaseReference? = null
    private val TAG = "SreachActivity"
    var figerList = ArrayList<figerAll>()
    override fun onClick(p0: View?) {
        when(p0){
            search_bar_main -> {
                when(x){
                    "Onepiece" ->{
                        Log.e(TAG,x)
                        rv_srech.apply {
                            figerList = arrayListOf()
                            callDataFiger("Onepiece")

                        }
                    }
                    "SAO" -> {
                        Log.e(TAG,x)
                        rv_srech.apply {
                            figerList = arrayListOf()
                            callDataFiger("SAO")

                        }
                    }
                    "Gundum" -> {
                        Log.e(TAG,x)
                        rv_srech.apply {
                            figerList = arrayListOf()
                            callDataFiger("Gundum")

                        }
                    }
                    "Other" -> {
                        Log.e(TAG,x)
                        rv_srech.apply {
                            figerList = arrayListOf()
                            callDataFiger("Other")

                        }
                    }
                    "Vocaloid" -> {
                        Log.e(TAG,x)
                        rv_srech.apply {
                            figerList = arrayListOf()
                            callDataFiger("Vocaloid")

                        }
                    }
                    else -> {
                    }
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sreach)

        val type = arrayListOf("Onepiece","SAO","Gundum","Other","Vocaloid")
        search_bar_main.setOnClickListener {

        }
        imageView3.setOnClickListener {
            this.finish()
            startActivity(Intent(this, Profile::class.java))
        }


        main_sprinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,type)
        main_sprinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                et_serch.hint = "Plass Select an Option"
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                et_serch.hint = type[p2]
                x =  type[p2]
            }

        }
        rv_srech.apply {
            layoutManager = LinearLayoutManager(this@Sreach)
            val recyclableAdapter = RecycleView(this@Sreach,figerList,"1")
            adapter = recyclableAdapter
        }

        search_bar_main.setOnClickListener(this)
    }
    fun callDataFiger(x : String){
        mUserDetailReference = FirebaseDatabase.getInstance().getReference("figer/"+x+"/")
        val childEventListener = object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                Log.e(TAG, "onChildMoved:" + p0!!.key)

                // A message has changed position
                val message = p0.getValue(figerAll::class.java)
                Toast.makeText(this@Sreach, "onChildMoved: " + message!!.itemName, Toast.LENGTH_SHORT).show()
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                Log.e(TAG, "onChildChanged:" + p0!!.key)

                // A message has changed
                val message = p0.getValue(figerAll::class.java)
                Toast.makeText(this@Sreach, "onChildChanged: " + message!!.itemName, Toast.LENGTH_SHORT).show()
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val message = p0.getValue(figerAll::class.java)
                figerList.add(message!!)
                Log.e(TAG, "onChildAdded:" + message.itemName + p1)
                Log.e(TAG,figerList.toString())
                rv_srech.apply {
                    val recyclableAdapter = RecycleView(this@Sreach,figerList,"1")
                    adapter = recyclableAdapter
                }
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                Log.e(TAG, "onChildRemoved:" + p0!!.key)

                // A message has been removed
                val message = p0.getValue(userXXX::class.java)
                Toast.makeText(this@Sreach, "onChildRemoved: " + message!!.address, Toast.LENGTH_SHORT).show()
            }
        }
        mUserDetailReference!!.addChildEventListener(childEventListener)
    }

}
