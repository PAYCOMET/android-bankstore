package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_CARD_COUNTRY;

import com.google.gson.annotations.SerializedName;
import com.paycomet.androidRestSDK.Model.Basic.PaycometMerchant;

public class PaycometPreauthConfirm extends PaycometMerchant {

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometPreauthConfirm() {}

    public PaycometPreauthConfirm(String amount, String order, String currency, String authCode) {
        super(amount, order, currency, authCode);
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
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
