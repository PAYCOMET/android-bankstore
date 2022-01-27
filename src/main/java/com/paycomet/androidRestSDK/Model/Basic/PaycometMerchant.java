package com.paycomet.androidRestSDK.Model.Basic;

import static com.paycomet.androidRestSDK.Utils.Constants.MERCHANT_AMOUNT;
import static com.paycomet.androidRestSDK.Utils.Constants.MERCHANT_AUTHCODE;
import static com.paycomet.androidRestSDK.Utils.Constants.MERCHANT_CURRENCY;
import static com.paycomet.androidRestSDK.Utils.Constants.MERCHANT_ORDER;

import com.google.gson.annotations.SerializedName;

public class PaycometMerchant {

    @SerializedName(MERCHANT_AMOUNT)
    private String amount;

    @SerializedName(MERCHANT_ORDER)
    private String order;

    @SerializedName(MERCHANT_CURRENCY)
    private String currency;

    @SerializedName(MERCHANT_AUTHCODE)
    private String authCode;

    public PaycometMerchant() {}

    public PaycometMerchant(String amount, String order, String currency) {
        this.amount = amount;
        this.order = order;
        this.currency = currency;
    }

    public PaycometMerchant(String amount, String order, String currency, String authCode) {
        this.amount = amount;
        this.order = order;
        this.currency = currency;
        this.authCode = authCode;
    }

    public String getAmount() {
        return amount;
    }

    public String getOrder() {
        return order;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAuthCode() {
        return authCode;
    }

    @Override
    public String toString() {
        return "PaycometMerchant{" +
                "amount='" + amount + '\'' +
                ", order='" + order + '\'' +
                ", currency='" + currency + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }
}
