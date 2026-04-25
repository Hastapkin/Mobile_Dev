package com.example.lab1;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        EditText etName   = findViewById(R.id.etName);
        EditText etScore1 = findViewById(R.id.etScore1);
        EditText etScore2 = findViewById(R.id.etScore2);
        EditText etScore3 = findViewById(R.id.etScore3);
        Button btnSave    = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String s1   = etScore1.getText().toString().trim();
            String s2   = etScore2.getText().toString().trim();
            String s3   = etScore3.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(s1)
                    || TextUtils.isEmpty(s2) || TextUtils.isEmpty(s3)) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                float score1 = Float.parseFloat(s1);
                float score2 = Float.parseFloat(s2);
                float score3 = Float.parseFloat(s3);

                if (score1 < 0 || score1 > 10 || score2 < 0 || score2 > 10 || score3 < 0 || score3 > 10) {
                    Toast.makeText(this, "Scores must be between 0 and 10", Toast.LENGTH_SHORT).show();
                    return;
                }

                StudentListActivity.studentList.add(new Student(name, score1, score2, score3));
                finish();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid score format", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
