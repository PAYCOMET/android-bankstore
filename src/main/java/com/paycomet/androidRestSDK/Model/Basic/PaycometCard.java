package com.paycomet.androidRestSDK.Model.Basic;

import static com.paycomet.androidRestSDK.Utils.Constants.CARD_BRAND;
import static com.paycomet.androidRestSDK.Utils.Constants.CARD_CATEGORY;
import static com.paycomet.androidRestSDK.Utils.Constants.CARD_EEA;
import static com.paycomet.androidRestSDK.Utils.Constants.CARD_EXPIRY_DATE;
import static com.paycomet.androidRestSDK.Utils.Constants.CARD_HASH;
import static com.paycomet.androidRestSDK.Utils.Constants.CARD_ISO;
import static com.paycomet.androidRestSDK.Utils.Constants.CARD_NUMBER;
import static com.paycomet.androidRestSDK.Utils.Constants.CARD_PSD2;
import static com.paycomet.androidRestSDK.Utils.Constants.CARD_SEPA;
import static com.paycomet.androidRestSDK.Utils.Constants.CARD_TOKEN_COF;
import static com.paycomet.androidRestSDK.Utils.Constants.CARD_TYPE;

import com.google.gson.annotations.SerializedName;

public class PaycometCard {

    @SerializedName(CARD_NUMBER)
    private String number;

    @SerializedName(CARD_BRAND)
    private String cardBrand;

    @SerializedName(CARD_TYPE)
    private String cardType;

    @SerializedName(CARD_ISO)
    private String cardIso;

    @SerializedName(CARD_EXPIRY_DATE)
    private String expiryDate;

    @SerializedName(CARD_HASH)
    private String cardHash;

    @SerializedName(CARD_CATEGORY)
    private String cardCategory;

    @SerializedName(CARD_SEPA)
    private String sepaCard;

    @SerializedName(CARD_PSD2)
    private String psd2Card;

    @SerializedName(CARD_TOKEN_COF)
    private String tokenCOF;

    @SerializedName(CARD_EEA)
    private String eeaCard;

    private String cvv;

    public PaycometCard(String number, String expiryDate, String cvv) {
        this.number = number;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public PaycometCard(String number, String expiryDate, String cardBrand, String cardType, String cardIso) {
        this.number = number;
        this.cardBrand = cardBrand;
        this.cardType = cardType;
        this.cardIso = cardIso;
        this.expiryDate = expiryDate;
    }

    public String getNumber() {
        return number;
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

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCardHash() {
        return cardHash;
    }

    public String getCardCategory() {
        return cardCategory;
    }

    public String getSepaCard() {
        return sepaCard;
    }

    public String getPsd2Card() {
        return psd2Card;
    }

    public String getTokenCOF() {
        return tokenCOF;
    }

    public String getEeaCard() {
        return eeaCard;
    }

    public String getCvv() {
        return cvv;
    }

    @Override
    public String toString() {
        return "PaycometCard{" +
                "number='" + number + '\'' +
                ", cardBrand='" + cardBrand + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardIso='" + cardIso + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cardHash='" + cardHash + '\'' +
                ", cardCategory='" + cardCategory + '\'' +
                ", sepaCard='" + sepaCard + '\'' +
                ", psd2Card='" + psd2Card + '\'' +
                ", tokenCOF='" + tokenCOF + '\'' +
                ", eeaCard='" + eeaCard + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}
