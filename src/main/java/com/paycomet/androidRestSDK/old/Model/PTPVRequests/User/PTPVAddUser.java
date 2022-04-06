package com.paycomet.androidRestSDK.old.Model.PTPVRequests.User;

import com.paycomet.androidRestSDK.Model.Basic.PaycometUser;

public class PTPVAddUser extends PaycometUser {
    public PTPVAddUser(PaycometUser user) {
        super(user.getUserId(), user.getUserToken());
        super.setErrorId(user.getErrorId());
    }
}
