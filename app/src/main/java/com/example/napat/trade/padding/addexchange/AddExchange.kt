package com.example.napat.trade.padding.addexchange

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.napat.trade.R
import com.example.napat.trade.padding.Padding
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_add_exchange.*
import kotlinx.android.synthetic.main.layout_padding.*
import java.util.*

class AddExchange : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0){
            back_button -> {
                this.finish()
                startActivity(Intent(this, Padding::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exchange)
        TEXTBAR.text = "Add Exchange"
        back_button.setOnClickListener(this)
        var name = et_name.text.toString().trim()


    }

    private fun uplodeimage(){
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/")
    }
}
