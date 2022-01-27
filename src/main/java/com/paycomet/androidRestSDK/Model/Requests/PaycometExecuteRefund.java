package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_CARD_COUNTRY;
import static com.paycomet.androidRestSDK.Utils.Constants.PAYMENT_METHOD_ID;

import com.google.gson.annotations.SerializedName;
import com.paycomet.androidRestSDK.Model.Basic.PaycometMerchant;

public class PaycometExecuteRefund extends PaycometMerchant {

    @SerializedName(PAYMENT_METHOD_ID)
    private String methodId;

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometExecuteRefund() {}

    public PaycometExecuteRefund(String amount, String order, String currency, String authCode, String methodId) {
        super(amount, order, currency, authCode);
        this.methodId = methodId;
    }

    public String getMethodId() {
        return methodId;
    }

    public String getErrorId() {
        return errorId;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    @Override
    public String toString() {
        return "PaycometExecuteRefund{" + super.toString() +
                "methodId='" + methodId + '\'' +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
