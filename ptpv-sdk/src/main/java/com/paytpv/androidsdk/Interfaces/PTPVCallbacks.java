package com.paytpv.androidsdk.Interfaces;

import com.paytpv.androidsdk.Model.Basic.PTPVError;
import com.paytpv.androidsdk.Model.PTPVRequests.PTPVGetIP;
import com.paytpv.androidsdk.Model.PTPVRequests.PTPVRemoveResponse;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVConfirmPurchaseDcc;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVExecutePurchaseDcc;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVExecuteRefund;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVPurchaseDetails;
import com.paytpv.androidsdk.Model.PTPVRequests.Subscription.PTPVSubscriptionResponse;
import com.paytpv.androidsdk.Model.PTPVRequests.User.PTPVAddUser;
import com.paytpv.androidsdk.Model.PTPVRequests.User.PTPVInfoUser;

public class PTPVCallbacks {
    /**
     * Callback containing the response or the error of the request for getting the current IP.
     */
    public interface GetIPResponse {
        void returnGetIPError(PTPVError error);
        void returnGetIPResponse(PTPVGetIP getIP);
    }

    /**
     * Callback containing the response or the error of the request for adding a new user.
     */
    public interface AddUserResponse {
        void returnAddUserError(PTPVError error);
        void returnAddUserResponse(PTPVAddUser addUserResponse);
    }

    /**
     * Callback containing the response or the error of the request for getting information about a user.
     */
    public interface InfoUserResponse {
        void returnInfoUserError(PTPVError error);
        void returnInfoUserResponse(PTPVInfoUser infoUserResponse);
    }

    /**
     * Callback containing the response or the error of the request for removing a user.
     */
    public interface RemoveUserResponse {
        void returnRemoveUserError(PTPVError error);
        void returnRemoveUserResponse(PTPVRemoveResponse removeUserResponse);
    }

    /**
     * Callback containing the response or the error of the request for making a purchase.
     */
    public interface PurchaseDetailsResponse {
        void returnPurchaseDetailsError(PTPVError error);
        void returnPurchaseDetailsResponse(PTPVPurchaseDetails executePurchaseResponse);
    }

    /**
     * Callback containing the response or the error of the request for making a purchase that involves different currencies.
     */
    public interface ExecutePurchaseDccResponse {
        void returnExecutePurchaseDccError(PTPVError error);
        void returnExecutePurchaseDccResponse(PTPVExecutePurchaseDcc executePurchaseResponse);
    }

    /**
     * Callback containing the response or the error of the request for confirming a purchase that involves different currencies.
     */
    public interface ConfirmPurchaseDccResponse {
        void returnConfirmPurchaseDccError(PTPVError error);
        void returnConfirmPurchaseDccResponse(PTPVConfirmPurchaseDcc confirmPurchaseResponse);
    }

    /**
     * Callback containing the response or the error of the request for making refunds of the performed operations.
     */
    public interface ExecuteRefundResponse {
        void returnExecuteRefundError(PTPVError error);
        void returnExecuteRefundResponse(PTPVExecuteRefund executeRefundResponse);
    }

    /**
     * Callback containing the response or the error of the request for creating a payment subscription.
     */
    public interface CreateSubscriptionResponse {
        void returnCreateSubscriptionError(PTPVError error);
        void returnCreateSubscriptionResponse(PTPVSubscriptionResponse createSubscriptionResponse);
    }

    /**
     * Callback containing the response or the error of the request for modifying a payment subscription.
     */
    public interface EditSubscriptionResponse {
        void returnEditSubscriptionError(PTPVError error);
        void returnEditSubscriptionResponse(PTPVSubscriptionResponse editSubscriptionResponse);
    }

    /**
     * Callback containing the response or the error of the request for removing a payment subscription.
     */
    public interface RemoveSubscriptionResponse {
        void returnRemoveSubscriptionError(PTPVError error);
        void returnRemoveSubscriptionResponse(PTPVRemoveResponse removeSubscriptionResponse);
    }
}
