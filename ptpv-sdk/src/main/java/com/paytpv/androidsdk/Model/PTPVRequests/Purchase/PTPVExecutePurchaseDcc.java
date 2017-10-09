
package com.paytpv.androidsdk.Model.PTPVRequests.Purchase;

import com.paytpv.androidsdk.Model.Basic.PTPVMerchant;
import com.google.gson.annotations.SerializedName;

import static com.paytpv.androidsdk.Utils.Constants.DCC_AMOUNT;
import static com.paytpv.androidsdk.Utils.Constants.DCC_CARD_COUNTRY;
import static com.paytpv.androidsdk.Utils.Constants.DCC_CURRENCY;
import static com.paytpv.androidsdk.Utils.Constants.DCC_CURRENCY_ISO;
import static com.paytpv.androidsdk.Utils.Constants.DCC_CURRENCY_NAME;
import static com.paytpv.androidsdk.Utils.Constants.DCC_EXCHANGE_RATE;
import static com.paytpv.androidsdk.Utils.Constants.DCC_MARKUP;
import static com.paytpv.androidsdk.Utils.Constants.DCC_SESSION;
import static com.paytpv.androidsdk.Utils.Constants.ERROR_ID;
import static com.paytpv.androidsdk.Utils.Constants.RESPONSE;

/**
 * Represents the response body of the "execute_purchase_dcc" operation.
 */
public class PTPVExecutePurchaseDcc extends PTPVMerchant {

    /**
     * Session var for later confirmation of the authorization. This value must be stored to confirm
     * payment in the currency chosen by the end user
     */
    @SerializedName(DCC_SESSION)
    private String dccSession;

    /**
     * Native currency of the customer's card
     */
    @SerializedName(DCC_CURRENCY)
    private String dccCurrency;

    /**
     * Native currency of the customer's card in ISO3
     */
    @SerializedName(DCC_CURRENCY_ISO)
    private String dccCurrencyIso;

    /**
     * Literal currency in string. If the native currency is the same as the product PAYTPV, this
     * field will have the value 0
     */
    @SerializedName(DCC_CURRENCY_NAME)
    private String dccCurrencyName;

    /**
     * Currency exchange rate. Return string but it will come in format float
     */
    @SerializedName(DCC_EXCHANGE_RATE)
    private String dccExchangeRate;

    /**
     * Amount of the operation in whole format. 1,00 EURO = 100, 4,50 EUROS = 450...
     */
    @SerializedName(DCC_AMOUNT)
    private String dccAmount;

    /**
     * Percentage value in float of DCC margin applied by the financial institution. For example:
     * 0.03 will be 3%
     */
    @SerializedName(DCC_MARKUP)
    private String dccMarkup;

    /**
     * Country of the issuer of the card in ISO3 Code (ex.: Spain = 724)
     */
    @SerializedName(DCC_CARD_COUNTRY)
    private String dccCardCountry;

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
     * Creates a new PTPVExecutePurchaseDcc response populated with the provided values.
     *
     * @param merchantAmount Amount of the operation in integer format. 1.00 EURO = 100, 4.50 EUROS
     *            = 450...
     * @param merchantOrder Reference of the operation. Must be unique on every valid transaction
     * @param merchantCurrency Currency of the transaction. See more details at <a
     *            http://developers.paytpv.com/en/documentacion/monedas">http://developers.paytpv.com/en/documentacion/monedas</a>
     */
    public PTPVExecutePurchaseDcc(String merchantAmount, String merchantOrder,
            String merchantCurrency) {
        super(merchantAmount, merchantOrder, merchantCurrency);
    }

    public String getDccSession() {
        return dccSession;
    }

    public String getDccCurrency() {
        return dccCurrency;
    }

    public String getDccCurrencyIso() {
        return dccCurrencyIso;
    }

    public String getDccCurrencyName() {
        return dccCurrencyName;
    }

    public String getDccExchangeRate() {
        return dccExchangeRate;
    }

    public String getDccAmount() {
        return dccAmount;
    }

    public String getDccMarkup() {
        return dccMarkup;
    }

    public String getDccCardCountry() {
        return dccCardCountry;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "PTPVExecutePurchaseDcc{" + super.toString() +
                ", dccSession='" + dccSession + '\'' +
                ", dccCurrency='" + dccCurrency + '\'' +
                ", dccCurrencyIso='" + dccCurrencyIso + '\'' +
                ", dccCurrencyName='" + dccCurrencyName + '\'' +
                ", dccExchangeRate='" + dccExchangeRate + '\'' +
                ", dccAmount='" + dccAmount + '\'' +
                ", dccMarkup='" + dccMarkup + '\'' +
                ", dccCardCountry='" + dccCardCountry + '\'' +
                ", errorId='" + errorId + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
