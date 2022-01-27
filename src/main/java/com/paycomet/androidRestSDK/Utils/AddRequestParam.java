package com.paycomet.androidRestSDK.Utils;

import static com.paycomet.androidRestSDK.Utils.Constants.OPERATION_TYPE_AUTHORIZATION;
import static com.paycomet.androidRestSDK.Utils.Constants.OPERATION_TYPE_DCC_AUTHORIZATION;
import static com.paycomet.androidRestSDK.Utils.Constants.OPERATION_TYPE_PREAUTHORIZATION;
import static com.paycomet.androidRestSDK.Utils.Constants.OPERATION_TYPE_SUBSCRIPTION;

import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddRequestParam {

    public static void addConfigurationParams(
            JsonObject mRequest,
            String terminal
    ){
        mRequest.addProperty("terminal", terminal);
    }

    public static void addConfigurationPaymentParams(
            JsonObject mRequest,
            String terminal
    ){
        JsonObject payment = new JsonObject();
        payment.addProperty("terminal", terminal);
        mRequest.add("payment", payment);
    }

    public static void addIpParams(
            JsonObject mRequest,
            String ip
    ) {
        mRequest.addProperty("ip", ip);
    }

    public static void addFormParams(
            JsonObject mRequest,
            String language,
            String amount,
            String currency,
            String originalIp,
            String secure,
            String userInteraction,
            String urlOk,
            String urlKo
    ) {

        mRequest.addProperty("operationType", OPERATION_TYPE_AUTHORIZATION);
        mRequest.addProperty("language", language);

        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("order", formatTimestamp());
        payment.addProperty("amount", amount);
        payment.addProperty("currency", currency);
        payment.addProperty("originalIp", originalIp);
        payment.addProperty("secure", secure);
        payment.addProperty("userInteraction", userInteraction);
        payment.addProperty("urlOk", urlOk);
        payment.addProperty("urlKo", urlKo);

        mRequest.add("payment", payment);
    }

    public static void addFormPreauthorizationParams(
            JsonObject mRequest,
            String language,
            String amount,
            String currency,
            String originalIp,
            String secure,
            String urlOk,
            String urlKo,
            String idUser,
            String tokenUser
    ) {

        mRequest.addProperty("operationType", OPERATION_TYPE_PREAUTHORIZATION);
        mRequest.addProperty("language", language);

        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("order", formatTimestamp());
        payment.addProperty("amount", amount);
        payment.addProperty("currency", currency);
        payment.addProperty("originalIp", originalIp);
        payment.addProperty("secure", secure);
        payment.addProperty("userInteraction", "1");
        payment.addProperty("urlOk", urlOk);
        payment.addProperty("urlKo", urlKo);
        payment.addProperty("idUser", idUser);
        payment.addProperty("tokenUser", tokenUser);

        mRequest.add("payment", payment);
    }

    public static void addFormSubscriptionParams(
            JsonObject mRequest,
            String language,
            String amount,
            String currency,
            String originalIp,
            String urlOk,
            String urlKo,
            String startDate,
            String endDate,
            String periodicity
    ) {

        mRequest.addProperty("operationType", OPERATION_TYPE_SUBSCRIPTION);
        mRequest.addProperty("language", language);

        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("order", formatTimestamp());
        payment.addProperty("amount", amount);
        payment.addProperty("currency", currency);
        payment.addProperty("originalIp", originalIp);
        payment.addProperty("secure", "1");
        payment.addProperty("userInteraction", "1");
        payment.addProperty("urlOk", urlOk);
        payment.addProperty("urlKo", urlKo);

        mRequest.add("payment", payment);

        JsonObject subscription = new JsonObject();
        subscription.addProperty("startDate", startDate);
        subscription.addProperty("endDate", endDate);
        subscription.addProperty("periodicity", periodicity);

        mRequest.add("subscription", subscription);
    }

    public static void addFormDccParams(
            JsonObject mRequest,
            String language,
            String amount,
            String currency,
            String originalIp,
            String secure,
            String urlOk,
            String urlKo,
            String idUser,
            String tokenUser
    ) {

        mRequest.addProperty("operationType", OPERATION_TYPE_DCC_AUTHORIZATION);
        mRequest.addProperty("language", language);

        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("order", formatTimestamp());
        payment.addProperty("amount", amount);
        payment.addProperty("currency", currency);
        payment.addProperty("originalIp", originalIp);
        payment.addProperty("secure", secure);
        payment.addProperty("userInteraction", "1");
        payment.addProperty("urlOk", urlOk);
        payment.addProperty("urlKo", urlKo);
        payment.addProperty("idUser", idUser);
        payment.addProperty("tokenUser", tokenUser);

        mRequest.add("payment", payment);
    }

    public static void addInfoErrorParams(
            JsonObject mRequest,
            String errorCode,
            String lang
    ) {

        mRequest.addProperty("errorCode", errorCode);
        mRequest.addProperty("lang", lang);
    }

    public static void addExchangeParams(
            JsonObject mRequest,
            String amount,
            String originalCurrency,
            String finalCurrency
    ) {

        mRequest.addProperty("amount", amount);
        mRequest.addProperty("originalCurrency", originalCurrency);
        mRequest.addProperty("finalCurrency", finalCurrency);
    }

    public static void addCardParams(

            JsonObject mRequest,
            String pan,
            String expiryMonth,
            String expiryYear,
            String cvc2,
            String productDescription,
            String language
    ) {

        mRequest.addProperty("pan", pan);
        mRequest.addProperty("expiryMonth", expiryMonth);
        mRequest.addProperty("expiryYear", expiryYear);
        mRequest.addProperty("cvc2", cvc2);
        mRequest.addProperty("productDescription", productDescription);
        mRequest.addProperty("language", language);
    }

    public static void addCardEditParams(

            JsonObject mRequest,
            String idUser,
            String tokenUser,
            String cvc2,
            String expiryMonth,
            String expiryYear
    ) {

        mRequest.addProperty("idUser", idUser);
        mRequest.addProperty("expiryMonth", expiryMonth);
        mRequest.addProperty("expiryYear", expiryYear);
        mRequest.addProperty("cvc2", cvc2);
        mRequest.addProperty("tokenUser", tokenUser);
    }

    public static void addCardTokenParams(JsonObject mRequest, String jetToken) {
        mRequest.addProperty("jetToken", jetToken);
    }

    public static void addCardInfoParams(

            JsonObject mRequest,
            String idUser,
            String tokenUser
    ) {

        mRequest.addProperty("idUser", idUser);
        mRequest.addProperty("tokenUser", tokenUser);
    }

    public static void addCardDeleteParams(

            JsonObject mRequest,
            String idUser,
            String tokenUser
    ) {

        mRequest.addProperty("idUser", idUser);
        mRequest.addProperty("tokenUser", tokenUser);
    }

    public static void addPaymentsParams(
            JsonObject mRequest,
            String amount,
            String currency,
            String originalIp,
            String methodId,
            String idUser,
            String tokenUser,
            String secure,
            String userInteraction,
            String productDescription
    ) {
        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("order", formatTimestamp());
        payment.addProperty("amount", amount);
        payment.addProperty("currency", currency);
        payment.addProperty("originalIp", originalIp);
        payment.addProperty("methodId", methodId);
        payment.addProperty("idUser", idUser);
        payment.addProperty("tokenUser", tokenUser);
        payment.addProperty("secure", secure);
        payment.addProperty("userInteraction", userInteraction);
        payment.addProperty("productDescription", productDescription);
        mRequest.add("payment", payment);
    }

    public static void addPaymentsRtokenParams(
            JsonObject mRequest,
            String amount,
            String currency,
            String originalIp,
            String methodId,
            String idUser,
            String tokenUser,
            String secure,
            String userInteraction,
            String productDescription,
            String merchantIdentifier
    ) {
        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("order", formatTimestamp());
        payment.addProperty("amount", amount);
        payment.addProperty("currency", currency);
        payment.addProperty("originalIp", originalIp);
        payment.addProperty("methodId", methodId);
        payment.addProperty("idUser", idUser);
        payment.addProperty("tokenUser", tokenUser);
        payment.addProperty("secure", secure);
        payment.addProperty("userInteraction", userInteraction);
        payment.addProperty("productDescription", productDescription);
        payment.addProperty("merchantIdentifier", merchantIdentifier);
        mRequest.add("payment", payment);
    }

    public static void addRefundParams(
            JsonObject mRequest,
            String amount,
            String currency,
            String authCode,
            String originalIp
    ) {
        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("amount", amount);
        payment.addProperty("currency", currency);
        payment.addProperty("authCode", authCode);
        payment.addProperty("originalIp", originalIp);
        mRequest.add("payment", payment);
    }

    public static void addPreauthCancelParams(
            JsonObject mRequest,
            String amount,
            String originalIp,
            String authCode,
            String deferred,
            String notifyDirectPayment
    ) {
        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("amount", amount);
        payment.addProperty("authCode", authCode);
        payment.addProperty("originalIp", originalIp);
        payment.addProperty("deferred", deferred);
        payment.addProperty("notifyDirectPayment", notifyDirectPayment);
        mRequest.add("payment", payment);
    }

    public static void addPreauthConfirmParams(
            JsonObject mRequest,
            String amount,
            String originalIp,
            String authCode,
            String deferred,
            String notifyDirectPayment
    ) {
        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("amount", amount);
        payment.addProperty("authCode", authCode);
        payment.addProperty("originalIp", originalIp);
        payment.addProperty("deferred", deferred);
        payment.addProperty("notifyDirectPayment", notifyDirectPayment);
        mRequest.add("payment", payment);
    }

    public static void addCreatePreauthParams(
            JsonObject mRequest,
            String amount,
            String currency,
            String originalIp,
            String methodId,
            String secure,
            String idUser,
            String tokenUser,
            String userInteraction
    ) {
        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("order", formatTimestamp());
        payment.addProperty("amount", amount);
        payment.addProperty("currency", currency);
        payment.addProperty("originalIp", originalIp);
        payment.addProperty("methodId", methodId);
        payment.addProperty("secure", secure);
        payment.addProperty("idUser", idUser);
        payment.addProperty("tokenUser", tokenUser);
        payment.addProperty("userInteraction", userInteraction);
        mRequest.add("payment", payment);
    }

    public static void addCreatePreauthRtokenParams(
            JsonObject mRequest,
            String amount,
            String currency,
            String originalIp,
            String methodId,
            String secure,
            String merchantIdentifier,
            String userInteraction
    ) {
        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("order", formatTimestamp());
        payment.addProperty("amount", amount);
        payment.addProperty("currency", currency);
        payment.addProperty("originalIp", originalIp);
        payment.addProperty("methodId", methodId);
        payment.addProperty("secure", secure);
        payment.addProperty("merchantIdentifier", merchantIdentifier);
        payment.addProperty("userInteraction", userInteraction);
        mRequest.add("payment", payment);
    }

    public static void addSplitTransferParams(
            JsonObject mRequest,
            String authCode,
            String splitId,
            String amount,
            String currency

    ) {
        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("authCode", authCode);
        payment.addProperty("order", formatTimestamp());
        mRequest.add("payment", payment);

        JsonObject submerchant = new JsonObject();
        submerchant.addProperty("splitId", splitId);
        submerchant.addProperty("amount", amount);
        submerchant.addProperty("currency", currency);
        mRequest.add("submerchant", submerchant);
    }

    public static void addSplitTransferReversalParams(
            JsonObject mRequest,
            String authCode,
            String splitId,
            String amount,
            String currency,
            String splitAuthCode

    ) {
        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("authCode", authCode);
        payment.addProperty("order", formatTimestamp());
        mRequest.add("payment", payment);

        JsonObject submerchant = new JsonObject();
        submerchant.addProperty("splitId", splitId);
        submerchant.addProperty("amount", amount);
        submerchant.addProperty("currency", currency);
        submerchant.addProperty("splitAuthCode", splitAuthCode);
        mRequest.add("submerchant", submerchant);
    }

    public static void addCreateSubscriptionParams(
            JsonObject mRequest,
            String methodId,
            String amount,
            String currency,
            String originalIp,
            String idUser,
            String tokenUser,
            String secure,
            String scoring,
            String productDescription,
            String merchantDescriptor,
            String userInteraction,
            String trxType,
            String scaException,
            String urlOk,
            String urlKo,
            String notifyDirectPayment,
            String startDate,
            String endDate,
            String periodicity

    ) {

        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("order", formatTimestamp());
        payment.addProperty("amount", amount);
        payment.addProperty("currency", currency);
        payment.addProperty("originalIp", originalIp);
        payment.addProperty("secure", secure);
        payment.addProperty("userInteraction", userInteraction);
        payment.addProperty("urlOk", urlOk);
        payment.addProperty("urlKo", urlKo);
        payment.addProperty("methodId", methodId);
        payment.addProperty("idUser", idUser);
        payment.addProperty("tokenUser", tokenUser);
        payment.addProperty("scoring", scoring);
        payment.addProperty("productDescription", productDescription);
        payment.addProperty("merchantDescriptor", merchantDescriptor);
        payment.addProperty("trxType", trxType);
        payment.addProperty("scaException", scaException);
        payment.addProperty("notifyDirectPayment", notifyDirectPayment);
        mRequest.add("payment", payment);

        JsonObject subscription = new JsonObject();
        subscription.addProperty("startDate", startDate);
        subscription.addProperty("endDate", endDate);
        subscription.addProperty("periodicity", periodicity);
        mRequest.add("subscription", subscription);
    }

    public static void addEditSubscriptionParams(
            JsonObject mRequest,
            String methodId,
            String amount,
            String originalIp,
            String idUser,
            String tokenUser,
            String startDate,
            String endDate,
            String periodicity,
            String active

    ) {

        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("amount", amount);
        payment.addProperty("originalIp", originalIp);
        payment.addProperty("methodId", methodId);
        payment.addProperty("idUser", idUser);
        payment.addProperty("tokenUser", tokenUser);
        mRequest.add("payment", payment);

        JsonObject subscription = new JsonObject();
        subscription.addProperty("startDate", startDate);
        subscription.addProperty("endDate", endDate);
        subscription.addProperty("periodicity", periodicity);
        subscription.addProperty("active", active);
        mRequest.add("subscription", subscription);
    }

    public static void addRemoveSubscriptionParams(
            JsonObject mRequest,
            String order,
            String idUser,
            String tokenUser

    ) {

        JsonObject payment = mRequest.getAsJsonObject("payment");
        payment.addProperty("order", order);
        payment.addProperty("idUser", idUser);
        payment.addProperty("tokenUser", tokenUser);
        mRequest.add("payment", payment);
    }

    private static String formatTimestamp() {
        return new SimpleDateFormat("yyyyMMddhhmmss").format(Calendar.getInstance().getTime());
    }
}
