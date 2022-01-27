package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.IP_NAME;
import static com.paycomet.androidRestSDK.Utils.Constants.ISO2;
import static com.paycomet.androidRestSDK.Utils.Constants.ISO3;
import static com.paycomet.androidRestSDK.Utils.Constants.ISO_NUM;
import static com.paycomet.androidRestSDK.Utils.Constants.REMOTE_ADDRESS;

import com.google.gson.annotations.SerializedName;

public class PaycometIP {

    @SerializedName(REMOTE_ADDRESS)
    private String remoteAddress;

    @SerializedName(ERROR_ID)
    private String errorId;

    @SerializedName(ISO_NUM)
    private String isoNum;

    @SerializedName(ISO2)
    private String iso2;

    @SerializedName(ISO3)
    private String iso3;

    @SerializedName(IP_NAME)
    private String ipName;

    public String getIsoNum() {
        return isoNum;
    }

    public String getIso2() {
        return iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public String getIpName() {
        return ipName;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public PaycometIP() {
    }

    public PaycometIP(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    @Override
    public String toString() {
        return "PaycometGetIP{" +
                "remoteAddress='" + remoteAddress + '\'' +
                ", errorId='" + errorId + '\'' +
                ", isoNum='" + isoNum + '\'' +
                ", iso2='" + iso2 + '\'' +
                ", iso3='" + iso3 + '\'' +
                ", ipName='" + ipName + '\'' +
                '}';
    }
}
