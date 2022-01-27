package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_CARD_COUNTRY;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_CHALLENGE_URL;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_ID_USER;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_METHOD_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_TOKEN_USER;

import com.google.gson.annotations.SerializedName;
import com.paycomet.androidRestSDK.Model.Basic.PaycometMerchant;

public class PaycometExecutePurchase extends PaycometMerchant {

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

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometExecutePurchase() {}

    public PaycometExecutePurchase(String amount, String order, String currency, String authCode, String methodId, String challengeUrl, String idUser, String tokenUser, String cardCountry) {
        super(amount, order, currency, authCode);
        this.methodId = methodId;
        this.challengeUrl = challengeUrl;
        this.idUser = idUser;
        this.tokenUser = tokenUser;
        this.cardCountry = cardCountry;
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

    public String getErrorId() {
        return errorId;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    /**
     * Result of operation. 0 or empty will be erroneous operation and 1 operation completed.
     */
    public String getResponse() {
        if(this.getErrorId().equals("0"))
            return "1";
        else
            return "0";
    }

    @Override
    public String toString() {
        return "PaycometExecutePurchase{" + super.toString() +
                "methodId='" + methodId + '\'' +
                ", challengeUrl='" + challengeUrl + '\'' +
                ", idUser='" + idUser + '\'' +
                ", tokenUser='" + tokenUser + '\'' +
                ", cardCountry='" + cardCountry + '\'' +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
