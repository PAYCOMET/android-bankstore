package com.paycomet.androidRestSDK;

import com.google.gson.JsonObject;
import com.paycomet.androidRestSDK.Model.Basic.PaycometMethod;
import com.paycomet.androidRestSDK.Model.Requests.PaycometBalance;
import com.paycomet.androidRestSDK.Model.Requests.PaycometConfirmPurchaseDcc;
import com.paycomet.androidRestSDK.Model.Requests.PaycometCreatePreauth;
import com.paycomet.androidRestSDK.Model.Requests.PaycometCreatePreauthRtoken;
import com.paycomet.androidRestSDK.Model.Requests.PaycometExchange;
import com.paycomet.androidRestSDK.Model.Requests.PaycometExecutePurchase;
import com.paycomet.androidRestSDK.Model.Requests.PaycometExecutePurchaseDcc;
import com.paycomet.androidRestSDK.Model.Requests.PaycometExecutePurchaseRtoken;
import com.paycomet.androidRestSDK.Model.Requests.PaycometExecuteRefund;
import com.paycomet.androidRestSDK.Model.Requests.PaycometForm;
import com.paycomet.androidRestSDK.Model.Requests.PaycometError;
import com.paycomet.androidRestSDK.Model.Requests.PaycometIP;
import com.paycomet.androidRestSDK.Model.Requests.PaycometHeartbeat;
import com.paycomet.androidRestSDK.Model.Requests.PaycometInfoUser;
import com.paycomet.androidRestSDK.Model.Requests.PaycometMarketplace;
import com.paycomet.androidRestSDK.Model.Requests.PaycometPreauthCancel;
import com.paycomet.androidRestSDK.Model.Requests.PaycometPreauthConfirm;
import com.paycomet.androidRestSDK.Model.Requests.PaycometRemoveResponse;
import com.paycomet.androidRestSDK.Model.Requests.PaycometSubscriptionResponse;
import com.paycomet.androidRestSDK.Model.Basic.PaycometUser;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PaycometApi {

    // IP
    @POST("/v1/ip")
    @Headers({"Content-type: application/json"})
    Observable<PaycometIP> postIp(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // IP
    @POST("/v1/ip/remote")
    @Headers({"Content-type: application/json"})
    Observable<PaycometIP> postIpRemote(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // FORM
    @POST("/v1/form")
    @Headers({"Content-type: application/json"})
    Observable<PaycometForm> postForm(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // BALANCE
    @POST("/v1/balance")
    @Headers({"Content-type: application/json"})
    Observable<PaycometBalance> postBalance(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // CARDS
    @POST("/v1/cards")
    @Headers({"Content-type: application/json"})
    Observable<PaycometUser> postCards(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // CARDS EDIT
    @POST("/v1/cards/edit")
    @Headers({"Content-type: application/json"})
    Observable<PaycometUser> postCardsEdit(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // CARDS DELETE
    @POST("/v1/cards/delete")
    @Headers({"Content-type: application/json"})
    Observable<PaycometRemoveResponse> postCardsDelete(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // CARDS INFO
    @POST("/v1/cards/info")
    @Headers({"Content-type: application/json"})
    Observable<PaycometInfoUser> postCardsInfo(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // HEARDBEAT
    @POST("/v1/heartbeat")
    @Headers({"Content-type: application/json"})
    Observable<PaycometHeartbeat> postHeartbeat(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // MARKETPLACE SPLIT TRANSFER
    @POST("/v1/marketplace/split-transfer")
    @Headers({"Content-type: application/json"})
    Observable<PaycometMarketplace> postMarketplaceSplitTransfer(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // MARKETPLACE SPLIT TRANSFER REVERSAL
    @POST("/v1/marketplace/split-transfer-reversal")
    @Headers({"Content-type: application/json"})
    Observable<PaycometMarketplace> postMarketplaceSplitTransferReversal(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // PAYMENTS EXECUTE PURCHASE
    @POST("/v1/payments")
    @Headers({"Content-type: application/json"})
    Observable<PaycometExecutePurchase> postExecutePurchase(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // PAYMENTS EXECUTE RTOKEN
    @POST("/v1/payments/rtoken")
    @Headers({"Content-type: application/json"})
    Observable<PaycometExecutePurchaseRtoken> postExecutePurchaseRtoken(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // PAYMENTS CANCEL PREATHORIZATION
    @POST("/v1/payments/{order}/preauth/cancel")
    @Headers({"Content-type: application/json"})
    Observable<PaycometPreauthCancel> postPreauthCancel(@Header("PAYCOMET-API-TOKEN") String api_key, @Path(value = "order", encoded = true) String order, @Body JsonObject request);

    // PAYMENTS CONFIRM PREATHORIZATION
    @POST("/v1/payments/{order}/preauth/confirm")
    @Headers({"Content-type: application/json"})
    Observable<PaycometPreauthConfirm> postPreauthConfirm(@Header("PAYCOMET-API-TOKEN") String api_key, @Path(value = "order", encoded = true) String order, @Body JsonObject request);

    // PAYMENTS CREATE PREATHORIZATION
    @POST("/v1/payments/preauth")
    @Headers({"Content-type: application/json"})
    Observable<PaycometCreatePreauth> postCreatePreauth(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // PAYMENTS CREATE PREATHORIZATION RTOKEN
    @POST("/v1/payments/preauthrtoken")
    @Headers({"Content-type: application/json"})
    Observable<PaycometCreatePreauthRtoken> postCreatePreauthRtoken(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // PAYMENTS EXECUTE REFUND
    @POST("/v1/payments/{order}/refund")
    @Headers({"Content-type: application/json"})
    Observable<PaycometExecuteRefund> postExecuteRefund(@Header("PAYCOMET-API-TOKEN") String api_key, @Path(value = "order", encoded = true) String order, @Body JsonObject request);

    // PAYMENTS DCC PURCHASE CREATE
    @POST("/v1/payments/dcc")
    @Headers({"Content-type: application/json"})
    Observable<PaycometExecutePurchaseDcc> postDCCPurchaseCreate(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // PAYMENTS DCC PURCHASE CONFIRM
    @POST("/v1/payments/dcc/{order}/confirm")
    @Headers({"Content-type: application/json"})
    Observable<PaycometConfirmPurchaseDcc> postDCCPurchaseConfirm(@Header("PAYCOMET-API-TOKEN") String api_key, @Path(value = "order", encoded = true) String order, @Body JsonObject request);

    // INFO ERRORS
    @POST("/v1/errors")
    @Headers({"Content-type: application/json"})
    Observable<PaycometError> postInfoErrors(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // EXCHANGE
    @POST("/v1/exchange")
    @Headers({"Content-type: application/json"})
    Observable<PaycometExchange> postExchange(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // METHODS
    @POST("/v1/methods")
    @Headers({"Content-type: application/json"})
    Observable<List<PaycometMethod>> postMethods(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // CREATE SUBSCRIPTION
    @POST("/v1/subscription")
    @Headers({"Content-type: application/json"})
    Observable<PaycometSubscriptionResponse> postCreateSubscription(@Header("PAYCOMET-API-TOKEN") String api_key, @Body JsonObject request);

    // EDIT SUBSCRIPTION
    @POST("/v1/subscription/{order}/edit")
    @Headers({"Content-type: application/json"})
    Observable<PaycometSubscriptionResponse> postEditSubscription(@Header("PAYCOMET-API-TOKEN") String api_key, @Path(value = "order", encoded = true) String order, @Body JsonObject request);

    // REMOVE SUBSCRIPTION
    @POST("/v1/subscription/{order}/remove")
    @Headers({"Content-type: application/json"})
    Observable<PaycometRemoveResponse> postRemoveSubscription(@Header("PAYCOMET-API-TOKEN") String api_key, @Path(value = "order", encoded = true) String order, @Body JsonObject request);
}
