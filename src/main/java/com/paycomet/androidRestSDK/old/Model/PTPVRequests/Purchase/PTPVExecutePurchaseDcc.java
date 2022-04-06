package com.paycomet.androidRestSDK.old.Model.PTPVRequests.Purchase;

import com.paycomet.androidRestSDK.Model.Basic.PaycometDcc;
import com.paycomet.androidRestSDK.Model.Requests.PaycometExecutePurchaseDcc;

public class PTPVExecutePurchaseDcc extends PaycometExecutePurchaseDcc {
    public PTPVExecutePurchaseDcc(PaycometExecutePurchaseDcc purchase) {
        super(purchase.getAmount(), purchase.getOrder(), purchase.getCurrency(), purchase.getAuthCode(), purchase.getMethodId(), purchase.getChallengeUrl(), purchase.getIdUser(), purchase.getTokenUser(), purchase.getCardCountry(), purchase.getDcc());
        super.setErrorId(purchase.getErrorId());
    }
}
