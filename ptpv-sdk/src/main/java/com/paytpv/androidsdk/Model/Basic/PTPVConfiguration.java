
package com.paytpv.androidsdk.Model.Basic;

/**
 * Represents the configuration of the contracted product.<br>
 * <br>
 * You provide a PTPVConfiguration instance to the PTPVAPIClient which will use it when making api
 * calls to PAYTPV.<br>
 * <br>
 * In order to use the PAYTPV payment gateway in your business, you must have the necessary
 * configuration parameters. These can be obtained through the PAYTPV customer management platform
 * at <a href="https://secure.paytpv.com/cp_control/">https://secure.paytpv.com/cp_control/</a><br>
 * <br>
 * Once inside the platform, the configuration of the contracted product can be reviewed through the
 * menu My products -> Configure product.<br>
 * <br>
 * After clicking the "Edit" button of the chosen product, a screen will appear with the basic
 * information of the product under the section "Technical configuration of the WEB POS".
 * Specifically, the information required during the integration process is:<br>
 * Password<br>
 * Terminal number<br>
 * Customer code<br>
 */
public class PTPVConfiguration {
    /**
     * Customer code
     */
    private String merchantCode;

    /**
     * Terminal number
     */
    private String terminal;

    /**
     * Password
     */
    private String password;

    /**
     * Identifier for JET encryption
     */
    private String jetId;

    /**
     * Populates the PTPVConfiguration instance with the provided values.
     * 
     * @param builder
     */
    private PTPVConfiguration(PTPVConfigurationBuilder builder) {
        this.merchantCode = builder.merchantCode;
        this.terminal = builder.terminal;
        this.password = builder.password;
        this.jetId = builder.jetId;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public String getTerminal() {
        return terminal;
    }

    public String getPassword() {
        return password;
    }

    public String getJetId() {
        return jetId;
    }

    /**
     * Builder class for the PTPVConfiguration instance
     */
    public static class PTPVConfigurationBuilder {
        private String merchantCode;
        private String terminal;
        private String password;
        private String jetId;

        public PTPVConfigurationBuilder(String merchantCode, String terminal, String password) {
            this.merchantCode = merchantCode;
            this.terminal = terminal;
            this.password = password;
        }

        public PTPVConfigurationBuilder setJetId(String jetId) {
            this.jetId = jetId;
            return this;
        }

        public PTPVConfiguration build() {
            return new PTPVConfiguration(this);
        }
    }
}
