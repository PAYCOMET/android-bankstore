package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.DCC;
import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_CARD_COUNTRY;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_CHALLENGE_URL;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_ID_USER;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_METHOD_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_TOKEN_USER;

import com.google.gson.annotations.SerializedName;
import com.paycomet.androidRestSDK.Model.Basic.PaycometDcc;
import com.paycomet.androidRestSDK.Model.Basic.PaycometMerchant;

public class PaycometExecutePurchaseDcc extends PaycometMerchant {

    @SerializedName(PAYMENT_METHOD_ID)
    private String methodId;

    @SerializedName(PAYMENT_CHALLENGE_URL)
    private String challengeUrl;

    @SerializedName(PAYMENT_ID_USER)
    private String idUser;

    @SerializedName(PAYMENT_TOKEN_USER)
    private String tokenUser;

    @SerializedName(PAYMENT_CARD_COUNTRY)
    private String cardCountry;

    @SerializedName(DCC)
    private PaycometDcc dcc;

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometExecutePurchaseDcc() {}

    public PaycometExecutePurchaseDcc(String amount, String order, String currency, String authCode, String methodId, String challengeUrl, String idUser, String tokenUser, String cardCountry, PaycometDcc dcc) {
        super(amount, order, currency, authCode);
        this.methodId = methodId;
        this.challengeUrl = challengeUrl;
        this.idUser = idUser;
        this.tokenUser = tokenUser;
        this.cardCountry = cardCountry;
        this.dcc = dcc;
    }

    public String getMethodId() {
        return methodId;
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

    public PaycometDcc getDcc() {
        return dcc;
    }

    public String getErrorId() {
        return errorId;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    @Override
    public String toString() {
        return "PaycometExecutePurchaseDcc{" + super.toString() +
                "methodId='" + methodId + '\'' +
                ", challengeUrl='" + challengeUrl + '\'' +
                ", idUser='" + idUser + '\'' +
                ", tokenUser='" + tokenUser + '\'' +
                ", cardCountry='" + cardCountry + '\'' +
                ", dcc=" + dcc +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
