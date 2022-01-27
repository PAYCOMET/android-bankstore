package com.paycomet.androidRestSDK.Model.Basic;

import static com.paycomet.androidRestSDK.Utils.Constants.SUBSCRIPTION_END_DATE;
import static com.paycomet.androidRestSDK.Utils.Constants.SUBSCRIPTION_PERIODICITY;
import static com.paycomet.androidRestSDK.Utils.Constants.SUBSCRIPTION_START_DATE;

import com.google.gson.annotations.SerializedName;

public class PaycometSubscription {

    @SerializedName(SUBSCRIPTION_START_DATE)
    private String startDate;

    @SerializedName(SUBSCRIPTION_END_DATE)
    private String endDate;

    @SerializedName(SUBSCRIPTION_PERIODICITY)
    private String periodicity;

    public PaycometSubscription() {}

    public PaycometSubscription(String startDate, String endDate, String periodicity) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.periodicity = periodicity;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    @Override
    public String toString() {
        return "PaycometSubscription{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", periodicity='" + periodicity + '\'' +
                '}';
    }
}
