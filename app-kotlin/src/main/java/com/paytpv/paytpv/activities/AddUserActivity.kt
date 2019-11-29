package com.paytpv.paytpv.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.*
import com.paytpv.androidsdk.Interfaces.PTPVCallbacks
import com.paytpv.androidsdk.Model.Basic.PTPVError
import com.paytpv.androidsdk.Model.PTPVRequests.PTPVRemoveResponse
import com.paytpv.androidsdk.Model.PTPVRequests.User.PTPVAddUser
import com.paytpv.androidsdk.Model.PTPVRequests.User.PTPVInfoUser
import com.paytpv.androidsdk.PTPVApiClient
import com.paytpv.paytpv.R
import com.paytpv.paytpv.utils.CardHandler
import com.paytpv.paytpv.utils.UserHandler


class AddUserActivity : AppCompatActivity() {

    // ------------------------------------------------------------------------------
    // Singleton for manipulating the cards ArrayList
    // ------------------------------------------------------------------------------
    private var mCards = CardHandler.instance

    // ------------------------------------------------------------------------------
    // Singleton for manipulating the users ArrayList
    // ------------------------------------------------------------------------------
    private var mUsers = UserHandler.instance

    private lateinit var mInfoUserId: TextView
    private lateinit var mInfoUserCardNumber: TextView
    private lateinit var mInfoUserCardBrand: TextView
    private lateinit var mInfoUserCardExpiryDate: TextView
    private lateinit var mInfoUserCardType: TextView

    private lateinit var mUserSpinner: Spinner
    private lateinit var mUserSpinnerAdapter: ArrayAdapter<String>
    private lateinit var mCardSpinner: Spinner
    private lateinit var mCardSpinnerAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        // ------------------------------------------------------------------------------
        // Get an instance of the PAYCOMET client
        // ------------------------------------------------------------------------------
        val client = PTPVApiClient.getInstance(this.applicationContext)

        // ------------------------------------------------------------------------------
        // Initialize views
        // ------------------------------------------------------------------------------
        mInfoUserId = findViewById(R.id.info_user_id) as TextView
        mInfoUserCardNumber = findViewById(R.id.info_user_card_number) as TextView
        mInfoUserCardBrand = findViewById(R.id.info_user_card_brand) as TextView
        mInfoUserCardExpiryDate = findViewById(R.id.info_user_card_expiry_date) as TextView
        mInfoUserCardType = findViewById(R.id.info_user_card_type) as TextView

        mUserSpinner = findViewById(R.id.users_spinner) as Spinner
        mUserSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item)
        mCardSpinner = findViewById(R.id.cards_spinner) as Spinner
        mCardSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item)

        // ------------------------------------------------------------------------------
        // Initialize the user Spinner
        // ------------------------------------------------------------------------------
        mUserSpinnerAdapter.add("Select user")
        mUserSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mUserSpinner.adapter = mUserSpinnerAdapter

        // ------------------------------------------------------------------------------
        // Initialize the card Spinner
        // ------------------------------------------------------------------------------
        mCardSpinnerAdapter.add("Select card")
        mCardSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mCardSpinner.adapter = mCardSpinnerAdapter

        // ------------------------------------------------------------------------------
        // Add all the saved cards to the cards Spinner
        // ------------------------------------------------------------------------------
        for (i in mCards.cardArray!!.indices) {
            mCardSpinnerAdapter.add(mCards.cardArray!![i].number)
        }

        // ------------------------------------------------------------------------------
        // Add all the saved users to the users Spinner
        // ------------------------------------------------------------------------------
        for (i in mUsers.userArray!!.indices) {
            mUserSpinnerAdapter.add(mUsers.userArray!![i].userId)
        }

        // ------------------------------------------------------------------------------
        // Set the Spinner selected item as the first one ("Select user/card")
        // ------------------------------------------------------------------------------
        mUserSpinner.setSelection(0)
        mCardSpinner.setSelection(0)

        // ------------------------------------------------------------------------------
        // If the "Add user" button was pressed,
        // ------------------------------------------------------------------------------
        val addUserButton = findViewById(R.id.add_user_button) as Button
        addUserButton.setOnClickListener {

            // ------------------------------------------------------------------------------
            // * Take the current item from the cards Spinner, if it's not the default one
            // * Convert it into a PTPVCard
            // ------------------------------------------------------------------------------
            if (mCardSpinner.selectedItem != null && mCardSpinner.selectedItemPosition != 0) {
                val card = mCards.cardArray!![mCardSpinner.selectedItemPosition-1]

                client.addUser(card, object: PTPVCallbacks.AddUserResponse {
                    override fun returnAddUserError(error: PTPVError?) {
                        Log.d("Add user error", error!!.error)
                        Toast.makeText(applicationContext, error.error, Toast.LENGTH_SHORT).show()
                    }

                    override fun returnAddUserResponse(addUserResponse: PTPVAddUser?) {
                        Log.d("Add user response", addUserResponse.toString())

                        // ------------------------------------------------------------------------------
                        // * Create a new PTPVUser
                        // * Add it to the users ArrayList
                        // * Add the user id to the cards Spinner
                        // * Notify the user Spinner new data was added
                        // ------------------------------------------------------------------------------
                        mUsers.userArray!!.add(addUserResponse!!)

                        mUserSpinnerAdapter.add(addUserResponse.userId)
                        mUserSpinnerAdapter.notifyDataSetChanged()

                        Toast.makeText(applicationContext, "User " + addUserResponse.userId + " created", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(applicationContext, "No card selected!", Toast.LENGTH_SHORT).show()
            }
        }

        // ------------------------------------------------------------------------------
        // If the "Info user" button was pressed,
        // ------------------------------------------------------------------------------
        val infoUserButton = findViewById(R.id.info_user_button) as Button
        infoUserButton.setOnClickListener {

            // ------------------------------------------------------------------------------
            // Clear the TextViews first
            // ------------------------------------------------------------------------------
            clearTextViews()

            // ------------------------------------------------------------------------------
            // * Take the current item from the users Spinner, if it's not the default one
            // * Convert it into a PTPVUser
            // ------------------------------------------------------------------------------
            if (mUserSpinner.selectedItem != null && mUserSpinner.selectedItemPosition != 0) {
                val user = mUsers.userArray!![mUserSpinner.selectedItemPosition-1]

                client.infoUser(user, object : PTPVCallbacks.InfoUserResponse {
                    override fun returnInfoUserError(error: PTPVError?) {
                        Log.d("Info user error", error!!.error)
                        Toast.makeText(applicationContext, error.error, Toast.LENGTH_SHORT).show()
                    }

                    override fun returnInfoUserResponse(infoUserResponse: PTPVInfoUser?) {
                        Log.d("Info user response", infoUserResponse.toString())

                        // ------------------------------------------------------------------------------
                        // Display information about the selected user
                        // ------------------------------------------------------------------------------
                        mInfoUserId.append(" " + user.userId)
                        mInfoUserCardNumber.append(" " + infoUserResponse!!.number)
                        mInfoUserCardBrand.append(" " + infoUserResponse.cardBrand)
                        mInfoUserCardExpiryDate.append(" " + infoUserResponse.expiryDate)
                        mInfoUserCardType.append(" " + infoUserResponse.cardType)
                    }
                })
            } else {
                Toast.makeText(applicationContext, "No user selected!", Toast.LENGTH_SHORT).show()
            }
        }

        // ------------------------------------------------------------------------------
        // If the "Remove user" button was pressed,
        // ------------------------------------------------------------------------------
        val removeUserButton = findViewById(R.id.remove_user_button) as Button
        removeUserButton.setOnClickListener {

            // ------------------------------------------------------------------------------
            // * Take the current item from the users Spinner, if it's not the default one
            // * Convert it into a PTPVUser
            // ------------------------------------------------------------------------------
            if (mUserSpinner.selectedItem != null && mUserSpinner.selectedItemPosition != 0) {
                val user = mUsers.userArray!![mUserSpinner.selectedItemPosition-1]

                client.removeUser(user, object : PTPVCallbacks.RemoveUserResponse {
                    override fun returnRemoveUserError(error: PTPVError?) {
                        Log.d("Remove user error", error!!.error)
                        Toast.makeText(applicationContext, error.error, Toast.LENGTH_SHORT).show()
                    }

                    override fun returnRemoveUserResponse(removeUserResponse: PTPVRemoveResponse?) {
                        Log.d("Remove user response", removeUserResponse.toString())
                        Toast.makeText(applicationContext, "User removed: " + user.userId, Toast.LENGTH_SHORT).show()

                        // ------------------------------------------------------------------------------
                        // * Remove the information about the selected user
                        // * Remove the user from the users ArrayList
                        // * Remove the user from the users Spinner
                        // * Notify the user Spinner new data was removed
                        // ------------------------------------------------------------------------------
                        clearTextViews()
                        mUsers.userArray!!.remove(user)
                        mUserSpinnerAdapter.remove(user.userId)
                        mUserSpinnerAdapter.notifyDataSetChanged()
                        mUserSpinner.setSelection(0)
                    }
                })
            } else {
                Toast.makeText(applicationContext, "No user selected!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // ------------------------------------------------------------------------------
    // Clears the TextViews by replacing their text with the original one
    // ------------------------------------------------------------------------------
    fun clearTextViews() {
        mInfoUserId.text = resources.getText(R.string.info_user_id)
        mInfoUserCardNumber.text = resources.getText(R.string.card_number)
        mInfoUserCardBrand.text = resources.getText(R.string.info_user_card_brand)
        mInfoUserCardExpiryDate.text = resources.getText(R.string.card_expiry)
        mInfoUserCardType.text = resources.getText(R.string.card_type)
    }
}
