package com.paycomet.androidRestSDK.old.Model.Basic;

import com.paycomet.androidRestSDK.Model.Basic.PaycometMerchant;

public class PTPVMerchant extends PaycometMerchant {
    /**
     * Creates an order. Used when executing a purchase.
     *
     * @param amount Amount of the operation in integer format. 1.00 EURO = 100, 4.50 EUROS = 450...
     * @param order Reference of the operation. Must be unique on every valid transaction
     * @param currency Currency of the transaction. See more details at <a
     *            http://developers.paytpv.com/en/documentacion/monedas">http://developers.paytpv.com/en/documentacion/monedas</a>
     * @param authCode Authorization bank code of the transaction (required to execute a return)
     */
    public PTPVMerchant(String amount, String order, String currency, String authCode) {
        super(amount,order,currency,authCode);
    }

    /**
     * Creates an order. Used when executing a purchase.
     *
     * @param amount Amount of the operation in integer format. 1.00 EURO = 100, 4.50 EUROS = 450...
     * @param order Reference of the operation. Must be unique on every valid transaction
     * @param currency Currency of the transaction. See more details at <a
     *            http://developers.paytpv.com/en/documentacion/monedas">http://developers.paytpv.com/en/documentacion/monedas</a>
     */
    public PTPVMerchant(String amount, String order, String currency) {
        super(amount,order,currency);
    }
}
