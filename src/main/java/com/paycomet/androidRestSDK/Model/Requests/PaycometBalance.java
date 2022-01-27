package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.BALANCE_AVAILABLE;
import static com.paycomet.androidRestSDK.Utils.Constants.BALANCE_GLOBAL;
import static com.paycomet.androidRestSDK.Utils.Constants.DEPOSIT;
import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;

import com.google.gson.annotations.SerializedName;

public class PaycometBalance {

    @SerializedName(BALANCE_GLOBAL)
    private String global;

    @SerializedName(BALANCE_AVAILABLE)
    private String available;

    @SerializedName(DEPOSIT)
    private String deposit;

    @SerializedName(ERROR_ID)
    private String errorId;

    public String getGlobal() {
        return global;
    }

    public String getAvailable() {
        return available;
    }

    public String getDeposit() {
        return deposit;
    }

    public String getErrorId() {
        return errorId;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public PaycometBalance() {}

    public PaycometBalance(String global, String available, String deposit) {
        this.global = global;
        this.available = available;
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "PaycometBalance{" +
                "global='" + global + '\'' +
                ", available='" + available + '\'' +
                ", deposit='" + deposit + '\'' +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
