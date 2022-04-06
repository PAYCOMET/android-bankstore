package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_CARD_COUNTRY;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_CHALLENGE_URL;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_ID_USER;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_METHOD_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_TOKEN_USER;

import com.google.gson.annotations.SerializedName;
import com.paycomet.androidRestSDK.Model.Basic.PaycometMerchant;

public class PaycometExecutePurchaseRtoken extends PaycometMerchant {

    @SerializedName(PAYMENT_METHOD_ID)
    private String methodId;

    @SerializedName(PAYMENT_CHALLENGE_URL)
    private String challengeUrl;

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometExecutePurchaseRtoken() {}

    public PaycometExecutePurchaseRtoken(String amount, String order, String currency, String authCode, String methodId, String challengeUrl) {
        super(amount, order, currency, authCode);
        this.methodId = methodId;
        this.challengeUrl = challengeUrl;
    }

    public String getMethodId() {
        return methodId;
    }

    public String getChallengeUrl() {
        return challengeUrl;
    }

    public String getErrorId() {
        return errorId;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    @Override
    public String toString() {
        return "PaycometExecutePurchase{" + super.toString() +
                "methodId='" + methodId + '\'' +
                ", challengeUrl='" + challengeUrl + '\'' +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
