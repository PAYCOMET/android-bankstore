
package com.paytpv.androidsdk.Model.Basic;

import com.google.gson.annotations.SerializedName;

import static com.paytpv.androidsdk.Utils.Constants.SUBSCRIPTION_AMOUNT;
import static com.paytpv.androidsdk.Utils.Constants.SUBSCRIPTION_CURRENCY;
import static com.paytpv.androidsdk.Utils.Constants.SUBSCRIPTION_ENDDATE;
import static com.paytpv.androidsdk.Utils.Constants.SUBSCRIPTION_ORDER;
import static com.paytpv.androidsdk.Utils.Constants.SUBSCRIPTION_PERIODICITY;
import static com.paytpv.androidsdk.Utils.Constants.SUBSCRIPTION_STARTDATE;

/**
 * Represents the subscription's details
 */
public class PTPVSubscription {
    /**
     * Amount of the operation in integer format. 1.00 EURO = 100, 4.50 EUROS = 450...
     */
    @SerializedName(SUBSCRIPTION_AMOUNT)
    private String subscriptionAmount;

    /**
     * First characters of the reference of the operation.<br>
     * <br>
     * Do not include the characters “[“ or “]”, they will be used to recognize the user of the
     * business.
     */
    @SerializedName(SUBSCRIPTION_ORDER)
    private String subscriptionOrder;

    /**
     * Currency of the transaction. See more details at <a href=
     * "https://docs.paycomet.com/en/documentacion/monedas">https://docs.paycomet.com/en/documentacion/monedas</a>
     */
    @SerializedName(SUBSCRIPTION_CURRENCY)
    private String subscriptionCurrency;

    /**
     * Subscription start date. If the value is empty the date is the same day of registration. The
     * format of the date is YYYY-MM-DD
     */
    @SerializedName(SUBSCRIPTION_STARTDATE)
    private String subscriptionStartDate;

    /**
     * Subscription end date. It may not be later than the Subscription start date + 5 years. The
     * format of the date is YYYY-MM-DD
     */
    @SerializedName(SUBSCRIPTION_ENDDATE)
    private String subscriptionEndDate;

    /**
     * Frequency of collection from the start date. The number expresses Days. It may not be greater
     * than 365 days
     */
    @SerializedName(SUBSCRIPTION_PERIODICITY)
    private String subscriptionPeriodicity;

    /**
     * If the registration process involves the payment of the first installment the value of this
     * parameter must be 1. If you only want the subscription registration without the payment of
     * the first installment (it will be executed with the parameters sent) its value must be 0
     */
    private String execute;

    /**
     * Creates a subscription.
     *
     * @param subscriptionAmount Amount of the operation in integer format. 1.00 EURO = 100, 4.50
     *            EUROS = 450...
     * @param subscriptionOrder First characters of the reference of the operation. Do not include
     *            the characters “[“ or “]”, they will be used to recognize the user of the
     *            business.
     * @param subscriptionCurrency Currency of the transaction. See more details at <a href=
     *            "https://docs.paycomet.com/en/documentacion/monedas">https://docs.paycomet.com/en/documentacion/monedas</a>
     * @param subscriptionStartDate Subscription start date. If the value is empty the date is the
     *            same day of registration. The format of the date is YYYY-MM-DD
     * @param subscriptionEndDate Subscription end date. It may not be later than the Subscription
     *            start date + 5 years. The format of the date is YYYY-MM-DD
     * @param subscriptionPeriodicity Frequency of collection from the start date. The number
     *            expresses Days. It may not be greater than 365 days
     */
    public PTPVSubscription(String subscriptionAmount, String subscriptionOrder,
            String subscriptionCurrency, String subscriptionStartDate, String subscriptionEndDate,
            String subscriptionPeriodicity) {
        this.subscriptionAmount = subscriptionAmount;
        this.subscriptionOrder = subscriptionOrder;
        this.subscriptionCurrency = subscriptionCurrency;
        this.subscriptionStartDate = subscriptionStartDate;
        this.subscriptionEndDate = subscriptionEndDate;
        this.subscriptionPeriodicity = subscriptionPeriodicity;
    }

    /**
     * Creates a subscription.
     *
     * @param subscriptionAmount Amount of the operation in integer format. 1.00 EURO = 100, 4.50
     *            EUROS = 450...
     * @param subscriptionOrder Original reference of the operation + [DS_IDUSER] + date of the
     *            transaction in format YYYYMMDD
     * @param subscriptionCurrency Currency of the transaction. See more details at <a href=
     *            "https://docs.paycomet.com/en/documentacion/monedas">https://docs.paycomet.com/en/documentacion/monedas</a>
     */
    public PTPVSubscription(String subscriptionAmount, String subscriptionOrder,
            String subscriptionCurrency) {
        this.subscriptionAmount = subscriptionAmount;
        this.subscriptionOrder = subscriptionOrder;
        this.subscriptionCurrency = subscriptionCurrency;
    }

    public String getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public String getSubscriptionOrder() {
        return subscriptionOrder;
    }

    public String getSubscriptionCurrency() {
        return subscriptionCurrency;
    }

    public String getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public String getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public String getSubscriptionPeriodicity() {
        return subscriptionPeriodicity;
    }

    public String getExecute() {
        return execute;
    }

    public void setExecute(String execute) {
        this.execute = execute;
    }

    @Override
    public String toString() {
        return "PTPVSubscription{" +
                "subscriptionAmount='" + subscriptionAmount + '\'' +
                ", subscriptionOrder='" + subscriptionOrder + '\'' +
                ", subscriptionCurrency='" + subscriptionCurrency + '\'' +
                ", subscriptionStartDate='" + subscriptionStartDate + '\'' +
                ", subscriptionEndDate='" + subscriptionEndDate + '\'' +
                ", subscriptionPeriodicity='" + subscriptionPeriodicity + '\'' +
                '}';
    }
}
