package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.CHALLENGE_URL;
import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;

import com.google.gson.annotations.SerializedName;

public class PaycometForm {

    @SerializedName(ERROR_ID)
    private String errorId;

    @SerializedName(CHALLENGE_URL)
    private String challengeUrl;

    public String getErrorId() {
        return errorId;
    }

    public String getChallengeUrl() {
        return challengeUrl;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public PaycometForm() {}

    public PaycometForm(String challengeUrl) {
        this.challengeUrl = challengeUrl;
    }

    @Override
    public String toString() {
        return "PaycometForm{" +
                "errorId='" + errorId + '\'' +
                ", challengeUrl='" + challengeUrl + '\'' +
                '}';
    }
}
