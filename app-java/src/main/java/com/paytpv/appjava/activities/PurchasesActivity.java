
package com.paytpv.appjava.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.paytpv.androidsdk.Interfaces.PTPVCallbacks;
import com.paytpv.androidsdk.Model.Basic.PTPVCard;
import com.paytpv.androidsdk.Model.Basic.PTPVCurrency;
import com.paytpv.androidsdk.Model.Basic.PTPVError;
import com.paytpv.androidsdk.Model.Basic.PTPVMerchant;
import com.paytpv.androidsdk.Model.Basic.PTPVProduct;
import com.paytpv.androidsdk.Model.Basic.PTPVUser;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVExecuteRefund;
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVPurchaseDetails;
import com.paytpv.androidsdk.Model.PTPVRequests.User.PTPVAddUser;
import com.paytpv.androidsdk.PTPVApiClient;
import com.paytpv.appjava.R;
import com.paytpv.appjava.adapters.ProductAdapter;
import com.paytpv.appjava.adapters.PurchaseAdapter;
import com.paytpv.appjava.utils.CardHandler;
import com.paytpv.appjava.utils.Products;
import com.paytpv.appjava.utils.PurchaseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PurchasesActivity extends AppCompatActivity {

    // ------------------------------------------------------------------------------
    // Singleton for manipulating the cards ArrayList
    // ------------------------------------------------------------------------------
    private ArrayList<PTPVCard> mCards = CardHandler.getInstance().getCardArray();

    // ------------------------------------------------------------------------------
    // Singleton for manipulating the purchases ArrayList
    // ------------------------------------------------------------------------------
    private ArrayList<PTPVPurchaseDetails> mPurchases = PurchaseHandler.getInstance()
            .getPurchaseArray();

    // ------------------------------------------------------------------------------
    // HashMap for storing the purchases with their users
    // ------------------------------------------------------------------------------
    private HashMap<PTPVPurchaseDetails, PTPVUser> mPurchasesHash = new HashMap<>();

    // ------------------------------------------------------------------------------
    // ArrayList containing the list of products
    // ------------------------------------------------------------------------------
    private ArrayList<PTPVProduct> mProductsArray = Products.getProductsArray();

    private PTPVApiClient mClient;

    private ProductAdapter mProductsAdapter;
    private PurchaseAdapter mPurchasesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);

        Products.initializeProducts();

        // ------------------------------------------------------------------------------
        // Get an instance to the client for executing operations
        // ------------------------------------------------------------------------------
        mClient = PTPVApiClient.getInstance(this);

        // ------------------------------------------------------------------------------
        // Initialize products ListView
        // ------------------------------------------------------------------------------
        ListView productsListView = (ListView) findViewById(R.id.products_list_view);
        View productsHeader = LayoutInflater.from(this).inflate(R.layout.list_view_products_header,
                productsListView, false);
        mProductsAdapter = new ProductAdapter(this, mProductsArray);

        productsListView.setAdapter(mProductsAdapter);
        productsListView.addHeaderView(productsHeader, null, false);

        // ------------------------------------------------------------------------------
        // Initialize purchases ListView
        // ------------------------------------------------------------------------------
        ListView purchasesListView = (ListView) findViewById(R.id.purchases_list_view);
        View purchasesHeader = LayoutInflater.from(this)
                .inflate(R.layout.list_view_purchases_header, productsListView, false);
        mPurchasesAdapter = new PurchaseAdapter(this, mPurchases);

        purchasesListView.setAdapter(mPurchasesAdapter);
        purchasesListView.addHeaderView(purchasesHeader, null, false);

        // ------------------------------------------------------------------------------
        // Listener for clicking on a product
        // ------------------------------------------------------------------------------
        productsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                executePurchaseDialog(i);
            }
        });

        // ------------------------------------------------------------------------------
        // Listener for clicking on a purchase
        // ------------------------------------------------------------------------------
        purchasesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                executeRefundDialog(i);
            }
        });
    }

    private void executePurchaseDialog(int i) {
        final int position = i;

        // ------------------------------------------------------------------------------
        // Initialize the Alert
        // ------------------------------------------------------------------------------
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View purchaseView = this.getLayoutInflater().inflate(R.layout.purchase_dialog, null);

        // ------------------------------------------------------------------------------
        // Initialize the user Spinner
        // ------------------------------------------------------------------------------
        final Spinner mCardSpinner = (Spinner) purchaseView
                .findViewById(R.id.registered_cards_spinner);
        ArrayAdapter<String> mCardSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item);

        mCardSpinnerAdapter.add("Select card");
        mCardSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCardSpinner.setAdapter(mCardSpinnerAdapter);

        // ------------------------------------------------------------------------------
        // Add all the saved users to the users Spinner
        // ------------------------------------------------------------------------------
        for (int x = 0; x < mCards.size(); x++) {
            mCardSpinnerAdapter.add(mCards.get(x).getNumber());
        }

        // ------------------------------------------------------------------------------
        // Configure the Alert
        // ------------------------------------------------------------------------------
        alert.setTitle("Purchase " + mProductsArray.get(position - 1).getDescription());
        alert.setMessage("Choose the card for the operation:");
        alert.setView(purchaseView);

        // ------------------------------------------------------------------------------
        // If the "Purchase" button was pressed, proceed to purchasing the product
        // with the selected card
        // ------------------------------------------------------------------------------
        alert.setPositiveButton("Purchase", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // ------------------------------------------------------------------------------
                // * Take the current item from the cards Spinner, if it's not the default one
                // * Convert it into a PTPVCard
                // ------------------------------------------------------------------------------
                if (mCardSpinner.getSelectedItem() != null
                        && mCardSpinner.getSelectedItemPosition() != 0) {
                    PTPVCard card = mCards.get(mCardSpinner.getSelectedItemPosition() - 1);

                    // ------------------------------------------------------------------------------
                    // Create a PTPVUser with the selected card.
                    // Executing a purchase requires a PTPVUser as a parameter
                    // ------------------------------------------------------------------------------
                    mClient.addUser(card, new PTPVCallbacks.AddUserResponse() {
                        @Override
                        public void returnAddUserError(PTPVError error) {
                            try {
                                Log.d("Execute purchase error", error.getError());
                                Toast.makeText(getApplicationContext(), error.getError(),
                                        Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void returnAddUserResponse(PTPVAddUser addUserResponse) {
                            final PTPVUser user = new PTPVUser(addUserResponse.getUserId(),
                                    addUserResponse.getUserToken());

                            // ------------------------------------------------------------------------------
                            // * Get the product's price from the ListView as a String
                            // * Convert it into a double, then multiply it by 100
                            // * Convert it back into an int (to round it), then into a String
                            // * Finally, create a PTPVMerchant object with the given parameters
                            // ------------------------------------------------------------------------------
                            TextView price = ((mProductsAdapter.getView(position - 1, null, null)
                                    .findViewById(R.id.product_price)));

                            String priceString = price.getText().toString();
                            Double priceDouble = Double.valueOf(priceString) * 100;
                            int priceInt = priceDouble.intValue();

                            PTPVMerchant merchant = new PTPVMerchant(String.valueOf(priceInt),
                                    getRandomString(), PTPVCurrency.EUR);

                            // ------------------------------------------------------------------------------
                            // Execute a purchase
                            // ------------------------------------------------------------------------------
                            mClient.executePurchase(addUserResponse, merchant,
                                    mProductsArray.get(position - 1),
                                    new PTPVCallbacks.PurchaseDetailsResponse() {
                                        @Override
                                        public void returnPurchaseDetailsError(PTPVError error) {
                                            try {
                                                Log.d("Execute purchase error", error.getError());
                                                Toast.makeText(getApplicationContext(),
                                                        error.getError(), Toast.LENGTH_SHORT)
                                                        .show();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void returnPurchaseDetailsResponse(
                                                PTPVPurchaseDetails executePurchaseResponse) {
                                            Log.d("Make purchase response",
                                                    executePurchaseResponse.toString());

                                            // ------------------------------------------------------------------------------
                                            // Add the purchase and it's corresponding user into the
                                            // HashMap
                                            // ------------------------------------------------------------------------------
                                            mPurchasesHash.put(executePurchaseResponse, user);

                                            // ------------------------------------------------------------------------------
                                            // * Add the purchase to the purchase ArrayList
                                            // * Notify the purchase ListView data was changed
                                            // ------------------------------------------------------------------------------
                                            mPurchases.add(executePurchaseResponse);
                                            mPurchasesAdapter.notifyDataSetChanged();

                                            Toast.makeText(getApplicationContext(),
                                                    "Purchase " + executePurchaseResponse.getOrder()
                                                            + " completed succesfully!",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    });
                }
            }
        });

        // ------------------------------------------------------------------------------
        // If the "Cancel" button was pressed, just close the alert dialog
        // ------------------------------------------------------------------------------
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert.show();
    }

    private void executeRefundDialog(int position) {

        // ------------------------------------------------------------------------------
        // Get the clicked purchase from the ArrayList
        // ------------------------------------------------------------------------------
        final PTPVPurchaseDetails purchase = mPurchases.get(position - 1);

        // ------------------------------------------------------------------------------
        // Initialize the Alert
        // ------------------------------------------------------------------------------
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        // ------------------------------------------------------------------------------
        // Configure the Alert
        // ------------------------------------------------------------------------------
        alert.setTitle("Refund for " + Double.valueOf(purchase.getAmount()) / 100 + " EUR");
        alert.setMessage("Refund the operation " + purchase.getOrder() + "?");

        // ------------------------------------------------------------------------------
        // Get the PTPVUser of the transaction from the HashMap
        // ------------------------------------------------------------------------------
        final PTPVUser user = mPurchasesHash.get(purchase);

        alert.setPositiveButton("Refund", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                mClient.executeRefund(user, purchase, new PTPVCallbacks.ExecuteRefundResponse() {
                    @Override
                    public void returnExecuteRefundError(PTPVError error) {
                        try {
                            Log.d("Execute refund error", error.getError());
                            Toast.makeText(getApplicationContext(), error.getError(),
                                    Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void returnExecuteRefundResponse(
                            PTPVExecuteRefund executeRefundResponse) {
                        Log.d("Execute refund response", executeRefundResponse.toString());

                        // ------------------------------------------------------------------------------
                        // * Remove the transaction from the ArrayList
                        // * Remove the transaction from the HashMap
                        // * Notify the purchase ListView
                        // ------------------------------------------------------------------------------
                        mPurchases.remove(purchase);
                        mPurchasesHash.remove(purchase);
                        mPurchasesAdapter.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext(),
                                "User " + user.getUserId() + " was refunded for "
                                        + Double.valueOf(purchase.getAmount()) / 100,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // ------------------------------------------------------------------------------
        // If the "Cancel" button was pressed, just close the alert dialog
        // ------------------------------------------------------------------------------
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert.show();
    }

    // ------------------------------------------------------------------------------
    // Generate a random string to use for the "Order" parameter (has to be unique)
    // ------------------------------------------------------------------------------
    private String getRandomString() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
    }
}
