package com.paycomet.androidRestSDK.old.Model.PTPVRequests;

import com.paycomet.androidRestSDK.Model.Requests.PaycometIP;

public class PTPVGetIP extends PaycometIP {
    public PTPVGetIP(PaycometIP ip) {
        super(ip.getRemoteAddress());
        super.setErrorId(ip.getErrorId());
    }
}
