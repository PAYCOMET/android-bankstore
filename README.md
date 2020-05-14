# ANDROID-SDK
PAYCOMET SDK for Apps in Android

The PAYCOMET SDK provides easy to use methods for connecting to the PAYCOMET API.

- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Examples](#examples)

## Requirements

The SDK is compatible with Android apps supporting API 15 and later.

Important<br>
Integration via ANDROID-SDK does not comply with PCI standards, to perform a mobile integration that complies with PCI standards you can integrate using <a href='https://docs.paycomet.com/en/documentacion/bankstore_jetiframe'>BankStore JET-IFRAME</a>.

---

## Installation

To integrate PAYCOMET into your project, add the following dependency to  the module `build.gradle`:

```ruby
dependencies {
    compile 'com.paytpv.androidsdk:paytpv-android-sdk:1.0'
}
```
The project is found on `jcenter`, so you need to add the following lines to the project `build.gradle`:

```ruby
repositories {
    jcenter()
}
```

## Usage

After you're done installing the SDK, you need to get the shared client and set up its configuration with your terminal details:

Java:

```java

PTPVApiClient client = PTPVApiClient.getInstance(this.getApplicationContext());
PTPVConfiguration configuration = new PTPVConfiguration.PTPVConfigurationBuilder(CONFIG_CODE, CONFIG_TERMINAL, CONFIG_PASSWORD)
        .setJetId(CONFIG_JET)
        .build();
client.setConfiguration(configuration);
```

Kotlin:

```kotlin

val client = PTPVApiClient.getInstance(applicationContext)
val configuration = PTPVConfiguration.PTPVConfigurationBuilder(CONFIG_CODE, CONFIG_TERMINAL, CONFIG_PASSWORD)
        .setJetId(CONFIG_JET)
        .build()
client.setConfiguration(configuration)
```

After you created the configuration, you can start making requests:

Java:

```java

// Create a PTPVCard object to store the user's card details
PTPVCard card = new PTPVCard("4111111111111111", "0518", "123");

client.addUser(card, new PTPVCallbacks.AddUserResponse() {

    @Override
    public void returnAddUserError(PTPVError error) {
        // Handle errors here
    }

    @Override
    public void returnAddUserResponse(PTPVAddUser addUserResponse) {

        // Define payment details
        PTPVMerchant merchant = new PTPVMerchant("199", "android_1234", PTPVCurrency.EUR);
        PTPVProduct product = new PTPVProduct("PAYCOMET", "Android SDK", "100");

        // Make the payment
        client.executePurchase(addUserResponse, merchant, product, new PTPVCallbacks.PurchaseDetailsResponse() {

            @Override
            public void returnPurchaseDetailsError(PTPVError error) {
                // Handle errors here
            }

            @Override
            public void returnPurchaseDetailsResponse(PTPVPurchaseDetails executePurchaseResponse) {
                // Handle successful payment here
            }
        });
    }
});
```

Kotlin:

```kotlin

// Create a PTPVCard object to store the user's card details
val card = PTPVCard("4111111111111111", "0518", "123")

client.addUser(card, object: PTPVCallbacks.AddUserResponse {

    override fun returnAddUserError(error: PTPVError?) {
        // Handle errors here
    }

    override fun returnAddUserResponse(addUserResponse: PTPVAddUser?) {

        // Define payment details
        val merchant = PTPVMerchant("199", "android_1234", PTPVCurrency.EUR)
        val product = PTPVProduct("PAYCOMET", "Android SDK", "100")

        // Make the payment
        client.executePurchase(addUserResponse, merchant, product, object : PTPVCallbacks.PurchaseDetailsResponse {

            override fun returnPurchaseDetailsError(error: PTPVError?) {
                // Handle errors here
            }

            override fun returnPurchaseDetailsResponse(executePurchaseResponse: PTPVPurchaseDetails?) {
                // Handle successful payment here
            }
        });
    }
});
```
## Examples

There are Java and Kotlin example applications included in the repository. They show how to use the SDK to: add and remove a card, add/get info/remove a user, make a payment and make a refund. Check the `app-java` and `app-kotlin` folders.
