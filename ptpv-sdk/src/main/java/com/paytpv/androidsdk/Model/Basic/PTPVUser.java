
package com.paytpv.androidsdk.Model.Basic;

import com.google.gson.annotations.SerializedName;

import static com.paytpv.androidsdk.Utils.Constants.USER_ID;
import static com.paytpv.androidsdk.Utils.Constants.USER_TOKEN;

/**
 * Represents the user registered in the system
 */
public class PTPVUser {
    /**
     * Unique identifier of the user registered in the system
     */
    @SerializedName(USER_ID)
    private String userId;

    /**
     * Token code associated with the user's ID
     */
    @SerializedName(USER_TOKEN)
    private String userToken;

    /**
     * Creates a PTPVUser.
     *
     * @param userId Unique identifier of the user registered in the system
     * @param userToken Token code associated with the user's ID
     */
    public PTPVUser(String userId, String userToken) {
        this.userId = userId;
        this.userToken = userToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserToken() {
        return userToken;
    }

    @Override
    public String toString() {
        return "PTPVUser{" +
                "userId='" + userId + '\'' +
                ", userToken='" + userToken + '\'' +
                '}';
    }
}
