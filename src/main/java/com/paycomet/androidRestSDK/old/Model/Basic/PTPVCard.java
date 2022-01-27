package com.paycomet.androidRestSDK.old.Model.Basic;

import com.paycomet.androidRestSDK.Model.Basic.PaycometCard;

public class PTPVCard extends PaycometCard {
    public PTPVCard(String number, String expiryDate, String cvv) {
        super(number, expiryDate, cvv);
    }

    public PTPVCard(String number, String expiryDate, String cardBrand, String cardType, String cardIso) {
        super(number, expiryDate, cardBrand, cardType, cardIso);
    }
}
