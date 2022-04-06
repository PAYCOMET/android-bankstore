package com.paycomet.androidRestSDK.old.Model.PTPVRequests.Purchase;

import com.paycomet.androidRestSDK.Model.Requests.PaycometCreatePreauth;
import com.paycomet.androidRestSDK.Model.Requests.PaycometExecutePurchase;
import com.paycomet.androidRestSDK.Model.Requests.PaycometPreauthCancel;
import com.paycomet.androidRestSDK.Model.Requests.PaycometPreauthConfirm;

public class PTPVPurchaseDetails extends PaycometExecutePurchase {
    public PTPVPurchaseDetails(PaycometExecutePurchase purchase) {
        super(purchase.getAmount(), purchase.getOrder(), purchase.getCurrency(), purchase.getAuthCode(), purchase.getMethodId(), purchase.getChallengeUrl(), purchase.getIdUser(), purchase.getTokenUser(), purchase.getCardCountry());
        super.setErrorId(purchase.getErrorId());
    }

    public PTPVPurchaseDetails(PaycometCreatePreauth preauth) {
        super(preauth.getAmount(), preauth.getOrder(), preauth.getCurrency(), preauth.getAuthCode(), preauth.getMethodId(), preauth.getChallengeUrl(), preauth.getIdUser(), preauth.getTokenUser(), preauth.getCardCountry());
        super.setErrorId(preauth.getErrorId());
    }

    public PTPVPurchaseDetails(PaycometPreauthConfirm preauth) {
        super(preauth.getAmount(), preauth.getOrder(), preauth.getCurrency(), preauth.getAuthCode(), "", "", "", "", "");
        super.setErrorId(preauth.getErrorId());
    }

    public PTPVPurchaseDetails(PaycometPreauthCancel preauth) {
        super(preauth.getAmount(), preauth.getOrder(), preauth.getCurrency(), preauth.getAuthCode(), "", "", "", "", "");
        super.setErrorId(preauth.getErrorId());
    }
}
