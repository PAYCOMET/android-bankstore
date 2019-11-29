
package com.paytpv.androidsdk.Model.Basic;

import com.google.gson.annotations.SerializedName;

import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_AMOUNT;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_AUTHCODE;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_CURRENCY;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_ORDER;

/**
 * Represents the order's details
 */
public class PTPVMerchant {
    /**
     * Amount of the operation in integer format. 1.00 EURO = 100, 4.50 EUROS = 450...
     */
    @SerializedName(MERCHANT_AMOUNT)
    private String amount;

    /**
     * Reference of the operation. Must be unique on every valid transaction
     */
    @SerializedName(MERCHANT_ORDER)
    private String order;

    /**
     * Currency of the transaction. See more details at <a href=
     * "https://docs.paycomet.com/en/documentacion/monedas">https://docs.paycomet.com/en/documentacion/monedas</a>
     */
    @SerializedName(MERCHANT_CURRENCY)
    private String currency;

    /**
     * Authorization bank code of the transaction (required to execute a return)
     */
    @SerializedName(MERCHANT_AUTHCODE)
    private String authCode;

    /**
     * Creates an order. Used when executing a purchase.
     *
     * @param amount Amount of the operation in integer format. 1.00 EURO = 100, 4.50 EUROS = 450...
     * @param order Reference of the operation. Must be unique on every valid transaction
     * @param currency Currency of the transaction. See more details at <a
     *            https://docs.paycomet.com/en/documentacion/monedas">https://docs.paycomet.com/en/documentacion/monedas</a>
     * @param authCode Authorization bank code of the transaction (required to execute a return)
     */
    public PTPVMerchant(String amount, String order, String currency, String authCode) {
        this.amount = amount;
        this.order = order;
        this.currency = currency;
        this.authCode = authCode;
    }

    /**
     * Creates an order. Used when executing a purchase.
     *
     * @param amount Amount of the operation in integer format. 1.00 EURO = 100, 4.50 EUROS = 450...
     * @param order Reference of the operation. Must be unique on every valid transaction
     * @param currency Currency of the transaction. See more details at <a
     *            https://docs.paycomet.com/en/documentacion/monedas">https://docs.paycomet.com/en/documentacion/monedas</a>
     */
    public PTPVMerchant(String amount, String order, String currency) {
        this.amount = amount;
        this.order = order;
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public String getOrder() {
        return order;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAuthCode() {
        return authCode;
    }

    @Override
    public String toString() {
        return "PTPVMerchant{" +
                "amount='" + amount + '\'' +
                ", order='" + order + '\'' +
                ", currency='" + currency + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }
}
