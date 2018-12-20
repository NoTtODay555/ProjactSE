package com.example.napat.trade.Sreach

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.napat.trade.R
import com.example.napat.trade.data.figerAll
import kotlinx.android.synthetic.main.item_crad.view.*

class ViewHolders(val view: View) :RecyclerView.ViewHolder(view){
    fun onBindData(figerAll: figerAll) {
        val optional = RequestOptions().error(R.drawable.icon_main)
        Log.e("tset",figerAll.toString())
        view.name_itme.text = figerAll?.itemName


        view.ex_con.text = figerAll.ExchangeConditions
        Glide.with(view).load(figerAll.image[1]).apply(optional)
                .into(view.item_image)

    }
}
