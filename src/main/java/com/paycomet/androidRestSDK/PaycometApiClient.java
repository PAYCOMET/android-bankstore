package com.paycomet.androidRestSDK;

import static com.paycomet.androidRestSDK.Utils.AddRequestParam.addConfigurationParams;
import static com.paycomet.androidRestSDK.Utils.AddRequestParam.addConfigurationPaymentParams;
import static com.paycomet.androidRestSDK.Utils.AddRequestParam.addFormDccParams;
import static com.paycomet.androidRestSDK.Utils.AddRequestParam.addFormParams;
import static com.paycomet.androidRestSDK.Utils.AddRequestParam.addFormPreauthorizationParams;
import static com.paycomet.androidRestSDK.Utils.AddRequestParam.addFormSubscriptionParams;
import static com.paycomet.androidRestSDK.Utils.AddRequestParam.addIpParams;
import static com.paycomet.androidRestSDK.Utils.Constants.API_URL;
import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.SHARED_PREFERENCES_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks;
import com.paycomet.androidRestSDK.Model.Basic.PaycometConfiguration;
import com.paycomet.androidRestSDK.Model.Requests.PaycometCreatePreauth;
import com.paycomet.androidRestSDK.Model.Requests.PaycometError;
import com.paycomet.androidRestSDK.Model.Requests.PaycometExecutePurchaseDcc;
import com.paycomet.androidRestSDK.Model.Requests.PaycometIP;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetBalanceResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetConfirmPurchaseDccResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetCreatePreauthResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetCreatePreauthRtokenResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetErrorResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetExchangeResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetExecutePurchaseDccResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetExecutePurchaseResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetExecutePurchaseRtokenResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetExecuteRefundResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetFormResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetHeartbeatResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetIPResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetInfoUserResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetMarketplaceResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetMethodsResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetPreauthCancelResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetPreauthConfirmResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetRemoveResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetSubscriptionResponse;
import com.paycomet.androidRestSDK.Interfaces.PaycometCallbacks.OnPaycometGetUserResponse;
import com.paycomet.androidRestSDK.Model.Requests.PaycometPreauthCancel;
import com.paycomet.androidRestSDK.Model.Requests.PaycometPreauthConfirm;
import com.paycomet.androidRestSDK.Model.Requests.PaycometRemoveResponse;
import com.paycomet.androidRestSDK.Utils.AddRequestParam;
import com.paycomet.androidRestSDK.old.Interfaces.PTPVCallbacks;
import com.paycomet.androidRestSDK.old.Model.Basic.PTPVCard;
import com.paycomet.androidRestSDK.old.Model.Basic.PTPVError;
import com.paycomet.androidRestSDK.old.Model.Basic.PTPVMerchant;
import com.paycomet.androidRestSDK.old.Model.Basic.PTPVProduct;
import com.paycomet.androidRestSDK.old.Model.Basic.PTPVSubscription;
import com.paycomet.androidRestSDK.old.Model.Basic.PTPVUser;
import com.paycomet.androidRestSDK.old.Model.PTPVRequests.PTPVGetIP;
import com.paycomet.androidRestSDK.old.Model.PTPVRequests.PTPVRemoveResponse;
import com.paycomet.androidRestSDK.old.Model.PTPVRequests.Preauthorization.PTPVPreauthorizationOperations;
import com.paycomet.androidRestSDK.old.Model.PTPVRequests.Purchase.PTPVConfirmPurchaseDcc;
import com.paycomet.androidRestSDK.old.Model.PTPVRequests.Purchase.PTPVExecutePurchaseDcc;
import com.paycomet.androidRestSDK.old.Model.PTPVRequests.Purchase.PTPVExecuteRefund;
import com.paycomet.androidRestSDK.old.Model.PTPVRequests.Purchase.PTPVPurchaseDetails;
import com.paycomet.androidRestSDK.old.Model.PTPVRequests.Subscription.PTPVSubscriptionResponse;
import com.paycomet.androidRestSDK.old.Model.PTPVRequests.User.PTPVAddUser;
import com.paycomet.androidRestSDK.old.Model.PTPVRequests.User.PTPVInfoUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaycometApiClient {

    /**
     * A shared singleton API client
     */
    private static PaycometApiClient mInstance = null;

    /**
     * The client's configuration
     */
    private PaycometConfiguration mConfiguration;

    /**
     * A client for making requests to the PAYCOMET API
     */
    private PaycometApi mPaycometApi;

    /**
     * Context for caching the IP in the SharedPreferences
     */
    private static Context mContext;

    /**
     * Get SharedPreferences for storing and reading the IP
     */
    private static SharedPreferences mPreferences;

    private PaycometApiClient() {
        // -----------------------------------------------------------------------------------------
        // Create a new Retrofit instance and build the API client based on it
        // -----------------------------------------------------------------------------------------
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.mPaycometApi = mRetrofit.create(PaycometApi.class);
    }

    public static synchronized PaycometApiClient getInstance(Context context) {
        PaycometApiClient.mContext = context;
        PaycometApiClient.mPreferences = PaycometApiClient.mContext
                .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        if (mInstance == null) {
            mInstance = new PaycometApiClient();

            return mInstance;
        }
        return mInstance;
    }

    /**
     * Sets up the initial configuration for the client.
     *
     * @param mConfiguration PTPVConfiguration
     */
    public void setConfiguration(PaycometConfiguration mConfiguration) {
        this.mConfiguration = mConfiguration;
    }

    public void getRemoteIp(
            OnPaycometGetIPResponse callback
    ) {

        JsonObject request = createRequest();

        this.mPaycometApi.postIpRemote(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetIP -> {

                            if (!paycometGetIP.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometGetIP.getErrorId()));
                            } else {
                                callback.onResponse(paycometGetIP);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    public void getIp(

            String ip,
            OnPaycometGetIPResponse callback
    ) {

        JsonObject request = createRequest();
        addIpParams(request, ip);

        this.mPaycometApi.postIp(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetIP -> {

                            if (!paycometGetIP.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometGetIP.getErrorId()));
                            } else {
                                callback.onResponse(paycometGetIP);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });


    }

    public void getForm(
                         String language,
                         String amount,
                         String currency,
                         String secure,
                         String userInteraction,
                         String urlOk,
                         String urlKo,
                         OnPaycometGetFormResponse callback
    ) {
        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                addFormParams(
                        request,
                        language,
                        amount,
                        currency,
                        ip.getRemoteAddress(),
                        secure,
                        userInteraction,
                        urlOk,
                        urlKo
                );

                mPaycometApi.postForm(mConfiguration.getAPIKey(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetForm -> {

                                    if (!paycometGetForm.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetForm.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetForm);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getFormPreauthorization(
            String language,
            String amount,
            String currency,
            String secure,
            String urlOk,
            String urlKo,
            String idUser,
            String tokenUser,
            OnPaycometGetFormResponse callback
    ) {
        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                addFormPreauthorizationParams(
                        request,
                        language,
                        amount,
                        currency,
                        ip.getRemoteAddress(),
                        secure,
                        urlOk,
                        urlKo,
                        idUser,
                        tokenUser
                );

                mPaycometApi.postForm(mConfiguration.getAPIKey(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetForm -> {

                                    if (!paycometGetForm.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetForm.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetForm);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getFormSubscription(
            String language,
            String amount,
            String currency,
            String urlOk,
            String urlKo,
            String startDate,
            String endDate,
            String periodicity,
            OnPaycometGetFormResponse callback
    ) {
        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                addFormSubscriptionParams(
                        request,
                        language,
                        amount,
                        currency,
                        ip.getRemoteAddress(),
                        urlOk,
                        urlKo,
                        startDate,
                        endDate,
                        periodicity
                );

                mPaycometApi.postForm(mConfiguration.getAPIKey(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetForm -> {

                                    if (!paycometGetForm.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetForm.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetForm);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getFormDcc(
            String language,
            String amount,
            String currency,
            String secure,
            String urlOk,
            String urlKo,
            String idUser,
            String tokenUser,
            OnPaycometGetFormResponse callback
    ) {
        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                addFormDccParams(
                        request,
                        language,
                        amount,
                        currency,
                        ip.getRemoteAddress(),
                        secure,
                        urlOk,
                        urlKo,
                        idUser,
                        tokenUser
                );

                mPaycometApi.postForm(mConfiguration.getAPIKey(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetForm -> {

                                    if (!paycometGetForm.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetForm.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetForm);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addUser(

            String pan,
            String expiryMonth,
            String expiryYear,
            String cvc2,
            String productDescription,
            String language,
            OnPaycometGetUserResponse callback
    ) {

        JsonObject request = createRequest();
        AddRequestParam.addCardParams(
                request,
                pan,
                expiryMonth,
                expiryYear,
                cvc2,
                productDescription,
                language
        );

        this.mPaycometApi.postCards(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetUser -> {

                            if (!paycometGetUser.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometGetUser.getErrorId()));
                            } else {
                                callback.onResponse(paycometGetUser);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    public void addUserToken(

            String jetToken,
            OnPaycometGetUserResponse callback
    ) {

        JsonObject request = createRequest();
        AddRequestParam.addCardTokenParams(
                request,
                jetToken
        );

        this.mPaycometApi.postCards(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetUser -> {

                            if (!paycometGetUser.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometGetUser.getErrorId()));
                            } else {
                                callback.onResponse(paycometGetUser);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    public void editUser(

            String idUser,
            String tokenUser,
            String cvc2,
            String expiryMonth,
            String expiryYear,
            OnPaycometGetUserResponse callback
    ) {

        JsonObject request = createRequest();
        AddRequestParam.addCardEditParams(
                request,
                idUser,
                tokenUser,
                cvc2,
                expiryMonth,
                expiryYear
        );

        this.mPaycometApi.postCardsEdit(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetEditCard -> {

                            if (!paycometGetEditCard.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometGetEditCard.getErrorId()));
                            } else {
                                callback.onResponse(paycometGetEditCard);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    public void infoUser(

            String idUser,
            String tokenUser,
            OnPaycometGetInfoUserResponse callback
    ) {

        JsonObject request = createRequest();
        AddRequestParam.addCardInfoParams(
                request,
                idUser,
                tokenUser
        );

        this.mPaycometApi.postCardsInfo(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetInfoUser -> {

                            if (!paycometGetInfoUser.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometGetInfoUser.getErrorId()));
                            } else {
                                callback.onResponse(paycometGetInfoUser);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    public void deleteUser(

            String idUser,
            String tokenUser,
            OnPaycometGetRemoveResponse callback
    ) {

        JsonObject request = createRequest();
        AddRequestParam.addCardDeleteParams(
                request,
                idUser,
                tokenUser
        );

        this.mPaycometApi.postCardsDelete(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetDeleteUser -> {

                            if (!paycometGetDeleteUser.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometGetDeleteUser.getErrorId()));
                            } else {
                                callback.onResponse(paycometGetDeleteUser);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    public void executePurchase(

            String amount,
            String currency,
            String methodId,
            String idUser,
            String tokenUser,
            String secure,
            String userInteraction,
            String productDescription,
            OnPaycometGetExecutePurchaseResponse callback
    ) {


        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addPaymentsParams(
                        request,
                        amount,
                        currency,
                        ip.getRemoteAddress(),
                        methodId,
                        idUser,
                        tokenUser,
                        secure,
                        userInteraction,
                        productDescription
                );

                mPaycometApi.postExecutePurchase(mConfiguration.getAPIKey(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetExecutePurchase -> {

                                    if (!paycometGetExecutePurchase.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetExecutePurchase.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetExecutePurchase);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Rtoken
    public void executePurchase(

            String amount,
            String currency,
            String methodId,
            String idUser,
            String tokenUser,
            String secure,
            String userInteraction,
            String productDescription,
            String merchantIdentifier,
            OnPaycometGetExecutePurchaseRtokenResponse callback
    ) {

        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addPaymentsRtokenParams(
                        request,
                        amount,
                        currency,
                        ip.getRemoteAddress(),
                        methodId,
                        idUser,
                        tokenUser,
                        secure,
                        userInteraction,
                        productDescription,
                        merchantIdentifier
                );

                mPaycometApi.postExecutePurchaseRtoken(mConfiguration.getAPIKey(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetExecutePurchaseRtoken -> {

                                    if (!paycometGetExecutePurchaseRtoken.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetExecutePurchaseRtoken.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetExecutePurchaseRtoken);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void ExecutePurchaseDcc(

            String amount,
            String currency,
            String methodId,
            String idUser,
            String tokenUser,
            String secure,
            String userInteraction,
            String productDescription,
            OnPaycometGetExecutePurchaseDccResponse callback
    ) {


        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addPaymentsParams(
                        request,
                        amount,
                        currency,
                        ip.getRemoteAddress(),
                        methodId,
                        idUser,
                        tokenUser,
                        secure,
                        userInteraction,
                        productDescription
                );

                mPaycometApi.postDCCPurchaseCreate(mConfiguration.getAPIKey(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetExecutePurchaseDcc -> {

                                    if (!paycometGetExecutePurchaseDcc.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetExecutePurchaseDcc.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetExecutePurchaseDcc);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void confirmPurchaseDcc(
            String order,
            String amount,
            String currency,
            String methodId,
            String idUser,
            String tokenUser,
            String secure,
            String userInteraction,
            String productDescription,
            OnPaycometGetConfirmPurchaseDccResponse callback
    ) {

        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addPaymentsParams(
                        request,
                        amount,
                        currency,
                        ip.getRemoteAddress(),
                        methodId,
                        idUser,
                        tokenUser,
                        secure,
                        userInteraction,
                        productDescription
                );

                mPaycometApi.postDCCPurchaseConfirm(mConfiguration.getAPIKey(), order, request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetConfirmPurchaseDcc -> {

                                    if (!paycometGetConfirmPurchaseDcc.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetConfirmPurchaseDcc.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetConfirmPurchaseDcc);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void executeRefund(
            String order,
            String amount,
            String currency,
            String authCode,
            OnPaycometGetExecuteRefundResponse callback
    ) {

        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addRefundParams(
                        request,
                        amount,
                        currency,
                        authCode,
                        ip.getRemoteAddress()
                );

                mPaycometApi.postExecuteRefund(mConfiguration.getAPIKey(), order, request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetExecuteRefund -> {

                                    if (!paycometGetExecuteRefund.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetExecuteRefund.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetExecuteRefund);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getBalance(
            OnPaycometGetBalanceResponse callback
    ) {

        JsonObject request = createRequest();

        this.mPaycometApi.postBalance(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetBalance -> {

                            if (!paycometGetBalance.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometGetBalance.getErrorId()));
                            } else {
                                callback.onResponse(paycometGetBalance);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    public void getErrorInfo(

            String errorCode,
            String lang,
            OnPaycometGetErrorResponse callback
    ) {

        JsonObject request = createRequest();
        AddRequestParam.addInfoErrorParams(
                request,
                errorCode,
                lang
        );

        this.mPaycometApi.postInfoErrors(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetInfoError -> {

                            if (!paycometGetInfoError.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometGetInfoError.getErrorId()));
                            } else {
                                callback.onResponse(paycometGetInfoError);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    public void exchange(
            String amount,
            String originalCurrency,
            String finalCurrency,
            OnPaycometGetExchangeResponse callback
    ) {

        JsonObject request = createRequest();
        AddRequestParam.addExchangeParams(
                request,
                amount,
                originalCurrency,
                finalCurrency
        );

        this.mPaycometApi.postExchange(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetExchange -> {

                            if (!paycometGetExchange.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometGetExchange.getErrorId()));
                            } else {
                                callback.onResponse(paycometGetExchange);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    public void getHeartbeat(
            OnPaycometGetHeartbeatResponse callback
    ) {

        JsonObject request = createRequest();

        this.mPaycometApi.postHeartbeat(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetHeartbeat -> {

                            if (!paycometGetHeartbeat.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometGetHeartbeat.getErrorId()));
                            } else {
                                callback.onResponse(paycometGetHeartbeat);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    public void cancelPreauthorization(
            String order,
            String amount,
            String authcode,
            String deferred,
            String notifyDirectPayment,
            OnPaycometGetPreauthCancelResponse callback
    ) {

        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addPreauthCancelParams(
                        request,
                        amount,
                        ip.getRemoteAddress(),
                        authcode,
                        deferred,
                        notifyDirectPayment
                );

                mPaycometApi.postPreauthCancel(mConfiguration.getAPIKey(), order, request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetCancelPreauth -> {

                                    if (!paycometGetCancelPreauth.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetCancelPreauth.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetCancelPreauth);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void confirmPreauthorization(
            String order,
            String amount,
            String authcode,
            String deferred,
            String notifyDirectPayment,
            OnPaycometGetPreauthConfirmResponse callback
    ) {


        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addPreauthConfirmParams(
                        request,
                        amount,
                        ip.getRemoteAddress(),
                        authcode,
                        deferred,
                        notifyDirectPayment
                );

                mPaycometApi.postPreauthConfirm(mConfiguration.getAPIKey(), order, request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetConfirmPreauth -> {

                                    if (!paycometGetConfirmPreauth.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetConfirmPreauth.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetConfirmPreauth);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void createPreauthorization(

            String amount,
            String currency,
            String methodId,
            String secure,
            String idUser,
            String tokenUser,
            String userInteraction,
            OnPaycometGetCreatePreauthResponse callback
    ) {

        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addCreatePreauthParams(
                        request,
                        amount,
                        currency,
                        ip.getRemoteAddress(),
                        methodId,
                        secure,
                        idUser,
                        tokenUser,
                        userInteraction
                );

                mPaycometApi.postCreatePreauth(mConfiguration.getAPIKey(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetCreatePreauth -> {

                                    if (!paycometGetCreatePreauth.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetCreatePreauth.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetCreatePreauth);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void createPreauthorizationRtoken(

            String amount,
            String currency,
            String methodId,
            String secure,
            String merchantIdentifier,
            String userInteraction,
            OnPaycometGetCreatePreauthRtokenResponse callback
    ) {

        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addCreatePreauthRtokenParams(
                        request,
                        amount,
                        currency,
                        ip.getRemoteAddress(),
                        methodId,
                        secure,
                        merchantIdentifier,
                        userInteraction
                );

                mPaycometApi.postCreatePreauthRtoken(mConfiguration.getAPIKey(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetCreatePreauthRtoken -> {

                                    if (!paycometGetCreatePreauthRtoken.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetCreatePreauthRtoken.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetCreatePreauthRtoken);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void methods(

            OnPaycometGetMethodsResponse callback
    ) {

        JsonObject request = createRequest();

        this.mPaycometApi.postMethods(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetMethods -> {

                            if (!paycometGetMethods.get(0).getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometGetMethods.get(0).getErrorId()));
                            } else {
                                callback.onResponse(paycometGetMethods);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    public void splitTransfer(

            String authCode,
            String splitId,
            String amount,
            String currency,
            OnPaycometGetMarketplaceResponse callback
    ) {

        JsonObject request = createPaymentRequest();
        AddRequestParam.addSplitTransferParams(
                request,
                authCode,
                splitId,
                amount,
                currency
        );

        this.mPaycometApi.postMarketplaceSplitTransfer(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometSplitTransfer -> {

                            if (!paycometSplitTransfer.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometSplitTransfer.getErrorId()));
                            } else {
                                callback.onResponse(paycometSplitTransfer);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    public void splitTransferReversal(

            String authCode,
            String splitId,
            String amount,
            String currency,
            String splitAuthCode,
            OnPaycometGetMarketplaceResponse callback
    ) {

        JsonObject request = createPaymentRequest();
        AddRequestParam.addSplitTransferReversalParams(
                request,
                authCode,
                splitId,
                amount,
                currency,
                splitAuthCode
        );

        this.mPaycometApi.postMarketplaceSplitTransferReversal(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometSplitTransferReversal -> {

                            if (!paycometSplitTransferReversal.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometSplitTransferReversal.getErrorId()));
                            } else {
                                callback.onResponse(paycometSplitTransferReversal);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    public void createSubscription(

            String methodId,
            String amount,
            String currency,
            String idUser,
            String tokenUser,
            String secure,
            String scoring,
            String productDescription,
            String merchantDescriptor,
            String userInteraction,
            String urlOk,
            String urlKo,
            String notifyDirectPayment,
            String startDate,
            String endDate,
            String periodicity,
            OnPaycometGetSubscriptionResponse callback
    ) {

        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addCreateSubscriptionParams(
                        request,
                        methodId,
                        amount,
                        currency,
                        ip.getRemoteAddress(),
                        idUser,
                        tokenUser,
                        secure,
                        scoring,
                        productDescription,
                        merchantDescriptor,
                        userInteraction,
                        "R",
                        "MIT",
                        urlOk,
                        urlKo,
                        notifyDirectPayment,
                        startDate,
                        endDate,
                        periodicity
                );

                mPaycometApi.postCreateSubscription(mConfiguration.getAPIKey(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetCreateSubscription -> {

                                    if (!paycometGetCreateSubscription.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetCreateSubscription.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetCreateSubscription);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void editSubscription(
            String order,
            String methodId,
            String amount,
            String idUser,
            String tokenUser,
            String startDate,
            String endDate,
            String periodicity,
            String active,
            OnPaycometGetSubscriptionResponse callback
    ) {

        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addEditSubscriptionParams(
                        request,
                        methodId,
                        amount,
                        ip.getRemoteAddress(),
                        idUser,
                        tokenUser,
                        startDate,
                        endDate,
                        periodicity,
                        active
                );

                mPaycometApi.postEditSubscription(mConfiguration.getAPIKey(), order, request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetEditSubscription -> {

                                    if (!paycometGetEditSubscription.getErrorId().equals("0")) {
                                        callback.onError(new PaycometError(paycometGetEditSubscription.getErrorId()));
                                    } else {
                                        callback.onResponse(paycometGetEditSubscription);
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void removeSubscription(
            String order,
            String idUser,
            String tokenUser,
            OnPaycometGetRemoveResponse callback
    ) {

        JsonObject request = createPaymentRequest();
        AddRequestParam.addRemoveSubscriptionParams(
                request,
                order,
                idUser,
                tokenUser
        );

        this.mPaycometApi.postRemoveSubscription(mConfiguration.getAPIKey(), order, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometRemoveSubscription -> {

                            if (!paycometRemoveSubscription.getErrorId().equals("0")) {
                                callback.onError(new PaycometError(paycometRemoveSubscription.getErrorId()));
                            } else {
                                callback.onResponse(paycometRemoveSubscription);
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.onError(new PaycometError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    private JsonObject createRequest() {
        // -----------------------------------------------------------------------------------------
        // Create a new request JsonObject
        // -----------------------------------------------------------------------------------------
        JsonObject request = new JsonObject();

        // -----------------------------------------------------------------------------------------
        // Add terminal to the new request
        // -----------------------------------------------------------------------------------------
        addConfigurationParams(request, mConfiguration.getTerminal());

        return request;
    }

    private JsonObject createPaymentRequest() {
        // -----------------------------------------------------------------------------------------
        // Create a new request JsonObject
        // -----------------------------------------------------------------------------------------
        JsonObject request = new JsonObject();

        // -----------------------------------------------------------------------------------------
        // Add terminal to the new request
        // -----------------------------------------------------------------------------------------
        addConfigurationPaymentParams(request, mConfiguration.getTerminal());

        return request;
    }

    // OLD

    @Deprecated
    public void addUser(

            PTPVCard card,
            PTPVCallbacks.AddUserResponse callback
    ) {

        JsonObject request = createRequest();
        AddRequestParam.addCardParams(
                request,
                card.getNumber(),
                card.getExpiryDate().substring(0,2),
                card.getExpiryDate().substring(2,4),
                card.getCvv(),
                "Andorid REST SDK OLD",
                "es"
        );

        this.mPaycometApi.postCards(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetUser -> {

                            if (!paycometGetUser.getErrorId().equals("0")) {
                                callback.returnAddUserError(
                                        new PTPVError(paycometGetUser.getErrorId()));
                            } else {
                                callback.returnAddUserResponse(new PTPVAddUser(paycometGetUser));
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.returnAddUserError(new PTPVError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    @Deprecated
    public void executePurchase(

            PTPVAddUser user,
            PTPVMerchant merchant,
            PTPVProduct product,
            PTPVCallbacks.PurchaseDetailsResponse callback
    ) {


        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addPaymentsParams(
                        request,
                        merchant.getAmount(),
                        merchant.getCurrency(),
                        ip.getRemoteAddress(),
                        "1",
                        user.getUserId(),
                        user.getUserToken(),
                        "0",
                        "0",
                        product.getDescription()
                );

                mPaycometApi.postExecutePurchase(mConfiguration.getAPIKey(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetExecutePurchase -> {

                                    if (!paycometGetExecutePurchase.getErrorId().equals("0")) {
                                        callback.returnPurchaseDetailsError(new PTPVError(paycometGetExecutePurchase.getErrorId()));
                                    } else {
                                        callback.returnPurchaseDetailsResponse(new PTPVPurchaseDetails(paycometGetExecutePurchase));
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.returnPurchaseDetailsError(new PTPVError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Deprecated
    public void getRemoteIp(
            PTPVCallbacks.GetIPResponse callback
    ) {

        JsonObject request = createRequest();

        this.mPaycometApi.postIpRemote(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetIP -> {

                            if (!paycometGetIP.getErrorId().equals("0")) {
                                callback.returnGetIPError(new PTPVError(paycometGetIP.getErrorId()));
                            } else {
                                callback.returnGetIPResponse(new PTPVGetIP(paycometGetIP));
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.returnGetIPError(new PTPVError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    @Deprecated
    public void infoUser(

            PTPVUser user,
            PTPVCallbacks.InfoUserResponse callback
    ) {

        JsonObject request = createRequest();
        AddRequestParam.addCardInfoParams(
                request,
                user.getUserId(),
                user.getUserToken()
        );

        this.mPaycometApi.postCardsInfo(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetInfoUser -> {

                            if (!paycometGetInfoUser.getErrorId().equals("0")) {
                                callback.returnInfoUserError(new PTPVError(paycometGetInfoUser.getErrorId()));
                            } else {
                                callback.returnInfoUserResponse(new PTPVInfoUser(paycometGetInfoUser));
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.returnInfoUserError(new PTPVError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    @Deprecated
    public void removeUser(

            PTPVUser user,
            PTPVCallbacks.RemoveUserResponse callback
    ) {

        JsonObject request = createRequest();
        AddRequestParam.addCardDeleteParams(
                request,
                user.getUserId(),
                user.getUserToken()
        );

        this.mPaycometApi.postCardsDelete(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetDeleteUser -> {

                            if (!paycometGetDeleteUser.getErrorId().equals("0")) {
                                callback.returnRemoveUserError(new PTPVError(paycometGetDeleteUser.getErrorId()));
                            } else {
                                callback.returnRemoveUserResponse(new PTPVRemoveResponse(paycometGetDeleteUser));
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.returnRemoveUserError(new PTPVError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }

    @Deprecated
    public void ExecutePurchaseDcc(

            PTPVUser user,
            PTPVMerchant merchant,
            PTPVProduct product,
            PTPVCallbacks.ExecutePurchaseDccResponse callback
    ) {


        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addPaymentsParams(
                        request,
                        merchant.getAmount(),
                        merchant.getCurrency(),
                        ip.getRemoteAddress(),
                        "1",
                        user.getUserId(),
                        user.getUserToken(),
                        "0",
                        "0",
                        product.getDescription()
                );

                mPaycometApi.postDCCPurchaseCreate(mConfiguration.getAPIKey(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetExecutePurchaseDcc -> {

                                    if (!paycometGetExecutePurchaseDcc.getErrorId().equals("0")) {
                                        callback.returnExecutePurchaseDccError(new PTPVError(paycometGetExecutePurchaseDcc.getErrorId()));
                                    } else {
                                        callback.returnExecutePurchaseDccResponse(new PTPVExecutePurchaseDcc(paycometGetExecutePurchaseDcc));
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.returnExecutePurchaseDccError(new PTPVError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Deprecated
    public void confirmPurchaseDcc(
            PTPVUser user,
            PTPVMerchant merchant,
            PTPVProduct product,
            PTPVCallbacks.ConfirmPurchaseDccResponse callback
    ) {

        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addPaymentsParams(
                        request,
                        merchant.getAmount(),
                        merchant.getCurrency(),
                        ip.getRemoteAddress(),
                        "1",
                        user.getUserId(),
                        user.getUserToken(),
                        "0",
                        "0",
                        product.getDescription()
                );

                mPaycometApi.postDCCPurchaseConfirm(mConfiguration.getAPIKey(), merchant.getOrder(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetConfirmPurchaseDcc -> {

                                    if (!paycometGetConfirmPurchaseDcc.getErrorId().equals("0")) {
                                        callback.returnConfirmPurchaseDccError(new PTPVError(paycometGetConfirmPurchaseDcc.getErrorId()));
                                    } else {
                                        callback.returnConfirmPurchaseDccResponse(new PTPVConfirmPurchaseDcc(paycometGetConfirmPurchaseDcc));
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.returnConfirmPurchaseDccError(new PTPVError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Deprecated
    public void executeRefund(
            PTPVUser user,
            PTPVPurchaseDetails purchase,
            PTPVCallbacks.ExecuteRefundResponse callback
    ) {

        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addRefundParams(
                        request,
                        purchase.getAmount(),
                        purchase.getCurrency(),
                        purchase.getAuthCode(),
                        ip.getRemoteAddress()
                );

                mPaycometApi.postExecuteRefund(mConfiguration.getAPIKey(), purchase.getOrder(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetExecuteRefund -> {

                                    if (!paycometGetExecuteRefund.getErrorId().equals("0")) {
                                        callback.returnExecuteRefundError(new PTPVError(paycometGetExecuteRefund.getErrorId()));
                                    } else {
                                        callback.returnExecuteRefundResponse(new PTPVExecuteRefund(paycometGetExecuteRefund));
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.returnExecuteRefundError(new PTPVError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Deprecated
    public void createSubscription(
            PTPVCard card,
            PTPVSubscription subscription,
            PTPVProduct product,
            PTPVCallbacks.CreateSubscriptionResponse callback
    ) {

        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {

                addUser(
                        card,
                        new PTPVCallbacks.AddUserResponse() {

                            @Override
                            public void returnAddUserError(PTPVError error) {
                                try {
                                    Log.d("IP", error.getError());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void returnAddUserResponse(PTPVAddUser addUserResponse) {
                                JsonObject request = createPaymentRequest();
                                AddRequestParam.addCreateSubscriptionParams(
                                        request,
                                        "1",
                                        subscription.getSubscriptionAmount(),
                                        subscription.getSubscriptionCurrency(),
                                        ip.getRemoteAddress(),
                                        addUserResponse.getUserId(),
                                        addUserResponse.getUserToken(),
                                        "0",
                                        product.getScoring(),
                                        product.getDescription(),
                                        "Android SDK REST OLD",
                                        "0",
                                        "R",
                                        "MIT",
                                        "",
                                        "",
                                        "",
                                        subscription.getSubscriptionStartDate(),
                                        subscription.getSubscriptionEndDate(),
                                        subscription.getSubscriptionPeriodicity()
                                );

                                mPaycometApi.postCreateSubscription(mConfiguration.getAPIKey(), request)
                                        .subscribeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(paycometGetCreateSubscription -> {

                                                    if (!paycometGetCreateSubscription.getErrorId().equals("0")) {
                                                        callback.returnCreateSubscriptionError(new PTPVError(paycometGetCreateSubscription.getErrorId()));
                                                    } else {
                                                        callback.returnCreateSubscriptionResponse(new PTPVSubscriptionResponse(paycometGetCreateSubscription));
                                                    }
                                                },
                                                throwable -> {
                                                    if (throwable instanceof HttpException) {
                                                        HttpException error = (HttpException) throwable;
                                                        String errorBody = error.response().errorBody().string();
                                                        callback.returnCreateSubscriptionError(new PTPVError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                                    }
                                                });
                            }
                        }
                );
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Deprecated
    public void editSubscription(
            PTPVUser user,
            PTPVSubscription subscription,
            String execute,
            PTPVCallbacks.EditSubscriptionResponse callback
    ) {

        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                JsonObject request = createPaymentRequest();
                AddRequestParam.addEditSubscriptionParams(
                        request,
                        "1",
                        subscription.getSubscriptionAmount(),
                        ip.getRemoteAddress(),
                        user.getUserId(),
                        user.getUserToken(),
                        subscription.getSubscriptionStartDate(),
                        subscription.getSubscriptionEndDate(),
                        subscription.getSubscriptionPeriodicity(),
                        execute
                );

                mPaycometApi.postEditSubscription(mConfiguration.getAPIKey(), subscription.getSubscriptionOrder(), request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(paycometGetEditSubscription -> {

                                    if (!paycometGetEditSubscription.getErrorId().equals("0")) {
                                        callback.returnEditSubscriptionError(new PTPVError(paycometGetEditSubscription.getErrorId()));
                                    } else {
                                        callback.returnEditSubscriptionResponse(new PTPVSubscriptionResponse(paycometGetEditSubscription));
                                    }
                                },
                                throwable -> {
                                    if (throwable instanceof HttpException) {
                                        HttpException error = (HttpException) throwable;
                                        String errorBody = error.response().errorBody().string();
                                        callback.returnEditSubscriptionError(new PTPVError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                                    }
                                });
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Deprecated
    public void removeSubscription(
            PTPVUser user,
            PTPVSubscriptionResponse subscription,
            PTPVCallbacks.RemoveSubscriptionResponse callback
    ) {
        this.removeSubscription(
                subscription.getSubscriptionOrder(),
                user.getUserId(),
                subscription.getUserToken(),
                new OnPaycometGetRemoveResponse() {
                    @Override
                    public void onResponse(PaycometRemoveResponse removeResponse) {
                        callback.returnRemoveSubscriptionResponse(new PTPVRemoveResponse(removeResponse));
                    }

                    @Override
                    public void onError(PaycometError error) {
                        callback.returnRemoveSubscriptionError(new PTPVError(error.getErrorId()));
                    }
                }

        );
    }

    @Deprecated
    public void preauthorizationOperations(

            PTPVUser user,
            PTPVMerchant merchant,
            PTPVProduct product,
            PTPVPreauthorizationOperations operation,
            PTPVCallbacks.PurchaseDetailsResponse callback
    ) {

        this.getRemoteIp(new OnPaycometGetIPResponse() {
            @Override
            public void onResponse(PaycometIP ip) {
                switch (operation) {
                    case CREATE_AUTHORIZATION: {
                        createPreauthorization(
                                merchant.getAmount(),
                                merchant.getCurrency(),
                                "1",
                                "0",
                                user.getUserId(),
                                user.getUserToken(),
                                "0",
                                new OnPaycometGetCreatePreauthResponse() {

                                    @Override
                                    public void onResponse(PaycometCreatePreauth createPreauth) {
                                        callback.returnPurchaseDetailsResponse(new PTPVPurchaseDetails(createPreauth));
                                    }

                                    @Override
                                    public void onError(PaycometError error) {
                                        try {
                                            Log.d("IP", error.getError());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                        );
                        break;
                    }
                    case CONFIRM_AUTHORIZATION: {
                        confirmPreauthorization(
                                merchant.getOrder(),
                                merchant.getAmount(),
                                merchant.getAuthCode(),
                                "0",
                                "",
                                new OnPaycometGetPreauthConfirmResponse() {
                                    @Override
                                    public void onResponse(PaycometPreauthConfirm preauthConfirm) {
                                        callback.returnPurchaseDetailsResponse(new PTPVPurchaseDetails(preauthConfirm));
                                    }

                                    @Override
                                    public void onError(PaycometError error) {
                                        try {
                                            Log.d("IP", error.getError());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                        );
                        break;
                    }
                    case CANCEL_AUTHORIZATION: {
                        cancelPreauthorization(
                                merchant.getOrder(),
                                merchant.getAmount(),
                                merchant.getAuthCode(),
                                "0",
                                "",
                                new OnPaycometGetPreauthCancelResponse() {
                                    @Override
                                    public void onResponse(PaycometPreauthCancel preauthCancel) {
                                        callback.returnPurchaseDetailsResponse(new PTPVPurchaseDetails(preauthCancel));
                                    }

                                    @Override
                                    public void onError(PaycometError error) {
                                        try {
                                            Log.d("IP", error.getError());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                        );
                        break;
                    }
                    case CONFIRM_DEFERRED_PREAUTHORIZATION: {
                        confirmPreauthorization(
                                merchant.getOrder(),
                                merchant.getAmount(),
                                merchant.getAuthCode(),
                                "1",
                                "",
                                new OnPaycometGetPreauthConfirmResponse() {
                                    @Override
                                    public void onResponse(PaycometPreauthConfirm preauthConfirm) {
                                        callback.returnPurchaseDetailsResponse(new PTPVPurchaseDetails(preauthConfirm));
                                    }

                                    @Override
                                    public void onError(PaycometError error) {
                                        try {
                                            Log.d("IP", error.getError());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                        );
                        break;
                    }
                    case CANCEL_DEFERRED_PREAUTHORIZATION: {
                        cancelPreauthorization(
                                merchant.getOrder(),
                                merchant.getAmount(),
                                merchant.getAuthCode(),
                                "1",
                                "",
                                new OnPaycometGetPreauthCancelResponse() {
                                    @Override
                                    public void onResponse(PaycometPreauthCancel preauthCancel) {
                                        callback.returnPurchaseDetailsResponse(new PTPVPurchaseDetails(preauthCancel));
                                    }

                                    @Override
                                    public void onError(PaycometError error) {
                                        try {
                                            Log.d("IP", error.getError());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                        );
                        break;
                    }
                    default: {

                    }
                }
            }

            @Override
            public void onError(PaycometError error) {
                try {
                    Log.d("IP", error.getError());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Deprecated
    public void addUserToken(

            String jetToken,
            PTPVCallbacks.AddUserResponse callback
    ) {

        JsonObject request = createRequest();
        AddRequestParam.addCardTokenParams(
                request,
                jetToken
        );

        this.mPaycometApi.postCards(mConfiguration.getAPIKey(), request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paycometGetUser -> {

                            if (!paycometGetUser.getErrorId().equals("0")) {
                                callback.returnAddUserError(new PTPVError(paycometGetUser.getErrorId()));
                            } else {
                                callback.returnAddUserResponse(new PTPVAddUser(paycometGetUser));
                            }
                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException error = (HttpException) throwable;
                                String errorBody = error.response().errorBody().string();
                                callback.returnAddUserError(new PTPVError(JsonParser.parseString(errorBody).getAsJsonObject().get(ERROR_ID).toString()));
                            }
                        });
    }
}
