package com.paycomet.androidRestSDK.old.Model.PTPVRequests.Subscription;

import com.paycomet.androidRestSDK.Model.Basic.PaycometSubscription;
import com.paycomet.androidRestSDK.Model.Requests.PaycometSubscriptionResponse;
import com.paycomet.androidRestSDK.old.Model.Basic.PTPVSubscription;


public class PTPVSubscriptionResponse extends PTPVSubscription {

    private String userId;
    private String userToken;
    private String cardCountry;
    private String authCode;
    private String errorId;

    public PTPVSubscriptionResponse(PaycometSubscriptionResponse subscriptionResponse) {
        super(subscriptionResponse.getAmount(), subscriptionResponse.getOrder(), subscriptionResponse.getCurrency(), subscriptionResponse.getSubscription().getStartDate(), subscriptionResponse.getSubscription().getEndDate(), subscriptionResponse.getSubscription().getPeriodicity());
        this.userId = subscriptionResponse.getIdUser();
        this.userToken = subscriptionResponse.getTokenUser();
        this.cardCountry = subscriptionResponse.getCardCountry();
        this.authCode = subscriptionResponse.getAuthCode();
        this.errorId = subscriptionResponse.getErrorId();
    }

    public PTPVSubscriptionResponse(String subscriptionAmount, String subscriptionOrder, String subscriptionCurrency) {
        super(subscriptionAmount, subscriptionOrder, subscriptionCurrency);
    }

    public String getUserId() {
        return userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public String getCardCountry() {
        return cardCountry;
    }

    public String getAuthCode() {
        return authCode;
    }

    public String getErrorId() {
        return errorId;
    }
}
