
package com.paytpv.androidsdk.Utils;

import com.paytpv.androidsdk.Model.Basic.PTPVCard;
import com.paytpv.androidsdk.Model.Basic.PTPVConfiguration;
import com.paytpv.androidsdk.Model.Basic.PTPVMerchant;
import com.paytpv.androidsdk.Model.Basic.PTPVProduct;
import com.paytpv.androidsdk.Model.Basic.PTPVSubscription;
import com.paytpv.androidsdk.Model.Basic.PTPVUser;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVExecutePurchaseDcc;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.paytpv.androidsdk.Utils.Constants.CARD_CVV;
import static com.paytpv.androidsdk.Utils.Constants.CARD_EXPIRY_DATE;
import static com.paytpv.androidsdk.Utils.Constants.CARD_NUMBER;
import static com.paytpv.androidsdk.Utils.Constants.CREATE_SUBSCRIPTION;
import static com.paytpv.androidsdk.Utils.Constants.DCC_CURRENCY;
import static com.paytpv.androidsdk.Utils.Constants.DCC_SESSION;
import static com.paytpv.androidsdk.Utils.Constants.EDIT_SUBSCRIPTION;
import static com.paytpv.androidsdk.Utils.Constants.JET_ID;
import static com.paytpv.androidsdk.Utils.Constants.JET_TOKEN;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_AMOUNT;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_AUTHCODE;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_CODE;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_CURRENCY;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_ORDER;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_SIGNATURE;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_TERMINAL;
import static com.paytpv.androidsdk.Utils.Constants.ORIGINAL_IP;
import static com.paytpv.androidsdk.Utils.Constants.PRODUCT_DESCRIPTION;
import static com.paytpv.androidsdk.Utils.Constants.PRODUCT_OWNER;
import static com.paytpv.androidsdk.Utils.Constants.PRODUCT_SCORING;
import static com.paytpv.androidsdk.Utils.Constants.SUBSCRIPTION_AMOUNT;
import static com.paytpv.androidsdk.Utils.Constants.SUBSCRIPTION_CURRENCY;
import static com.paytpv.androidsdk.Utils.Constants.SUBSCRIPTION_ENDDATE;
import static com.paytpv.androidsdk.Utils.Constants.SUBSCRIPTION_EXECUTE;
import static com.paytpv.androidsdk.Utils.Constants.SUBSCRIPTION_ORDER;
import static com.paytpv.androidsdk.Utils.Constants.SUBSCRIPTION_PERIODICITY;
import static com.paytpv.androidsdk.Utils.Constants.SUBSCRIPTION_STARTDATE;
import static com.paytpv.androidsdk.Utils.Constants.TIMESTAMP;
import static com.paytpv.androidsdk.Utils.Constants.USER_ID;
import static com.paytpv.androidsdk.Utils.Constants.USER_TOKEN;

/**
 * Class containing all the necessary methods for building request's body
 */
public class AddRequestParams {

    /**
     * Adds the configuration parameters to every JsonObject request
     *
     * @param mRequest The JSON object containing the parameters for the request
     * @param mConfiguration The PTPVConfiguration instance needed for generating the signature
     */
    public static void addConfigurationParams(JsonObject mRequest, PTPVConfiguration mConfiguration) {
        mRequest.addProperty(MERCHANT_CODE, mConfiguration.getMerchantCode());
        mRequest.addProperty(MERCHANT_TERMINAL, mConfiguration.getTerminal());
    }

    /**
     * Builds the request body for getting the current IP
     *
     * @param mRequest The JSON object containing the parameters for the request
     * @param configuration The PTPVConfiguration instance needed for generating the signature
     */
    public static void addIpParams(JsonObject mRequest, PTPVConfiguration configuration) {
        String now = formatTimestamp();
        mRequest.addProperty(TIMESTAMP, now);
        mRequest.addProperty(MERCHANT_SIGNATURE, Signatures.getIpHash(now, configuration));
    }

    /**
     * Builds the request body for creating a user with a token previously obtained from BankStore JET solution
     *
     * @param mRequest The JSON object containing the parameters for the request
     * @param configuration The PTPVConfiguration instance needed for generating the signature
     * @param ip User's IP
     * @param jetToken Token obtained from javascript
     */
    public static void addJetParams(JsonObject mRequest, PTPVConfiguration configuration, String ip,
            String jetToken) {
        mRequest.addProperty(ORIGINAL_IP, ip);
        mRequest.addProperty(JET_ID, configuration.getJetId());
        mRequest.addProperty(JET_TOKEN, jetToken);
    }

    /**
     * Builds the request body for operations involving a PTPVCard ("add_user")
     *
     * @param mRequest The JSON object containing the parameters for the request
     * @param card A PTPVCard instance containing necessary parameters for the request
     * @param ip User's IP
     */
    public static void addCardParams(JsonObject mRequest, PTPVCard card, String ip) {
        mRequest.addProperty(ORIGINAL_IP, ip);
        mRequest.addProperty(CARD_NUMBER, card.getNumber());
        mRequest.addProperty(CARD_EXPIRY_DATE, card.getExpiryDate());
        mRequest.addProperty(CARD_CVV, card.getCvv());
    }

    /**
     * Builds the request body for operations involving a PTPVUser ("info_user", "remove_user" etc)
     *
     * @param mRequest The JSON object containing the parameters for the request
     * @param user A PTPVUser instance containing necessary parameters for the request
     * @param ip User's IP
     */
    public static void addUserParams(JsonObject mRequest, PTPVUser user, String ip) {
        mRequest.addProperty(ORIGINAL_IP, "5.14.232.34");
        mRequest.addProperty(USER_ID, user.getUserId());
        mRequest.addProperty(USER_TOKEN, user.getUserToken());
    }

    /**
     * Builds the request body for operations involving a PTPVMerchant ("execute_purchase",
     * "execute_purchase_dcc" etc)
     *
     * @param mRequest The JSON object containing the parameters for the request
     * @param merchant A PTPVMerchant instance containing necessary parameters for the request
     */
    public static void addMerchantParams(JsonObject mRequest, PTPVMerchant merchant) {
        if (merchant.getAmount() != null) {
            mRequest.addProperty(MERCHANT_AMOUNT, merchant.getAmount());
        }
        if (merchant.getOrder() != null) {
            mRequest.addProperty(MERCHANT_ORDER, merchant.getOrder());
        }
        if (merchant.getCurrency() != null) {
            mRequest.addProperty(MERCHANT_CURRENCY, merchant.getCurrency());
        }
        if (merchant.getAuthCode() != null) {
            mRequest.addProperty(MERCHANT_AUTHCODE, merchant.getAuthCode());
        }
    }

    /**
     * Builds the request body for operations involving conversions ("confirm_purchase_dcc")
     *
     * @param mRequest The JSON object containing the parameters for the request
     * @param purchaseDcc A PTPVExecutePurchaseDcc instance containing necessary parameters for the
     *            request
     */
    public static void addConfirmPurchaseDCCParams(JsonObject mRequest,
            PTPVExecutePurchaseDcc purchaseDcc) {
        mRequest.addProperty(DCC_CURRENCY, purchaseDcc.getDccCurrency());
        mRequest.addProperty(DCC_SESSION, purchaseDcc.getDccSession());
    }

    /**
     * Builds the request body for operations involving a PTPVProduct ("execute_purchase",
     * "execute_purchase_dcc" etc)
     *
     * @param mRequest The JSON object containing the parameters for the request
     * @param product A PTPVProduct instance containing necessary parameters for the request
     */
    public static void addProductParams(JsonObject mRequest, PTPVProduct product) {
        if (product.getDescription() != null) {
            mRequest.addProperty(PRODUCT_DESCRIPTION, product.getDescription());
        }
        if (product.getOwner() != null) {
            mRequest.addProperty(PRODUCT_OWNER, product.getOwner());
        }
        if (product.getScoring() != null) {
            mRequest.addProperty(PRODUCT_SCORING, product.getScoring());
        }
    }

    /**
     * Builds the request body for creating a PTPVSubscription ("create_subscription")
     *
     * @param mRequest The JSON object containing the parameters for the request
     * @param subscription A PTPVSubscription instance containing necessary parameters for the
     *            request
     * @param request The request that needs to be made ("create", "edit" etc)
     */
    public static void addSubscriptionParams(JsonObject mRequest, PTPVSubscription subscription,
            String request) {
        mRequest.addProperty(SUBSCRIPTION_STARTDATE, subscription.getSubscriptionStartDate());
        mRequest.addProperty(SUBSCRIPTION_ENDDATE, subscription.getSubscriptionEndDate());
        mRequest.addProperty(SUBSCRIPTION_PERIODICITY, subscription.getSubscriptionPeriodicity());
        mRequest.addProperty(SUBSCRIPTION_AMOUNT, subscription.getSubscriptionAmount());

        if (request.equals(CREATE_SUBSCRIPTION)) {
            mRequest.addProperty(SUBSCRIPTION_CURRENCY, subscription.getSubscriptionCurrency());
            mRequest.addProperty(SUBSCRIPTION_ORDER, subscription.getSubscriptionOrder());
        }

        if (request.equals(EDIT_SUBSCRIPTION)) {
            mRequest.addProperty(SUBSCRIPTION_EXECUTE, subscription.getExecute());
        }
    }

    /**
     * Removes all the JSON object's key-value pairs
     *
     * @param mRequest The JSON object containing the parameters for the request
     */
    public static void removeParams(JsonObject mRequest) {
        mRequest.remove(TIMESTAMP);

        mRequest.remove(CARD_NUMBER);
        mRequest.remove(CARD_EXPIRY_DATE);
        mRequest.remove(CARD_CVV);

        mRequest.remove(USER_ID);
        mRequest.remove(USER_TOKEN);

        mRequest.remove(MERCHANT_AMOUNT);
        mRequest.remove(MERCHANT_ORDER);
        mRequest.remove(MERCHANT_CURRENCY);
        mRequest.remove(MERCHANT_AUTHCODE);

        mRequest.remove(PRODUCT_DESCRIPTION);
        mRequest.remove(PRODUCT_OWNER);
        mRequest.remove(PRODUCT_SCORING);

        mRequest.remove(DCC_CURRENCY);
        mRequest.remove(DCC_SESSION);

        mRequest.remove(SUBSCRIPTION_STARTDATE);
        mRequest.remove(SUBSCRIPTION_ENDDATE);
        mRequest.remove(SUBSCRIPTION_ORDER);
        mRequest.remove(SUBSCRIPTION_PERIODICITY);
        mRequest.remove(SUBSCRIPTION_AMOUNT);
        mRequest.remove(SUBSCRIPTION_CURRENCY);

        mRequest.remove(MERCHANT_SIGNATURE);
    }

    /**
     * Returns the current time in "yyyyMMddhhmmss" format
     *
     * @return Current date
     */
    private static String formatTimestamp() {
        DateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

        return sdf.format(Calendar.getInstance().getTime());
    }
}
