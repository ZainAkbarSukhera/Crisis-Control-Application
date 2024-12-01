package com.example.testing.activities;

import com.example.testing.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class DonorPaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_payment);

        // Retrieve the amount passed from DonationPostActivity
        Intent intent = getIntent();
        String amount = intent.getStringExtra("Amount");

        if (amount == null || amount.isEmpty()) {
            // Handle the case where amount is null or empty
            Toast.makeText(this, "Invalid amount received. Returning to the previous screen.", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED); // Set the result to canceled
            finish(); // Close the activity
            return;
        }

        // Display the amount in a Toast (can be replaced with actual payment logic/UI)
        Toast.makeText(this, "Amount to be paid: " + amount + " Rs", Toast.LENGTH_LONG).show();

        // Start the payment process (can be replaced with actual payment integration)
        simulatePaymentSuccess(amount);
    }

    private void simulatePaymentSuccess(String amount) {
        // Simulated payment process: Replace with actual payment logic or integration
        boolean paymentSuccessful = true; // Simulate success (can be a result of real payment logic)
        String code="abc";

        Intent resultIntent = new Intent();

        if (paymentSuccessful) {
            // Payment successful
            code="000";
            resultIntent.putExtra("pp_ResponseCode", code); // Set a meaningful response code for success
            resultIntent.putExtra("PaymentStatus", "Success");
            Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
        } else {
            // Payment failed
            code="999";
            resultIntent.putExtra("pp_ResponseCode", code); // Set a meaningful response code for failure
            resultIntent.putExtra("PaymentStatus", "Failure");
//            Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
        }

        // Return the result to the calling activity
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}