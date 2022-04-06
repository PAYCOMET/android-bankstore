package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;

import com.google.gson.annotations.SerializedName;

public class PaycometRemoveResponse {

    @SerializedName(ERROR_ID)
    private String errorId;

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

    public PaycometRemoveResponse() {}

    public PaycometRemoveResponse(String errorId) {
        this.errorId = errorId;
    }

    @Override
    public String toString() {
        return "PaycometRemoveResponse{" +
                "errorId='" + errorId + '\'' +
                '}';
    }
}
