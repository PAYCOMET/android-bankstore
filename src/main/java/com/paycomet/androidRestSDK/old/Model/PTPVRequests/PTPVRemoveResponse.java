package com.paycomet.androidRestSDK.old.Model.PTPVRequests;

import com.paycomet.androidRestSDK.Model.Requests.PaycometRemoveResponse;

public class PTPVRemoveResponse extends PaycometRemoveResponse {

    public PTPVRemoveResponse(PaycometRemoveResponse removeResponse) {
        super(removeResponse.getErrorId());
        super.setErrorId(removeResponse.getErrorId());
    }
}
