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

public class PaycometConfirmPurchaseDcc extends PaycometMerchant {

    @SerializedName(PAYMENT_CHALLENGE_URL)
    private String challengeUrl;

    @SerializedName(PAYMENT_CARD_COUNTRY)
    private String cardCountry;

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometConfirmPurchaseDcc() {}

    public PaycometConfirmPurchaseDcc(String amount, String order, String currency, String authCode, String challengeUrl, String cardCountry) {
        super(amount, order, currency, authCode);
        this.challengeUrl = challengeUrl;
        this.cardCountry = cardCountry;
    }

    public String getChallengeUrl() {
        return challengeUrl;
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
    public String getResponse(){
        if(this.errorId.equals("0"))
            return "1";
        else
            return "0";
    }

    @Override
    public String toString() {
        return "PaycometConfirmPurchaseDcc{" + super.toString() +
                "challengeUrl='" + challengeUrl + '\'' +
                ", cardCountry='" + cardCountry + '\'' +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
