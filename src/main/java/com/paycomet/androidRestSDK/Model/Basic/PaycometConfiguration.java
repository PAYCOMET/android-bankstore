package com.paycomet.androidRestSDK.Model.Basic;

public class PaycometConfiguration {

    /**
     * API KEY
     */
    private String APIKey;

    /**
     * Terminal number
     */
    private String terminal;

    /**
     * Populates the PaycometConfiguration instance with the provided values.
     *
     * @param builder
     */
    private PaycometConfiguration(PaycometConfigurationBuilder builder) {
        this.APIKey = builder.APIKey;
        this.terminal = builder.terminal;
    }

    public String getAPIKey() {
        return APIKey;
    }

    public String getTerminal() {
        return terminal;
    }

    /**
     * Builder class for the PaycometConfiguration instance
     */
    public static class PaycometConfigurationBuilder {
        private String APIKey;
        private String terminal;

        public PaycometConfigurationBuilder(String APIKey, String terminal) {
            this.APIKey = APIKey;
            this.terminal = terminal;
        }

        public PaycometConfiguration build() {
            return new PaycometConfiguration(this);
        }
    }
}
