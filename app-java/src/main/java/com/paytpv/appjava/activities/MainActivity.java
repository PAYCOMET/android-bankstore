
package com.paytpv.appjava.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.paytpv.androidsdk.Interfaces.PTPVCallbacks;
import com.paytpv.androidsdk.Model.Basic.PTPVConfiguration;
import com.paytpv.androidsdk.Model.Basic.PTPVError;
import com.paytpv.androidsdk.Model.PTPVRequests.User.PTPVAddUser;
import com.paytpv.androidsdk.PTPVApiClient;
import com.paytpv.appjava.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String CONFIG_CODE = "x95jmwbg";
    private final String CONFIG_TERMINAL = "5549";
    private final String CONFIG_PASSWORD = "trd6zfvcjgs71x43n982";
    private final String CONFIG_JET = "yXpSxP8cH4d7Fq1h60zQrnLYCBf5NvMV";

    private PTPVApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ------------------------------------------------------------------------------
        // Get an instance of the PAYTPV client
        // ------------------------------------------------------------------------------
        this.mClient = PTPVApiClient.getInstance(this.getApplicationContext());
        PTPVConfiguration configuration = new PTPVConfiguration.PTPVConfigurationBuilder(
                CONFIG_CODE, CONFIG_TERMINAL, CONFIG_PASSWORD)
                        .setJetId(CONFIG_JET)
                        .build();

        // ------------------------------------------------------------------------------
        // Set the client's configuration
        // ------------------------------------------------------------------------------
        this.mClient.setConfiguration(configuration);

        addListeners();
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        // ------------------------------------------------------------------------------
        // Open the related activities
        // ------------------------------------------------------------------------------
        int id = view.getId();
        if (id == R.id.add_card) {
            intent = new Intent(this, AddCardActivity.class);
            startActivity(intent);
        } else if (id == R.id.add_user) {
            intent = new Intent(this, AddUserActivity.class);
            startActivity(intent);
        } else if (id == R.id.add_user_token) {
            addUserWithToken();
        } else if (id == R.id.purchases) {
            intent = new Intent(this, PurchasesActivity.class);
            startActivity(intent);
        }
    }

    private void addListeners() {
        Button addCardButton = (Button) findViewById(R.id.add_card);
        Button addUserButton = (Button) findViewById(R.id.add_user);
        Button addUserTokenButton = (Button) findViewById(R.id.add_user_token);
        Button purchaseButton = (Button) findViewById(R.id.purchases);

        addCardButton.setOnClickListener(this);
        addUserButton.setOnClickListener(this);
        addUserTokenButton.setOnClickListener(this);
        purchaseButton.setOnClickListener(this);
    }

    // ------------------------------------------------------------------------------
    // Open an AlertDialog for creating a new user with a PAYTPV token
    // ------------------------------------------------------------------------------
    private void addUserWithToken() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText editText = new EditText(this);

        // ------------------------------------------------------------------------------
        // Configure the Alert
        // ------------------------------------------------------------------------------
        alert.setTitle("Create user with token");
        alert.setMessage("Enter the PAYTPV token:");
        alert.setView(editText);

        // ------------------------------------------------------------------------------
        // If the "Create" button was pressed, proceed to creating a new user with the
        // corresponding PAYTPV token
        // ------------------------------------------------------------------------------
        alert.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mClient.addUserToken(editText.getText().toString(),
                        new PTPVCallbacks.AddUserResponse() {

                            @Override
                            public void returnAddUserError(PTPVError error) {
                                try {
                                    Log.d("Add user error", error.getError());
                                    Toast.makeText(getApplicationContext(), error.getError(),
                                            Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void returnAddUserResponse(PTPVAddUser addUserResponse) {
                                Log.d("Add user response", addUserResponse.toString());
                                Toast.makeText(getApplicationContext(), addUserResponse.toString(),
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
}
