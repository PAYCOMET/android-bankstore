package com.paytpv.appjava.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.paytpv.androidsdk.Interfaces.PTPVCallbacks;
import com.paytpv.androidsdk.Model.Basic.PTPVCard;
import com.paytpv.androidsdk.Model.Basic.PTPVError;
import com.paytpv.androidsdk.Model.Basic.PTPVUser;
import com.paytpv.androidsdk.Model.PTPVRequests.PTPVRemoveResponse;
import com.paytpv.androidsdk.Model.PTPVRequests.User.PTPVAddUser;
import com.paytpv.androidsdk.Model.PTPVRequests.User.PTPVInfoUser;
import com.paytpv.androidsdk.PTPVApiClient;
import com.paytpv.appjava.R;
import com.paytpv.appjava.utils.CardHandler;
import com.paytpv.appjava.utils.UserHandler;

import java.util.ArrayList;

public class AddUserActivity extends AppCompatActivity {

    // ------------------------------------------------------------------------------
    // Singleton for manipulating the cards ArrayList
    // ------------------------------------------------------------------------------
    private ArrayList<PTPVCard> mCards = CardHandler.getInstance().getCardArray();

    // ------------------------------------------------------------------------------
    // Singleton for manipulating the users ArrayList
    // ------------------------------------------------------------------------------
    private ArrayList<PTPVUser> mUsers = UserHandler.getInstance().getUserArray();

    private TextView mInfoUserId;
    private TextView mInfoUserCardNumber;
    private TextView mInfoUserCardBrand;
    private TextView mInfoUserCardExpiryDate;
    private TextView mInfoUserCardType;

    private Spinner mUserSpinner;
    private ArrayAdapter<String> mUserSpinnerAdapter;
    private Spinner mCardSpinner;
    private ArrayAdapter<String> mCardSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        // ------------------------------------------------------------------------------
        // Get an instance of the PAYTPV client
        // ------------------------------------------------------------------------------
        final PTPVApiClient client = PTPVApiClient.getInstance(this.getApplicationContext());

        // ------------------------------------------------------------------------------
        // Initialize views
        // ------------------------------------------------------------------------------
        mInfoUserId = (TextView) findViewById(R.id.info_user_id);
        mInfoUserCardNumber = (TextView) findViewById(R.id.info_user_card_number);
        mInfoUserCardBrand = (TextView) findViewById(R.id.info_user_card_brand);
        mInfoUserCardExpiryDate = (TextView) findViewById(R.id.info_user_card_expiry_date);
        mInfoUserCardType = (TextView) findViewById(R.id.info_user_card_type);

        mUserSpinner = (Spinner) findViewById(R.id.users_spinner);
        mUserSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        mCardSpinner = (Spinner) findViewById(R.id.cards_spinner);
        mCardSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        // ------------------------------------------------------------------------------
        // Initialize the user Spinner
        // ------------------------------------------------------------------------------
        mUserSpinnerAdapter.add("Select user");
        mUserSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mUserSpinner.setAdapter(mUserSpinnerAdapter);

        // ------------------------------------------------------------------------------
        // Initialize the card Spinner
        // ------------------------------------------------------------------------------
        mCardSpinnerAdapter.add("Select card");
        mCardSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCardSpinner.setAdapter(mCardSpinnerAdapter);

        // ------------------------------------------------------------------------------
        // Add all the saved cards to the cards Spinner
        // ------------------------------------------------------------------------------
        for (int i=0; i<mCards.size();i++) {
            mCardSpinnerAdapter.add(mCards.get(i).getNumber());
        }

        // ------------------------------------------------------------------------------
        // Add all the saved users to the users Spinner
        // ------------------------------------------------------------------------------
        for (int i=0; i<mUsers.size();i++) {
            mUserSpinnerAdapter.add(mUsers.get(i).getUserId());
        }

        // ------------------------------------------------------------------------------
        // Set the Spinner selected item as the first one ("Select user/card")
        // ------------------------------------------------------------------------------
        mUserSpinner.setSelection(0);
        mCardSpinner.setSelection(0);

        // ------------------------------------------------------------------------------
        // If the "Add user" button was pressed,
        // ------------------------------------------------------------------------------
        Button addUserButton = (Button) findViewById(R.id.add_user_button);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ------------------------------------------------------------------------------
                // * Take the current item from the cards Spinner, if it's not the default one
                // * Convert it into a PTPVCard
                // ------------------------------------------------------------------------------
                if (mCardSpinner.getSelectedItem() != null && mCardSpinner.getSelectedItemPosition() != 0) {
                    PTPVCard card = mCards.get(mCardSpinner.getSelectedItemPosition()-1);

                    client.addUser(card, new PTPVCallbacks.AddUserResponse() {


                        @Override
                        public void returnAddUserError(PTPVError error) {
                            try {
                                Log.d("Add user error", error.getError());
                                Toast.makeText(getApplicationContext(), error.getError(), Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void returnAddUserResponse(PTPVAddUser addUserResponse) {
                            Log.d("Add user response", addUserResponse.toString());

                            // ------------------------------------------------------------------------------
                            // * Create a new PTPVUser
                            // * Add it to the users ArrayList
                            // * Add the user id to the cards Spinner
                            // * Notify the user Spinner new data was added
                            // ------------------------------------------------------------------------------
                            mUsers.add(addUserResponse);

                            mUserSpinnerAdapter.add(addUserResponse.getUserId());
                            mUserSpinnerAdapter.notifyDataSetChanged();

                            Toast.makeText(getApplicationContext(), "User " + addUserResponse.getUserId() + " created", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "No card selected!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ------------------------------------------------------------------------------
        // If the "Info user" button was pressed,
        // ------------------------------------------------------------------------------
        Button infoUserButton = (Button) findViewById(R.id.info_user_button);
        infoUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ------------------------------------------------------------------------------
                // Clear the TextViews first
                // ------------------------------------------------------------------------------
                clearTextViews();

                // ------------------------------------------------------------------------------
                // * Take the current item from the users Spinner, if it's not the default one
                // * Convert it into a PTPVUser
                // ------------------------------------------------------------------------------
                if (mUserSpinner.getSelectedItem() != null && mUserSpinner.getSelectedItemPosition() != 0) {
                    final PTPVUser user = mUsers.get(mUserSpinner.getSelectedItemPosition()-1);

                    client.infoUser(user, new PTPVCallbacks.InfoUserResponse() {
                        @Override
                        public void returnInfoUserError(PTPVError error) {
                            try {
                                Log.d("Info user error", error.getError());
                                Toast.makeText(getApplicationContext(), error.getError(), Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void returnInfoUserResponse(PTPVInfoUser infoUserResponse) {
                            Log.d("Info user response", infoUserResponse.toString());

                            // ------------------------------------------------------------------------------
                            // Display information about the selected user
                            // ------------------------------------------------------------------------------
                            mInfoUserId.append(" " + user.getUserId());
                            mInfoUserCardNumber.append(" " + infoUserResponse.getNumber());
                            mInfoUserCardBrand.append(" " + infoUserResponse.getCardBrand());
                            mInfoUserCardExpiryDate.append(" " + infoUserResponse.getExpiryDate());
                            mInfoUserCardType.append(" " + infoUserResponse.getCardType());
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "No user selected!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ------------------------------------------------------------------------------
        // If the "Remove user" button was pressed,
        // ------------------------------------------------------------------------------
        Button removeUserButton = (Button) findViewById(R.id.remove_user_button);
        removeUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ------------------------------------------------------------------------------
                // * Take the current item from the users Spinner, if it's not the default one
                // * Convert it into a PTPVUser
                // ------------------------------------------------------------------------------
                if (mUserSpinner.getSelectedItem() != null && mUserSpinner.getSelectedItemPosition() != 0) {
                    final PTPVUser user = mUsers.get(mUserSpinner.getSelectedItemPosition() - 1);

                    client.removeUser(user, new PTPVCallbacks.RemoveUserResponse() {
                        @Override
                        public void returnRemoveUserError(PTPVError error) {

                            try {
                                Log.d("Remove user error", error.getError());
                                Toast.makeText(getApplicationContext(), error.getError(), Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void returnRemoveUserResponse(PTPVRemoveResponse removeUserResponse) {
                            Log.d("Remove user response", removeUserResponse.toString());
                            Toast.makeText(getApplicationContext(), "User removed: " + user.getUserId(), Toast.LENGTH_SHORT).show();

                            // ------------------------------------------------------------------------------
                            // * Remove the information about the selected user
                            // * Remove the user from the users ArrayList
                            // * Remove the user from the users Spinner
                            // * Notify the user Spinner new data was removed
                            // ------------------------------------------------------------------------------
                            clearTextViews();
                            mUsers.remove(user);
                            mUserSpinnerAdapter.remove(user.getUserId());
                            mUserSpinnerAdapter.notifyDataSetChanged();
                            mUserSpinner.setSelection(0);
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "No user selected!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // ------------------------------------------------------------------------------
    // Clears the TextViews by replacing their text with the original one
    // ------------------------------------------------------------------------------
    private void clearTextViews() {
        mInfoUserId.setText(getResources().getText(R.string.info_user_id));
        mInfoUserCardNumber.setText(getResources().getText(R.string.card_number));
        mInfoUserCardBrand.setText(getResources().getText(R.string.info_user_card_brand));
        mInfoUserCardExpiryDate.setText(getResources().getText(R.string.card_expiry));
        mInfoUserCardType.setText(getResources().getText(R.string.card_type));
    }
}
