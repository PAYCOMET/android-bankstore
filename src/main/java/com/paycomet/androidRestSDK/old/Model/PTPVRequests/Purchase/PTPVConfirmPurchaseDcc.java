package com.paycomet.androidRestSDK.old.Model.PTPVRequests.Purchase;

import com.paycomet.androidRestSDK.Model.Requests.PaycometConfirmPurchaseDcc;

public class PTPVConfirmPurchaseDcc extends PaycometConfirmPurchaseDcc {
    public PTPVConfirmPurchaseDcc(PaycometConfirmPurchaseDcc purchase) {
        super(purchase.getAmount(), purchase.getOrder(), purchase.getCurrency(), purchase.getAuthCode(), purchase.getChallengeUrl(), purchase.getCardCountry());
        super.setErrorId(purchase.getErrorId());
    }
}
