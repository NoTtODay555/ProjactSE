package com.example.napat.trade.Login

import android.app.Activity
import com.example.napat.trade.data.Username
import com.facebook.login.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference

interface View {
    interface UserLogin{
        fun userLogin(UserName : Username)
    }
}