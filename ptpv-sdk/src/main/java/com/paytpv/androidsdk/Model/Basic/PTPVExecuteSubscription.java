
package com.paytpv.androidsdk.Model.Basic;

/**
 * Represents whether or not should the subscription charge when created or updated
 */
public interface PTPVExecuteSubscription {
    /**
     * Charges at the beginning of the subscription
     */
    String EXECUTE = "1";

    /**
     * Doesn't charge at the beginning of the subscription
     */
    String NOT_EXECUTE = "0";
}
