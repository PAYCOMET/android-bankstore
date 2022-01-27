package com.paycomet.androidRestSDK.old.Model.PTPVRequests.User;

import com.paycomet.androidRestSDK.Model.Requests.PaycometInfoUser;

public class PTPVInfoUser extends PaycometInfoUser {
    public PTPVInfoUser(PaycometInfoUser user) {
        super(user.getNumber(), user.getExpiryDate(), user.getCardBrand(), user.getCardType(), user.getCardIso());
        super.setErrorId(user.getErrorId());
    }
}
