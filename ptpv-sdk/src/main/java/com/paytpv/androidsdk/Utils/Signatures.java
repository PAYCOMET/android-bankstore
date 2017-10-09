
package com.paytpv.androidsdk.Utils;

import com.paytpv.androidsdk.Model.Basic.PTPVCard;
import com.paytpv.androidsdk.Model.Basic.PTPVConfiguration;
import com.paytpv.androidsdk.Model.Basic.PTPVMerchant;
import com.paytpv.androidsdk.Model.Basic.PTPVSubscription;
import com.paytpv.androidsdk.Model.Basic.PTPVUser;
import com.paytpv.androidsdk.Model.PTPVRequests.Preauthorization.PTPVPreauthorizationOperations;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVExecutePurchaseDcc;

import java.security.MessageDigest;

/**
 * Class providing methods for generating the mandatory signature for each request
 */
public class Signatures {

    /**
     * Generates a signature for the "json-remote-ip" request
     *
     * @param now Current date, in String format
     * @param configuration A PTPVConfiguration instance
     * @return Hash value representing the signature for the request
     */
    public static String getIpHash(String now, PTPVConfiguration configuration) {
        if (configuration == null) {
            return "";
        }

        String text = configuration.getMerchantCode() + configuration.getTerminal()
                + now + configuration.getPassword();

        return SHAEncrypt(text, "SHA-256");
    }

    /**
     * Generates a signature for the "add_user" request
     *
     * @param card A PTPVCard instance
     * @param configuration The PTPVConfiguration instance
     * @return Hash value representing the signature for the request
     */
    public static String addUserHash(PTPVCard card, PTPVConfiguration configuration) {
        if (configuration == null) {
            return "";
        }
        String text = configuration.getMerchantCode() + card.getNumber()
                + card.getCvv() + configuration.getTerminal()
                + configuration.getPassword();

        return SHAEncrypt(text, "SHA-1");
    }

    /**
     * Generates a signature for the "add_user_token" request
     *
     * @param jetToken Token obtained from javascript
     * @param configuration The PTPVConfiguration instance
     * @return Hash value representing the signature for the request
     */
    public static String addUserTokenHash(String jetToken, PTPVConfiguration configuration) {
        if (configuration == null) {
            return "";
        }
        String text = configuration.getMerchantCode() + jetToken
                + configuration.getJetId() + configuration.getTerminal()
                + configuration.getPassword();

        return SHAEncrypt(text, "SHA-1");
    }

    /**
     * Generates a signature for the "info_user" request
     *
     * @param user A PTPVUser instance
     * @param configuration The PTPVConfiguration instance
     * @return Hash value representing the signature for the request
     */
    public static String infoUserHash(PTPVUser user, PTPVConfiguration configuration) {
        if (configuration == null) {
            return "";
        }
        String text = configuration.getMerchantCode() + user.getUserId()
                + user.getUserToken() + configuration.getTerminal()
                + configuration.getPassword();

        return SHAEncrypt(text, "SHA-1");
    }

    /**
     * Generates a signature for the "execute_purchase" request
     *
     * @param user A PTPVUser instance
     * @param merchant A PTPVMerchant instance
     * @param configuration The PTPVConfiguration instance
     * @return Hash value representing the signature for the request
     */
    public static String executePurchaseHash(PTPVUser user, PTPVMerchant merchant,
            PTPVConfiguration configuration) {
        if (configuration == null) {
            return "";
        }
        String text = configuration.getMerchantCode() + user.getUserId()
                + user.getUserToken() + configuration.getTerminal()
                + merchant.getAmount() + merchant.getOrder()
                + configuration.getPassword();

        return SHAEncrypt(text, "SHA-1");
    }

    /**
     * Generates a signature for the "execute_purchase_dcc" request
     *
     * @param merchant A PTPVMerchant instance
     * @param purchaseDcc A PTPVExecutePurchaseDcc instance
     * @param configuration The PTPVConfiguration instance
     * @return Hash value representing the signature for the request
     */
    public static String confirmPurchaseHash(PTPVMerchant merchant,
            PTPVExecutePurchaseDcc purchaseDcc, PTPVConfiguration configuration) {
        if (configuration == null) {
            return "";
        }
        String text = configuration.getMerchantCode() + configuration.getTerminal()
                + merchant.getOrder() + purchaseDcc.getDccCurrency()
                + purchaseDcc.getDccSession() + configuration.getPassword();

        return SHAEncrypt(text, "SHA-1");
    }

    /**
     * Generates a signature for the "confirm_purchase_dcc" request
     *
     * @param user A PTPVUser instance
     * @param merchant A PTPVMerchant instance
     * @param configuration The PTPVConfiguration instance
     * @return Hash value representing the signature for the request
     */
    public static String executeRefundHash(PTPVUser user, PTPVMerchant merchant,
            PTPVConfiguration configuration) {
        if (configuration == null) {
            return "";
        }
        String text = configuration.getMerchantCode() + user.getUserId()
                + user.getUserToken() + configuration.getTerminal()
                + merchant.getAuthCode() + merchant.getOrder()
                + configuration.getPassword();

        return SHAEncrypt(text, "SHA-1");
    }

    /**
     * Generates a signature for the "create_subscription" request
     *
     * @param card A PTPVCard instance
     * @param subscription A PTPVSubscription instance
     * @param configuration The PTPVConfiguration instance
     * @return Hash value representing the signature for the request
     */
    public static String createSubscriptionHash(PTPVCard card, PTPVSubscription subscription,
            PTPVConfiguration configuration) {
        if (configuration == null) {
            return "";
        }
        String text = configuration.getMerchantCode() + card.getNumber()
                + card.getCvv() + configuration.getTerminal()
                + subscription.getSubscriptionAmount() + subscription.getSubscriptionCurrency()
                + configuration.getPassword();

        return SHAEncrypt(text, "SHA-1");
    }

    /**
     * Generates a signature for the "edit_subscription" request
     *
     * @param user A PTPVUser instance
     * @param subscription A PTPVSubscription instance
     * @param configuration The PTPVConfiguration instance
     * @return Hash value representing the signature for the request
     */
    public static String editSubscriptionHash(PTPVUser user, PTPVSubscription subscription,
            PTPVConfiguration configuration) {
        if (configuration == null) {
            return "";
        }
        String text = configuration.getMerchantCode() + user.getUserId()
                + user.getUserToken() + configuration.getTerminal()
                + subscription.getSubscriptionAmount() + configuration.getPassword();

        return SHAEncrypt(text, "SHA-1");
    }

    /**
     * Generates a signature for the "remove_subscription" request
     *
     * @param user A PTPVUser instance
     * @param configuration The PTPVConfiguration instance
     * @return Hash value representing the signature for the request
     */
    public static String removeSubscriptionHash(PTPVUser user, PTPVConfiguration configuration) {
        if (configuration == null) {
            return "";
        }
        String text = configuration.getMerchantCode() + user.getUserId()
                + user.getUserToken() + configuration.getTerminal()
                + configuration.getPassword();

        return SHAEncrypt(text, "SHA-1");
    }

    /**
     * Generates a signature for the "create_subscription_token" request
     *
     * @param user A PTPVUser instance
     * @param subscription A PTPVSubscription instance
     * @param configuration The PTPVConfiguration instance
     * @return Hash value representing the signature for the request
     */
    public static String createSubscriptionTokenHash(PTPVUser user, PTPVSubscription subscription,
            PTPVConfiguration configuration) {
        if (configuration == null) {
            return "";
        }
        String text = configuration.getMerchantCode() + user.getUserId()
                + user.getUserToken() + configuration.getTerminal()
                + subscription.getSubscriptionAmount() + subscription.getSubscriptionCurrency()
                + configuration.getPassword();

        return SHAEncrypt(text, "SHA-1");
    }

    /**
     * Generates a signature for the preauthorization requests (create, confirm, cancel)
     *
     * @param user A PTPVUser instance
     * @param merchant A PTPVMerchant instance
     * @param operation The operation to be executed (create, confirm or cancel preauthorization)
     * @param configuration The PTPVConfiguration instance
     * @return
     */
    public static String preauthorizationOperationsHash(PTPVUser user, PTPVMerchant merchant,
            PTPVPreauthorizationOperations operation, PTPVConfiguration configuration) {
        if (configuration == null) {
            return "";
        }
        String text = configuration.getMerchantCode() + user.getUserId()
                + user.getUserToken() + configuration.getTerminal();

        switch (operation) {
            case CREATE_AUTHORIZATION: {
                text += merchant.getAmount() + merchant.getOrder();
                break;
            }
            default: {
                text += merchant.getOrder() + merchant.getAmount();
                break;
            }
        }

        text += configuration.getPassword();

        return SHAEncrypt(text, "SHA-1");
    }

    /**
     * Encrypts the text using the SHA-1 or SHA-256 algorithm
     *
     * @param text Text to be encrypted
     * @param hashType The algorithm for the encryption
     * @return Hash value of the text
     */
    private static String SHAEncrypt(String text, String hashType) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(hashType);
            messageDigest.update(text.getBytes("UTF-8"));
            byte[] bytes = messageDigest.digest();
            StringBuilder buffer = new StringBuilder();
            for (byte b : bytes) {
                buffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return buffer.toString();
        } catch (Exception ignored) {
            ignored.printStackTrace();
            return null;
        }
    }
}
