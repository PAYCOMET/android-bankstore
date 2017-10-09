
package com.paytpv.androidsdk.Model.PTPVRequests.User;

import com.paytpv.androidsdk.Model.Basic.PTPVCard;
import com.google.gson.annotations.SerializedName;

import static com.paytpv.androidsdk.Utils.Constants.ERROR_ID;

/**
 * Represents the response body of the "info_user" operation.
 */
public class PTPVInfoUser extends PTPVCard {
    /**
     * Error ID of the request, if any.
     */
    @SerializedName(ERROR_ID)
    private String errorId;

    /**
     * Creates a new PTPVAddUser response populated with the provided values.
     *
     * @param number Card number
     * @param expiryDate Expiry date of the card
     * @param cardBrand Card brand
     * @param cardType Type of the card
     * @param cardIso ISO3 Code of the country of the issuer of the card
     */
    public PTPVInfoUser(String number, String expiryDate, String cardBrand, String cardType,
            String cardIso) {
        super(number, expiryDate, cardBrand, cardType, cardIso);
    }

    public String getErrorId() {
        return errorId;
    }

    @Override
    public String toString() {
        return "PTPVInfoUser{" + super.toString() +
                "errorId='" + errorId + '\'' +
                '}';
    }
}
