package com.paytpv.paytpv.utils

import com.paytpv.androidsdk.Model.Basic.PTPVCard

class CardHandler private constructor() {

    private object Holder {
        val INSTANCE = CardHandler()
    }

    companion object {
        val instance: CardHandler by lazy { Holder.INSTANCE }
    }

    var cardArray: ArrayList<PTPVCard>? = ArrayList()
}
