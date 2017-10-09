package com.paytpv.appjava.utils;

import com.paytpv.androidsdk.Model.Basic.PTPVUser;

import java.util.ArrayList;

public class UserHandler {
    private static UserHandler singleton = new UserHandler( );
    private ArrayList<PTPVUser> userArray = new ArrayList<>();

    private UserHandler() { }

    public static UserHandler getInstance( ) {
        return singleton;
    }

    public ArrayList<PTPVUser> getUserArray() {
        return userArray;
    }
}
