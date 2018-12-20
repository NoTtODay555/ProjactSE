package com.example.napat.trade.data

import java.time.temporal.Temporal

data class figerDetail(
    var id : Int = 0,
    var Image : Int = 0,
    var Detail : Detail = Detail("","","",0.0f),
    var type : String = "",
    var ExchangeConditions : String = "",
    var Intermediary : Boolean = false
)

data class Detail(
    var itemName : String ="",
    var condition : String= "",
    var Blamed : String = "",
    var itemSize : Float = 0.0f
)

data class Username(
    var id : Number? = 0,
    var username : String? = "",
    var password : String? = "",
    var surname : String? = "",
    var telephone : String? = "",
    var facebookName : String? = "",
    var address : String? = "",
    var district : String? = "",
    var provice : String? = "",
    var zipCode : String? = "",
    var idCard : String? = ""
)