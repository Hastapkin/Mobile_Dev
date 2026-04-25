package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private static final List<Product> PRODUCTS = Arrays.asList(
            new Product(1, "Laptop",      999.99),
            new Product(2, "Smartphone",  699.99),
            new Product(3, "Headphones",  149.99),
            new Product(4, "Tablet",      449.99),
            new Product(5, "Keyboard",     89.99),
            new Product(6, "Mouse",        49.99)
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button btnViewCart = findViewById(R.id.btnViewCart);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ProductAdapter(this, PRODUCTS));

        btnViewCart.setOnClickListener(v ->
                startActivity(new Intent(this, CartActivity.class)));
    }
}
