
package com.paytpv.androidsdk.Model.PTPVRequests.Purchase;

import com.google.gson.annotations.SerializedName;

import static com.paytpv.androidsdk.Utils.Constants.ERROR_ID;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_AUTHCODE;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_CURRENCY;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_ORDER;
import static com.paytpv.androidsdk.Utils.Constants.RESPONSE;

/**
 * Represents the response body of the "execute_refund" operation.
 */
public class PTPVExecuteRefund {

    @SerializedName(MERCHANT_ORDER)
    private String merchantOrder;

    @SerializedName(MERCHANT_CURRENCY)
    private String merchantCurrency;

    @SerializedName(MERCHANT_AUTHCODE)
    private String merchantAuthCode;

    @SerializedName(ERROR_ID)
    private String errorId;

    @SerializedName(RESPONSE)
    private String response;

    public String getMerchantOrder() {
        return merchantOrder;
    }

    public String getMerchantCurrency() {
        return merchantCurrency;
    }

    public String getMerchantAuthCode() {
        return merchantAuthCode;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "PTPVExecuteRefund{" + super.toString() +
                "merchantOrder='" + merchantOrder + '\'' +
                ", merchantCurrency='" + merchantCurrency + '\'' +
                ", merchantAuthCode='" + merchantAuthCode + '\'' +
                ", errorId='" + errorId + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
