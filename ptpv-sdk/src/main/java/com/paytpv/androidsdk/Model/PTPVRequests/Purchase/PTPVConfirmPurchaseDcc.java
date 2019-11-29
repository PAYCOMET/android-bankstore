
package com.paytpv.androidsdk.Model.PTPVRequests.Purchase;

import com.paytpv.androidsdk.Model.Basic.PTPVMerchant;
import com.google.gson.annotations.SerializedName;

import static com.paytpv.androidsdk.Utils.Constants.ERROR_ID;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_CARD_COUNTRY;
import static com.paytpv.androidsdk.Utils.Constants.RESPONSE;

/**
 * Represents the response body of the "confirm_purchase_dcc" operation.
 */
public class PTPVConfirmPurchaseDcc extends PTPVMerchant {

    /**
     * Country of the issuer of the card in ISO3 Code (ex.: Spain = 724)
     */
    @SerializedName(MERCHANT_CARD_COUNTRY)
    private String merchantCardCountry;

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

    /**
     * Creates a new PTPVConfirmPurchaseDcc response populated with the provided values.
     *
     * @param merchantAmount Amount of the operation in integer format. 1.00 EURO = 100, 4.50 EUROS
     *            = 450...
     * @param merchantOrder Reference of the operation. Must be unique on every valid transaction
     * @param merchantCurrency Currency of the transaction. See more details at <a
     *            https://docs.paycomet.com/en/documentacion/monedas">https://docs.paycomet.com/en/documentacion/monedas</a>
     * @param merchantAuthCode Authorization bank code of the transaction (required to execute a
     *            return)
     */
    public PTPVConfirmPurchaseDcc(String merchantAmount, String merchantOrder,
            String merchantCurrency, String merchantAuthCode) {
        super(merchantAmount, merchantOrder, merchantCurrency, merchantAuthCode);
    }

    public String getMerchantCardCountry() {
        return merchantCardCountry;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "PTPVConfirmPurchaseDcc{" + super.toString() +
                ", merchantCardCountry='" + merchantCardCountry + '\'' +
                ", errorId='" + errorId + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
