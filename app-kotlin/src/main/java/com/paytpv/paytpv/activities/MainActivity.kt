package com.paytpv.paytpv.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.paytpv.androidsdk.Interfaces.PTPVCallbacks
import com.paytpv.androidsdk.Model.Basic.PTPVConfiguration
import com.paytpv.androidsdk.Model.Basic.PTPVError
import com.paytpv.androidsdk.Model.PTPVRequests.User.PTPVAddUser
import com.paytpv.androidsdk.PTPVApiClient
import com.paytpv.paytpv.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val CONFIG_CODE = "x95jmwbg"
    private val CONFIG_TERMINAL = "5549"
    private val CONFIG_PASSWORD = "trd6zfvcjgs71x43n982"
    private val CONFIG_JET = "yXpSxP8cH4d7Fq1h60zQrnLYCBf5NvMV"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ------------------------------------------------------------------------------
        // Get an instance of the PAYCOMET client
        // ------------------------------------------------------------------------------
        val mClient = PTPVApiClient.getInstance(applicationContext)
        val configuration = PTPVConfiguration.PTPVConfigurationBuilder(CONFIG_CODE, CONFIG_TERMINAL, CONFIG_PASSWORD)
                .setJetId(CONFIG_JET)
                .build()

        // ------------------------------------------------------------------------------
        // Set the client's configuration
        // ------------------------------------------------------------------------------
        mClient.setConfiguration(configuration)

        addListeners()
    }

    override fun onClick(v: View?) {
        // ------------------------------------------------------------------------------
        // Open the related activities
        // ------------------------------------------------------------------------------
        when (v!!.id) {
            R.id.add_card -> {
                intent = Intent(this, AddCardActivity::class.java)
                startActivity(intent)
            }
            R.id.add_user -> {
                intent = Intent(this, AddUserActivity::class.java)
                startActivity(intent)
            }
            R.id.add_user_token -> addUserWithToken()
            R.id.purchases -> {
                intent = Intent(this, PurchasesActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun addListeners() {
        val addCardButton = findViewById(R.id.add_card) as Button
        val addUserButton = findViewById(R.id.add_user) as Button
        val addUserTokenButton = findViewById(R.id.add_user_token) as Button
        val purchaseButton = findViewById(R.id.purchases) as Button

        addCardButton.setOnClickListener(this)
        addUserButton.setOnClickListener(this)
        addUserTokenButton.setOnClickListener(this)
        purchaseButton.setOnClickListener(this)
    }

    // ------------------------------------------------------------------------------
    // Open an AlertDialog for creating a new user with a PAYCOMET token
    // ------------------------------------------------------------------------------
    private fun addUserWithToken() {
        val client = PTPVApiClient.getInstance(applicationContext)

        val alert = AlertDialog.Builder(this)
        val editText = EditText(this)

        // ------------------------------------------------------------------------------
        // Configure the Alert
        // ------------------------------------------------------------------------------
        alert.setTitle("Create user with token")
        alert.setMessage("Enter the PAYCOMET token:")
        alert.setView(editText)

        // ------------------------------------------------------------------------------
        // If the "Create" button was pressed, proceed to creating a new user with the
        // corresponding PAYCOMET token
        // ------------------------------------------------------------------------------
        alert.setPositiveButton("Create", { _, _ ->
            client.addUserToken(editText.text.toString(), object : PTPVCallbacks.AddUserResponse {
                override fun returnAddUserError(error: PTPVError?) {
                    Log.d("Add user error", error!!.error)
                    Toast.makeText(applicationContext, error.error, Toast.LENGTH_SHORT).show()
                }

                override fun returnAddUserResponse(addUserResponse: PTPVAddUser?) {
                    Log.d("Add user response", addUserResponse.toString())
                    Toast.makeText(applicationContext, addUserResponse.toString(), Toast.LENGTH_SHORT).show()
                }

            })
        })

        // ------------------------------------------------------------------------------
        // If the "Cancel" button was pressed, just close the alert dialog
        // ------------------------------------------------------------------------------
        alert.setNegativeButton("Cancel", { _, _ -> })

        alert.show()
    }
}

