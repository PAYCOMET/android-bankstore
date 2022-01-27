package com.paycomet.androidRestSDK.old.Model.PTPVRequests.Preauthorization;

public enum PTPVPreauthorizationOperations {
    /**
     * Creates a user preauthorization in the system.<br>
     * <br>
     * Once the user is registered in the system, preauthorization operations may be performed by
     * sending their credentials and data of the operation.
     */
    CREATE_AUTHORIZATION,

    /**
     * Confirms a preauthorization.<br>
     * <br>
     * Once a preauthorization operation has been performed and authorized, it can be confirmed to
     * make the cash payment within 7 days; after that date, preauthorizations become invalid. The
     * amount of the preauthorization confirmation can be less than, equal to or greater than 15% of
     * the original preauthorization.
     */
    CONFIRM_AUTHORIZATION,

    /**
     * Cancels a preauthorization.<br>
     * <br>
     * Once a preauthorization has been performed, it can be canceled within 7 days.
     */
    CANCEL_AUTHORIZATION,

    /**
     * Confirms a deferred preauthorization in the system.<br>
     * <br>
     * Once a deferred pre-authorisation operation has been carried out and authorised, it can be
     * confirmed to carry out the effective payment within the following 72 hours; after this time,
     * the deferred pre-authorisations lose their validity. The amount of the deferred
     * pre-authorisation must be exactly equal to that of the original deferred pre-authorisation.
     */
    CONFIRM_DEFERRED_PREAUTHORIZATION,

    /**
     * Cancels a deferred preauthorization in the system.<br>
     * <br>
     * Once a deferred pre-authorisation has been carried out, it can be cancelled within the
     * following 72 hours.
     */
    CANCEL_DEFERRED_PREAUTHORIZATION
}
