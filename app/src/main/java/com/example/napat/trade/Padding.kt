package com.example.napat.trade

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.layout_signin.*

class Padding : AppCompatActivity(),View.OnClickListener {
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
        setContentView(R.layout.activity_waitting_confirmation)
        TEXTBAR.text = "Padding Excheang"
        imageView4.setOnClickListener(this)

    }
}
