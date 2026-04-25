package com.example.lab2.exercise2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class Exercise2Activity extends AppCompatActivity {

    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise2);

        txtResult = findViewById(R.id.txtResult);

        Button btnShortToast = findViewById(R.id.btnShortToast);
        Button btnLongToast = findViewById(R.id.btnLongToast);
        Button btnSnackbar = findViewById(R.id.btnSnackbar);
        Button btnDialog = findViewById(R.id.btnDialog);
        Button btnDatePicker = findViewById(R.id.btnDatePicker);
        Button btnTimePicker = findViewById(R.id.btnTimePicker);

        // Short Toast
        btnShortToast.setOnClickListener(v ->
                Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
        );

        // Long Toast
        btnLongToast.setOnClickListener(v ->
                Toast.makeText(this, "This is a long toast", Toast.LENGTH_LONG).show()
        );

        // Snackbar with action
        btnSnackbar.setOnClickListener(v -> {
            View root = findViewById(android.R.id.content);
            Snackbar.make(root, "Item deleted", Snackbar.LENGTH_LONG)
                    .setAction("Undo", undoView -> {
                        Toast.makeText(this, "Undo clicked", Toast.LENGTH_SHORT).show();
                    })
                    .show();
        });

        // AlertDialog
        btnDialog.setOnClickListener(v ->
                new AlertDialog.Builder(this)
                        .setTitle("Confirmation")
                        .setMessage("Do you want to delete it?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                            txtResult.setText("Result: Deleted");
                        })
                        .setNegativeButton("No", null)
                        .show()
        );

        // DatePickerDialog
        btnDatePicker.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(this,
                    (view, year, month, dayOfMonth) -> {
                        String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
                        txtResult.setText("Date: " + date);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });

        // TimePickerDialog
        btnTimePicker.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog timeDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> {
                        String time = String.format("%02d:%02d", hourOfDay, minute);
                        Toast.makeText(this, time, Toast.LENGTH_SHORT).show();
                        txtResult.setText("Time: " + time);
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true);
            timeDialog.show();
        });
    }
}
