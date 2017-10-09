
package com.paytpv.appjava.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.paytpv.androidsdk.Model.Basic.PTPVCard;
import com.paytpv.appjava.R;
import com.paytpv.appjava.utils.CardHandler;

import java.util.ArrayList;

public class AddCardActivity extends AppCompatActivity {

    // ------------------------------------------------------------------------------
    // Singleton for manipulating the cards ArrayList
    // ------------------------------------------------------------------------------
    private ArrayList<PTPVCard> mCards = CardHandler.getInstance().getCardArray();

    private Spinner mCardSpinner;
    private ArrayAdapter<String> mCardSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        mCardSpinner = (Spinner) findViewById(R.id.cards_spinner);
        mCardSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        // ------------------------------------------------------------------------------
        // Initialize the card Spinner
        // ------------------------------------------------------------------------------
        mCardSpinnerAdapter.add("List of cards");
        mCardSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCardSpinner.setAdapter(mCardSpinnerAdapter);

        // ------------------------------------------------------------------------------
        // Add all the saved cards to the cards Spinner
        // ------------------------------------------------------------------------------
        for (int i = 0; i < mCards.size(); i++) {
            mCardSpinnerAdapter.add(mCards.get(i).getNumber());
        }

        // ------------------------------------------------------------------------------
        // Set the Spinner selected item as the first one ("List of cards")
        // ------------------------------------------------------------------------------
        mCardSpinner.setSelection(0);

        // ------------------------------------------------------------------------------
        // If the "Add card" button was pressed,
        // ------------------------------------------------------------------------------
        Button addCardButton = (Button) findViewById(R.id.add_card_button);
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ------------------------------------------------------------------------------
                // Get info about the card from the EditTexts
                // ------------------------------------------------------------------------------
                String cardNumber = ((EditText) findViewById(R.id.card_number_edit_text)).getText()
                        .toString();
                String cardExpiryMonth = ((EditText) findViewById(R.id.exp_month_edit_text))
                        .getText().toString();
                String cardExpiryYear = ((EditText) findViewById(R.id.exp_year_edit_text)).getText()
                        .toString();
                String cardCVC = ((EditText) findViewById(R.id.card_cvc_edit_text)).getText()
                        .toString();

                // ------------------------------------------------------------------------------
                // If the fields are valid (not empty, for now), create a new card
                // ------------------------------------------------------------------------------
                if (!cardNumber.isEmpty() && !cardExpiryMonth.isEmpty() && !cardExpiryYear.isEmpty()
                        && !cardCVC.isEmpty()) {
                    PTPVCard card = new PTPVCard(cardNumber, cardExpiryMonth + cardExpiryYear,
                            cardCVC);

                    // ------------------------------------------------------------------------------
                    // Check if the card already exists in the Array
                    // ------------------------------------------------------------------------------
                    Boolean alreadyExists = false;
                    for (int i = 0; i < mCards.size(); i++) {
                        if (card.getNumber().equals(mCards.get(i).getNumber())) {
                            alreadyExists = true;
                        }
                    }

                    // ------------------------------------------------------------------------------
                    // * If the card doesn't exist, add it to the cards ArrayList
                    // * Add the card number to the cards Spinner
                    // * Notify the card Spinner new data was added
                    // ------------------------------------------------------------------------------
                    if (!alreadyExists) {
                        mCards.add(card);
                        mCardSpinnerAdapter.add(card.getNumber());
                        mCardSpinnerAdapter.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext(), "Card added to the list!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "The card already exists!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "One or more fields are empty/incorrect!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ------------------------------------------------------------------------------
        // If the "Remove card" button was pressed,
        // ------------------------------------------------------------------------------
        Button removeUserButton = (Button) findViewById(R.id.remove_card_button);
        removeUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ------------------------------------------------------------------------------
                // * Take the current item from the cards Spinner, if it's not the default one
                // * Convert it into a PTPVCard
                // ------------------------------------------------------------------------------
                if (mCardSpinner.getSelectedItem() != null
                        && mCardSpinner.getSelectedItemPosition() != 0) {
                    PTPVCard card = mCards.get(mCardSpinner.getSelectedItemPosition() - 1);

                    // ------------------------------------------------------------------------------
                    // * Remove the information about the selected card
                    // * Remove the card from the cards ArrayList
                    // * Remove the card from the cards Spinner
                    // * Notify the Spinner new data was removed
                    // ------------------------------------------------------------------------------
                    mCards.remove(card);
                    mCardSpinnerAdapter.remove(card.getNumber());
                    mCardSpinnerAdapter.notifyDataSetChanged();
                    mCardSpinner.setSelection(0);

                    Toast.makeText(getApplicationContext(), "Card removed: " + card.getNumber(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No card selected!", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}
