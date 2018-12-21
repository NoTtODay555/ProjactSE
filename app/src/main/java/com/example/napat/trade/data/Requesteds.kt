package com.example.napat.trade.data

import com.google.firebase.database.Exclude

class Requesteds {
    var figerAll : figerAll = figerAll()
    var queue : ArrayList<figerAll> = arrayListOf()

    constructor(figerAll: figerAll, queue: ArrayList<figerAll>) {
        this.figerAll = figerAll
        this.queue = queue
    }
    @Exclude
    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result["figerAll"] = figerAll!!
        result["queue"] = queue!!
        return result
    }

}