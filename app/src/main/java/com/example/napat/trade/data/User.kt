package com.example.napat.trade.data

class User {
    var name: String? = null
    var email: String? = null

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    constructor(username: String?, email: String?) {
        this.name = username
        this.email = email
    }
}