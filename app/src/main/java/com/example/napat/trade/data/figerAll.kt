package com.example.napat.trade.data
import android.support.constraint.solver.widgets.ConstraintWidgetGroup
import com.example.napat.trade.R.id.password
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class figerAll {
    var itemName : String =""
    var image : ArrayList<String> = arrayListOf()
    var condition : String= ""
    var blamed : String = ""
    var itemSize : String = ""
    var ExchangeConditions : String = ""
    constructor(){

    }
    constructor(itemName: String, condition: String, blamed: String, itemSize: String, ExchangeConditions : String,image : ArrayList<String>) {
        this.itemName = itemName
        this.condition = condition
        this.blamed = blamed
        this.itemSize = itemSize
        this.ExchangeConditions = ExchangeConditions
        this.image = image
    }


    @Exclude
    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result["itemName"] = itemName!!
        result["condition"] = condition!!
        result["blamed"] = blamed!!
        result["itemSize"] = itemSize!!
        result["ExchangeConditions"] = ExchangeConditions!!
        result["image"] = image
        return result
    }


}