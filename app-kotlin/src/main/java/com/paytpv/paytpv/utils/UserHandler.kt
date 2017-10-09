package com.paytpv.paytpv.utils

import com.paytpv.androidsdk.Model.Basic.PTPVUser

class UserHandler {

    private object Holder {
        val INSTANCE = UserHandler()
    }

    companion object {
        val instance: UserHandler by lazy { Holder.INSTANCE }
    }

    var userArray: ArrayList<PTPVUser>? = ArrayList()
}