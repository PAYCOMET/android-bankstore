package com.paycomet.androidRestSDK.Model.Basic;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.METHOD_ACTIVE;
import static com.paycomet.androidRestSDK.Utils.Constants.METHOD_ALLOW_API_REFUNDS;
import static com.paycomet.androidRestSDK.Utils.Constants.METHOD_DESCRIPTION;
import static com.paycomet.androidRestSDK.Utils.Constants.METHOD_ID;
import static com.paycomet.androidRestSDK.Utils.Constants.METHOD_LOGO_LANDSCAPE;
import static com.paycomet.androidRestSDK.Utils.Constants.METHOD_LOGO_SCUARE;
import static com.paycomet.androidRestSDK.Utils.Constants.METHOD_NAME;

import com.google.gson.annotations.SerializedName;

public class PaycometMethod {

    @SerializedName(METHOD_ID)
    private String id;

    @SerializedName(METHOD_NAME)
    private String name;

    @SerializedName(METHOD_DESCRIPTION)
    private String description;

    @SerializedName(METHOD_ACTIVE)
    private String active;

    @SerializedName(METHOD_ALLOW_API_REFUNDS)
    private String allowAPIRefunds;

    @SerializedName(METHOD_LOGO_SCUARE)
    private String logo_square;

    @SerializedName(METHOD_LOGO_LANDSCAPE)
    private String logo_landscape;

    @SerializedName(ERROR_ID)
    private String errorId;

    public PaycometMethod() {}

    public PaycometMethod(String id, String name, String description, String active, String allowAPIRefunds, String logo_square, String logo_landscape) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.allowAPIRefunds = allowAPIRefunds;
        this.logo_square = logo_square;
        this.logo_landscape = logo_landscape;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getActive() {
        return active;
    }

    public String getAllowAPIRefunds() {
        return allowAPIRefunds;
    }

    public String getLogo_square() {
        return logo_square;
    }

    public String getLogo_landscape() {
        return logo_landscape;
    }

    public String getErrorId() {
        return errorId;
    }
    protected void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    @Override
    public String toString() {
        return "PaycometMethod{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active='" + active + '\'' +
                ", allowAPIRefunds='" + allowAPIRefunds + '\'' +
                ", logo_square='" + logo_square + '\'' +
                ", logo_landscape='" + logo_landscape + '\'' +
                '}';
    }
}
