package com.example.napat.trade.data

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class userXXX {
    var id : String? = ""
    var username : String? = ""
    var password : String? = ""
    var surname : String? = ""
    var telephone : String? = ""
    var facebookName : String? = ""
    var address : String? = ""
    var district : String? = ""
    var provice : String? = ""
    var zipCode : String? = ""
    var idCard : String? = ""
    var paddingException : ArrayList<figerAll> = arrayListOf()
    var completed : ArrayList<figerAll> = arrayListOf()
    var sentExchange : ArrayList<figerAll> = arrayListOf()
    var awaiting : ArrayList<figerAll> = arrayListOf()
    var request : ArrayList<figerAll> = arrayListOf()


    constructor(){

    }

    constructor(
        id: String,
        username: String,
        password: String,
        surname: String,
        telephone: String,
        facebookName: String,
        address: String,
        district: String,
        provice: String,
        zipCode: String,
        idCard: String,
        paddingException: ArrayList<figerAll>,
        completed : ArrayList<figerAll>,
        sentExchange : ArrayList<figerAll>,
        awaiting : ArrayList<figerAll>,
        request : ArrayList<figerAll>

    ) {
        this.id = id
        this.username = username
        this.password = password
        this.surname = surname
        this.telephone = telephone
        this.facebookName = facebookName
        this.address = address
        this.district = district
        this.provice = provice
        this.zipCode = zipCode
        this.idCard = idCard
        this.paddingException = paddingException
        this.completed = completed
        this.sentExchange = sentExchange
        this.awaiting = awaiting
        this.request = request
    }



    @Exclude
    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result["id"] = id!!
        result["username"] = username!!
        result["password"] = password!!
        result["surname"] = surname!!
        result["telephone"] = telephone!!
        result["facebookName"] = facebookName!!
        result["address"] = address!!
        result["district"] = district!!
        result["provice"] = provice!!
        result["zipCode"] = zipCode!!
        result["idCard"] = idCard!!
        result["paddingException"] = paddingException!!
        result["completed"] = completed!!
        result["sentExchange"] = sentExchange!!
        result["awaiting"] = awaiting!!
        result["request"] = request!!

        return result
    }
}