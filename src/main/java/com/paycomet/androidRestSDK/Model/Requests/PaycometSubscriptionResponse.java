package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.SUBSCRIPTION;
import static com.paycomet.androidRestSDK.Utils.Constants.CHALLENGE_URL;
import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.MERCHANT_AMOUNT;
import static com.paycomet.androidRestSDK.Utils.Constants.MERCHANT_AUTHCODE;
import static com.paycomet.androidRestSDK.Utils.Constants.MERCHANT_CURRENCY;
import static com.paycomet.androidRestSDK.Utils.Constants.MERCHANT_ORDER;
import static com.paycomet.androidRestSDK.Utils.Constants.METHOD_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_CARD_COUNTRY;
import static com.paycomet.androidRestSDK.Utils.Constants.USER_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.USER_TOKEN;

import com.google.gson.annotations.SerializedName;
import com.paycomet.androidRestSDK.Model.Basic.PaycometSubscription;

public class PaycometSubscriptionResponse {

    @SerializedName(MERCHANT_AMOUNT)
    private String amount;

    @SerializedName(MERCHANT_CURRENCY)
    private String currency;

    @SerializedName(METHOD_ID)
    private String methodId;

    @SerializedName(MERCHANT_ORDER)
    private String order;

    @SerializedName(MERCHANT_AUTHCODE)
    private String authCode;

    @SerializedName(CHALLENGE_URL)
    private String challengeUrl;

    @SerializedName(USER_ID)
    private String idUser;

    @SerializedName(USER_TOKEN)
    private String tokenUser;

    @SerializedName(PAYMENT_CARD_COUNTRY)
    private String cardCountry;

    @SerializedName(SUBSCRIPTION)
    private PaycometSubscription subscription;

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometSubscriptionResponse() {}

    public PaycometSubscriptionResponse(String amount, String currency, String methodId, String order, String authCode, String challengeUrl, String idUser, String tokenUser, String cardCountry, PaycometSubscription subscription) {
        this.amount = amount;
        this.currency = currency;
        this.methodId = methodId;
        this.order = order;
        this.authCode = authCode;
        this.challengeUrl = challengeUrl;
        this.idUser = idUser;
        this.tokenUser = tokenUser;
        this.cardCountry = cardCountry;
        this.subscription = subscription;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getMethodId() {
        return methodId;
    }

    public String getOrder() {
        return order;
    }

    public String getAuthCode() {
        return authCode;
    }

    public String getChallengeUrl() {
        return challengeUrl;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getTokenUser() {
        return tokenUser;
    }

    public String getCardCountry() {
        return cardCountry;
    }

    public PaycometSubscription getSubscription() {
        return subscription;
    }

    public String getErrorId() {
        return errorId;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    @Override
    public String toString() {
        return "PaycometSubscriptionResponse{" +
                "amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", methodId='" + methodId + '\'' +
                ", order='" + order + '\'' +
                ", authCode='" + authCode + '\'' +
                ", challengeUrl='" + challengeUrl + '\'' +
                ", idUser='" + idUser + '\'' +
                ", tokenUser='" + tokenUser + '\'' +
                ", cardCountry='" + cardCountry + '\'' +
                ", subscription=" + subscription +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
