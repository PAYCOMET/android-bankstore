package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.EXCHANGE_AMOUNT;

import com.google.gson.annotations.SerializedName;

public class PaycometExchange {

    @SerializedName(EXCHANGE_AMOUNT)
    private String amount;

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometExchange() {}

    public PaycometExchange(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public String getErrorId() {
        return errorId;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    @Override
    public String toString() {
        return "PaycometExchange{" +
                "amount='" + amount + '\'' +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
