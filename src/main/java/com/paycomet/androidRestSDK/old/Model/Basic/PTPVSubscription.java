package com.paycomet.androidRestSDK.old.Model.Basic;

public class PTPVSubscription {

    private String subscriptionAmount;
    private String subscriptionOrder;
    private String subscriptionCurrency;
    private String subscriptionStartDate;
    private String subscriptionEndDate;
    private String subscriptionPeriodicity;

    public PTPVSubscription() {}

    public PTPVSubscription(String subscriptionAmount, String subscriptionOrder, String subscriptionCurrency) {
        this.subscriptionAmount = subscriptionAmount;
        this.subscriptionOrder = subscriptionOrder;
        this.subscriptionCurrency = subscriptionCurrency;
    }

    public PTPVSubscription(String subscriptionAmount, String subscriptionOrder, String subscriptionCurrency, String subscriptionStartDate, String subscriptionEndDate, String subscriptionPeriodicity) {
        this.subscriptionAmount = subscriptionAmount;
        this.subscriptionOrder = subscriptionOrder;
        this.subscriptionCurrency = subscriptionCurrency;
        this.subscriptionStartDate = subscriptionStartDate;
        this.subscriptionEndDate = subscriptionEndDate;
        this.subscriptionPeriodicity = subscriptionPeriodicity;
    }

    public String getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public String getSubscriptionOrder() {
        return subscriptionOrder;
    }

    public String getSubscriptionCurrency() {
        return subscriptionCurrency;
    }

    public String getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public String getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public String getSubscriptionPeriodicity() {
        return subscriptionPeriodicity;
    }
}
