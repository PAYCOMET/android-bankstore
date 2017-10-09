package com.paytpv.paytpv.utils

import com.paytpv.androidsdk.Model.Basic.PTPVProduct

class Products {
    var productsArray: ArrayList<PTPVProduct> = ArrayList()

    init {
        productsArray.add(PTPVProduct("Donald", "Mickey Mouse", "98"))
        productsArray.add(PTPVProduct("Johnny", "Hair Gel", "27"))
        productsArray.add(PTPVProduct("Mark", "Arrows", "0"))
        productsArray.add(PTPVProduct("Goku", "Dragon Ball", "100"))
    }
}