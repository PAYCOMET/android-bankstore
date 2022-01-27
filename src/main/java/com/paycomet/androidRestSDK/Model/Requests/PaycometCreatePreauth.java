package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_CARD_COUNTRY;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_CHALLENGE_URL;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_ID_USER;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_METHOD_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_TOKEN_USER;

import com.google.gson.annotations.SerializedName;
import com.paycomet.androidRestSDK.Model.Basic.PaycometMerchant;

public class PaycometCreatePreauth extends PaycometMerchant {

    @SerializedName(PAYMENT_CARD_COUNTRY)
    private String cardCountry;

    @SerializedName(PAYMENT_METHOD_ID)
    private String methodId;

    @SerializedName(PAYMENT_CHALLENGE_URL)
    private String challengeUrl;

    @SerializedName(PAYMENT_ID_USER)
    private String idUser;

    @SerializedName(PAYMENT_TOKEN_USER)
    private String tokenUser;

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometCreatePreauth() {}

    public PaycometCreatePreauth(String amount, String order, String currency, String authCode, String cardCountry, String methodId, String challengeUrl, String idUser, String tokenUser) {
        super(amount, order, currency, authCode);
        this.cardCountry = cardCountry;
        this.methodId = methodId;
        this.challengeUrl = challengeUrl;
        this.idUser = idUser;
        this.tokenUser = tokenUser;
    }

    public String getCardCountry() {
        return cardCountry;
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

    public String getErrorId() {
        return errorId;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    @Override
    public String toString() {
        return "PaycometCreatePreauth{" + super.toString() +
                "cardCountry='" + cardCountry + '\'' +
                ", methodId='" + methodId + '\'' +
                ", challengeUrl='" + challengeUrl + '\'' +
                ", idUser='" + idUser + '\'' +
                ", tokenUser='" + tokenUser + '\'' +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
