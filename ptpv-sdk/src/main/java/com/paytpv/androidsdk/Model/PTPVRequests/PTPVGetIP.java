
package com.paytpv.androidsdk.Model.PTPVRequests;

import com.google.gson.annotations.SerializedName;

import static com.paytpv.androidsdk.Utils.Constants.ERROR_ID;
import static com.paytpv.androidsdk.Utils.Constants.REMOTE_ADDRESS;

/**
 * Represents the response body of the "json-remote-ip" operation.
 */
public class PTPVGetIP {
    /**
     * Remote address of the phone's network.
     */
    @SerializedName(REMOTE_ADDRESS)
    private String remoteAddress;

    /**
     * Error ID of the request, if any.
     */
    @SerializedName(ERROR_ID)
    private String errorId;

    public String getErrorId() {
        return errorId;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public PTPVGetIP() {
    }

    public PTPVGetIP(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    @Override
    public String toString() {
        return "PTPVGetIP{" +
                "errorId='" + errorId + '\'' +
                ", remoteAddress='" + remoteAddress + '\'' +
                '}';
    }
}
