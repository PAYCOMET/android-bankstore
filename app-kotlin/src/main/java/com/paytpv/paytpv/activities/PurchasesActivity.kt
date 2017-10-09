package com.paytpv.paytpv.activities

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import com.paytpv.androidsdk.Interfaces.PTPVCallbacks
import com.paytpv.androidsdk.Model.Basic.PTPVCurrency
import com.paytpv.androidsdk.Model.Basic.PTPVError
import com.paytpv.androidsdk.Model.Basic.PTPVMerchant
import com.paytpv.androidsdk.Model.Basic.PTPVUser
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVExecuteRefund
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVPurchaseDetails
import com.paytpv.androidsdk.Model.PTPVRequests.User.PTPVAddUser
import com.paytpv.androidsdk.PTPVApiClient
import com.paytpv.paytpv.R
import com.paytpv.paytpv.adapters.ProductAdapter
import com.paytpv.paytpv.adapters.PurchaseAdapter
import com.paytpv.paytpv.utils.CardHandler
import com.paytpv.paytpv.utils.Products
import com.paytpv.paytpv.utils.PurchaseHandler
import java.util.*
import kotlin.collections.HashMap

class PurchasesActivity : AppCompatActivity() {

    // ------------------------------------------------------------------------------
    // Singleton for manipulating the cards ArrayList
    // ------------------------------------------------------------------------------
    private var mCards = CardHandler.instance

    // ------------------------------------------------------------------------------
    // Singleton for manipulating the purchases ArrayList
    // ------------------------------------------------------------------------------
    private var mPurchases = PurchaseHandler.instance

    // ------------------------------------------------------------------------------
    // HashMap for storing the purchases with their users
    // ------------------------------------------------------------------------------
    private lateinit var mPurchasesHash: HashMap<PTPVPurchaseDetails, PTPVUser>

    private lateinit var mProductsAdapter: ProductAdapter
    private lateinit var mPurchasesAdapter: PurchaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchases)

        // ------------------------------------------------------------------------------
        // Initialize products ListView
        // ------------------------------------------------------------------------------
        val productsListView = findViewById(R.id.products_list_view) as ListView
        val productsHeader = LayoutInflater.from(this).inflate(R.layout.list_view_products_header, productsListView, false)
        mProductsAdapter = ProductAdapter(this, Products().productsArray)

        productsListView.adapter = mProductsAdapter
        productsListView.addHeaderView(productsHeader, null, false)

        // ------------------------------------------------------------------------------
        // Initialize purchases ListView
        // ------------------------------------------------------------------------------
        val purchasesListView = findViewById(R.id.purchases_list_view) as ListView
        val purchasesHeader = LayoutInflater.from(this).inflate(R.layout.list_view_purchases_header, productsListView, false)
        mPurchasesAdapter = PurchaseAdapter(this, mPurchases.purchaseArray!!)

        purchasesListView.adapter = mPurchasesAdapter
        purchasesListView.addHeaderView(purchasesHeader, null, false)

        // ------------------------------------------------------------------------------
        // Initialize the HashMap
        // ------------------------------------------------------------------------------
        mPurchasesHash = HashMap()

        // ------------------------------------------------------------------------------
        // Listener for clicking on a product
        // ------------------------------------------------------------------------------
        productsListView.setOnItemClickListener { _, _, position, _ ->
            executePurchaseDialog(position)
        }

        // ------------------------------------------------------------------------------
        // Listener for clicking on a purchase
        // ------------------------------------------------------------------------------
        purchasesListView.setOnItemClickListener { _, _, position, _ ->
            executeRefundDialog(position)
        }
    }

    private fun executePurchaseDialog(position: Int) {

        // ------------------------------------------------------------------------------
        // Get an instance to the client for executing operations
        // ------------------------------------------------------------------------------
        val client = PTPVApiClient.getInstance(this)

        // ------------------------------------------------------------------------------
        // Initialize the Alert
        // ------------------------------------------------------------------------------
        val alert = AlertDialog.Builder(this)
        val purchaseView = this.layoutInflater.inflate(R.layout.purchase_dialog, null)

        // ------------------------------------------------------------------------------
        // Initialize the user Spinner
        // ------------------------------------------------------------------------------
        val mCardSpinner = purchaseView.findViewById(R.id.registered_cards_spinner) as Spinner
        val mCardSpinnerAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item)

        mCardSpinnerAdapter.add("Select card")
        mCardSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mCardSpinner.adapter = mCardSpinnerAdapter

        // ------------------------------------------------------------------------------
        // Add all the saved users to the users Spinner
        // ------------------------------------------------------------------------------
        for (i in mCards.cardArray!!.indices) {
            mCardSpinnerAdapter.add(mCards.cardArray!![i].number)
        }

        // ------------------------------------------------------------------------------
        // Configure the Alert
        // ------------------------------------------------------------------------------
        alert.setTitle("Purchase " + Products().productsArray[position - 1].description)
        alert.setMessage("Choose the card for the operation:")
        alert.setView(purchaseView)

        // ------------------------------------------------------------------------------
        // If the "Purchase" button was pressed, proceed to purchasing the product
        // with the selected card
        // ------------------------------------------------------------------------------
        alert.setPositiveButton("Purchase", { _, _ ->

            // ------------------------------------------------------------------------------
            // * Take the current item from the cards Spinner, if it's not the default one
            // * Convert it into a PTPVCard
            // ------------------------------------------------------------------------------
            if (mCardSpinner.selectedItem != null && mCardSpinner.selectedItemPosition != 0) {
                val card = mCards.cardArray!![mCardSpinner.selectedItemPosition - 1]

                // ------------------------------------------------------------------------------
                // Create a PTPVUser with the selected card.
                // Executing a purchase requires a PTPVUser as a parameter
                // ------------------------------------------------------------------------------
                client.addUser(card, object : PTPVCallbacks.AddUserResponse {
                    override fun returnAddUserResponse(addUserResponse: PTPVAddUser?) {

                        // ------------------------------------------------------------------------------
                        // * Get the product's price from the ListView as a String
                        // * Convert it into a double, then multiply it by 100
                        // * Convert it back into an int (to round it), then into a String
                        // * Finally, create a PTPVMerchant object with the given parameters
                        // ------------------------------------------------------------------------------
                        val price = ((mProductsAdapter.getView(position - 1, null, null)
                                .findViewById(R.id.product_price) as TextView)
                                .text.toString().toDouble() * 100).toInt().toString()
                        val merchant = PTPVMerchant(price, getRandomString(), PTPVCurrency.EUR)

                        // ------------------------------------------------------------------------------
                        // Execute a purchase
                        // ------------------------------------------------------------------------------
                        client.executePurchase(addUserResponse, merchant, Products().productsArray[position - 1],
                                object : PTPVCallbacks.PurchaseDetailsResponse {
                                    override fun returnPurchaseDetailsResponse(executePurchaseResponse: PTPVPurchaseDetails?) {
                                        Log.d("Make purchase response", executePurchaseResponse.toString())

                                        // ------------------------------------------------------------------------------
                                        // Add the purchase and it's corresponding user into the HashMap
                                        // ------------------------------------------------------------------------------
                                        mPurchasesHash.put(executePurchaseResponse!!, addUserResponse!!)

                                        // ------------------------------------------------------------------------------
                                        // * Add the purchase to the purchase ArrayList
                                        // * Notify the purchase ListView data was changed
                                        // ------------------------------------------------------------------------------
                                        mPurchases.purchaseArray!!.add(executePurchaseResponse)
                                        mPurchasesAdapter.notifyDataSetChanged()


                                        Toast.makeText(applicationContext, "Purchase " + executePurchaseResponse.order + " completed succesfully!", Toast.LENGTH_SHORT).show()
                                    }

                                    override fun returnPurchaseDetailsError(error: PTPVError?) {
                                        Log.d("Execute purchase error", error!!.error)
                                        Toast.makeText(applicationContext, error.error, Toast.LENGTH_SHORT).show()
                                    }
                                })
                    }

                    override fun returnAddUserError(error: PTPVError?) {
                        Log.d("Execute purchase error", error!!.error)
                        Toast.makeText(applicationContext, error.error, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })

        // ------------------------------------------------------------------------------
        // If the "Cancel" button was pressed, just close the alert dialog
        // ------------------------------------------------------------------------------
        alert.setNegativeButton("Cancel", { _, _ -> })

        alert.show()
    }

    private fun executeRefundDialog(position: Int) {

        // ------------------------------------------------------------------------------
        // Get the clicked purchase from the ArrayList
        // ------------------------------------------------------------------------------
        val purchase = mPurchases.purchaseArray!![position - 1]

        // ------------------------------------------------------------------------------
        // Get an instance to the client for executing operations
        // ------------------------------------------------------------------------------
        val client = PTPVApiClient.getInstance(this)

        // ------------------------------------------------------------------------------
        // Initialize the Alert
        // ------------------------------------------------------------------------------
        val alert = AlertDialog.Builder(this)

        // ------------------------------------------------------------------------------
        // Configure the Alert
        // ------------------------------------------------------------------------------
        alert.setTitle("Refund for " + purchase.amount.toDouble()/100 + " EUR")
        alert.setMessage("Refund the operation " + purchase.order + "?")

        // ------------------------------------------------------------------------------
        // Get the PTPVUser of the transaction from the HashMap
        // ------------------------------------------------------------------------------
        val user = mPurchasesHash[purchase]

        alert.setPositiveButton("Refund", { _, _ ->
            client.executeRefund(user, purchase, object: PTPVCallbacks.ExecuteRefundResponse {
                override fun returnExecuteRefundResponse(executeRefundResponse: PTPVExecuteRefund?) {
                    Log.d("Execute refund response", executeRefundResponse.toString())

                    // ------------------------------------------------------------------------------
                    // * Remove the transaction from the ArrayList
                    // * Remove the transaction from the HashMap
                    // * Notify the purchase ListView
                    // ------------------------------------------------------------------------------
                    mPurchases.purchaseArray!!.remove(purchase)
                    mPurchasesHash.remove(purchase)
                    mPurchasesAdapter.notifyDataSetChanged()

                    Toast.makeText(applicationContext, "User " + user!!.userId + " was refunded for " + purchase.amount.toDouble()/100 , Toast.LENGTH_SHORT).show()
                }

                override fun returnExecuteRefundError(error: PTPVError?) {
                    Log.d("Execute refund error", error!!.error)
                    Toast.makeText(applicationContext, error.error, Toast.LENGTH_SHORT).show()
                }
            })
        })

        // ------------------------------------------------------------------------------
        // If the "Cancel" button was pressed, just close the alert dialog
        // ------------------------------------------------------------------------------
        alert.setNegativeButton("Cancel", { _, _ -> })

        alert.show()
    }

    // ------------------------------------------------------------------------------
    // Generate a random string to use for the "Order" parameter (has to be unique)
    // ------------------------------------------------------------------------------
    private fun getRandomString(): String {
        val chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray()
        val sb = StringBuilder()
        val random = Random()

        for (i in 0..19) {
            val c = chars[random.nextInt(chars.size)]
            sb.append(c)
        }

        return sb.toString()
    }
}
