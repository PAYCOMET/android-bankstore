
package com.paytpv.androidsdk.Model.PTPVRequests.Subscription;

import com.paytpv.androidsdk.Model.Basic.PTPVSubscription;
import com.google.gson.annotations.SerializedName;

import static com.paytpv.androidsdk.Utils.Constants.ERROR_ID;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_AUTHCODE;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_CARD_COUNTRY;
import static com.paytpv.androidsdk.Utils.Constants.USER_ID;
import static com.paytpv.androidsdk.Utils.Constants.USER_TOKEN;

/**
 * Represents the response body of the "create_subscription" operation.
 */
public class PTPVSubscriptionResponse extends PTPVSubscription {

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

    /**
     * Country of the issuer of the card in ISO3 Code (ex.: 724 = Spain). May be left empty
     */
    @SerializedName(MERCHANT_CARD_COUNTRY)
    private String cardCountry;

    /**
     * Authorization bank code of the transaction
     */
    @SerializedName(MERCHANT_AUTHCODE)
    private String authCode;

    /**
     * Error ID of the request, if any.
     */
    @SerializedName(ERROR_ID)
    private String errorId;

    /**
     * Creates a new PTPVSubscriptionResponse response populated with the provided values.
     *
     * @param subscriptionAmount Amount of the operation in integer format. 1.00 EURO = 100, 4.50
     *            EUROS = 450...
     * @param subscriptionOrder Original reference of the operation + [DS_IDUSER] + date of the
     *            transaction in format YYYYMMDD
     * @param subscriptionCurrency Currency of the transaction. See more details at <a href=
     *            "https://docs.paycomet.com/en/documentacion/monedas">https://docs.paycomet.com/en/documentacion/monedas</a>
     */
    public PTPVSubscriptionResponse(String subscriptionAmount, String subscriptionOrder,
            String subscriptionCurrency) {
        super(subscriptionAmount, subscriptionOrder, subscriptionCurrency);
    }

    public String getUserId() {
        return userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public String getCardCountry() {
        return cardCountry;
    }

    public String getAuthCode() {
        return authCode;
    }

    public String getErrorId() {
        return errorId;
    }

    @Override
    public String toString() {
        return "PTPVSubscriptionResponse{" + super.toString() +
                "userId='" + userId + '\'' +
                ", userToken='" + userToken + '\'' +
                ", cardCountry='" + cardCountry + '\'' +
                ", authCode='" + authCode + '\'' +
                ", errorId='" + errorId + '\'' +
                '}';
    }
}
