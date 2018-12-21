package com.example.napat.trade.padding.addexchange

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.napat.trade.R
import com.example.napat.trade.data.figerAll
import com.example.napat.trade.data.userXXX
import com.example.napat.trade.padding.Awaiting
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_add_exchange.*
import kotlinx.android.synthetic.main.layout_padding.*
import java.util.*
import kotlin.collections.ArrayList

class AddExchange : AppCompatActivity(), View.OnClickListener {
    var x = "Other"
    var urlimage = ""
    var count = 0
    var image : ArrayList<String> = arrayListOf()
    var userId = userXXX()
    var typeItem = "Other"

    private var ExchangeConditions = "Other"
    private var mDatabase: DatabaseReference? = null
    private val fireBaseAuth = FirebaseAuth.getInstance()
    private val user = fireBaseAuth.currentUser

    override fun onClick(p0: View?) {
        when(p0){
            back_button -> {
                this.finish()
                startActivity(Intent(this, Awaiting::class.java))
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exchange)
        TEXTBAR.text = "Add Exchange"
        editText3.isEnabled = false
        back_button.setOnClickListener(this)
        mDatabase = FirebaseDatabase.getInstance().reference
        callDataUser()
        cetegoriesExchang.isEnabled = false

        val type = arrayListOf("Onepiece","SAO","Gundum","Other","Vocaloid")
        other.adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,type)
        other.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                typeItem = type[p2]
            }
        }
        checkBox2.setOnClickListener{
            checkBox3.isChecked = false
            checkBox4.isChecked = false
            editText3.isEnabled = false
            cetegoriesExchang.isEnabled = false
            x = "Other"
        }
        checkBox3.setOnClickListener{
            checkBox2.isChecked = false
            checkBox4.isChecked = false
            editText3.isEnabled = false
            cetegoriesExchang.isEnabled = true
            cetegoriesExchang.adapter =  ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,type)
            other.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) {}
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    x = type[p2]
                }
            }

        }
        checkBox4.setOnClickListener{
            checkBox2.isChecked = false
            checkBox3.isChecked = false
            editText3.isEnabled = true
            cetegoriesExchang.isEnabled = false
        }
        add_padding.setOnClickListener {
            if (editText3.isEnabled) x = editText3.text.toString().trim()
            val name = et_name.text.toString().trim()
            val condition = textView18.text.toString().trim()
            val blamed = defect.text.toString().trim()
            val itemSize = size.text.toString().trim()
            val listrequast = ArrayList<figerAll>()
            val figer = user?.uid?.let { it1 ->
                figerAll( listrequast,name,condition, blamed, itemSize, ExchangeConditions, image, it1)
            }
            figer?.let { it1 -> userId.paddingException.add(it1) }
            FirebaseDatabase.getInstance().reference.child("figer").child(typeItem).child(UUID.randomUUID().toString()).setValue(figer)
            user?.uid?.let { it1 -> FirebaseDatabase.getInstance().reference.child("users").child(it1).setValue(userId) }
            this.finish()
            startActivity(Intent(this,Awaiting::class.java))
        }
        uplode.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,0)

        }

    }
    var selectedPhotoUri : Uri? =null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        count += 1
        selectedPhotoUri = data?.data
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

        val bitmapDrawable = BitmapDrawable(bitmap)
        when(count){
            1 -> {
                image1.setBackgroundDrawable(bitmapDrawable)
                uploadImageToFirebaseStore(typeItem)
            }
            2 -> {
                image2.setBackgroundDrawable(bitmapDrawable)
                uploadImageToFirebaseStore(typeItem)
            }
            3 -> {
                image3.setBackgroundDrawable(bitmapDrawable)
                uploadImageToFirebaseStore(typeItem)
            }
            else -> Toast.makeText(this,"Uplode is full",Toast.LENGTH_LONG)
        }

    }
    private fun uploadImageToFirebaseStore(types : String){
        if (selectedPhotoUri == null) return
        val uris =  UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/figerImage/"+types+"/"+uris)
        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("Register", "complect${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener { url ->
                    urlimage = url.toString()
                    image.add(urlimage)
                }
            }
    }
    private fun callDataUser(){
        user!!.uid?.let {
            mDatabase!!.child("users").child(it).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(userXXX::class.java)
                    if (user == null) {
                        Toast.makeText(this@AddExchange, "onDataChange: User data is null!", Toast.LENGTH_SHORT).show()
                        return
                    }
                    userId = user
                }
                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }

}
