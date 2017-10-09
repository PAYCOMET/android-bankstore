
package com.paytpv.androidsdk;

import com.paytpv.androidsdk.Model.PTPVRequests.PTPVGetIP;
import com.paytpv.androidsdk.Model.PTPVRequests.PTPVRemoveResponse;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVConfirmPurchaseDcc;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVExecutePurchaseDcc;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVExecuteRefund;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVPurchaseDetails;
import com.paytpv.androidsdk.Model.PTPVRequests.Subscription.PTPVSubscriptionResponse;
import com.paytpv.androidsdk.Model.PTPVRequests.User.PTPVAddUser;
import com.paytpv.androidsdk.Model.PTPVRequests.User.PTPVInfoUser;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.paytpv.androidsdk.Utils.Constants.ADD_USER;
import static com.paytpv.androidsdk.Utils.Constants.ADD_USER_TOKEN;
import static com.paytpv.androidsdk.Utils.Constants.CANCEL_DEFERRED_PREAUTHORIZATION;
import static com.paytpv.androidsdk.Utils.Constants.CANCEL_PREAUTHORIZATION;
import static com.paytpv.androidsdk.Utils.Constants.CONFIRM_DEFERRED_PREAUTHORIZATION;
import static com.paytpv.androidsdk.Utils.Constants.CONFIRM_PREAUTHORIZATION;
import static com.paytpv.androidsdk.Utils.Constants.CONFIRM_PURCHASE_DCC;
import static com.paytpv.androidsdk.Utils.Constants.CREATE_PREAUTHORIZATION;
import static com.paytpv.androidsdk.Utils.Constants.CREATE_SUBSCRIPTION;
import static com.paytpv.androidsdk.Utils.Constants.CREATE_SUBSCRIPTION_TOKEN;
import static com.paytpv.androidsdk.Utils.Constants.EDIT_SUBSCRIPTION;
import static com.paytpv.androidsdk.Utils.Constants.EXECUTE_PURCHASE;
import static com.paytpv.androidsdk.Utils.Constants.EXECUTE_PURCHASE_DCC;
import static com.paytpv.androidsdk.Utils.Constants.EXECUTE_REFUND;
import static com.paytpv.androidsdk.Utils.Constants.GET_IP;
import static com.paytpv.androidsdk.Utils.Constants.INFO_USER;
import static com.paytpv.androidsdk.Utils.Constants.REMOVE_SUBSCRIPTION;
import static com.paytpv.androidsdk.Utils.Constants.REMOVE_USER;

interface PTPVApi {

    /**
     * Retrofit API call for a "json-remote-ip" request
     *
     * @param request The request body
     * @return A PTPVGetIp response object
     */
    @Headers("Content-type: application/json")
    @POST(GET_IP)
    Observable<PTPVGetIP> getIP(@Body JsonObject request);

    /**
     * Retrofit API call for a "add_user" request
     *
     * @param request The request body
     * @return A PTPVAddUser response object
     */
    @Headers("Content-type: application/json")
    @POST(ADD_USER)
    Observable<PTPVAddUser> addUser(@Body JsonObject request);

    /**
     * Retrofit API call for a "add_user_token" request
     *
     * @param request The request body
     * @return A PTPVAddUser response object
     */
    @Headers("Content-type: application/json")
    @POST(ADD_USER_TOKEN)
    Observable<PTPVAddUser> addUserToken(@Body JsonObject request);

    /**
     * Retrofit API call for a "info_user" request
     *
     * @param request The request body
     * @return A PTPVInfoUser response object
     */
    @Headers("Content-type: application/json")
    @POST(INFO_USER)
    Observable<PTPVInfoUser> infoUser(@Body JsonObject request);

    /**
     * Retrofit API call for a "remove_user" request
     *
     * @param request The request body
     * @return A PTPVRemoveUser response object
     */
    @Headers("Content-type: application/json")
    @POST(REMOVE_USER)
    Observable<PTPVRemoveResponse> removeUser(@Body JsonObject request);

    /**
     * Retrofit API call for a "execute_purchase" request
     *
     * @param request The request body
     * @return A PTPVPurchaseDetails response object
     */
    @Headers("Content-type: application/json")
    @POST(EXECUTE_PURCHASE)
    Observable<PTPVPurchaseDetails> executePurchase(@Body JsonObject request);

    /**
     * Retrofit API call for a "execute_purchase_dcc" request
     *
     * @param request The request body
     * @return A PTPVExecutePurchaseDcc response object
     */
    @Headers("Content-type: application/json")
    @POST(EXECUTE_PURCHASE_DCC)
    Observable<PTPVExecutePurchaseDcc> executePurchaseDcc(@Body JsonObject request);

    /**
     * Retrofit API call for a "confirm_purchase_dcc" request
     *
     * @param request The request body
     * @return A PTPVConfirmPurchaseDcc response object
     */
    @Headers("Content-type: application/json")
    @POST(CONFIRM_PURCHASE_DCC)
    Observable<PTPVConfirmPurchaseDcc> confirmPurchaseDcc(@Body JsonObject request);

    /**
     * Retrofit API call for a "execute_refund" request
     *
     * @param request The request body
     * @return A PTPVExecuteRefund response object
     */
    @Headers("Content-type: application/json")
    @POST(EXECUTE_REFUND)
    Observable<PTPVExecuteRefund> executeRefund(@Body JsonObject request);

    /**
     * Retrofit API call for a "create_subscription" request
     *
     * @param request The request body
     * @return A PTPVSubscriptionResponse response object
     */
    @Headers("Content-type: application/json")
    @POST(CREATE_SUBSCRIPTION)
    Observable<PTPVSubscriptionResponse> createSubscription(@Body JsonObject request);

    /**
     * Retrofit API call for a "edit_subscription" request
     *
     * @param request The request body
     * @return A PTPVSubscriptionResponse response object
     */
    @Headers("Content-type: application/json")
    @POST(EDIT_SUBSCRIPTION)
    Observable<PTPVSubscriptionResponse> editSubscription(@Body JsonObject request);

    /**
     * Retrofit API call for a "remove_subscription" request
     *
     * @param request The request body
     * @return A PTPVRemoveResponse response object
     */
    @Headers("Content-type: application/json")
    @POST(REMOVE_SUBSCRIPTION)
    Observable<PTPVRemoveResponse> removeSubscription(@Body JsonObject request);

    /**
     * Retrofit API call for a "create_subscription_token" request
     *
     * @param request The request body
     * @return A PTPVSubscriptionResponse response object
     */
    @Headers("Content-type: application/json")
    @POST(CREATE_SUBSCRIPTION_TOKEN)
    Observable<PTPVSubscriptionResponse> createSubscriptionToken(@Body JsonObject request);

    /**
     * Retrofit API call for a "create_preauthorization" request
     *
     * @param request The request body
     * @return A PTPVPurchaseDetails response object
     */
    @Headers("Content-type: application/json")
    @POST(CREATE_PREAUTHORIZATION)
    Observable<PTPVPurchaseDetails> createPreauthorization(@Body JsonObject request);

    /**
     * Retrofit API call for a "preauthorization_confirm" request
     *
     * @param request The request body
     * @return A PTPVPurchaseDetails response object
     */
    @Headers("Content-type: application/json")
    @POST(CONFIRM_PREAUTHORIZATION)
    Observable<PTPVPurchaseDetails> confirmPreauthorization(@Body JsonObject request);

    /**
     * Retrofit API call for a "preauthorization_cancel" request
     *
     * @param request The request body
     * @return A PTPVPurchaseDetails response object
     */
    @Headers("Content-type: application/json")
    @POST(CANCEL_PREAUTHORIZATION)
    Observable<PTPVPurchaseDetails> cancelPreauthorization(@Body JsonObject request);

    /**
     * Retrofit API call for a "deferred_preauthorization_confirm" request
     *
     * @param request The request body
     * @return A PTPVPurchaseDetails response object
     */
    @Headers("Content-type: application/json")
    @POST(CONFIRM_DEFERRED_PREAUTHORIZATION)
    Observable<PTPVPurchaseDetails> confirmDeferredPreauthorization(@Body JsonObject request);

    /**
     * Retrofit API call for a "deferred_preauthorization_cancel" request
     *
     * @param request The request body
     * @return A PTPVPurchaseDetails response object
     */
    @Headers("Content-type: application/json")
    @POST(CANCEL_DEFERRED_PREAUTHORIZATION)
    Observable<PTPVPurchaseDetails> cancelDeferredPreauthorization(@Body JsonObject request);
}
