package com.paycomet.androidRestSDK.Model.Basic;

import static com.paycomet.androidRestSDK.Utils.Constants.SUBMERCHANT_AMOUNT;
import static com.paycomet.androidRestSDK.Utils.Constants.SUBMERCHANT_CURRENCY;
import static com.paycomet.androidRestSDK.Utils.Constants.SUBMERCHANT_SPLIT_AUTH_CODE;

import com.google.gson.annotations.SerializedName;

public class PaycometSubmerchant {

    @SerializedName(SUBMERCHANT_AMOUNT)
    private String amount;

    @SerializedName(SUBMERCHANT_CURRENCY)
    private String currency;

    @SerializedName(SUBMERCHANT_SPLIT_AUTH_CODE)
    private String splitAuthCode;

    public PaycometSubmerchant() {}

    public PaycometSubmerchant(String amount, String currency, String splitAuthCode) {
        this.amount = amount;
        this.currency = currency;
        this.splitAuthCode = splitAuthCode;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getSplitAuthCode() {
        return splitAuthCode;
    }

    @Override
    public String toString() {
        return "PaycometSubmerchant{" +
                "amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", splitAuthCode='" + splitAuthCode + '\'' +
                '}';
    }
}
