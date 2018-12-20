package com.example.napat.trade.Sreach

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.napat.trade.R
import com.example.napat.trade.data.figerAll

class RecycleView (val activity : Sreach,val listFiger : ArrayList<figerAll>) : RecyclerView.Adapter<ViewHolders>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolders {
        return ViewHolders(LayoutInflater.from(p0.context).inflate(R.layout.item_crad, p0, false))
    }

    override fun getItemCount(): Int {
        return listFiger.size
    }

    override fun onBindViewHolder(p0: ViewHolders, p1: Int) {
        return p0.onBindData(listFiger[p1])
    }

}