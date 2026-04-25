package com.example.lab1;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        Student s = (Student) getIntent().getSerializableExtra("student");
        if (s == null) { finish(); return; }

        ((TextView) findViewById(R.id.tvName)).setText("Name: " + s.getName());
        ((TextView) findViewById(R.id.tvScore1)).setText("Subject 1: " + s.getScore1());
        ((TextView) findViewById(R.id.tvScore2)).setText("Subject 2: " + s.getScore2());
        ((TextView) findViewById(R.id.tvScore3)).setText("Subject 3: " + s.getScore3());
        ((TextView) findViewById(R.id.tvAverage)).setText(String.format("Average: %.2f", s.getAverage()));

        TextView tvGrade = findViewById(R.id.tvGrade);
        tvGrade.setText("Grade: " + s.getGrade());
        tvGrade.setTextColor(getGradeColor(s.getGrade()));
    }

    private int getGradeColor(String grade) {
        switch (grade) {
            case "Excellent": return Color.parseColor("#2E7D32");
            case "Good":      return Color.parseColor("#1565C0");
            case "Average":   return Color.parseColor("#E65100");
            default:          return Color.RED;
        }
    }
}
