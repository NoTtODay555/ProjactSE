package com.example.napat.trade.padding

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.napat.trade.padding.addexchange.AddExchange
import com.example.napat.trade.Profile
import com.example.napat.trade.R
import kotlinx.android.synthetic.main.layout_padding.*

class Padding : AppCompatActivity(),View.OnClickListener {
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
    }
}
