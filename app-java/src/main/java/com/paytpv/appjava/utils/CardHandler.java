package com.paytpv.appjava.utils;

import com.paytpv.androidsdk.Model.Basic.PTPVCard;

import java.util.ArrayList;

public class CardHandler {
    private static CardHandler singleton = new CardHandler( );
    private ArrayList<PTPVCard> cardArray = new ArrayList<>();

    private CardHandler() { }

    public static CardHandler getInstance( ) {
        return singleton;
    }

    public ArrayList<PTPVCard> getCardArray() {
        return cardArray;
    }
}
