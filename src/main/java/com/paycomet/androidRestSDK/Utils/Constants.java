package com.paycomet.androidRestSDK.Utils;

public class Constants {

    /**
     * SharedPreferences constants
     */
    public static final String SHARED_PREFERENCES_NAME = "PAYCOMET";

    /**
     * Networking constants
     */
    public static final String API_URL = "https://rest.paycomet.com/";

    /**
     * Constants representing IP parameters
     */
    public static final String REMOTE_ADDRESS = "remoteAddress";
    public static final String ISO_NUM = "isoNum";
    public static final String ISO2 = "iso2";
    public static final String ISO3 = "iso3";
    public static final String IP_NAME = "name";

    /**
     * Constants User
     */
    public static final String USER_ID = "idUser";
    public static final String USER_TOKEN = "tokenUser";

    /**
     * Constants Form
     */
    public static final String CHALLENGE_URL = "challengeUrl";

    /**
     * Constants Balance
     */
    public static final String BALANCE_GLOBAL = "global";
    public static final String BALANCE_AVAILABLE = "available";
    public static final String DEPOSIT = "deposit";

    /**
     * Constants Cards
     */
    public static final String CARD_NUMBER = "pan";
    public static final String CARD_BRAND = "cardBrand";
    public static final String CARD_TYPE = "cardType";
    public static final String CARD_ISO = "cardICountryISO3";
    public static final String CARD_EXPIRY_DATE = "expiryDate";
    public static final String CARD_HASH = "cardHash";
    public static final String CARD_CATEGORY = "cardCategory";
    public static final String CARD_SEPA = "sepaCard";
    public static final String CARD_PSD2 = "psd2Card";
    public static final String CARD_TOKEN_COF = "tokenCOF";
    public static final String CARD_EEA = "eeaCard";

    /**
     * Constants Heartbeat
     */
    public static final String HEARTBEAT_TIME = "time";
    public static final String HEARTBEAT_PROCESSOR_TIME = "processorTime";
    public static final String HEART_PROCESSOR_STATUS = "processorStatus";

    /**
     * Constants Merchant
     */
    public static final String MERCHANT_AMOUNT = "amount";
    public static final String MERCHANT_ORDER = "order";
    public static final String MERCHANT_CURRENCY = "currency";
    public static final String MERCHANT_AUTHCODE = "authCode";

    /**
     * Constants Payment
     */
    public static final String PAYMENT_METHOD_ID = "methodId";
    public static final String PAYMENT_CHALLENGE_URL = "challengeUrl";
    public static final String PAYMENT_ID_USER = "idUser";
    public static final String PAYMENT_TOKEN_USER = "tokenUser";
    public static final String PAYMENT_CARD_COUNTRY = "cardCountry";

    /**
     * Constants Dcc
     */
    public static final String DCC = "dcc";
    public static final String DCC_SESSION = "session";
    public static final String DCC_CURRENCY = "currency";
    public static final String DCC_CURRENCY_ISO3 = "currencyIso3";
    public static final String DCC_CURRENCY_NAME = "currencyName";
    public static final String DCC_EXCHANGE = "exchange";
    public static final String DCC_AMOUNT = "amount";
    public static final String DCC_DATE = "date";
    public static final String DCC_MARKUP = "markup";


    /**
     * Constants Marketplace
     */
    public static final String MARKETPLACE_ORDER = "order";

    /**
     * Constants Submerchant
     */
    public static final String SUBMERCHANT = "submerchant";
    public static final String SUBMERCHANT_AMOUNT = "amount";
    public static final String SUBMERCHANT_CURRENCY = "currency";
    public static final String SUBMERCHANT_SPLIT_AUTH_CODE = "splitAuthCode";

    /**
     * Constants Exchange
     */
    public static final String EXCHANGE_AMOUNT = "amount";

    /**
     * Constants Methods
     */
    public static final String METHOD_ID = "id";
    public static final String METHOD_NAME = "name";
    public static final String METHOD_DESCRIPTION = "description";
    public static final String METHOD_ACTIVE = "active";
    public static final String METHOD_ALLOW_API_REFUNDS = "allowAPIRefunds";
    public static final String METHOD_LOGO_SCUARE = "logo_square";
    public static final String METHOD_LOGO_LANDSCAPE = "logo_landscape";

    /**
     * Constants Subscriptions
     */
    public static final String SUBSCRIPTION = "subscription";
    public static final String SUBSCRIPTION_START_DATE = "startDate";
    public static final String SUBSCRIPTION_END_DATE = "endDate";
    public static final String SUBSCRIPTION_PERIODICITY = "periodicity";

    /**
     * Constants Operation Types
     */
    public static final String OPERATION_TYPE_AUTHORIZATION = "1";
    public static final String OPERATION_TYPE_PREAUTHORIZATION = "3";
    public static final String OPERATION_TYPE_SUBSCRIPTION = "9";
    public static final String OPERATION_TYPE_DCC_AUTHORIZATION = "116";

    /**
     * Errors constants
     */
    public static final String ERROR_ID = "errorCode";
    public static final String ERROR_DESCRIPTION = "errorDescription";

}
