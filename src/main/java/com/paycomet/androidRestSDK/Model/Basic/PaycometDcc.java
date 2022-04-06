package com.paycomet.androidRestSDK.Model.Basic;

import static com.paycomet.androidRestSDK.Utils.Constants.DCC_AMOUNT;
import static com.paycomet.androidRestSDK.Utils.Constants.DCC_CURRENCY;
import static com.paycomet.androidRestSDK.Utils.Constants.DCC_CURRENCY_ISO3;
import static com.paycomet.androidRestSDK.Utils.Constants.DCC_CURRENCY_NAME;
import static com.paycomet.androidRestSDK.Utils.Constants.DCC_DATE;
import static com.paycomet.androidRestSDK.Utils.Constants.DCC_EXCHANGE;
import static com.paycomet.androidRestSDK.Utils.Constants.DCC_MARKUP;
import static com.paycomet.androidRestSDK.Utils.Constants.DCC_SESSION;

import com.google.gson.annotations.SerializedName;

public class PaycometDcc {

    @SerializedName(DCC_SESSION)
    private String session;

    @SerializedName(DCC_CURRENCY)
    private String currency;

    @SerializedName(DCC_CURRENCY_ISO3)
    private String currencyIso3;

    @SerializedName(DCC_CURRENCY_NAME)
    private String currencyName;

    @SerializedName(DCC_EXCHANGE)
    private String exchange;

    @SerializedName(DCC_AMOUNT)
    private String amount;

    @SerializedName(DCC_DATE)
    private String date;

    @SerializedName(DCC_MARKUP)
    private String markup;

    public PaycometDcc() {}

    public PaycometDcc(String session, String currency, String currencyIso3, String currencyName, String exchange, String amount, String date, String markup) {
        this.session = session;
        this.currency = currency;
        this.currencyIso3 = currencyIso3;
        this.currencyName = currencyName;
        this.exchange = exchange;
        this.amount = amount;
        this.date = date;
        this.markup = markup;
    }

    public String getSession() {
        return session;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCurrencyIso3() {
        return currencyIso3;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getExchange() {
        return exchange;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getMarkup() {
        return markup;
    }

    @Override
    public String toString() {
        return "PaycometDcc{" +
                "session='" + session + '\'' +
                ", currency='" + currency + '\'' +
                ", currencyIso3='" + currencyIso3 + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", exchange='" + exchange + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                ", markup='" + markup + '\'' +
                '}';
    }
}
