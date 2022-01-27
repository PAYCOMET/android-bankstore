package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.MARKETPLACE_ORDER;
import static com.paycomet.androidRestSDK.Utils.Constants.SUBMERCHANT;

import com.google.gson.annotations.SerializedName;
import com.paycomet.androidRestSDK.Model.Basic.PaycometSubmerchant;

public class PaycometMarketplace {

    @SerializedName(MARKETPLACE_ORDER)
    private String order;

    @SerializedName(SUBMERCHANT)
    private PaycometSubmerchant submerchant;

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometMarketplace() {}

    public PaycometMarketplace(String order, PaycometSubmerchant submerchant) {
        this.order = order;
        this.submerchant = submerchant;
    }

    public String getOrder() {
        return order;
    }

    public PaycometSubmerchant getSubmerchant() {
        return submerchant;
    }

    public String getErrorId() {
        return errorId;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    @Override
    public String toString() {
        return "PaycometMarketplace{" +
                "order='" + order + '\'' +
                ", submerchant=" + submerchant +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
