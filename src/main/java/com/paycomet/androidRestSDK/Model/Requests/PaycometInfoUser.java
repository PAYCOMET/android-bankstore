package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;

import com.google.gson.annotations.SerializedName;
import com.paycomet.androidRestSDK.Model.Basic.PaycometCard;

public class PaycometInfoUser extends PaycometCard {

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometInfoUser(String number, String expiryDate, String cardBrand, String cardType, String cardIso) {
        super(number, expiryDate, cardBrand, cardType, cardIso);
    }

    public String getErrorId() {
        return errorId;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    @Override
    public String toString() {
        return "PaycometInfoUser{" + super.toString() +
                "errorId='" + errorId + '\'' +
                '}';
    }
}
