package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab2.exercise1.Exercise1Activity;
import com.example.lab2.exercise2.Exercise2Activity;
import com.example.lab2.exercise3.Exercise3Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnExercise1 = findViewById(R.id.btnExercise1);
        Button btnExercise2 = findViewById(R.id.btnExercise2);
        Button btnExercise3 = findViewById(R.id.btnExercise3);

        btnExercise1.setOnClickListener(v ->
                startActivity(new Intent(this, Exercise1Activity.class)));
        btnExercise2.setOnClickListener(v ->
                startActivity(new Intent(this, Exercise2Activity.class)));
        btnExercise3.setOnClickListener(v ->
                startActivity(new Intent(this, Exercise3Activity.class)));
    }
}
