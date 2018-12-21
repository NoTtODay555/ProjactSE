package com.example.napat.trade.detail

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.napat.trade.R
import com.example.napat.trade.data.figerAll

class recycleDetail (val activity : Activity, val listFiger : ArrayList<figerAll>, var x : String,var y : String) : RecyclerView.Adapter<ViewHoldersDetail>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHoldersDetail {
        return ViewHoldersDetail(LayoutInflater.from(p0.context).inflate(R.layout.item_crad, p0, false),activity)
    }

    override fun getItemCount(): Int {
        return listFiger.size
    }

    override fun onBindViewHolder(p0: ViewHoldersDetail, p1: Int) {
        return p0.onBindData(listFiger[p1],y,x)
    }

}