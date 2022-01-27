package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.HEARTBEAT_PROCESSOR_TIME;
import static com.paycomet.androidRestSDK.Utils.Constants.HEARTBEAT_TIME;
import static com.paycomet.androidRestSDK.Utils.Constants.HEART_PROCESSOR_STATUS;

import com.google.gson.annotations.SerializedName;

public class PaycometHeartbeat {

    @SerializedName(HEARTBEAT_TIME)
    private String time;

    @SerializedName(HEARTBEAT_PROCESSOR_TIME)
    private String processorTime;

    @SerializedName(HEART_PROCESSOR_STATUS)
    private String processorStatus;

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometHeartbeat() {}

    public PaycometHeartbeat(String time, String processorTime, String processorStatus) {
        this.time = time;
        this.processorTime = processorTime;
        this.processorStatus = processorStatus;
    }

    public String getTime() {
        return time;
    }

    public String getProcessorTime() {
        return processorTime;
    }

    public String getProcessorStatus() {
        return processorStatus;
    }

    public String getErrorId() {
        return errorId;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    @Override
    public String toString() {
        return "PaycometHeartbeat{" +
                "time='" + time + '\'' +
                ", processorTime='" + processorTime + '\'' +
                ", processorStatus='" + processorStatus + '\'' +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
