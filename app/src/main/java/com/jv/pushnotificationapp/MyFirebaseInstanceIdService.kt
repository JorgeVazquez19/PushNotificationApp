package com.jv.pushnotificationapp

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

//clase para generar el token
class MyFirebaseInstanceIdService : FirebaseInstanceIdService() {

    //TAG para buscar el token
    val TAG = "PushNotifService"
    lateinit var name: String

    override fun onTokenRefresh() {
        // Genera el token
        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Token: ${token}")

    }

}