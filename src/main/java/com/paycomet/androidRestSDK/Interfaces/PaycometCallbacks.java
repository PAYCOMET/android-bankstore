package com.paycomet.androidRestSDK.Interfaces;

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
import com.paycomet.androidRestSDK.Model.Requests.PaycometError;
import com.paycomet.androidRestSDK.Model.Requests.PaycometForm;
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

public class PaycometCallbacks {

    public interface OnPaycometGetIPResponse {
        void onResponse(PaycometIP ip);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetFormResponse {
        void onResponse(PaycometForm form);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetBalanceResponse {
        void onResponse(PaycometBalance balance);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetUserResponse {
        void onResponse(PaycometUser user);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetRemoveResponse {
        void onResponse(PaycometRemoveResponse removeResponse);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetInfoUserResponse {
        void onResponse(PaycometInfoUser infoUser);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetHeartbeatResponse {
        void onResponse(PaycometHeartbeat heartbeat);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetMarketplaceResponse {
        void onResponse(PaycometMarketplace marketplace);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetExecutePurchaseResponse {
        void onResponse(PaycometExecutePurchase executePurchase);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetExecutePurchaseRtokenResponse {
        void onResponse(PaycometExecutePurchaseRtoken executePurchaseRtoken);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetPreauthCancelResponse {
        void onResponse(PaycometPreauthCancel preauthCancel);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetPreauthConfirmResponse {
        void onResponse(PaycometPreauthConfirm preauthConfirm);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetCreatePreauthResponse {
        void onResponse(PaycometCreatePreauth createPreauth);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetCreatePreauthRtokenResponse {
        void onResponse(PaycometCreatePreauthRtoken createPreauthRtoken);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetExecuteRefundResponse {
        void onResponse(PaycometExecuteRefund executeRefund);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetExecutePurchaseDccResponse {
        void onResponse(PaycometExecutePurchaseDcc executePurchaseDcc);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetConfirmPurchaseDccResponse {
        void onResponse(PaycometConfirmPurchaseDcc confirmPurchaseDcc);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetErrorResponse {
        void onResponse(PaycometError error);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetExchangeResponse {
        void onResponse(PaycometExchange exchange);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetMethodsResponse {
        void onResponse(List<PaycometMethod> methods);
        void onError(PaycometError error);
    }

    public interface OnPaycometGetSubscriptionResponse {
        void onResponse(PaycometSubscriptionResponse subscriptionResponse);
        void onError(PaycometError error);
    }
}


