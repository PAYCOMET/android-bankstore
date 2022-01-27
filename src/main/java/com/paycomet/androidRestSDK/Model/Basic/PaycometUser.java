package com.paycomet.androidRestSDK.Model.Basic;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.USER_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.USER_TOKEN;

import com.google.gson.annotations.SerializedName;

public class PaycometUser {

    /**
     * Unique identifier of the user registered in the system
     */
    @SerializedName(USER_ID)
    private String userId;

    /**
     * Token code associated with the user's ID
     */
    @SerializedName(USER_TOKEN)
    private String userToken;

    @SerializedName(ERROR_ID)
    private String errorId;

    public String getUserId() {
        return userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public String getErrorId() {
        return errorId;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public PaycometUser() {}

    public PaycometUser(String userId, String userToken) {
        this.userId = userId;
        this.userToken = userToken;
    }

    @Override
    public String toString() {
        return "PaycometUser{" +
                "userId='" + userId + '\'' +
                ", userToken='" + userToken + '\'' +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
