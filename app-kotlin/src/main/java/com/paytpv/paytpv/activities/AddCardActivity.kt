package com.paytpv.paytpv.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.paytpv.androidsdk.Model.Basic.PTPVCard
import com.paytpv.paytpv.R
import com.paytpv.paytpv.utils.CardHandler

class AddCardActivity : AppCompatActivity() {

    // ------------------------------------------------------------------------------
    // Singleton for manipulating the cards ArrayList
    // ------------------------------------------------------------------------------
    private var mCards = CardHandler.instance

    private lateinit var mCardSpinner: Spinner
    private lateinit var mCardSpinnerAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        mCardSpinner = findViewById(R.id.cards_spinner) as Spinner
        mCardSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item)

        // ------------------------------------------------------------------------------
        // Initialize the card Spinner
        // ------------------------------------------------------------------------------
        mCardSpinnerAdapter.add("List of cards")
        mCardSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mCardSpinner.adapter = mCardSpinnerAdapter

        // ------------------------------------------------------------------------------
        // Add all the saved cards to the cards Spinner
        // ------------------------------------------------------------------------------
        for (i in mCards.cardArray!!.indices) {
            mCardSpinnerAdapter.add(mCards.cardArray!![i].number)
        }

        // ------------------------------------------------------------------------------
        // Set the Spinner selected item as the first one ("List of cards")
        // ------------------------------------------------------------------------------
        mCardSpinner.setSelection(0)

        // ------------------------------------------------------------------------------
        // If the "Add card" button was pressed,
        // ------------------------------------------------------------------------------
        val addCardButton = findViewById(R.id.add_card_button) as Button
        addCardButton.setOnClickListener {

            // ------------------------------------------------------------------------------
            // Get info about the card from the EditTexts
            // ------------------------------------------------------------------------------
            val cardNumber = (findViewById(R.id.card_number_edit_text) as EditText).text
            val cardExpiryMonth = (findViewById(R.id.exp_month_edit_text) as EditText).text
            val cardExpiryYear = (findViewById(R.id.exp_year_edit_text) as EditText).text
            val cardCVC = (findViewById(R.id.card_cvc_edit_text) as EditText).text

            // ------------------------------------------------------------------------------
            // If the fields are valid (not empty, for now), create a new card
            // ------------------------------------------------------------------------------
            if (!cardNumber.isEmpty() && !cardExpiryMonth.isEmpty() && !cardExpiryYear.isEmpty() && !cardCVC.isEmpty()) {
                val card = PTPVCard(cardNumber.toString(), cardExpiryMonth.toString() + cardExpiryYear.toString(), cardCVC.toString())

                // ------------------------------------------------------------------------------
                // Check if the card already exists in the Array
                // ------------------------------------------------------------------------------
                val alreadyExists = mCards.cardArray!!.indices.any { mCards.cardArray!![it].number == card.number }

                // ------------------------------------------------------------------------------
                // * If the card doesn't exist, add it to the cards ArrayList
                // * Add the card number to the cards Spinner
                // * Notify the card Spinner new data was added
                // ------------------------------------------------------------------------------
                if (!alreadyExists) {
                    mCards.cardArray!!.add(card)
                    mCardSpinnerAdapter.add(card.number)
                    mCardSpinnerAdapter.notifyDataSetChanged()

                    Toast.makeText(applicationContext, "Card added to the list!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "The card already exists!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "One or more fields are empty/incorrect!", Toast.LENGTH_SHORT).show()
            }
        }

        // ------------------------------------------------------------------------------
        // If the "Remove card" button was pressed,
        // ------------------------------------------------------------------------------
        val removeUserButton = findViewById(R.id.remove_card_button) as Button
        removeUserButton.setOnClickListener {

            // ------------------------------------------------------------------------------
            // * Take the current item from the cards Spinner, if it's not the default one
            // * Convert it into a PTPVCard
            // ------------------------------------------------------------------------------
            if (mCardSpinner.selectedItem != null && mCardSpinner.selectedItemPosition != 0) {
                val card = mCards.cardArray!![mCardSpinner.selectedItemPosition - 1]

                // ------------------------------------------------------------------------------
                // * Remove the information about the selected card
                // * Remove the card from the cards ArrayList
                // * Remove the card from the cards Spinner
                // * Notify the Spinner new data was removed
                // ------------------------------------------------------------------------------
                mCards.cardArray!!.remove(card)
                mCardSpinnerAdapter.remove(card.number)
                mCardSpinnerAdapter.notifyDataSetChanged()
                mCardSpinner.setSelection(0)

                Toast.makeText(applicationContext, "Card removed: " + card.number, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "No card selected!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
