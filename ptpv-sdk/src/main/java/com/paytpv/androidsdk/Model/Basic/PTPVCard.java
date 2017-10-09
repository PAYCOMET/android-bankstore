
package com.paytpv.androidsdk.Model.Basic;

import com.google.gson.annotations.SerializedName;

import static com.paytpv.androidsdk.Utils.Constants.CARD_BRAND;
import static com.paytpv.androidsdk.Utils.Constants.CARD_INFO_EXPIRY_DATE;
import static com.paytpv.androidsdk.Utils.Constants.CARD_ISO;
import static com.paytpv.androidsdk.Utils.Constants.CARD_NUMBER;
import static com.paytpv.androidsdk.Utils.Constants.CARD_TYPE;

/**
 * Represents the user's credit card details.
 */
public class PTPVCard {
    /**
     * Card number, without any spaces or dashes. When receiving the card details from PAYTPV, only
     * the last four digits will be returned. The rest will be masked with X.
     */
    @SerializedName(CARD_NUMBER)
    private String number;

    /**
     * Expiry date of the card, expressed as “mmyy” (two-digits for the month and two-digits for the
     * year). When receiving the card details from PAYTPV, the expiry date is in the format YYYY/MM.
     */
    @SerializedName(CARD_INFO_EXPIRY_DATE)
    private String expiryDate;

    /**
     * CVV2 Code of the card
     */
    private String cvv;

    /**
     * Card brand (Visa, MasterCard, American Express, etc)
     */
    @SerializedName(CARD_BRAND)
    private String cardBrand;

    /**
     * Type of card (DEBIT, CREDIT, etc)
     */
    @SerializedName(CARD_TYPE)
    private String cardType;

    /**
     * ISO3 Code of the country of the issuer of the card
     */
    @SerializedName(CARD_ISO)
    private String cardIso;

    /**
     * Creates a PTPVCard.
     *
     * @param number Card number, without any spaces or dashes.
     * @param expiryDate Expiry date of the card, expressed as “mmyy” (two-digits for the month and
     *            two-digits for the year).
     * @param cvv CVV2 Code of the card.
     */
    public PTPVCard(String number, String expiryDate, String cvv) {
        this.number = number;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    /**
     * Creates a PTPVCard with more information.
     *
     * @param number Card number, without any spaces or dashes.
     * @param expiryDate Expiry date of the card, expressed as “mmyy” (two-digits for the month and
     *            two-digits for the year).
     * @param cardBrand Card brand (Visa, MasterCard, American Express, etc)
     * @param cardType Type of card (DEBIT, CREDIT, etc)
     * @param cardIso ISO3 Code of the country of the issuer of the card
     */
    public PTPVCard(String number, String expiryDate, String cardBrand, String cardType,
            String cardIso) {
        this.number = number;
        this.expiryDate = expiryDate;
        this.cardBrand = cardBrand;
        this.cardType = cardType;
        this.cardIso = cardIso;
    }

    public String getNumber() {
        return number;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardIso() {
        return cardIso;
    }

    @Override
    public String toString() {
        return "PTPVCard{" +
                "number='" + number + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", cardBrand='" + cardBrand + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardIso='" + cardIso + '\'' +
                '}';
    }
}
