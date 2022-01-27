
## Usage

After you're done installing the SDK, you need to get the shared client and set up its configuration with your terminal details:

Java:

```java

PaycometApiClient client = PaycometApiClient.getInstance(this.getApplicationContext());
        PaycometConfiguration configuration = new PaycometConfiguration.PaycometConfigurationBuilder(CONFIG_API_KEY, CONFIG_TERMINAL).build();
        client.setConfiguration(configuration);
```

After you created the configuration, you can start making requests:

Java:

```java
client.getForm(
                "es",
                "0",
                "EUR",
                "1",
                "1",
                "https://www.paycomet.com/url-ok",
                "https://www.paycomet.com/url-ko",
                new PaycometCallbacks.OnPaycometGetFormResponse() {
            @Override
            public void onResponse(PaycometForm form) {

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