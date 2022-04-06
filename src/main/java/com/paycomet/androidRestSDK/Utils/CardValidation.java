package com.paycomet.androidRestSDK.Utils;

import static com.paycomet.androidRestSDK.Model.Requests.PaycometError.isInteger;

import java.util.Calendar;

public class CardValidation {
    /**
     * Checks if the card number is valid
     *
     * @param number The number of the card
     * @return Either true, if the number of the card if valid, or false, if not.
     */
    public static boolean checkNumber(String number) {
        if (number.length() != 16 && !isInteger(number, 10)) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the card expiry date is valid
     *
     * @param expiryDate The expiry date of the card
     * @return Either true, if the expiry date of the card if valid, or false, if not.
     */
    public static boolean checkExpiryDate(String expiryDate) {
        if (expiryDate.length() != 4) {
            return false;
        }

        int month = Integer.parseInt(expiryDate) / 100;
        int year = 2000 + Integer.parseInt(expiryDate) % 100;

        Calendar cal = Calendar.getInstance();

        if (!(year >= cal.get(Calendar.YEAR)) && !(month >= (cal.get(Calendar.MONTH) + 1))) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the card CVV is valid
     *
     * @param cvv The CVV of the card
     * @return Either true, if the CVV of the card if valid, or false, if not.
     */
    public static boolean checkCVV(String cvv) {
        if ((cvv.length() != 3 && cvv.length() != 4) || !cvv.matches("[0-9]+")) {
            return false;
        }
        return true;
    }
}
