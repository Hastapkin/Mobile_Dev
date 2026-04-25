package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.btnExercise1)).setOnClickListener(v ->
                startActivity(new Intent(this, ToDoActivity.class)));

        ((Button) findViewById(R.id.btnExercise2)).setOnClickListener(v ->
                startActivity(new Intent(this, StudentListActivity.class)));

        ((Button) findViewById(R.id.btnExercise3)).setOnClickListener(v ->
                startActivity(new Intent(this, ProductListActivity.class)));
    }
}
