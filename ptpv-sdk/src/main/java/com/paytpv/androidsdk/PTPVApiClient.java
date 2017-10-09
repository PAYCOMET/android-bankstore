
package com.paytpv.androidsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.paytpv.androidsdk.Interfaces.PTPVCallbacks;
import com.paytpv.androidsdk.Model.Basic.PTPVCard;
import com.paytpv.androidsdk.Model.Basic.PTPVConfiguration;
import com.paytpv.androidsdk.Model.Basic.PTPVError;
import com.paytpv.androidsdk.Model.Basic.PTPVExecuteSubscription;
import com.paytpv.androidsdk.Model.Basic.PTPVMerchant;
import com.paytpv.androidsdk.Model.Basic.PTPVProduct;
import com.paytpv.androidsdk.Model.Basic.PTPVSubscription;
import com.paytpv.androidsdk.Model.Basic.PTPVUser;
import com.paytpv.androidsdk.Model.PTPVRequests.PTPVGetIP;
import com.paytpv.androidsdk.Model.PTPVRequests.Preauthorization.PTPVPreauthorizationOperations;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVExecutePurchaseDcc;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVPurchaseDetails;
import com.paytpv.androidsdk.Utils.Signatures;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.paytpv.androidsdk.Utils.AddRequestParams.addCardParams;
import static com.paytpv.androidsdk.Utils.AddRequestParams.addConfigurationParams;
import static com.paytpv.androidsdk.Utils.AddRequestParams.addConfirmPurchaseDCCParams;
import static com.paytpv.androidsdk.Utils.AddRequestParams.addIpParams;
import static com.paytpv.androidsdk.Utils.AddRequestParams.addJetParams;
import static com.paytpv.androidsdk.Utils.AddRequestParams.addMerchantParams;
import static com.paytpv.androidsdk.Utils.AddRequestParams.addProductParams;
import static com.paytpv.androidsdk.Utils.AddRequestParams.addSubscriptionParams;
import static com.paytpv.androidsdk.Utils.AddRequestParams.addUserParams;
import static com.paytpv.androidsdk.Utils.CardValidation.checkCVV;
import static com.paytpv.androidsdk.Utils.CardValidation.checkExpiryDate;
import static com.paytpv.androidsdk.Utils.CardValidation.checkNumber;
import static com.paytpv.androidsdk.Utils.Constants.CACHE_INTERVAL;
import static com.paytpv.androidsdk.Utils.Constants.CREATE_SUBSCRIPTION;
import static com.paytpv.androidsdk.Utils.Constants.DATE_KEY;
import static com.paytpv.androidsdk.Utils.Constants.EDIT_SUBSCRIPTION;
import static com.paytpv.androidsdk.Utils.Constants.IP_KEY;
import static com.paytpv.androidsdk.Utils.Constants.MERCHANT_SIGNATURE;
import static com.paytpv.androidsdk.Utils.Constants.SHARED_PREFERENCES_NAME;
import static com.paytpv.androidsdk.Utils.Constants.WS_URL;

/**
 * A service containing a PAYTPV API client
 */
public class PTPVApiClient {
    /**
     * A shared singleton API client
     */
    private static PTPVApiClient mInstance = null;

    /**
     * The client's configuration
     */
    private PTPVConfiguration mConfiguration;
    /**
     * A client for making requests to the PAYTPV API
     */
    private PTPVApi mPtpvApi;

    /**
     * Context for caching the IP in the SharedPreferences
     */
    private static Context mContext;

    /**
     * Get SharedPreferences for storing and reading the IP
     */
    private static SharedPreferences mPreferences;

    /**
     * Initializes the API client.
     */
    private PTPVApiClient() {
        // -----------------------------------------------------------------------------------------
        // Create a new Retrofit instance and build the API client based on it
        // -----------------------------------------------------------------------------------------
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(WS_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.mPtpvApi = mRetrofit.create(PTPVApi.class);
    }

    public static synchronized PTPVApiClient getInstance(Context context) {
        PTPVApiClient.mContext = context;
        PTPVApiClient.mPreferences = PTPVApiClient.mContext
                .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        if (mInstance == null) {
            mInstance = new PTPVApiClient();

            return mInstance;
        }
        return mInstance;
    }

    /**
     * Sets up the initial configuration for the client.
     *
     * @param mConfiguration PTPVConfiguration
     */
    public void setConfiguration(PTPVConfiguration mConfiguration) {
        this.mConfiguration = mConfiguration;
    }

    /**
     * Request for getting the IP (stored in SharedPreferences or by webservice)
     *
     * @param callback The callback to run with the returned PTPVGetIP object, or an error.
     */
    private void getIP(PTPVCallbacks.GetIPResponse callback) {

        // -------------------------------------------------------------------------
        // Get the cached date of the IP from the SharedPreferences and the
        // current date, both in milliseconds
        // -------------------------------------------------------------------------
        long cachedDate = Long.parseLong(getCachedDate());
        final long currentTime = new Date(System.currentTimeMillis()).getTime();

        // -------------------------------------------------------------------------
        // * Check if the cached date is one minute older than the current date
        // * If it is, reset the cached date to 0
        // -------------------------------------------------------------------------
        if (cachedDate != 0 && currentTime > (cachedDate + CACHE_INTERVAL)) {
            PTPVApiClient.mPreferences.edit().putString(DATE_KEY, "0").apply();
        }

        // -------------------------------------------------------------------------
        // If the cached date is 0, reset the IP to the default value
        // -------------------------------------------------------------------------
        if (Long.parseLong(getCachedDate()) == 0) {
            PTPVApiClient.mPreferences.edit().putString(IP_KEY, "").apply();
        }

        // -------------------------------------------------------------------------
        // Get the IP from the SharedPreferences
        // -------------------------------------------------------------------------
        String ip = getCachedIP();

        // -------------------------------------------------------------------------
        // If there is no IP stored, or if it expired from cache,
        // make a request to get it
        // -------------------------------------------------------------------------
        if (ip.isEmpty()) {
            JsonObject request = createRequest();

            addIpParams(request, this.mConfiguration);

            this.mPtpvApi.getIP(request)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(ptpvGetIP -> {
                        if (!ptpvGetIP.getErrorId().equals("0")) {
                            callback.returnGetIPError(new PTPVError(ptpvGetIP.getErrorId()));
                        } else {

                            // -------------------------------------------------------------------------
                            // * If the IP was not found in SharedPreferences, store it
                            // * Then return it in a callback
                            // -------------------------------------------------------------------------
                            PTPVApiClient.mPreferences.edit()
                                    .putString(IP_KEY, ptpvGetIP.getRemoteAddress()).apply();
                            PTPVApiClient.mPreferences.edit()
                                    .putString(DATE_KEY, String.valueOf(currentTime)).apply();

                            callback.returnGetIPResponse(ptpvGetIP);
                        }
                    });
        } else {

            // -------------------------------------------------------------------------
            // If the IP was found in SharedPreferences, return it in a callback
            // -------------------------------------------------------------------------
            callback.returnGetIPResponse(new PTPVGetIP(ip));
        }
    }

    /**
     * Adds a card to PAYTPV. <br>
     * <br>
     * This operation must be activated by PAYTPV. Contact us to register for the desired product.
     *
     * @param card A PTPVCard instance containing the credit card details.
     * @param callback The callback to run with the returned PTPVAddUser object, or an error.
     * @see PTPVCard
     */
    public void addUser(PTPVCard card, PTPVCallbacks.AddUserResponse callback) {
        // -------------------------------------------------------------------------
        // Check if the card is valid
        // -------------------------------------------------------------------------
        if (card == null) {
            return;
        }

        if (!checkNumber(card.getNumber()) || !checkExpiryDate(card.getExpiryDate())
                || !checkCVV(card.getCvv())) {
            return;
        }

        this.getIP(new PTPVCallbacks.GetIPResponse() {
            @Override
            public void returnGetIPError(PTPVError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void returnGetIPResponse(PTPVGetIP getIP) {
                // -------------------------------------------------------------------------
                // Create a new request object
                // -------------------------------------------------------------------------
                JsonObject request = createRequest();

                // -------------------------------------------------------------------------
                // Add parameters matching the new request
                // -------------------------------------------------------------------------
                addCardParams(request, card, getIP.getRemoteAddress());

                request.addProperty(MERCHANT_SIGNATURE,
                        Signatures.addUserHash(card, mConfiguration));

                // -------------------------------------------------------------------------
                // Make the desired request
                // -------------------------------------------------------------------------
                mPtpvApi.addUser(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ptpvAddUser -> {
                            if (!ptpvAddUser.getErrorId().equals("0")) {

                                // -------------------------------------------------------------------------
                                // In case of error, return it
                                // -------------------------------------------------------------------------
                                callback.returnAddUserError(
                                        new PTPVError(ptpvAddUser.getErrorId()));
                            } else {

                                // -------------------------------------------------------------------------
                                // Return the response
                                // -------------------------------------------------------------------------
                                callback.returnAddUserResponse(ptpvAddUser);
                            }
                        }, error -> {
                            error.printStackTrace();

                            callback.returnAddUserError(new PTPVError(error.getMessage()));
                        });
            }
        });
    }

    /**
     * Returns the user information for the registered user. <br>
     * <br>
     * This function will be used to confirm to the customers of the business which card will be
     * used to make the payment. This step is optional but it is convenient to avoid mistrust.
     *
     * @param user A PTPVUser instance containing the user registration within the system.
     * @param callback The callback to run with the returned PTPVInfoUser object, or an error.
     * @see PTPVUser
     */
    public void infoUser(PTPVUser user, PTPVCallbacks.InfoUserResponse callback) {
        if (user == null) {
            return;
        }

        this.getIP(new PTPVCallbacks.GetIPResponse() {
            @Override
            public void returnGetIPError(PTPVError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void returnGetIPResponse(PTPVGetIP getIP) {
                JsonObject request = createRequest();

                addUserParams(request, user, getIP.getRemoteAddress());

                request.addProperty(MERCHANT_SIGNATURE,
                        Signatures.infoUserHash(user, mConfiguration));

                mPtpvApi.infoUser(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ptpvInfoUser -> {
                            if (!ptpvInfoUser.getErrorId().equals("0")) {
                                callback.returnInfoUserError(
                                        new PTPVError(ptpvInfoUser.getErrorId()));
                            } else {
                                callback.returnInfoUserResponse(ptpvInfoUser);
                            }
                        }, error -> callback
                                .returnInfoUserError(new PTPVError(error.getMessage())));
            }
        });
    }

    /**
     * Removes a user from the account of the business.
     * 
     * @param user A PTPVUser instance containing the user registration within the system.
     * @param callback The callback to run with the returned PTPVRemoveUser object, or an error.
     * @see PTPVUser
     */
    public void removeUser(PTPVUser user, PTPVCallbacks.RemoveUserResponse callback) {
        if (user == null) {
            return;
        }

        this.getIP(new PTPVCallbacks.GetIPResponse() {
            @Override
            public void returnGetIPError(PTPVError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void returnGetIPResponse(PTPVGetIP getIP) {
                JsonObject request = createRequest();

                addUserParams(request, user, getIP.getRemoteAddress());

                request.addProperty(MERCHANT_SIGNATURE,
                        Signatures.infoUserHash(user, mConfiguration));

                mPtpvApi.removeUser(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ptpvRemoveUser -> {
                            if (!ptpvRemoveUser.getErrorId().equals("0")) {
                                callback.returnRemoveUserError(
                                        new PTPVError(ptpvRemoveUser.getErrorId()));
                            } else {
                                callback.returnRemoveUserResponse(ptpvRemoveUser);
                            }
                        }, error -> callback
                                .returnRemoveUserError(new PTPVError(error.getMessage())));
            }
        });
    }

    /**
     * Execute a payment with the user's details. <br>
     * <br>
     * Once the user is registered in the system, charges may be made to their account by sending
     * their credentials and data of the operation.
     *
     * @param user A PTPVUser instance containing the user details.
     * @param merchant A PTPVMerchant instance containing the order details.
     * @param product A PTPVProduct instance containing the product details. Optional.
     * @param callback The callback to run with the returned PTPVPurchaseDetails object, or an
     *            error.
     * @see PTPVUser
     * @see PTPVMerchant
     * @see PTPVProduct
     */
    public void executePurchase(PTPVUser user, PTPVMerchant merchant, PTPVProduct product,
            PTPVCallbacks.PurchaseDetailsResponse callback) {
        if (user == null || merchant == null) {
            return;
        }

        this.getIP(new PTPVCallbacks.GetIPResponse() {
            @Override
            public void returnGetIPError(PTPVError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void returnGetIPResponse(PTPVGetIP getIP) {
                JsonObject request = createRequest();

                addUserParams(request, user, getIP.getRemoteAddress());
                addMerchantParams(request, merchant);
                if (product != null) {
                    addProductParams(request, product);
                }

                request.addProperty(MERCHANT_SIGNATURE,
                        Signatures.executePurchaseHash(user, merchant, mConfiguration));

                mPtpvApi.executePurchase(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ptpvPurchaseDetails -> {
                            if (!ptpvPurchaseDetails.getErrorId().equals("0")) {
                                callback.returnPurchaseDetailsError(
                                        new PTPVError(ptpvPurchaseDetails.getErrorId()));
                            } else {
                                callback.returnPurchaseDetailsResponse(ptpvPurchaseDetails);
                            }
                        }, error -> callback
                                .returnPurchaseDetailsError(new PTPVError(error.getMessage())));
            }
        });
    }

    /**
     * Execute a Dynamic currency conversion purchase with the user's details. <br>
     * <br>
     * This operation must be activated by PAYTPV. Contact us to register for the desired product.
     * <br>
     * <br>
     * Once the user is registered on the system, they can make payments with their account by
     * sending their credentials and operation information. The DCC caseload requires that a payment
     * process is carried out in two steps: execute_purchase_dcc, where the native currency of the
     * card is received (in the case of the card having the same currency as the product associated
     * with the transaction, the result will be a 1:1 conversion) and will be subsequently confirmed
     * with the confirm_purchase_dcc method with the selected currency and the original session of
     * the transaction.
     *
     * @param user A PTPVUser instance containing the user details.
     * @param merchant A PTPVMerchant instance containing the order details.
     * @param product A PTPVProduct instance containing the product details. Optional.
     * @param callback The callback to run with the returned PTPVExecutePurchaseDcc object, or an
     *            error.
     * @see PTPVUser
     * @see PTPVMerchant
     * @see PTPVProduct
     */
    public void executePurchaseDcc(PTPVUser user, PTPVMerchant merchant, PTPVProduct product,
            PTPVCallbacks.ExecutePurchaseDccResponse callback) {
        if (user == null || merchant == null) {
            return;
        }

        this.getIP(new PTPVCallbacks.GetIPResponse() {
            @Override
            public void returnGetIPError(PTPVError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void returnGetIPResponse(PTPVGetIP getIP) {
                JsonObject request = createRequest();

                addUserParams(request, user, getIP.getRemoteAddress());
                addMerchantParams(request, merchant);
                if (product != null) {
                    addProductParams(request, product);
                }

                request.addProperty(MERCHANT_SIGNATURE,
                        Signatures.executePurchaseHash(user, merchant, mConfiguration));

                mPtpvApi.executePurchaseDcc(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ptpvExecutePurchaseDcc -> {
                            if (!ptpvExecutePurchaseDcc.getErrorId().equals("0")) {
                                callback.returnExecutePurchaseDccError(
                                        new PTPVError(ptpvExecutePurchaseDcc.getErrorId()));
                            } else {
                                callback.returnExecutePurchaseDccResponse(ptpvExecutePurchaseDcc);
                            }
                        }, error -> callback
                                .returnExecutePurchaseDccError(new PTPVError(error.getMessage())));
            }
        });
    }

    /**
     * Confirms the currency for a dcc transaction. <br>
     * <br>
     * Once the 'DS_MERCHANT_DCC_SESSION' parameter has been restored when a DCC purchase has been
     * made, the state of the transaction will be “waiting” for the currency confirmation. The
     * business must suggest to the client the currency in which they wish to pay (showing the
     * conversion in real time) and when it is selected, the business must confirm the authorisation
     * with the currency selected by the end user.
     *
     * @param user A PTPVUser instance containing the user details.
     * @param merchant A PTPVMerchant instance containing the order details.
     * @param purchaseDcc A PTPVExecutePurchaseDcc instance containing the conversion variables of
     *            the operation.
     * @param callback The callback to run with the returned PTPVConfirmPurchaseDcc object, or an
     *            error.
     * @see PTPVUser
     * @see PTPVMerchant
     * @see PTPVExecutePurchaseDcc
     */
    public void confirmPurchaseDcc(PTPVUser user, PTPVMerchant merchant,
            PTPVExecutePurchaseDcc purchaseDcc, PTPVCallbacks.ConfirmPurchaseDccResponse callback) {
        if (user == null || merchant == null || purchaseDcc == null) {
            return;
        }

        this.getIP(new PTPVCallbacks.GetIPResponse() {
            @Override
            public void returnGetIPError(PTPVError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void returnGetIPResponse(PTPVGetIP getIP) {
                JsonObject request = createRequest();

                addUserParams(request, user, getIP.getRemoteAddress());
                addMerchantParams(request, merchant);
                addConfirmPurchaseDCCParams(request, purchaseDcc);

                request.addProperty(MERCHANT_SIGNATURE,
                        Signatures.confirmPurchaseHash(merchant, purchaseDcc, mConfiguration));

                mPtpvApi.confirmPurchaseDcc(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ptpvConfirmPurchaseDcc -> {
                            if (!ptpvConfirmPurchaseDcc.getErrorId().equals("0")) {
                                callback.returnConfirmPurchaseDccError(
                                        new PTPVError(ptpvConfirmPurchaseDcc.getErrorId()));
                            } else {
                                callback.returnConfirmPurchaseDccResponse(ptpvConfirmPurchaseDcc);
                            }
                        }, error -> callback
                                .returnConfirmPurchaseDccError(new PTPVError(error.getMessage())));
            }
        });
    }

    /**
     * Refunds a purchase.
     *
     * @param user A PTPVUser instance containing the user details.
     * @param purchaseDetails A PTPVPurchaseDetails instance containing the order details. It needs
     *            to contain the field "authCode", otherwise it will result in an error.
     * @param callback The callback to run with the returned PTPVConfirmPurchaseDcc object, or an
     *            error.
     * @see PTPVUser
     * @see PTPVPurchaseDetails
     */
    public void executeRefund(PTPVUser user, PTPVPurchaseDetails purchaseDetails,
            PTPVCallbacks.ExecuteRefundResponse callback) {
        if (user == null || purchaseDetails == null) {
            return;
        }

        this.getIP(new PTPVCallbacks.GetIPResponse() {
            @Override
            public void returnGetIPError(PTPVError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void returnGetIPResponse(PTPVGetIP getIP) {
                JsonObject request = createRequest();

                addUserParams(request, user, getIP.getRemoteAddress());
                addMerchantParams(request, purchaseDetails);

                request.addProperty(MERCHANT_SIGNATURE,
                        Signatures.executeRefundHash(user, purchaseDetails, mConfiguration));

                Set<Map.Entry<String, JsonElement>> entries = request.entrySet();
                for (Map.Entry<String, JsonElement> entry : entries) {
                    Log.d("REFUND request", entry.getKey() + ", " + entry.getValue());
                }

                mPtpvApi.executeRefund(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ptpvExecuteRefund -> {
                            if (!ptpvExecuteRefund.getErrorId().equals("0")) {
                                callback.returnExecuteRefundError(
                                        new PTPVError(ptpvExecuteRefund.getErrorId()));
                            } else {
                                callback.returnExecuteRefundResponse(ptpvExecuteRefund);
                            }
                        }, error -> callback
                                .returnExecuteRefundError(new PTPVError(error.getMessage())));
            }
        });
    }

    /**
     * Creates a subscription to the account of the business with the given credit card.<br>
     * <br>
     * The registration of a subscription implies the registration of a user in the BankStore system
     * of PAYTPV. This process is completely independent from the isolated charge to a customer of
     * the business.<br>
     * <br>
     * If the execution of the first installment has an error for several reasons (balance, validity
     * of the card, etc...), the subscription will be canceled having to create a new subscription.
     *
     * @param card A PTPVCard instance containing the credit card details (number, expiry date and
     *            CVV).
     * @param subscription A PTPVSubscription instance containing the subscription details.
     * @param product A PTPVProduct instance containing the product scoring. Optional.
     * @param callback The callback to run with the returned PTPVSubscriptionResponse object, or an
     *            error.
     * @see PTPVCard
     * @see PTPVSubscription
     * @see PTPVProduct
     */
    public void createSubscription(PTPVCard card, PTPVSubscription subscription,
            PTPVProduct product, PTPVCallbacks.CreateSubscriptionResponse callback) {
        if (card == null || subscription == null) {
            return;
        }

        this.getIP(new PTPVCallbacks.GetIPResponse() {
            @Override
            public void returnGetIPError(PTPVError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void returnGetIPResponse(PTPVGetIP getIP) {
                JsonObject request = createRequest();

                addCardParams(request, card, getIP.getRemoteAddress());
                if (product != null) {
                    addProductParams(request, product);
                }
                addSubscriptionParams(request, subscription, CREATE_SUBSCRIPTION);

                request.addProperty(MERCHANT_SIGNATURE,
                        Signatures.createSubscriptionHash(card, subscription, mConfiguration));

                mPtpvApi.createSubscription(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ptpvCreateSubscription -> {
                            if (!ptpvCreateSubscription.getErrorId().equals("0")) {
                                callback.returnCreateSubscriptionError(
                                        new PTPVError(ptpvCreateSubscription.getErrorId()));
                            } else {
                                callback.returnCreateSubscriptionResponse(ptpvCreateSubscription);
                            }
                        }, error -> callback
                                .returnCreateSubscriptionError(new PTPVError(error.getMessage())));
            }
        });
    }

    /**
     * Updates an existing subscription.<br>
     * <br>
     * If a user renews their subscription or simply wants to increase the payment of the service we
     * offer the service of editing a subscription. In this case it will not be possible to change
     * the currency nor the bank details of the customer of the business. The modification of the
     * subscription involves the prior registration of a user in subscription mode in the BankStore
     * system of PAYTPV. This process is completely independent from the isolated charge to a
     * customer of the business.
     *
     * @param user A PTPVUser instance containing the user details.
     * @param subscription A PTPVSubscription instance containing the subscription details.
     * @param execute A PTPVSubscriptionExecute instance. If the registration process involves the
     *            payment of the first installment, the value of DS_EXECUTE must be `1`. If you only
     *            want the subscription registration without the payment of the first installment
     *            (it will be executed with the parameters sent), its value must be `0`. If you want
     *            to edit a newly created subscription following the "create_subscription_token"
     *            request, this value must be `0`.
     * @param callback The callback to run with the returned PTPVSubscriptionResponse object, or an
     *            error.
     * @see PTPVUser
     * @see PTPVSubscription
     * @see PTPVExecuteSubscription
     */
    public void editSubscription(PTPVUser user, PTPVSubscription subscription, String execute,
            PTPVCallbacks.EditSubscriptionResponse callback) {
        if (user == null || subscription == null) {
            return;
        }

        subscription.setExecute(execute);

        this.getIP(new PTPVCallbacks.GetIPResponse() {
            @Override
            public void returnGetIPError(PTPVError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void returnGetIPResponse(PTPVGetIP getIP) {
                JsonObject request = createRequest();

                addUserParams(request, user, getIP.getRemoteAddress());
                addSubscriptionParams(request, subscription, EDIT_SUBSCRIPTION);

                request.addProperty(MERCHANT_SIGNATURE,
                        Signatures.editSubscriptionHash(user, subscription, mConfiguration));

                mPtpvApi.editSubscription(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ptpvEditSubscription -> {
                            if (!ptpvEditSubscription.getErrorId().equals("0")) {
                                callback.returnEditSubscriptionError(
                                        new PTPVError(ptpvEditSubscription.getErrorId()));
                            } else {
                                callback.returnEditSubscriptionResponse(ptpvEditSubscription);
                            }
                        }, error -> callback
                                .returnEditSubscriptionError(new PTPVError(error.getMessage())));
            }
        });
    }

    /**
     * Removes a subscription from the account of the business.
     *
     * @param user A PTPVUser instance containing the user details.
     * @param callback The callback to run with the returned PTPVSubscriptionResponse object, or an
     *            error.
     * @see PTPVUser
     */
    public void removeSubscription(PTPVUser user,
            PTPVCallbacks.RemoveSubscriptionResponse callback) {
        if (user == null) {
            return;
        }

        this.getIP(new PTPVCallbacks.GetIPResponse() {
            @Override
            public void returnGetIPError(PTPVError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void returnGetIPResponse(PTPVGetIP getIP) {
                JsonObject request = createRequest();

                addUserParams(request, user, getIP.getRemoteAddress());

                request.addProperty(MERCHANT_SIGNATURE,
                        Signatures.removeSubscriptionHash(user, mConfiguration));

                mPtpvApi.removeSubscription(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ptpvCreateSubscription -> {
                            if (!ptpvCreateSubscription.getErrorId().equals("0")) {
                                callback.returnRemoveSubscriptionError(
                                        new PTPVError(ptpvCreateSubscription.getErrorId()));
                            } else {
                                callback.returnRemoveSubscriptionResponse(ptpvCreateSubscription);
                            }
                        }, error -> callback
                                .returnRemoveSubscriptionError(new PTPVError(error.getMessage())));
            }
        });
    }

    /**
     * Creates a subscription to the account of the business for a user that was already registered
     * in the system, without it being necessary in this case to send card data again.
     *
     * @param user A PTPVUser instance containing the user details.
     * @param subscription A PTPVSubscription instance containing the subscription details.
     * @param product A PTPVProduct instance containing the product scoring. Optional.
     * @param callback The callback to run with the returned PTPVSubscriptionResponse object, or an
     *            error.
     * @see PTPVUser
     * @see PTPVSubscription
     * @see PTPVProduct
     */
    public void createSubscriptionToken(PTPVUser user, PTPVSubscription subscription,
            PTPVProduct product, PTPVCallbacks.CreateSubscriptionResponse callback) {
        if (user == null || subscription == null) {
            return;
        }

        this.getIP(new PTPVCallbacks.GetIPResponse() {
            @Override
            public void returnGetIPError(PTPVError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void returnGetIPResponse(PTPVGetIP getIP) {
                JsonObject request = createRequest();

                addUserParams(request, user, getIP.getRemoteAddress());
                if (product != null) {
                    addProductParams(request, product);
                }
                addSubscriptionParams(request, subscription, CREATE_SUBSCRIPTION);

                request.addProperty(MERCHANT_SIGNATURE, Signatures
                        .createSubscriptionTokenHash(user, subscription, mConfiguration));

                mPtpvApi.createSubscriptionToken(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ptpvCreateSubscription -> {
                            if (!ptpvCreateSubscription.getErrorId().equals("0")) {
                                callback.returnCreateSubscriptionError(
                                        new PTPVError(ptpvCreateSubscription.getErrorId()));
                            } else {
                                callback.returnCreateSubscriptionResponse(ptpvCreateSubscription);
                            }
                        }, error -> callback
                                .returnCreateSubscriptionError(new PTPVError(error.getMessage())));
            }
        });
    }

    /**
     * Performs the requested preauthorization operation.
     *
     * @param user A PTPVUser instance containing the user details.
     * @param merchant A PTPVMerchant instance containing the order details.
     * @param product A PTPVProduct instance containing the product scoring. Optional. May only be
     *            used if the operation is CREATE.
     * @param operation The operation to be executed (create, confirm or cancel preauthorization)
     * @param callback The callback to run with the returned PTPVSubscriptionResponse object, or an
     *            error.
     * @see PTPVUser
     * @see PTPVMerchant
     * @see PTPVProduct
     * @see PTPVPreauthorizationOperations
     */
    public void preauthorizationOperations(PTPVUser user, PTPVMerchant merchant,
            PTPVProduct product, PTPVPreauthorizationOperations operation,
            PTPVCallbacks.PurchaseDetailsResponse callback) {
        if (user == null || merchant == null) {
            return;
        }

        this.getIP(new PTPVCallbacks.GetIPResponse() {
            @Override
            public void returnGetIPError(PTPVError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void returnGetIPResponse(PTPVGetIP getIP) {
                JsonObject request = createRequest();

                addUserParams(request, user, getIP.getRemoteAddress());
                addMerchantParams(request, merchant);

                request.addProperty(MERCHANT_SIGNATURE,
                        Signatures.preauthorizationOperationsHash(user, merchant, operation,
                                mConfiguration));

                Observable<PTPVPurchaseDetails> operationRequest;

                switch (operation) {
                    case CREATE_AUTHORIZATION: {
                        if (product != null) {
                            addProductParams(request, product);
                        }
                        operationRequest = mPtpvApi.createPreauthorization(request);
                        break;
                    }
                    case CONFIRM_AUTHORIZATION: {
                        operationRequest = mPtpvApi.confirmPreauthorization(request);
                        break;
                    }
                    case CANCEL_AUTHORIZATION: {
                        operationRequest = mPtpvApi.cancelPreauthorization(request);
                        break;
                    }
                    case CONFIRM_DEFERRED_PREAUTHORIZATION: {
                        operationRequest = mPtpvApi.confirmDeferredPreauthorization(request);
                        break;
                    }
                    case CANCEL_DEFERRED_PREAUTHORIZATION: {
                        operationRequest = mPtpvApi.cancelDeferredPreauthorization(request);
                        break;
                    }
                    default: {
                        operationRequest = null;
                    }
                }
                if (operationRequest == null) {
                    return;
                }

                operationRequest
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ptpvPurchaseDetails -> {
                            if (!ptpvPurchaseDetails.getErrorId().equals("0")) {
                                callback.returnPurchaseDetailsError(
                                        new PTPVError(ptpvPurchaseDetails.getErrorId()));
                            } else {
                                callback.returnPurchaseDetailsResponse(ptpvPurchaseDetails);
                            }
                        }, error -> callback
                                .returnPurchaseDetailsError(new PTPVError(error.getMessage())));
            }
        });
    }

    /**
     * Registers a user based on a token previously obtained trough the solution BankStore JET. See
     * more information at <a href=
     * "http://developers.paytpv.com/en/documentacion/jet-bankstore">http://developers.paytpv.com/en/documentacion/jet-bankstore</a>
     *
     * @param jetToken Token obtained from javascript
     * @param callback The callback to run with the returned PTPVUser object, or an error.
     */
    public void addUserToken(String jetToken, PTPVCallbacks.AddUserResponse callback) {
        this.getIP(new PTPVCallbacks.GetIPResponse() {
            @Override
            public void returnGetIPError(PTPVError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void returnGetIPResponse(PTPVGetIP getIP) {
                JsonObject request = createRequest();

                addJetParams(request, mConfiguration, getIP.getRemoteAddress(),
                        jetToken);
                request.addProperty(MERCHANT_SIGNATURE,
                        Signatures.addUserTokenHash(jetToken, mConfiguration));

                mPtpvApi.addUserToken(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ptpvAddUserToken -> {
                            if (!ptpvAddUserToken.getErrorId().equals("0")) {
                                callback.returnAddUserError(
                                        new PTPVError(ptpvAddUserToken.getErrorId()));
                            } else {
                                callback.returnAddUserResponse(ptpvAddUserToken);
                            }
                        }, error -> callback.returnAddUserError(new PTPVError(error.getMessage())));
            }
        });
    }

    private String getCachedIP() {
        return PTPVApiClient.mPreferences.getString(IP_KEY, "");
    }

    private String getCachedDate() {
        return PTPVApiClient.mPreferences.getString(DATE_KEY, "0");
    }

    private JsonObject createRequest() {
        // -----------------------------------------------------------------------------------------
        // Create a new request JsonObject
        // -----------------------------------------------------------------------------------------
        JsonObject request = new JsonObject();

        // -----------------------------------------------------------------------------------------
        // Add configuration parameters to the new request
        // -----------------------------------------------------------------------------------------
        addConfigurationParams(request, mConfiguration);

        return request;
    }
}
