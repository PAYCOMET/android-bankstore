package com.paytpv.paytpv.utils

import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVPurchaseDetails

class PurchaseHandler private constructor() {

    private object Holder {
        val INSTANCE = PurchaseHandler()
    }

    companion object {
        val instance: com.paytpv.paytpv.utils.PurchaseHandler by lazy { Holder.INSTANCE }
    }

    var purchaseArray: ArrayList<PTPVPurchaseDetails>? = ArrayList()
}