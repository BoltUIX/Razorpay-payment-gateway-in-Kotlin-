package com.win.razorpay

import android.app.Activity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.win.razorpay.databinding.ActivityMainBinding
import org.json.JSONObject
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), PaymentResultListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            savePayments(5f,this)
        }
    }


    private fun savePayments(amount: Float, context: Activity) {
        val checkout = Checkout()
        checkout.setKeyID("rzp_live_7rk7sJYf7JnVOk")
        try {
            // rounding off the amount.
            val amountValue = (amount * 100).roundToInt()

            val options = JSONObject()
            options.put("name", "Shopping Cart")  // to put name
            options.put(
                "description",
                "Quality products at affordable price."
            )     // put description
            options.put("theme.color", "#1F4FE0") // to set theme color
            options.put("currency", "INR") // put the currency
            options.put("amount", amountValue)    // put amount
            /*val retryObj = JSONObject()
        retryObj.put("enabled", true)
        retryObj.put("max_count", 4)
        options.put("retry", retryObj)*/
            val prefill = JSONObject()
            prefill.put("email", "boltuix@gmail.com")   // put email
            prefill.put("contact", "123457891")    // put mobile number
            options.put("prefill", prefill)

            checkout.open(context, options) // open razorpay to checkout
        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
        }
    }


    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment Successfully:", Toast.LENGTH_LONG).show()
        //Toast.makeText(context, "" +p0, Toast.LENGTH_LONG).show()
    }
    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Error in payment: ", Toast.LENGTH_LONG).show()
        //Toast.makeText(context, "$p0 : $p1", Toast.LENGTH_LONG).show()
    }
}