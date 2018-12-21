package com.example.napat.trade.request

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.napat.trade.R
import com.example.napat.trade.data.figerAll
import com.example.napat.trade.detail.DetailItem
import kotlinx.android.synthetic.main.item_crad.view.*

class ViewHolderRequest (val view: View, var context : Activity) : RecyclerView.ViewHolder(view){
    fun onBindData(figerAll: figerAll, x: String?) {
        val optional = RequestOptions().error(R.drawable.icon_main)
        Log.e("tset",figerAll.toString())
        view.name_itme.text = figerAll?.itemName
        view.ex_con.text = figerAll.ExchangeConditions
        if (figerAll.image.isNotEmpty()){
            Glide.with(view).load(figerAll?.image[1]).apply(optional)
                .into(view.item_image)
        }

        view.setOnClickListener{
            context.finish()
            val i = Intent(context, DetailItem::class.java)
            i.putExtra("figerAll",figerAll.itemName)
            context.startActivity(i)
        }


    }
}