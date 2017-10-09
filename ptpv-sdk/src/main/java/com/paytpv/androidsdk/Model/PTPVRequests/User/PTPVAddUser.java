
package com.paytpv.androidsdk.Model.PTPVRequests.User;

import com.paytpv.androidsdk.Model.Basic.PTPVUser;
import com.google.gson.annotations.SerializedName;

import static com.paytpv.androidsdk.Utils.Constants.ERROR_ID;

/**
 * Represents the response body of the "add_user" operation.
 */
public class PTPVAddUser extends PTPVUser {
    /**
     * Error ID of the request, if any.
     */
    @SerializedName(ERROR_ID)
    private String errorId;

    /**
     * Creates a new PTPVAddUser response populated with the provided values.
     *
     * @param idUser User's unique identifier
     * @param tokenUser User's token
     */
    public PTPVAddUser(String idUser, String tokenUser) {
        super(idUser, tokenUser);
    }

    public String getErrorId() {
        return errorId;
    }

    @Override
    public String toString() {
        return "PTPVAddUser{" + super.toString() +
                "errorId='" + errorId + '\'' +
                '}';
    }
}
