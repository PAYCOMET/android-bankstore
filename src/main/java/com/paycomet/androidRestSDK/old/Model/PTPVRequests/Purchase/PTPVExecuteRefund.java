package com.paycomet.androidRestSDK.old.Model.PTPVRequests.Purchase;

import com.paycomet.androidRestSDK.Model.Requests.PaycometExecuteRefund;

public class PTPVExecuteRefund extends PaycometExecuteRefund {
    public PTPVExecuteRefund(PaycometExecuteRefund refund) {
        super(refund.getAmount(), refund.getOrder(), refund.getCurrency(), refund.getAuthCode(), refund.getMethodId());
        super.setErrorId(getErrorId());
    }
}
