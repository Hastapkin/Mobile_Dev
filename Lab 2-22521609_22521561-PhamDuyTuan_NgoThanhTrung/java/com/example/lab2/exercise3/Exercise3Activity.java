package com.example.lab2.exercise3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.lab2.R;

public class Exercise3Activity extends AppCompatActivity
        implements FirstFragment.OnMessageSendListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise3);

        Button btnAdd = findViewById(R.id.btnAddFragment);
        Button btnReplace = findViewById(R.id.btnReplaceFragment);
        Button btnRemove = findViewById(R.id.btnRemoveFragment);
        Button btnPop = findViewById(R.id.btnPopBackStack);
        Button btnDialog = findViewById(R.id.btnOpenDialog);

        // Add Fragment
        btnAdd.setOnClickListener(v -> {
            FirstFragment fragment = FirstFragment.newInstance("Hello from Activity");
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        // Replace with SecondFragment
        btnReplace.setOnClickListener(v -> {
            SecondFragment fragment = new SecondFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        // Remove current fragment
        btnRemove.setOnClickListener(v -> {
            FragmentManager fm = getSupportFragmentManager();
            Fragment current = fm.findFragmentById(R.id.fragmentContainer);
            if (current != null) {
                fm.beginTransaction()
                        .remove(current)
                        .commit();
            } else {
                Toast.makeText(this, "No fragment to remove", Toast.LENGTH_SHORT).show();
            }
        });

        // Pop back stack
        btnPop.setOnClickListener(v -> {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else {
                Toast.makeText(this, "Back stack is empty", Toast.LENGTH_SHORT).show();
            }
        });

        // Open DialogFragment
        btnDialog.setOnClickListener(v -> {
            MyDialogFragment dialog = new MyDialogFragment();
            dialog.show(getSupportFragmentManager(), "MyDialog");
        });
    }

    // Called by FirstFragment via interface
    @Override
    public void onMessageSend(String message) {
        SecondFragment secondFragment = new SecondFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", message);
        secondFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, secondFragment)
                .addToBackStack(null)
                .commit();
    }
}
