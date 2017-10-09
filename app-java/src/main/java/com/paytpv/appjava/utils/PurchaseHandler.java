package com.paytpv.appjava.utils;

import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVPurchaseDetails;

import java.util.ArrayList;

public class PurchaseHandler {
    private static PurchaseHandler singleton = new PurchaseHandler( );
    private ArrayList<PTPVPurchaseDetails> purchaseArray = new ArrayList<>();

    private PurchaseHandler() { }

    public static PurchaseHandler getInstance( ) {
        return singleton;
    }

    public ArrayList<PTPVPurchaseDetails> getPurchaseArray() {
        return purchaseArray;
    }
}
