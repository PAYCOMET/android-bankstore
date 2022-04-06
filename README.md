# ANDROID-SDK
PAYCOMET SDK for Apps in Android

The PAYCOMET SDK provides easy to use methods for connecting to the PAYCOMET <a href='https://docs.paycomet.com/es/integracion/rest-docs'>REST API</a>.

- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)

## Requirements
The SDK is compatible with Android apps supporting API 21 and later.

## Installation
In the process of uploading to mavenCentral

Import the module with IDE you are working

## Usage

After you're done installing the SDK, you need to get the shared client and set up its configuration with your terminal details:

Java:

```java

PaycometApiClient client = PaycometApiClient.getInstance(this.getApplicationContext());
        PaycometConfiguration configuration = new PaycometConfiguration.PaycometConfigurationBuilder(
                CONFIG_API_KEY,
                CONFIG_TERMINAL
        ).build();
        client.setConfiguration(configuration);
```

Add internet permissions:

AndroidManifest:

```xml

<uses-permission android:name="android.permission.INTERNET" />
```

After you created the configuration, you can start making requests:

Form:

```java

client.getForm(
                LANGUAGE, // Example: es
                AMOUNT, // Example: 0
                CURRENCY, // Example: EUR
                SECURE, // Example: 1
                USER_INTERACTION, // Example: 1
                URL_OK, // Example: https://www.paycomet.com/url-ok
                URL_KO, // Example: https://www.paycomet.com/url-ko
                new PaycometCallbacks.OnPaycometGetFormResponse() {
                    @Override
                    public void onResponse(PaycometForm form) {

                        // Example of handling the challengeUrl in WebView
                        String url = form.getChallengeUrl();
                        WebSettings webSettings = webView.getSettings();
                        webSettings.setJavaScriptEnabled(true);
                        webView.clearCache(true);
                        webView.loadUrl(url);
                        webView.setWebViewClient(new WebViewClient());
                    }

                    @Override
                    public void onError(PaycometError error) {
                        // Handle errors here
                    }
                });
```

ExecutePurchase:

```java
client.addUser(
                PAN, // Example: 4273682057894021
                EXPIRY_MONTH, // Example: 05
                EXPIRY_YEAR, // Example: 2023
                CVC2, // Example: 123
                PRODUCT_DESCRIPTION, // Example: Android SDK
                LANGUAGE, // Example: es
                new PaycometCallbacks.OnPaycometGetUserResponse() {
                    @Override
                    public void onResponse(PaycometUser user) {
                        client.executePurchase(
                                AMOUNT, // Example: 0
                                CURRENCY, // Example: EUR
                                METHOD_ID, // Example: 1
                                user.getUserId(),
                                user.getUserToken(),
                                SECURE, // Example: 1
                                USER_INTERACTION, // Example: 1
                                PRODUCT_DESCRIPTION, // Example: Android SDK
                                new PaycometCallbacks.OnPaycometGetExecutePurchaseResponse() {
                                    @Override
                                    public void onResponse(PaycometExecutePurchase executePurchase) {
                                        // Handle successful payment here
                                    }

                                    @Override
                                    public void onError(PaycometError error) {
                                        // Handle errors here
                                    }
                                }
                        );
                    }

                    @Override
                    public void onError(PaycometError error) {
                        // Handle errors here
                    }
                }
        );
```

Retrieves country info by IP:

```java
client.getRemoteIp(
                new PaycometCallbacks.OnPaycometGetIPResponse() {
                    @Override
                    public void onResponse(PaycometIP ip) {

                        client.getIp(
                                ip.getRemoteAddress(),
                                new PaycometCallbacks.OnPaycometGetIPResponse() {
                                    @Override
                                    public void onResponse(PaycometIP ip) {
                                        // Handle successful response here
                                    }

                                    @Override
                                    public void onError(PaycometError error) {
                                        // Handle errors here
                                    }
                                }
                        );
                    }

                    @Override
                    public void onError(PaycometError error) {
                        // Handle errors here
                    }
                }
        );
```