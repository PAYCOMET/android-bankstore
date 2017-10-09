
package com.paytpv.androidsdk.Model.PTPVRequests;

import com.google.gson.annotations.SerializedName;

import static com.paytpv.androidsdk.Utils.Constants.ERROR_ID;
import static com.paytpv.androidsdk.Utils.Constants.RESPONSE;

/**
 * Represents the response body of the "remove" operations.
 */
public class PTPVRemoveResponse {
    /**
     * Error ID of the request, if any.
     */
    @SerializedName(ERROR_ID)
    private String errorId;

    /**
     * Result of operation. 0 or empty will be erroneous operation and 1 operation completed.
     */
    @SerializedName(RESPONSE)
    private String response;

    public String getErrorId() {
        return errorId;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "PTPVRemoveResponse{" +
                "errorId='" + errorId + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
