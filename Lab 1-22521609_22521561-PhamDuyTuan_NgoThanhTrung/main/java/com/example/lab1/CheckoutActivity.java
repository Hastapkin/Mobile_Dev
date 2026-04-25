package com.example.lab1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        List<CartItem> items = CartManager.getInstance().getCartItems();

        StringBuilder sb = new StringBuilder("Order Summary:\n\n");
        for (CartItem item : items) {
            sb.append(String.format("• %s  x%d  =  $%.2f\n",
                    item.getProduct().getName(), item.getQuantity(), item.getTotalPrice()));
        }

        ((TextView) findViewById(R.id.tvSummary)).setText(sb.toString());
        ((TextView) findViewById(R.id.tvTotal)).setText(
                String.format("Total: $%.2f", CartManager.getInstance().getTotalPrice()));

        ((Button) findViewById(R.id.btnConfirm)).setOnClickListener(v -> {
            CartManager.getInstance().clearCart();
            Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_LONG).show();
            finishAffinity();
            startActivity(getIntent().setClass(this, ProductListActivity.class));
        });
    }
}
