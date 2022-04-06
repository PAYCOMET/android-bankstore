package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_CARD_COUNTRY;

import com.google.gson.annotations.SerializedName;
import com.paycomet.androidRestSDK.Model.Basic.PaycometMerchant;

public class PaycometPreauthCancel extends PaycometMerchant {

    @SerializedName(PAYMENT_CARD_COUNTRY)
    private String cardCountry;

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometPreauthCancel() {}

    public PaycometPreauthCancel(String amount, String order, String currency, String cardCountry) {
        super(amount, order, currency);
        this.cardCountry = cardCountry;
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

    @Override
    public String toString() {
        return "PaycometPurchaseDetails{" + super.toString() +
                "cardCountry='" + cardCountry + '\'' +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
