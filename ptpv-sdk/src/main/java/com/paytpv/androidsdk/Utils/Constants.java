
package com.paytpv.androidsdk.Utils;

/**
 * Class containing constants
 */
public class Constants {

    /**
     * SharedPreferences constants
     */
    public static final String SHARED_PREFERENCES_NAME = "PAYTPV";
    public static final String DATE_KEY = "cached_date";
    public static final String IP_KEY = "cached_ip";

    /**
     * Networking constants
     */
    public static final String WS_URL = "https://api.paycomet.com/gateway/json-bankstore/";
    public static final String ADD_USER = "add_user";
    public static final String ADD_USER_TOKEN = "add_user_token";
    public static final String GET_IP = "json-remote-ip";
    public static final String INFO_USER = "info_user";
    public static final String REMOVE_USER = "remove_user";
    public static final String EXECUTE_PURCHASE = "execute_purchase";
    public static final String EXECUTE_PURCHASE_DCC = "execute_purchase_dcc";
    public static final String CONFIRM_PURCHASE_DCC = "confirm_purchase_dcc";
    public static final String EXECUTE_REFUND = "execute_refund";
    public static final String CREATE_SUBSCRIPTION = "create_subscription";
    public static final String EDIT_SUBSCRIPTION = "edit_subscription";
    public static final String REMOVE_SUBSCRIPTION = "remove_subscription";
    public static final String CREATE_SUBSCRIPTION_TOKEN = "create_subscription_token";
    public static final String CREATE_PREAUTHORIZATION = "create_preauthorization";
    public static final String CONFIRM_PREAUTHORIZATION = "preauthorization_confirm";
    public static final String CANCEL_PREAUTHORIZATION = "preauthorization_cancel";
    public static final String CONFIRM_DEFERRED_PREAUTHORIZATION = "deferred_preauthorization_confirm";
    public static final String CANCEL_DEFERRED_PREAUTHORIZATION = "deferred_preauthorization_cancel";

    /**
     * Mandatory constants for each request
     */
    public static final String MERCHANT_CODE = "DS_MERCHANT_MERCHANTCODE";
    public static final String MERCHANT_TERMINAL = "DS_MERCHANT_TERMINAL";
    public static final String MERCHANT_SIGNATURE = "DS_MERCHANT_MERCHANTSIGNATURE";
    public static final String ORIGINAL_IP = "DS_ORIGINAL_IP";

    /**
     * Constants representing IP parameters
     */
    public static final String TIMESTAMP = "DS_TIMESTAMP";
    public static final String REMOTE_ADDRESS = "DS_REMOTE_ADDRESS";
    public static final int CACHE_INTERVAL = 60000;

    /**
     * Card constants
     */
    public static final String CARD_NUMBER = "DS_MERCHANT_PAN";
    public static final String CARD_EXPIRY_DATE = "DS_MERCHANT_EXPIRYDATE";
    public static final String CARD_CVV = "DS_MERCHANT_CVV2";
    public static final String CARD_BRAND = "DS_CARD_BRAND";
    public static final String CARD_TYPE = "DS_CARD_TYPE";
    public static final String CARD_ISO = "DS_CARD_I_COUNTRY_ISO3";
    public static final String CARD_INFO_EXPIRY_DATE = "DS_EXPIRYDATE";

    /**
     * User constants
     */
    public static final String USER_ID = "DS_IDUSER";
    public static final String USER_TOKEN = "DS_TOKEN_USER";

    /**
     * Jet constants
     */
    public static final String JET_ID = "DS_MERCHANT_JETID";
    public static final String JET_TOKEN = "DS_MERCHANT_JETTOKEN";

    /**
     * Purchase constants
     */
    public static final String MERCHANT_AMOUNT = "DS_MERCHANT_AMOUNT";
    public static final String MERCHANT_ORDER = "DS_MERCHANT_ORDER";
    public static final String MERCHANT_CURRENCY = "DS_MERCHANT_CURRENCY";
    public static final String MERCHANT_AUTHCODE = "DS_MERCHANT_AUTHCODE";
    public static final String MERCHANT_CARD_COUNTRY = "DS_MERCHANT_CARDCOUNTRY";

    /**
     * Dcc purchase constants
     */
    public static final String DCC_SESSION = "DS_MERCHANT_DCC_SESSION";
    public static final String DCC_CURRENCY = "DS_MERCHANT_DCC_CURRENCY";
    public static final String DCC_CURRENCY_ISO = "DS_MERCHANT_DCC_CURRENCYISO3";
    public static final String DCC_CURRENCY_NAME = "DS_MERCHANT_DCC_CURRENCYNAME";
    public static final String DCC_EXCHANGE_RATE = "DS_MERCHANT_DCC_EXCHANGE";
    public static final String DCC_AMOUNT = "DS_MERCHANT_DCC_AMOUNT";
    public static final String DCC_MARKUP = "DS_MERCHANT_DCC_MARKUP";
    public static final String DCC_CARD_COUNTRY = "DS_MERCHANT_DCC_CARDCOUNTRY";

    /**
     * Product constants
     */
    public static final String PRODUCT_DESCRIPTION = "DS_MERCHANT_PRODUCTDESCRIPTION";
    public static final String PRODUCT_OWNER = "DS_MERCHANT_OWNER";
    public static final String PRODUCT_SCORING = "DS_MERCHANT_SCORING";

    /**
     * Subscription constants
     */
    public static final String SUBSCRIPTION_AMOUNT = "DS_SUBSCRIPTION_AMOUNT";
    public static final String SUBSCRIPTION_ORDER = "DS_SUBSCRIPTION_ORDER";
    public static final String SUBSCRIPTION_CURRENCY = "DS_SUBSCRIPTION_CURRENCY";
    public static final String SUBSCRIPTION_STARTDATE = "DS_SUBSCRIPTION_STARTDATE";
    public static final String SUBSCRIPTION_ENDDATE = "DS_SUBSCRIPTION_ENDDATE";
    public static final String SUBSCRIPTION_PERIODICITY = "DS_SUBSCRIPTION_PERIODICITY";
    public static final String SUBSCRIPTION_EXECUTE = "DS_EXECUTE";

    /**
     * Response constants
     */
    public static final String ERROR_ID = "DS_ERROR_ID";
    public static final String RESPONSE = "DS_RESPONSE";
}
